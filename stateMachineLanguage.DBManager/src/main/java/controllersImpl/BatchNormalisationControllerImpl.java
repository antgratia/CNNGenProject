package controllersImpl;

import controllers.IBatchNormalisationController;
import domain.BatchNormalisation;
import servicesImpl.BatchNormalisationServiceImpl;

public class BatchNormalisationControllerImpl implements IBatchNormalisationController {
	
	BatchNormalisationServiceImpl batchNormalisationServiceImpl = new BatchNormalisationServiceImpl();

	@Override
	public Iterable<BatchNormalisation> findAll() {
		return batchNormalisationServiceImpl.findAll();
	}

	@Override
	public BatchNormalisation find(Long id) {
		return batchNormalisationServiceImpl.find(id);
	}

	@Override
	public void delete(Long id) {
		batchNormalisationServiceImpl.delete(id);
		
	}

	@Override
	public BatchNormalisation createOrUpdate(BatchNormalisation bn) {
		return batchNormalisationServiceImpl.createOrUpdate(bn);
	}


}
