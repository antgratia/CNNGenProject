package servicesImpl;

import domain.Convolution;
import services.IConvolutionService;

public class ConvolutionServiceImpl extends GenericService<Convolution> implements IConvolutionService {

	@Override
	Class<Convolution> getEntityType() {
		// TODO Auto-generated method stub
		return Convolution.class;
	}
	
	
}
