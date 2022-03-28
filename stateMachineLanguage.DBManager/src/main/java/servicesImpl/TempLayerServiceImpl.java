package servicesImpl;

import domain.TempLayer;
import services.ITempLayerService;

public class TempLayerServiceImpl extends GenericService<TempLayer> implements ITempLayerService {

	@Override
	Class<TempLayer> getEntityType() {
		// TODO Auto-generated method stub
		return TempLayer.class;
	}

}
