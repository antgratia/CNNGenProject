package operation;

import org.eclipse.emf.ecore.util.EcoreUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.uma.jmetal.operator.crossover.CrossoverOperator;

import genetic.AccEnergySolution;
import utils.CheckArchitectureValidity;
import xtext.cNNDescLang.Architecture;
import xtext.cNNDescLang.CNNDescLang;
import xtext.cNNDescLang.Classification;
import xtext.cNNDescLang.impl.CNNDescLangFactoryImpl;

@SuppressWarnings("serial")
public class GrammarCrossoverOperator implements CrossoverOperator<AccEnergySolution> {
	
	private final double probability;
	private final int numberParentChild;
	CNNDescLangFactoryImpl factory = new CNNDescLangFactoryImpl();
	CheckArchitectureValidity cav = new CheckArchitectureValidity();
	
	
	public GrammarCrossoverOperator(double probability, int nb) throws Exception {
		if(probability < 0) throw new Exception("probability < 0");
		this.probability = probability;
		this.numberParentChild = nb;
	}

	@Override
	public List<AccEnergySolution> execute(List<AccEnergySolution> source) {
		Random r = new Random();
		List<AccEnergySolution> offspring = new ArrayList<>();
		if (source == null) {
			System.out.println("Parents list is null");
        }else if (source.size() != numberParentChild) {
            System.out.println("The number of parents is not " + numberParentChild + " : " + source.size());
        }
		
		for(int i =0; i<source.size(); i+= 2) {
			if(source.get(i) == source.get(i+1)) {
	        	return source;
	        }
			AccEnergySolution parent1 = source.get(i).copy();
			AccEnergySolution parent2 = source.get(i+1).copy();
			AccEnergySolution offspring1 = parent1.copy();
			AccEnergySolution offspring2 = parent2.copy();
			
	        if (r.nextInt(101) < probability) {
	            performCrossover(parent1, parent2, offspring1, offspring2);
	        }
	
	        offspring.add(offspring1);
	        offspring.add(offspring2);
	        
		}
		return offspring;
	}

	private void performCrossover(AccEnergySolution parent1, AccEnergySolution parent2, AccEnergySolution offspring1,
			AccEnergySolution offspring2) {
		Random r = new Random();
		int indiceParent1 = r.nextInt(parent1.variables().get(0).getCnndesclang().getFe().size());
		int indiceParent2 = r.nextInt(parent2.variables().get(0).getCnndesclang().getFe().size());
		
		if((indiceParent1 == 0 && indiceParent2 == parent2.variables().get(0).getCnndesclang().getFe().size()) || 
				(indiceParent1 == parent1.variables().get(0).getCnndesclang().getFe().size() && indiceParent2==0)) {
			indiceParent1 = Math.round(parent1.variables().get(0).getCnndesclang().getFe().size()/2) ;
			
		}
		
		
		CNNDescLang newcnndl1 = factory.createCNNDescLang();
		CNNDescLang newcnndl2 = factory.createCNNDescLang();
		
		performCrossover(newcnndl1, EcoreUtil.copy(parent1.variables().get(0)), indiceParent1, EcoreUtil.copy(parent2.variables().get(0)), indiceParent2);
		performCrossover(newcnndl2, EcoreUtil.copy(parent2.variables().get(0)), indiceParent2, EcoreUtil.copy(parent1.variables().get(0)), indiceParent1);
		//performCrossover(newcnndl2, new AccEnergySolution(parent2), parent1.variables().get(0));
		
		offspring1.variables().clear();
		offspring1.variables().add(newcnndl1);
		offspring2.variables().clear();
		offspring2.variables().add(newcnndl2);
		
	}

	
	private void performCrossover(CNNDescLang newcnndl1, CNNDescLang parent1, int indicePart1, CNNDescLang parent2,
			int indicePart2) {
		Architecture archi = factory.createArchitecture();
		archi.setInput("input");
		archi.setOutput("output");
		newcnndl1.setCnndesclang(archi);
		
		int i = 0;
		while(i<parent1.getCnndesclang().getFe().size() && i < indicePart1) {
			archi.getFe().add(parent1.getCnndesclang().getFe().get(i));
			i++;
		}
		
		i = indicePart2;
		while(i<parent2.getCnndesclang().getFe().size()) {
			archi.getFe().add(parent2.getCnndesclang().getFe().get(i));
			i++;
		}
		
		archi.setInter(parent2.getCnndesclang().getInter());
		
		List<Classification> temp = new ArrayList<>();
		for (Classification classifi : parent2.getCnndesclang().getClass_()) {
		    temp.add(classifi);
		}
		archi.getClass_().addAll(temp);
		
		System.out.println("\ncrossover verification:");
		
		try {
			EList<Diagnostic> error = cav.checkValidity(newcnndl1);
			if(error.size() > 0) {
				System.out.println(error.get(0));
				System.out.println("indice1 : " + indicePart1);
				System.out.println("indice2 : " + indicePart2);
				System.out.println("size fe 1 : " + parent1.getCnndesclang().getFe().size());
				System.out.println("size fe 2 : " + parent2.getCnndesclang().getFe().size());
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public double crossoverProbability() {
		return this.probability;
	}

	@Override
	public int numberOfRequiredParents() {
		return this.numberParentChild;
	}

	@Override
	public int numberOfGeneratedChildren() {
		return this.numberParentChild;
	}

}
