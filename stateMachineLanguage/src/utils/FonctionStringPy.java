package utils;

import java.util.Locale;

/*
 * 
 * Class for all python instruction to write in a python file
 * 
 */


public class FonctionStringPy {
	public FonctionStringPy () {
		
	}
	
	public String strImport() {
		return "import numpy as np\r\n"
				+ "import os\r\n"
				+ "from tensorflow import keras\r\n"
				+ "from tensorflow.keras.callbacks import Callback, EarlyStopping, TensorBoard\r\n"
				+ "from tensorflow.keras.models import Sequential, Model,load_model\r\n"
				+ "from tensorflow.keras.layers import Input, Add, Dense, Activation, ZeroPadding2D, BatchNormalization, Flatten, Conv2D, AveragePooling2D, MaxPooling2D, GlobalMaxPooling2D, GlobalAveragePooling2D, MaxPool2D, Concatenate, Dropout\r\n"
				+ "from tensorflow.keras.initializers import glorot_uniform\r\n"
				+ "from tensorflow.keras.utils import plot_model\r\n"
				+ "from keras_flops import get_flops\r\n"
				+ "from codecarbon import OfflineEmissionsTracker\r\n"
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
	
	public String writeGlobalVariable() {
		return "batch_size = 64\n"
				+ "nb_epochs = 5\n\n";
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
	
	public String writeStartCodeCarbon(String country_iso_code, String file_name, String emission_dir) {
		return "\t# start Emission tracker \n"
				+ "\ttracker = OfflineEmissionsTracker(country_iso_code=\""+ country_iso_code +"\", log_level='error', output_file=\""+ file_name + "_emissions.csv\", output_dir=\""+ emission_dir+"\")\r\n"
				+ "\ttracker.start()\n\n";
	}
	
	public String writeStopCodeCarbon() {
		return "\t# stop tracker \n"
				+ "\temissions = tracker.stop()\n\n";
	}
	
	public String writeFlops() {
		return "\t # get number of flops\n"
				+ "\tflops = get_flops(model, batch_size=batch_size)\n";
	}
	
	public String writeFunctionEarlyStopingCarbon() {
		return "class MyTrainingCallBack(Callback):\r\n"
				+ "        def __init__(self, codecarbon_tracker):\r\n"
				+ "            self.codecarbon_tracker = codecarbon_tracker\r\n"
				+ "            pass\r\n"
				+ "\r\n"
				+ "        ## Q4: How to stop training in an epoch when we pass a energy cap?\r\n"
				+ "        # Use the energy measured at section 1b as an energy cap for the\r\n"
				+ "        # training \r\n"
				+ "        # \r\n"
				+ "        # Hint: variable to tell TF to stop training: self.model.stop_training\r\n"
				+ "        # (True or False)       \r\n"
				+ "        def on_epoch_end(self, epoch, logs=None):\r\n"
				+ "            self.codecarbon_tracker.flush()\r\n"
				+ "\r\n"
				+ "            # Energy measured in the 1b run on my laptop \r\n"
				+ "            energy_cap_kwh = 0.001071591612688175\r\n"
				+ "            \r\n"
				+ "            # Getting the total energy consumption from the tracker\r\n"
				+ "            train_total_energy = self.codecarbon_tracker._total_energy.kWh\r\n"
				+ "\r\n"
				+ "            # Checking if we pass the energy cap at the end of the epoch\r\n"
				+ "            if train_total_energy >= energy_cap_kwh:\r\n"
				+ "                # command to tell TF to stop training\r\n"
				+ "                self.model.stop_training = True            \r\n"
				+ "\r\n"
				+ "        ## Q5: How to stop training in a **batch** when we pass a energy cap?\r\n"
				+ "        # Use the energy measured at section 1b as an energy cap for the\r\n"
				+ "        # training\r\n"
				+ "        #   \r\n"
				+ "        # Useful resources: Custom callbacks:\r\n"
				+ "        # https://www.tensorflow.org/guide/keras/custom_callback \r\n"
				+ "        # \r\n"
				+ "        # Hint: use self.codecarbon_tracker._measure_power_and_energy() instead\r\n"
				+ "        # of self.codecarbon_tracker.flush() to avoid IO overhead\r\n"
				+ "        ## Q6: What happens if you don't call _measure_power_and_energy() or flush()?\r\n"
				+ "        def on_batch_end(self, batch, logs=None):\r\n"
				+ "            # Energy measured in the 1b run on my laptop \r\n"
				+ "            energy_cap_kwh = 0.001071591612688175\r\n"
				+ "            \r\n"
				+ "            # Actively calling the trackers's function to get energy values\r\n"
				+ "            # Otherwise the tracker will get energy values only every 15 seconds\r\n"
				+ "            # (default value). We could use flush but flush performs IO\r\n"
				+ "            # operations and this would result in a larger overhead\r\n"
				+ "            self.codecarbon_tracker._measure_power_and_energy()\r\n"
				+ "\r\n"
				+ "            # Getting the total energy consumption from the tracker\r\n"
				+ "            train_total_energy = self.codecarbon_tracker._total_energy.kWh            \r\n"
				+ "\r\n"
				+ "            # Checking if we pass the energy cap at the end of the batch\r\n"
				+ "            if train_total_energy >= energy_cap_kwh:\r\n"
				+ "                # command to tell TF to stop training\r\n"
				+ "                self.model.stop_training = True\n";
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
		return String.format("\n\t\t%s = GlobalAveragePooling2D()("+ X_or_shortcut+ ")\n", X_or_shortcut);
	}
	
	public String writeGloMaxPooling(String X_or_shortcut) {
		return String.format("\n\t\t%s = GlobalMaxPooling2D()("+ X_or_shortcut+ ")\n", X_or_shortcut);
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
		return String.format("\t\t%s = Add()([%s, %s])\n", x1, x1, x2);
	}
	
	public String writeUpconv(String x1, int kernel) {
		return String.format("\t\t%s = UpSampling2D(size = %d)(%s)", x1, kernel, x1);
	}
	
	public String writeCallbackMethode(String tbDir) {
		return "\tes = EarlyStopping(monitor='val_loss', min_delta=0.001, verbose=1, restore_best_weights=True, patience=7)\r\n"
				+ "\ttb = TensorBoard(log_dir=\""+ tbDir +"\")\r\n"
				+ "\tlist_cb = [es, tb]\n\n";
	}
	
	public String writeCallbackMethodeCodeCarbon(String tbDir) {
		return "\tes = EarlyStopping(monitor='val_loss', min_delta=0.001, verbose=1, restore_best_weights=True, patience=7)\r\n"
				+ "\ttb = TensorBoard(log_dir=\""+ tbDir +"\")\r\n"
				+ "\tlist_cb = [es, tb, MyTrainingCallBack(tracker)]\n\n";
	}
	
	public String writeTrain() {
		return "\tstart = time()\n"
				+ "\thistory = model.fit(train_x, train_y, epochs=nb_epochs, batch_size=batch_size, validation_split=0.2, callbacks=list_cb)\n"
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
			    + "\tlog_file.write('Train result : ' + str(model.evaluate(test_x, test_y)))\n"
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
	    		+ "\tflops = 0\n"
	    		+ "\thistory = None\n"
	    		+ "\terror_file.close()\n\n";
	}
	
	public String gestionFinally(String log_dir, String file_name) {
		return "finally:\n"
			   + "\tfile = open(\""+log_dir+ "architecture_results.csv\", 'a', newline ='')\n"
			   +"\twith file:\n"
			   
			   +"\t\t# identifying header \n"  
			   +"\t\theader = ['File_name', 'Training_time(s)', 'Train_result_acc', 'Train_result_loss', 'Test_result_acc', 'Test_result_loss', 'Nb_layers', 'Epochs', "
			   + "'Flops', 'CPU_energy_consumption' , 'GPU_energy_consumption', 'RAM_energy_consumption', 'Tot_energy_consumption', 'Emissions']\n"
			   +"\t\twriter = csv.DictWriter(file, fieldnames = header)\n"
			   +"\t\tif (history != None):\n"
			   +"\t\t\tepochs = len(history.history['loss'])\n"
			      
			   +"\t\t# writing data row-wise into the csv file\n"
			   +"\t\t# writer.writeheader()\n" 
			   +"\t\twriter.writerow({'File_name' : '"+ file_name + "',\n"  
			   +"                      'Training_time(s)': training_time,  \n"
			   +"                      'Train_result_acc': train_result_acc,\n"
			   +"                      'Train_result_loss': train_result_loss,\n"
			   +"                      'Test_result_acc': test_result_acc,\n"
			   +"                      'Test_result_loss': test_result_loss,\n"
			   +"                      'Nb_layers': nb_layers,\n"
			   +"                      'Epochs' : epochs,\n"
			   +"					   'Flops' : flops,\n"
			   +"                      'CPU_energy_consumption' : tracker._total_cpu_energy.kWh,\n"
			   +"                      'GPU_energy_consumption' : tracker._total_gpu_energy.kWh,\n"
			   +"                      'RAM_energy_consumption' : tracker._total_ram_energy.kWh,\n"
			   +"                      'Tot_energy_consumption' : tracker._total_energy.kWh,\n"
			   +"                      'Emissions' : emissions\n"
			   +"                      })\n"
			   +"\t\tprint('add line into architecture_results.csv')\n"
			   +"\tfile.close()\n";
	}
}