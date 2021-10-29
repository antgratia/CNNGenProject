package utils;

import java.util.ArrayList;
import java.util.List;

public class GestionWay {
	private static String chara = "X";
	private int nb;
	private static List<String> listX;
	
	private GestionWay() {
		this.nb = 0;
	}
	
	public String getCurrent() {
		if(nb == 0)
			return listX.get(nb);
		else return listX.get(nb-1);
	}
	
	public String getNext() {
		return listX.get(nb);
	}
	
	public void add() {
		nb++;
		listX.add(chara+nb);
	}
	
	public String getXfromList(int index) {
		return listX.get(index);
	}
	
	public int getNbElem() {
		return nb;
	}
	
	public void removeLastFromList() {
		listX.remove(nb);
		nb--;
	}
	
	private static void init() {
		listX = new ArrayList<String>();
		listX.add(chara);
	}
	
	public static GestionWay getGestionWay() {
		init();
		return new GestionWay();
	}
	
	public List<String> getlist() {
		return listX;
	}
	
	public void removeBeforeLastFromList() {
		if(nb>=1) {
		listX.remove(nb-1);
		nb--;
		}
	}
	
	
	
	
	
	
	
	
	
}
