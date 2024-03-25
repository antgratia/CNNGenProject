package test;

import org.testng.annotations.Test;

import utils.CheckArchitectureValidity;
import utils.GestionHPP;
import utils.ProgramConfig;
import views.ArchitectureGraphView;
import xtext.cNNDescLang.impl.CNNDescLangFactoryImpl;
import xtext.cNNDescLang.Architecture;
import xtext.cNNDescLang.CNNDescLang;
import xtext.cNNDescLang.Classification;
import xtext.cNNDescLang.ConvDrop;
import xtext.cNNDescLang.ConvOrMerge;
import xtext.cNNDescLang.Convolution;
import xtext.cNNDescLang.FeatureExtraction;
import xtext.cNNDescLang.FlattenOrGlobal;
import xtext.cNNDescLang.Interstice;
import xtext.cNNDescLang.Left;
import xtext.cNNDescLang.Merge;
import xtext.cNNDescLang.MergeBody;
import xtext.cNNDescLang.Right;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

public class TestVerificationFunction {
	
  ArchitectureGraphView ag;
  GestionHPP ghpp;
  CNNDescLangFactoryImpl factory;

  @BeforeTest
  public void Init() {
	  ProgramConfig programConfig = new ProgramConfig();
	  factory = new CNNDescLangFactoryImpl();
	  ag = new ArchitectureGraphView();
	  ghpp = new GestionHPP(programConfig);
	  //ag.init("testverif");
	  
	  CNNDescLang cnndl = createExCNNDL();
	  
	  try {
		ag.createGraph(cnndl.getCnndesclang());	
		
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
  
  
  private CNNDescLang createExCNNDL() {
	  CNNDescLang cnndl = factory.createCNNDescLang();
	
	//architecture
	Architecture archi = factory.createArchitecture();
	archi.setInput("input");
	archi.setOutput("output");
	cnndl.setCnndesclang(archi);
	
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
		var strcnndl = cav.checkValidity("", cnndl);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  
	return cnndl;
	  
  }

}
