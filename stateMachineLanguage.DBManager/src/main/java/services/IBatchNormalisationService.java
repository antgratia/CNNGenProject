package services;

import domain.BatchNormalisation;

public interface IBatchNormalisationService {

	BatchNormalisation findByLayerpos(int layerpos) throws Exception;
}
