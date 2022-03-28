package controllersImpl;

import controllers.IIntersticeController;
import domain.Interstice;
import servicesImpl.IntersticeServiceImpl;

public class IntersticeControllerImpl implements IIntersticeController {
	
	IntersticeServiceImpl intersticeService = new IntersticeServiceImpl();

	@Override
	public Iterable<Interstice> findAll() {
		return intersticeService.findAll();
	}

	@Override
	public Interstice find(Long id) {
		return intersticeService.find(id);
	}

	@Override
	public void delete(Long id) {
		intersticeService.delete(id);
		
	}

	@Override
	public Interstice createOrUpdate(Interstice interstice) {
		return intersticeService.createOrUpdate(interstice);
	}

}
