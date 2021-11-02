package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.BatchNormalisation;
import models.Convolution;
import models.Dense;
import models.Dropout;
import models.LayerInterface;
import models.Pooling;

public class GestionHpp {
	
	// info img
	private final static int MAX_SIZE_IMG = 28;
	private static int currentSizeImg = 28;
	
	private final static int NB_CLASS = 10;
	private final static String FCT_ACTIVATION = "softmax";
	
	
	// hpp Convolution + Pooling
	private static List<Integer> kernel = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<Integer> stride = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<String> padding = new ArrayList<String>(List.of("same","valid"));
		// hpp Convolution supp
	private static List<String> fctActivation = new ArrayList<String>(List.of("relu","selu","tanh"));
	private static int currentNBFilters;
	private static final int INIT_NB_FILTER = 16;
	
	// hpp dropout
	private static List<Double> dropoutRate = new ArrayList<Double>(List.of(.1, .2, .4, .5, .8, .01));
	
	//hpp batch Normalisation
	private static List<Double> epsilon = new ArrayList<Double>(List.of(1.1e-10, 1.001e-5, 0.001, 1.1e-5, 1.1e-7));;
	
	// hpp Dense
	private static int entryParams = 0;
	
	
	// entry point
	public static void gestionConvolution(Convolution conv){
		optimisationKernelPaddingStride(conv);
			
		conv.setNbFilter(multFilterby2());
		Random rand = new Random();
		conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
			
	}
	
	public static void gestionPooling(Pooling pool) {
		optimisationKernelPaddingStride(pool);

	}

	public static void gestionDropout(Dropout dropout) {
		Random rand = new Random();
		dropout.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
		
	}

	public static void gestionBN(BatchNormalisation bn) {
		Random rand = new Random();
		bn.setEpsilon(epsilon.get(rand.nextInt(epsilon.size())));
	}

	public static void gestionDense(Dense dense) {
		if(entryParams == 0) entryParams = currentSizeImg*currentSizeImg*currentNBFilters;
		
		if(dense.isLast()) {
			dense.setUnits(NB_CLASS);
			dense.setFctActivation(FCT_ACTIVATION);
		}else {
			Random rand = new Random();
			int units;
			if (entryParams >=1000) {
				units = (int)(entryParams*20/100);
			}else {
				units = (int)(entryParams*Randomizer.generate(10, 80)/100);
			}
			dense.setUnits(units);
			dense.setFctActivation(fctActivation.get(rand.nextInt(fctActivation.size())));
			entryParams = units;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void optimisationKernelPaddingStride(LayerInterface layer) {
		Random rand = new Random();
		
		String pad = padding.get(rand.nextInt(padding.size()));
		int knl = 1; // kernel
		int strd = 1; // stride
		
		List<Integer> kernel_value_filtered;
		List<Integer> stride_value_filtered;
		
		if(pad == "valid") {
			  // kernel <= input size
			  kernel_value_filtered = (List<Integer>) kernel.stream().filter(k -> k <= currentSizeImg);
			  knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
				        
			  //stride <= kernel
			  strd = (int)currentSizeImg/knl;
			  if(strd > knl) {
				  int k = knl;
				  stride_value_filtered = (List<Integer>) stride.stream().filter(s -> s <= k);
				  strd = stride_value_filtered.get(rand.nextInt(stride.size()));
			  }
				            
		}else {
			 // Kernel <= output/2
			 if(currentSizeImg == 2) {
				kernel_value_filtered = new ArrayList<Integer>(List.of(1,2));
			 }else if(currentSizeImg == 1){
				 kernel_value_filtered = new ArrayList<Integer>(List.of(1));
			 }else {
				kernel_value_filtered = (List<Integer>) kernel.stream().filter(k -> k <= Math.ceil(currentSizeImg/2));
			 }
				            
			 knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			 
			 // kernel >= stride
			 int k = knl;
			 stride_value_filtered = (List<Integer>) stride.stream().filter(s -> s <= k);
			 strd = stride_value_filtered.get(rand.nextInt(stride_value_filtered.size()));
				    
		}
		
		calculCurrentSize(pad, knl, strd);
		
		
		if (layer instanceof Convolution) {
			Convolution conv;
			conv = (Convolution) layer;
			conv.setPadding(pad);
			conv.setKernel(knl);
			conv.setStride(strd);
		}else {
			Pooling pool;
			pool = (Pooling) layer;
			pool.setPadding(pad);
			pool.setKernel(knl);
			pool.setStride(strd);
		}
			 
	}
	
	public static int multFilterby2() {
		return currentNBFilters*2;
	}
	
	public static int addInitToFilter() {
		return currentNBFilters + INIT_NB_FILTER;
	}
	
	public static void calculCurrentSize(String pad, int knl, int strd) {
		
	}
	
	
	public static class Randomizer {
	    public static int generate(int min,int max) {
	        return min + (int)(Math.random() * ((max - min) + 1));
	    }
	}
	
	
	
}
