package services;

import domain.TempLayer;

public interface ITempLayerService {
	
	TempLayer findByLayerpos(int layerpos) throws Exception;

}
