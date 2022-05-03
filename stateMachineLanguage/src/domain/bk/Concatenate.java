package domain.bk;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Concatenate extends Layer implements LayerInterface{
	
	
	public Concatenate(int layerPos) {
		super(layerPos);
		// TODO Auto-generated constructor stub
	}
	
	

}
