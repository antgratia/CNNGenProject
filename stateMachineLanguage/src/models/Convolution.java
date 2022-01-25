package models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.GestionHpp;

@Data
public class Convolution implements LayerInterface {

	private int nbFilter;
	private int kernel;
	private int stride;
	private String fct_activation;
	private String padding;
	
	@Setter(AccessLevel.PRIVATE)
	@Getter(AccessLevel.PRIVATE)
	private GestionHpp gestionHpp;
	
	public Convolution() {
		gestionHpp = GestionHpp.getGestionHpp();
		
	}
	
	@Override
	public LayerInterface getHyperparameters() throws Exception {
		gestionHpp.gestionConvolution(this);
		return this;
	}


	
	

}
