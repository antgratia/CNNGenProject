package models;

import utils.GestionHpp;

public class Dense implements LayerInterface {

	private int units;
	private String fctActivation;
	
	
	
	@Override
	public LayerInterface getHyperparameters() throws Exception {
		GestionHpp.gestionDense(this);
		return this;
	}



	public int getUnits() {
		return units;
	}



	public void setUnits(int units) {
		this.units = units;
	}



	public String getFctActivation() {
		return fctActivation;
	}



	public void setFctActivation(String fctActivation) {
		this.fctActivation = fctActivation;
	}
	
	

}
