package controller;



import lombok.Data;
import utils.GestionHPP;
import utils.GestionWay;
import utils.ProgramConfig;
import views.ArchitectureGraphView;

@Data
public class MainController {

	private GestionHPP gestionHPP;
	private GestionWay gestionWay;
	private ArchitectureGraphView graphview;
	
	public MainController (ProgramConfig programConfig) {
		gestionWay = new GestionWay();
		graphview = new ArchitectureGraphView();
		//graphview.init(dbName);
		gestionHPP = new GestionHPP(programConfig);
	}
	
	public MainController () {
		gestionWay = new GestionWay();
	}

}
