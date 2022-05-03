package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import domain.DBManagment;
import domain.Input;
import servicesImpl.InputServiceImpl;

public class InputServiceTest {


	InputServiceImpl inputs;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	
  @BeforeTest
  public void init() {
	  try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			inputs = new InputServiceImpl();
		}catch (Exception e) {
			fail("create DB");
			e.printStackTrace();
		} 
  }
  
  Input input = new Input();
  
  @Test (priority = 0)
	public void createConvTest() {
	  	Input ninput = inputs.createOrUpdate(input);
		assertEquals(input, ninput);
	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Input ninput = inputs.find(input.getId());
		assertEquals(ninput.getId(), input.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Input ninput = inputs.find((long) 5);
		assertNull(ninput);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Input input1 = new Input();
		Input input2 = new Input();
		inputs.createOrUpdate(input1);
		inputs.createOrUpdate(input2);
		
		Iterable<Input> it = inputs.findAll();
		int i =0;
		for(Input c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		inputs.delete(input.getId());
		assertNull(inputs.find(input.getId()));
	}
}
