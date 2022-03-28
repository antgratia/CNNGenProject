package domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public abstract class Layer {

	@Id @GeneratedValue
	protected Long id;
	
	protected int layerPos;
	
	protected boolean reduction;
	protected boolean last;
	protected int imgSize;
	protected int nbFilter;
	
	public Layer(int layerPos, boolean reduction, boolean last, int imgSize, int nbFilter) {
		super();
		this.layerPos = layerPos;
		this.reduction = reduction;
		this.last = last;
		this.imgSize = imgSize;
		this.nbFilter = nbFilter;
		this.nextLayer = new ArrayList<>();
	}
	
	public Layer() {
		this.nextLayer = new ArrayList<>();
	}
	
	@Relationship(type = "Next", direction = Relationship.OUTGOING)
	protected List<Layer> nextLayer;
	
	
}
