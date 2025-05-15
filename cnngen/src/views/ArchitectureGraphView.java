package views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import domain.Add;
import domain.BatchNormalisation;
import domain.Concatenate;
import domain.Dense;
import domain.Dropout;
import domain.Input;
import domain.Layer;
import domain.Output;
import domain.Pooling;
import domain.TempLayer;
import lombok.Data;
import utils.GestionHPP;
import xtext.cNNDescLang.Architecture;
import xtext.cNNDescLang.Classification;
import xtext.cNNDescLang.ConvDrop;
import xtext.cNNDescLang.Convolution;
import xtext.cNNDescLang.FeatureExtraction;
import xtext.cNNDescLang.Interstice;
import xtext.cNNDescLang.Left;
import xtext.cNNDescLang.Merge;
import xtext.cNNDescLang.MergeBody;
import xtext.cNNDescLang.MergeConv;
import xtext.cNNDescLang.Right;

@Data
public class ArchitectureGraphView {
	
	//Controllers
	private final static int MAX_BUDGET_IMG = 10;
	private final static int MAX_NB_VERIF = 10;
	
	private List<Layer> graph;
	private int layerPos;
	private static Random rand = new Random();
	private static List<String> add_or_concat = new ArrayList<String>(List.of("add", "concat"));
	private int flops;
	private int nbParams;
	
	private Layer lastLayer; 
	private String str_add_or_concat;
	
	
	private int budgetImg = 0;
	
	
	// constructor
	public ArchitectureGraphView(){
		this.graph = new ArrayList<>();
		this.layerPos = 0;
	}
	
	public Layer getLayerByLayerpos(int layerpos) {
		for( Layer l : graph){
			if (l.getLayerPos() == layerpos){
				return l;
			}
		}
		return null;
	}
	
	public float computeFlops() {
		for(Layer l : graph) {
			if(l instanceof domain.Convolution) {
				domain.Convolution c = (domain.Convolution) l;
				flops += 2*c.getInputFilter()*c.getKernel()*c.getKernel()*c.getOutputImgSize()*c.getOutputImgSize();
			}else if (l instanceof Pooling) {
				Pooling p = (Pooling) l;
				flops += 2*p.getInputFilter()*p.getKernel()*p.getKernel()*p.getOutputImgSize()*p.getOutputImgSize();

			}else if (l instanceof Dense) {
				Dense d = (Dense) l;
				flops += 2*d.getInputImgSize()*d.getOutputImgSize();
			}
		}
		return flops;
	}
	
	public int computeParameters() {
		for (Layer l : graph) {
			if(l instanceof domain.Convolution) {
				domain.Convolution c = (domain.Convolution) l;
				nbParams += (c.getKernel()*c.getKernel()*c.getInputFilter() + 1 )*c.getOutputFilter();
			}else if (l instanceof Dense) {
				Dense d = (Dense) l;
				int prevUnit = 0;
				if(d.getPrevLayer() instanceof domain.Interstice) {
					domain.Interstice i = (domain.Interstice) d.getPrevLayer();
					if(i.getType() == "flatten") {
						prevUnit = i.getOutputFilter()*i.getOutputImgSize()*i.getOutputImgSize();
					}else {
						prevUnit = i.getOutputFilter();
					}
					
				}else if (d.getPrevLayer() instanceof Dense){
					Dense prevDense = (Dense) d.getPrevLayer();
					prevUnit = prevDense.getUnits();
				}
				nbParams += prevUnit*d.getUnits()+d.getUnits();
			}
		}
		return nbParams;
	}
	
	
	
	// store the architecture in the DB
	public void createGraph(Architecture archi) throws Exception {
		
		str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		
		// verify input not missing
		if (!archi.getInput().isEmpty()){
					
			Input input = new Input(layerPos);
			graph.add(input);
			//inputController.createOrUpdate(input);
			layerPos++;
			lastLayer = input;
		}else{
			throw new Exception("missing Input");
		}
		
		// verify feature extraction not missing
		if (!archi.getFe().isEmpty()){
			gestionFE(archi.getFe());
			//println(a.fe)

		}else{
			throw new Exception("missing feature extra");
		}
		
		if (archi.getInter() != null && archi.getClass_() != null){
			gestionInter(archi.getInter());
			gestionDense(archi.getClass_());
		}
		
		
		if(!archi.getOutput().isEmpty()){
			
			Output output = new Output(layerPos);
			// add edge for lastlayer -> output
			
			output.getPrevLayer().add(lastLayer);
			
			// add output layer to graph
			graph.add(output);
			
			lastLayer.getNextLayer().add(output);
				
		}else{
			throw new Exception("missing output");
		}
		
		// add tempLayer for complexe ways (more than 3)
		//addTempLayer();
		
	}


	/*private void addTempLayer() {
		Iterable<Map<String, Object>> it = dbQueryController.countEdgesNodes();
		
		List<Layer> layers = new ArrayList<>();
		for(Map<String, Object> i: it) {
			if((Long) i.get("cnt")>2) {
			  layers.add((Layer) i.get("n"));
			}	
		}
	}*/

