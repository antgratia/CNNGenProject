package domain;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.model.Result;

import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Query;

@Data
@NoArgsConstructor
public class DBManagment {
	
	private static final String QUERY_CREATE_DB = "CREATE DATABASE $0 if not exists";
	private static final String QUERY_REMOVE_ALL_RELATION = "MATCH (n)-[r]->(n1) delete r";
	private static final String QUERY_REMOVEL_ALL_NODES = "MATCH (n) delete n";
	private static final String QUERY_FIND_BY_LAYERPOS = "MATCH (n) WHERE n.layerPos = $0 RETURN n";
	private static final String QUERY_FIND_BY_ID = "MATCH (n) WHERE ID(n) = $0 RETURN n";
	private static final String QUERY_COUNT_EDGES_FROM_NODES = "MATCH p=((n1)-[:Next]->()) UNWIND nodes(p) as n call{ WITH n match (c)<-[r:Next]-(n) RETURN count(r) as cnt} RETURN DISTINCT n, cnt";
	
	
	
	
	private String DBName;
	
	
	public void createNewDB(String name) throws Exception {
		DBName = name;
		Map<Integer, String> params = new HashMap<>();
		params.put(0,name);
		Query<Integer, String> query = new Query<>(QUERY_CREATE_DB, params);
		DBConnFactory.getInstance().getSession().query(query.getModifyQuery(), Collections.<String, Object>emptyMap());
		DBConnFactory.createSessionFactory(DBName);
		
	}
	
	public void clearDB() throws SQLException {
		DBConnFactory.getInstance().getSession().query(QUERY_REMOVE_ALL_RELATION, Collections.<String, Object>emptyMap());
		DBConnFactory.getInstance().getSession().query(QUERY_REMOVEL_ALL_NODES, Collections.<String, Object>emptyMap());
		
	}
	
	public Iterable<Map<String, Object>> findByLayerPos(int layerPos) throws Exception {
		Map<Integer, Integer> params = new HashMap<>();
		params.put(0,layerPos);
		Query<Integer, Integer> query = new Query<>(QUERY_FIND_BY_LAYERPOS, params);		
		
		Result result = DBConnFactory.getInstance().getSession().query(query.getModifyQuery(), Collections.<String, Object>emptyMap());
		return result.queryResults();
			
	}
	
	public Iterable<Map<String, Object>> findByID(Long id) throws Exception {
		Map<Integer, Long> params = new HashMap<>();
		params.put(0,id);
		Query<Integer, Long> query = new Query<>(QUERY_FIND_BY_ID, params);	
		
		Result result = DBConnFactory.getInstance().getSession().query(query.getModifyQuery(), Collections.<String, Object>emptyMap());
		return result.queryResults();
	}
	
	public Iterable<Map<String, Object>> countEdgesNodes() throws SQLException {
		Result result = DBConnFactory.getInstance().getSession().query(QUERY_COUNT_EDGES_FROM_NODES, Collections.<String, Object>emptyMap());
		return result.queryResults();
	}
	
	
	

}
