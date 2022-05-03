package services;

import domain.Output;

public interface IOutputService {

	Output findByLayerpos(int layerpos) throws Exception;
}
