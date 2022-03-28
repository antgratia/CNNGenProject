package controllersImpl;

import controllers.IDenseController;
import domain.Dense;
import servicesImpl.DenseServiceImpl;

public class DenseControllerImpl implements IDenseController {

	DenseServiceImpl denseService = new DenseServiceImpl();

	@Override
	public Iterable<Dense> findAll() {
		return denseService.findAll();
	}

	@Override
	public Dense find(Long id) {
		return denseService.find(id);
	}

	@Override
	public void delete(Long id) {
		denseService.delete(id);
		
	}

	@Override
	public Dense createOrUpdate(Dense dense) {
		return denseService.createOrUpdate(dense);
	}


}