	private void gestionDense(EList<Classification> class_) throws Exception {
		for(Classification c: class_) {
			
			if (c.getDrop() != null) {
				// add prevlayer -> Dropout 
				Dropout drop = new Dropout(layerPos);
				
				drop.getPrevLayer().add(lastLayer);
				
				graph.add(drop);
				
				// add edge prevLayer -> dropout
				lastLayer.getNextLayer().add(drop);
				
				
				layerPos++;
				lastLayer = drop;
				
			}
			
			Dense dense = new Dense(layerPos);
			
			dense.getPrevLayer().add(lastLayer);
			// add dense layer to graph
			
			graph.add(dense);
			
			// add edge prevLayer -> Dense
			lastLayer.getNextLayer().add(dense);
			
			layerPos++;
			lastLayer = dense;
		}
		
	}


	private void gestionInter(Interstice interstice) throws Exception {
		if (interstice.getFg().getFlat() != null || interstice.getFg().getGp() != null){
			String type = null;
			if(interstice.getFg().getFlat() != null)
				type = "flatten";
			else if(interstice.getFg().getGp().equals("global_avg_pooling"))
				type = "global_avg_pooling";
			else if(interstice.getFg().getGp().equals("global_max_pooling"))
				type = "global_max_pooling";
			else
				System.out.println("ArchitectureGraphView gestionInter: error");
			
			domain.Interstice inter = new domain.Interstice(layerPos, type);
			
			inter.getPrevLayer().add(lastLayer);
			
			// add flatten to graph
			graph.add(inter);
			
			// add edge prevlayer -> flatten
			lastLayer.getNextLayer().add(inter);
			
			
			layerPos++;
			lastLayer = inter;
			
			
		}
		
	}


	private void gestionFE(EList<FeatureExtraction> fe) throws Exception {
		for (FeatureExtraction elem: fe){
			if (elem.getConv() != null){
				gestionConv(elem.getConv(), lastLayer);
				

			}else if (elem.getMerge() != null){
				gestionMerge(elem.getMerge());
				
			}else {
				throw new Exception("gestionFe errors"); 	
			}
			
			if (elem.getDrop() != null) {
				// add prevlayer -> Dropout 
				Dropout drop = new Dropout(layerPos);
				
				drop.getPrevLayer().add(lastLayer);
				
				graph.add(drop);
				
				// add edge prevLayer -> dropout
				lastLayer.getNextLayer().add(drop);
				
				
				layerPos++;
				lastLayer=drop;
			}
				
			if (elem.getPool() != null) {
				// add prevlayer -> Dropout 
				Pooling pool = new Pooling(layerPos);
				
				pool.getPrevLayer().add(lastLayer);
				
				graph.add(pool);
				// add edge prevLayer -> dropout
				lastLayer.getNextLayer().add(pool);
							
				
				layerPos++;
				lastLayer = pool;
			}
				
		}
		
	}


	private void gestionMerge(Merge merge) throws Exception {
		Layer layerPrevMerge = lastLayer;
		Layer firstLayer = lastLayer;
				
		if(merge.getMergeBody().size()==1) {
			gestionMergeBody(merge.getMergeBody().get(0), layerPrevMerge);
		
		}else {
			for (MergeBody mb:merge.getMergeBody()) {
				firstLayer = gestionMergeBody(mb, layerPrevMerge, firstLayer);
				layerPrevMerge = lastLayer;
				}	
			}
	}


	private Layer gestionMergeBody(MergeBody mb, Layer layerPrevMerge, Layer firstLayer) throws Exception {
		Layer lastLayerLeft = gestionLeft(mb.getLeft(), layerPrevMerge);
		Layer lastLayerRight = gestionRight(mb.getRight(), firstLayer);
		
		//String str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		
		if(str_add_or_concat == "add") {
			Add add = new Add(layerPos);
			add.getPrevLayer().add(lastLayerLeft);
			add.getPrevLayer().add(lastLayerRight);
			graph.add(add);
			lastLayerLeft.getNextLayer().add(add);
			lastLayerRight.getNextLayer().add(add);
			lastLayer = add;
		}
		else {
			Concatenate concat = new Concatenate(layerPos);
			
			concat.getPrevLayer().add(lastLayerLeft);
			concat.getPrevLayer().add(lastLayerRight);
			
			graph.add(concat);
			lastLayerLeft.getNextLayer().add(concat);
			lastLayerRight.getNextLayer().add(concat);
			lastLayer = concat;
		}
		

		layerPos++;
		return lastLayerRight;
	}


	private void gestionMergeBody(MergeBody mb, Layer layerPrevMerge) throws Exception {
		Layer lastLayerLeft = gestionLeft(mb.getLeft(), layerPrevMerge);
		Layer lastLayerRight = gestionRight(mb.getRight(), layerPrevMerge);
		
		//String str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));

		if(str_add_or_concat == "add") {
			Add add = new Add(layerPos);
			add.getPrevLayer().add(lastLayerLeft);
			add.getPrevLayer().add(lastLayerRight);
			graph.add(add);
			lastLayerLeft.getNextLayer().add(add);
			lastLayerRight.getNextLayer().add(add);
			lastLayer = add;
		}
		else {
			Concatenate concat = new Concatenate(layerPos);
			concat.getPrevLayer().add(lastLayerLeft);
			concat.getPrevLayer().add(lastLayerRight);
			graph.add(concat);
			lastLayerLeft.getNextLayer().add(concat);
			lastLayerRight.getNextLayer().add(concat);
			lastLayer = concat;
		}		


		layerPos++;

		
	}
	

