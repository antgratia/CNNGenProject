package utils;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Injector;

import lombok.Data;
import xtext.CNNDescLangStandaloneSetup;
import xtext.cNNDescLang.CNNDescLang;

@Data
public class ConvertCNNDL {

	private String path = "../../../stateMachineLanguage.generator/output/architecture_cnndl/test.cnndl";
	
	
	public CNNDescLang stringToSML(String archi) throws IOException {

		Injector injector = new CNNDescLangStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet rs = injector.getInstance(XtextResourceSet.class);
		

		Resource r = rs.createResource(URI.createURI(path));
		r.load(new StringInputStream(archi), null);
				
		CNNDescLang model = (CNNDescLang) r.getContents().get(0);
				
		return model;
	}

}
