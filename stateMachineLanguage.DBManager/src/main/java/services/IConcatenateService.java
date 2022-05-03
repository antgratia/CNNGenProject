package services;

import domain.Concatenate;

public interface IConcatenateService {

	Concatenate findByLayerpos(int layerpos) throws Exception;
}
