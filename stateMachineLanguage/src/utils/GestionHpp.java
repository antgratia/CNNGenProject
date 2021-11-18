package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import models.BatchNormalisation;
import models.Convolution;
import models.Dense;
import models.Dropout;
import models.LayerInterface;
import models.MergeSimple;
import models.Pooling;
import xtext.sML.ConvDrop;
import xtext.sML.MergeNonRecu;
import xtext.sML.MergeRecu;

@SuppressWarnings("unused")
public class GestionHpp {
	
	private static GestionHpp gestionHpp;
	
	// info img
	private final static int MAX_SIZE_IMG = 28;
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
	private static List<Double> compressFactor = new ArrayList<Double>(List.of(0.2,0.3,0.5,0.65,0.8));
	
	private static String str_add_or_concat = "";
	
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
		Random rand = new Random();
		conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
			
	}
	
	public void gestionConvolutionMerge(int s, String pad, Convolution conv){
		Random rand = new Random();
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
		Random rand = new Random();
		dropout.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
		
	}

	public void gestionBN(BatchNormalisation bn) {
		Random rand = new Random();
		bn.setEpsilon(epsilon.get(rand.nextInt(epsilon.size())));
	}

	public void gestionDense(Dense dense) {
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
	
	
	private static void optimisationKernelPaddingStride(LayerInterface layer) {
		Random rand = new Random();
		
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
		Random rand = new Random();
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
	
	public void gestionMergeNonRecu(MergeNonRecu mnr, MergeSimple ms, boolean mergeRecu, String str_add_or_concat){
		
		Random rand = new Random();
		if (str_add_or_concat == "")
			str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		
		ms.setAdd_or_concat(str_add_or_concat);
		
		if(mergeRecu == true) {
			if(mnr.getLeftNonRec().getP() != null) {
				Pooling p = new Pooling();
				p.setPadding("same");
				p.setStride(1);
				p.setKernel(kernel.get(rand.nextInt(kernel.size())));
				
				ms.addLeft(p);
			}
			
			// convdrop
			for (ConvDrop conv: mnr.getLeftNonRec().getConvdrop()) {
				Convolution c = new Convolution();
				gestionConvolutionMerge(1, "same", c);
				ms.addLeft(c);
			}
			
			if(mnr.getRight().getConv() != null) {
				for (xtext.sML.Convolution conv: mnr.getRight().getConv()) {
					Convolution c = new Convolution();
					gestionConvolutionMerge(1, "same", c);
					ms.addRight(c);
				}
			}
			
			// pooling
			if(mnr.getLeftNonRec().getPool() != null) {
				Pooling p = new Pooling();
				p.setPadding("same");
				p.setStride(1);
				p.setKernel(kernel.get(rand.nextInt(kernel.size())));
				
				ms.addLeft(p);
			}
			
		}else {
			if (mnr.getRight().getEmpty() != null) {
				// one way is empty
				if(mnr.getLeftNonRec().getP() != null) {
					Pooling p = new Pooling();
					p.setPadding("same");
					p.setStride(1);
					p.setKernel(kernel.get(rand.nextInt(kernel.size())));
					
					ms.addLeft(p);
				}
				
				// convdrop
				if(ms.getAdd_or_concat() == "add") {
					for (ConvDrop conv: mnr.getLeftNonRec().getConvdrop()) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
					
					
				}else {
					int i = 0;
					while (i < mnr.getLeftNonRec().getConvdrop().size()-1) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
						i++;
					}
					
					Convolution c = new Convolution();
					addInitToFilter();
					gestionConvolutionMerge(1, "same", c);
					ms.addLeft(c);
				}
				// pooling
				if(mnr.getLeftNonRec().getPool() != null) {
					Pooling p = new Pooling();
					p.setPadding("same");
					p.setStride(1);
					p.setKernel(kernel.get(rand.nextInt(kernel.size())));
					
					ms.addLeft(p);
				}
			}else {
				int initImgSize = currentSizeImg;
				
				if(mnr.getLeftNonRec().getP() != null) {
					Pooling p = new Pooling();
					p.setPadding("same");
					p.setStride(1);
					p.setKernel(kernel.get(rand.nextInt(kernel.size())));
					
					ms.addLeft(p);
				}
				
				// management conv for reduction img
				int countConvLeft = mnr.getLeftNonRec().getConvdrop().size();
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
				int countConvRight = mnr.getRight().getConv().size();
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
				
				// pooling
				if(mnr.getLeftNonRec().getPool() != null) {
					Pooling p = new Pooling();
					p.setPadding("same");
					p.setStride(1);
					p.setKernel(kernel.get(rand.nextInt(kernel.size())));
					
					ms.addLeft(p);
				}
				
			}
		}
	}
	
	public void gestionMergeRecu(MergeRecu mr, MergeSimple ms) {
		Random rand = new Random();
		if(str_add_or_concat == "") {
			str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		}
		
		
			ms.setAdd_or_concat(str_add_or_concat);
			
				// pooling -> conv -> merge -> conv -> pooling
				
				// one way is empty
				if(mr.getLeft().getP() != null) {
					Pooling p = new Pooling();
					p.setPadding("same");
					p.setStride(1);
					p.setKernel(kernel.get(rand.nextInt(kernel.size())));
					
					ms.addLeft(p);
				}
				
				// convdrop
				if (mr.getLeft().getConvdropbegin() != null){
					for (ConvDrop conv: mr.getLeft().getConvdropbegin()) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
				}
			
			if (str_add_or_concat == "concat") {
				addInitToFilter();
			}
			if (mr.getLeft().getMerge().getMr() != null) { // managmenet merge recusive	
				gestionMergeRecu(mr.getLeft().getMerge().getMr(), ms);
			}else { // end of merge recusive
				gestionMergeNonRecu(mr.getLeft().getMerge().getMnr(), ms, true, str_add_or_concat);
				str_add_or_concat = "";
			}
				
				// convdrop
				if (mr.getLeft().getConvdropend() != null){					
					for (ConvDrop conv: mr.getLeft().getConvdropend()) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
				}
				
				if (mr.getRight().getEmpty() == null) {
					// add to right
					for (xtext.sML.Convolution conv: mr.getRight().getConv()) {
						Convolution c = new Convolution();
						gestionConvolutionMerge(1, "same", c);
						ms.addRight(c);
					}
				}
				
				// pooling
				if(mr.getLeft().getPool() != null) {
					Pooling p = new Pooling();
					p.setPadding("same");
					p.setStride(1);
					p.setKernel(kernel.get(rand.nextInt(kernel.size())));
					
					ms.addLeft(p);
					
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
