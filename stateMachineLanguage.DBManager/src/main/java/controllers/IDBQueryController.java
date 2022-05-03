package controllers;

import java.util.Map;

public interface IDBQueryController {

	void createNewDB(String name);
	void clearDB();
	Iterable<Map<String, Object>> findByLayerPos(int layerPos);
	Iterable<Map<String, Object>> findByID(Long id);
	Iterable<Map<String, Object>> countEdgesNodes();

}
