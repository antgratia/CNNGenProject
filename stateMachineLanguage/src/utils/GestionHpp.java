package utils;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.BatchNormalisation;
import models.Convolution;
import models.Dense;
import models.Dropout;
import models.LayerInterface;
import models.Pooling;

public class GestionHpp {
	
	// info img
	private final int maxSizeImg = 28;
	private int currentSizeImg = 28;
	
	private final int nb_class = 10;
	
	
	// hpp Convolution + Pooling
	private static List<Integer> kernel = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<Integer> stride = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<String> padding = new ArrayList<String>(List.of("same","valid"));
		// hpp Convolution supp
	private static List<String> fctActivation = new ArrayList<String>(List.of("relu","selu","tanh"));
	private int nbFilter;
	
	// hpp dropout
	private static List<Double> dropoutRate = new ArrayList<Double>(List.of(.1, .2, .4, .5, .8, .01));
	
	//hpp batch Normalisation
	private static List<Double> epsilon = new ArrayList<Double>(List.of(1.1e-10, 1.001e-5, 0.001, 1.1e-5, 1.1e-7));;
	
	// hpp Dense
	private int units;
	
	
	// entry point
	public static void gestionConvolution(Convolution conv){
			conv.setKernel(kernel.get(0));
			conv.setNbFilter(10);
			conv.setPadding(padding.get(0));
			conv.setFct_activation(fctActivation.get(0));
			conv.setStride(stride.get(0));


	}
	
	public static void gestionPooling(Pooling pool) {
		
	}

	public static void gestionDropout(Dropout dropout) {
		Random rand = new Random();
		dropout.setDropoutRate(dropoutRate.get(rand.nextInt(dropoutRate.size())));
		
	}

	public static void gestionBN(BatchNormalisation bn) {
		Random rand = new Random();
		bn.setEpsilon(epsilon.get(rand.nextInt(epsilon.size())));
	}

	public static void gestionDense(Dense dense) {
		
	}
	
	
	
	
}
