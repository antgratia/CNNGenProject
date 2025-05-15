package test;


import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;

import genetic.AccEnergySolution;
import genetic.ArchitectureProblem;
import operation.Evaluator;
import operation.GrammarCrossoverOperator;
import operation.GrammarMutationOperator;
import operation.Selection;

public class Main {
	
	public static void main(String[] args) {
		
		int max_evaluations = 500;
		int populationSize = 10;
		int matingPoolSize = 4;
		int offspringPopulation = 10;
		
		int crossoverProb = 60;
		int mutationProb = 1;
		
		if (args.length == 1 ) {
			max_evaluations = Integer.parseInt(args[0]);
		}else if(args.length == 6) {
			max_evaluations = Integer.parseInt(args[0]);
			crossoverProb = Integer.parseInt(args[1]);
			mutationProb = Integer.parseInt(args[2]);
			populationSize = Integer.parseInt(args[3]);
			matingPoolSize = Integer.parseInt(args[4]);
			offspringPopulation = Integer.parseInt(args[5]);
			
		}
		
		System.out.println("maxeval : " + max_evaluations);
		System.out.println("crossover Probability : " + crossoverProb);
		System.out.println("Mutation Probability : " + mutationProb);
		System.out.println("population Size : " + populationSize);
		System.out.println("matingPoolSize : " + matingPoolSize);
		System.out.println("offspringPopulation : " + offspringPopulation);
		
		ArchitectureProblem problem = new ArchitectureProblem();
		GrammarMutationOperator mutationOperator = null;
		GrammarCrossoverOperator grammarCrossoverOperator = null;
		try {
			grammarCrossoverOperator = new GrammarCrossoverOperator(crossoverProb, matingPoolSize);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		Evaluator eval = new Evaluator(offspringPopulation);
		Selection selection = new Selection();
		try {
			mutationOperator = new GrammarMutationOperator(mutationProb);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		NSGAII<AccEnergySolution> algorithm = new NSGAII<>(
				problem,
				max_evaluations,
				populationSize,
				matingPoolSize,
				offspringPopulation,
				grammarCrossoverOperator,
				mutationOperator,
				selection,
				eval);
		
        algorithm.run();
        System.out.println("Final population:");
        algorithm.result().forEach(System.out::println);
    }

}

