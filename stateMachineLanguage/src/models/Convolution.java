package models;

import utils.GestionHpp;

public class Convolution implements LayerInterface {

	private int nbFilter;
	private int kernel;
	private int stride;
	private String fct_activation;
	private String padding;
	
	
	
	
	@Override
	public LayerInterface getHyperparameters() throws Exception {
		GestionHpp.gestionConvolution(this);
		return this;
	}



	public int getNbFilter() {
		return nbFilter;
	}



	public void setNbFilter(int nbFilter) {
		this.nbFilter = nbFilter;
	}



	public int getKernel() {
		return kernel;
	}



	public void setKernel(int kernel) {
		this.kernel = kernel;
	}



	public int getStride() {
		return stride;
	}



	public void setStride(int stride) {
		this.stride = stride;
	}



	public String getFct_activation() {
		return fct_activation;
	}



	public void setFct_activation(String fct_activation) {
		this.fct_activation = fct_activation;
	}



	public String getPadding() {
		return padding;
	}



	public void setPadding(String padding) {
		this.padding = padding;
	}
	
	

}
