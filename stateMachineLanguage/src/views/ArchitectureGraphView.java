package views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import com.google.inject.Inject;

import controllers.IAddController;
import controllers.IBatchNormalisationController;
import controllers.IConcatenateController;
import controllers.IConvolutionController;
import controllers.IDBQueryController;
import controllers.IDenseController;
import controllers.IDropoutController;
import controllers.IInputController;
import controllers.IIntersticeController;
import controllers.IOutputController;
import controllers.IPoolingController;
import controllers.ITempLayerController;
import controllersImpl.AddControllerImpl;
import controllersImpl.BatchNormalisationControllerImpl;
import controllersImpl.ConcatenateControllerImpl;
import controllersImpl.ConvolutionControllerImpl;
import controllersImpl.DBQueryControllerImpl;
import controllersImpl.DenseControllerImpl;
import controllersImpl.DropoutControllerImpl;
import controllersImpl.InputControllerImpl;
import controllersImpl.IntersticeControllerImpl;
import controllersImpl.OutputControllerImpl;
import controllersImpl.PoolingControllerImpl;
import controllersImpl.TempLayerControllerImpl;
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
import utils.GestionHPPNeo4j;
import xtext.sML.Architecture;
import xtext.sML.Classification;
import xtext.sML.ConvDrop;
import xtext.sML.Convolution;
import xtext.sML.FeatureExtraction;
import xtext.sML.Interstice;
import xtext.sML.Left;
import xtext.sML.Merge;
import xtext.sML.MergeBody;
import xtext.sML.MergeConv;
import xtext.sML.Right;

@Data
public class ArchitectureGraphView {
	
	//Controllers
	private IConvolutionController convolutionController;
	private IInputController inputController ;
	private IOutputController outputController ;
	private IDBQueryController dbQueryController ;
	private IDropoutController dropoutController;
	private IDenseController denseController;
	private IIntersticeController intersticeController;
	private IBatchNormalisationController batchNormalisationController;
	private IPoolingController poolingController;
	private IConcatenateController concatenateController;
	private IAddController addController;
	private ITempLayerController tempLayerController;

	
	private final static int MAX_NB_VERIF = 10;
	
	private List<Layer> graph;
	private int layerPos;
	private static Random rand = new Random();
	private static List<String> add_or_concat = new ArrayList<String>(List.of("add", "concat"));
	
	private Layer lastLayer; 
	private String str_add_or_concat;
	
	
	// constructor
	public ArchitectureGraphView(){
		this.graph = new ArrayList<>();
		this.layerPos = 0;
	}
	
	// init the DB and all the controller
	public void init(String dbName) {
		dbQueryController = new DBQueryControllerImpl();
		
		dbQueryController.createNewDB(dbName);
		dbQueryController.clearDB();
		
		convolutionController = new ConvolutionControllerImpl();
		inputController = new InputControllerImpl();
		outputController = new OutputControllerImpl();
		dropoutController = new DropoutControllerImpl();
		denseController = new DenseControllerImpl();
		intersticeController  = new IntersticeControllerImpl();
		batchNormalisationController = new BatchNormalisationControllerImpl();
		poolingController  = new PoolingControllerImpl();
		concatenateController = new ConcatenateControllerImpl();
		addController = new AddControllerImpl();
		tempLayerController = new TempLayerControllerImpl();
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
			outputController.createOrUpdate(output);
			
			lastLayer.getNextLayer().add(output);
			
			
		}else{
			throw new Exception("missing output");
		}
		
		// add tempLayer for complexe ways (more than 3)
		//addTempLayer();
		
		PersiteGraphDB();
		
		System.out.println("structure graph saved");
		
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
			
