package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import utils.CoZeroProxiesApi;


public class GeneratorMain{
	
	
	static String expDir = "experiment_2/";
	static final int REPEAT = 30;
	
	// output dir
	static String output_dir = "output/";
	// python directory
	static String pyDir = "architecture_py/";

    // log directory
    static String logDir = "architecture_log/";
    
    // png directory
    static String pngDir = "architecture_img/";
    
    // csv directory
    static String csvDir = "architecture_csv/";
    
    // json directory
    static String jsonDir = "architecture_json/";
	
	
	public static void main(String[] args) {
		
		
		CoZeroProxiesApi api = new CoZeroProxiesApi();
		
		
		
		createFolder();
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(output_dir+ jsonDir + expDir +"zen_score.json");
			writer.println('[');
		
			for (int i=1; i<(REPEAT+1); i++) {
				GeneratorUtils tg = new GeneratorUtils();
				
				String filename = output_dir + pyDir+ expDir + "architecture_"+i+".py";
				System.out.println("Random Generation...");
				ArrayList<String> strReturnList = tg.generate(filename, expDir);
				System.out.println(filename + " generate\n");
				
				
				try {
					String reponse = api.callZenScore(strReturnList, filename);
					while(reponse == null){}
					
					System.out.println(reponse+ "\n\n");
					
					writer.println(reponse);
					if(i<REPEAT) writer.println(',');
					
					
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

	private static void createFolder() {
		File folder = new File(output_dir+pyDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(output_dir+logDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(output_dir+pngDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(output_dir+csvDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(output_dir+jsonDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
	}

}