	private Layer gestionRight(Right right, Layer layerPrevMerge) throws Exception {
		Layer rightPrevLayer = layerPrevMerge;
		if(!right.getConv().isEmpty()) {
			for (Convolution c: right.getConv()) {
				lastLayer = gestionConv(c, rightPrevLayer);
				rightPrevLayer = lastLayer;
			}
			
			return lastLayer;
		}else{
			return layerPrevMerge;
		}
	}


	private Layer gestionLeft(Left left, Layer layerPrevMerge) throws Exception {
		//Layer leftPrevLayer = layerPrevMerge;
		if (left.getP() != null) {
			// add prevlayer -> Dropout 
			Pooling pool = new Pooling(layerPos);
			pool.getPrevLayer().add(lastLayer);
			// add edge prevLayer -> dropout
			graph.add(pool);
			
			lastLayer.getNextLayer().add(pool);
			
			
			lastLayer = pool;
			layerPos++;
		}
		
		if (left.getCom().getConvdrop() != null){
			for (ConvDrop convdrop: left.getCom().getConvdrop()){
				lastLayer = gestionConv(convdrop.getConv(), lastLayer);
				
				if(convdrop.getDrop() != null) {
					Dropout drop = new Dropout(layerPos);
					drop.getPrevLayer().add(lastLayer);
					// add edge prevLayer -> dropout
					graph.add(drop);
					lastLayer.getNextLayer().add(drop);
					
					
					lastLayer=drop;
					layerPos++;
				}	
			}	
		}
		
		if(left.getCom().getMergeConv() != null) {
			for(MergeConv mc : left.getCom().getMergeConv()){
				
				gestionMerge(mc.getMerge());
				
				if (!mc.getConvdrop().isEmpty()){
					for (ConvDrop convdrop: mc.getConvdrop()){
						lastLayer = gestionConv(convdrop.getConv(), lastLayer);
						
						if(convdrop.getDrop() != null) {
							Dropout drop = new Dropout(layerPos);
							drop.getPrevLayer().add(lastLayer);
							// add edge prevLayer -> dropout
							graph.add(drop);
							lastLayer.getNextLayer().add(drop);
							
							lastLayer=drop;
							layerPos++;
						}
					}
				}
			}
		}
		
		if (left.getPool() != null) {
			// add prevlayer -> Dropout 
			Pooling pool = new Pooling(layerPos);
			pool.getPrevLayer().add(lastLayer);
			// add edge prevLayer -> dropout
			graph.add(pool);
			
			lastLayer.getNextLayer().add(pool);
			
			lastLayer = pool;
			layerPos++;
		}
		
		return lastLayer;
	}


	private Layer gestionConv(Convolution conv, Layer prevLayer) throws Exception {
		if(conv.getConv() != null) {
			domain.Convolution LCconv = new domain.Convolution(layerPos);
			
			LCconv.getPrevLayer().add(prevLayer);
			
			graph.add(LCconv);
			
			prevLayer.getNextLayer().add(LCconv);


			layerPos++;
			lastLayer = LCconv;
			
		}else if (conv.getBnconv() != null) {
			BatchNormalisation bn = new BatchNormalisation(layerPos);
			bn.getPrevLayer().add(prevLayer);
			prevLayer.getNextLayer().add(bn);
			
			graph.add(bn);
			
			layerPos++;
			
			domain.Convolution LCconv = new domain.Convolution(layerPos);
			LCconv.getPrevLayer().add(bn);
			graph.add(LCconv);
			
			bn.getNextLayer().add(LCconv);
			
			layerPos++;
			lastLayer= LCconv;
			
		}else if(conv.getConvbn() != null) {
			domain.Convolution LCconv = new domain.Convolution(layerPos);
			LCconv.getPrevLayer().add(prevLayer);
			graph.add(LCconv);
			
			prevLayer.getNextLayer().add(LCconv);
			
			layerPos++;
			
			BatchNormalisation bn = new BatchNormalisation(layerPos);
			bn.getPrevLayer().add(LCconv);
			graph.add(bn);

			LCconv.getNextLayer().add(bn);
			
			layerPos++;
			lastLayer=bn;
		}else {
			throw new Exception("ArchitecureGraph, GestionConv, not possible");
		}
		
		return lastLayer;
	}
	
	
	private void verificationImage(GestionHPP gestionHPP) throws Exception {
		int i = 0;
		while(i < graph.size()) {
			CorrectionLayer(graph.get(i), gestionHPP);
			i++;
		}
		
	}
	
