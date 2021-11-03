package models;

import utils.GestionHpp;

public class Pooling implements LayerInterface {

	private int kernel;
	private int stride;
	private String padding;
	private GestionHpp gestionHpp;

	public Pooling() {
		gestionHpp = GestionHpp.getGestionHpp();
	}


	@Override
	public LayerInterface getHyperparameters() throws Exception {
		gestionHpp.gestionPooling(this);
		return this;
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



	public String getPadding() {
		return padding;
	}



	public void setPadding(String padding) {
		this.padding = padding;
	}
	
	

}
