package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import utils.CheckArchitectureValidity;
import utils.GeneratorUtils;
import utils.ProgramConfig;
import views.ArchitectureGraphView;


public class CNNGeneratorMain{
	
	static CheckArchitectureValidity cav;
	
	
	static String expDir = "test_3/";
	static int repeat = 1;
	
	static String newCnndl = "";
	
	// output dir
	static String outputDir;
	
	// python directory
	static String pyDir;
	
	// python directory
	static String cnndlDir;

    // log directory
    static String logDir;
    
    // png directory
    static String pngDir;
    
    // csv directory
    static String csvDir;
    
    // tensorboard directory
    static String tensorboardDir;
    
    // Code Carbon output directory
    static String emissionDir;
    
    // Adjacency matrix directory
    static String adjacencyDir;
    
    static FileWriter writer = null;
    private static boolean addHeader = false;


	private static ArchitectureGraphView archi;
    
    // json directory
    //static String jsonDir = "architecture_json/";
	
	
	public static void main(String[] args) throws Exception {
		
		ProgramConfig programConfig = new ProgramConfig();
		
		// init
		outputDir = programConfig.getOutputDir();
		pyDir = programConfig.getPyDir();
		cnndlDir = programConfig.getSMLDir();
		logDir = programConfig.getLogDir();
		pngDir = programConfig.getPngDir();
		csvDir = programConfig.getCSVDir();
		tensorboardDir = programConfig.getTensorboardDir();
		emissionDir = programConfig.getEmissionDir();
		adjacencyDir = programConfig.getAdjacencyDir();
		
		
        int timeoutMillis = 15000; // Timeout de 15 secondes (60000 millisecondes)
		
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
			// param1 -> dir
			// param2 -> nb archi to build
			
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
			ExecutorService executor = Executors.newSingleThreadExecutor();
			for (int i=1; i<(repeat+1); i++) {
				final int iterationNumber = i;
				
				// Execute in Future for timeout
	            Future<?> future = executor.submit(() -> {
	                GeneratorUtils gu = new GeneratorUtils();
					String pyFilename = outputDir + pyDir + expDir + "architecture_"+iterationNumber+".py";
					String cnndlFilename = outputDir + cnndlDir + expDir + "architecture_"+iterationNumber+".sml";
					System.out.println("Random Generation no : " + iterationNumber);
					gu.generate(pyFilename, cnndlFilename, expDir, programConfig);
					System.out.println(pyFilename + " generate");
					
	            });

	            try {
	            	Instant start = Instant.now();
	                future.get(timeoutMillis, TimeUnit.MILLISECONDS);
	                Instant end = Instant.now();
	                Duration timeElapsed = Duration.between(start, end);
					total_time += timeElapsed.toMillis();
					System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds\n");
	            } catch (Exception e) {
	                System.out.println("Timeout occurred for iteration " + i);
	                future.cancel(true); // cancel task
	                i --;
	                continue; // next it
	            }
			}
			
			/*
			for (int i=1; i<(repeat+1); i++) {
				timeoutThread.start();
				GeneratorUtils gu = new GeneratorUtils();
				String pyFilename = outputDir + pyDir + expDir + "architecture_"+i+".py";
				String smlFilename = outputDir + smlDir + expDir + "architecture_"+i+".sml";
				System.out.println("Random Generation no : " + i);
				Instant start = Instant.now();
				gu.generate(pyFilename, smlFilename, expDir, programConfig);
				Instant end = Instant.now();
				System.out.println(pyFilename + " generate");
				Duration timeElapsed = Duration.between(start, end);
				total_time += timeElapsed.toMillis();
				System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds\n");
				timeoutThread.interrupt();	
			}*/
			
			System.out.println("Total Time : " + total_time + " milliseconds\n");
			
			
		}else if(args.length == 3) {
			
			// instantiate specific architecture 
			// param1 -> dir
			// param2 -> num
			// param3 -> archi to build
			
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
			String pyFilename = outputDir + pyDir + expDir + "architecture_"+nb+".py";
			String cnndlFilename = outputDir + cnndlDir + expDir + "architecture_"+nb+".sml";
			System.out.println("Create Architecture no : " + nb);
			Instant start = Instant.now();
			gu.generate(pyFilename, cnndlFilename, expDir, args[2], programConfig);
			Instant end = Instant.now();
			System.out.println(pyFilename + " generate");
			Duration timeElapsed = Duration.between(start, end);
			System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds\n");
			
			
		}else if (args.length == 4 ) {
			
			// generate from param3 to param4
			// param1 -> type
			// param2 -> dir
			// param3 -> factor
			// param4 -> nb archi
			
			if(args[0].equals("gen")) {
				if(args[1] != "") expDir = args[1];
				else {
					System.out.println("Argument 1 is invalid");
					System.exit(1);
				}
				
				int factor = 1;
				if(Integer.parseInt(args[2]) >= 0) factor = Integer.parseInt(args[2]);
				else {
					System.out.println("Argument 3 must be higher than 0");
					System.exit(1);
				}
				
				if(Integer.parseInt(args[3]) > 0) repeat = Integer.parseInt(args[3]);
				else {
					System.out.println("Argument 4 must be higher than 0");
					System.exit(1);
				}
				
				createFolder();
				int total_time = 0;
				ExecutorService executor = Executors.newSingleThreadExecutor();
				for (int i=(repeat*factor)+1; i<((repeat*factor)+repeat+1); i++) {
					final int iterationNumber = i;
					int tries = 0;
					boolean stillContinue = true;
					
					while (stillContinue && tries <= 4) {  // Repeat until the task is successful
				        Future<?> future = executor.submit(() -> {
				            GeneratorUtils gu = new GeneratorUtils();
				            String pyFilename = outputDir + pyDir + expDir + "architecture_" + iterationNumber + ".py";
				            String cnndlFilename = outputDir + cnndlDir + expDir + "architecture_" + iterationNumber + ".sml";
				            System.out.println("Random Generation no : " + iterationNumber);
				            archi = gu.generate(pyFilename, cnndlFilename, expDir, programConfig);
				            System.out.println(pyFilename + " generate");
				        });

				        try {
				            Instant start = Instant.now();
				            future.get(timeoutMillis, TimeUnit.MILLISECONDS);  // Wait for the task with timeout
				            Instant end = Instant.now();
				            Duration timeElapsed = Duration.between(start, end);
				            total_time += timeElapsed.toMillis();
				            System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds\n");
				            stillContinue = false;
				        } catch (TimeoutException e) {
				            System.out.println("Timeout occurred for iteration " + i + ", retrying...");
				            future.cancel(true);  // Cancel the current task
				            tries ++;
				        } catch (Exception e) {
				            System.out.println("An error occurred for iteration " + i + ": " + e.getMessage());
				            future.cancel(true);
				            tries ++;
				        }
				    }
					if (tries > 4) {
			            executor = Executors.newSingleThreadExecutor();
			            i--;
					}
					
					if(stillContinue == false) {
						try {	
			            	
			            	writer = new FileWriter(outputDir + "dt_info.csv", true);
			            	
				            if(!addHeader) {
				            	writer.append("nom,flops, NbParams, nbLayers\n");
				            	addHeader = true;
				            }
				            
				            archi.computeFlops();
				            archi.computeParameters();
				            
				            writer.append("architecture_" + iterationNumber +","+ archi.getFlops()+","+ archi.getNbParams() +","+ archi.getGraph().size() + "\n");
							
						} catch (IOException e) {
							System.out.println(e);
						} finally {
							try {
								writer.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					
					
				}
				
				executor.shutdown();
				System.out.println("Total Time : " + total_time + " milliseconds\n");
				
				
			}
			
			
			
		}else {
			throw new IllegalArgumentException("Too Much Argument");
		}
		

	}

	// create all the require folder
	private static void createFolder() {
		File folder = new File(outputDir+pyDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+logDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+pngDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+csvDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+tensorboardDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+cnndlDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+emissionDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		folder = new File(outputDir+adjacencyDir+expDir);
		if(!folder.exists()) folder.mkdirs();
		
		
		//folder = new File(output_dir+jsonDir+expDir);
		//if(!folder.exists()) folder.mkdirs();
		
	}

}
