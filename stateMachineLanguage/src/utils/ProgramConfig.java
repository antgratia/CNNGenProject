package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import lombok.Getter;


public class ProgramConfig {
	@Getter
	private Properties prop = new Properties();
	//private String fileName = "../../app.config";
	private String fileName = "app.config";
	
	public ProgramConfig() {
		
		try (FileInputStream fis = new FileInputStream(fileName)) {
		    prop.load(fis);
		} catch (FileNotFoundException ex) {
		    System.out.println(ex);
		} catch (IOException ex) {
		    System.out.println(ex);
		}
	}
	
	private List<Integer> arrayStringToArrayInt(String[] array) {
		List<Integer> intArray = new ArrayList<Integer>(array.length);
		for (int i = 0; i < array.length; i++) {
			intArray.add(Integer.parseInt(array[i]));
        }
		
		return intArray;
	}
	
	private List<Double> arrayStringToArrayDouble(String[] array) {
		List<Double> intArray = new ArrayList<Double>(array.length);
		for (int i = 0; i < array.length; i++) {
			intArray.add(Double.parseDouble(array[i]));
        }
		
		return intArray;
	}
	
	// General
	public String getContryIsoCode() {
		return prop.getProperty("CONTRY_ISO_CODE");
	}
	public String getDataset() {
		return prop.getProperty("DATASET");
	}
	
	// Directories
	public String getOutputDir() {
		return prop.getProperty("OUTPUT_DIR");
	}
	public String getPyDir() {
		return prop.getProperty("PY_DIR");
	}
	public String getSMLDir() {
		return prop.getProperty("SML_DIR");
	}
	public String getLogDir() {
		return prop.getProperty("LOG_DIR");
	}
	public String getPngDir() {
		return prop.getProperty("PNG_DIR");
	}
	public String getCSVDir() {
		return prop.getProperty("CSV_DIR");
	}
	public String getTensorboardDir() {
		return prop.getProperty("TENSORBOARD_DIR");
	}
	public String getEmissionDir() {
		return prop.getProperty("EMISSION_DIR");
	}
	
	// Generation Archi
	public List<String> getFlattenOrGlobalPool() {
		return Arrays.asList(prop.getProperty("FLAT_GLO").split(","));
	}
	public List<String> getPoolingOrNot() {
		float proba = Float.parseFloat(prop.getProperty("POOLING"));
		int nb = (int) (1.0/proba);
		List<String> PoolingOrNot = new ArrayList<>();
		while(nb-2>0) {
			PoolingOrNot.add("");
			nb--;
		}
		PoolingOrNot.add("avg_pooling");
		PoolingOrNot.add("max_pooling");
		return PoolingOrNot;
	}
	public List<String> getDropout() {
		float proba = Float.parseFloat(prop.getProperty("DROPOUT"));
		int nb = (int) (1.0/proba);
		List<String> dropout = new ArrayList<>();
		
		while(nb-1>0) {
			dropout.add("");
			nb--;
		}
		dropout.add("dropout");
		return dropout;
	}
	public List<String> getConvolution() {
		return Arrays.asList(prop.getProperty("CONVOLUTION").split(","));
	}
	public List<String> getConvOrMerge() {
		return Arrays.asList(prop.getProperty("CONV_MERGE").split(","));
	}
	public List<String> getConvOrEmpty() {
		return Arrays.asList(prop.getProperty("CONV_EMPTY").split(","));
	}
	public List<Integer> getNbFeatureExtraction() {
		return arrayStringToArrayInt(prop.getProperty("NB_FEATURE_EXTRACTION").split(","));
	}
	public List<Integer> getNbDense() {
		return arrayStringToArrayInt(prop.getProperty("NB_DENSE").split(","));
	}
	public List<Integer> getNbOtherZero() {
		return arrayStringToArrayInt(prop.getProperty("NB_OTHER_ZERO").split(","));
	}
	public List<Integer> getNbOther() {
		return arrayStringToArrayInt(prop.getProperty("NB_OTHER").split(","));
	}

	// hyperparameters
	
	// Directories
	public int getEpochs() {
		return  Integer.parseInt(prop.getProperty("EPOCHS"));
	}
	
	public int getBatchSize() {
		return  Integer.parseInt(prop.getProperty("BATCH_SIZE"));
	}
	
	public int getMaxSizeImg() {
		return Integer.parseInt(prop.getProperty("MAX_SIZE_IMG"));
	}
	public int getInputFilter() {
		return Integer.parseInt(prop.getProperty("INPUT_FILTER"));
	}
	public int getInitNbFilter() {
		return Integer.parseInt(prop.getProperty("INIT_NB_FILTER"));
	}
	public int getNbClass() {
		return Integer.parseInt(prop.getProperty("NB_CLASS"));
	}
	public String getFonctionClassification() {
		return prop.getProperty("FONCTION_CLASSIFICATION");
	}
	public List<Integer> getKernelValues() {
		return arrayStringToArrayInt(prop.getProperty("KERNEL_VALUES").split(","));
	}
	public List<Integer> getStringValues() {
		return arrayStringToArrayInt(prop.getProperty("STRIDE_VALUES").split(","));
	}
	public List<String> getPaddingValues() {
		return Arrays.asList(prop.getProperty("PADDING_VALUES").split(","));
	}
	public List<String> getFonctionActivation() {
		return Arrays.asList(prop.getProperty("FONCTION_ACTIVATION").split(","));
	}
	public List<Double> getDropoutRate() {
		return arrayStringToArrayDouble(prop.getProperty("DROPOUT_RATE").split(","));
	}	
	public List<Double> getEpsilon() {
		return arrayStringToArrayDouble(prop.getProperty("EPSILON").split(","));
	}
	public List<String> getAddOrConcat() {
		return Arrays.asList(prop.getProperty("ADD_CONCAT").split(","));
	}
	public List<Double> getCompressFactor() {
		return arrayStringToArrayDouble(prop.getProperty("COMPRESS_FACTOR").split(","));
	}
}
