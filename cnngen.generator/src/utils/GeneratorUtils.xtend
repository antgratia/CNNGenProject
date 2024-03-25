package utils

import java.io.PrintWriter
import java.util.List
import java.util.Random
import xtext.cNNDescLang.Architecture
import xtext.cNNDescLang.FeatureExtraction
import xtext.cNNDescLang.Merge
import xtext.cNNDescLang.impl.CNNDescLangFactoryImpl
import xtext.generator.CNNDescLangGenerator
import xtext.cNNDescLang.CNNDescLang

class GeneratorUtils {
	
		var CNNDescLangFactoryImpl factory = new CNNDescLangFactoryImpl()
		
		var CNNDescLangGenerator smlGenerator = new CNNDescLangGenerator()
		
		static Random rand = new Random();
		
			
		static List<String> flatOrGLo
		static List<String> poolOrNot
		static List<String> dropOrNot
		static List<String> convs
		static List<String> convOrMerge
		static List<String> convOrEmpty
		
		static List<Integer> nbFeatureExtraction
		static List<Integer> nbDense
		static List<Integer> nbOtherZero
		static List<Integer> nbOther
		
		CheckArchitectureValidity cav;
		
		def getconfig(ProgramConfig programConfig){
			flatOrGLo = programConfig.flattenOrGlobalPool
			poolOrNot = programConfig.poolingOrNot
			dropOrNot = programConfig.dropout
			convs = programConfig.convolution
			convOrEmpty = programConfig.convOrEmpty
			convOrMerge = programConfig.convOrMerge
			nbFeatureExtraction = programConfig.nbFeatureExtraction
			nbDense = programConfig.nbDense
			nbOtherZero = programConfig.nbOtherZero
			nbOther = programConfig.nbOther
		}
		
		// generate random architectures valid and create the py file
		def generate(String pyFilename, String smlFilename, String expDir, ProgramConfig programConfig){
		
			getconfig(programConfig)
			
			// init sentence
			var CNNDescLang cnndl = factory.createCNNDescLang()
			
			
			// creation architecture 
			var Architecture archi = factory.createArchitecture()
			archi.input = "input"
			archi.output = "output"
			cnndl.cnndesclang = archi
			
			
			// feature extration
			var nbfe = nbFeatureExtraction.get(rand.nextInt(nbFeatureExtraction.size()));
			for (var i=0; i<nbfe; i++){
				archi.fe.add(featureExtractionManagement)
			}
			
			// Interstice
			archi.inter = IntersticeManagement
			
			// classification
			var nbCla = nbDense.get(rand.nextInt(nbDense.size())); 
			for (var j=0; j<nbCla; j++){
				archi.class_.add(classificationManagement)
				
			} 
			
			//var SML sml = test
			
			cav = new CheckArchitectureValidity()
			var strSML = cav.checkValidity(smlFilename, cnndl)
			
			var writer = new PrintWriter(smlFilename, "UTF-8");
			writer.println(strSML);
			writer.close();
			
			
			// generate python file
			smlGenerator.generate(cnndl, pyFilename, expDir, programConfig);
			

		}
		
		// generate a random architecture valid and create the py file
		def generate(String pyFilename, String smlFilename, String expDir, String strSML, ProgramConfig programConfig){
			
			getconfig(programConfig)
			
			// verify
			cav = new CheckArchitectureValidity()
			cav.checkValidity(strSML)
			
			// convert into SML
			var csml = new ConvertCNNDL()
			var sml = csml.stringToSML(strSML)
			
			var writer = new PrintWriter(smlFilename, "UTF-8");
			writer.println(strSML);
			writer.close();
			
			
			// generate python file
			smlGenerator.generate(sml, pyFilename, expDir, programConfig)
			
		}
		
		
		def featureExtractionManagement(){
			
			var FeatureExtraction fe = factory.createFeatureExtraction()
				
			var strConvOrMerge = convOrMerge.get(rand.nextInt(convOrMerge.size()))
			if(strConvOrMerge == "conv"){
				// === CONV
				fe.conv = convManagement
			}else{
				// === MERGE
				fe.merge = mergeManagement(false)
			}
			
			
			// === DROPOUT
			var strDropOrNot = dropOrNot.get(rand.nextInt(dropOrNot.size()));
			
			
			if(strDropOrNot === ""){
				// do nothing
			} else{
				fe.drop = strDropOrNot
			}
			
			
			// === POOL	
			var strPoolOrNot = poolOrNot.get(rand.nextInt(poolOrNot.size()));
			
			if(strPoolOrNot === ""){
				// do nothing
			} else{
				fe.pool = strPoolOrNot
			}
			
			
			
			return fe
		}
		
