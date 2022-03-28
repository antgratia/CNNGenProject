package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.Dropout;
import domain.DBManagment;
import servicesImpl.DropoutServiceImpl;

public class DropoutServiceTest {


	DropoutServiceImpl drops;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			drops = new DropoutServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Dropout drop = new Dropout();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Dropout ndrop = drops.createOrUpdate(drop);
		assertEquals(drop, ndrop);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Dropout ndrop = drops.find(drop.getId());
		assertEquals(ndrop.getId(), drop.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Dropout ndrop = drops.find((long) 5);
		assertNull(ndrop);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Dropout drop1 = new Dropout();
		Dropout drop2 = new Dropout();
		drops.createOrUpdate(drop1);
		drops.createOrUpdate(drop2);
		
		Iterable<Dropout> it = drops.findAll();
		int i =0;
		for(Dropout c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		drops.delete(drop.getId());
		assertNull(drops.find(drop.getId()));
	}
}
