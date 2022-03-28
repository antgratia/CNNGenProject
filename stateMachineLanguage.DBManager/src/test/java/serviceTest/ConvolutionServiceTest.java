package serviceTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.sql.SQLException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import domain.Convolution;
import domain.DBManagment;
import servicesImpl.ConvolutionServiceImpl;

public class ConvolutionServiceTest {
	
	ConvolutionServiceImpl convolutionService;
	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	@BeforeSuite
	public void init() {
		try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			convolutionService = new ConvolutionServiceImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	Convolution convPers = new Convolution(1,false,false,32,3,3,3,"same","relu");
	
	@Test (priority = 0)
  	public void createConvTest() {
		Convolution conv = convolutionService.createOrUpdate(convPers);
		assertEquals(convPers, conv);
  	}
	
	@Test (priority = 1)
	public void findByIDTest() {
		Convolution conv = convolutionService.find(convPers.getId());
		assertEquals(conv.getId(), convPers.getId());
	}
	
	@Test (priority = 1)
	public void findByIDNotExistTest() {
		Convolution conv = convolutionService.find((long) 5);
		assertNull(conv);
	}
	
	@Test (priority = 2)
	public void findAllTest() {
		Convolution conv1 = new Convolution(1,false,false,32,3,3,3,"same","relu");
		Convolution conv2 = new Convolution(1,false,false,32,3,3,3,"same","gelu");
		convolutionService.createOrUpdate(conv1);
		convolutionService.createOrUpdate(conv2);
		
		Iterable<Convolution> it = convolutionService.findAll();
		int i =0;
		for(Convolution c: it) {
			i++;
		}
		assertEquals(i, 3);
	}
	
	@Test (priority = 3)
	public void deleteTest() {
		convolutionService.delete(convPers.getId());
		assertNull(convolutionService.find(convPers.getId()));
	}
	
}
