package utils;

import java.util.Locale;

public class FonctionStringPy {
	public FonctionStringPy () {
		
	}
	
	public String strImport() {
		return "import numpy as np\r\n"
				+ "import os\r\n"
				+ "from tensorflow import keras\r\n"
				+ "from tensorflow.keras.callbacks import EarlyStopping\r\n"
				+ "from tensorflow.keras.models import Sequential, Model,load_model\r\n"
				+ "from tensorflow.keras.layers import Input, Add, Dense, Activation, ZeroPadding2D, BatchNormalization, Flatten, Conv2D, AveragePooling2D, MaxPooling2D, GlobalMaxPooling2D, GlobalAveragePooling2D, MaxPool2D, Concatenate, Dropout\r\n"
				+ "from tensorflow.keras.initializers import glorot_uniform\r\n"
				+ "from tensorflow.keras.utils import plot_model\r\n"
				+ "import tensorflow as tf\r\n"
				+ "import sys\r\n"
				+ "import traceback\r\n"
				+ "import csv\r\n"
				+ "from time import time\n\n";
	}
	
	public String writeMnistDataSet() {
		return "(train_x, train_y), (test_x, test_y) = keras.datasets.mnist.load_data()\r\n"
				+ "\r\n"
				+ "# normaliser les pixel 0-255 -> 0-1\r\n"
				+ "train_x = train_x / 255.0\r\n"
				+ "test_x = test_x / 255.0\r\n"
				+ "\r\n"
				+ "train_x = tf.expand_dims(train_x, 3)\r\n"
				+ "test_x = tf.expand_dims(test_x, 3)\r\n"
				+ "\r\n"
				+ "val_x = train_x[:5000]\r\n"
				+ "val_y = train_y[:5000]\n\n";
	}
	
	public String writecifar10Dataset() {
		return "# load dataset\r\n"
				+ "(train_x, train_y), (test_x, test_y) = keras.datasets.cifar10.load_data()\r\n"
				+ "\r\n"
				+ "# normalize to range 0-1\r\n"
				+ "train_x = train_x / 255.0\r\n"
				+ "test_x = test_x / 255.0\r\n"
				+ "\r\n"
				+ "val_x = train_x[:5000]\r\n"
				+ "val_y = train_y[:5000]\r\n"
				+ "\n\n";
	}
	
	public String writeInitValue() {
		return "# init training time\r\n"
				+ "training_time = 0\r\n"
				+ "# init result test/train\r\n"
				+ "test_result_loss = \"\"\r\n"
				+ "test_result_acc = \"\"\r\n"
				+ "\r\n"
				+ "train_result_loss = \"\"\r\n"
				+ "train_result_acc = \"\"\r\n"
				+ "\r\n"
				+ "nb_layers = \"not build\"\r\n"
				+ "history = None\n"
				+ "epochs = 0\n"
				+ "\r\n\n";
	}
	
	public String writeInput(String shape, String x) {
		return String.format("\t\tX_input = %s = Input(%s)\n", x, shape);
	}
	
	public String writeConv(int nbFilter, int kernel, int stride, String fct_activation, String padding, String X_or_shortcut) {
		return String.format("\t\t%s = Conv2D(%d, kernel_size=%d, strides=%d, activation='%s', padding='%s')(" + X_or_shortcut+ ")\n", 
				X_or_shortcut, nbFilter, kernel, stride, fct_activation, padding );
	}
	
	
	public String writeAvgPooling(int kernel, int stride, String padding, String X_or_shortcut) {
		return String.format("\t\t%s = AveragePooling2D(pool_size=%d, strides=%d, padding='%s')(" + X_or_shortcut+ ")\n",
				X_or_shortcut, kernel, stride, padding);
	}
	
	public String writeMaxPooling(int kernel, int stride, String padding, String X_or_shortcut) {
		return String.format("\t\t%s = MaxPooling2D(pool_size=%d, strides=%d, padding='%s')(" + X_or_shortcut+ ")\n",
				X_or_shortcut, kernel, stride, padding);
	}
	
	public String writeDropout(double dropoutRate, String X_or_shortcut) {
		return String.format(Locale.US,"\t\t%s = Dropout(%.2f)(" + X_or_shortcut+ ")\n", X_or_shortcut, dropoutRate);
	}
	
	public String writeBN(double epsilon, String X_or_shortcut) {
		return String.format(Locale.US,"\t\t%s = BatchNormalization(epsilon=%.2f, axis=3)" + "(" + X_or_shortcut+ ")\n", 
				X_or_shortcut, epsilon);
	}
	
	public String writeGloAvgPooling(String X_or_shortcut) {
		return String.format("\n\t\t%s GlobalAveragePooling2D()("+ X_or_shortcut+ ")\n", X_or_shortcut);
	}
	
	public String writeGloMaxPooling(String X_or_shortcut) {
		return String.format("\n\t\t%s GlobalMaxPooling2D()("+ X_or_shortcut+ ")\n", X_or_shortcut);
	}
	
