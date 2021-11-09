package models;

import java.util.ArrayList;
import java.util.List;

import utils.GestionHpp;
import xtext.sML.MergeNonRecu;
import xtext.sML.MergeRecu;

public class MergeSimple {

	private List<LayerInterface> left = new ArrayList<LayerInterface>();
	private List<LayerInterface> right = new ArrayList<LayerInterface>();
	private String add_or_concat;
	
	private GestionHpp gestionHpp = GestionHpp.getGestionHpp();
	
	public MergeSimple(MergeNonRecu mnr) {
		gestionHpp.gestionMergeNonRecu(mnr, this);
	}
	
	public MergeSimple(MergeRecu mr) {
		gestionHpp.gestionMergeRecu(mr, this);
	}

	public List<LayerInterface> getLeft() {
		return left;
	}

	public void setLeft(List<LayerInterface> left) {
		this.left = left;
	}

	public List<LayerInterface> getRight() {
		return right;
	}

	public void setRight(List<LayerInterface> right) {
		this.right = right;
	}

	public String getAdd_or_concat() {
		return add_or_concat;
	}

	public void setAdd_or_concat(String add_or_concat) {
		this.add_or_concat = add_or_concat;
	}

	public GestionHpp getGestionHpp() {
		return gestionHpp;
	}

	public void setGestionHpp(GestionHpp gestionHpp) {
		this.gestionHpp = gestionHpp;
	}
	
	public void addLeft(LayerInterface l) {
		this.left.add(l);
	}
	
	public void removeFirstLeft() {
		this.left.remove(0);
	}
	
	public void addRight(LayerInterface l ) {
		this.right.add(l);
	}
	
	public void removeFirstRight() {
		this.right.remove(0);
	}
	
}
