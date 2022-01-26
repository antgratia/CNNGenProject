package main;

public class GeneratorMain {

	public static void main(String[] args) {
		GeneratorUtils tg = new GeneratorUtils();
		
		// output dir
		var output_dir = "output/";
		// python directory
		var py_dir = "architecture_py/";
		
		for (int i=1; i<11; i++) {
			String filename = output_dir + py_dir + "architecture_"+i+".py";
			System.out.println("Random Generation...");
			tg.generate(filename);
			System.out.println(filename + " generate\n");
		}

	}

}
