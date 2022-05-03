package controllersImpl;

import controllers.IConcatenateController;
import domain.Concatenate;
import servicesImpl.ConcatenateServiceImpl;

public class ConcatenateControllerImpl implements IConcatenateController {
	
	ConcatenateServiceImpl concatenateService = new ConcatenateServiceImpl();

	@Override
	public Iterable<Concatenate> findAll() {
		return concatenateService.findAll();
	}

	@Override
	public Concatenate find(Long id) {
		return concatenateService.find(id);
	}

	@Override
	public void delete(Long id) {
		concatenateService.delete(id);
		
	}

	@Override
	public Concatenate createOrUpdate(Concatenate concat) {
		return concatenateService.createOrUpdate(concat);
	}

	@Override
	public Concatenate findByLayerpos(int layerpos) {
		try {
			return concatenateService.findByLayerpos(layerpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
