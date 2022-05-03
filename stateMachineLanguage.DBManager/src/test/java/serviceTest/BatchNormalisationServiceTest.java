package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.BatchNormalisation;
import domain.DBManagment;
import servicesImpl.BatchNormalisationServiceImpl;

public class BatchNormalisationServiceTest {
	
	
	BatchNormalisationServiceImpl bns;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			bns = new BatchNormalisationServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  BatchNormalisation bn = new BatchNormalisation();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	BatchNormalisation nbn = bns.createOrUpdate(bn);
		assertEquals(bn, nbn);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		BatchNormalisation nbn = bns.find(bn.getId());
		assertEquals(nbn.getId(), bn.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		BatchNormalisation nbn = bns.find((long) 5);
		assertNull(nbn);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		BatchNormalisation bn1 = new BatchNormalisation();
		BatchNormalisation bn2 = new BatchNormalisation();
		bns.createOrUpdate(bn1);
		bns.createOrUpdate(bn2);
		
		Iterable<BatchNormalisation> it = bns.findAll();
		int i =0;
		for(BatchNormalisation c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		bns.delete(bn.getId());
		assertNull(bns.find(bn.getId()));
	}

}
