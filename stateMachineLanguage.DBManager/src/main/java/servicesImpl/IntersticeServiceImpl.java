package servicesImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.model.Result;

import domain.DBConnFactory;
import domain.Interstice;
import services.IIntersticeService;
import utils.Query;

public class IntersticeServiceImpl extends GenericService<Interstice> implements IIntersticeService {

	private final static String QUERY_FIND_BY_LAYERPOS = "MATCH (n) WHERE n.layerPos = $0 RETURN n";
	
	@Override
	Class<Interstice> getEntityType() {
		// TODO Auto-generated method stub
		return Interstice.class;
	}

	@Override
	public Interstice findByLayerpos(int layerpos) throws Exception {
		Map<Integer, Integer> params = new HashMap<>();
		params.put(0, layerpos);
		Query<Integer, Integer> query = new Query<>(QUERY_FIND_BY_LAYERPOS, params);
		Result result = DBConnFactory.getInstance().getSession().query(query.getModifyQuery(),  Collections.<String, Object>emptyMap());
		for (Map<String, Object> res :result.queryResults()) {
			return (Interstice) res.get("n");
		}
		return null;
	}

}
