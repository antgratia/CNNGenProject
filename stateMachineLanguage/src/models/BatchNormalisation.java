package models;

import utils.GestionHpp;

public class BatchNormalisation implements LayerInterface {

	private double epsilon;
	private GestionHpp gestionHpp;
	
	public BatchNormalisation() {
		gestionHpp = GestionHpp.getGestionHpp();
	}
	

	@Override
	public LayerInterface getHyperparameters() throws Exception {
		gestionHpp.gestionBN(this);
		return this;
	}


	public double getEpsilon() {
		return epsilon;
	}


	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
	
	
	
	

}
