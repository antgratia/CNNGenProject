/*
 * generated by Xtext 2.26.0
 */
package xtext.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractCNNDescLangValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(xtext.cNNDescLang.CNNDescLangPackage.eINSTANCE);
		return result;
	}
}
