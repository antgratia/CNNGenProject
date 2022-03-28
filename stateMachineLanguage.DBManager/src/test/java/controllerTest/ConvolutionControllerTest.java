package controllerTest;

import org.testng.annotations.Test;

import controllers.IConvolutionController;
import controllersImpl.ConvolutionControllerImpl;
import domain.Convolution;
import domain.DBManagment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.BeforeTest;

public class ConvolutionControllerTest {
  
	IConvolutionController convCtrl;
	Convolution conv;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";

  @BeforeTest
  public void beforeTest() {
	  try {
		dbm.createNewDB(dbname);
		dbm.clearDB();
		convCtrl = new ConvolutionControllerImpl();
		conv = new Convolution();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
  }
  
  @Test (priority = 0)
  public void createConvTest() {
	 Convolution conv1 =convCtrl.createOrUpdate(conv);
	 assertEquals(conv, conv1);
  }
  
  @Test (priority = 1)
  public void findTest() {
	  Convolution conv1 =convCtrl.find(conv.getId());
	  assertEquals(conv, conv1);
  }
  
  @Test (priority = 1)
  public void findAllTest() {
	 Convolution conv1 = new Convolution(1,false,false,32,3,3,3,"same","relu");
	 Convolution conv2 = new Convolution(1,false,false,32,3,3,3,"same","gelu");
	 convCtrl.createOrUpdate(conv1);
	 convCtrl.createOrUpdate(conv2);
	 Iterable<Convolution> it = convCtrl.findAll();
	 
	 int i=0;
	 for(Convolution c: it) {
		 i++;
	 }
	 assertEquals(i, 3);
  }
  
  @Test (priority = 2)
  public void deleteTest() {
	  convCtrl.delete(conv.getId());
	  assertNull(convCtrl.find(conv.getId()));
  }

}
