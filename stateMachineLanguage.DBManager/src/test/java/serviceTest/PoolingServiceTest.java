package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.DBManagment;
import domain.Pooling;
import servicesImpl.PoolingServiceImpl;

public class PoolingServiceTest {

	PoolingServiceImpl pools;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			pools = new PoolingServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Pooling pool = new Pooling();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Pooling npool = pools.createOrUpdate(pool);
		assertEquals(pool, npool);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Pooling npool = pools.find(pool.getId());
		assertEquals(npool.getId(), pool.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Pooling npool = pools.find((long) 5);
		assertNull(npool);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Pooling pool1 = new Pooling();
		Pooling pool2 = new Pooling();
		pools.createOrUpdate(pool1);
		pools.createOrUpdate(pool2);
		
		Iterable<Pooling> it = pools.findAll();
		int i =0;
		for(Pooling c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		pools.delete(pool.getId());
		assertNull(pools.find(pool.getId()));
	}
}
