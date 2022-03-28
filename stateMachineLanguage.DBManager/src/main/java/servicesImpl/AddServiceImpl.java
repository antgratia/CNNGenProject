package servicesImpl;

import domain.Add;
import services.IAddService;

public class AddServiceImpl extends GenericService<Add> implements IAddService {

	@Override
	Class<Add> getEntityType() {
		// TODO Auto-generated method stub
		return Add.class;
	}

	
}
