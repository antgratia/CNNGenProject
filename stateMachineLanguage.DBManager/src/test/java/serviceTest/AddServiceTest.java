package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.Add;
import domain.DBManagment;
import servicesImpl.AddServiceImpl;

public class AddServiceTest {

	AddServiceImpl as;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			as = new AddServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Add add = new Add();
  
  @Test (priority = 0)
	public void createConvTest() {
		Add nAdd = as.createOrUpdate(add);
		assertEquals(add, nAdd);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Add nadd = as.find(add.getId());
		assertEquals(nadd.getId(), add.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Add nadd = as.find((long) 5);
		assertNull(nadd);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Add add1 = new Add();
		Add add2 = new Add();
		as.createOrUpdate(add1);
		as.createOrUpdate(add2);
		
		Iterable<Add> it = as.findAll();
		int i =0;
		for(Add c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		as.delete(add.getId());
		assertNull(as.find(add.getId()));
	}

}
