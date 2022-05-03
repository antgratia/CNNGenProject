package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import domain.bk.Add;
import domain.bk.BatchNormalisation;
import domain.bk.Concatenate;
import domain.bk.Dense;
import domain.bk.Dropout;
import domain.bk.Input;
import domain.bk.Layer;
import domain.bk.LayerInterface;
import domain.bk.Output;
import domain.bk.Pooling;
import domain.bk.TempLayer;
import lombok.Data;
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
public class ArchitectureGraph {
	
	private Map<LayerInterface, List<LayerInterface>> graph;
	private Map<LayerInterface, List<LayerInterface>> reverseGraph;
	private int layerPos;
	
	private static Random rand = new Random();
	private static List<String> add_or_concat = new ArrayList<String>(List.of("add", "concat"));
	
	public ArchitectureGraph(){
		this.graph = new TreeMap<>();
		this.reverseGraph = new TreeMap<>();
		this.layerPos=0;
	}
	
	public ArchitectureGraph(Map<LayerInterface, List<LayerInterface>> graph) {
		this.graph = graph;
	}
	
	// add new cell
	public void addCell(LayerInterface cell) {
		this.graph.putIfAbsent(cell, new ArrayList<>());
	}

	// remove the cell
	public void removeCell(LayerInterface cell) {
	    this.graph.values().stream().forEach(e -> e.remove(cell));
	    this.graph.remove(cell);
	}
	
	// add edge cell1 -> cell2
	public void addEdge(LayerInterface cell1, LayerInterface cell2) {
	    this.graph.get(cell1).add(cell2);
	}
	
	
	// return last layer of the graph
	public LayerInterface getLastLayer() {
		int size = graph.keySet().size();
		return (LayerInterface) graph.keySet().toArray()[size-1];
		
	}
	
	// remove edge cell1 -> cell2
	public void removeEdge(LayerInterface cell1, LayerInterface cell2) {
	    List<LayerInterface> eV1 = graph.get(cell1);
	    List<LayerInterface> eV2 = graph.get(cell2);
	    if (eV1 != null)
	        eV1.remove(cell2);
	    if (eV2 != null)
	        eV2.remove(cell1);
	}
	
	// return List of edge form cell
	public List<LayerInterface> getEdge(LayerInterface cell) {
	    return graph.get(cell);
	}
	
	public List<LayerInterface> getReverseEdge(LayerInterface cell) {
	    return reverseGraph.get(cell);
	}
	
	// return cell form given id else return null
	public LayerInterface getByID(int id) {
		for(LayerInterface layer: graph.keySet()) {
			if(layer instanceof Layer) {
				if(((Layer) layer).getLayerPos() == id)
					return layer;
			}
			
		}
		
		return null;
	}
	
	private void reverseGraph() {
		for(Entry<LayerInterface, List<LayerInterface>> cell: graph.entrySet()) {
			for(LayerInterface edge:cell.getValue()) {
				if(cell.getKey().getLayerPos() == 0) {
					reverseGraph.putIfAbsent(cell.getKey(), new ArrayList<>());
				}
				
				if(reverseGraph.containsKey(edge)) {
					reverseGraph.get(edge).add(cell.getKey());
				}else {
					reverseGraph.putIfAbsent(edge, new ArrayList<>());
					reverseGraph.get(edge).add(cell.getKey());
				}
			}
		}
	}
	
	public Map<LayerInterface, List<LayerInterface>> inverse() {
		return this.reverseGraph;
	}