			domain.Interstice inter = new domain.Interstice(layerPos);
			
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
	
	
	// Persist the layer in the DB
	private void createOrUpdateLayer(Layer layer) throws Exception {
		if(layer instanceof domain.Convolution) convolutionController.createOrUpdate((domain.Convolution) layer);
		else if (layer instanceof Pooling) poolingController.createOrUpdate((Pooling) layer);
		else if (layer instanceof Output) outputController.createOrUpdate((Output) layer);
		else if (layer instanceof Dense) denseController.createOrUpdate((Dense) layer);
		else if (layer instanceof Dropout) dropoutController.createOrUpdate((Dropout) layer);
		else if (layer instanceof TempLayer) tempLayerController.createOrUpdate((TempLayer) layer);
		else if (layer instanceof BatchNormalisation) batchNormalisationController.createOrUpdate((BatchNormalisation) layer);
		else if (layer instanceof domain.Interstice) intersticeController.createOrUpdate((domain.Interstice) layer);
		else if (layer instanceof Add) addController.createOrUpdate((Add) layer);
		else if (layer instanceof Concatenate) concatenateController.createOrUpdate((Concatenate) layer);
		else if (layer instanceof Input) inputController.createOrUpdate((Input) layer);
		else throw new Exception("ArchitectureGraphView updateLayer : forgot Layer ? " + layer.getClass());
	}
	
	// persist the graph in the DB
	public void PersiteGraphDB() throws Exception {
		for (Layer layer: graph) {
			createOrUpdateLayer(layer);
		}
	}
	
	
	// add hyperparameters value to architecture 
	public void architectureHpp(GestionHPPNeo4j gestionHPPNeo4j) throws Exception {
		// find last layer
		graph.get(graph.size()-2).setLast(true);
		
		// set activation function
		gestionHPPNeo4j.setStr_fctActivation(gestionHPPNeo4j.getFctActivation().get(rand.nextInt(gestionHPPNeo4j.getFctActivation().size())));
		
		
		int indice = 0;
		while (indice<graph.size()) {
			// set hpp for normal layer
			if(graph.get(indice).getNextLayer().size()<2) 
				layerAndHppManagement(graph.get(indice)	,gestionHPPNeo4j);
			// set hpp for residual connetion
			else if (graph.get(indice).getNextLayer().size() == 2) 
				gestionMerge(graph.get(indice), gestionHPPNeo4j, indice);
			else 
			// not handle
				throw new Exception("ArchitectureGraphView architectureHpp: nb edges > 2 " + graph.get(indice));
			
			indice++;
		}
		
		System.out.println("graph hpp saved");
		
		// save in DB
		PersiteGraphDB();
		
		System.out.println("verifications...");

		// verify the img size
		verifyImgSize(gestionHPPNeo4j);
		
		System.out.println("1");
		
		// verify hpp filter
	    verifyFilters();
	    
	    System.out.println("2");
	    // save change in DB
		PersiteGraphDB();
		
		System.out.println("verification clear");

		
	}
	
