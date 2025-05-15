package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Interstice extends Layer {
	
	private String type;
	
	public Interstice(int layerPos, String type) {
		this.layerPos = layerPos;
		this.type = type;
	}

}
