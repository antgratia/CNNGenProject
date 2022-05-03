package domain.bk;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Convolution extends Layer implements LayerInterface{
	
	private int kernel;
	private int stride;
	private String padding;
	private String fct_activation;
	
	
	public Convolution(int layerPos) {
		super(layerPos);
	}
	
	
}
