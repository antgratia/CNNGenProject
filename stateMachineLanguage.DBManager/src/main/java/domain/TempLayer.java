package domain;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString (callSuper = true)
public class TempLayer extends Layer {

}
