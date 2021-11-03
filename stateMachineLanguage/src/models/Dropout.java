package models;

import utils.GestionHpp;

public class Dropout implements LayerInterface {

	private double dropoutRate;
	private GestionHpp gestionHpp;
	
	public Dropout() {
		gestionHpp = GestionHpp.getGestionHpp();
	}

	@Override
	public LayerInterface getHyperparameters() throws Exception {
		gestionHpp.gestionDropout(this);
		return this;
	}

	public double getDropoutRate() {
		return dropoutRate;
	}

	public void setDropoutRate(double dropoutRate) {
		this.dropoutRate = dropoutRate;
	}
	
	

}
