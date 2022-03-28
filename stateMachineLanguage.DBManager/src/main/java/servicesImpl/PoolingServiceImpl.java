package servicesImpl;

import domain.Pooling;
import services.IPoolingService;

public class PoolingServiceImpl extends GenericService<Pooling> implements IPoolingService {

	@Override
	Class<Pooling> getEntityType() {
		// TODO Auto-generated method stub
		return Pooling.class;
	}

}
