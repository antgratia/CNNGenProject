package domain.bk;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import domain.Convolution;
import domain.Layer;
import domain.Pooling;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.GestionHpp;
import xtext.sML.Merge;
import xtext.sML.MergeBody;


@Data
public class MergeSimple {

	private List<LayerInterface> left = new ArrayList<LayerInterface>();
	private List<LayerInterface> right = new ArrayList<LayerInterface>();
	private List<Layer> leftLayer = new ArrayList<>();
	private List<Layer> rightLayer = new ArrayList<>();
	private String add_or_concat = "";
	
	@Setter(AccessLevel.PRIVATE)
	@Getter(AccessLevel.PRIVATE)
	private GestionHpp gestionHpp = GestionHpp.getGestionHpp();
	
	public MergeSimple(Merge merge) {
		gestionHpp.gestionMerge(merge, this);
		if(this.add_or_concat == "concat") gestionHpp.compressedFilter();
	}
	
	public MergeSimple(EList<MergeBody> listMergeBody) {
		for(MergeBody mb: listMergeBody) {
			gestionHpp.gestionMergeBody(mb, this, add_or_concat);
			if(this.add_or_concat == "concat") gestionHpp.compressedFilter();
		}
		
		
	}
	
	public void addLeft(LayerInterface l) {
		this.left.add(l);
	}
	
	public void removeFirstLeft() {
		if(left.size()>0) {
			this.left.remove(0);
		}else {
			this.leftLayer.remove(0);
		}
	}
	
	public void addRight(LayerInterface l ) {
		this.right.add(l);
	}
	
	public void removeFirstRight() {
		if(right.size()>0) {
			this.right.remove(0);
		}else {
			this.rightLayer.remove(0);
		}
		
	}

	public void addLeft(Layer layer) {
		this.leftLayer.add(layer);
	}

	public void addRight(Layer l) {
		this.rightLayer.add(l);
		
	}
	
}
