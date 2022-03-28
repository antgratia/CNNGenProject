package controllersImpl;

import controllers.IDropoutController;
import domain.Dropout;
import servicesImpl.DropoutServiceImpl;

public class DropoutControllerImpl implements IDropoutController {

	DropoutServiceImpl dropoutService = new DropoutServiceImpl();

	@Override
	public Iterable<Dropout> findAll() {
		return dropoutService.findAll();
	}

	@Override
	public Dropout find(Long id) {
		return dropoutService.find(id);
	}

	@Override
	public void delete(Long id) {
		dropoutService.delete(id);
		
	}

	@Override
	public Dropout createOrUpdate(Dropout dropout) {
		return dropoutService.createOrUpdate(dropout);
	}

	

}
