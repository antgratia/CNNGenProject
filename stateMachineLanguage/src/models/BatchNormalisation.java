package models;

import utils.GestionHpp;

public class BatchNormalisation implements LayerInterface {

	private double epsilon;
	

	@Override
	public LayerInterface getHyperparameters() throws Exception {
		GestionHpp.gestionBN(this);
		return this;
	}


	public double getEpsilon() {
		return epsilon;
	}


	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
	
	
	
	

}
