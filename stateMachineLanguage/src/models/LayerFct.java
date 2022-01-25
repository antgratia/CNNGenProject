package models;

import lombok.Data;

@Data
public class LayerFct{
	private boolean isRecu;
	private int nbRecu;
	private TypeLayerEnum Type;
	private boolean isReduction;
	
	
	
	public LayerFct(boolean isRecu, int nbRecu, TypeLayerEnum type, boolean isReduction) {
		super();
		this.isRecu = isRecu;
		this.nbRecu = nbRecu;
		Type = type;
		this.isReduction = isReduction;
	}
}
