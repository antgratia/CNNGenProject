package main;

import java.io.File;
import java.time.Duration;
import java.time.Instant;


public class GeneratorMain{
	
	
	static String expDir = "test_3/";
	static final int REPEAT = 1;
	
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
    
    // tensorboard directory
    static String tensorboardDir = "architecture_tb/";
    
    // json directory
    //static String jsonDir = "architecture_json/";
    
	
	
	public static void main(String[] args) {
		
		
		//createFolder();
		int total_time = 0;
		
		for (int i=1; i<(REPEAT+1); i++) {
			GeneratorUtils tg = new GeneratorUtils();
			String filename = output_dir + pyDir+ expDir + "architecture_"+i+".py";
			System.out.println("Random Generation no : " + i);
			Instant start = Instant.now();
			String DBName = expDir.replace("_", "").replace("/", "") + "Architecture"+i;
			tg.generate(filename, expDir, DBName);
			Instant end = Instant.now();
			System.out.println(filename + " generate");
			Duration timeElapsed = Duration.between(start, end);
			total_time += timeElapsed.toMillis();
			System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds\n");
						
		}
		
		System.out.println("Total Time : " + total_time + " milliseconds\n");
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
		
		folder = new File(output_dir+tensorboardDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		//folder = new File(output_dir+jsonDir+expDir);
		//if(!folder.exists()) folder.mkdirs();
		
	}

}