		def IntersticeManagement(){
			var inter = factory.createInterstice()
			var flatorGlo = factory.createFlattenOrGlobal()
			
			var strflatOrGLo = flatOrGLo.get(rand.nextInt(flatOrGLo.size()));
			
			if(strflatOrGLo == "flatten"){
				// === FLATTEN
				flatorGlo.flat = strflatOrGLo
				
			}else {
				// ==== GLOPOOL
				flatorGlo.gp = strflatOrGLo
			}
			
			inter.setFg(flatorGlo)
			return inter
		}
		
		def classificationManagement(){
			var classif = factory.createClassification()
				
			// === DROPOUT
			var strDropOrNot = dropOrNot.get(rand.nextInt(dropOrNot.size()));
			
			if(strDropOrNot === ""){
				// do nothing
			} else{
				classif.drop = strDropOrNot
			}
			
			classif.d = "dense"
			
			return classif
		}

		def convManagement(){
			var strConv = convs.get(rand.nextInt(convs.size()));
			
			var conv = factory.createConvolution
			if(strConv == "conv"){
				conv.conv = strConv
				
			}else if (strConv == "bnconv"){
				conv.bnconv = strConv
				
			}else {
				conv.convbn = strConv
				
			}
			
			return conv
		}
		
		def mergeManagement(boolean recu){
			var merge = factory.createMerge
			if(recu){
				merge.mergeBody.add(mergeBodyManagement(1))
			}else{
				var nbMerge = nbOther.get(rand.nextInt(nbOther.size())); 
				for(var i=0; i<nbMerge; i++){
					merge.mergeBody.add(mergeBodyManagement(nbMerge))
				}
			}
			
			return merge
		}
		
		def mergeBodyManagement(int nbMerge){
			var mergeBody = factory.createMergeBody
			
			// LEFT
			mergeBody.left = leftManagement
			
			// RIGHT
			mergeBody.right = rightManagement(nbMerge)
			
			return mergeBody
		}
		
		def leftManagement(){
			var left = factory.createLeft
			
			// === POOL	
			var strPoolOrNot = poolOrNot.get(rand.nextInt(poolOrNot.size()));
			
			if(strPoolOrNot === ""){
				// do nothing
			} else{
				left.p = strPoolOrNot
				
			}
			
			// conv or merge
			left.com = convOrMergeManagement
			
			
			// === POOL	
			strPoolOrNot = poolOrNot.get(rand.nextInt(poolOrNot.size()));
			
			if(strPoolOrNot === ""){
				// do nothing
			} else{
				left.pool = strPoolOrNot
				
			}
			
			
			return left
		}
		
		def rightManagement(int nbMerge){
			var right = factory.createRight
			var strConvOrEmpty = ""
			if(nbMerge > 1 ) strConvOrEmpty = "conv"
			else strConvOrEmpty = convOrEmpty.get(rand.nextInt(convOrEmpty.size()))
			
			var nb = nbOther.get(rand.nextInt(nbOther.size())); 
			
			if(strConvOrEmpty === "conv"){
				for (var i = 0; i<nb; i++){
					right.conv.add(convManagement)
				}
			}else{
				right.empty = strConvOrEmpty
			}
			
			return right
		}
		
		
		def convOrMergeManagement(){
			var com = factory.createConvOrMerge
			
			var strConvOrMerge = convOrMerge.get(rand.nextInt(convOrMerge.size()));
			if(strConvOrMerge == "conv"){
				// === CONV
				var nb = nbOther.get(rand.nextInt(nbOther.size())); 
			
				for(var i =0; i<nb; i++){
					com.convdrop.add(convDropManagement)
				}
			}else{
				// === MERGE
				var nbConv = nbOther.get(rand.nextInt(nbOther.size()))
				var nbMerge = nbOther.get(rand.nextInt(nbOther.size())); 
			
			
				for(var i=0; i<nbConv; i++){
					com.convdrop.add(convDropManagement)
				}
				for(var i=0; i<nbMerge; i++){
					com.mergeConv.add( mergeConvManagement	)
				}
			
			}
			
			return com
		}
		
		def convDropManagement(){
			var convDrop = factory.createConvDrop
			
			convDrop.conv = convManagement
			
			// === DROPOUT
			var strDropOrNot = dropOrNot.get(rand.nextInt(dropOrNot.size()));
			
			if(strDropOrNot === ""){
				// do nothing
			} else{
				convDrop.drop = strDropOrNot
			}
			
			return convDrop
		}

		def mergeConvManagement(){
			var mc = factory.createMergeConv
			
			// Merge
			mc.merge = mergeManagement(true) as Merge
			
			// Convdrop
			var nbConv = nbOtherZero.get(rand.nextInt(nbOtherZero.size()))
			for(var i=0; i<nbConv; i++){
				mc.convdrop.add(convDropManagement)
			}
			return mc
		}
}