package utilsTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import domain.Convolution;
import utils.Query;

public class ClassQueryTest {

	
  @Test
  public void param1Test() {
	  String query = "CREATE DATABASE $0 if not exists";
	  Map<Integer, String> params = new HashMap<>();
	  params.put(0,"name");
	  
	  Query<Integer, String> queryParam;
		try {
			queryParam = new Query<>(query , params);
			 assertEquals(queryParam.getModifyQuery(), "CREATE DATABASE name if not exists");
		} catch (Exception e) {
			fail("should not happend");
			e.printStackTrace();
		}
	  
	 
  }
  
  @Test
  public void param2Test() {
	  String query = "Your name is $0 and your surname is $1";
	  Map<Integer, String> params = new HashMap<>();
	  params.put(0,"name");
	  params.put(1, "surname");
	  
	  Query<Integer, String> queryParam;
	try {
		queryParam = new Query<>(query , params);
		assertEquals(queryParam.getModifyQuery(), "Your name is name and your surname is surname");
	} catch (Exception e) {
		fail("should not happend");
		e.printStackTrace();
	}
	  
	  
  }
  
  @Test
  public void paramConvolutionTest() {
	  String query = "This is a Convolution : $0";
	  Map<Integer, Convolution> params = new HashMap<>();
	  params.put(0, new Convolution());
	  
	  Query<Integer, Convolution> queryParam;
	try {
		queryParam = new Query<>(query , params);
		assertEquals(queryParam.getModifyQuery(), "This is a Convolution : Convolution(super=Layer(id=null, layerPos=0, reduction=false, last=false, imgSize=0, nbFilter=0), kernel=0, stride=0, padding=null, fct_activation=null)");
	} catch (Exception e) {
		fail("should not happend");
		e.printStackTrace();
	}
	  
	  
  }
  
  @Test
  public void paramStringConvTest() {
	  String query = "This is a $0 : $1";
	  Map<Integer, Object> params = new HashMap<>();
	  params.put(0, "Convolution");
	  params.put(1, new Convolution());
		  
	  Query<Integer, Object> queryParam;
	try {
		queryParam = new Query<>(query , params);
		assertEquals(queryParam.getModifyQuery(), "This is a Convolution : Convolution(super=Layer(id=null, layerPos=0, reduction=false, last=false, imgSize=0, nbFilter=0), kernel=0, stride=0, padding=null, fct_activation=null)");
	} catch (Exception e) {
		fail("should not happend");
		e.printStackTrace();
	}
	  
	  
  }
  
}









