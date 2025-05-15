package operation;

import java.util.List;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import genetic.AccEnergySolution;
import utils.FolderManager;

@SuppressWarnings("serial")
public class Evaluator implements SolutionListEvaluator<AccEnergySolution>{

	
	private FolderManager fm = FolderManager.getFolderManager();
	
	private int MaxNumPop ;
	
	public Evaluator(int MaxNumPop) {
		this.MaxNumPop = MaxNumPop;
	}
	
	
	@Override
	public List<AccEnergySolution> evaluate(List<AccEnergySolution> solutionList, Problem<AccEnergySolution> problem) {
		System.out.println("\ngeneration " + fm.getNbGen()+":\n");
		while(solutionList.size()<this.MaxNumPop) {
			solutionList.add(problem.createSolution());
		}
		
		for (AccEnergySolution sol: solutionList){
			problem.evaluate(sol);
		}
		
		
		fm.setNbArchi(0);
		fm.increaseNbGen();
		return solutionList;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
