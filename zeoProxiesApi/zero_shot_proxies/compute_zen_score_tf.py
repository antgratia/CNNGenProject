from operator import mod
from flask import session
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
from tensorflow.keras.models import Model, Sequential
import tensorflow.keras.backend as K
import numpy as np
import time




def compute_zen_score(list_layer, list_stride, filename, batch_size, resolution, dim, repeat, mixup_gamma):

    time_start = time.time()
    info = {}
    nas_score_list = []
    pooling = 0
    dropout = 0
    nb_layer = 0

    for i in range(repeat):
        model, pooling, dropout, nb_layer = init_model(list_layer, list_stride)

        input = tf.random.normal([batch_size,resolution,resolution,dim])
        input2 = tf.random.normal([batch_size,resolution,resolution,dim])
        mixup_input = input + mixup_gamma * input2

        output = model(input, training=True)
        mixup_output = model(mixup_input, training=True)

        nas_score = tf.math.reduce_sum(tf.math.abs(output - mixup_output),axis=[1,2,3])
        nas_score = tf.math.reduce_mean(nas_score, keepdims=True)


        #compute bn scaling
        log_bn_scaling_factor = 0.0
        #bn_scaling_factor = 0.0
        for i in model.layers:
            if isinstance(i, layers.BatchNormalization):
                print(i.moving_variance)
                bn_scaling_factor = tf.math.sqrt(tf.math.reduce_mean(i.moving_variance))
                log_bn_scaling_factor += tf.math.log(bn_scaling_factor)
            pass
        pass

        #print(bn_scaling_factor)
        #print(tf.math.log(bn_scaling_factor))

        print(log_bn_scaling_factor)
        nas_score = tf.math.log(nas_score) + log_bn_scaling_factor
        nas_score_list.append(float(nas_score))

    print(nas_score_list)

    std_nas_score = np.std(nas_score_list)
    avg_precision = 1.96 * std_nas_score / np.sqrt(len(nas_score_list))
    avg_nas_score = np.mean(nas_score_list)
    
    time_glo = (time.time() - time_start)  
    time_cost = time_glo/repeat
    info['file_name'] = filename
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

def init_model(list_layer,list_stride):
    pooling = 0
    dropout = 0
    nb_layer = 0
    index_stride = 0
    filter = 32
    model = Sequential()
    for i in list_layer:
        if (i == "conv"):
            model.add(layers.Conv2D(filter,kernel_size=int(list_stride[index_stride]), strides=1, padding="same", activation='relu',kernel_initializer=tf.random_normal_initializer, bias_initializer=tf.zeros_initializer))
            nb_layer +=1
            index_stride += 1
            filter*2
        elif (i == "bn"):
            model.add(layers.BatchNormalization(epsilon=1e-05, momentum=0.1))
            nb_layer +=1
        elif(i == "pooling"):
            model.add(layers.MaxPool2D())
            pooling +=1
        elif(i == "dropout"):
            model.add(layers.Dropout(rate=0.5))
            dropout += 1
        else: continue
    return model, pooling, dropout, nb_layer