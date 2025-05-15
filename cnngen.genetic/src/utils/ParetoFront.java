package utils;

import java.util.ArrayList;
import java.util.List;

import genetic.AccEnergySolution;

public class ParetoFront {

	private List<AccEnergySolution> frontier;
	
	public ParetoFront() {
        frontier = new ArrayList<>();
    }
	
	private boolean isDominatedBy(AccEnergySolution solution, AccEnergySolution other) {
        return solution.objectives()[0] <= other.objectives()[0] && solution.objectives()[1] <= other.objectives()[1]
                && (solution.objectives()[0] < other.objectives()[0] || solution.objectives()[1] < other.objectives()[1]);
    }
	
    public void mergeIntoFrontier(AccEnergySolution solution) {
        List<AccEnergySolution> toBeRemoved = new ArrayList<>();
        for (AccEnergySolution f : frontier) {
            if (isDominatedBy(solution, f)) {
                return;
            }
            if (isDominatedBy(f, solution)) {
                toBeRemoved.add(f);
            }
        }
        frontier.removeAll(toBeRemoved);
        frontier.add(solution);
    }
    
    public boolean isBelowFrontier(AccEnergySolution solution) {
        for (AccEnergySolution f : this.getFrontier()) {
            if (isDominatedBy(solution, f)) {
                return true;
            }
        }
        return false;
    }
    
    public List<AccEnergySolution> getFrontier() {
        return frontier;
    }
}
