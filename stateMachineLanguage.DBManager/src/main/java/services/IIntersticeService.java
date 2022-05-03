package services;

import domain.Interstice;

public interface IIntersticeService {
	Interstice findByLayerpos(int layerpos) throws Exception;

}