	private void CorrectionLayer(Layer layer, GestionHPP gestionHPP) throws Exception {
		if (layer instanceof Add) {
			Add add = (Add) layer;
			if(add.getPrevLayer().get(0).getOutputImgSize() != add.getPrevLayer().get(1).getOutputImgSize()) {
				Layer layerModif = add.getPrevLayer().get(1);
				if(add.getPrevLayer().get(0).getOutputImgSize() > add.getPrevLayer().get(1).getOutputImgSize()) {
					while(layerModif.getInputImgSize() == layerModif.getOutputImgSize() && 
							layerModif.getNextLayer().size()==1 && 
							layerModif.getPrevLayer().size()==1){
						layerModif = layerModif.getPrevLayer().get(0);
					}
					if(layerModif instanceof domain.Convolution || layerModif instanceof Pooling) {
						int objectifImgSize = add.getPrevLayer().get(0).getOutputImgSize();
						gestionHPP.optiHPPfromImgObjective(layerModif, objectifImgSize);
					}
					
				}else {
					while(layerModif.getInputImgSize() == layerModif.getOutputImgSize() && 
							layerModif.getNextLayer().size()==1 && 
							layerModif.getPrevLayer().size()==1){
						layerModif = layerModif.getPrevLayer().get(0);
					}
					if(layerModif instanceof domain.Convolution || layerModif instanceof Pooling) {
						int objectifImgSize = add.getPrevLayer().get(0).getOutputImgSize();
						gestionHPP.optiHPPfromImgObjective(layerModif, objectifImgSize);
					}
						
				}
				layerModif = layerModif.getNextLayer().get(0);
				while(layerModif.getLayerPos() != add.getLayerPos()) {
					layerModif.setInputImgSize(layerModif.getPrevLayer().get(0).getOutputImgSize());
					layerModif.setOutputImgSize(layerModif.getPrevLayer().get(0).getOutputImgSize());
					layerModif = layerModif.getNextLayer().get(0);
				}
			}
			
		}else if (layer instanceof Concatenate) {
			Concatenate concat = (Concatenate) layer;
			if(concat.getPrevLayer().get(0).getOutputImgSize() != concat.getPrevLayer().get(1).getOutputImgSize()) {
				Layer layerModif = concat.getPrevLayer().get(1);
				if(concat.getPrevLayer().get(0).getOutputImgSize() > concat.getPrevLayer().get(1).getOutputImgSize()) {
					while(layerModif.getInputImgSize() == layerModif.getOutputImgSize() && 
							layerModif.getNextLayer().size()==1 && 
							layerModif.getPrevLayer().size()==1){
						layerModif = layerModif.getPrevLayer().get(0);
					}
					if(layerModif instanceof domain.Convolution || layerModif instanceof Pooling) {
						int objectifImgSize = concat.getPrevLayer().get(0).getOutputImgSize();
						gestionHPP.optiHPPfromImgObjective(layerModif, objectifImgSize);
					}
				}else {
					while(layerModif.getInputImgSize() == layerModif.getOutputImgSize() && 
							layerModif.getNextLayer().size()==1 && 
							layerModif.getPrevLayer().size()==1){
						layerModif = layerModif.getPrevLayer().get(0);
					}
					if(layerModif instanceof domain.Convolution || layerModif instanceof Pooling) {
						int objectifImgSize = concat.getPrevLayer().get(0).getOutputImgSize();
						gestionHPP.optiHPPfromImgObjective(layerModif, objectifImgSize);
					}
				}
				
				layerModif = layerModif.getNextLayer().get(0);
				while(layerModif.getLayerPos() != concat.getLayerPos()) {
					layerModif.setInputImgSize(layerModif.getPrevLayer().get(0).getOutputImgSize());
					layerModif.setOutputImgSize(layerModif.getPrevLayer().get(0).getOutputImgSize());
					layerModif = layerModif.getNextLayer().get(0);
				}
			}			
		}
	}
	
	
	// add hyperparameters value to architecture 
	public void architectureHpp(GestionHPP gestionHPP) throws Exception {
		// find last layer
		graph.get(graph.size()-2).setLast(true);
		
		// set activation function
		gestionHPP.setStr_fctActivation(gestionHPP.getFctActivation().get(rand.nextInt(gestionHPP.getFctActivation().size())));
		
		
		int indice = 0;
		while (indice<graph.size()) {
			// set hpp for normal layer
			if(graph.get(indice).getNextLayer().size()<2) 
				layerAndHppManagement(graph.get(indice)	,gestionHPP);
			// set hpp for residual connetion
			else if (graph.get(indice).getNextLayer().size() == 2) 
				indice = gestionMerge(graph.get(indice), gestionHPP);
			else 
			// not handle
				throw new Exception("ArchitectureGraphView architectureHpp: nb edges > 2 " + graph.get(indice));
			
			indice++;
		}
		
		System.out.println("graph hpp saved");
		
		correction(gestionHPP);
		
		verificationImage(gestionHPP);
		
		
		//System.out.println("verifications...");

		//verify the img size
		//verifyImgSize(gestionHPP);
		
		System.out.println("verifications filter");
		
		//verify hpp filter
	    verifyFilters();
	    
	    //System.out.println("2");
		
		System.out.println("verification clear");

		
	}
	
