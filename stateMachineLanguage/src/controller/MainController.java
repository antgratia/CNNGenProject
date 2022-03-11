package controller;

import lombok.Data;
import models.ArchitectureGraph;
import utils.GestionHpp;
import utils.GestionWay;
import xtext.sML.Architecture;

@Data
public class MainController implements LayerController{

	private GestionHpp gestionHpp;
	private ArchitectureGraph graph;
	private GestionWay gestionWay;
	
	public MainController () {
		gestionHpp = new GestionHpp();
		gestionWay = new GestionWay();
		graph = new ArchitectureGraph();
	}
	
	public void createGraph(Architecture archi) {	
		try {
			graph.createGraph(archi);
		} catch (Exception e) {
			System.out.println("MainController : createGraph error");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void getGraphHyperparameters() {
		try {
			gestionHpp.setGraphHPP(graph);
		} catch (Exception e) {
			System.out.println("MainController : getGraphHyperparameters error");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
