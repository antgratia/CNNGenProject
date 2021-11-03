package models;

import utils.GestionHpp;

public class Dense implements LayerInterface {

	private int units;
	private String fctActivation;
	private boolean last;
	private GestionHpp gestionHpp;
	
	
	
	
	public Dense(boolean last) {
		super();
		this.last = last;
		gestionHpp = GestionHpp.getGestionHpp();
	}



	@Override
	public LayerInterface getHyperparameters() throws Exception {
		gestionHpp.gestionDense(this);
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



	public boolean isLast() {
		return last;
	}



	public void setLast(boolean last) {
		this.last = last;
	}
	
	
	
	

}