	// verify the img size
	public void verifyImgSize(GestionHPPNeo4j gestionHPPNeo4j) throws Exception {
		boolean verify = true;
		int nb_verif = 0;
		while(verify && nb_verif < MAX_NB_VERIF) {
			verify = false;
			for(Layer cellAddOrConcat :graph.stream()
													.filter(c-> c instanceof Add || c instanceof Concatenate)
													.sorted(Comparator.comparingInt(Layer::getLayerPos))
													.collect(Collectors.toList())) {
				
				List<Layer> listEdges = cellAddOrConcat.getPrevLayer();
				
				if(listEdges.get(0).getImgSize() < listEdges.get(1).getImgSize()) {
					// right way don't reduce enough 
					Layer findConv = listEdges.get(1);
					while(!(findConv instanceof domain.Convolution) && !(findConv instanceof Pooling)) {
						if(findConv instanceof Input) throw new Exception("ArchitectureGraphView verifyImgSize: can't find conv or pool");
						findConv = findConv.getPrevLayer().get(0);
					}
					
					if(findConv instanceof domain.Convolution) {
						int startImgSize = findConv.getPrevLayer().get(0).getImgSize();
						gestionHPPNeo4j.recomputeSize(findConv, startImgSize, listEdges.get(0).getImgSize());
						
					}else if (findConv instanceof Pooling){
						int startImgSize = findConv.getPrevLayer().get(0).getImgSize();
						gestionHPPNeo4j.recomputeSize(findConv, startImgSize, listEdges.get(0).getImgSize());
						
					}else throw new Exception("ArchitectureGraphView verifyImgSize: not possible ");
					
					// propagation of img change
					while( !(findConv instanceof Output) && (findConv.getNextLayer().get(0).getId() != cellAddOrConcat.getId())) {
						findConv.getNextLayer().get(0).setImgSize(findConv.getImgSize());
						findConv = findConv.getNextLayer().get(0);
					}
					
					verify = true;
				}else if (listEdges.get(0).getImgSize() > listEdges.get(1).getImgSize()){
					// right way reduce too much
					Layer findConv = listEdges.get(0);
					while(!(findConv instanceof domain.Convolution) && !(findConv instanceof Pooling)) {
						if(findConv instanceof Input) throw new Exception("GestionHPP verifyImgSize : can't find conv or pool");
						findConv = findConv.getPrevLayer().get(0);
					}
					
					int startImgSize = findConv.getPrevLayer().get(0).getImgSize();
					gestionHPPNeo4j.recomputeSize(findConv, startImgSize, listEdges.get(1).getImgSize());
					
					// propagation of img change
					while(!(findConv instanceof Output) && (findConv.getNextLayer().get(0).getId() != cellAddOrConcat.getId())) {
						findConv.getNextLayer().get(0).setImgSize(findConv.getImgSize());
						findConv = findConv.getNextLayer().get(0);
					}
					
					verify = true;
				}else {
					
				}
			}
			nb_verif++;
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
					
				if(listEdges.get(0).getNbFilter() != listEdges.get(1).getNbFilter()) {
					
					Layer lastLayerLeft = listEdges.get(0);
					Layer lastLayerRight = listEdges.get(1);
					int nbFilterLeft = listEdges.get(0).getNbFilter();
					int nbFilterRight = listEdges.get(1).getNbFilter();
					
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
						
						lastLayerRight.setNbFilter(nbFilterLeft);
						// propagation of img change
						while(!(lastLayerRight instanceof Output) && (lastLayerRight.getNextLayer().get(0).getId() != cellAdd.getId()) ) {
							lastLayerRight.getNextLayer().get(0).setNbFilter(lastLayerRight.getNbFilter());
							lastLayerRight = lastLayerRight.getNextLayer().get(0);
						}
					}else if (lastLayerLeft instanceof domain.Convolution) {
						lastLayerLeft.setNbFilter(nbFilterRight);
						// propagation of filter change
						while(!(lastLayerLeft instanceof Output) && (lastLayerLeft.getNextLayer().get(0).getId() != cellAdd.getId())) {
							lastLayerLeft.getNextLayer().get(0).setNbFilter(lastLayerLeft.getNbFilter());
							lastLayerLeft = lastLayerLeft.getNextLayer().get(0);
						}
					}else {
						throw new Exception("GestionHPP verifyFilters: conv not find");
					}					
					
					verif = true;
				}
	
			}
			System.out.println(nb_verif);
			nb_verif++;
		}
		
	}

	private void gestionMerge(Layer layer, GestionHPPNeo4j gestionHPPNeo4j, int indice) throws Exception {
		//init
		layerAndHppManagement(layer, gestionHPPNeo4j);
		
		// manage left and right
		List<Layer> leftRight = layer.getNextLayer().stream().sorted(Comparator.comparingInt(Layer::getLayerPos))
		.collect(Collectors.toList());
		Layer firstLayerLeft = leftRight.get(0);
		Layer firstLayerRight = leftRight.get(1);
		
		if(firstLayerRight instanceof Add || firstLayerRight instanceof Concatenate) {
			// Empty
			gestionEmptyMerge(firstLayerLeft, firstLayerRight, gestionHPPNeo4j);
			if(indice < firstLayerRight.getLayerPos())
				indice = firstLayerRight.getLayerPos();
		}	
		else {
			gestionReductionMerge(firstLayerLeft, firstLayerRight, gestionHPPNeo4j, indice);
		}
		
	}

	private void gestionReductionMerge(Layer firstLayerLeft, Layer firstLayerRight, GestionHPPNeo4j gestionHPPNeo4j, int indice) throws Exception {
		lastLayer = firstLayerRight;
		if(lastLayer.getNextLayer().size()==2) lastLayer=lastLayer.getNextLayer().stream()
				.sorted(Comparator.comparingInt(Layer::getLayerPos))
				.collect(Collectors.toList()).get(1);
		else lastLayer = lastLayer.getNextLayer().stream()
				.sorted(Comparator.comparingInt(Layer::getLayerPos))
				.collect(Collectors.toList()).get(0);
		while(!(lastLayer instanceof Add) && !(lastLayer instanceof Concatenate)) {
			if(lastLayer.getNextLayer().size()==2)lastLayer=lastLayer.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(1);
			else lastLayer = lastLayer.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(0);
		}
		
		// creation leftway
		List<Layer> leftWay = new ArrayList<>();
		Layer layerLeft = firstLayerLeft;
		while(layerLeft != lastLayer) {
			if(layerLeft instanceof Output) throw new Exception("ArchitectureGraphView gestionReductionMerge: too far");
			leftWay.add(layerLeft);
			layerLeft = layerLeft.getNextLayer().stream()
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList()).get(0);
		}
		
		// list that contain all layer in RightWay
		List<Layer> rightWay = new ArrayList<>();

		Layer layerRight = firstLayerRight;
		while(layerRight != lastLayer) {
			rightWay.add(layerRight);
			if(layerRight.getNextLayer().size() == 2) {
				layerRight = layerRight.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(1);
			}else {
				layerRight = layerRight.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(0);	
			}
		}
		
		//find if other merge exist in leftway
		List<Layer> listOfMerge = leftWay.stream()
				.filter(c -> c.getNextLayer().size()>1)
				.sorted(Comparator.comparingInt(Layer::getLayerPos))
				.collect(Collectors.toList());
		
		for( Layer merge: listOfMerge) {			
			gestionMerge(merge, gestionHPPNeo4j, indice);
		}
		
		List<Layer> leftWayEmptyLayer = leftWay.stream()
				.filter(c -> c.getNbFilter() == 0)
				.collect(Collectors.toList());
		
		if(leftWayEmptyLayer.stream()
				.filter(c -> (c instanceof domain.Convolution || c instanceof Pooling))
				.collect(Collectors.toList()).isEmpty() || 
		   rightWay.stream()
				.filter(c -> (c instanceof domain.Convolution || c instanceof Pooling))
				.collect(Collectors.toList()).isEmpty()) {
			// no reduction
			
			//handle leftway
			for(int i=0; i<leftWay.size();i++)
				if(leftWay.get(i).getNbFilter() == 0) {
					Layer prevlayer = leftWay.get(i).getPrevLayer().get(0);
					leftWay.get(i).setReduction(false);
					layerAndHppManagement(leftWay.get(i), gestionHPPNeo4j);
					leftWay.get(i).setImgSize(prevlayer.getImgSize());
					leftWay.get(i).setNbFilter(prevlayer.getNbFilter());
			}
			
			//handle rightway
			for(int i=0; i<rightWay.size();i++)
				if(rightWay.get(i).getNbFilter() == 0) {
					Layer prevlayer = rightWay.get(i).getPrevLayer().get(0);
					rightWay.get(i).setReduction(false);
					layerAndHppManagement(rightWay.get(i), gestionHPPNeo4j);
					rightWay.get(i).setImgSize(prevlayer.getImgSize());
					rightWay.get(i).setNbFilter(prevlayer.getNbFilter());
					
			}
		}else {
			// reduction
			
			//handle leftway
			List<Layer> leftWayEmptyLayerConvOrPool = leftWayEmptyLayer.stream()
					.filter(c -> (c instanceof domain.Convolution || c instanceof Pooling))
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList());
			int redu = rand.nextInt(leftWayEmptyLayerConvOrPool.size());
			for(int i = 0; i<leftWay.size();i++) {
				if(leftWay.get(i).getLayerPos() == leftWayEmptyLayerConvOrPool.get(redu).getLayerPos()) {
					layerAndHppManagement(leftWay.get(i), gestionHPPNeo4j);
				}else { 
					if(leftWay.get(i).getNbFilter() == 0) {
						Layer prevlayer = leftWay.get(i).getPrevLayer().get(0);
						leftWay.get(i).setReduction(false);
						layerAndHppManagement(leftWay.get(i), gestionHPPNeo4j);
						leftWay.get(i).setImgSize(prevlayer.getImgSize());
						leftWay.get(i).setNbFilter(prevlayer.getNbFilter());

					}
				}
			}
				
			Layer layerRedu = leftWayEmptyLayerConvOrPool.get(redu);
			
			//hangle rightWay
			List<Layer> rightWayEmptyLayerConvOrPool = rightWay.stream()
					.filter(c -> (c instanceof domain.Convolution || c instanceof Pooling))
					.sorted(Comparator.comparingInt(Layer::getLayerPos))
					.collect(Collectors.toList());
			
			redu = rand.nextInt(rightWayEmptyLayerConvOrPool.size());
			for(int i = 0; i<rightWay.size();i++) {
				if(rightWay.get(i).getLayerPos() == rightWayEmptyLayerConvOrPool.get(redu).getLayerPos()) {
					if((layerRedu instanceof domain.Convolution &&  rightWay.get(i) instanceof domain.Convolution) 
							|| layerRedu instanceof Pooling &&  rightWay.get(i) instanceof Pooling) {
						if(layerRedu instanceof domain.Convolution) {
							domain.Convolution conv = (domain.Convolution) layerRedu;
							((domain.Convolution) rightWay.get(i)).setFctActivation(conv.getFctActivation());
							((domain.Convolution) rightWay.get(i)).setKernel(conv.getKernel());
							((domain.Convolution) rightWay.get(i)).setPadding(conv.getPadding());
							((domain.Convolution) rightWay.get(i)).setStride(conv.getStride());
							rightWay.get(i).setImgSize(GestionHPPNeo4j.calculCurrentSize(conv.getPadding(),conv.getKernel(), conv.getStride(), firstLayerLeft.getPrevLayer().get(0).getImgSize()));
							rightWay.get(i).setNbFilter(layerRedu.getNbFilter());
						}else {
							Pooling poolRedu = (Pooling) layerRedu;
							((Pooling)rightWay.get(i)).setPadding(poolRedu.getPadding());
							((Pooling)rightWay.get(i)).setKernel(poolRedu.getKernel());
							((Pooling)rightWay.get(i)).setStride(poolRedu.getStride());
							rightWay.get(i).setImgSize(GestionHPPNeo4j.calculCurrentSize(poolRedu.getPadding(),poolRedu.getKernel(), poolRedu.getStride(), firstLayerLeft.getPrevLayer().get(0).getImgSize()));
							rightWay.get(i).setNbFilter(layerRedu.getNbFilter());
						}

					}else if(layerRedu instanceof domain.Convolution){
						domain.Convolution convRedu = (domain.Convolution) layerRedu;
						if(rightWay.get(i) instanceof Pooling) {
							Pooling pool = new Pooling();
							pool.setKernel(convRedu.getKernel());
							pool.setPadding(convRedu.getPadding());
							pool.setStride(convRedu.getStride());
							
							rightWay.get(i).setImgSize(GestionHPPNeo4j.calculCurrentSize(pool.getPadding(),pool.getKernel(), pool.getStride(), firstLayerLeft.getPrevLayer().get(0).getImgSize()));
							rightWay.get(i).setNbFilter(layerRedu.getNbFilter());
						}else {
							throw new Exception("GestionHPP gestionMerge: rightway not pool");
						}
					}else if(layerRedu instanceof Pooling) {
						Pooling poolRedu = (Pooling) layerRedu;
						if(rightWay.get(i) instanceof domain.Convolution) {
							domain.Convolution conv = new domain.Convolution();
							conv.setKernel(poolRedu.getKernel());
							conv.setPadding(poolRedu.getPadding());
							conv.setStride(poolRedu.getStride());
							conv.setFctActivation(gestionHPPNeo4j.getStr_fctActivation());
							conv.setNbFilter(layerRedu.getNbFilter());

							rightWay.get(i).setImgSize(GestionHPPNeo4j.calculCurrentSize(conv.getPadding(),conv.getKernel(), conv.getStride(), firstLayerLeft.getPrevLayer().get(0).getImgSize()));
							rightWay.get(i).setNbFilter(layerRedu.getNbFilter());
						}else {
							throw new Exception("GestionHPP gestionMerge: rightway not conv");
						}
						
					}else {
						throw new Exception("GestionHPP gestionMerge: layerRedu not conv or pool");
					}

				}else {
					if(rightWay.get(i).getNbFilter() == 0) {
						Layer prevlayer = rightWay.get(i).getPrevLayer().get(0);
						rightWay.get(i).setReduction(false);
						layerAndHppManagement(rightWay.get(i), gestionHPPNeo4j);
						rightWay.get(i).setImgSize(prevlayer.getImgSize());
						rightWay.get(i).setNbFilter(prevlayer.getNbFilter());

					}
				}
				
			}
		}
			
		if(indice < lastLayer.getLayerPos())
			indice = lastLayer.getLayerPos();
	}
	
	private void gestionEmptyMerge(Layer firstLayerLeft, Layer last,GestionHPPNeo4j gestionHPPNeo4j) throws Exception {
		Layer layerLeft = firstLayerLeft;
		layerLeft.setReduction(false);
		layerAndHppManagement(layerLeft,  gestionHPPNeo4j);
		while((layerLeft.getNextLayer().size() > 0) && layerLeft != last) {
			if(layerLeft.getNextLayer().size() > 1) {
				for(Layer layer: layerLeft.getNextLayer()) {
					gestionEmptyMerge(layer, last, gestionHPPNeo4j);
				}
				layerLeft = layerLeft.getNextLayer().get(0);
			}else {
				layerLeft = layerLeft.getNextLayer().stream()
						.sorted(Comparator.comparingInt(Layer::getLayerPos))
						.collect(Collectors.toList()).get(0);
				layerLeft.setReduction(false);
				layerAndHppManagement(layerLeft,gestionHPPNeo4j);
			}
			
		}	
	}
	

	private void layerAndHppManagement(Layer layer, GestionHPPNeo4j gestionHPPNeo4j) throws Exception {
		if(layer instanceof domain.Convolution) {
			domain.Convolution conv =  (domain.Convolution) layer;
			
			//set hpp
			gestionHPPNeo4j.gestionConvolution(conv, conv.isReduction());
			
			//set img size
			conv.setImgSize(GestionHPPNeo4j.calculCurrentSize(conv.getPadding(), conv.getKernel(), conv.getStride(), conv.getPrevLayer().get(0).getImgSize()));
		}
		else if (layer instanceof Pooling) {
			
			Pooling pool = (Pooling) layer;
			
			gestionHPPNeo4j.gestionPooling(pool, pool.isReduction());
			pool.setImgSize(GestionHPPNeo4j.calculCurrentSize(pool.getPadding(), pool.getKernel(), pool.getStride(), pool.getPrevLayer().get(0).getImgSize()));
			pool.setNbFilter(pool.getPrevLayer().get(0).getNbFilter());
		}
		else if (layer instanceof Output) {
			Output output = (Output) layer;
			
			output.setImgSize(output.getPrevLayer().get(0).getImgSize());
			output.setNbFilter(output.getPrevLayer().get(0).getNbFilter());

		}
		else if (layer instanceof Dense) {
			Dense dense = (Dense) layer;
			gestionHPPNeo4j.gestionDense(dense, dense.isLast());
			dense.setImgSize(dense.getPrevLayer().get(0).getImgSize());
			dense.setNbFilter(dense.getPrevLayer().get(0).getNbFilter());
			
		}
		else if (layer instanceof Dropout) {
			Dropout drop = (Dropout) layer;
			
			gestionHPPNeo4j.gestionDropout(drop);
			drop.setImgSize(drop.getPrevLayer().get(0).getImgSize());
			drop.setNbFilter(drop.getPrevLayer().get(0).getNbFilter());
			
		}
		else if (layer instanceof TempLayer) {
			TempLayer temp = (TempLayer) layer;
			temp.setImgSize(temp.getPrevLayer().get(0).getImgSize());
			temp.setNbFilter(temp.getPrevLayer().get(0).getNbFilter());

		}
		else if (layer instanceof BatchNormalisation) {
			BatchNormalisation bn = (BatchNormalisation) layer;
			
			gestionHPPNeo4j.gestionBN(bn);
			bn.setImgSize(bn.getPrevLayer().get(0).getImgSize());
			bn.setNbFilter(bn.getPrevLayer().get(0).getNbFilter());
		}
		else if (layer instanceof domain.Interstice) {
			domain.Interstice inter = (domain.Interstice) layer;
			
			inter.setImgSize(inter.getPrevLayer().get(0).getImgSize());
			inter.setNbFilter(inter.getPrevLayer().get(0).getNbFilter());
		}
		else if (layer instanceof Add) {
			Add add = (Add) layer;
			
			if(add.getPrevLayer().get(0).getImgSize() >= add.getPrevLayer().get(1).getImgSize()) {
				add.setImgSize(add.getPrevLayer().get(1).getImgSize());
				add.setNbFilter(add.getPrevLayer().get(1).getNbFilter());
			}else {
				add.setImgSize(add.getPrevLayer().get(0).getImgSize());
				add.setNbFilter(add.getPrevLayer().get(0).getNbFilter());
			}
			
		}
		else if (layer instanceof Concatenate) {
			Concatenate concat = (Concatenate) layer;
			
			if(concat.getPrevLayer().get(0).getImgSize() >= concat.getPrevLayer().get(1).getImgSize()) {
				concat.setImgSize(concat.getPrevLayer().get(1).getImgSize());
				concat.setNbFilter(gestionHPPNeo4j.compressedFilter(concat.getPrevLayer().get(0).getNbFilter() + concat.getPrevLayer().get(1).getNbFilter()));
			}else {
				concat.setImgSize(concat.getPrevLayer().get(0).getImgSize());
				concat.setNbFilter(gestionHPPNeo4j.compressedFilter(concat.getPrevLayer().get(0).getNbFilter() + concat.getPrevLayer().get(1).getNbFilter()));
			}
			
		}
		else if (layer instanceof Input) {
			Input input = (Input) layer;
			
			input.setImgSize(gestionHPPNeo4j.getCurrentSizeImg());
			input.setNbFilter(gestionHPPNeo4j.getInputFilter());

		}
		else throw new Exception("ArchitectureGraphView updateLayer : forgot Layer ? " + layer.getClass());
		
	}
	
}