import numpy as np
import os
from tensorflow import keras
from tensorflow.keras.callbacks import EarlyStopping, TensorBoard
from tensorflow.keras.models import Sequential, Model,load_model
from tensorflow.keras.layers import Input, Add, Dense, Activation, ZeroPadding2D, BatchNormalization, Flatten, Conv2D, AveragePooling2D, MaxPooling2D, GlobalMaxPooling2D, GlobalAveragePooling2D, MaxPool2D, Concatenate, Dropout
from tensorflow.keras.initializers import glorot_uniform
from tensorflow.keras.utils import plot_model
import tensorflow as tf
import sys
import traceback
import csv
from time import time

# load dataset
(train_x, train_y), (test_x, test_y) = keras.datasets.cifar10.load_data()

# normalize to range 0-1
train_x = train_x / 255.0
test_x = test_x / 255.0

val_x = train_x[:5000]
val_y = train_y[:5000]


# init training time
training_time = 0
# init result test/train
test_result_loss = ""
test_result_acc = ""

train_result_loss = ""
train_result_acc = ""

nb_layers = "not build"
history = None
epochs = 0


try:
	def getModel():
		X_input = X = Input([32, 32, 3])
		X = BatchNormalization(epsilon=0.00, axis=3)(X)
		X = Conv2D(16, kernel_size=5, strides=1, activation='relu', padding='same')(X)
		X = MaxPooling2D(pool_size=1, strides=1, padding='valid')(X)
		X = Conv2D(32, kernel_size=1, strides=1, activation='relu', padding='valid')(X)
		X = MaxPooling2D(pool_size=4, strides=4, padding='same')(X)
		X = Conv2D(48, kernel_size=1, strides=1, activation='tanh', padding='valid')(X)
		X = BatchNormalization(epsilon=0.00, axis=3)(X)
		X = Dropout(0.80)(X)
		X = MaxPooling2D(pool_size=2, strides=1, padding='valid')(X)
		X = Conv2D(64, kernel_size=3, strides=2, activation='tanh', padding='same')(X)
		X = BatchNormalization(epsilon=0.00, axis=3)(X)

		X = Flatten()(X)
		X = Dropout(0.50)(X)
		X = Dense(10, activation='softmax')(X)
		model = Model(inputs=X_input, outputs=X)
		return model

	model = getModel()
	plot_model(model, show_shapes=True, to_file="../../architecture_img/tensorboard_1/architecture_2.png")
	model.compile(optimizer='adam', loss=keras.losses.sparse_categorical_crossentropy, metrics=['accuracy'])

	es = EarlyStopping(monitor='loss', min_delta=0.001, verbose=1, restore_best_weights=True, patience=7)
	tb = TensorBoard(log_dir="../../architecture_tb/tensorboard_1/architecture_2")
	list_cb = [es, tb]
	start = time()
	history = model.fit(train_x, train_y, epochs=100, batch_size=64, validation_split=0.3, callbacks=list_cb)
	training_time = time()-start
	print(model.evaluate(test_x, test_y))

	log_file = open( "../../architecture_log/tensorboard_1/architecture_2.log", "w")

	# save test result
	log_file.write('test result : ' + str(model.evaluate(test_x, test_y)))
	test_result_loss = model.evaluate(test_x, test_y)[0]
	test_result_acc = model.evaluate(test_x, test_y)[1]

	# save train result
	log_file.write('train result : ' + str(model.evaluate(test_x, test_y)))
	log_file.write('History train result : ' + str(history.history))
	train_result_loss = model.evaluate(train_x, train_y)[0]
	train_result_acc = model.evaluate(train_x, train_y)[1]
	print('OK: file ../../architecture_log/tensorboard_1/architecture_2.log has been create')
	nb_layers = len(model.layers)
	log_file.close()


# something go wrong
except:
	print('error: file ../../architecture_log/tensorboard_1/architecture_2_error.log has been create')
	error_file = open("../../architecture_log/tensorboard_1/architecture_2_error.log" , "w")
	traceback.print_exc(file=error_file)
	result_loss = "Error"
	result_acc = "Error"
	epochs = 0
	history = None
	error_file.close()

finally:
	file = open("../../architecture_csv/tensorboard_1/architecture_results.csv", 'a', newline ='')
	with file:
		# identifying header 
		header = ['file_name', 'training_time(s)', 'test_result_loss', 'test_result_acc', 'train_result_acc', 'train_result_loss', 'nb_layers', 'epochs']
		writer = csv.DictWriter(file, fieldnames = header)
		if (history != None):
			epochs = len(history.history['loss'])
		# writing data row-wise into the csv file
		# writer.writeheader()
		writer.writerow({'file_name' : 'architecture_2',
                      'training_time(s)': training_time,  
                      'test_result_loss': test_result_loss,
                      'test_result_acc': test_result_acc,
                      'train_result_acc': train_result_acc,
                      'train_result_loss': train_result_loss,
                      'nb_layers': nb_layers,
                      'epochs' : epochs
                      })
		print('add line into architecture_results.csv')
	file.close()

