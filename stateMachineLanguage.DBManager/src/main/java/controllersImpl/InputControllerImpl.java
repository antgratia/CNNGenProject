package controllersImpl;

import controllers.IInputController;
import domain.Input;
import servicesImpl.InputServiceImpl;

public class InputControllerImpl implements IInputController {
	
	InputServiceImpl inputService = new InputServiceImpl();

	@Override
	public Iterable<Input> findAll() {
		return inputService.findAll();
	}

	@Override
	public Input find(Long id) {
		return inputService.find(id);
	}

	@Override
	public void delete(Long id) {
		inputService.delete(id);

	}

	@Override
	public Input createOrUpdate(Input input) {
		return inputService.createOrUpdate(input);
	}

	@Override
	public Input findByLayerpos(int layerpos) {
		try {
			return inputService.findByLayerpos(layerpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
