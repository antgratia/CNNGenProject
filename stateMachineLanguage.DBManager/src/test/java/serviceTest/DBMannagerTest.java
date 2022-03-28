package serviceTest;

import static org.junit.Assert.*;


import java.sql.SQLException;

import org.neo4j.ogm.session.Session;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import domain.DBConnFactory;
import domain.DBManagment;


public class DBMannagerTest {
	
	DBManagment dbm;
	String name;
	
	@BeforeSuite
	public void init() {
		dbm = new DBManagment();
		name = "test1";
	}
	@Test
	public void testConnDB() {
		try {
			Session sess = DBConnFactory.getInstance().getSession();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("error connect db");
		}
	}
	
	
	
	@Test
	public void testCreateDB() {
		try {
			dbm.createNewDB(name);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("not able to create "+ name);
		} catch (Exception e) {
			fail("not able to create query class");
			e.printStackTrace();
		}
	}

}
