package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.DBManagment;
import domain.Output;
import servicesImpl.OutputServiceImpl;

public class OutputServiceTest {


	OutputServiceImpl outs;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			outs = new OutputServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Output out = new Output();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Output nout = outs.createOrUpdate(out);
		assertEquals(out, nout);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Output nout = outs.find(out.getId());
		assertEquals(nout.getId(), out.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Output nout = outs.find((long) 5);
		assertNull(nout);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Output out1 = new Output();
		Output out2 = new Output();
		outs.createOrUpdate(out1);
		outs.createOrUpdate(out2);
		
		Iterable<Output> it = outs.findAll();
		int i =0;
		for(Output c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		outs.delete(out.getId());
		assertNull(outs.find(out.getId()));
	}
}
