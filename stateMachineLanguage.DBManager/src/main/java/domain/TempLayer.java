package domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter @Setter
@NoArgsConstructor
@ToString (callSuper = true)
public class TempLayer extends Layer {

}
