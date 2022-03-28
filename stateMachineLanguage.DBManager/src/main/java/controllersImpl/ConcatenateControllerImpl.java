package controllersImpl;

import controllers.IConcatenateController;
import domain.Concatenate;
import servicesImpl.ConcatenateServiceImpl;

public class ConcatenateControllerImpl implements IConcatenateController {
	
	ConcatenateServiceImpl concatenateServiceImpl = new ConcatenateServiceImpl();

	@Override
	public Iterable<Concatenate> findAll() {
		return concatenateServiceImpl.findAll();
	}

	@Override
	public Concatenate find(Long id) {
		return concatenateServiceImpl.find(id);
	}

	@Override
	public void delete(Long id) {
		concatenateServiceImpl.delete(id);
		
	}

	@Override
	public Concatenate createOrUpdate(Concatenate concat) {
		return concatenateServiceImpl.createOrUpdate(concat);
	}

}
