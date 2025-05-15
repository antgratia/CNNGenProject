package generation;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.EList;

import domain.Dense;
import utils.CheckArchitectureValidity;
import utils.ProgramConfig;
import views.ArchitectureGraphView;
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
import xtext.cNNDescLang.MergeConv;
import xtext.cNNDescLang.Right;
import xtext.cNNDescLang.impl.CNNDescLangFactoryImpl;
import xtext.generator.CNNDescLangGenerator;

public class GeneratorGeneticUtils {

	CNNDescLangFactoryImpl factory = new CNNDescLangFactoryImpl();
			
	CNNDescLangGenerator smlGenerator = new CNNDescLangGenerator();
	
	static Random rand = new Random();
	
		
	static List<String> flatOrGLo;
	static List<String> poolOrNot;
	static List<String> dropOrNot;
	static List<String> convs;
	static List<String> convOrMerge;
	static List<String> convOrEmpty;
	
	static List<Integer> nbFeatureExtraction;
	static List<Integer> nbDense;
	static List<Integer> nbOtherZero;
	static List<Integer> nbOther;
	
	CheckArchitectureValidity cav;
	
	ProgramConfig pc;
	
	
	public void getconfig(ProgramConfig programConfig){
		flatOrGLo = programConfig.getFlattenOrGlobalPool();
		poolOrNot = programConfig.getPoolingOrNot();
		dropOrNot = programConfig.getDropout();
		convs = programConfig.getConvolution();
		convOrEmpty = programConfig.getConvOrEmpty();
		convOrMerge = programConfig.getConvOrMerge();
		nbFeatureExtraction = programConfig.getNbFeatureExtraction();
		nbDense = programConfig.getNbDense();
		nbOtherZero = programConfig.getNbOtherZero();
		nbOther = programConfig.getNbOther();
	}
	
	public ProgramConfig getPC() {
		return this.pc;
	}
	
	public GeneratorGeneticUtils() {
		pc = new ProgramConfig();
		getconfig(pc);
		cav = new CheckArchitectureValidity();

	}
	
	public CNNDescLang getNewArchi() throws IOException {
		// init sentence
		CNNDescLang cnndl = factory.createCNNDescLang();
		
		
		// creation architecture 
		Architecture archi = factory.createArchitecture();
		archi.setInput("input");
		archi.setOutput("output");
		cnndl.setCnndesclang(archi);
		
		
		// feature extration
		var nbfe = nbFeatureExtraction.get(rand.nextInt(nbFeatureExtraction.size()));
		for (var i=0; i<nbfe; i++){
			archi.getFe().add(featureExtractionManagement());	
		}
		
		// Interstice
		archi.setInter(IntersticeManagement());
		
		// classification
		var nbCla = nbDense.get(rand.nextInt(nbDense.size())); 
		for (var j=0; j<nbCla; j++){
			archi.getClass_().add(classificationManagement());
			
		} 
		
		//var SML sml = test
		
		cav = new CheckArchitectureValidity();
		String strSML = cav.checkValidity(null, cnndl);
		
		return cnndl;
	}
	
	public ArchitectureGraphView generate(CNNDescLang cnndl, String pyFilename, String expDir) {
		smlGenerator = new CNNDescLangGenerator();
		return smlGenerator.generate(cnndl, pyFilename, expDir, pc);
	}
	
	public FeatureExtraction featureExtractionManagement(){
		
		FeatureExtraction fe = factory.createFeatureExtraction();
			
		var strConvOrMerge = convOrMerge.get(rand.nextInt(convOrMerge.size()));
		if(strConvOrMerge.equals("conv")){
			// === CONV
			fe.setConv(convManagement());
		}else{
			// === MERGE
			fe.setMerge(mergeManagement(false));
		}
		
		
		// === DROPOUT
		var strDropOrNot = dropOrNot.get(rand.nextInt(dropOrNot.size()));
		
		
		if(strDropOrNot.equals("")){
			// do nothing
		} else{
			fe.setDrop(strDropOrNot);
		}
		
		
		// === POOL	
		var strPoolOrNot = poolOrNot.get(rand.nextInt(poolOrNot.size()));
		
		if(strPoolOrNot.equals("")){
			// do nothing
		} else{
			fe.setPool(strPoolOrNot);
		}
		
		
		
		return fe;
	}
	
	public Interstice IntersticeManagement(){
		Interstice inter = factory.createInterstice();
		FlattenOrGlobal flatorGlo = factory.createFlattenOrGlobal();
		
		String strflatOrGLo = flatOrGLo.get(rand.nextInt(flatOrGLo.size()));
		
		if(strflatOrGLo.equals("flatten")){
			// === FLATTEN
			flatorGlo.setFlat(strflatOrGLo);
			
		}else {
			// ==== GLOPOOL
			flatorGlo.setGp(strflatOrGLo);
		}
		
		inter.setFg(flatorGlo);
		return inter;
	}
	
