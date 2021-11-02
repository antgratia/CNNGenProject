package models;

import utils.GestionHpp;

public class Dropout implements LayerInterface {

	private double dropoutRate;
	
	public Dropout(int dropoutRate) {
		super();
		this.dropoutRate = dropoutRate;
	}
	
	public Dropout() {
		super();
	}



	@Override
	public LayerInterface getHyperparameters() throws Exception {
		GestionHpp.gestionDropout(this);
		return this;
	}

	public double getDropoutRate() {
		return dropoutRate;
	}

	public void setDropoutRate(double dropoutRate) {
		this.dropoutRate = dropoutRate;
	}
	
	

}