	public void correction(GestionHPP gestionHPP) {
		for(Layer layer : graph){
			if((layer.getPrevLayer().size()>1) && (layer.getNextLayer().size()>1)) {
				if(layer instanceof domain.Convolution) {
					domain.Convolution conv =  (domain.Convolution) layer;
					
					// init input filter + img
					conv.setInputFilter(conv.getPrevLayer().get(0).getOutputFilter());
					conv.setInputImgSize(conv.getPrevLayer().get(0).getOutputImgSize());
					
					//set hpp
					conv.setOutputImgSize(GestionHPP.calculCurrentSize(conv.getPadding(), conv.getKernel(), conv.getStride(), conv.getInputImgSize()));
					
			
				}
				else if (layer instanceof Pooling) {
					
					Pooling pool = (Pooling) layer;
					
					pool.setInputFilter(pool.getPrevLayer().get(0).getOutputFilter());
					pool.setInputImgSize(pool.getPrevLayer().get(0).getOutputImgSize());
					
					pool.setOutputImgSize(GestionHPP.calculCurrentSize(pool.getPadding(), pool.getKernel(), pool.getStride(), pool.getInputImgSize()));
					
					pool.setOutputFilter(pool.getInputFilter());
				}
				else {
					layer.setInputFilter(layer.getPrevLayer().get(0).getOutputFilter());
					layer.setInputImgSize(layer.getPrevLayer().get(0).getOutputImgSize());
					
					layer.setOutputFilter(layer.getPrevLayer().get(0).getOutputFilter());
					layer.setOutputImgSize(layer.getPrevLayer().get(0).getOutputImgSize());
				}
			}
		}
	}
	
	// verify filters
	public void verifyFilters() throws Exception {
		boolean verif = true;
		int nb_verif = 0;
		while (verif && nb_verif < MAX_NB_VERIF) {
			verif = false;
			for(Layer cellAdd :graph.stream()
									.filter(c-> c instanceof Add)
									.sorted(Comparator.comparingInt(Layer::getLayerPos))
									.collect(Collectors.toList())) {
				
				
				
				List<Layer> listEdges = cellAdd.getPrevLayer();
					
				if(listEdges.get(0).getOutputFilter() != listEdges.get(1).getOutputFilter()) {
					
					Layer lastLayerLeft = listEdges.get(0);
					Layer lastLayerRight = listEdges.get(1);
					int nbFilterLeft = listEdges.get(0).getOutputFilter();
					int nbFilterRight = listEdges.get(1).getOutputFilter();
					
					while(!(lastLayerRight instanceof domain.Convolution) && !(lastLayerLeft instanceof domain.Convolution) 
							&& !(lastLayerRight instanceof Input && lastLayerLeft instanceof Input)) {
						
						if(!(lastLayerRight instanceof Input)) {
							lastLayerRight = lastLayerRight.getPrevLayer().get(0);
						}
						if(!(lastLayerLeft instanceof Input)) {
							lastLayerLeft = lastLayerLeft.getPrevLayer().get(0);
						}
					}
					
					
					if(lastLayerRight instanceof domain.Convolution) {
						
						lastLayerRight.setOutputFilter(nbFilterLeft);
						// propagation of img change
						while(!(lastLayerRight instanceof Output) && (lastLayerRight.getNextLayer().get(0).getLayerPos() != cellAdd.getLayerPos()) ) {
							lastLayerRight.getNextLayer().get(0).setOutputFilter(lastLayerRight.getOutputFilter());
							lastLayerRight = lastLayerRight.getNextLayer().get(0);
						}
					}else if (lastLayerLeft instanceof domain.Convolution) {
						lastLayerLeft.setOutputFilter(nbFilterRight);
						// propagation of filter change
						while(!(lastLayerLeft instanceof Output) && (lastLayerLeft.getNextLayer().get(0).getLayerPos() != cellAdd.getLayerPos())) {
							lastLayerLeft.getNextLayer().get(0).setOutputFilter(lastLayerLeft.getOutputFilter());
							lastLayerLeft = lastLayerLeft.getNextLayer().get(0);
						}
					}else {
						throw new Exception("GestionHPP verifyFilters: conv not find");
					}					
					
					verif = true;
				}
	
			}
			nb_verif++;
		}
		
	}

	private int gestionMerge(Layer layer, GestionHPP gestionHPP) throws Exception {
		//init
		layerAndHppManagement(layer, gestionHPP);
		
		// manage left and right
		List<Layer> leftRight = layer.getNextLayer().stream().sorted(Comparator.comparingInt(Layer::getLayerPos))
		.collect(Collectors.toList());
		Layer firstLayerLeft = leftRight.get(0);
		Layer firstLayerRight = leftRight.get(1);
		
		
		Layer lastLayerMerge = firstLayerRight;
		// find last layer merge
		while(!(lastLayerMerge instanceof Add) && !(lastLayerMerge instanceof Concatenate)) {
			if(lastLayerMerge.getNextLayer().size()==2)lastLayerMerge=lastLayerMerge.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(1);
			else lastLayerMerge = lastLayerMerge.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(0);
		}
		
		if(firstLayerRight instanceof Add || firstLayerRight instanceof Concatenate) {
			// Empty
			gestionEmptyMerge(firstLayerLeft, lastLayerMerge, gestionHPP);
		}	
		else {
			gestionReductionMerge(layer, lastLayerMerge, gestionHPP);
		}
		
		return lastLayerMerge.getLayerPos();
		
	}
	
