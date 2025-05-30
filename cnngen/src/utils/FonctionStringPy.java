package utils;

import java.util.Locale;

import org.testng.annotations.Ignore;

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
				+ "from codecarbon import OfflineEmissionsTracker\r\n"
				+ "import tensorflow as tf\r\n"
				+ "import sys\r\n"
				+ "import traceback\r\n"
				+ "import csv\r\n"
				+ "from time import time\n\n"
				+ "from PIL import Image\n\n";
	}
	
	
	private String upscale() {
		return "def upscale_images(images, new_size): \r\n"
				+ "	upscaled_images = [] \r\n"
				+ "	for img in images: \r\n"
				+ "		img_array = np.array(img)\r\n"
				+ "		img_upscaled = np.expand_dims(np.array(Image.fromarray(img_array).resize(new_size)), axis=-1)\r\n"
				+ "		upscaled_images.append(img_upscaled)\r\n"
				+ "	return np.array(upscaled_images)\r\n";
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
	
	public String writeFashionMnistDataSet() {
		
		return  upscale() + "(train_x, train_y), (test_x, test_y) = keras.datasets.fashion_mnist.load_data()\r\n"
				+ "new_size = (32, 32)\r\n"
				+ "train_x = upscale_images(train_x, new_size)\r\n"
				+ "test_x = upscale_images(test_x, new_size)\r\n"
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
	
	public String writecifar100Dataset() {
		return "# load dataset\r\n"
				+ "(train_x, train_y), (test_x, test_y) = keras.datasets.cifar100.load_data()\r\n"
				+ "\r\n"
				+ "# normalize to range 0-1\r\n"
				+ "train_x = train_x / 255.0\r\n"
				+ "test_x = test_x / 255.0\r\n"
				+ "\r\n"
				+ "val_x = train_x[:5000]\r\n"
				+ "val_y = train_y[:5000]\r\n"
				+ "\n\n";
	}
	
	public String writeGlobalVariable(int batch_size, int epochs) {
		return "batch_size = "+ batch_size+"\n"
				+ "nb_epochs = "+epochs+"\n\n";
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
				+ "nb_params = 0\n"
				+ "\r\n\n";
	}
	
	public String writeInitTrackerCodeCarbon(String country_iso_code, String file_name, String exp_dir, String emission_dir) {
		return "# start Emission tracker \n"
				+ "tracker = OfflineEmissionsTracker(country_iso_code=\""+ country_iso_code +"\", log_level='error', output_file=\""+ file_name + "_emissions.csv\", output_dir=\""+ emission_dir+exp_dir+"\" )\r\n\n";
	}
	
	public String writeStopCodeCarbon() {
		return "\t# stop tracker \n"
				+ "\temissions = tracker.stop()\n\n";
	}
	
	public String writeModel(String out) {
		return "\t\tmodel = Model(inputs=X_input, outputs="+ out + ")\n"
				+ "\t\treturn model\n\n"
				+ "\tmodel = getModel()\n"
				+ "\tnb_params = model.count_params()\n";
		 
	}
	
	public String writeImg(String png_dir, String exp_dir, String file_name) {
		return String.format("\tplot_model(model, show_shapes=True, to_file=\"%s\")\n", 
    			png_dir+exp_dir+file_name+".png"
    		);
    		
	}
	
	public String writeCompile() {
		return "\tmodel.compile(optimizer='adam', loss=keras.losses.sparse_categorical_crossentropy, metrics=['accuracy'])\n\n";
	}
	
	public String writeFunctionEarlyStopingCarbon() {
		return "class MyTrainingCallBack(Callback):\r\n"
				+ "        def __init__(self, codecarbon_tracker):\r\n"
				+ "            self.codecarbon_tracker = codecarbon_tracker\r\n"
				+ "            pass\r\n"
				+ "\r\n"
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
				+ "        # Use the energy measured at section 1b as an energy cap for the\r\n"
				+ "        # training\r\n"
				+ "        #   \r\n"
				+ "        # Useful resources: Custom callbacks:\r\n"
				+ "        # https://www.tensorflow.org/guide/keras/custom_callback \r\n"
				+ "        # \r\n"
				+ "        # Hint: use self.codecarbon_tracker._measure_power_and_energy() instead\r\n"
				+ "        # of self.codecarbon_tracker.flush() to avoid IO overhead\r\n"
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
		return String.format(Locale.US,"\t\t%s = BatchNormalization(epsilon=%s, axis=3)" + "(" + X_or_shortcut+ ")\n", 
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
	
	private String writeEarlyStopping() {
		return "\tes = EarlyStopping(monitor='val_loss', min_delta=0.001, verbose=1, restore_best_weights=True, patience=7)\r\n";
	}
	
	private String writeTensorBoard(String tbDir) {
		return "\ttb = TensorBoard(log_dir=\""+ tbDir +"\")\r\n";
	}
	
	public String writeCallbackMethode(String tbDir, boolean es, boolean tb) throws Exception {
		String cb = "";
		if(es == true && tb == true) {
			cb += writeEarlyStopping();
			cb += writeTensorBoard(tbDir);
			cb += "\tlist_cb = [es, tb]\n\n";
		}else if(es == true && tb == false) {
			cb += writeEarlyStopping();
			cb += "\tlist_cb = [es]\n\n";
		}else if (es == false && tb == true) {
			cb += writeTensorBoard(tbDir);
			cb += "\tlist_cb = [tb]\n\n";
		}else if (es == false && tb == false) {
			cb += "\tlist_cb = []\n\n";
		}else {
			throw new Exception("writeCallbackMethode");
		}
		
		return cb;
				
	}
	
	public String writeCallbackMethodeCodeCarbon(String tbDir) {
		return "\tes = EarlyStopping(monitor='val_loss', min_delta=0.001, verbose=1, restore_best_weights=True, patience=7)\r\n"
				+ "\ttb = TensorBoard(log_dir=\""+ tbDir +"\")\r\n"
				+ "\tlist_cb = [es, tb, MyTrainingCallBack(tracker)]\n\n";
	}
	
	public String writeStartCodeCarbonTracker() {
		return "\ttracker.start()\n";
	}
	
	public String writeTrain() {
		return "\tstart = time()\n"
				+ "\thistory = model.fit(train_x, train_y, epochs=nb_epochs, batch_size=batch_size, validation_split=0.2, callbacks=list_cb, verbose=0)\n\n"
				+ "\ttraining_time = time()-start\n\n";
	}
	
	public String gestionGood(String log_dir, String file_name) {
		String log_file = log_dir + file_name +".log";
		return "\tlog_file = open( \""+ log_file + "\", \"w\")\n"
			    + "\n\t# save test result\n"
			    + "\tlog_file.write('test result : ' + str(model.evaluate(test_x, test_y, verbose=0)))\n"
			    + "\ttest_result_loss = model.evaluate(test_x, test_y, verbose=0)[0]\n"
			    + "\ttest_result_acc = model.evaluate(test_x, test_y, verbose=0)[1]\n"
			    
			    + "\n\t# save train result\n"
			    + "\tlog_file.write('Train result : ' + str(model.evaluate(test_x, test_y, verbose=0)))\n"
			    + "\tlog_file.write('History train result : ' + str(history.history))\n"
			    + "\ttrain_result_loss = model.evaluate(train_x, train_y,verbose=0)[0]\n"
			    + "\ttrain_result_acc = model.evaluate(train_x, train_y, verbose=0)[1]\n"
			    
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
	
	public String gestionFinally(String log_dir, String file_name, float flops) {
		return "finally:\n"
			   + "\tfile = open(\""+log_dir+ "architecture_results.csv\", 'a', newline ='')\n"
			   +"\twith file:\n"
			   
			   +"\t\t# identifying header \n"  
			   +"\t\theader = ['File_name', 'Training_time(s)', 'Train_result_acc', 'Train_result_loss', 'Test_result_acc', 'Test_result_loss', 'Nb_layers', 'Epochs', "
			   + "'Flops', 'nb_params' ]\n"
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
			   +"					   'Flops' : " + flops + ",\n"
			   +"                      'nb_params' : nb_params\n"
			   +"                      })\n"
			   +"\t\tprint('add line into architecture_results.csv')\n"
			   +"\tfile.close()\n";
	}
}