package services;

import domain.Pooling;

public interface IPoolingService {

	Pooling findByLayerpos(int layerpos) throws Exception;
}
