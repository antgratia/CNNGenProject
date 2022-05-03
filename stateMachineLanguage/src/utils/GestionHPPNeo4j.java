package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domain.*;
import lombok.Data;
import models.ArchitectureGraph;
import views.ArchitectureGraphView;
import xtext.sML.ConvDrop;
import xtext.sML.Merge;
import xtext.sML.MergeBody;
import xtext.sML.MergeConv;

@Data
@SuppressWarnings("unused")
public class GestionHPPNeo4j {
	
	// info img
	private final static int MAX_SIZE_IMG = 32;
	private static int currentSizeImg;
	
	private final static int NB_CLASS = 10;
	private final static String FCT_ACTIVATION = "softmax";
	
	
	// hpp Convolution + Pooling
	private static List<Integer> kernel = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<Integer> stride = new ArrayList<Integer>(List.of(1,2,3,4));
	private static List<String> padding = new ArrayList<String>(List.of("same","valid"));
		// hpp Convolution supp
	private List<String> fctActivation = new ArrayList<String>(List.of("relu","selu","tanh"));
	//private int currentNBFilters;
	private static final int INPUT_FILTER= 3;
	private static final int INIT_NB_FILTER = 16;
	
	// hpp dropout
	private static List<Double> dropoutRate = new ArrayList<Double>(List.of(.1, .2, .4, .5, .8, .01));
	
	//hpp batch Normalisation
	private static List<Double> epsilon = new ArrayList<Double>(List.of(1.1e-10, 1.001e-5, 0.001, 1.1e-5, 1.1e-7));;
	
	// hpp Dense
	private int entryParams;
	
	// hpp
	private static List<String> add_or_concat = new ArrayList<String>(List.of("add", "concat"));
	private static List<Double> compressFactor = new ArrayList<Double>(List.of(0.5,0.6,0.7,0.8,0.9));
	
	private static String str_add_or_concat = "";
	
	private static Random rand = new Random();
	
	public GestionHPPNeo4j() {
		//currentNBFilters = INPUT_FILTER;
		entryParams = 0;
		currentSizeImg = MAX_SIZE_IMG;
	}
	
	public int getCurrentSizeImg(){
		return currentSizeImg;
	}
	
	public int getInputFilter() {
		return INPUT_FILTER;
	}
	
	public void gestionConvolution(Convolution conv, boolean reduction){
		
		if(reduction) {
			optimisationKernelPaddingStride(conv);
			conv.setNbFilter(addInitToFilter(conv.getPrevLayer().get(0).getNbFilter()));
		}else {
			// kernel <= input size
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= currentSizeImg).collect(Collectors.toList());
			int knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			
			conv.setKernel(knl);
			conv.setStride(1);
			conv.setPadding("same");
			conv.setNbFilter(conv.getPrevLayer().get(0).getNbFilter());
		}
		
		
		
		conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
			
	}
	
	public void gestionPooling(Pooling pool, boolean reduction) {
		if(reduction) {
			optimisationKernelPaddingStride(pool);
		}else {
			// kernel <= input size
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= currentSizeImg).collect(Collectors.toList());
			int knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
						
			pool.setKernel(knl);
			pool.setStride(1);
			pool.setPadding("same");
		}
	}

	public void gestionDropout(Dropout drop) {
		drop.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
	}

	public void gestionBN(BatchNormalisation bn) {
		bn.setEpsilon(epsilon.get(rand.nextInt(epsilon.size())));
	}

	public void gestionDense(Dense dense, boolean isLast) {
		if(entryParams == 0) entryParams = currentSizeImg*currentSizeImg*dense.getPrevLayer().get(0).getNbFilter();
		
		if(isLast) {
			dense.setUnits(NB_CLASS);
			dense.setFctActivation(FCT_ACTIVATION);
		}else {
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
	
	
	private static void optimisationKernelPaddingStride(Layer layer) {
		
		String pad = padding.get(rand.nextInt(padding.size()));
		int knl = 1; // kernel
		int strd = 1; // stride
		
		List<Integer> kernel_value_filtered = null;
		List<Integer> stride_value_filtered;
		
		if(currentSizeImg == 2) {
			kernel_value_filtered = new ArrayList<Integer>(List.of(1,2));
		}else if(currentSizeImg == 1) {
			kernel_value_filtered = new ArrayList<Integer>(List.of(1));
		}
		
		
		if(pad == "valid") {
			  // kernel <= input size
			 if(kernel_value_filtered == null) {
				 kernel_value_filtered = kernel.stream().filter(k -> k <= currentSizeImg).collect(Collectors.toList());
				 knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			 }    
			  //stride <= kernel
			  strd = (int)currentSizeImg/knl;
			  if(strd > knl) {
				  int k = knl;
				  stride_value_filtered = stride.stream().filter(s -> s <= k).collect(Collectors.toList());
				  strd = stride_value_filtered.get(rand.nextInt(stride_value_filtered.size()));
			  }
				            
		}else {
			 // Kernel <= output/2
			 if(kernel_value_filtered == null) {
				 kernel_value_filtered = kernel.stream().filter(k -> k <= Math.ceil(currentSizeImg/2)).collect(Collectors.toList());
			 }
				            
			 knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			 
			 // kernel >= stride
			 int k = knl;
			 stride_value_filtered = stride.stream().filter(s -> s <= k).collect(Collectors.toList());
			 strd = stride_value_filtered.get(rand.nextInt(stride_value_filtered.size()));
				    
		}
		
		
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
		
		currentSizeImg = calculCurrentSize(pad, knl, strd, currentSizeImg);
			 
	}
	
	public int compressedFilter(int nbFilter) {
		int nbFilterCompress = (int) (nbFilter*(compressFactor.get(rand.nextInt(compressFactor.size()))));
		
		if(nbFilterCompress%2 != 0) {
			nbFilterCompress+=1;
		}
		return nbFilterCompress;
	}
	
	private int multFilterby2(int currentNBFilters) {
		if(currentNBFilters == INPUT_FILTER) currentNBFilters = 8;
		currentNBFilters *= 2;
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
		return currentNBFilters;
	}
	
	private int addInitToFilter(int currentNBFilters) {
		
		if(currentNBFilters == INPUT_FILTER) currentNBFilters = 0;
		
		currentNBFilters += INIT_NB_FILTER;
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
		return currentNBFilters;
	}
	
	public static int calculCurrentSize(String pad, int knl, int strd, int imgSize) {
		int newCurrentSizeImg = 0;
		if(pad == "valid") {
			
			newCurrentSizeImg = ((imgSize-knl)/strd)+1;
			
		}else {
	        if(imgSize%strd == 0)
	        	newCurrentSizeImg = (int)(imgSize/strd);
	        else
	        	newCurrentSizeImg = (int)(imgSize/strd)+1;
		}
		return newCurrentSizeImg;
	}
	
	
	private static class Randomizer {
	    public static int generate(int min,int max) {
	        return min + (int)(Math.random() * ((max - min) + 1));
	    }
	}

	
	public void recomputeSize(Layer layer, int startImgSize, int objectifImgSize) throws Exception {
		
		int newKnl = 0;
		String newPad = "";
		
		if(startImgSize<objectifImgSize) {
			throw new Exception("GestionHPP recomputeSize: startimg < ObjectifImg");
		}
		
		int newStride = Math.round(startImgSize/objectifImgSize);
		
		if( calculCurrentSize("same", newStride, newStride,  startImgSize) == objectifImgSize) {
			newPad="same";
			int s = newStride;
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= startImgSize && k>=s).collect(Collectors.toList());
			
			if(kernel_value_filtered.size() <1) {
				newKnl = newStride;
			}else {
				newKnl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			}
			
			
		}else {
			newPad = "valid";
			newKnl = startImgSize -((objectifImgSize-1)*newStride);
			boolean knlReduction = false; 
			while(calculCurrentSize("valid", newKnl, newStride, startImgSize) != objectifImgSize){
				if(newKnl-1> 0 && !knlReduction) {
					newKnl -=1;
					knlReduction = true;
				}else if(newStride-1 > 0 && knlReduction) {
					newStride-=1;
					knlReduction = false;
				}else {
					throw new Exception("HPP invalide");
				}
			}
		}
		
		
		
		if(layer instanceof Convolution) {
			((Convolution) layer).setKernel(newKnl);
			((Convolution) layer).setPadding(newPad);
			((Convolution) layer).setStride(newStride);
			
		}else if(layer instanceof Pooling) {
			((Pooling) layer).setKernel(newKnl);
			((Pooling) layer).setPadding(newPad);
			((Pooling) layer).setStride(newStride);
		}else {
			throw new Exception("GestionHPP recomputeSize : layerinterface Wrong type");
		}
		
		layer.setImgSize(calculCurrentSize(newPad, newKnl, newStride,  startImgSize));
		
	}


	
}
