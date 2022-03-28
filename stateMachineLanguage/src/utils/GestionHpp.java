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
import domain.Add;
import domain.BatchNormalisation;
import domain.Concatenate;
import domain.Convolution;
import domain.Dense;
import domain.Dropout;
import domain.Input;
import domain.Interstice;
import domain.LayerInterface;
import domain.Output;
import domain.Pooling;
import domain.TempLayer;
import models.ArchitectureGraph;
import models.MergeSimple;
import models.TypeLayerEnum;
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
	
	public void gestionConvolution(Convolution conv, boolean reduction){
		
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
		if(entryParams == 0) entryParams = currentSizeImg*currentSizeImg*currentNBFilters;
		
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
	
	
	private static void optimisationKernelPaddingStride(LayerInterface layer) {
		
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
	
	private static int calculCurrentSize(String pad, int knl, int strd, int imgSize) {
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
			ms.addLeft(new Pooling());
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
			ms.addLeft(new Pooling());
		}
		
	}
	
	
	private static class Randomizer {
	    public static int generate(int min,int max) {
	        return min + (int)(Math.random() * ((max - min) + 1));
	    }
	}

	
	/** 
	 * 
	 * ===== NEW VERSION USING GRAPH =======
	 * @throws Exception 
	 * 
	 **/
	
	public void setGraphHPP(ArchitectureGraph graph) throws Exception {
		
		/*
		System.out.println("\n\n");
		for(Entry<LayerCell, Set<LayerCell>> i: graph.getGraph().entrySet()){
			
			System.out.println(i);
			System.out.println("\n");
		}*/
		
		// find last layer
		int idLastLayer = graph.getGraph().keySet()
						  .stream()
						  .filter(c -> c instanceof Output)
						  .collect(Collectors.toList())
						  .get(0)
						  .getLayerPos() - 1;
	    graph.getByID(idLastLayer).setLast(true);
		
	    // init Input
		int i = 0;
		LayerInterface layer = graph.getByID(i);
		layer.setNbFilter(INPUT_FILTER);
		layer.setImgSize(MAX_SIZE_IMG);
		i++;
		
		while(i<graph.getGraph().size()) {
			layer = graph.getByID(i);
			List<LayerInterface> edges = graph.getEdge(layer);
			
			if(edges.size()==1 || (layer instanceof Output)) {
			// manage simple layer	
				getHyperparametters(
						layer, 
						graph.getReverseEdge(layer).get(0), 
						(graph.getReverseEdge(layer).size()==2) ?  graph.getReverseEdge(layer).get(1) : null);
				i++;
			}else if(edges.size()==2) {
			// manage Merge
				getHyperparametters(
						layer, 
						graph.getReverseEdge(layer).get(0), 
						(graph.getReverseEdge(layer).size()==2) ?  graph.getReverseEdge(layer).get(1) : null);
				
				LayerInterface lastLayer = null;
				if(edges.get(1) instanceof Concatenate || edges.get(1) instanceof Add) {
					lastLayer = edges.get(1);
					gestionMerge(graph, layer, lastLayer, true);
				}else {
					lastLayer = edges.get(0);
					while(!(lastLayer instanceof Concatenate) &&
							 !(lastLayer instanceof Add) ) {
								
							List<LayerInterface> lastLayerEdges = graph.getEdge(lastLayer);
							lastLayer = lastLayerEdges.get(0);
						}
						
						gestionMerge(graph, layer, lastLayer, false);
				}
				
				i=lastLayer.getLayerPos();
			}else {
				throw new Exception("GestionHpp setGraphHPP: edges > 2 or < 1");
			}
		}
	    
		graph.removeTempLayer();
		
		verifyAllLayer(graph);

		verifyImgSize(graph);
		
	    verifyFilters(graph);
	    
	    

	}

	private void verifyAllLayer(ArchitectureGraph graph) throws Exception {
		Map<LayerInterface, List<LayerInterface>> listFiltred = graph.getReverseGraph().entrySet().stream()
				.filter(c -> c.getKey().getNbFilter() == 0 || (c instanceof Convolution && ((Convolution)c).getFct_activation() == null))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		for(Entry<LayerInterface, List<LayerInterface>> cell : listFiltred.entrySet()) {
			cell.getKey().setReduction(false);
			getHyperparametters(
					cell.getKey(), 
					cell.getValue().get(0), 
					(cell.getValue().size()==2) ?  cell.getValue().get(1) : null);
		}
	}

	private void verifyImgSize(ArchitectureGraph graph) throws Exception {
		for(LayerInterface listCellAddOrConcat:graph.inverse().keySet().stream()
				.filter(c-> c instanceof Add || c instanceof Concatenate)
				.sorted(Comparator.comparingInt(LayerInterface::getLayerPos)
						.reversed())
				.collect(Collectors.toList())) {
			
			List<LayerInterface> listEdges = graph.getReverseEdge(listCellAddOrConcat).stream().collect(Collectors.toList());
			
			if(listEdges.get(0).getImgSize() < listEdges.get(1).getImgSize()) {
				// right way don't reduce enough 
				LayerInterface findConv = listEdges.get(1);
				while(!(findConv instanceof Convolution)) {
					findConv = graph.getReverseEdge(findConv).get(0);
				}
				
				if(findConv instanceof Convolution) {
					int startImgSize = graph.getReverseEdge(findConv).get(0).getImgSize();
					recomputeSize(findConv, startImgSize, listEdges.get(0).getImgSize());
					
				}
			}else if (listEdges.get(0).getImgSize() > listEdges.get(1).getImgSize()){
				// right way reduce too much
				LayerInterface findConv = listEdges.get(0);
				while(!(findConv instanceof Convolution) && !(findConv instanceof Pooling)) {
					if(findConv instanceof Input) {
						throw new Exception("GestionHPP verifyImgSize : can't find conv or pool");
					}
					findConv = graph.getReverseEdge(findConv).get(0);
				}
				
				int startImgSize = graph.getReverseEdge(findConv).get(0).getImgSize();
				recomputeSize(findConv, startImgSize, listEdges.get(1).getImgSize());
				
				
			}else {
				// all good
			}
		}
		
	}

	private void recomputeSize(LayerInterface layerInterface, int startImgSize, int objectifImgSize) throws Exception {
		
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
		
		layerInterface.setImgSize(calculCurrentSize(newPad, newKnl, newStride,  startImgSize));
		
	}

	private void verifyFilters(ArchitectureGraph graph) throws Exception {
				
		for(LayerInterface cell:graph.inverse().keySet().stream().sorted(Comparator.comparingInt(LayerInterface::getLayerPos)
				.reversed()).collect(Collectors.toList())) {
			if(cell instanceof Add) {
				List<LayerInterface> edge = graph.getReverseEdge(cell)
						.stream()
						.sorted(Comparator.comparingInt(LayerInterface::getLayerPos)
								.reversed())
						.collect(Collectors.toList());
				
				LayerInterface lastLayerRight = edge.get(0);
				LayerInterface lastLayerLeft = edge.get(1);
				
					
				if(lastLayerRight.getNbFilter() != lastLayerLeft.getNbFilter()) {
					lastLayerRight.setNbFilter(lastLayerLeft.getNbFilter());
					
					LayerInterface lastConvRight = lastLayerRight;
					while(!(lastConvRight instanceof Convolution)) {
						lastConvRight = graph.getReverseEdge(lastLayerRight).stream().collect(Collectors.toList()).get(0);
					}
					
					if(lastConvRight instanceof Convolution) {
						
						((Convolution)lastConvRight).setNbFilter(lastLayerLeft.getNbFilter());
					}else {
						throw new Exception("GestionHPP verifyFilters: conv not find");
					}
					
				}
				
				
			}
		}
		
	}

	void gestionMerge(ArchitectureGraph graph, LayerInterface layerFirst, LayerInterface layerLast, boolean isEmpty) throws Exception {
		
		if(layerFirst instanceof TempLayer) {
			LayerInterface reverseEdge = graph.getReverseEdge(layerFirst).stream().collect(Collectors.toList()).get(0);
			layerFirst.setImgSize(reverseEdge.getImgSize());
			layerFirst.setNbFilter(reverseEdge.getNbFilter());
		}
		
		if(isEmpty) {
			for(Entry<LayerInterface, List<LayerInterface>> cell: graph.getGraph().entrySet()) {
				if(cell.getKey().getLayerPos()>layerFirst.getLayerPos() && cell.getKey().getLayerPos()<layerLast.getLayerPos()) {
					cell.getKey().setReduction(false);
					getHyperparametters(
							cell.getKey(), 
							graph.getReverseEdge(cell.getKey()).get(0), 
							(graph.getReverseEdge(cell.getKey()).size()==2) ?  graph.getReverseEdge(cell.getKey()).get(1) : null);
				}
			}
		}else {
			LayerInterface FirstLayerLeft = graph.getEdge(layerFirst)
					.stream()
					.collect(Collectors.toList()).get(0);
			
			LayerInterface FirstLayerRight = graph.getEdge(layerFirst)
					.stream()
					.collect(Collectors.toList()).get(1);

			// list that contain layer in leftway
			List<LayerInterface> leftWay = new ArrayList<>();

			LayerInterface layerLeft = FirstLayerLeft;
			while((layerLeft.getLayerPos() != layerLast.getLayerPos()) && (layerLeft.getLayerPos() <= layerLast.getLayerPos())) {
				leftWay.add(layerLeft);
				layerLeft = graph.getEdge(layerLeft).stream()
						.sorted(Comparator.comparingInt(LayerInterface::getLayerPos))
						.collect(Collectors.toList()).get(0);
			}
			
			// list that contain all layer in RightWay
			List<LayerInterface> RightWay = new ArrayList<>();

			LayerInterface layerRight = FirstLayerRight;
			while((layerRight.getLayerPos() != layerLast.getLayerPos()) &&
					((layerRight.getLayerPos() <= layerLast.getLayerPos()) && 
					(!(layerRight instanceof Add) || !(layerRight instanceof Concatenate)))) {
				
				RightWay.add(layerRight);
				if(graph.getEdge(layerRight).stream()
						.sorted(Comparator.comparingInt(LayerInterface::getLayerPos))
						.collect(Collectors.toList()).size()==2) {
					layerRight = graph.getEdge(layerRight).stream()
							.sorted(Comparator.comparingInt(LayerInterface::getLayerPos))
							.collect(Collectors.toList()).get(1);
				}else {
					layerRight = graph.getEdge(layerRight).stream()
							.sorted(Comparator.comparingInt(LayerInterface::getLayerPos))
							.collect(Collectors.toList()).get(0);
					
				}
				
				
			}
			
			//find if other merge exist in leftway
			List<LayerInterface> listOfMerge = leftWay.stream()
					.filter(c -> graph.getEdge(c).size()>1)
					.sorted(Comparator.comparingInt(LayerInterface::getLayerPos))
					.collect(Collectors.toList());
			if(listOfMerge.size()>0) {
				LayerInterface firstMerge = listOfMerge.get(0);
				List<LayerInterface> edges = graph.getEdge(firstMerge).stream().collect(Collectors.toList());
				boolean newIsEmpty = (edges.get(1) instanceof Add ||  edges.get(1) instanceof Concatenate);
				
				LayerInterface lastMerge = edges.get(1);
				while(lastMerge instanceof Add && lastMerge instanceof Concatenate) {
					leftWay.add(layerRight);
					lastMerge = graph.getEdge(lastMerge).stream()
							.sorted(Comparator.comparingInt(LayerInterface::getLayerPos))
							.collect(Collectors.toList()).get(0);
					
				}
				
				gestionMerge(graph, firstMerge, lastMerge, newIsEmpty);
			}
			
			List<LayerInterface> leftWayEmptyLayer = leftWay.stream()
					.filter(c -> c.getNbFilter() == 0)
					.collect(Collectors.toList());
			
			if(leftWayEmptyLayer.stream()
					.filter(c -> (c instanceof Convolution || c instanceof Pooling))
					.collect(Collectors.toList()).isEmpty() || 
			   RightWay.stream()
					.filter(c -> (c instanceof Convolution || c instanceof Pooling))
					.collect(Collectors.toList()).isEmpty()) {
				// no reduction
				
				//handle leftway
				for(int i=0; i<leftWay.size();i++)
					if(leftWay.get(i).getNbFilter() == 0) {
						LayerInterface prevlayer = graph.getByID(leftWay.get(i).getLayerPos()-1);
						leftWay.get(i).setReduction(false);
						getHyperparametters(
								leftWay.get(i), 
								graph.getReverseEdge(leftWay.get(i)).get(0), 
								(graph.getReverseEdge(leftWay.get(i)).size()==2) ? graph.getReverseEdge(leftWay.get(i)).get(1) : null);
				}
				
				//handle rightway
				for(int i=0; i<RightWay.size();i++)
					if(RightWay.get(i).getNbFilter() == 0) {
						LayerInterface prevlayer = graph.getByID(RightWay.get(i).getLayerPos()-1);
						RightWay.get(i).setReduction(false);
						getHyperparametters(
								RightWay.get(i), 
								graph.getReverseEdge(RightWay.get(i)).get(0), 
								(graph.getReverseEdge(RightWay.get(i)).size()==2) ? graph.getReverseEdge(RightWay.get(i)).get(1) : null);
				}
			}else {
				// reduction
				
				//handle leftway
				List<LayerInterface> leftWayEmptyLayerConvOrPool = leftWayEmptyLayer.stream().filter(c -> (c instanceof Convolution || c instanceof Pooling))
						.collect(Collectors.toList());
				int redu = rand.nextInt(leftWayEmptyLayerConvOrPool.size());
				for(int i = 0; i<leftWay.size();i++) {
					if(leftWay.get(i).getLayerPos() == leftWayEmptyLayerConvOrPool.get(redu).getLayerPos()) {
						getHyperparametters(
								leftWay.get(i), 
								graph.getReverseEdge(leftWay.get(i)).get(0), 
								(graph.getReverseEdge(leftWay.get(i)).size()==2) ? graph.getReverseEdge(leftWay.get(i)).get(1) : null);

					}else { 
						if(leftWay.get(i).getNbFilter() == 0) {
							LayerInterface prevlayer = graph.getByID(leftWay.get(i).getLayerPos()-1);
							leftWay.get(i).setReduction(false);
							getHyperparametters(
									leftWay.get(i), 
									graph.getReverseEdge(leftWay.get(i)).get(0), 
									(graph.getReverseEdge(leftWay.get(i)).size()==2) ? graph.getReverseEdge(leftWay.get(i)).get(1) : null);

						}
					}
				}
					
				LayerInterface layerRedu = leftWayEmptyLayerConvOrPool.get(redu);
				
				//hangle rightWay
				List<LayerInterface> rightWayEmptyLayerConvOrPool = RightWay.stream()
						.filter(c -> (c instanceof Convolution || c instanceof Pooling))
						.collect(Collectors.toList());
				
				redu = rand.nextInt(rightWayEmptyLayerConvOrPool.size());
				for(int i = 0; i<RightWay.size();i++) {
					if(RightWay.get(i).getLayerPos() == rightWayEmptyLayerConvOrPool.get(redu).getLayerPos()) {
						if((layerRedu instanceof Convolution &&  RightWay.get(i) instanceof Convolution) 
								|| layerRedu instanceof Pooling &&  RightWay.get(i) instanceof Pooling) {
							if(layerRedu instanceof Convolution) {
								Convolution conv = (Convolution) layerRedu;
								((Convolution) RightWay.get(i)).setFct_activation(conv.getFct_activation());
								((Convolution) RightWay.get(i)).setKernel(conv.getKernel());
								((Convolution) RightWay.get(i)).setPadding(conv.getPadding());
								((Convolution) RightWay.get(i)).setStride(conv.getStride());
								RightWay.get(i).setImgSize(calculCurrentSize(conv.getPadding(),conv.getKernel(), conv.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}else {
								Pooling poolRedu = (Pooling) layerRedu;
								((Pooling)RightWay.get(i)).setPadding(poolRedu.getPadding());
								((Pooling)RightWay.get(i)).setKernel(poolRedu.getKernel());
								((Pooling)RightWay.get(i)).setStride(poolRedu.getStride());
								RightWay.get(i).setImgSize(calculCurrentSize(poolRedu.getPadding(),poolRedu.getKernel(), poolRedu.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}

						}else if(layerRedu instanceof Convolution){
							Convolution convRedu = (Convolution) layerRedu;
							if(RightWay.get(i) instanceof Pooling) {
								Pooling pool = new Pooling();
								pool.setKernel(convRedu.getKernel());
								pool.setPadding(convRedu.getPadding());
								pool.setStride(convRedu.getStride());
								
								RightWay.get(i).setImgSize(calculCurrentSize(pool.getPadding(),pool.getKernel(), pool.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}else {
								throw new Exception("GestionHPP gestionMerge: rightway not pool");
							}
						}else if(layerRedu instanceof Pooling) {
							Pooling poolRedu = (Pooling) layerRedu;
							if(RightWay.get(i) instanceof Convolution) {
								Convolution conv = new Convolution();
								conv.setKernel(poolRedu.getKernel());
								conv.setPadding(poolRedu.getPadding());
								conv.setStride(poolRedu.getStride());
								conv.setFct_activation(fctActivation.get(rand.nextInt(fctActivation.size())));
								conv.setNbFilter(layerRedu.getNbFilter());

								RightWay.get(i).setImgSize(calculCurrentSize(conv.getPadding(),conv.getKernel(), conv.getStride(), layerFirst.getImgSize()));
								RightWay.get(i).setNbFilter(layerRedu.getNbFilter());
							}else {
								throw new Exception("GestionHPP gestionMerge: rightway not conv");
							}
							
						}else {
							throw new Exception("GestionHPP gestionMerge: layerRedu not conv or pool");
						}

					}else {
						if(RightWay.get(i).getNbFilter() == 0) {
							LayerInterface prevlayer = graph.getByID(RightWay.get(i).getLayerPos()-1);
							RightWay.get(i).setReduction(false);
							getHyperparametters(
									RightWay.get(i), 
									graph.getReverseEdge(RightWay.get(i)).get(0), 
									(graph.getReverseEdge(RightWay.get(i)).size()==2) ? graph.getReverseEdge(RightWay.get(i)).get(1) : null);

						}
					}
					
				}
				
			}
			
		}

	}

	private void getHyperparametters(LayerInterface layer, LayerInterface prevLayer, LayerInterface prevLayerConcat) throws Exception {
		currentSizeImg = prevLayer.getImgSize();
		if(currentSizeImg == 0) {
			System.out.println(layer);
			System.out.println(prevLayer);
		}
		currentNBFilters = prevLayer.getNbFilter();
		
		if(layer instanceof Convolution) {
			gestionConvolution((Convolution) layer,layer.isReduction());
			layer.setImgSize(calculCurrentSize(
					((Convolution) layer).getPadding(), 
					((Convolution) layer).getKernel(), 
					((Convolution) layer).getStride(), 
					prevLayer.getImgSize()));
			
		}else if (layer instanceof Pooling) {
			gestionPooling((Pooling) layer, layer.isReduction());
			layer.setImgSize(calculCurrentSize(
					((Pooling) layer).getPadding(), 
					((Pooling) layer).getKernel(), 
					((Pooling) layer).getStride(), 
					prevLayer.getImgSize()));
			layer.setNbFilter(currentNBFilters);
			
		}else if (layer instanceof BatchNormalisation) {
			gestionBN((BatchNormalisation) layer);
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
			
		}else if (layer instanceof Dropout) {
			gestionDropout((Dropout) layer);
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
		}else if (layer instanceof Dense) {
			gestionDense((Dense) layer, layer.isLast());
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
			
		}else if (layer instanceof Concatenate){
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(prevLayer.getNbFilter() + prevLayerConcat.getNbFilter());
		}else if (layer instanceof Add) {
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
		}else if (layer instanceof TempLayer) {
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
		}else if (layer instanceof Interstice) {
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
		}else if(layer instanceof Output){
			layer.setImgSize(currentSizeImg);
			layer.setNbFilter(currentNBFilters);
		}else {
			throw new Exception("GestionHpp getHyperparametters: Layer Forgotten ?");
		}
		
	}
	
}
