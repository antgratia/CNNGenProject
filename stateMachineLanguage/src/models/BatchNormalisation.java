package models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.GestionHpp;

@Data
public class BatchNormalisation implements LayerInterface {

	private double epsilon;
	
	@Setter(AccessLevel.PRIVATE)
	@Getter(AccessLevel.PRIVATE)
	private GestionHpp gestionHpp;
	
	public BatchNormalisation() {
		gestionHpp = GestionHpp.getGestionHpp();
	}
	

	@Override
	public LayerInterface getHyperparameters() throws Exception {
		//gestionHpp.gestionBN(this);
		return this;
	}
	
	
	

}
