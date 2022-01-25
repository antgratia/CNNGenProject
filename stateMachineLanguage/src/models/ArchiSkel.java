package models;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xtext.sML.Architecture;
import xtext.sML.FeatureExtraction;
import xtext.sML.MergeBody;

@EqualsAndHashCode
@ToString
public class ArchiSkel {

	private static ArchiSkel archiSkel;
	
	@Getter @Setter int archiLocalisation;
	@Getter @Setter private List<LayerFct> skel = new ArrayList<LayerFct>();
	

	// layer isrecu nbrecu type redu
	
	private ArchiSkel() {
	}

	public void createSkel(Architecture archi) {
		for(FeatureExtraction fe: archi.getFe()) {
			if(fe.getConv() != null) {
				skel.add(new LayerFct(false, 0, TypeLayerEnum.C, true));
			}else {
				boolean isRecu = false;
				boolean isRedu = true;
				int nbRecu = 0;
				if(fe.getMerge().getMergeBody().size() == 1) {
					if(fe.getMerge().getMergeBody().get(0).getLeft().getCom().getMergeConv() != null) {
						isRecu=true;
						nbRecu++;
						isRedu=false;
						
						skel.add(new LayerFct(isRecu, nbRecu, TypeLayerEnum.RECU, isRedu));
					}else {
						if(fe.getMerge().getMergeBody().get(0).getRight().getEmpty() != null) {
							skel.add(new LayerFct(isRecu, nbRecu, TypeLayerEnum.RES_BLOCK, isRedu));
						}else {
							isRedu=false;
							skel.add(new LayerFct(isRecu, nbRecu, TypeLayerEnum.ID, isRedu));
						}
					}
				}else {
					for (MergeBody mb:fe.getMerge().getMergeBody()) {
						if(mb.getLeft().getCom().getMergeConv() != null) {
							isRecu=true;
							nbRecu++;
							isRedu=false;
						}else{
							if(mb.getRight().getEmpty() == null) isRedu=false;
						}
					}
					skel.add(new LayerFct(isRecu, nbRecu, TypeLayerEnum.H, isRedu));
				}
			}

			if(fe.getPool() != null)
				skel.add(new LayerFct(false, 0, TypeLayerEnum.P, true));
		}
	}
	
	public static ArchiSkel getArchiSkel() {
		if (archiSkel == null)
			archiSkel = new ArchiSkel();
		return archiSkel;
	}
	
	

}
