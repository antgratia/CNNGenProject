package domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dense extends Layer implements LayerInterface{

	private int units;
	private String fctActivation;
	
	public Dense(int layerPos) {
		super(layerPos);
		// TODO Auto-generated constructor stub
	}
	
	
}
