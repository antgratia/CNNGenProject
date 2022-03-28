package serviceTest;

import org.testng.annotations.Test;

import domain.Concatenate;
import domain.DBManagment;
import servicesImpl.ConcatenateServiceImpl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;

public class ConcatenateServiceTest {
	
	ConcatenateServiceImpl cs;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			cs = new ConcatenateServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Concatenate c = new Concatenate();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Concatenate nc = cs.createOrUpdate(c);
		assertEquals(c, nc);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Concatenate nc = cs.find(c.getId());
		assertEquals(nc.getId(), c.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Concatenate nc = cs.find((long) 5);
		assertNull(nc);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Concatenate c1 = new Concatenate();
		Concatenate c2 = new Concatenate();
		cs.createOrUpdate(c1);
		cs.createOrUpdate(c2);
		
		Iterable<Concatenate> it = cs.findAll();
		int i =0;
		for(Concatenate c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		cs.delete(c.getId());
		assertNull(cs.find(c.getId()));
	}

}
