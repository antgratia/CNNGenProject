package controllersImpl;

import controllers.IBatchNormalisationController;
import domain.BatchNormalisation;
import servicesImpl.BatchNormalisationServiceImpl;

public class BatchNormalisationControllerImpl implements IBatchNormalisationController {
	
	BatchNormalisationServiceImpl batchNormalisationService = new BatchNormalisationServiceImpl();

	@Override
	public Iterable<BatchNormalisation> findAll() {
		return batchNormalisationService.findAll();
	}

	@Override
	public BatchNormalisation find(Long id) {
		return batchNormalisationService.find(id);
	}

	@Override
	public void delete(Long id) {
		batchNormalisationService.delete(id);
		
	}

	@Override
	public BatchNormalisation createOrUpdate(BatchNormalisation bn) {
		return batchNormalisationService.createOrUpdate(bn);
	}

	@Override
	public BatchNormalisation findByLayerpos(int layerpos) {
		try {
			return batchNormalisationService.findByLayerpos(layerpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
