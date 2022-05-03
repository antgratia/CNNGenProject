package stateMachineLanguage.DBManager;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import controllers.IBatchNormalisationController;
import controllers.IConvolutionController;
import controllers.IPoolingController;
import controllersImpl.BatchNormalisationControllerImpl;
import controllersImpl.ConvolutionControllerImpl;
import controllersImpl.PoolingControllerImpl;
import domain.BatchNormalisation;
import domain.Convolution;
import domain.DBManagment;
import domain.Dropout;
import domain.Pooling;

public class LayerIntegrationTest {

	DBManagment dbm = new DBManagment();
	String dbname = "test1";
	
	Convolution conv;
	Pooling pool;
	BatchNormalisation bn;
	
	IConvolutionController convCtrl;
	IPoolingController poolCtrl;
	IBatchNormalisationController bnCtrl;
	
	
	@BeforeTest
	public void init() {
		try {
			dbm.createNewDB(dbname);
			dbm.clearDB();
			
			conv = new Convolution();
			pool = new Pooling();
			bn = new BatchNormalisation();
			
			convCtrl = new ConvolutionControllerImpl();
			poolCtrl = new PoolingControllerImpl();
			bnCtrl = new BatchNormalisationControllerImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	@Test
	public void updateConvTest() {
		convCtrl.createOrUpdate(conv);
		
		conv.setFct_activation("gelu");
		conv.setImgSize(32);
		
		Convolution modifConv = convCtrl.createOrUpdate(conv);
		assertEquals(conv, modifConv);
	}
	
	@Test
	public void addEdgeConvPoolTest() {
		poolCtrl.createOrUpdate(pool);
		conv.getNextLayer().add(pool);
		assertEquals(conv.getNextLayer().size(), 1);
	}
	
	@Test
	public void addEdgePoolBNTest() {
		bnCtrl.createOrUpdate(bn);
		pool.getNextLayer().add(bn);
		poolCtrl.createOrUpdate(pool);
		assertEquals(pool.getNextLayer().size(), 1);
	}
	
	@Test
	public void addnewEdgeConv() {
		conv.getNextLayer().add(new Dropout());
		assertEquals(conv.getNextLayer().size(), 2);
	}
	
	

}
