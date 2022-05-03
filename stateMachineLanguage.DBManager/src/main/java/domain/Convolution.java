package domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Convolution extends Layer{
	
	private int kernel;
	private int stride;
	private String padding;
	private String fct_activation;

	public Convolution(int layerPos, boolean reduction, boolean last, int imgSize, int nbFilter, int kernel, int stride,
			String padding, String fct_activation) {
		super(layerPos, reduction, last, imgSize, nbFilter);
		this.kernel = kernel;
		this.stride = stride;
		this.padding = padding;
		this.fct_activation = fct_activation;
	}

	public Convolution(int layerPos) {
		this.layerPos = layerPos;
	}
	
	
}
