package servicesImpl;

import domain.BatchNormalisation;
import services.IBatchNormalisationService;

public class BatchNormalisationServiceImpl extends GenericService<BatchNormalisation> implements IBatchNormalisationService {

	@Override
	Class<BatchNormalisation> getEntityType() {
		// TODO Auto-generated method stub
		return BatchNormalisation.class;
	}

}
