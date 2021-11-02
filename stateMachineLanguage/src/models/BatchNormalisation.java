package models;

import utils.GestionHpp;

public class BatchNormalisation implements LayerInterface {

	private double epsilon;
	
	
	
	private BatchNormalisation(double epsilon) {
		super();
		this.epsilon = epsilon;
	}

	private BatchNormalisation() {
		super();
	}



	@Override
	public LayerInterface getHyperparameters() throws Exception {
		GestionHpp.gestionBN(this);
		return this;
	}
	
	

}