	// create graph form Xtext sentense
	public void createGraph(Architecture archi) throws Exception {
		
		// verify input not missing
		if (!archi.getInput().isEmpty()){
			
			Input input = new Input(layerPos);
			this.addCell(input);
			layerPos++;
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
			
			Output ouput = new Output(layerPos);
			// add edge for lastlayer -> output
			this.addEdge(getLastLayer(), ouput);
			
			// add output layer to graph
			this.addCell(ouput);
			

			
		}else{
			throw new Exception("missing output");
		}
		
		// add tempLayer for complexe ways (more than 3)
		addTempLayer();
		
		// compute reverse graph
		reverseGraph();
		
	}
	
	
	// handle feature extraction part
	private void gestionFE(EList<FeatureExtraction> fe) throws Exception {
		for (FeatureExtraction elem: fe){
			if (elem.getConv() != null){
				gestionConv(elem.getConv(), getLastLayer());
				

			}else if (elem.getMerge() != null){
				gestionMerge(elem.getMerge());
				
			}else {
				throw new Exception("gestionFe errors");
			}
			
			if (elem.getDrop() != null) {
				// add prevlayer -> Dropout 
				Dropout drop = new Dropout(layerPos);
				// add edge prevLayer -> dropout
				this.addEdge(getLastLayer(), drop);
				this.addCell(drop);
				layerPos++;
			}
				
			if (elem.getPool() != null) {
				// add prevlayer -> Dropout 
				Pooling pool = new Pooling(layerPos);
				// add edge prevLayer -> dropout
				this.addEdge(getLastLayer(), pool);
				this.addCell(pool);
				layerPos++;
			}
				
		}
		
	}

	
	// handle convolutionnal layer
	private LayerInterface gestionConv(Convolution conv, LayerInterface prevLayer) throws Exception {
		if(conv.getConv() != null) {
			domain.bk.Convolution LCconv = new domain.bk.Convolution(layerPos);
			
			this.addEdge(prevLayer, LCconv);
			this.addCell(LCconv);
			layerPos++;
			return LCconv;
			
		}else if (conv.getBnconv() != null) {
			BatchNormalisation bn = new BatchNormalisation(layerPos);
			
			this.addEdge(prevLayer, bn);
			this.addCell(bn);
			layerPos++;
			
			domain.bk.Convolution LCconv = new domain.bk.Convolution(layerPos);
			
			this.addEdge(getLastLayer(), LCconv);
			this.addCell(LCconv);
			layerPos++;
			return LCconv;
			
		}else if(conv.getConvbn() != null) {
			domain.bk.Convolution LCconv = new domain.bk.Convolution(layerPos);
			
			this.addEdge(prevLayer, LCconv);
			this.addCell(LCconv);
			layerPos++;
			
			BatchNormalisation bn = new BatchNormalisation(layerPos);
			
			this.addEdge(getLastLayer(), bn);
			this.addCell(bn);
			layerPos++;
			return bn;
		}else {
			throw new Exception("ArchitecureGraph, GestionConv, not possible");
		}
	}
	
	// handle merge
	private void gestionMerge(Merge merge) throws Exception {
		//conv [(conv conv, Empty)] conv
		LayerInterface layerPrevMerge = getLastLayer();
		LayerInterface firstLayer = getLastLayer();
		
		if(merge.getMergeBody().size()==1) {
			gestionMergeBody(merge.getMergeBody().get(0), layerPrevMerge);
		}else {
			for (MergeBody mb:merge.getMergeBody()) {
				firstLayer = gestionMergeBody(mb, layerPrevMerge, firstLayer);
				layerPrevMerge = getLastLayer();
			}	
		}
		
			
	}
	
	// handle hightway 
	private LayerInterface gestionMergeBody(MergeBody mb, LayerInterface layerPrevMerge, LayerInterface fl) throws Exception {
		LayerInterface lastLayerLeft = gestionLeft(mb.getLeft(), layerPrevMerge);
		LayerInterface lastLayerRight = gestionRight(mb.getRight(), fl);
		
		String str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		LayerInterface LayerTemp;
		if(str_add_or_concat == "add") LayerTemp = new Add(layerPos);
		else LayerTemp = new Concatenate(layerPos);
	
		addEdge(lastLayerLeft, LayerTemp);
		addEdge(lastLayerRight, LayerTemp);
		
		addCell(LayerTemp);
		layerPos++;
		return lastLayerRight;
		
	}
	
