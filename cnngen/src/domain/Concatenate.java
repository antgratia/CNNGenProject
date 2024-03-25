package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Concatenate extends Layer {
	
	public Concatenate(int layerPos) {
		this.layerPos = layerPos;
	}

}
