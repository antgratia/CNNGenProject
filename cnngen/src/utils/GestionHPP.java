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
import views.ArchitectureGraphView;
import xtext.cNNDescLang.ConvDrop;
import xtext.cNNDescLang.Merge;
import xtext.cNNDescLang.MergeBody;
import xtext.cNNDescLang.MergeConv;

/*
 * 
 * Class handle all the value of HPP
 * 
 */

@Data
@SuppressWarnings("unused")
public class GestionHPP {
	
	// Input Layer info img / filter
	private static int inputLayerInputImgSize;
	private static int inputLayerinputFilter; 
	
	private int nb_class;
	private String fctClassification;
	
	
	// hpp Convolution + Pooling
	private static List<Integer> kernel;
	private static List<Integer> stride;
	private static List<String> padding;
		// hpp Convolution supp
	private List<String> fctActivation;
	//private int currentNBFilters;
	
	private static int initFilter;
	
	// hpp dropout
	private static List<Double> dropoutRate;
	
	//hpp batch Normalisation
	private static List<Double> epsilon;
	
	// hpp Dense
	private int entryParams;
	
	// hpp
	private static List<String> add_or_concat;
	private static List<Double> compressFactor;
	
	private static String str_add_or_concat = "";
	private String str_fctActivation = "";
	
	private static Random rand = new Random();
	
	public GestionHPP(ProgramConfig programConfig) {
		//currentNBFilters = INPUT_FILTER;
		entryParams = 0;
		inputLayerInputImgSize = programConfig.getMaxSizeImg();
		nb_class = programConfig.getNbClass();
		fctClassification = programConfig.getFonctionClassification();
		kernel = programConfig.getKernelValues();
		stride = programConfig.getStringValues();
		padding = programConfig.getPaddingValues();
		fctActivation = programConfig.getFonctionActivation();
		inputLayerinputFilter = programConfig.getInputFilter();
		initFilter = programConfig.getInitNbFilter();
		dropoutRate = programConfig.getDropoutRate();
		epsilon = programConfig.getEpsilon();
		add_or_concat = programConfig.getAddOrConcat();
		compressFactor = programConfig.getCompressFactor();
	}

	
	// set HPP for convolution layer
	public void gestionConvolution(Convolution conv, boolean reduction) throws Exception{
		
		if(reduction) {
			optimisationKernelPaddingStride(conv);
			conv.setOutputFilter(addInitToFilter(conv.getInputFilter()));
		}else {
			// kernel <= input size
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= conv.getInputImgSize()).collect(Collectors.toList());
			if(kernel_value_filtered.size() == 0)
				kernel_value_filtered.add(1);

			int knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			
			conv.setKernel(knl);
			conv.setStride(1);
			conv.setPadding("same");
			conv.setOutputFilter(conv.getInputFilter());
			conv.setOutputImgSize(calculCurrentSize(conv.getPadding(), conv.getKernel(), conv.getStride(), conv.getInputImgSize()));
			
		}
		
