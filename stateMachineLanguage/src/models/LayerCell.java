package models;

import lombok.Data;

@Data
public class LayerCell implements Comparable<LayerCell>{
	
	private int ID;
	private TypeLayerEnum typeLayer;
	private boolean reduction;
	private boolean last;
	private LayerInterface layer;
	private int imgSize;
	private int nbFilter;
	
	
	public LayerCell(int id, TypeLayerEnum tl) {
		this.ID = id;
		this.typeLayer = tl;
		this.reduction = true;
		this.last = false;
	}


	@Override
	public int compareTo(LayerCell o) {
		if(this.ID == o.ID) return 0;
		if(this.ID > o.ID) return 1;
		return -1;
	}
	
	
	
}