	private void gestionReductionMerge(Layer firstLayer, Layer lastlayer, GestionHPP gestionHPP) throws Exception {
		int objectiveImgSize = 0;
		int objectiveFilter = 0;
		
		
		// initialisation order layer + merge layer content
		List<List<Layer>> listMergeRecursive = new ArrayList<List<Layer>>();
		List<Layer> layerLeftReductionPossible = new ArrayList<>();
		
		List<Layer> anchorRight = new ArrayList<>();
		List<Layer> layerRightReductionPossible = new ArrayList<>();
		
		
		// init left
		Layer layerLeft = firstLayer.getNextLayer().stream()
		.sorted(Comparator.comparingInt(Layer::getLayerPos))
		.collect(Collectors.toList()).get(0);
		layerLeft.setReduction(false);
		
		while (layerLeft.getLayerPos() != lastlayer.getLayerPos()) {
			
			if (layerLeft.getNextLayer().size()==2) {
				List<Layer> startEndLayer = new ArrayList<>();
				startEndLayer.add(layerLeft);
				while (!(layerLeft instanceof Add) && !(layerLeft instanceof Concatenate)) {	
					layerLeft = layerLeft.getNextLayer().stream()
							.sorted(Comparator.comparingInt(Layer::getLayerPos))
							.collect(Collectors.toList()).get(0);
				}
				startEndLayer.add(layerLeft);
				listMergeRecursive.add(startEndLayer);
			}
			
			if(layerLeft instanceof domain.Convolution || layerLeft instanceof Pooling) {
				layerLeftReductionPossible.add(layerLeft);
				
			}
			layerLeft = layerLeft.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(0);
			layerLeft.setReduction(false);
		}
		
		//init right
		Layer layerRight = firstLayer.getNextLayer().stream()
				.sorted(Comparator.comparingInt(Layer::getLayerPos))
				.collect(Collectors.toList()).get(1);
		layerRight.setReduction(false);
		
		while (layerRight.getLayerPos() != lastlayer.getLayerPos()) {
			
			if (layerRight.getNextLayer().size()==2) {
				anchorRight.add(layerRight);
				
				if(layerRight instanceof domain.Convolution || layerRight instanceof Pooling)
					layerRightReductionPossible.add(layerRight);
				
				layerRight = layerRight.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(1);
				
			}else {
				if(layerRight instanceof domain.Convolution || layerRight instanceof Pooling) 
					layerRightReductionPossible.add(layerRight);
				
				layerRight = layerRight.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(0);
				
			}
			layerRight.setReduction(false);
			
			
			
		}
		
		
		if (anchorRight.size() > 0) {
			
			//left
			
			List<Layer> anchorLeft = new ArrayList<>();
			
			for (Layer l : anchorRight) {
				anchorLeft.add(l.getNextLayer().get(0));
			}
			anchorLeft.add(lastlayer);
			
			for(Layer y: anchorLeft) {
				List<Layer> subLayerLeftReductionPossible = new ArrayList<>();
				for (Layer l : layerLeftReductionPossible) {
					if(y.getLayerPos()>l.getLayerPos()) {
						subLayerLeftReductionPossible.add(l);
					}
						
				}
				
				for(Layer l: subLayerLeftReductionPossible) {
					layerLeftReductionPossible.remove(l);
				}
				
				if(subLayerLeftReductionPossible.size() > 0) {
					int leftLayerIndice = rand.nextInt(subLayerLeftReductionPossible.size());
					subLayerLeftReductionPossible.get(leftLayerIndice).setReduction(true);
				}else {
					System.out.println("subLayerLeftReductionPossible : " + subLayerLeftReductionPossible);
					System.out.println(y.getLayerPos());
				}
				
			}
			
			
			layerLeft = firstLayer.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(0);
			while (layerLeft.getLayerPos() != lastlayer.getLayerPos()) {
				if(layerLeft.getNextLayer().size() == 2) {
					gestionMerge(layerLeft, gestionHPP);
				}else {
					layerAndHppManagement(layerLeft, gestionHPP);
				}
				
				layerLeft = layerLeft.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(0);
			}
			
			
			//right
			
			anchorRight.add(lastlayer);
			
			for(Layer y: anchorRight) {
				List<Layer> subLayerRightReductionPossible = new ArrayList<>();
				for (Layer l : layerRightReductionPossible) {
					if(y.getLayerPos() >= l.getLayerPos()) {
						subLayerRightReductionPossible.add(l);	
					}
				}
				
				for(Layer l: subLayerRightReductionPossible) {
					layerRightReductionPossible.remove(l);
				}
					
				if (subLayerRightReductionPossible.size() > 0) {
					int rightLayerIndice = rand.nextInt(subLayerRightReductionPossible.size());
					subLayerRightReductionPossible.get(rightLayerIndice).setReduction(true);
				}else {
					System.out.println("layerRightReductionPossible : " + layerRightReductionPossible);
					System.out.println(y.getLayerPos());
				}
				
			}
			
			
			layerRight = firstLayer.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(1);
			
			List<Layer> saveLayer = new ArrayList<>();
			while (layerRight.getLayerPos() != lastlayer.getLayerPos()) {
				
				if (layerRight.getNextLayer().size()==2) {
					saveLayer.add(layerRight);
					
					objectiveImgSize = layerRight.getNextLayer().get(0).getInputImgSize();
					objectiveFilter = layerRight.getNextLayer().get(0).getInputFilter();
					
					for (Layer l : saveLayer) {
						if(l.isReduction()) {
							layerAndHppManagement(l, gestionHPP, objectiveImgSize, objectiveFilter);
						}else {
							layerAndHppManagement(l, gestionHPP);
						}
					}
					saveLayer = new ArrayList<>();
					
					layerRight = layerRight.getNextLayer().stream()
							.sorted(Comparator.comparingInt(Layer::getLayerPos))
							.collect(Collectors.toList()).get(1);
					
				}else {
					saveLayer.add(layerRight);
					layerRight = layerRight.getNextLayer().stream()
							.sorted(Comparator.comparingInt(Layer::getLayerPos))
							.collect(Collectors.toList()).get(0);
				}
			}
			
			objectiveImgSize = lastlayer.getPrevLayer().get(0).getOutputImgSize();
			objectiveFilter = lastlayer.getPrevLayer().get(0).getOutputFilter();
			
			for (Layer l : saveLayer) {
				if(l.isReduction()) {
					layerAndHppManagement(l, gestionHPP, objectiveImgSize, objectiveFilter);
				}else {
					layerAndHppManagement(l, gestionHPP);
				}
			}
			saveLayer = new ArrayList<>();
			
			layerAndHppManagement(lastlayer, gestionHPP);
			
			
			
		}else {
			int leftLayerIndice = rand.nextInt(layerLeftReductionPossible.size());
			layerLeftReductionPossible.get(leftLayerIndice).setReduction(true);
			
			
			
			int rightLayerIndice = rand.nextInt(layerRightReductionPossible.size());
			layerRightReductionPossible.get(rightLayerIndice).setReduction(true);
			
			Layer leftLayer = firstLayer.getNextLayer().get(0);
			
			while(leftLayer.getLayerPos() != lastlayer.getLayerPos()) {
				if(leftLayer.getNextLayer().size()==2) {
					gestionMerge(leftLayer, gestionHPP);
				}else {
					layerAndHppManagement(leftLayer, gestionHPP);
				}
					leftLayer = leftLayer.getNextLayer().get(0);
			}
			
			objectiveImgSize = leftLayer.getPrevLayer().get(0).getOutputImgSize();
			objectiveFilter = leftLayer.getPrevLayer().get(0).getOutputFilter();
			
			Layer rightLayer = firstLayer.getNextLayer().get(1);
			
			while(rightLayer.getLayerPos() != lastlayer.getLayerPos()) {
				if(rightLayer.isReduction() == true) {
					layerAndHppManagement(rightLayer, gestionHPP, objectiveImgSize, objectiveFilter);
				}else {
					layerAndHppManagement(rightLayer, gestionHPP);
				}
				rightLayer = rightLayer.getNextLayer().get(0);
			}
			layerAndHppManagement(lastlayer, gestionHPP);
			
		}
		
		
	}

	
	private void gestionEmptyMerge(Layer firstLayerLeft, Layer last,GestionHPP gestionHPP) throws Exception {
		Layer layerLeft = firstLayerLeft;
		layerLeft.setReduction(false);
		layerAndHppManagement(layerLeft,  gestionHPP);
		while((layerLeft.getNextLayer().size() > 0) && layerLeft != last) {
			if(layerLeft.getNextLayer().size() > 1) {
				for(Layer layer: layerLeft.getNextLayer()) {
					gestionEmptyMerge(layer, last, gestionHPP);
				}
				layerLeft = layerLeft.getNextLayer().get(0);
			}else {
				layerLeft = layerLeft.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(0);
				layerLeft.setReduction(false);
				layerAndHppManagement(layerLeft,gestionHPP);
			}
			
		}	
	}
	
