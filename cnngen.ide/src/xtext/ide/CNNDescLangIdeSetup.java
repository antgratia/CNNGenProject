/*
 * generated by Xtext 2.26.0
 */
package xtext.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import xtext.CNNDescLangRuntimeModule;
import xtext.CNNDescLangStandaloneSetup;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class CNNDescLangIdeSetup extends CNNDescLangStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new CNNDescLangRuntimeModule(), new CNNDescLangIdeModule()));
	}
	
}
