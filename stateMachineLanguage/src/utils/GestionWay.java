package utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GestionWay {
	private static String chara = "X";
	private int nb;
	private static List<String> listX;
	
	public GestionWay() {
		this.nb = 0;
		listX = new ArrayList<String>();
		listX.add(chara);
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
	
	public void removeLastFromList() {
		listX.remove(nb);
		nb--;
	}
	
	public void removeBeforeLastFromList() {
		if(nb>=1) {
		listX.remove(nb-1);
		nb--;
		}
	}
	
	
	
	
	
	
	
	
	
}
