package services;

import domain.Add;

public interface IAddService {

	
	Add findByLayerpos(int layerpos) throws Exception;
}
