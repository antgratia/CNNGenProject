package domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Convolution extends Layer{
	
	private int kernel;
	private int stride;
	private String padding;
	private String fctActivation;

	public Convolution(int layerPos, boolean reduction, boolean last, int imgSize, int nbFilter, int kernel, int stride,
			String padding, String fctActivation) {
		super(layerPos, reduction, last, imgSize, nbFilter);
		this.kernel = kernel;
		this.stride = stride;
		this.padding = padding;
		this.fctActivation = fctActivation;
	}

	public Convolution(int layerPos) {
		this.layerPos = layerPos;
	}
	
	
}