	public Classification classificationManagement(){
		Classification classif = factory.createClassification();
			
		// === DROPOUT
		var strDropOrNot = dropOrNot.get(rand.nextInt(dropOrNot.size()));
		
		if(strDropOrNot.equals("")){
			// do nothing
		} else{
			classif.setDrop(strDropOrNot);
		}
		classif.setD("dense");
		
		return classif;
	}

	public Convolution convManagement(){
		String strConv = convs.get(rand.nextInt(convs.size()));
		
		Convolution conv = factory.createConvolution();
		if(strConv.equals("conv")){
			conv.setConv(strConv);
			
		}else if (strConv.equals("bnconv")){
			conv.setBnconv(strConv);
			
		}else {
			conv.setConvbn(strConv);
		}
		
		return conv;
	}
	
	public Merge mergeManagement(boolean recu){
		Merge merge = factory.createMerge();
		if(recu){
			merge.getMergeBody().add(mergeBodyManagement(1));
		}else{
			var nbMerge = nbOther.get(rand.nextInt(nbOther.size())); 
			for(var i=0; i<nbMerge; i++){
				merge.getMergeBody().add(mergeBodyManagement(nbMerge));
			}
		}
		
		return merge;
	}
	
	public MergeBody mergeBodyManagement(int nbMerge){
		MergeBody mergeBody = factory.createMergeBody();
		
		// LEFT
		mergeBody.setLeft(leftManagement());
		
		// RIGHT
		mergeBody.setRight(rightManagement(nbMerge));
		
		return mergeBody;
	}
	
	public Left leftManagement(){
		Left left = factory.createLeft();
		
		// === POOL	
		var strPoolOrNot = poolOrNot.get(rand.nextInt(poolOrNot.size()));
		
		if(strPoolOrNot.equals("")){
			// do nothing
		} else{
			left.setP(strPoolOrNot);
		}
		
		// conv or merge
		left.setCom(convOrMergeManagement());
		
		
		// === POOL	
		strPoolOrNot = poolOrNot.get(rand.nextInt(poolOrNot.size()));
		
		if(strPoolOrNot.equals("")){
			// do nothing
		} else{
			left.setPool(strPoolOrNot);			
		}
		
		
		return left;
	}
	
	public Right rightManagement(int nbMerge){
		var right = factory.createRight();
		var strConvOrEmpty = "";
		if(nbMerge > 1 ) strConvOrEmpty = "conv";
		else strConvOrEmpty = convOrEmpty.get(rand.nextInt(convOrEmpty.size()));
		
		var nb = nbOther.get(rand.nextInt(nbOther.size())); 
		
		if(strConvOrEmpty.equals("conv")){
			for (var i = 0; i<nb; i++){
				right.getConv().add(convManagement());
			}
		}else{
			right.setEmpty(strConvOrEmpty);
		}
		
		return right;
	}
	
	
	public ConvOrMerge convOrMergeManagement(){
		ConvOrMerge com = factory.createConvOrMerge();
		
		var strConvOrMerge = convOrMerge.get(rand.nextInt(convOrMerge.size()));
		if(strConvOrMerge.equals("conv")){
			// === CONV
			var nb = nbOther.get(rand.nextInt(nbOther.size())); 
		
			for(var i =0; i<nb; i++){
				com.getConvdrop().add(convDropManagement());
			}
		}else{
			// === MERGE
			var nbConv = nbOther.get(rand.nextInt(nbOther.size()));
			var nbMerge = nbOther.get(rand.nextInt(nbOther.size())); 
		
		
			for(var i=0; i<nbConv; i++){
				com.getConvdrop().add(convDropManagement());
			}
			for(var i=0; i<nbMerge; i++){
				com.getMergeConv().add(mergeConvManagement());
			}
		}
		
		return com;
	}
	
	public ConvDrop convDropManagement(){
		ConvDrop convDrop = factory.createConvDrop();
		
		convDrop.setConv(convManagement());
		
		// === DROPOUT
		var strDropOrNot = dropOrNot.get(rand.nextInt(dropOrNot.size()));
		
		if(strDropOrNot.equals("")){
			// do nothing
		} else{
			convDrop.setDrop(strDropOrNot);
		}
		
		return convDrop;
	}

	public MergeConv mergeConvManagement(){
		MergeConv mc = factory.createMergeConv();
		
		// Merge
		mc.setMerge(mergeManagement(true));
		
		// Convdrop
		var nbConv = nbOtherZero.get(rand.nextInt(nbOtherZero.size()));
		for(var i=0; i<nbConv; i++){
			mc.getConvdrop().add(convDropManagement());
		}
		return mc;
	}
	
	
}
