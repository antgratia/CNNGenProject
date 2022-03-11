package models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.GestionHpp;

@Data
public class Dense implements LayerInterface {

	private int units;
	private String fctActivation;
	private boolean last;
	
	@Setter(AccessLevel.PRIVATE)
	@Getter(AccessLevel.PRIVATE)
	private GestionHpp gestionHpp;
	
	
	
	
	public Dense(boolean last) {
		super();
		this.last = last;
		gestionHpp = GestionHpp.getGestionHpp();
	}



	@Override
	public LayerInterface getHyperparameters() throws Exception {
		//gestionHpp.gestionDense(this);
		return this;
	}
	
	
	
	

}
