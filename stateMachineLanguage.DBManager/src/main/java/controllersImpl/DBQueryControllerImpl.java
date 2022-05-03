package controllersImpl;

import java.sql.SQLException;
import java.util.Map;

import controllers.IDBQueryController;
import domain.DBManagment;

public class DBQueryControllerImpl implements IDBQueryController{
	
	DBManagment db = new DBManagment();

	@Override
	public void createNewDB(String name) {
		try {
			db.createNewDB(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void clearDB() {
		try {
			db.clearDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Iterable<Map<String, Object>> findByLayerPos(int layerPos) {
		try {
			return db.findByLayerPos(layerPos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<Map<String, Object>> findByID(Long id) {
		try {
			return db.findByID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<Map<String, Object>> countEdgesNodes() {
		try {
			return db.countEdgesNodes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
