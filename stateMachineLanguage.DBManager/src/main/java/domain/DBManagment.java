package domain;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Query;

@Data
@NoArgsConstructor
public class DBManagment {
	
	private static final String QUERY_CREATE_DB = "CREATE DATABASE $0 if not exists";
	private static final String QUERY_REMOVE_ALL_RELATION = "MATCH (n)-[r]->(n1) delete r";
	private static final String QUERY_REMOVEL_ALL_NODES = "MATCH (n) delete n";
	private static final String QUERY_USE_DB = "USE $0";
	
	private String DBName;
	
	
	public void createNewDB(String name) throws Exception {
		DBName = name;
		Map<Integer, String> params = new HashMap<>();
		params.put(0,name);
		Query<Integer, String> query = new Query<>(QUERY_CREATE_DB, params);
		DBConnFactory.getInstance().getSession().query(query.getModifyQuery(), Collections.<String, Object>emptyMap());
		
		DBConnFactory.createSessionFactory(DBName);
		
	}
	
	public void useDB() throws Exception {
		Map<Integer, String> params = new HashMap<>();
		params.put(0,DBName);
		
		Query<Integer, String> query = new Query<>(QUERY_USE_DB, params);
		DBConnFactory.getInstance().getSession().query(query.getModifyQuery(), Collections.<String, Object>emptyMap());
	}
	
	public void clearDB() throws SQLException {
		DBConnFactory.getInstance().getSession().query(QUERY_REMOVE_ALL_RELATION, Collections.<String, Object>emptyMap());
		DBConnFactory.getInstance().getSession().query(QUERY_REMOVEL_ALL_NODES, Collections.<String, Object>emptyMap());
		
	}
	
	

}
