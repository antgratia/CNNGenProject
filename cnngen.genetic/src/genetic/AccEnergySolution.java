package genetic;

import java.util.HashMap;
import java.util.stream.IntStream;

import org.uma.jmetal.solution.AbstractSolution;
import org.uma.jmetal.solution.Solution;

import xtext.cNNDescLang.CNNDescLang;

@SuppressWarnings("serial")
public class AccEnergySolution extends AbstractSolution<CNNDescLang>{

	protected AccEnergySolution(int numberOfVariables, int numberOfObjectives) {
		super(numberOfVariables, numberOfObjectives);
	}

	public AccEnergySolution(AccEnergySolution solution) {
		this(solution.variables().size(), solution.objectives().length);
		
		IntStream.range(0, solution.variables().size()).forEach(i -> variables().set(i, solution.variables().get(i)));
	    IntStream.range(0, solution.objectives().length).forEach(i -> objectives()[i] = solution.objectives()[i]);
	    IntStream.range(0, solution.constraints().length).forEach(i -> constraints()[i] = solution.constraints()[i]);
	    
	    attributes = new HashMap<>(solution.attributes);
		
	}

	@Override
	public AccEnergySolution copy() {
		return new AccEnergySolution(this);
	}

}
