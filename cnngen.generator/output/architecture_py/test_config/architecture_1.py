import numpy as np
import os
from tensorflow import keras
from tensorflow.keras.callbacks import Callback, EarlyStopping, TensorBoard
from tensorflow.keras.models import Sequential, Model,load_model
from tensorflow.keras.layers import Input, Add, Dense, Activation, ZeroPadding2D, BatchNormalization, Flatten, Conv2D, AveragePooling2D, MaxPooling2D, GlobalMaxPooling2D, GlobalAveragePooling2D, MaxPool2D, Concatenate, Dropout
from tensorflow.keras.initializers import glorot_uniform
from tensorflow.keras.utils import plot_model
from keras_flops import get_flops
from codecarbon import OfflineEmissionsTracker
import tensorflow as tf
import sys
import traceback
import csv
from time import time

(train_x, train_y), (test_x, test_y) = keras.datasets.mnist.load_data()

# normaliser les pixel 0-255 -> 0-1
train_x = train_x / 255.0
test_x = test_x / 255.0

train_x = tf.expand_dims(train_x, 3)
test_x = tf.expand_dims(test_x, 3)

val_x = train_x[:5000]
val_y = train_y[:5000]

batch_size = 64
nb_epochs = 1

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


# start Emission tracker 
tracker = OfflineEmissionsTracker(country_iso_code="BEL", log_level='error', output_file="architecture_1_emissions.csv", output_dir="../../architecture_emissions/test_config/" )

