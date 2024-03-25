package domain;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = { "nextLayer", "prevLayer"})
@EqualsAndHashCode(exclude = { "nextLayer", "prevLayer"})
public abstract class Layer implements Comparable<Integer>{
	
	protected int layerPos;
	
	protected boolean reduction;
	protected boolean last;
	protected int inputImgSize;
	protected int outputImgSize;
	protected int inputFilter;
	protected int outputFilter;
	
	protected List<Layer> nextLayer;
	
	protected List<Layer> prevLayer;
	
	public Layer(int layerPos, boolean reduction, boolean last, int inputImgSize, int inputFilter) {
		super();
		this.layerPos = layerPos;
		this.reduction = reduction;
		this.last = last;
		this.inputImgSize = inputImgSize;
		this.inputFilter = inputFilter;
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
