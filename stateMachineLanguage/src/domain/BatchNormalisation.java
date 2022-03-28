package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BatchNormalisation extends Layer implements LayerInterface{

	private double epsilon;

	public BatchNormalisation(int layerPos) {
		super(layerPos);
		// TODO Auto-generated constructor stub
	}
	
	
}
