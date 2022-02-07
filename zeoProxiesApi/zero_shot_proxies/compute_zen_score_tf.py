from operator import mod
from flask import session
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
from tensorflow.keras.models import Model, Sequential
import tensorflow.keras.backend as K
import numpy as np
import time




def compute_zen_score(list_layer , filename, batch_size, resolution, mixup_gamma):

    time_start = time.time()
    info = {}
    nas_score_list = []
    pooling = 0
    dropout = 0
    nb_layer = 0

    for i in range(32):
        model, pooling, dropout, nb_layer = init_model(list_layer)

        input = tf.random.normal([batch_size,resolution,resolution,1])
        input2 = tf.random.normal([batch_size,resolution,resolution,1])
        mixup_input = input + mixup_gamma * input2
 
        output = model(input)
        mixup_output = model(mixup_input)

        nas_score = tf.math.reduce_sum(tf.math.abs(output - mixup_output),axis=[1,2,3])
        nas_score = tf.math.reduce_mean(nas_score, keepdims=True)
        
        #compute bn scaling
        log_bn_scaling_factor = 0.0
        for i in model.layers:
            if isinstance(i, layers.BatchNormalization):
                bn_scaling_factor = tf.math.sqrt(tf.math.reduce_mean(i.moving_variance))
                log_bn_scaling_factor += tf.math.log(bn_scaling_factor)
            pass
        pass

        nas_score = tf.math.log(nas_score) + log_bn_scaling_factor
        nas_score_list.append(float(nas_score))

    std_nas_score = np.std(nas_score_list)
    avg_precision = 1.96 * std_nas_score / np.sqrt(len(nas_score_list))
    avg_nas_score = np.mean(nas_score_list)
    
    time_glo = (time.time() - time_start)  
    time_cost = time_glo/32
    info['filename'] = filename
    info['pooling'] = pooling
    info['dropout'] = dropout
    info['nb_layer'] = nb_layer
    info['nb_layer_tot'] = nb_layer + pooling + dropout
    info['time_glo'] = time_glo
    info['time_cost'] = time_cost
    info['avg_nas_score'] = float(avg_nas_score)
    info['std_nas_score'] = float(std_nas_score)
    info['avg_precision'] = float(avg_precision)

    return info

def init_model(list_layer):
    pooling = 0
    dropout = 0
    nb_layer = 0
    model = Sequential()
    for i in list_layer:
        if (i == "conv"):
            model.add(layers.Conv2D(32,kernel_size=1,kernel_initializer=tf.random_normal_initializer, bias_initializer=tf.zeros_initializer))
            nb_layer +=1
        elif (i == "bn"):
            model.add(layers.BatchNormalization())
            nb_layer +=1
        elif(i == "pooling"):
            pooling +=1
        elif(i == "dropout"):
            dropout += 1
        else: continue
    return model, pooling, dropout, nb_layer