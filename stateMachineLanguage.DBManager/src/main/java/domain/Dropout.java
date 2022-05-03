package domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NodeEntity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dropout extends Layer {
	
	private double dropoutRate;
	
	public Dropout(int layerPos) {
		this.layerPos = layerPos;
	}


}
