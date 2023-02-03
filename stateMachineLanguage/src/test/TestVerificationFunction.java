package test;

import org.testng.annotations.Test;

import utils.CheckArchitectureValidity;
import utils.GestionHPPNeo4j;
import views.ArchitectureGraphView;
import xtext.sML.Architecture;
import xtext.sML.Classification;
import xtext.sML.ConvDrop;
import xtext.sML.ConvOrMerge;
import xtext.sML.Convolution;
import xtext.sML.FeatureExtraction;
import xtext.sML.FlattenOrGlobal;
import xtext.sML.Interstice;
import xtext.sML.Left;
import xtext.sML.Merge;
import xtext.sML.MergeBody;
import xtext.sML.Right;
import xtext.sML.SML;
import xtext.sML.impl.SMLFactoryImpl;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

public class TestVerificationFunction {
	
  ArchitectureGraphView ag;
  GestionHPPNeo4j ghpp;
  SMLFactoryImpl factory;

  @BeforeTest
  public void Init() {
	  
	  factory = new SMLFactoryImpl();
	  ag = new ArchitectureGraphView();
	  ghpp = new GestionHPPNeo4j();
	  ag.init("testverif");
	  
	  SML sml = createExSML();
	  
	  try {
		ag.createGraph(sml.getSml());	
		
		ag.architectureHpp(ghpp);
		
		
		makeErrors(ag, 11);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }
  
// [Input(super=Layer(id=20, layerPos=0, reduction=true, last=false, imgSize=32, nbFilter=3)), Convolution(super=Layer(id=21, layerPos=1, reduction=true, last=false, imgSize=31, nbFilter=16), kernel=2, stride=1, padding=valid, fctActivation=tanh), BatchNormalisation(super=Layer(id=22, layerPos=2, reduction=true, last=false, imgSize=31, nbFilter=16), epsilon=1.1E-7), Convolution(super=Layer(id=23, layerPos=3, reduction=true, last=false, imgSize=7, nbFilter=32), kernel=7, stride=4, padding=valid, fctActivation=tanh), BatchNormalisation(super=Layer(id=25, layerPos=4, reduction=false, last=false, imgSize=7, nbFilter=32), epsilon=1.1E-7), Convolution(super=Layer(id=26, layerPos=5, reduction=false, last=false, imgSize=7, nbFilter=32), kernel=3, stride=1, padding=same, fctActivation=tanh), BatchNormalisation(super=Layer(id=27, layerPos=6, reduction=false, last=false, imgSize=7, nbFilter=32), epsilon=1.1E-5), Convolution(super=Layer(id=28, layerPos=7, reduction=false, last=false, imgSize=7, nbFilter=32), kernel=4, stride=1, padding=same, fctActivation=tanh), BatchNormalisation(super=Layer(id=29, layerPos=8, reduction=false, last=false, imgSize=7, nbFilter=32), epsilon=1.001E-5), Convolution(super=Layer(id=24, layerPos=9, reduction=true, last=false, imgSize=31, nbFilter=32), kernel=1, stride=1, padding=same, fctActivation=tanh), BatchNormalisation(super=Layer(id=31, layerPos=10, reduction=false, last=false, imgSize=31, nbFilter=32), epsilon=1.1E-5), Convolution(super=Layer(id=32, layerPos=11, reduction=false, last=false, imgSize=7, nbFilter=32), kernel=7, stride=4, padding=valid, fctActivation=tanh), BatchNormalisation(super=Layer(id=33, layerPos=12, reduction=false, last=false, imgSize=31, nbFilter=32), epsilon=1.1E-10), Concatenate(super=Layer(id=30, layerPos=13, reduction=true, last=false, imgSize=7, nbFilter=44)), Interstice(super=Layer(id=34, layerPos=14, reduction=true, last=false, imgSize=7, nbFilter=44)), Dense(super=Layer(id=35, layerPos=15, reduction=true, last=false, imgSize=7, nbFilter=44), units=316, fctActivation=tanh), Dense(super=Layer(id=18, layerPos=16, reduction=true, last=true, imgSize=7, nbFilter=44), units=10, fctActivation=softmax), Output(super=Layer(id=19, layerPos=17, reduction=true, last=false, imgSize=7, nbFilter=44))]


@Test
  public void testVerifyImgSize() {
	
	try {
		ag.verifyImgSize(ghpp);
		
		assertEquals(ag.getGraph().get(8).getImgSize(), ag.getGraph().get(12).getImgSize());
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  @Test
  public void testverifyFilters() {
	  
	  try {
		ag.verifyFilters();
		ag.PersiteGraphDB();
		if(ag.getGraph().get(13) instanceof domain.Add){
			assertEquals(ag.getGraph().get(8).getNbFilter(), ag.getGraph().get(12).getNbFilter());
		}		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 
  private void makeErrors(ArchitectureGraphView ag, int layerpos ) throws Exception {
	domain.Convolution conv = (domain.Convolution) ag.getGraph().get(layerpos);
	int prevImgSize =  ag.getGraph().get(layerpos-1).getImgSize();
	System.out.println(prevImgSize);
	conv.setPadding("same");
	conv.setStride(2);
	conv.setKernel(2);
	conv.setImgSize(prevImgSize/2);
	conv.setNbFilter(150);
	
	ag.getGraph().get(layerpos+1).setImgSize(conv.getImgSize());
	ag.getGraph().get(layerpos+1).setNbFilter(conv.getNbFilter());
	
	ag.PersiteGraphDB();
	
  }
  
  
  private SML createExSML() {
	SML sml = factory.createSML();
	
	//architecture
	Architecture archi = factory.createArchitecture();
	archi.setInput("input");
	archi.setOutput("output");
	sml.setSml(archi);
	
	// feature extraction
		// add conv
	FeatureExtraction fe = factory.createFeatureExtraction();
	Convolution conv = factory.createConvolution();
	conv.setConvbn("convbn");
	fe.setConv(conv);
	archi.getFe().add(fe);
	
	// add merge
	fe = factory.createFeatureExtraction();
	Merge merge = factory.createMerge();
	fe.setMerge(merge);
	
	MergeBody mergeBody = factory.createMergeBody();
	
	Left left = factory.createLeft();
	
	ConvOrMerge com = factory.createConvOrMerge();
	
	for(var i =0; i<3; i++){
		ConvDrop convDrop = factory.createConvDrop();
		conv = factory.createConvolution();
		conv.setConvbn("convbn");
		convDrop.setConv(conv);
		com.getConvdrop().add(convDrop);
	}
	
	left.setCom(com);

	Right right = factory.createRight();
	
	for (var i = 0; i<2; i++){
		conv = factory.createConvolution();
		conv.setConvbn("convbn");
		right.getConv().add(conv);
	}
	
	mergeBody.setLeft(left);
	mergeBody.setRight(right);
	
	merge.getMergeBody().add(mergeBody);
	
	archi.getFe().add(fe);
	
	
	// Interstice
	Interstice inter = factory.createInterstice();
	FlattenOrGlobal flatorGlo = factory.createFlattenOrGlobal();
	flatorGlo.setFlat("flatten"); 

	inter.setFg(flatorGlo);
	archi.setInter(inter);
				
	// classification
	for (var j=0; j<2; j++){
		Classification classif = factory.createClassification();
		classif.setD("dense");
		archi.getClass_().add(classif);
					
	} 
				
	CheckArchitectureValidity cav = new CheckArchitectureValidity();
	try {
		var strSML = cav.checkValidity("", sml);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  
	return sml;
	  
  }

}
