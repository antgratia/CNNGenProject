package models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.GestionHpp;

@Data
public class Pooling implements LayerInterface {

	private int kernel;
	private int stride;
	private String padding;
	
	@Setter(AccessLevel.PRIVATE)
	@Getter(AccessLevel.PRIVATE)
	private GestionHpp gestionHpp;

	public Pooling() {
		gestionHpp = GestionHpp.getGestionHpp();
	}



	public Pooling(int kernel, int stride, String padding) {
		super();
		this.kernel = kernel;
		this.stride = stride;
		this.padding = padding;
	}



	@Override
	public LayerInterface getHyperparameters() throws Exception {
		//gestionHpp.gestionPooling(this);
		return this;
	}
	

}
