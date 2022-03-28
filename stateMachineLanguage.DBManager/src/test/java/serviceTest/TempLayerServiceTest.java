package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.DBManagment;
import domain.TempLayer;
import servicesImpl.TempLayerServiceImpl;

public class TempLayerServiceTest {

	TempLayerServiceImpl temps;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
		
	@BeforeTest
	public void init() {
		try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			temps = new TempLayerServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
	}
	  
	TempLayer temp = new TempLayer();
  
  	@Test (priority = 0)
	public void createConvTest() {
	  	TempLayer ntemp = temps.createOrUpdate(temp);
		assertEquals(temp, ntemp);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		TempLayer ntemp = temps.find(temp.getId());
		assertEquals(ntemp.getId(), temp.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		TempLayer ntemp = temps.find((long) 5);
		assertNull(ntemp);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		TempLayer temp1 = new TempLayer();
		TempLayer temp2 = new TempLayer();
		temps.createOrUpdate(temp1);
		temps.createOrUpdate(temp2);
		
		Iterable<TempLayer> it = temps.findAll();
		int i =0;
		for(TempLayer c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		temps.delete(temp.getId());
		assertNull(temps.find(temp.getId()));
	}
}
