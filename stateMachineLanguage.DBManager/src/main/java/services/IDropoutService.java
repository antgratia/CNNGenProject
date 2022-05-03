package services;

import domain.Dropout;

public interface IDropoutService{

	Dropout findByLayerpos(int layerpos) throws Exception;
}
