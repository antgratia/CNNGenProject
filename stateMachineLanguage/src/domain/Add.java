package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Add extends Layer implements LayerInterface{
	
	public Add(int layerPos) {
		super(layerPos);
		// TODO Auto-generated constructor stub
	}

	
}
