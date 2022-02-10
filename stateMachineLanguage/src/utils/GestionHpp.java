package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import models.BatchNormalisation;
import models.Convolution;
import models.Dense;
import models.Dropout;
import models.LayerInterface;
import models.MergeSimple;
import models.Pooling;
import xtext.sML.ConvDrop;
import xtext.sML.Merge;
import xtext.sML.MergeBody;
import xtext.sML.MergeConv;


@SuppressWarnings("unused")
public class GestionHpp {
	
	private static GestionHpp gestionHpp;
	
	// info img
	private final static int MAX_SIZE_IMG = 32;
	private static int currentSizeImg;
	
	private final static int NB_CLASS = 10;
	private final static String FCT_ACTIVATION = "softmax";
	
	
	// hpp Convolution + Pooling
	private static List<Integer> kernel = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<Integer> stride = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<String> padding = new ArrayList<String>(List.of("same","valid"));
		// hpp Convolution supp
	private static List<String> fctActivation = new ArrayList<String>(List.of("relu","selu","tanh"));
	private int currentNBFilters;
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
	
	private GestionHpp() {
		currentNBFilters = INIT_NB_FILTER;
		entryParams = 0;
		currentSizeImg = MAX_SIZE_IMG;
	}
	
	// design singleton
	public static GestionHpp getGestionHpp() {
		if (gestionHpp == null) {
			gestionHpp = new GestionHpp();
		}
		return gestionHpp;
	}
	
	// entry point
	public void gestionConvolution(Convolution conv){
		optimisationKernelPaddingStride(conv);
		
		multFilterby2();
		conv.setNbFilter(currentNBFilters);
		conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
			
	}
	
	public void gestionConvolutionMerge(int s, String pad, Convolution conv){
		// kernel <= input size
		List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= currentSizeImg).collect(Collectors.toList());
		int knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
		
		conv.setKernel(knl);
		conv.setStride(s);
		conv.setPadding(pad);
		
		conv.setNbFilter(currentNBFilters);
		
		conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
			
	}
	
	public void gestionPooling(Pooling pool) {
		optimisationKernelPaddingStride(pool);

	}

	public void gestionDropout(Dropout dropout) {
		dropout.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
		
	}

	public void gestionBN(BatchNormalisation bn) {
		bn.setEpsilon(epsilon.get(rand.nextInt(epsilon.size())));
	}

	public void gestionDense(Dense dense) {
		if(entryParams == 0) entryParams = currentSizeImg*currentSizeImg*currentNBFilters;
		
		if(dense.isLast()) {
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
	
	
	private static void optimisationKernelPaddingStride(LayerInterface layer) {
		
		String pad = padding.get(rand.nextInt(padding.size()));
		int knl = 1; // kernel
		int strd = 1; // stride
		
		List<Integer> kernel_value_filtered;
		List<Integer> stride_value_filtered;
		
		if(pad == "valid") {
			  // kernel <= input size
			  kernel_value_filtered = kernel.stream().filter(k -> k <= currentSizeImg).collect(Collectors.toList());
			  knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
				        
			  //stride <= kernel
			  strd = (int)currentSizeImg/knl;
			  if(strd > knl) {
				  int k = knl;
				  stride_value_filtered = stride.stream().filter(s -> s <= k).collect(Collectors.toList());
				  strd = stride_value_filtered.get(rand.nextInt(stride_value_filtered.size()));
			  }
				            
		}else {
			 // Kernel <= output/2
			 if(currentSizeImg == 2) {
				kernel_value_filtered = new ArrayList<Integer>(List.of(1,2));
			 }else if(currentSizeImg == 1){
				 kernel_value_filtered = new ArrayList<Integer>(List.of(1));
			 }else {
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
		
		calculCurrentSize(pad, knl, strd);
			 
	}
	

	public int compressedFilter() {
		multFilterby2();
		currentNBFilters = (int) (currentNBFilters*(compressFactor.get(rand.nextInt(compressFactor.size()))));
		return currentNBFilters;
	}
	
	private int multFilterby2() {
		currentNBFilters = currentNBFilters*2;
		return currentNBFilters;
	}
	
	private int addInitToFilter() {
		currentNBFilters = currentNBFilters + INIT_NB_FILTER;
		return currentNBFilters;
	}
	
	private static void calculCurrentSize(String pad, int knl, int strd) {
		int newCurrentSizeImg = 0;
		if(pad == "valid") {
			
	        while(currentSizeImg>=knl) {
	        	currentSizeImg -= strd;
	        	newCurrentSizeImg += 1;
	        }
		}else {
	        if(currentSizeImg%strd == 0)
	        	newCurrentSizeImg = (int)(currentSizeImg/strd);
	        else
	        	newCurrentSizeImg = (int)(currentSizeImg/strd)+1;
		}
		currentSizeImg = newCurrentSizeImg;
	}
	
	public void gestionMerge(Merge merge, MergeSimple ms) {
		if(merge.getMergeBody() != null) {
			for(MergeBody mb: merge.getMergeBody()) {
				gestionMergeBody(mb, ms, str_add_or_concat);
			}
		}
	}
	
	public void gestionMergeBody(MergeBody mb, MergeSimple ms, String str_add_or_concat){

		if (str_add_or_concat == "")
			str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		
		ms.setAdd_or_concat(str_add_or_concat);
		
		if(mb.getLeft().getP() != null) {
			ms.addLeft(new Pooling(kernel.get(rand.nextInt(kernel.size())),1,"same"));
		}
		

		
		if(mb.getLeft().getCom().getMergeConv() != null) {
			// Merge recursive
			
			// convdrop
			if(mb.getLeft().getCom().getConvdrop() != null) {
				for (ConvDrop conv: mb.getLeft().getCom().getConvdrop()) {
					Convolution c = new Convolution();
					gestionConvolutionMerge(1, "same", c);
					ms.addLeft(c);
				}
			}
			
			for(MergeConv mc :mb.getLeft().getCom().getMergeConv()) {
				gestionMerge(mc.getMerge(), ms);
				
				if(mc.getConvdrop() != null) {
					for (ConvDrop conv: mc.getConvdrop()) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
				}
			}
			
			if(mb.getRight().getConv() != null) {
				for (xtext.sML.Convolution conv: mb.getRight().getConv()) {
					Convolution c = new Convolution();
					gestionConvolutionMerge(1, "same", c);
					ms.addRight(c);
				}
			}
			
		}else {
			// Merge Non Recusive
			if(mb.getRight().getEmpty() != null) {
				
				int initImgSize = currentSizeImg;
				
				// management conv for reduction img
				int countConvLeft = mb.getLeft().getCom().getConvdrop().size();
				int numLeft = rand.nextInt(countConvLeft);
				Convolution reduceConv = new Convolution();
				int i = 0;
				while(i < countConvLeft) {
					if(numLeft == i) {
						gestionConvolution(reduceConv);
						ms.addLeft(reduceConv);
					}else {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
					i++;
				}
				
				// add reduction on left to right
				int countConvRight = mb.getRight().getConv().size();
				int numRight = rand.nextInt(countConvRight);
				
				i = 0;
				while(i < countConvRight) {
					if(numRight == i) {
						ms.addRight(reduceConv);
					}else {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addRight(c);
					}
					i++;
				}
				
			}else {
				// convdrop
				if(mb.getLeft().getCom().getConvdrop() != null) {
					for (ConvDrop conv: mb.getLeft().getCom().getConvdrop()) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
				}
			}
		}
		
		// pooling
		if(mb.getLeft().getPool() != null) {
			ms.addLeft(new Pooling(kernel.get(rand.nextInt(kernel.size())),1,"same"));
		}
		
	}
	
	
	private static class Randomizer {
	    public static int generate(int min,int max) {
	        return min + (int)(Math.random() * ((max - min) + 1));
	    }
	}

	public static void destroy() {
		gestionHpp = null;
	}

	
	
	
}
