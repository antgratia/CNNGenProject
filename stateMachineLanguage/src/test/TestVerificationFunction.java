package test;

import org.testng.annotations.Test;

import utils.CheckArchitectureValidity;
import utils.GestionHPP;
import utils.ProgramConfig;
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
  GestionHPP ghpp;
  SMLFactoryImpl factory;

  @BeforeTest
  public void Init() {
	  ProgramConfig programConfig = new ProgramConfig();
	  factory = new SMLFactoryImpl();
	  ag = new ArchitectureGraphView();
	  ghpp = new GestionHPP(programConfig);
	  //ag.init("testverif");
	  
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
  

@Test
  public void testVerifyImgSize() {
	
	try {
		//ag.verifyImgSize(ghpp);
		
		assertEquals(ag.getGraph().get(8).getOutputImgSize(), ag.getGraph().get(12).getOutputImgSize());
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  @Test
  public void testverifyFilters() {
	  
	  try {
		//ag.verifyFilters();
		//ag.PersiteGraphDB();
		if(ag.getGraph().get(13) instanceof domain.Add){
			assertEquals(ag.getGraph().get(8).getOutputFilter(), ag.getGraph().get(12).getOutputFilter());
		}		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 
  private void makeErrors(ArchitectureGraphView ag, int layerpos ) throws Exception {
	domain.Convolution conv = (domain.Convolution) ag.getGraph().get(layerpos);
	int prevImgSize =  ag.getGraph().get(layerpos-1).getInputImgSize();
	System.out.println(prevImgSize);
	conv.setPadding("same");
	conv.setStride(2);
	conv.setKernel(2);
	conv.setOutputImgSize(prevImgSize/2);
	conv.setOutputFilter(150);
	
	ag.getGraph().get(layerpos+1).setOutputImgSize(conv.getOutputImgSize());
	ag.getGraph().get(layerpos+1).setOutputFilter(conv.getOutputFilter());
	
	//ag.PersiteGraphDB();
	
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
