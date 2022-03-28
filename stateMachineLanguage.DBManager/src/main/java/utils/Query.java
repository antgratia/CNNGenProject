package utils;

import java.util.Map;
import java.util.Map.Entry;

import lombok.Data;

@Data
public class Query<K,V> {
	
	private String query;
	private Map<K, V> params;
	private String modifyQuery;
	
	public Query(String query, Map<K, V> params) throws Exception {
		super();
		if(query == null || params == null) throw new NullPointerException();
		if(params.size()<1) throw new Exception("params is empty");

		
		this.query = query;
		this.params = params;
		this.modifyQuery = this.query;
		for(Entry<K, V> param: params.entrySet()) {
			modifyQuery = modifyQuery.replace("$"+param.getKey(), param.getValue().toString());
		}
		
	}




}
