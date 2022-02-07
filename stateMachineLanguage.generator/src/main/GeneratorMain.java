package main;

import java.io.IOException;
import java.io.PrintWriter;

import utils.CoZeroProxiesApi;


public class GeneratorMain{

	public static void main(String[] args) {
		
		GeneratorUtils tg = new GeneratorUtils();
		CoZeroProxiesApi api = new CoZeroProxiesApi();
		
		
		// output dir
		var output_dir = "output/";
		// python directory
		var py_dir = "architecture_py/";
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(output_dir+ "zen_score.json");
			writer.println('[');
		
			for (int i=1; i<10; i++) {
				String filename = output_dir + py_dir + "architecture_"+i+".py";
				System.out.println("Random Generation...");
				String strAchitectureSimplify = tg.generate(filename);
				System.out.println(filename + " generate\n");
				
				
				try {
					String reponse = api.callZenScore(strAchitectureSimplify, filename);
					while(reponse == null){
						
					}
					System.out.println(reponse);
					
					writer.println(reponse);
					writer.println(',');
					
					
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			writer.println(']');
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			
			writer.close();
		}
		
		
		

	}

}
