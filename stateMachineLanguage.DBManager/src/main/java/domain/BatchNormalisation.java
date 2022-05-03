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
public class BatchNormalisation extends Layer {

	private double epsilon;
	
	public BatchNormalisation(int layerPos) {
		this.layerPos = layerPos;
	}
}
