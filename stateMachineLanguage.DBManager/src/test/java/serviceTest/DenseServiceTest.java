package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.Dense;
import domain.DBManagment;
import servicesImpl.DenseServiceImpl;

public class DenseServiceTest {
	
	DenseServiceImpl ds;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			ds = new DenseServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Dense d = new Dense();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Dense nd = ds.createOrUpdate(d);
		assertEquals(d, nd);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Dense nd = ds.find(d.getId());
		assertEquals(nd.getId(), d.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Dense nd = ds.find((long) 5);
		assertNull(nd);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Dense d1 = new Dense();
		Dense d2 = new Dense();
		ds.createOrUpdate(d1);
		ds.createOrUpdate(d2);
		
		Iterable<Dense> it = ds.findAll();
		int i =0;
		for(Dense c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		ds.delete(d.getId());
		assertNull(ds.find(d.getId()));
	}
}
