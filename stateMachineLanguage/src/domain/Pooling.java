package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pooling extends Layer {
	

	private int kernel;
	private int stride;
	private String padding;
	
	public Pooling(int layerPos) {
		this.layerPos = layerPos;
	}
}
