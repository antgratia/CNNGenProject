package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;

import lombok.Getter;
import models.ArchitectureGraph;
import models.BatchNormalisation;
import models.Convolution;
import models.Dense;
import models.Dropout;
import models.LayerCell;
import models.LayerInterface;
import models.MergeSimple;
import models.Pooling;
import models.TypeLayerEnum;
import xtext.sML.Architecture;
import xtext.sML.Classification;
import xtext.sML.ConvDrop;
import xtext.sML.FeatureExtraction;
import xtext.sML.Interstice;
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
	private static List<Integer> stride = new ArrayList<Integer>(List.of(1,2,3,4));
	private static List<String> padding = new ArrayList<String>(List.of("same","valid"));
		// hpp Convolution supp
	private static List<String> fctActivation = new ArrayList<String>(List.of("relu","selu","tanh"));
	private int currentNBFilters;
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
	
	public GestionHpp() {
		currentNBFilters = INPUT_FILTER;
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
	public Convolution gestionConvolution(boolean reduction){
		Convolution conv = new Convolution();
		
		if(reduction) {
			optimisationKernelPaddingStride(conv);
			addInitToFilter();
		}else {
			// kernel <= input size
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= currentSizeImg).collect(Collectors.toList());
			int knl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
			
			conv.setKernel(knl);
			conv.setStride(1);
			conv.setPadding("same");
		}
		
		
		conv.setNbFilter(currentNBFilters);
		conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
		
		return conv;
			
	}
	
	public Pooling gestionPooling(boolean reduction) {
		Pooling pool = new Pooling();
		optimisationKernelPaddingStride(pool);
		return pool;
	}

	public Dropout gestionDropout() {
		Dropout drop = new Dropout();
		drop.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
		return drop;
	}

	public BatchNormalisation gestionBN() {
		BatchNormalisation bn = new BatchNormalisation();
		bn.setEpsilon(epsilon.get(rand.nextInt(epsilon.size())));
		return bn;
	}

	public Dense gestionDense(boolean last) {
		Dense dense = new Dense(last);
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
		
		return dense;
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
		currentNBFilters = (int) (currentNBFilters*(compressFactor.get(rand.nextInt(compressFactor.size()))));
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
		return currentNBFilters;
	}
	
	private int multFilterby2() {
		currentNBFilters = currentNBFilters*2;
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
		return currentNBFilters;
	}
	
	private int addInitToFilter() {
		currentNBFilters = currentNBFilters + INIT_NB_FILTER;
		
		if(currentNBFilters%2 != 0) {
			currentNBFilters+=1;
		}
		
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
	
	private static int calculCurrentSize(String pad, int knl, int strd, int imgSize) {
		int newCurrentSizeImg = 0;
		if(pad == "valid") {
			
	        while(imgSize>=knl) {
	        	imgSize -= strd;
	        	newCurrentSizeImg += 1;
	        }
		}else {
	        if(imgSize%strd == 0)
	        	newCurrentSizeImg = (int)(imgSize/strd);
	        else
	        	newCurrentSizeImg = (int)(imgSize/strd)+1;
		}
		return newCurrentSizeImg;
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
					//gestionConvolutionMerge(1, "same", c);
					ms.addLeft(c);
				}
			}
			
			for(MergeConv mc :mb.getLeft().getCom().getMergeConv()) {
				gestionMerge(mc.getMerge(), ms);
				
				if(mc.getConvdrop() != null) {
					for (ConvDrop conv: mc.getConvdrop()) {
						Convolution c = new Convolution();
						//gestionConvolutionMerge(1, "same", c);
						ms.addLeft(c);
					}
				}
			}
			
			if(mb.getRight().getConv() != null) {
				for (xtext.sML.Convolution conv: mb.getRight().getConv()) {
					Convolution c = new Convolution();
					//gestionConvolutionMerge(1, "same", c);
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
						//gestionConvolution(reduceConv);
						ms.addLeft(reduceConv);
					}else {
						Convolution c = new Convolution();
						//gestionConvolutionMerge(1, "same", c);
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
						//gestionConvolutionMerge(1, "same", c);
						ms.addRight(c);
					}
					i++;
				}
				
			}else {
				// convdrop
				if(mb.getLeft().getCom().getConvdrop() != null) {
					for (ConvDrop conv: mb.getLeft().getCom().getConvdrop()) {
						Convolution c = new Convolution();
						//gestionConvolutionMerge(1, "same", c);
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
	
	/** 
	 * 
	 * ===== NEW VERSION USING GRAPH =======
	 * @throws Exception 
	 * 
	 **/
	
	public void setGraphHPP(ArchitectureGraph graph) throws Exception {
		
		replaceTempToAddOrConcat(graph);
		
		/*
		System.out.println("\n\n");
		for(Entry<LayerCell, Set<LayerCell>> i: graph.getGraph().entrySet()){
			
			System.out.println(i);
			System.out.println("\n");
		}*/
		
		// find last layer
		int idLastLayer = graph.getGraph().keySet()
						  .stream()
						  .filter(c -> c.getTypeLayer() == TypeLayerEnum.OUT)
						  .collect(Collectors.toList())
						  .get(0)
						  .getID() - 1;
	    graph.getByID(idLastLayer).setLast(true);
		
	    
		int i = 0;
		LayerCell layer = graph.getByID(i);
		layer.setNbFilter(INPUT_FILTER);
		i++;
		
		while(i<graph.getGraph().size()) {
			layer = graph.getByID(i);
			Set<LayerCell> edges = graph.getEdge(layer);
			if(edges.size()<=1) {
				if(layer.getLayer() == null) {
					layer.setLayer(getHyperParametters(layer.getTypeLayer(), layer.isReduction(), layer.isLast()));
					layer.setImgSize(currentSizeImg);
					layer.setNbFilter(currentNBFilters);
				}
				i++;
			}else if(edges.size()==2) {
			
				layer.setLayer(getHyperParametters(layer.getTypeLayer(), layer.isReduction(), layer.isLast()));
				layer.setImgSize(currentSizeImg);
				layer.setNbFilter(currentNBFilters);
					
				List<LayerCell> listLayerCell = new ArrayList<>(edges)
						.stream()
						.sorted(Comparator.comparingInt(LayerCell::getID)
								.reversed())
						.collect(Collectors.toList());
				LayerCell lastLayer = null;
				for(int j =0; j<listLayerCell.size()-1; j++) {
					lastLayer = listLayerCell.get(j);
					boolean empty = true;
					while(lastLayer.getTypeLayer() != TypeLayerEnum.CONCAT &&
						lastLayer.getTypeLayer() != TypeLayerEnum.ADD ) {
							
						List<LayerCell> edge = new ArrayList<>(graph.getEdge(lastLayer))
								.stream()
								.sorted(Comparator.comparingInt(LayerCell::getID)
										.reversed())
								.collect(Collectors.toList());
						lastLayer = edge.get(0);
						empty = false;
					}
					
					gestionMerge(graph, layer, lastLayer, empty);
				}
				
				i=lastLayer.getID();
				if(lastLayer.getTypeLayer() == TypeLayerEnum.CONCAT) {
					List<LayerCell> reverseEdge = graph.getReverseEdge(lastLayer)
							.stream()
							.sorted(Comparator.comparingInt(LayerCell::getID))
							.collect(Collectors.toList());
					
					lastLayer.setNbFilter(reverseEdge.get(0).getNbFilter() + reverseEdge.get(1).getNbFilter());
					lastLayer.setImgSize(reverseEdge.get(0).getImgSize());
					currentNBFilters = reverseEdge.get(0).getNbFilter() + reverseEdge.get(1).getNbFilter();
					compressedFilter();
				}
				
				if (lastLayer.getTypeLayer() == TypeLayerEnum.ADD) {
					List<LayerCell> reverseEdge = graph.getReverseEdge(lastLayer)
							.stream()
							.sorted(Comparator.comparingInt(LayerCell::getID))
							.collect(Collectors.toList());
					
					lastLayer.setNbFilter(reverseEdge.get(0).getNbFilter());
					lastLayer.setImgSize(reverseEdge.get(0).getImgSize());
				}
			}else {
				throw new Exception("GestionHpp setGraphHPP: edges > 2");
			}
		}
	    
		graph.removeTempLayer();

		verifyImgSize(graph);
		
	    verifyFilters(graph);
	    
	    

	}
	


	private void verifyImgSize(ArchitectureGraph graph) throws Exception {
		for(LayerCell listCellAddOrConcat:graph.inverse().keySet().stream()
				.filter(c-> c.getTypeLayer() == TypeLayerEnum.ADD || c.getTypeLayer() == TypeLayerEnum.CONCAT)
				.sorted(Comparator.comparingInt(LayerCell::getID)
						.reversed())
				.collect(Collectors.toList())) {
			
			List<LayerCell> listEdges = graph.getReverseEdge(listCellAddOrConcat).stream().collect(Collectors.toList());
			
			if(listEdges.get(0).getImgSize() != listEdges.get(1).getImgSize()) {
				if(listEdges.get(1).getTypeLayer() == TypeLayerEnum.CONV) {
					int startImgSize = graph.getReverseEdge(listEdges.get(0)).stream().collect(Collectors.toList()).get(0).getImgSize();
					recomputeSize(listEdges.get(1), startImgSize, listEdges.get(0).getImgSize());
					
				}
			}
		}
		
	}

	private void recomputeSize(LayerCell layerCell, int startImgSize, int objectifImgSize) throws Exception {
		LayerInterface layerInterface = layerCell.getLayer();
		
		int newKnl = 0;
		String newPad = "";
		
		int newStride = Math.round(startImgSize/objectifImgSize);
		
		if( calculCurrentSize("same", newStride, newStride,  startImgSize) == objectifImgSize) {
			newPad="same";
			int s = newStride;
			List<Integer> kernel_value_filtered = kernel.stream().filter(k -> k <= startImgSize && k>=s).collect(Collectors.toList());
			newKnl = kernel_value_filtered.get(rand.nextInt(kernel_value_filtered.size()));
		}else {
			newPad = "valid";
			newKnl = newStride;
			while((calculCurrentSize(newPad, newKnl, newStride,  startImgSize) != objectifImgSize) && (newKnl<= Math.round(startImgSize/2))){
				newKnl+=1;
			}
		}
		
		
		
		if(layerInterface instanceof Convolution) {
			((Convolution) layerInterface).setKernel(newKnl);
			((Convolution) layerInterface).setPadding(newPad);
			((Convolution) layerInterface).setStride(newStride);
			
		}else if(layerInterface instanceof Pooling) {
			((Pooling) layerInterface).setKernel(newKnl);
			((Pooling) layerInterface).setPadding(newPad);
			((Pooling) layerInterface).setStride(newStride);
		}else {
			throw new Exception("GestionHPP recomputeSize : layerinterface Wrong type");
		}
		
		layerCell.setImgSize(calculCurrentSize(newPad, newKnl, newStride,  startImgSize));
		layerCell.setLayer(layerInterface);
		
	}

	private void verifyFilters(ArchitectureGraph graph) throws Exception {
				
		for(LayerCell cell:graph.inverse().keySet().stream().sorted(Comparator.comparingInt(LayerCell::getID)
				.reversed()).collect(Collectors.toList())) {
			if(cell.getTypeLayer() == TypeLayerEnum.ADD) {
				List<LayerCell> edge = graph.getReverseEdge(cell)
						.stream()
						.sorted(Comparator.comparingInt(LayerCell::getID)
								.reversed())
						.collect(Collectors.toList());
				
				LayerCell lastLayerRight = edge.get(0);
				LayerCell lastLayerLeft = edge.get(1);
				
					
				if(lastLayerRight.getNbFilter() != lastLayerLeft.getNbFilter()) {
					lastLayerRight.setNbFilter(lastLayerLeft.getNbFilter());
					
					LayerCell lastConvRight = lastLayerRight;
					while(lastConvRight.getTypeLayer() != TypeLayerEnum.CONV) {
						lastConvRight = graph.getReverseEdge(lastLayerRight).stream().collect(Collectors.toList()).get(0);
					}
					
					if(lastConvRight.getLayer() instanceof Convolution) {
						Convolution conv = (Convolution) lastConvRight.getLayer();
						conv.setNbFilter(lastLayerLeft.getNbFilter());
					}else {
						throw new Exception("GestionHPP verifyFilters: conv not find");
					}
					
				}
				
				
			}
		}
		
	}

	void gestionMerge(ArchitectureGraph graph, LayerCell layerFirst, LayerCell layerLast, boolean isEmpty) throws Exception {
		
		if(layerFirst.getTypeLayer() == TypeLayerEnum.TEMP_LAYER) {
			LayerCell reverseEdge = graph.getReverseEdge(layerFirst).stream().collect(Collectors.toList()).get(0);
			layerFirst.setImgSize(reverseEdge.getImgSize());
			layerFirst.setNbFilter(reverseEdge.getNbFilter());
		}
		
		if(isEmpty) {
			for(Entry<LayerCell, Set<LayerCell>> cell: graph.getGraph().entrySet()) {
				if(cell.getKey().getID()>layerFirst.getID() && cell.getKey().getID()<layerLast.getID()) {
					cell.getKey().setReduction(false);
					cell.getKey().setLayer(
							getHyperParametters(
									cell.getKey().getTypeLayer(), 
									cell.getKey().isReduction(), 
									cell.getKey().isLast()));
					cell.getKey().setImgSize(currentSizeImg);
					cell.getKey().setNbFilter(currentNBFilters);
				}
			}
		}else {
			LayerCell FirstLayerLeft = graph.getEdge(layerFirst)
					.stream()
					.collect(Collectors.toList()).get(0);
			
			LayerCell FirstLayerRight = graph.getEdge(layerFirst)
					.stream()
					.collect(Collectors.toList()).get(1);

			// list that contain layer in leftway
			List<LayerCell> leftWay = new ArrayList<>();

			LayerCell layerLeft = FirstLayerLeft;
			while(layerLeft.getID() != layerLast.getID()) {
				leftWay.add(layerLeft);
				layerLeft = graph.getEdge(layerLeft).stream()
						.sorted(Comparator.comparingInt(LayerCell::getID))
						.collect(Collectors.toList()).get(0);
			}
			
			// list that contain all layer in RightWay
			List<LayerCell> RightWay = new ArrayList<>();

			LayerCell layerRight = FirstLayerRight;
			while(layerRight.getID() != layerLast.getID()) {
				RightWay.add(layerRight);
				if(graph.getEdge(layerRight).stream()
						.sorted(Comparator.comparingInt(LayerCell::getID))
						.collect(Collectors.toList()).size()==2) {
					layerRight = graph.getEdge(layerRight).stream()
							.sorted(Comparator.comparingInt(LayerCell::getID))
							.collect(Collectors.toList()).get(1);
				}else {
					layerRight = graph.getEdge(layerRight).stream()
							.sorted(Comparator.comparingInt(LayerCell::getID))
							.collect(Collectors.toList()).get(0);
				}
				
				
			}
			
			//find if other merge exist in leftway
			List<LayerCell> listOfMerge = leftWay.stream()
					.filter(c -> graph.getEdge(c).size()>1)
					.sorted(Comparator.comparingInt(LayerCell::getID))
					.collect(Collectors.toList());
			if(listOfMerge.size()>0) {
				LayerCell firstMerge = listOfMerge.get(0);
				List<LayerCell> edges = graph.getEdge(firstMerge).stream().collect(Collectors.toList());
				boolean newIsEmpty = (edges.get(1).getTypeLayer() == TypeLayerEnum.ADD ||  edges.get(1).getTypeLayer() == TypeLayerEnum.CONCAT);
				
				LayerCell lastMerge = edges.get(1);
				while(lastMerge.getTypeLayer() != TypeLayerEnum.ADD && lastMerge.getTypeLayer() != TypeLayerEnum.CONCAT) {
					leftWay.add(layerRight);
					lastMerge = graph.getEdge(lastMerge).stream()
							.sorted(Comparator.comparingInt(LayerCell::getID))
							.collect(Collectors.toList()).get(0);
					
				}
				
				gestionMerge(graph, firstMerge, lastMerge, newIsEmpty);
			}
			
			List<LayerCell> leftWayEmptyLayer = leftWay.stream()
					.filter(c -> c.getLayer() == null)
					.collect(Collectors.toList());
			
			if(leftWayEmptyLayer.stream()
					.filter(c -> (c.getTypeLayer() == TypeLayerEnum.CONV || c.getTypeLayer() == TypeLayerEnum.POOL))
					.collect(Collectors.toList()).isEmpty() || 
			   RightWay.stream()
					.filter(c -> (c.getTypeLayer() == TypeLayerEnum.CONV || c.getTypeLayer() == TypeLayerEnum.POOL))
					.collect(Collectors.toList()).isEmpty()) {
				// no reduction
				
				//handle leftway
				for(int i=0; i<leftWay.size();i++)
					if(leftWay.get(i).getLayer() == null) {
						LayerCell prevlayer = graph.getByID(leftWay.get(i).getID()-1);
						leftWay.get(i).setReduction(false);
						leftWay.get(i).setLayer(getHyperParametters(leftWay.get(i).getTypeLayer(), leftWay.get(i).isReduction(), leftWay.get(i).isLast()));
						leftWay.get(i).setNbFilter(prevlayer.getNbFilter());
						leftWay.get(i).setImgSize(prevlayer.getImgSize());
				}
				
				//handle rightway
				for(int i=0; i<RightWay.size();i++)
					if(RightWay.get(i).getLayer() == null) {
						LayerCell prevlayer = graph.getByID(RightWay.get(i).getID()-1);
						RightWay.get(i).setReduction(false);
						RightWay.get(i).setLayer(getHyperParametters(RightWay.get(i).getTypeLayer(), RightWay.get(i).isReduction(), RightWay.get(i).isLast()));
						RightWay.get(i).setNbFilter(prevlayer.getNbFilter());
						RightWay.get(i).setImgSize(prevlayer.getImgSize());
				}
			}else {
				// reduction
				
				//handle leftway
				List<LayerCell> leftWayEmptyLayerConvOrPool = leftWayEmptyLayer.stream().filter(c -> (c.getTypeLayer() == TypeLayerEnum.CONV || c.getTypeLayer() == TypeLayerEnum.POOL))
						.collect(Collectors.toList());
				int redu = rand.nextInt(leftWayEmptyLayerConvOrPool.size());
				for(int i = 0; i<leftWay.size();i++) {
					if(leftWay.get(i).getID() == leftWayEmptyLayerConvOrPool.get(redu).getID()) {
						leftWay.get(i).setLayer(getHyperParametters(leftWay.get(i).getTypeLayer(), leftWay.get(i).isReduction(), leftWay.get(i).isLast()));
						leftWay.get(i).setImgSize(currentSizeImg);
						leftWay.get(i).setNbFilter(currentNBFilters);
					}else { 
						if(leftWay.get(i).getLayer() == null) {
							LayerCell prevlayer = graph.getByID(leftWay.get(i).getID()-1);
							leftWay.get(i).setReduction(false);
							leftWay.get(i).setLayer(getHyperParametters(leftWay.get(i).getTypeLayer(),leftWay.get(i).isReduction(), leftWay.get(i).isLast()));
							leftWay.get(i).setNbFilter(prevlayer.getNbFilter());
							leftWay.get(i).setImgSize(prevlayer.getImgSize());
						}
					}
				}
					
				LayerCell layerRedu = leftWayEmptyLayerConvOrPool.get(redu);
				
				//hangle rightWay
				List<LayerCell> rightWayEmptyLayerConvOrPool = RightWay.stream()
						.filter(c -> (c.getTypeLayer() == TypeLayerEnum.CONV || c.getTypeLayer() == TypeLayerEnum.POOL))
						.collect(Collectors.toList());
				
				redu = rand.nextInt(rightWayEmptyLayerConvOrPool.size());
				for(int i = 0; i<RightWay.size();i++) {
					if(RightWay.get(i).getID() == rightWayEmptyLayerConvOrPool.get(redu).getID()) {
						if(layerRedu.getTypeLayer() == RightWay.get(i).getTypeLayer()) {
							if(layerRedu.getTypeLayer() == TypeLayerEnum.CONV) {
								Convolution conv = (Convolution) layerRedu.getLayer();
								RightWay.get(i).setLayer(conv);
								RightWay.get(i).setImgSize(calculCurrentSize(conv.getPadding(),conv.getKernel(), conv.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}else {
								Pooling poolRedu = (Pooling) layerRedu.getLayer();
								RightWay.get(i).setLayer(poolRedu);
								RightWay.get(i).setImgSize(calculCurrentSize(poolRedu.getPadding(),poolRedu.getKernel(), poolRedu.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}

						}else if(layerRedu.getTypeLayer() == TypeLayerEnum.CONV){
							Convolution convRedu = (Convolution) layerRedu.getLayer();
							if(RightWay.get(i).getTypeLayer() == TypeLayerEnum.POOL) {
								Pooling pool = new Pooling();
								pool.setKernel(convRedu.getKernel());
								pool.setPadding(convRedu.getPadding());
								pool.setStride(convRedu.getStride());
								
								RightWay.get(i).setLayer(pool);
								RightWay.get(i).setImgSize(calculCurrentSize(pool.getPadding(),pool.getKernel(), pool.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}else {
								throw new Exception("GestionHPP gestionMerge: rightway not pool");
							}
						}else if(layerRedu.getTypeLayer() == TypeLayerEnum.POOL) {
							Pooling poolRedu = (Pooling) layerRedu.getLayer();
							if(RightWay.get(i).getTypeLayer() == TypeLayerEnum.CONV) {
								Convolution conv = new Convolution();
								conv.setKernel(poolRedu.getKernel());
								conv.setPadding(poolRedu.getPadding());
								conv.setStride(poolRedu.getStride());
								conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
								conv.setNbFilter(layerRedu.getNbFilter());
								
								RightWay.get(i).setLayer(conv);
								RightWay.get(i).setImgSize(calculCurrentSize(conv.getPadding(),conv.getKernel(), conv.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}else {
								throw new Exception("GestionHPP gestionMerge: rightway not conv");
							}
							
						}else {
							throw new Exception("GestionHPP gestionMerge: layerRedu not conv or pool");
						}

					}else {
						if(RightWay.get(i).getLayer() == null) {
							LayerCell prevlayer = graph.getByID(RightWay.get(i).getID()-1);
							RightWay.get(i).setReduction(false);
							RightWay.get(i).setLayer(getHyperParametters(RightWay.get(i).getTypeLayer(),RightWay.get(i).isReduction(), RightWay.get(i).isLast()));
							RightWay.get(i).setImgSize(prevlayer.getImgSize());
							RightWay.get(i).setNbFilter(prevlayer.getNbFilter());
						}
					}
					
				}
				
			}
			
		}

	}

	private LayerInterface getHyperParametters(TypeLayerEnum typeLayer, boolean reduction, boolean last) throws Exception {
		if(TypeLayerEnum.CONV == typeLayer) {
			return gestionConvolution(reduction);
		}else if (TypeLayerEnum.POOL == typeLayer) {
			return gestionPooling(reduction);
		}else if (TypeLayerEnum.BN == typeLayer) {
			return gestionBN();
		}else if (TypeLayerEnum.DROP == typeLayer) {
			return gestionDropout();
		}else if (TypeLayerEnum.DENSE == typeLayer) {
			return gestionDense(last);
		}else {
			return null;
		}
		
	}

	// replace TypeLayerEnum.TEMP to TypeLayerEnum.ADD or TypeLayerEnum.CONCAT
	public void replaceTempToAddOrConcat(ArchitectureGraph graph) {

		
		List<LayerCell> listTemp = graph.getGraph().keySet().stream().filter(c -> c.getTypeLayer() == TypeLayerEnum.TEMP_ADD_CONCAT).collect(Collectors.toList());
		for(LayerCell elem: listTemp) {
			str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
			if(str_add_or_concat == "add") elem.setTypeLayer(TypeLayerEnum.ADD);
			else elem.setTypeLayer(TypeLayerEnum.CONCAT);
		}
		
	}
	
}
