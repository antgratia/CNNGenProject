package servicesImpl;

import domain.Dropout;
import services.IDropoutService;

public class DropoutServiceImpl extends GenericService<Dropout> implements IDropoutService {

	@Override
	Class<Dropout> getEntityType() {
		// TODO Auto-generated method stub
		return Dropout.class;
	}

	
}