	// handle residual connection
	private void gestionMergeBody(MergeBody mb, LayerInterface layerPrevMerge) throws Exception {
		LayerInterface lastLayerLeft = gestionLeft(mb.getLeft(), layerPrevMerge);
		LayerInterface lastLayerRight = gestionRight(mb.getRight(), layerPrevMerge);
		
		String str_add_or_concat = add_or_concat.get(rand.nextInt(add_or_concat.size()));
		LayerInterface LayerTemp;
		if(str_add_or_concat == "add") LayerTemp = new Add(layerPos);
		else LayerTemp = new Concatenate(layerPos);
		
		
		addEdge(lastLayerLeft, LayerTemp);
		addEdge(lastLayerRight, LayerTemp);
		
		addCell(LayerTemp);
		layerPos++;
		
	}
	
	// handle left way in residual connection
	private LayerInterface gestionLeft(Left left, LayerInterface layerPrevMerge) throws Exception {
		LayerInterface leftPrevLayer = layerPrevMerge;
		if (left.getP() != null) {
			// add prevlayer -> Dropout 
			Pooling pool = new Pooling(layerPos);
			// add edge prevLayer -> dropout
			this.addEdge(leftPrevLayer, pool);
			this.addCell(pool);
			
			leftPrevLayer = pool;
			layerPos++;
		}
		
		if (left.getCom().getConvdrop() != null){
			for (ConvDrop convdrop: left.getCom().getConvdrop()){
				leftPrevLayer = gestionConv(convdrop.getConv(), getLastLayer());
				
				if(convdrop.getDrop() != null) {
					Dropout drop = new Dropout(layerPos);
					// add edge prevLayer -> dropout
					this.addEdge(leftPrevLayer, drop);
					this.addCell(drop);
					
					leftPrevLayer=drop;
					layerPos++;
				}	
			}	
		}
		
		if(left.getCom().getMergeConv() != null) {
			for(MergeConv mc : left.getCom().getMergeConv()){
				
				gestionMerge(mc.getMerge());
				
				if (!mc.getConvdrop().isEmpty()){
					for (ConvDrop convdrop: mc.getConvdrop()){
						leftPrevLayer = gestionConv(convdrop.getConv(), getLastLayer());
						
						if(convdrop.getDrop() != null) {
							Dropout drop = new Dropout(layerPos);
							// add edge prevLayer -> dropout
							this.addEdge(leftPrevLayer, drop);
							this.addCell(drop);
							
							leftPrevLayer=drop;
							layerPos++;
						}
					}
				}
			}
		}
		
		if (left.getPool() != null) {
			// add prevlayer -> Dropout 
			Pooling pool = new Pooling(layerPos);
			// add edge prevLayer -> dropout
			this.addEdge(getLastLayer(), pool);
			this.addCell(pool);
			
			leftPrevLayer = pool;
			layerPos++;
		}
		
		
		return getLastLayer();
		
	}

	// handle right way in Residual connection
	private LayerInterface gestionRight(Right right, LayerInterface layerPrevMerge) throws Exception {
		LayerInterface rightPrevLayer = layerPrevMerge;
		if(!right.getConv().isEmpty()) {
			for (Convolution c: right.getConv()) {
				gestionConv(c, rightPrevLayer);
				rightPrevLayer = getLastLayer();
			}
			
			return getLastLayer();
		}else{
			return layerPrevMerge;
		}
	}

	// handle GAP / flatten layer
	private void gestionInter(Interstice inter) {
		if (inter.getFg().getFlat() != null || inter.getFg().getGp() != null){
			
			domain.bk.Interstice flatten = new domain.bk.Interstice(layerPos);
			
			// add edge prevlayer -> flatten
			this.addEdge(getLastLayer(), flatten);
			
			// add flatten to graph
			this.addCell(flatten);
			layerPos++;
			
			
		}
	}
	
	// handle Dense part
	private void gestionDense(EList<Classification> class_) {
		for(Classification c: class_) {
			
			if (c.getDrop() != null) {
				// add prevlayer -> Dropout 
				Dropout drop = new Dropout(layerPos);
				
				// add edge prevLayer -> dropout
				this.addEdge(getLastLayer(), drop);
				this.addCell(drop);
				layerPos++;
				
			}
			
			Dense dense = new Dense(layerPos);
			// add edge prevLayer -> Dense
			this.addEdge(getLastLayer(), dense);
			// add dense layer to graph
			this.addCell(dense);
			layerPos++;
		}
		
	}
	
