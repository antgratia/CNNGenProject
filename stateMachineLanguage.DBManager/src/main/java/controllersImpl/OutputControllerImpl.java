package controllersImpl;

import controllers.IOutputController;
import domain.Output;
import servicesImpl.OutputServiceImpl;

public class OutputControllerImpl implements IOutputController {

	OutputServiceImpl outputService = new OutputServiceImpl();
	
	@Override
	public Iterable<Output> findAll() {
		return outputService.findAll();
	}

	@Override
	public Output find(Long id) {
		return outputService.find(id);
	}

	@Override
	public void delete(Long id) {
		outputService.delete(id);

	}

	@Override
	public Output createOrUpdate(Output output) {
		return outputService.createOrUpdate(output);
	}

	@Override
	public Output findByLayerpos(int layerpos) {
		try {
			return outputService.findByLayerpos(layerpos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
