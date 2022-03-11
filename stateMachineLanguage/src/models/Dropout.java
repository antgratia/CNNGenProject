package models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.GestionHpp;

@Data
public class Dropout implements LayerInterface {

	private double dropoutRate;
	
	@Setter(AccessLevel.PRIVATE)
	@Getter(AccessLevel.PRIVATE)
	private GestionHpp gestionHpp;
	
	public Dropout() {
		gestionHpp = GestionHpp.getGestionHpp();
	}

	@Override
	public LayerInterface getHyperparameters() throws Exception {
		//gestionHpp.gestionDropout(this);
		return this;
	}

	
	

}
