package main;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

import utils.CheckArchitectureValidity;
import utils.GeneratorUtils;


public class GeneratorMain{
	
	static CheckArchitectureValidity cav;
	
	
	static String expDir = "test_3/";
	static int repeat = 1;
	
	static String newSml = "";
	
	// output dir
	static String output_dir = "output/";
	
	// python directory
	static String pyDir = "architecture_py/";
	
	// python directory
	static String smlDir = "architecture_sml/";

    // log directory
    static String logDir = "architecture_log/";
    
    // png directory
    static String pngDir = "architecture_img/";
    
    // csv directory
    static String csvDir = "architecture_csv/";
    
    // tensorboard directory
    static String tensorboardDir = "architecture_tb/";
    
    // emssion Code Carbon output directory
    static String emissionDir = "emissions/";
    
    
    // json directory
    //static String jsonDir = "architecture_json/";
    
	
	
	public static void main(String[] args) throws Exception {
		
		
		if (args.length == 1 ) {
			// only check if sml is valid
			
			cav = new CheckArchitectureValidity();
			Object[] errors = cav.checkValidity(args[0]);
			
			if (errors.length > 0 ) {
				System.out.println(errors[0].toString());
				System.exit(2);
			}
			System.exit(0);
			
		}else if (args.length == 2 ) {
			
			// do experiment 
			
			if(args[0] != "") expDir = args[0];
			else {
				System.out.println("Argument 1 is invalid");
				System.exit(1);
			}
			
			if(Integer.parseInt(args[1]) > 0) repeat = Integer.parseInt(args[1]);
			else {
				System.out.println("Argument 2 must be higher than 0");
				System.exit(1);
			}
			
			
			createFolder();
			int total_time = 0;
			for (int i=1; i<(repeat+1); i++) {
				GeneratorUtils gu = new GeneratorUtils();
				String pyFilename = output_dir + pyDir + expDir + "architecture_"+i+".py";
				String smlFilename = output_dir + smlDir + expDir + "architecture_"+i+".sml";
				System.out.println("Random Generation no : " + i);
				Instant start = Instant.now();
				String DBName = expDir.replace("_", "").replace("/", "") + "Architecture"+i;
				gu.generate(pyFilename, smlFilename, expDir, DBName);
				Instant end = Instant.now();
				System.out.println(pyFilename + " generate");
				Duration timeElapsed = Duration.between(start, end);
				total_time += timeElapsed.toMillis();
				System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds\n");
							
			}
			
			System.out.println("Total Time : " + total_time + " milliseconds\n");
			
			
		}else if(args.length == 3) {
			
			if(args[0] != "") expDir = args[0];
			else {
				System.out.println("Argument 1 is invalid");
				System.exit(1);
			}
			int nb = 0;
			if(Integer.parseInt(args[1]) > 0) nb = Integer.parseInt(args[1]);
			else {
				System.out.println("Argument 2 must be higher than 0");
				System.exit(1);
			}
			
			if(args[2] == ""){
				System.out.println("Argument 1 is invalid");
				System.exit(1);
			}
			
			createFolder();
			GeneratorUtils gu = new GeneratorUtils();
			String pyFilename = output_dir + pyDir + expDir + "architecture_"+nb+".py";
			String smlFilename = output_dir + smlDir + expDir + "architecture_"+nb+".sml";
			System.out.println("Create Architecture no : " + nb);
			Instant start = Instant.now();
			String DBName = expDir.replace("_", "").replace("/", "") + "Architecture"+nb;
			gu.generate(pyFilename, smlFilename, expDir, DBName, args[2]);
			Instant end = Instant.now();
			System.out.println(pyFilename + " generate");
			Duration timeElapsed = Duration.between(start, end);
			System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds\n");
			
			
		}else {
			throw new IllegalArgumentException("Too Much Argument");
		}
		

	}

	// create all the require folder
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
		
		folder = new File(output_dir+smlDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(output_dir+emissionDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		//folder = new File(output_dir+jsonDir+expDir);
		//if(!folder.exists()) folder.mkdirs();
		
	}

}
