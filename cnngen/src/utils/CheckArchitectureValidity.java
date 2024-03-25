package utils;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Injector;

import xtext.CNNDescLangStandaloneSetup;
import xtext.cNNDescLang.CNNDescLang;
import xtext.cNNDescLang.Architecture;
import xtext.cNNDescLang.Classification;
import xtext.cNNDescLang.ConvDrop;
import xtext.cNNDescLang.ConvOrMerge;
import xtext.cNNDescLang.Convolution;
import xtext.cNNDescLang.FeatureExtraction;
import xtext.cNNDescLang.Interstice;
import xtext.cNNDescLang.Left;
import xtext.cNNDescLang.Merge;
import xtext.cNNDescLang.MergeBody;
import xtext.cNNDescLang.MergeConv;
import xtext.cNNDescLang.Right;



/*
 * 
 *	class that check the validity of a sentence
 *
 */


public class CheckArchitectureValidity {

	private String path = "../../../stateMachineLanguage.generator/output/architecture_cnndl/test.cnndl";
	
	/*
	 * 
	 * 	Check the validity of SML type
	 * 
	 */

	public String checkValidity(String file, CNNDescLang cnndl) throws IOException {

		String smlArchi = SMLToString(cnndl.getCnndesclang());

		System.out.println(smlArchi);

		Injector injector = new CNNDescLangStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet rs = injector.getInstance(XtextResourceSet.class);

		Resource r = rs.createResource(URI.createURI(path));
		r.load(new StringInputStream(smlArchi), null);
				
		System.out.println(r.getErrors());
		
		return smlArchi;
	}
	
	/*
	 * 
	 * Check validity of a string
	 * 
	 */
	
	public Object[] checkValidity(String smlArchi) throws IOException {


		Injector injector = new CNNDescLangStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet rs = injector.getInstance(XtextResourceSet.class);

		Resource r = rs.createResource(URI.createURI(path));
		r.load(new StringInputStream(smlArchi), null);
		
		return(r.getErrors().toArray());
	}

	/*
	 * 
	 * 	convert type SML to string
	 * 
	 */
	
	private String SMLToString(Architecture architecture) {

		String smlArchi = "";

		if(architecture.getInput() != "") smlArchi += "input ";

		for(FeatureExtraction fe : architecture.getFe()) {
			smlArchi += feToString(fe);
		}

		if(architecture.getInter() != null)
			smlArchi += interToString(architecture.getInter());

		for(Classification dense: architecture.getClass_())
			smlArchi += classToString(dense);

		if(architecture.getOutput() != "") smlArchi += "output ";

		return smlArchi;
	}

	private String feToString(FeatureExtraction fe) {
		String strFe = "";
		if(fe.getConv() != null ){
			// === CONV
			strFe = convToString(fe.getConv());
		}else if (fe.getMerge() != null){
			// === MERGE
			strFe = mergeToString(fe.getMerge());
		}


		// === DROPOUT
		if(fe.getDrop() != null){
			strFe += "dropout ";
		}

		// === POOL		
		if(fe.getPool() != null){
			strFe += poolToString(fe.getPool());
		}


		return strFe;
	}

	private String convToString(Convolution conv) {
		String strConv = "";
		if(conv.getConv() != null) strConv += "conv ";
		else if (conv.getBnconv() != null) strConv += "bnconv ";
		else if (conv.getConvbn() != null) strConv += "convbn ";
		else strConv += "error conv";
		return strConv;
	}

	private String mergeToString(Merge merge) {
		String strMerge = "[ ";
		for(MergeBody mb: merge.getMergeBody())
			strMerge += mergeBodyToString(mb);

		strMerge += "] ";
		return strMerge;
	}

	private String mergeBodyToString(MergeBody mb) {
		String strMb ="( ";

		strMb += leftToString(mb.getLeft());

		strMb += ", ";

		strMb += rightToString(mb.getRight());

		strMb += ") ";
		return strMb;
	}

	private String leftToString(Left left) {
		String strLeft = "";

		if(left.getP() != null)
			strLeft += poolToString(left.getP());

		strLeft += convOrMergeToString(left.getCom());


		if(left.getPool() != null)
			strLeft += poolToString(left.getPool());

		return strLeft;
	}

	private String convOrMergeToString(ConvOrMerge com) {
		String strCom = "";

		for(ConvDrop cd : com.getConvdrop())
			strCom += convDropToString(cd);

		if(com.getMergeConv() != null) {
			for (MergeConv mc: com.getMergeConv()) {
				for(ConvDrop cd: mc.getConvdrop())
					strCom += convDropToString(cd);
					strCom += mergeToString(mc.getMerge());
			}
		}

		return strCom;
	}

	private String convDropToString(ConvDrop cd) {
		String strConvDrop = "";
		strConvDrop += convToString(cd.getConv());
		if(cd.getDrop() != null) strConvDrop += "dropout ";
		return strConvDrop;
	}

	private String rightToString(Right right) {
		String strRight = "";
		if(right.getEmpty() != null)
			strRight += "Empty ";
		else  {
			for(Convolution c: right.getConv())
				strRight += convToString(c);
		}
		return strRight;
	}

	private String poolToString(String pool) {
		
		String strPool = "";
		if (pool.equals("avg_pooling")) strPool += "avg_pooling ";
		else if (pool.equals("max_pooling")) strPool += "max_pooling ";
		else strPool +="poolToString : error pool";
		return strPool;
	}

	private String interToString(Interstice inter) {
		String strInter = "";
		if(inter.getFg().getFlat() != null) strInter += "flatten ";
		else if (inter.getFg().getGp().equals("global_avg_pooling")) strInter += "global_avg_pooling ";
		else if (inter.getFg().getGp().equals("global_max_pooling")) strInter += "global_max_pooling ";
		else strInter += "error inter ";
		return strInter;
	}

	private String classToString(Classification dense) {
		String strDense = "";
		if(dense.getDrop() != null) strDense += "dropout ";
		strDense += "dense ";
		return strDense;
	}

}