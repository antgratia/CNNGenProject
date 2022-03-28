package servicesImpl;

import domain.Interstice;
import services.IIntersticeService;

public class IntersticeServiceImpl extends GenericService<Interstice> implements IIntersticeService {

	@Override
	Class<Interstice> getEntityType() {
		// TODO Auto-generated method stub
		return Interstice.class;
	}

}