	// add temp layer when there is more than 3 edges for 1 layer
	private void addTempLayer() throws Exception {
		Map<LayerInterface, List<LayerInterface>> listMoreThreeEdges = graph.entrySet().stream().filter(c -> c.getValue().size()>2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		for(Entry<LayerInterface, List<LayerInterface>> cell: listMoreThreeEdges.entrySet()) {
			while(cell.getValue().size()>2) {
				layerPos++;
				TempLayer layerTemp = new TempLayer(layerPos);
					
				// add layerTemp into graph
				this.addCell(layerTemp);
				
				// add edge in layerTemp
				this.addEdge(layerTemp, cell.getValue().get(0));
				this.addEdge(layerTemp, cell.getValue().get(1));
				
				// add edge cell -> LayerTemp
				this.addEdge(cell.getKey(), layerTemp);
				
				// remove edge from cell
				removeEdge(cell.getValue().get(0),cell.getKey());
				removeEdge(cell.getValue().get(0),cell.getKey());
					
				// insert the new layerTemp in the right possition
				InsertLayerRightPosition();
			}
				
		}
		
	}
	
	private void InsertLayerRightPosition() throws Exception {
		LayerInterface lastLayer = getLastLayer();
		if(lastLayer instanceof Output) {
			throw new Exception("ArchitectureGraph verifyID: not possible");
		}else {
			int idInsertion = this.getEdge(lastLayer).stream().collect(Collectors.toList()).get(0).getLayerPos();
			List<LayerInterface> listLayers = this.graph.keySet()
					  .stream()
					  .sorted(Comparator.comparingInt(LayerInterface::getLayerPos)
							  .reversed())
					  .collect(Collectors.toList());
			int i =0;
			while(listLayers.get(i).getLayerPos() > idInsertion-1) {
				listLayers.get(i).setLayerPos(listLayers.get(i).getLayerPos()+1);
				i++;
			}
			
			lastLayer.setLayerPos(idInsertion);
		}
		
		reorder();
	}

	private void reorder() {
		Map<LayerInterface, List<LayerInterface>> newGraph = graph.entrySet().stream()
				.sorted((e1,e2) -> Integer.compare(e1.getKey().getLayerPos(), e2.getKey().getLayerPos()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, TreeMap<LayerInterface, List<LayerInterface>>::new));

		for(Entry<LayerInterface, List<LayerInterface>> cell: newGraph.entrySet()) {
			List<LayerInterface> listEdgeSorted = cell.getValue().stream().sorted(Comparator.comparingInt(LayerInterface::getLayerPos)).collect(Collectors.toList());
			cell.getValue().clear();
			for(LayerInterface edge: listEdgeSorted) {
				newGraph.get(cell.getKey()).add(edge);
			}
		}
		
		graph = newGraph;
	}
	
	private void verifyID() {
		List<LayerInterface> listCell = graph.keySet().stream().collect(Collectors.toList());
		for(int i =0; i<listCell.size()-1; i++) {
			if(listCell.get(i).getLayerPos()+1 != listCell.get(i+1).getLayerPos()) {
				listCell.get(i+1).setLayerPos(listCell.get(i).getLayerPos()+1);
			}
		}
	}
	
	public void removeTempLayer() {
		List<LayerInterface> tempLayer = graph.keySet().stream().filter(c -> c instanceof TempLayer).collect(Collectors.toList());
		
		for(LayerInterface currentLayer : tempLayer) {
			LayerInterface prevLayer = getByID(currentLayer.getLayerPos()-1);
			//LayerCell nextLayer = getByID(layer.getID()+1);
			List<LayerInterface> currentLayerEdges = getEdge(currentLayer);
			List<LayerInterface> prevLayerEdges = getEdge(prevLayer);
			
			for(LayerInterface edges: currentLayerEdges) {
				prevLayerEdges.add(edges);
			}
			
			removeEdge(currentLayer, prevLayer);
			removeCell(currentLayer);
			verifyID();
		}
		
		reverseGraph.clear();
		reverseGraph();

	}
}
