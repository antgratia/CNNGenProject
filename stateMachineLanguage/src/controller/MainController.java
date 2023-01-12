package controller;

import lombok.Data;
import utils.GestionHPPNeo4j;
import utils.GestionWay;
import views.ArchitectureGraphView;

@Data
public class MainController {

	private GestionHPPNeo4j gestionHPPNeo4j;
	private GestionWay gestionWay;
	private ArchitectureGraphView graphview;
	
	public MainController (String dbName) {
		gestionWay = new GestionWay();
		graphview = new ArchitectureGraphView();
		graphview.init(dbName);
		gestionHPPNeo4j = new GestionHPPNeo4j();
	}
	
	public MainController () {
		gestionWay = new GestionWay();
	}

}
