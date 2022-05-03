package services;

import domain.Input;

public interface IInputService {

	Input findByLayerpos(int layerpos) throws Exception;
}
