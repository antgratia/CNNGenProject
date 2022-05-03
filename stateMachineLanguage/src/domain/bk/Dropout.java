package domain.bk;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dropout extends Layer implements LayerInterface{
	
	private double dropoutRate;

	public Dropout(int layerPos) {
		super(layerPos);
	}

	
}
