package servicesImpl;

import domain.Dense;
import services.IDenseService;

public class DenseServiceImpl extends GenericService<Dense> implements IDenseService {

	@Override
	Class<Dense> getEntityType() {
		// TODO Auto-generated method stub
		return Dense.class;
	}

}
