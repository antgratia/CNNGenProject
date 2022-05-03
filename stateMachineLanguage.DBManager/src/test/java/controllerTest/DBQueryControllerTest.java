package controllerTest;

import org.testng.annotations.Test;

import controllers.IConvolutionController;
import controllers.IDBQueryController;
import controllersImpl.ConvolutionControllerImpl;
import controllersImpl.DBQueryControllerImpl;
import domain.*;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.BeforeTest;

public class DBQueryControllerTest {

	IDBQueryController dbQueryController ;
	IConvolutionController convolutionController;
	
  @BeforeTest
  public void beforeTest() {
	  dbQueryController = new DBQueryControllerImpl();
	  dbQueryController.createNewDB("test1");
	  dbQueryController.clearDB();
	  
	  
	  convolutionController = new ConvolutionControllerImpl();
  }
  
  @Test
  public void findbyLayerpos() {
	  Convolution conv = new Convolution();
	  conv.setLayerPos(24);
	  convolutionController.createOrUpdate(conv);
	  
	  Iterable<Map<String, Object>> it = dbQueryController.findByLayerPos(conv.getLayerPos());
	  
	  for(Map<String, Object> i : it) {
		 for (Entry<String, Object> e : i.entrySet()) {
			 
			 assertEquals(conv.getLayerPos(), ((Convolution) e.getValue()).getLayerPos());
		 }
	  }
  }
  
  
  @Test
  public void findByID() {
	  Convolution conv = new Convolution();
	  conv.setLayerPos(50);
	  convolutionController.createOrUpdate(conv);
	  
	  Iterable<Map<String, Object>> it = dbQueryController.findByID(conv.getId());
	  
	  for(Map<String, Object> i : it) {
			 for (Entry<String, Object> elem : i.entrySet()) {
				 assertEquals(conv.getId(), ((Convolution) elem.getValue()).getId());
			 }
		  }
  }
  @Test
  public void countEdgesNodesTest() {
	  Convolution convprev = new Convolution();
	  Convolution convPrinc = new Convolution();
	  Convolution conv1 = new Convolution();
	  Convolution conv2 = new Convolution();
	  Convolution conv3 = new Convolution();
	  
	  convprev.getNextLayer().add(convPrinc);
	  convPrinc.getPrevLayer().add(convprev);
	  
	  convolutionController.createOrUpdate(convprev);
	  
	  
	  conv1.getPrevLayer().add(convPrinc);
	  conv2.getPrevLayer().add(convPrinc);
	  conv3.getPrevLayer().add(convPrinc);
	  
	  convolutionController.createOrUpdate(conv1);
	  convolutionController.createOrUpdate(conv2);
	  convolutionController.createOrUpdate(conv3);
	  
	  
	  convPrinc.getNextLayer().add(conv1);
	  convPrinc.getNextLayer().add(conv2);
	  convPrinc.getNextLayer().add(conv3);
	  
	  convolutionController.createOrUpdate(convPrinc);
	  
	  Iterable<Map<String, Object>> it = dbQueryController.countEdgesNodes();
	  
	  List<Layer> layers = new ArrayList<>();
	  int count = 0 ;
	  for(Map<String, Object> i: it) {
		  if((Long) i.get("cnt")>2) {
			  layers.add((Layer) i.get("n"));
		  }
		  count++;
		
	  }
	  
	  assertEquals(count, 5);
	  assertEquals(layers.size(), 1);
  }

  
}