try:
	def getModel():
		X_input = X = Input([28,28,1])

		X1 = X
		X = AveragePooling2D(pool_size=3, strides=1, padding='same')(X)
		X = BatchNormalization(epsilon=1.1E-5, axis=3)(X)
		X = Conv2D(1, kernel_size=2, strides=1, activation='selu', padding='same')(X)
		X = Conv2D(1, kernel_size=4, strides=1, activation='selu', padding='same')(X)
		X = BatchNormalization(epsilon=1.001E-5, axis=3)(X)

		X2 = X
		X = AveragePooling2D(pool_size=4, strides=1, padding='same')(X)
		X = Conv2D(1, kernel_size=6, strides=1, activation='selu', padding='same')(X)
		X = Conv2D(1, kernel_size=2, strides=1, activation='selu', padding='same')(X)
		X = BatchNormalization(epsilon=0.001, axis=3)(X)
		X = AveragePooling2D(pool_size=3, strides=1, padding='same')(X)

		X = Add()([X, X2])
		X = Conv2D(1, kernel_size=3, strides=1, activation='selu', padding='same')(X)
		X = Conv2D(16, kernel_size=5, strides=5, activation='selu', padding='valid')(X)
		X = AveragePooling2D(pool_size=4, strides=1, padding='same')(X)

		X1 = BatchNormalization(epsilon=0.001, axis=3)(X1)
		X1 = Conv2D(16, kernel_size=1, strides=1, activation='selu', padding='same')(X1)
		X = Add()([X, X1])
		X = MaxPooling2D(pool_size=5, strides=1, padding='same')(X)
		X = Conv2D(16, kernel_size=3, strides=1, activation='selu', padding='same')(X)
		X = BatchNormalization(epsilon=1.1E-10, axis=3)(X)
		X = BatchNormalization(epsilon=1.1E-5, axis=3)(X)
		X = Conv2D(32, kernel_size=1, strides=1, activation='selu', padding='same')(X)
		X = Dropout(0.20)(X)
		X = MaxPooling2D(pool_size=5, strides=1, padding='same')(X)

		X1 = Conv2D(32, kernel_size=8, strides=5, activation='selu', padding='valid')(X1)
		X1 = BatchNormalization(epsilon=1.1E-10, axis=3)(X1)
		X1 = BatchNormalization(epsilon=1.1E-10, axis=3)(X1)
		X1 = Conv2D(32, kernel_size=4, strides=1, activation='selu', padding='same')(X1)
		X = Add()([X, X1])
		X = AveragePooling2D(pool_size=2, strides=1, padding='same')(X)
		X = Conv2D(48, kernel_size=1, strides=1, activation='selu', padding='same')(X)
		X = BatchNormalization(epsilon=1.001E-5, axis=3)(X)
		X = MaxPooling2D(pool_size=1, strides=1, padding='same')(X)

		X1 = X
		X = MaxPooling2D(pool_size=5, strides=1, padding='same')(X)
		X = Conv2D(48, kernel_size=2, strides=1, activation='selu', padding='same')(X)
		X = BatchNormalization(epsilon=0.001, axis=3)(X)
		X = BatchNormalization(epsilon=1.1E-5, axis=3)(X)
		X = Conv2D(48, kernel_size=4, strides=1, activation='selu', padding='same')(X)
		X = Dropout(0.50)(X)
		X = AveragePooling2D(pool_size=3, strides=1, padding='same')(X)

		X = Add()([X, X1])
		X = MaxPooling2D(pool_size=2, strides=2, padding='valid')(X)
		X = Conv2D(64, kernel_size=1, strides=1, activation='selu', padding='same')(X)
		X = AveragePooling2D(pool_size=1, strides=1, padding='valid')(X)

		X1 = X
		X = MaxPooling2D(pool_size=1, strides=1, padding='same')(X)
		X = BatchNormalization(epsilon=0.001, axis=3)(X)
		X = Conv2D(64, kernel_size=2, strides=1, activation='selu', padding='same')(X)
		X = Dropout(0.50)(X)
		X = BatchNormalization(epsilon=1.001E-5, axis=3)(X)
		X = Conv2D(64, kernel_size=1, strides=1, activation='selu', padding='same')(X)
		X = Dropout(0.20)(X)
		X = MaxPooling2D(pool_size=2, strides=1, padding='same')(X)

		X = Add()([X, X1])
		X = Dropout(0.50)(X)
		X = MaxPooling2D(pool_size=1, strides=1, padding='valid')(X)

		X = GlobalAveragePooling2D()(X)
		X = Dense(72, activation='selu')(X)
		X = Dropout(0.01)(X)
		X = Dense(34, activation='selu')(X)
		X = Dropout(0.80)(X)
		X = Dense(10, activation='softmax')(X)
		model = Model(inputs=X_input, outputs=X)
		return model

	model = getModel()
	nb_params = model.count_params()
	plot_model(model, show_shapes=True, to_file="../../architecture_img/test_config/architecture_1.png")
	model.compile(optimizer='adam', loss=keras.losses.sparse_categorical_crossentropy, metrics=['accuracy'])

	es = EarlyStopping(monitor='val_loss', min_delta=0.001, verbose=1, restore_best_weights=True, patience=7)
	tb = TensorBoard(log_dir="../../architecture_tb/test_config/architecture_1")
	list_cb = [es, tb]

	tracker.start()
	start = time()
	history = model.fit(train_x, train_y, epochs=nb_epochs, batch_size=batch_size, validation_split=0.2, callbacks=list_cb)

	training_time = time()-start

	# stop tracker 
	emissions = tracker.stop()

	 # get number of flops
	flops = get_flops(model, batch_size=batch_size)
	log_file = open( "../../architecture_log/test_config/architecture_1.log", "w")

	# save test result
	log_file.write('test result : ' + str(model.evaluate(test_x, test_y)))
	test_result_loss = model.evaluate(test_x, test_y)[0]
	test_result_acc = model.evaluate(test_x, test_y)[1]

	# save train result
	log_file.write('Train result : ' + str(model.evaluate(test_x, test_y)))
	log_file.write('History train result : ' + str(history.history))
	train_result_loss = model.evaluate(train_x, train_y)[0]
	train_result_acc = model.evaluate(train_x, train_y)[1]
	print('OK: file ../../architecture_log/test_config/architecture_1.log has been create')
	nb_layers = len(model.layers)
	log_file.close()


# something go wrong
except:
	print('error: file ../../architecture_log/test_config/architecture_1_error.log has been create')
	error_file = open("../../architecture_log/test_config/architecture_1_error.log" , "w")
	traceback.print_exc(file=error_file)
	result_loss = "Error"
	result_acc = "Error"
	epochs = 0
	flops = 0
	history = None
	error_file.close()

finally:
	file = open("../../architecture_csv/test_config/architecture_results.csv", 'a', newline ='')
	with file:
		# identifying header 
		header = ['File_name', 'Training_time(s)', 'Train_result_acc', 'Train_result_loss', 'Test_result_acc', 'Test_result_loss', 'Nb_layers', 'Epochs', 'Flops', 'nb_params' ]
		writer = csv.DictWriter(file, fieldnames = header)
		if (history != None):
			epochs = len(history.history['loss'])
		# writing data row-wise into the csv file
		# writer.writeheader()
		writer.writerow({'File_name' : 'architecture_1',
                      'Training_time(s)': training_time,  
                      'Train_result_acc': train_result_acc,
                      'Train_result_loss': train_result_loss,
                      'Test_result_acc': test_result_acc,
                      'Test_result_loss': test_result_loss,
                      'Nb_layers': nb_layers,
                      'Epochs' : epochs,
					   'Flops' : flops,
                      'nb_params' : nb_params
                      })
		print('add line into architecture_results.csv')
	file.close()

