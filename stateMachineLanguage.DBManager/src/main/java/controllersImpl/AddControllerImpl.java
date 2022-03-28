package controllersImpl;

import controllers.IAddController;
import domain.Add;
import servicesImpl.AddServiceImpl;

public class AddControllerImpl implements IAddController {
	
	AddServiceImpl addServiceImpl = new AddServiceImpl();

	@Override
	public Iterable<Add> findAll() {
		return addServiceImpl.findAll();
	}

	@Override
	public Add find(Long id) {
		return addServiceImpl.find(id);
	}

	@Override
	public void delete(Long id) {
		addServiceImpl.delete(id);
		
	}

	@Override
	public Add createOrUpdate(Add add) {
		return addServiceImpl.createOrUpdate(add);
	}

}
