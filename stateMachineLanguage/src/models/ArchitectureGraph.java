package models;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;

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
	
	private Map<LayerCell, Set<LayerCell>> graph;
	private Map<LayerCell, Set<LayerCell>> reverseGraph;
	private int layerPos;
	
	
	public ArchitectureGraph(){
		this.graph = new TreeMap<>();
		this.reverseGraph = new TreeMap<>();
		this.layerPos=0;
	}
	
	public ArchitectureGraph(Map<LayerCell, Set<LayerCell>> graph) {
		this.graph = graph;
	}
	
	// add new cell
	public void addCell(LayerCell cell) {
		this.graph.putIfAbsent(cell, new TreeSet<>());
	}

	// remove the cell
	public void removeCell(LayerCell cell) {
	    this.graph.values().stream().forEach(e -> e.remove(cell));
	    this.graph.remove(cell);
	}
	
	// add edge cell1 -> cell2
	public void addEdge(LayerCell cell1, LayerCell cell2) {
	    this.graph.get(cell1).add(cell2);
	}
	
	
	// return last layer of the graph
	public LayerCell getLastLayer() {
		int size = graph.keySet().size();
		return (LayerCell) graph.keySet().toArray()[size-1];
		
	}
	
	// remove edge cell1 -> cell2
	public void removeEdge(LayerCell cell1, LayerCell cell2) {
	    Set<LayerCell> eV1 = graph.get(cell1);
	    Set<LayerCell> eV2 = graph.get(cell2);
	    if (eV1 != null)
	        eV1.remove(cell2);
	    if (eV2 != null)
	        eV2.remove(cell1);
	}
	
	// return List of edge form cell
	public Set<LayerCell> getEdge(LayerCell cell) {
	    return graph.get(cell);
	}
	
	public Set<LayerCell> getReverseEdge(LayerCell cell) {
	    return reverseGraph.get(cell);
	}
	
	// return cell form given id else return null
	public LayerCell getByID(int id) {
		for(LayerCell lc: graph.keySet()) {
			if(lc.getID() == id)
				return lc;
		}
		
		return null;
	}
	
	private void reverseGraph() {
		for(Entry<LayerCell, Set<LayerCell>> cell: graph.entrySet()) {
			for(LayerCell edge:cell.getValue()) {
				if(cell.getKey().getID() == 0) {
					reverseGraph.putIfAbsent(cell.getKey(), new TreeSet<>());
				}
				
				if(reverseGraph.containsKey(edge)) {
					reverseGraph.get(edge).add(cell.getKey());
				}else {
					reverseGraph.putIfAbsent(edge, new TreeSet<>());
					reverseGraph.get(edge).add(cell.getKey());
				}
			}
		}
	}
	
	public Map<LayerCell, Set<LayerCell>> inverse() {
		return this.reverseGraph;
	}


	// create graph form Xtext sentense
	public void createGraph(Architecture archi) throws Exception {
		
		// verify input not missing
		if (!archi.getInput().isEmpty()){
			
			LayerCell input = new LayerCell(layerPos, TypeLayerEnum.IN);
			input.setImgSize(32);
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
			
			LayerCell ouput = new LayerCell(layerPos, TypeLayerEnum.OUT);
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
				LayerCell drop = new LayerCell(layerPos, TypeLayerEnum.DROP);
				// add edge prevLayer -> dropout
				this.addEdge(getLastLayer(), drop);
				this.addCell(drop);
				layerPos++;
			}
				
			if (elem.getPool() != null) {
				// add prevlayer -> Dropout 
				LayerCell pool = new LayerCell(layerPos, TypeLayerEnum.POOL);
				// add edge prevLayer -> dropout
				this.addEdge(getLastLayer(), pool);
				this.addCell(pool);
				layerPos++;
			}
				
		}
		
	}

	
	// handle convolutionnal layer
	private LayerCell gestionConv(Convolution conv, LayerCell prevLayer) throws Exception {
		if(conv.getConv() != null) {
			LayerCell LCconv = new LayerCell(layerPos, TypeLayerEnum.CONV);
			
			this.addEdge(prevLayer, LCconv);
			this.addCell(LCconv);
			layerPos++;
			return LCconv;
			
		}else if (conv.getBnconv() != null) {
			LayerCell bn = new LayerCell(layerPos, TypeLayerEnum.BN);
			
			this.addEdge(prevLayer, bn);
			this.addCell(bn);
			layerPos++;
			
			LayerCell LCconv = new LayerCell(layerPos, TypeLayerEnum.CONV);
			
			this.addEdge(getLastLayer(), LCconv);
			this.addCell(LCconv);
			layerPos++;
			return LCconv;
			
		}else if(conv.getConvbn() != null) {
			LayerCell LCconv = new LayerCell(layerPos, TypeLayerEnum.CONV);
			
			this.addEdge(prevLayer, LCconv);
			this.addCell(LCconv);
			layerPos++;
			
			LayerCell bn = new LayerCell(layerPos, TypeLayerEnum.BN);
			
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
		LayerCell layerPrevMerge = getLastLayer();
		LayerCell firstLayer = getLastLayer();
		
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
	private LayerCell gestionMergeBody(MergeBody mb, LayerCell layerPrevMerge, LayerCell fl) throws Exception {
		LayerCell lastLayerLeft = gestionLeft(mb.getLeft(), layerPrevMerge);
		LayerCell lastLayerRight = gestionRight(mb.getRight(), fl);
		
		LayerCell LayerTemp = new LayerCell(layerPos, TypeLayerEnum.TEMP_ADD_CONCAT);
		addEdge(lastLayerLeft, LayerTemp);
		addEdge(lastLayerRight, LayerTemp);
		
		addCell(LayerTemp);
		layerPos++;
		return lastLayerRight;
		
	}
	
	// handle residual connection
	private void gestionMergeBody(MergeBody mb, LayerCell layerPrevMerge) throws Exception {
		LayerCell lastLayerLeft = gestionLeft(mb.getLeft(), layerPrevMerge);
		LayerCell lastLayerRight = gestionRight(mb.getRight(), layerPrevMerge);
		
		LayerCell LayerTemp = new LayerCell(layerPos, TypeLayerEnum.TEMP_ADD_CONCAT);
		addEdge(lastLayerLeft, LayerTemp);
		addEdge(lastLayerRight, LayerTemp);
		
		addCell(LayerTemp);
		layerPos++;
		
	}
	
	// handle left way in residual connection
	private LayerCell gestionLeft(Left left, LayerCell layerPrevMerge) throws Exception {
		LayerCell leftPrevLayer = layerPrevMerge;
		if (left.getP() != null) {
			// add prevlayer -> Dropout 
			LayerCell pool = new LayerCell(layerPos, TypeLayerEnum.POOL);
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
					LayerCell drop = new LayerCell(layerPos, TypeLayerEnum.DROP);
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
							LayerCell drop = new LayerCell(layerPos, TypeLayerEnum.DROP);
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
			LayerCell pool = new LayerCell(layerPos, TypeLayerEnum.POOL);
			// add edge prevLayer -> dropout
			this.addEdge(getLastLayer(), pool);
			this.addCell(pool);
			
			leftPrevLayer = pool;
			layerPos++;
		}
		
		
		return getLastLayer();
		
	}

	// handle right way in Residual connection
	private LayerCell gestionRight(Right right, LayerCell layerPrevMerge) throws Exception {
		LayerCell rightPrevLayer = layerPrevMerge;
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
		if (inter.getFg().getFlat() != null){
			
			LayerCell flatten = new LayerCell(layerPos, TypeLayerEnum.FLAT);
			
			// add edge prevlayer -> flatten
			this.addEdge(getLastLayer(), flatten);
			
			// add flatten to graph
			this.addCell(flatten);
			layerPos++;
			
			
		}else{
			
			LayerCell gloPool = new LayerCell(layerPos, TypeLayerEnum.GLOPOOL);
			
			// add edge prevlayer -> flatten
			this.addEdge(getLastLayer(), gloPool);
			
			// add glopol to graph
			this.addCell(gloPool);
			layerPos++;
			

		}
	}
	
	// handle Dense part
	private void gestionDense(EList<Classification> class_) {
		for(Classification c: class_) {
			
			if (c.getDrop() != null) {
				// add prevlayer -> Dropout 
				LayerCell drop = new LayerCell(layerPos, TypeLayerEnum.DROP);
				// add edge prevLayer -> dropout
				this.addEdge(getLastLayer(), drop);
				this.addCell(drop);
				layerPos++;
				
			}
			
			LayerCell dense = new LayerCell(layerPos, TypeLayerEnum.DENSE);
			// add edge prevLayer -> Dense
			this.addEdge(getLastLayer(), dense);
			// add dense layer to graph
			this.addCell(dense);
			layerPos++;
		}
		
	}
	
	// add temp layer when there is more than 3 edges for 1 layer
	private void addTempLayer() throws Exception {
		Map<LayerCell, Set<LayerCell>> listMoreThreeEdges = graph.entrySet().stream().filter(c -> c.getValue().size()>2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		for(Entry<LayerCell, Set<LayerCell>> cell: listMoreThreeEdges.entrySet()) {
			while(cell.getValue().size()>2) {
				layerPos++;
				LayerCell layerTemp = new LayerCell(layerPos, TypeLayerEnum.TEMP_LAYER);
					
				// add layerTemp into graph
				this.addCell(layerTemp);
				
				// add edge in layerTemp
				this.addEdge(layerTemp, cell.getValue().stream().collect(Collectors.toList()).get(0));
				this.addEdge(layerTemp, cell.getValue().stream().collect(Collectors.toList()).get(1));
				
				// add edge cell -> LayerTemp
				this.addEdge(cell.getKey(), layerTemp);
				
				// remove edge from cell
				removeEdge(cell.getValue().stream().collect(Collectors.toList()).get(0),cell.getKey());
				removeEdge(cell.getValue().stream().collect(Collectors.toList()).get(0),cell.getKey());
					
				// insert the new layerTemp in the right possition
				InsertLayerRightPosition();
			}
				
		}
		
	}
	
	private void InsertLayerRightPosition() throws Exception {
		LayerCell lastLayer = getLastLayer();
		if(lastLayer.getTypeLayer() == TypeLayerEnum.OUT) {
			throw new Exception("ArchitectureGraph verifyID: not possible");
		}else {
			int idInsertion = this.getEdge(lastLayer).stream().collect(Collectors.toList()).get(0).getID();
			List<LayerCell> listLayers = this.graph.keySet()
					  .stream()
					  .sorted(Comparator.comparingInt(LayerCell::getID)
							  .reversed())
					  .collect(Collectors.toList());
			int i =0;
			while(listLayers.get(i).getID() > idInsertion-1) {
				listLayers.get(i).setID(listLayers.get(i).getID()+1);
				i++;
			}
			
			lastLayer.setID(idInsertion);
		}
		
		reorder();
	}

	private void reorder() {
		Map<LayerCell, Set<LayerCell>> newGraph = graph.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, TreeMap<LayerCell, Set<LayerCell>>::new));

		for(Entry<LayerCell, Set<LayerCell>> cell: newGraph.entrySet()) {
			List<LayerCell> listEdgeSorted = cell.getValue().stream().sorted(Comparator.comparingInt(LayerCell::getID)).collect(Collectors.toList());
			cell.getValue().clear();
			for(LayerCell edge: listEdgeSorted) {
				newGraph.get(cell.getKey()).add(edge);
			}
		}
		
		graph = newGraph;
	}
	
	private void verifyID() {
		List<LayerCell> listCell = graph.keySet().stream().collect(Collectors.toList());
		for(int i =0; i<listCell.size()-1; i++) {
			if(listCell.get(i).getID()+1 != listCell.get(i+1).getID()) {
				listCell.get(i+1).setID(listCell.get(i).getID()+1);
			}
		}
	}
	
	public void removeTempLayer() {
		List<LayerCell> tempLayer = graph.keySet().stream().filter(c -> c.getTypeLayer() == TypeLayerEnum.TEMP_LAYER).collect(Collectors.toList());
		
		for(LayerCell currentLayer : tempLayer) {
			LayerCell prevLayer = getByID(currentLayer.getID()-1);
			//LayerCell nextLayer = getByID(layer.getID()+1);
			Set<LayerCell> currentLayerEdges = getEdge(currentLayer);
			Set<LayerCell> prevLayerEdges = getEdge(prevLayer);
			
			for(LayerCell edges: currentLayerEdges) {
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
