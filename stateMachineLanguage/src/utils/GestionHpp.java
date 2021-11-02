package utils;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import models.Convolution;
import models.LayerInterface;
import models.Pooling;

public class GestionHpp {
	
	private static List<Integer> kernel = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<Integer> stride = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7));
	private static List<String> padding = new ArrayList<String>(List.of("same","valid"));
	private static List<String> fctActivation = new ArrayList<String>(List.of("relu","selu","tanh"));

	public static void gestionKernelPaddingStride(LayerInterface layer) throws Exception {
		if (layer instanceof Convolution) {
			Convolution conv = (Convolution) layer;
			conv.setKernel(kernel.get(0));
			conv.setNbFilter(10);
			conv.setPadding(padding.get(0));
			conv.setFct_activation(fctActivation.get(0));
			conv.setStride(stride.get(0));
		}else if (layer instanceof Pooling){
			
		}else {
			throw new UnexpectedException("error: gestionKernelPaddingStride");
		}

	}
}
