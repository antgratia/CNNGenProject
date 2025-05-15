package genetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.Problem;

import au.com.bytecode.opencsv.CSVReader;
import domain.Convolution;
import domain.Dense;
import domain.Interstice;
import domain.Layer;
import generation.GeneratorGeneticUtils;
import utils.FolderManager;
import views.ArchitectureGraphView;
import xtext.cNNDescLang.CNNDescLang;

@SuppressWarnings("serial")
public class ArchitectureProblem implements Problem<AccEnergySolution>{
	
	private GeneratorGeneticUtils ggu = new GeneratorGeneticUtils();
	private FolderManager fm = FolderManager.getFolderManager();
	
	private String csvFile = "result.csv";
	private boolean addHeader = false;
	FileWriter writer = null;

	@Override
	public AccEnergySolution createSolution() {
		AccEnergySolution solution = new AccEnergySolution(numberOfVariables(), numberOfObjectives());
		for (int i =0; i < this.numberOfVariables(); i++) {
			try {
				solution.variables().clear();
				solution.variables().add(ggu.getNewArchi());
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		return solution;
	}

	@Override
	public AccEnergySolution evaluate(AccEnergySolution solution) {
		fm.createFolder();
		for (int i = 0; i < solution.variables().size(); i++) {
			// generate architecture file
			String pythonFileName = "architecture_"+fm.getNbArchi()+".py";
			String py_file = fm.getOutputDir()+fm.getPyDir()+fm.getExpDir()+pythonFileName;
			ArchitectureGraphView archi = ggu.generate(solution.variables().get(i), py_file, fm.getExpDir());
			
			int[] layers = CountingLayers(archi);
			
			if(testBuildArchitecture(fm.getOutputDir(), "test_compil.py")) {
			
	            //CNNDescLang cnndl = solution.variables().get(i);
	            archi.computeParameters();
	            // get result from predictor Decition tree
	            float dt_result = ExecutePythonPredictor("dt", py_file, archi.getNbParams(), archi.getGraph().size(),  archi.getFlops(), 
	            		layers[0], layers[1], layers[2], layers[3], layers[4]);
	            
	            // get result from predictor python code
	            float pc_result = ExecutePythonPredictor("pc", py_file, 0, 0, 0, 0, 0, 0, 0, 0);
	            
	            //List<Float> res = trainArchitecture(fm.getOutputDir()+fm.getPyDir()+fm.getExpDir(), py_file, pythonFileName, fm.getNbArchi());
	            //float dt_result = res.get(0);
	            //float pc_result = res.get(1);
	            
	            solution.objectives()[0] = dt_result;
	            //solution.objectives()[0] = 0.0;
	            solution.objectives()[1] = -pc_result;
	            
			}else {
				solution.objectives()[0] = Float.MAX_VALUE;
				solution.objectives()[1] = Float.MAX_VALUE;
			}
			
			try {	
            	
            	writer = new FileWriter(fm.getOutputDir()+csvFile, true);
            	
	            if(!addHeader) {
	            	writer.append("generation,nom,dt,pc\n");
	            	addHeader = true;
	            }
	            
	            writer.append(fm.getExpDir()+","+ pythonFileName +","+ solution.objectives()[0]+"," + -solution.objectives()[1] +"\n");
				
			} catch (IOException e) {
				System.out.println(e);
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   
            
        }

		fm.setNbArchi(fm.getNbArchi()+1);
		return solution;
	}
	
	private int[] CountingLayers(ArchitectureGraphView archi) {
		int[] l = {0,0,0,0,0}; 
		for(Layer layer : archi.getGraph()){
			if (layer instanceof Convolution){
				l[0]++;
			}else if( layer instanceof Dense) {
				l[1]++;
			}else if( layer instanceof Interstice) {
				Interstice inters = (Interstice) layer;
				if(inters.getType().equals("global_avg_pooling") ) {
					l[2]++;
				}else if(inters.getType().equals("global_max_pooling")){
					l[3]++;
				}else {
					l[4]++;
				}
			}
		}
		
		return l;
	}

	public boolean testBuildArchitecture(String dir, String py_name){
		List<String> command = new ArrayList<>();
		command.add("python");
        command.add(py_name);
        
        
        ProcessBuilder pb = new ProcessBuilder(command);
	    pb.directory(new File(dir));
	    pb.redirectErrorStream(true);
	    // Start the process
	    Process process;
	    try {
			process = pb.start();
			
			BufferedReader breader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            
            while ((line = breader.readLine()) != null) {
                try {
                	System.out.println(line);
                	
                	if(line.equals("exception") ) {
                		return false;
                	}
                		
                }catch (Exception e) {
					System.out.println(e);
				}
            }
            int exitCode = process.waitFor();
            
            return true;
	    }catch (IOException | InterruptedException e) {
          return false;
      }
		
	}
	
	
//	public List<Float> trainArchitecture(String dir, String file_name, String py_name, int nb) {
//		List<Float> res = new ArrayList<>();
//		List<String> command = new ArrayList<>();
//		
//		
//        command.add("python");
//        command.add(py_name);
//        
//        ProcessBuilder pb = new ProcessBuilder(command);
//        pb.directory(new File(dir));
//        pb.redirectErrorStream(true);
//        // Start the process
//        Process process;
//		try {
//			process = pb.start();
//			
//			BufferedReader breader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            
//            while ((line = breader.readLine()) != null) {
//                try {
//                	System.out.println(line);
//                }catch (Exception e) {
//					System.out.println(e);
//				}
//            }   
//            
//			int exitCode = process.waitFor();
//			
//			String csv_dir = "architecture_csv/";
//			String emission_dir = "architecture_emissions/";
//			String python_dir = "architecture_py/";
//			
//			String csv_file = file_name.replace(python_dir, csv_dir).replace("_"+nb+".py", "_results.csv");
//			String emission_file = file_name.replace(python_dir, emission_dir).replace(".py", "_emissions.csv");
//			
//			CSVReader reader = new CSVReader(new FileReader(emission_file));
//	        List<String[]> raws = reader.readAll();
//
//	        String[] raw = raws.get(1);
//	        float emission = Float.parseFloat(raw[4]);
//	        
//		    reader = new CSVReader(new FileReader(csv_file));
//	        raws = reader.readAll();
//	        
//	        raw = raws.get(nb);
//	        float acc = Float.parseFloat(raw[4]);
//	        
//	        res.add(emission);
//	        res.add(acc);
//			
//		}catch (IOException | InterruptedException e) {
//            //e.printStackTrace();
//			res.add((float) 0);
//			res.add((float) 0);
//        }     
//        
//        return res;
//	}

	@Override
	public String name() {
		return this.name();
	}

	@Override
	public int numberOfConstraints() {
		return 0;
	}

	@Override
	public int numberOfObjectives() {
		return 2;
	}

	@Override
	public int numberOfVariables() {
		return 1;
	}
	
	
//	private float ExecutePythonPredictor(String model, String file_name, int epochs, int nb_params, int nb_layers, int flop) {
//		float result = 0;
//		try {
//			
//			// Path to the Conda environment activation script
//            String condaScript = "conda activate py39DL && ";
//            // Path to the Python file you want to execute
//            String pythonScript = "python pycode_predictor.py --model=" + model+ " --file_name=" + file_name + " --dt_param_epochs=" + epochs
//            		+ " --dt_param_flop=" + flop + " --dt_param_nblayers=" + nb_layers + " --dt_param_nbparams=" + nb_params;
//            
//            String command = condaScript + pythonScript;
//            
//            // Specify the command to execute the Python script
//            //
//            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
//            pb.redirectErrorStream(true);
//            // Start the process
//            Process process = pb.start();
//            
//            // Read the output from the process
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            
//            while ((line = reader.readLine()) != null) {
//                try {
//                	System.out.println(line);
//                	result = Float.parseFloat(line);
//                }catch (Exception e) {
//					//do nothing
//				}
//            }
//            
//            int exitCode = process.waitFor();
//                         
//		}catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//		return result;
//	}
	
	
	//version Lucia
	private float ExecutePythonPredictor(String model, String file_name, int nb_params, int nb_layers, int flop, 
			int nbConv, int nbDense, int nbGlobalAvgPooling, int nbGglobalMaxPooling, int nbFlatten) {
		float result = 0;
		try {
			
			List<String> command = new ArrayList<>();
	        command.add("python");
	        command.add("pycode_predictor.py");
	        command.add("--model=" + model);
	        command.add("--file_name=" + file_name);
	        command.add("--dt_param_flop=" + flop);
	        command.add("--dt_param_nblayers=" + nb_layers);
	        command.add("--dt_param_nbparams=" + nb_params);
	        command.add("--dt_param_convs=" + nbConv);
	        command.add("--dt_param_dense=" + nbDense);
	        command.add("--dt_param_gap=" + nbGlobalAvgPooling);
	        command.add("--dt_param_gmp=" + nbGglobalMaxPooling);
	        command.add("--dt_param_flatten=" + nbFlatten);
			            
            
            // Specify the command to execute the Python script
            //
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            // Start the process
            Process process = pb.start();
            
            // Read the output from the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            
            while ((line = reader.readLine()) != null) {
                try {
                	result = Float.parseFloat(line);
                }catch (Exception e) {
					//do nothing
				}
            }           
            
            int exitCode = process.waitFor();
                         
		}catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
		return result;
	}
}
