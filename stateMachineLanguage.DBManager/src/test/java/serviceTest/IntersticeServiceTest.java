package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.DBManagment;
import domain.Interstice;
import servicesImpl.IntersticeServiceImpl;

public class IntersticeServiceTest {

	IntersticeServiceImpl inters;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			inters = new IntersticeServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Interstice inter = new Interstice();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Interstice ninter = inters.createOrUpdate(inter);
		assertEquals(inter, ninter);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Interstice ninter = inters.find(inter.getId());
		assertEquals(ninter.getId(), inter.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Interstice ninter = inters.find((long) 5);
		assertNull(ninter);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Interstice inter1 = new Interstice();
		Interstice inter2 = new Interstice();
		inters.createOrUpdate(inter1);
		inters.createOrUpdate(inter2);
		
		Iterable<Interstice> it = inters.findAll();
		int i =0;
		for(Interstice c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		inters.delete(inter.getId());
		assertNull(inters.find(inter.getId()));
	}
}
