package domain.bk;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Layer implements Comparable<LayerInterface>{
	
	protected int layerPos;
	
	protected boolean reduction;
	protected boolean last;
	protected int imgSize;
	protected int nbFilter;
	
	protected Layer(int layerPos) {
		this.layerPos = layerPos;
		this.reduction = true;
	}
	
	@Override
	public int compareTo(LayerInterface o) {
		if(this.layerPos == o.getLayerPos()) return 0;
		if(this.layerPos > o.getLayerPos()) return 1;
		return -1;
	}
	
}