	private void layerAndHppManagement(Layer layer, GestionHPP gestionHPP, int objectiveImgSize, int objectiveFilter) throws Exception {
		if (!layer.isReduction()) throw new Exception("GestionHPPNeo4j optiHPPfromImgObjectif: wrong fonction");
		
		if(layer instanceof domain.Convolution) {
			domain.Convolution conv =  (domain.Convolution) layer;
			
			// init input filter + img
			conv.setInputFilter(conv.getPrevLayer().get(0).getOutputFilter());
			conv.setInputImgSize(conv.getPrevLayer().get(0).getOutputImgSize());
			
			//set hpp
			gestionHPP.gestionConvolution(conv, conv.isReduction(), objectiveImgSize, objectiveFilter);
			
			
		}
		else if (layer instanceof Pooling) {
			
			Pooling pool = (Pooling) layer;
			
			pool.setInputFilter(pool.getPrevLayer().get(0).getOutputFilter());
			pool.setInputImgSize(pool.getPrevLayer().get(0).getOutputImgSize());
			
			
			gestionHPP.gestionPooling(pool, pool.isReduction(), objectiveImgSize);
			
			pool.setOutputFilter(objectiveFilter);
		}
	}
	

	private void layerAndHppManagement(Layer layer, GestionHPP gestionHPP) throws Exception {
		if(layer instanceof domain.Convolution) {
			domain.Convolution conv =  (domain.Convolution) layer;
			
			// init input filter + img
			conv.setInputFilter(conv.getPrevLayer().get(0).getOutputFilter());
			conv.setInputImgSize(conv.getPrevLayer().get(0).getOutputImgSize());
			
			//set hpp
			gestionHPP.gestionConvolution(conv, conv.isReduction());
			
			//set img size
			//conv.setImgSize(GestionHPPNeo4j.calculCurrentSize(conv.getPadding(), conv.getKernel(), conv.getStride(), conv.getPrevLayer().get(0).getImgSize()));
		}
		else if (layer instanceof Pooling) {
			
			Pooling pool = (Pooling) layer;
			
			pool.setInputFilter(pool.getPrevLayer().get(0).getOutputFilter());
			pool.setInputImgSize(pool.getPrevLayer().get(0).getOutputImgSize());
			
			
			gestionHPP.gestionPooling(pool, pool.isReduction());
			
			//pool.setImgSize(GestionHPPNeo4j.calculCurrentSize(pool.getPadding(), pool.getKernel(), pool.getStride(), pool.getPrevLayer().get(0).getImgSize()));
			pool.setOutputFilter(pool.getInputFilter());
		}
		else if (layer instanceof Output) {
			Output output = (Output) layer;
			
			output.setInputFilter(output.getPrevLayer().get(0).getOutputFilter());
			output.setInputImgSize(output.getPrevLayer().get(0).getOutputImgSize());
			
			output.setOutputFilter(output.getInputFilter());
			output.setOutputImgSize(output.getInputImgSize());

		}
		else if (layer instanceof Dense) {
			Dense dense = (Dense) layer;
			
			dense.setInputFilter(dense.getPrevLayer().get(0).getOutputFilter());
			dense.setInputImgSize(dense.getPrevLayer().get(0).getOutputImgSize());
			
			gestionHPP.gestionDense(dense, dense.isLast());
			
			dense.setOutputFilter(dense.getInputFilter());
			dense.setOutputImgSize(dense.getInputImgSize());
			
		}
		else if (layer instanceof Dropout) {
			Dropout drop = (Dropout) layer;
			
			drop.setInputFilter(drop.getPrevLayer().get(0).getOutputFilter());
			drop.setInputImgSize(drop.getPrevLayer().get(0).getOutputImgSize());
			
			gestionHPP.gestionDropout(drop);
			
			drop.setOutputFilter(drop.getInputFilter());
			drop.setOutputImgSize(drop.getInputImgSize());
			
			
		}
		/*else if (layer instanceof TempLayer) {
			TempLayer temp = (TempLayer) layer;
			temp.setImgSize(temp.getPrevLayer().get(0).getImgSize());
			temp.setNbFilter(temp.getPrevLayer().get(0).getNbFilter());

		}*/
		else if (layer instanceof BatchNormalisation) {
			BatchNormalisation bn = (BatchNormalisation) layer;
			
			bn.setInputFilter(bn.getPrevLayer().get(0).getOutputFilter());
			bn.setInputImgSize(bn.getPrevLayer().get(0).getOutputImgSize());
			
			gestionHPP.gestionBN(bn);
			
			bn.setOutputFilter(bn.getInputFilter());
			bn.setOutputImgSize(bn.getInputImgSize());
		}
		else if (layer instanceof domain.Interstice) {
			domain.Interstice inter = (domain.Interstice) layer;
			
			inter.setInputFilter(inter.getPrevLayer().get(0).getOutputFilter());
			inter.setInputImgSize(inter.getPrevLayer().get(0).getOutputImgSize());
			
			inter.setOutputFilter(inter.getInputFilter());
			inter.setOutputImgSize(inter.getInputImgSize());
		}
		else if (layer instanceof Add) {
			Add add = (Add) layer;
			
			add.setInputFilter(add.getPrevLayer().get(0).getOutputFilter());
			add.setInputImgSize(add.getPrevLayer().get(0).getOutputImgSize());
			
			add.setOutputFilter(add.getInputFilter());
			add.setOutputImgSize(add.getInputImgSize());
			
		}
		else if (layer instanceof Concatenate) {
			Concatenate concat = (Concatenate) layer;
			
			concat.setInputFilter(concat.getPrevLayer().get(0).getOutputFilter() + concat.getPrevLayer().get(1).getOutputFilter());
			concat.setInputImgSize(concat.getPrevLayer().get(0).getOutputImgSize());
			
			concat.setOutputFilter(gestionHPP.compressedFilter(concat.getInputFilter()));
			concat.setOutputImgSize(concat.getInputImgSize());
			
			
		}
		else if (layer instanceof Input) {
			Input input = (Input) layer;
			
			gestionHPP.initInputLayer(input);

		}
		else throw new Exception("ArchitectureGraphView updateLayer : forgot Layer ? " + layer.getClass());
		
	}
	
	
	public String createAdjencyMatrix() {
		int numVertices = graph.size();
		boolean[][] adjMatrix = new boolean[numVertices][numVertices];
		
		for (Layer layer: graph) {
			for (int i=0; i<layer.getNextLayer().size(); i++) {
				adjMatrix[layer.getLayerPos()][layer.getNextLayer().get(i).getLayerPos()] = true;
				adjMatrix[layer.getNextLayer().get(i).getLayerPos()][layer.getLayerPos()] = true;
				
			}
		}
		
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < numVertices; i++) {
		      //s.append(graph.get(i).getClass().getSimpleName() + ": ");
		      for (boolean j : adjMatrix[i]) {
		        s.append((j ? 1 : 0) + " ");
		      }
		      s.append("\n");
		    }
		    return s.toString();
			
	}
	
}