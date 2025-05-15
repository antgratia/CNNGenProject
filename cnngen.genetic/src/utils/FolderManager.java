package utils;

import java.io.File;

import lombok.Data;

@Data
public class FolderManager {
	
	private static FolderManager fm = null;
	
	private ProgramConfig pc;

	private String outputDir;

	private String pyDir;

	private String cnndlDir;

	private String logDir;

	private String pngDir;

	private String csvDir;

	private String tensorboardDir;

	private String emissionDir;

	//private String adjacencyDir;
	
	private String expDir;
	
	private int nbGen = 0;
	private int nbArchi = 0;
	
	protected FolderManager() {
		pc = new ProgramConfig();
		
		// init
		outputDir = pc.getOutputDir();
		pyDir = pc.getPyDir();
		cnndlDir = pc.getSMLDir();
		logDir = pc.getLogDir();
		pngDir = pc.getPngDir();
		csvDir = pc.getCSVDir();
		tensorboardDir = pc.getTensorboardDir();
		emissionDir = pc.getEmissionDir();
		//adjacencyDir = pc.getAdjacencyDir();
	}
	
	public static FolderManager getFolderManager() {
		if(fm == null)
			fm = new FolderManager();
		return fm;
	}
	
	public void increaseNbGen(){
		nbGen++;
	}
	
	// create all the require folder
	public void createFolder() {
		
		expDir = "generation_" + nbGen+ "/";
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
			
		//folder = new File(outputDir+adjacencyDir+expDir);
		//if(!folder.exists()) folder.mkdirs();
			
		}

}
