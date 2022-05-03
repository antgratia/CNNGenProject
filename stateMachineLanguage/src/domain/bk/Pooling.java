package domain.bk;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pooling extends Layer implements LayerInterface{
	
	private int kernel;
	private int stride;
	private String padding;
	
	
	public Pooling(int layerPos) {
		super(layerPos);
		// TODO Auto-generated constructor stub
	}
	
	
}
