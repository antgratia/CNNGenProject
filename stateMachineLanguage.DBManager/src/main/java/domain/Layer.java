package domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = { "nextLayer", "prevLayer"})
@EqualsAndHashCode(exclude = { "nextLayer", "prevLayer"})
public abstract class Layer implements Comparable<Integer>{

	@Id @GeneratedValue
	protected Long id;
	
	protected int layerPos;
	
	protected boolean reduction;
	protected boolean last;
	protected int imgSize;
	protected int nbFilter;
	
	@Relationship(type = "Next", direction = Relationship.OUTGOING)
	protected List<Layer> nextLayer;
	
	@Relationship(type = "Previous", direction = Relationship.OUTGOING)
	protected List<Layer> prevLayer;
	
	public Layer(int layerPos, boolean reduction, boolean last, int imgSize, int nbFilter) {
		super();
		this.layerPos = layerPos;
		this.reduction = reduction;
		this.last = last;
		this.imgSize = imgSize;
		this.nbFilter = nbFilter;
		this.nextLayer = new ArrayList<>();
		this.prevLayer = new ArrayList<>();
	}
	
	public Layer() {
		this.nextLayer = new ArrayList<>();
		this.prevLayer = new ArrayList<>();
		this.reduction = true;
	}  
	

	@Override
	public int compareTo(Integer o) {
		if(this.layerPos == o) return 0; 
		else if(this.layerPos < 0) return 1;
		else return -1;
	}
	
	
}
