package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Output extends Layer implements LayerInterface {
	
	
	public Output(int layerPos) {
		super(layerPos);
	}
	
	
}