		conv.setFctActivation(str_fctActivation);
			
	}
	
	// set HPP for convolution layer
	public void gestionConvolution(Convolution conv, boolean reduction,  int objectiveImgSize, int objectiveFilter) throws Exception{
		
		optiHPPfromImgObjective(conv, objectiveImgSize);
		conv.setOutputFilter(objectiveFilter);
		conv.setFctActivation(str_fctActivation);
			
	}
	
	
	// set HPP for pooling layer
	public void gestionPooling(Pooling pool, boolean reduction) throws Exception {
	
		if(reduction) {
			optimisationKernelPaddingStride(pool);
		}else {
			// kernel <= input size
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= pool.getInputImgSize()).collect(Collectors.toList());
			if(kernel_value_filtered.size() == 0)
				kernel_value_filtered.add(1);
			int knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
						
			pool.setKernel(knl);
			pool.setStride(1);
			pool.setPadding("same");
			
			pool.setOutputImgSize(calculCurrentSize(pool.getPadding(), pool.getKernel(), pool.getStride(), pool.getInputImgSize()));
		}
	}
	
	// set HPP for pooling layer
	public void gestionPooling(Pooling pool, boolean reduction, int objectiveImgSize) throws Exception {
		optiHPPfromImgObjective(pool, objectiveImgSize);
	}

	// set HPP for Dropout layer
	public void gestionDropout(Dropout drop) {
		drop.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
	}

	// set Hpp for Batch Normalization Layer
	public void gestionBN(BatchNormalisation bn) {
		bn.setEpsilon( epsilon.get(rand.nextInt(epsilon.size())));
	}

	// set HPP for Dense layer
	public void gestionDense(Dense dense, boolean isLast) {		
		if(entryParams == 0) entryParams = dense.getInputImgSize()*dense.getInputImgSize()*dense.getInputFilter();
		
		if(isLast) {
			dense.setUnits(this.nb_class);
			dense.setFctActivation(this.fctClassification);
		}else {
			int units;
			if (entryParams >=1000) {
				units = (int)(entryParams*20/100);
			}else {
				units = (int)(entryParams*Randomizer.generate(10, 80)/100);
			}
			
			if(units %2 != 0 ) units++;
			
			// memory prevention
			if(units >= 1000) units = 256;
			
			dense.setUnits(units);
			dense.setFctActivation(str_fctActivation);
			entryParams = units;
		}

	}
	
	// handle HPP kernel Padding Stride
	private static void optimisationKernelPaddingStride(Layer layer) throws Exception {
		
		String pad = padding.get(rand.nextInt(padding.size()));
		int knl = 1; // kernel
		int strd = 1; // stride
		
		List<Integer> kernel_value_filtered = null;
		List<Integer> stride_value_filtered;
		
		if(layer.getInputImgSize() == 2) {
			kernel_value_filtered = new ArrayList<Integer>(List.of(1,2));
		}else if(layer.getInputImgSize() == 1) {
			kernel_value_filtered = new ArrayList<Integer>(List.of(1));
		}
		
		if(pad.equals("valid")) {
			  // kernel <= input size
			 if(kernel_value_filtered == null) {
				 kernel_value_filtered = kernel.stream().filter(k -> k <= layer.getInputImgSize()).collect(Collectors.toList());
				 knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			 }    
			  //stride <= kernel
			  strd = (int)layer.getInputImgSize()/knl;
			  if(strd > knl) {
				  int k = knl;
				  stride_value_filtered = stride.stream().filter(s -> s <= k).collect(Collectors.toList());
				  strd = stride_value_filtered.get(rand.nextInt(stride_value_filtered.size()));
			  }
				            
		}else if (pad.equals("same")){
			 // Kernel <= output/2
			 if(kernel_value_filtered == null) {
				 kernel_value_filtered = kernel.stream().filter(k -> k <= Math.ceil(layer.getInputImgSize()/2)).collect(Collectors.toList());
			 }
				            
			 knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			 
			 // kernel >= stride
			 int k = knl;
			 stride_value_filtered = stride.stream().filter(s -> s <= k).collect(Collectors.toList());
			 strd = stride_value_filtered.get(rand.nextInt(stride_value_filtered.size()));
				    
		}else throw new Exception("GestionHPP optimisationKernelPaddingStride : not possible");
		
		
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
		
		layer.setOutputImgSize(calculCurrentSize(pad, knl, strd, layer.getInputImgSize()));
			 
	}
	
	// reduce HPP filter
	public int compressedFilter(int nbFilter) {
		int nbFilterCompress = (int) (nbFilter*(compressFactor.get(rand.nextInt(compressFactor.size()))));
		
		if(nbFilterCompress%2 != 0) {
			nbFilterCompress+=1;
		}
		
		return nbFilterCompress;
	}
	
	// multiple by 2 the filter
	private int multFilterby2(int currentNBFilters) {
		if(currentNBFilters == inputLayerinputFilter) currentNBFilters = 8;
		currentNBFilters *= 2;
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
		return currentNBFilters;
	}
	
	// add fixe number to filter
	private int addInitToFilter(int currentNBFilters) {
		
		if(currentNBFilters == inputLayerinputFilter) currentNBFilters = 0;
		
		currentNBFilters += initFilter;
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
		return currentNBFilters;
	}
	
	// compute the current img size
	public static int calculCurrentSize(String pad, int knl, int strd, int imgSize) {
		int newCurrentSizeImg = 0;
		if(pad.equals("valid")) {
			newCurrentSizeImg = (int) ((imgSize-knl)/strd)+1;
			
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

	
	public void optiHPPfromImgObjective(Layer layer, int objectiveImgSize) throws Exception {
		int knl = 0;
		String pad = "";
		
		if(layer.getInputImgSize()<objectiveImgSize) 
			throw new Exception("GestionHPP optiHPPfromImgObjective: startimg < ObjectifImg");
		
		int stride = Math.round(layer.getInputImgSize()/objectiveImgSize);
		
		if( calculCurrentSize(pad, stride, stride, layer.getInputImgSize()) == objectiveImgSize) {
			pad="same";
			int s = stride;
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= layer.getInputImgSize() && k>=s).collect(Collectors.toList());
			
			if(kernel_value_filtered.size() <1) {
				knl = stride;
			}else {
				knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			}
			
			
		}else {
			pad = "valid";
			knl = layer.getInputImgSize() -((objectiveImgSize-1)*stride);
			boolean knlReduction = false; 
			while(calculCurrentSize("valid", knl, stride, layer.getInputImgSize()) != objectiveImgSize){
				if(knl-1> 0 && !knlReduction) {
					knl -=1;
					knlReduction = true;
				}else if(stride-1 > 0 && knlReduction) {
					stride-=1;
					knlReduction = false;
				}else {
					throw new Exception("HPP invalide");
				}
			}
		}
		
		if(layer instanceof Convolution) {
			((Convolution) layer).setKernel(knl);
			((Convolution) layer).setPadding(pad);
			((Convolution) layer).setStride(stride);
			
		}else if(layer instanceof Pooling) {
			((Pooling) layer).setKernel(knl);
			((Pooling) layer).setPadding(pad);
			((Pooling) layer).setStride(stride);
		}else {
			throw new Exception("GestionHPP recomputeSize : layerinterface Wrong type");
		}
		
		layer.setOutputImgSize(calculCurrentSize(pad, knl, stride,  layer.getInputImgSize()));
		
		
	}
	
	// compute the img size
	public void recomputeSize(Layer layer, int startImgSize, int objectifImgSize) throws Exception {
		
		int newKnl = 0;
		String newPad = "";
		
		if(startImgSize<objectifImgSize) {
			throw new Exception("GestionHPP recomputeSize: startimg < ObjectifImg");
		}
		
		int newStride = Math.round(startImgSize/objectifImgSize);
		
		if( calculCurrentSize("same", newKnl, newStride,  startImgSize) == objectifImgSize) {
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
		
		layer.setOutputImgSize(calculCurrentSize(newPad, newKnl, newStride,  startImgSize));
		
	}

	public void initInputLayer(Input input) {
		input.setInputFilter(inputLayerinputFilter);
		input.setOutputFilter(inputLayerinputFilter);
		
		input.setInputImgSize(inputLayerInputImgSize);
		input.setOutputImgSize(inputLayerInputImgSize);
		
	}


	
}
