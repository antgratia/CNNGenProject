package utils;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Injector;

import lombok.Data;
import xtext.SMLStandaloneSetup;
import xtext.sML.SML;

@Data
public class ConvertSML {

	private String path = "../../../stateMachineLanguage.generator/output/architecture_SML/test.SML";
	
	
	public SML stringToSML(String archi) throws IOException {

		Injector injector = new SMLStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet rs = injector.getInstance(XtextResourceSet.class);
		

		Resource r = rs.createResource(URI.createURI(path));
		r.load(new StringInputStream(archi), null);
				
		SML model = (SML) r.getContents().get(0);
				
		return model;
	}

}
