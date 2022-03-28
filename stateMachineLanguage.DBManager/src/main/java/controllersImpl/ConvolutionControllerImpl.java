package controllersImpl;



import controllers.IConvolutionController;
import domain.Convolution;
import servicesImpl.ConvolutionServiceImpl;

public class ConvolutionControllerImpl implements IConvolutionController{
	
	ConvolutionServiceImpl convService = new ConvolutionServiceImpl();

	@Override
	public Iterable<Convolution> findAll() {
		return convService.findAll();
		
	}

	@Override
	public Convolution find(Long id) {
		return convService.find(id);
	}

	@Override
	public void delete(Long id) {
		convService.delete(id);	
		
	}

	@Override
	public Convolution createOrUpdate(Convolution convolution) {
		return convService.createOrUpdate(convolution);
	}

}
