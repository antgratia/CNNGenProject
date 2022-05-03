package controllersImpl;

import controllers.IAddController;
import domain.Add;
import servicesImpl.AddServiceImpl;

public class AddControllerImpl implements IAddController {
	
	AddServiceImpl addService = new AddServiceImpl();

	@Override
	public Iterable<Add> findAll() {
		return addService.findAll();
	}

	@Override
	public Add find(Long id) {
		return addService.find(id);
	}

	@Override
	public void delete(Long id) {
		addService.delete(id);
		
	}

	@Override
	public Add createOrUpdate(Add add) {
		return addService.createOrUpdate(add);
	}

	@Override
	public Add findByLayerpos(int layerpos) {
		try {
			return addService.findByLayerpos(layerpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
