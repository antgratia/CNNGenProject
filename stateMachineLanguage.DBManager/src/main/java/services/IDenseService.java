package services;

import domain.Dense;

public interface IDenseService{
	
	Dense findByLayerpos(int layerpos) throws Exception;

}