	public String writeFlatten(String X_or_shortcut) {
		return String.format("\n\t\t%s = Flatten()("+ X_or_shortcut+ ")\n", X_or_shortcut);
	}
	
	public String writeDense(int nb_neurones, String fct_activation, String X_or_shortcut) {
		return String.format("\t\t%s = Dense(%d, activation='%s')("+ X_or_shortcut+ ")\n",
				X_or_shortcut, nb_neurones, fct_activation);
	}
	
	public String writeInitMerge(String x1, String x2) {
		return String.format("\n\t\t%s = %s\n", x2, x1);
	}
	
	public String writeConcat(String x1, String x2) {
		return String.format("\t\t%s = Concatenate()([%s, %s])\n", x1, x1, x2);
	}
	
	public String writeAdd(String x1, String x2) {
		return String.format("\t\tX = Add()([%s, %s])\n", x1, x2);
	}
	
	public String writeUpconv(String x1, int kernel) {
		return String.format("\t\t%s = UpSampling2D(size = %d)(%s)", x1, kernel, x1);
	}
	
	public String writeTrain() {
		return "\tstart = time()\n"
				+ "\tes = tf.keras.callbacks.EarlyStopping(monitor='loss', verbose=1, restore_best_weights=True, patience=8)\r\n"
				+ "\tlist_cb = [es]\n"
				+ "\thistory = model.fit(train_x, train_y, epochs=5, batch_size=64, validation_split=0.3, callbacks=list_cb)\n"
				+ "\ttraining_time = time()-start\n"
				+ "\tprint(model.evaluate(test_x, test_y))\n\n";
	}
	
	public String gestionGood(String log_dir, String file_name) {
		String log_file = log_dir + file_name +".log";
		return "\tlog_file = open( \""+ log_file + "\", \"w\")\n"
			    + "\n\t# save test result\n"
			    + "\tlog_file.write('test result : ' + str(model.evaluate(test_x, test_y)))\n"
			    + "\ttest_result_loss = model.evaluate(test_x, test_y)[0]\n"
			    + "\ttest_result_acc = model.evaluate(test_x, test_y)[1]\n"
			    
			    + "\n\t# save train result\n"
			    + "\tlog_file.write('train result : ' + str(model.evaluate(test_x, test_y)))\n"
			    + "\tlog_file.write('History train result : ' + str(history.history))\n"
			    + "\ttrain_result_loss = model.evaluate(train_x, train_y)[0]\n"
			    + "\ttrain_result_acc = model.evaluate(train_x, train_y)[1]\n"
			    
			    + "\tprint('OK: file " + log_file + " has been create')\n"
			    
			    + "\tnb_layers = len(model.layers)\n"
			    + "\tlog_file.close()\n\n";
	}
	
	public String gestionError(String log_dir, String file_name) {
	    
	    String error_file = log_dir + file_name + "_error.log";
	    return "\n# something go wrong\n"
	    		+ "except:\n"
	    		+ "\tprint('error: file " + error_file + " has been create')\n"
	    		+ "\terror_file = open(\"" + error_file + "\" , \"w\")\n"
	    		+ "\ttraceback.print_exc(file=error_file)\n"
	    		+ "\tresult_loss = \"Error\"\n"
	    		+ "\tresult_acc = \"Error\"\n"
	    		+ "\tepochs = 0\n"
	    		+ "\thistory = None\n"
	    		+ "\terror_file.close()\n\n";
	}
	
	public String gestionFinally(String file_name) {
		return "finally:\n"
			   + "\tfile = open('../architecture_results.csv', 'a', newline ='')\n"
			   +"\twith file:\n"
			   
			   +"\t\t# identifying header \n"  
			   +"\t\theader = ['file_name', 'training_time(s)', 'test_result_loss', 'test_result_acc', 'train_result_acc', 'train_result_loss', 'nb_layers', 'epochs']\n"
			   +"\t\twriter = csv.DictWriter(file, fieldnames = header)\n"
			   +"\t\tif (history != None):\n"
			   +"\t\t\tepochs = len(history.history['loss'])\n"
			      
			   +"\t\t# writing data row-wise into the csv file\n"
			   +"\t\t# writer.writeheader()\n" 
			   +"\t\twriter.writerow({'file_name' : '"+ file_name + "',\n"  
			   +"                      'training_time(s)': training_time,  \n"
			   +"                      'test_result_loss': test_result_loss,\n"
			   +"                      'test_result_acc': test_result_acc,\n"
			   +"                      'train_result_acc': train_result_acc,\n"
			   +"                      'train_result_loss': train_result_loss,\n"
			   +"                      'nb_layers': nb_layers,\n"
			   +"                      'epochs' : epochs\n"
			   +"                      })\n"
			   +"\t\tprint('add line into architecture_results_v4.csv')\n"
			   +"\tfile.close()\n";
	}
}