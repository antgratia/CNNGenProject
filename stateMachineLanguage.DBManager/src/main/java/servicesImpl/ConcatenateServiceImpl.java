package servicesImpl;

import domain.Concatenate;
import services.IConcatenateService;

public class ConcatenateServiceImpl extends GenericService<Concatenate> implements IConcatenateService {

	@Override
	Class<Concatenate> getEntityType() {
		// TODO Auto-generated method stub
		return Concatenate.class;
	}

}
