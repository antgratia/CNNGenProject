package operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import org.uma.jmetal.operator.mutation.MutationOperator;

import generation.GeneratorGeneticUtils;
import genetic.AccEnergySolution;
import utils.CheckArchitectureValidity;
import xtext.cNNDescLang.Architecture;
import xtext.cNNDescLang.CNNDescLang;
import xtext.cNNDescLang.Classification;
import xtext.cNNDescLang.ConvDrop;
import xtext.cNNDescLang.Convolution;
import xtext.cNNDescLang.FeatureExtraction;
import xtext.cNNDescLang.FlattenOrGlobal;
import xtext.cNNDescLang.Interstice;
import xtext.cNNDescLang.Merge;
import xtext.cNNDescLang.MergeBody;
import xtext.cNNDescLang.MergeConv;
import xtext.cNNDescLang.impl.CNNDescLangFactoryImpl;

@SuppressWarnings("serial")
public class GrammarMutationOperator implements MutationOperator<AccEnergySolution>{

	private final double probability;
	private CNNDescLangFactoryImpl factory = new CNNDescLangFactoryImpl();
	private GeneratorGeneticUtils ggu = new GeneratorGeneticUtils();
	CheckArchitectureValidity cav = new CheckArchitectureValidity();
	
	public GrammarMutationOperator(double probability) throws Exception {
		if(probability < 0) throw new Exception("probability < 0");
		this.probability = probability;
	}
	
	@Override
	public AccEnergySolution execute(AccEnergySolution solution) {
		Random r = new Random();
		for (int i = 0; i < solution.variables().size(); i++) {
			if (r.nextInt(101) < probability) {
				CNNDescLang cnndl = solution.variables().get(i);
				CNNDescLang newCnndl = mutate(cnndl);
				solution.variables().clear();
				solution.variables().add(newCnndl);
			}
		}
		
		return solution;
	}

	private CNNDescLang mutate(CNNDescLang cnndl) {
		CNNDescLang mutatedCnndl = cnndl;
		Random rd = new Random();
		
		List<String> possibleOp = getOperation(cnndl.getCnndesclang());
		
		int randomIndex = rd.nextInt(possibleOp.size());
		if(possibleOp.get(randomIndex).equals("gp")) {
			Interstice inter = mutatedCnndl.getCnndesclang().getInter();
			if(inter.getFg().getFlat() != null) {
				FlattenOrGlobal flatorGlo = factory.createFlattenOrGlobal();
				if(rd.nextInt(101)<50) {
					flatorGlo.setGp("global_avg_pooling");
				}else {
					flatorGlo.setGp("global_max_pooling");
				}
				inter.setFg(flatorGlo);
				
			}else if(inter.getFg().getGp().equals("global_avg_pooling")) {
				FlattenOrGlobal flatorGlo = factory.createFlattenOrGlobal();
				if(rd.nextInt(101)<50) {
					flatorGlo.setFlat("flatten");
				}else {
					flatorGlo.setGp("global_max_pooling");
				}
				inter.setFg(flatorGlo);
				
			}else if (inter.getFg().getGp().equals("global_max_pooling")) {
				FlattenOrGlobal flatorGlo = factory.createFlattenOrGlobal();
				if(rd.nextInt(101)<50) {
					flatorGlo.setFlat("flatten");
				}else {
					flatorGlo.setGp("global_avg_pooling");
				}
				inter.setFg(flatorGlo);
				
			}else {
				System.out.println("grammarmutation mutate : error " + possibleOp.get(randomIndex));
			}
		}else {
			if(rd.nextInt(101)>60 && (cnndl.getCnndesclang().getFe().size()>1 && cnndl.getCnndesclang().getClass_().size()>1)) {
				// remove
				if(possibleOp.get(randomIndex).equals("conv")) {
					mutatedCnndl.getCnndesclang().getFe().remove(randomIndex);
				}else if (possibleOp.get(randomIndex).equals("merge")) {
					mutatedCnndl.getCnndesclang().getFe().remove(randomIndex);
				}else if (possibleOp.get(randomIndex).equals("dense")) {
					int indexdeleted = randomIndex - (possibleOp.size()-2-mutatedCnndl.getCnndesclang().getClass_().size());
					mutatedCnndl.getCnndesclang().getClass_().remove(indexdeleted);
				}else if (possibleOp.get(randomIndex).equals("drop")) {
					int i = 0;
					boolean removedDrop = false;
					while(i < mutatedCnndl.getCnndesclang().getFe().size() && !removedDrop) {
						FeatureExtraction fe = mutatedCnndl.getCnndesclang().getFe().get(i);
						if(fe.getDrop() != null) {
							fe.setDrop(null);
							removedDrop = true;
						}else if (fe.getMerge() != null ){
							int j = 0;
							while(j < fe.getMerge().getMergeBody().size() && !removedDrop) {
								MergeBody mergebody = fe.getMerge().getMergeBody().get(j);
								int x = 0;
								while(x < mergebody.getLeft().getCom().getConvdrop().size() && !removedDrop) {
									ConvDrop convdrop = mergebody.getLeft().getCom().getConvdrop().get(x);
									if(convdrop.getDrop() != null) {
										convdrop.setDrop(null);
										removedDrop=true;
									}
									x++;
								}
								
								x = 0;
								while(x < mergebody.getLeft().getCom().getMergeConv().size() && !removedDrop) {
									MergeConv mergeconv = mergebody.getLeft().getCom().getMergeConv().get(x);
									int y = 0;
									while(y < mergeconv.getConvdrop().size() && !removedDrop) {
										ConvDrop convdrop = mergeconv.getConvdrop().get(y);
										if(convdrop.getDrop() != null) {
											convdrop.setDrop(null);
											removedDrop=true;
										}
										y++;
									}
									x++;
								}								
								j++;
							}
								
						}else {
						 // do nothing
						}
						
						i++;
					}
					
					if(!removedDrop) {
						i = 0;
						while(i< mutatedCnndl.getCnndesclang().getClass_().size() && !removedDrop) {
							Classification dense = mutatedCnndl.getCnndesclang().getClass_().get(i);
							if(dense.getD() != null) {
								dense.setD(null);
								removedDrop = true;
							}
						}
					}
				}else if (possibleOp.get(randomIndex).equals("pool")) {
					int i = 0;
					boolean removedPool = false;
					while(i < mutatedCnndl.getCnndesclang().getFe().size() && !removedPool) {
						FeatureExtraction fe = mutatedCnndl.getCnndesclang().getFe().get(i);
						if(fe.getPool() != null) {
							fe.setPool(null);
							removedPool = true;
						}else if (fe.getMerge() != null ){
							int j = 0;
							while(j < fe.getMerge().getMergeBody().size() && !removedPool) {
								MergeBody mergebody = fe.getMerge().getMergeBody().get(j);
								if(mergebody.getLeft().getP() != null) {
									mergebody.getLeft().setP(null);
									removedPool = true;
								}else if (mergebody.getLeft().getPool() != null) {
									mergebody.getLeft().setPool(null);
									removedPool = true;
								}else {
								 // do nothing
								}
								j++;
							}
						}else {
							//do nothing
						}
						i++;
					}
						
				}
				
			}else {
				// add
				possibleOp = Arrays.asList("conv", "convbn", "bnconv", "dropout", "avg_pooling", "max_pooling", "dense", "merge");
				randomIndex = rd.nextInt(possibleOp.size());
				if(possibleOp.get(randomIndex).equals("conv") || possibleOp.get(randomIndex).equals("convbn") || possibleOp.get(randomIndex).equals("bnconv")) {
					int feInsertIndex = rd.nextInt(mutatedCnndl.getCnndesclang().getFe().size());
					FeatureExtraction feInsert = factory.createFeatureExtraction();
					feInsert.setConv(convManagement(possibleOp.get(randomIndex)));
					mutatedCnndl.getCnndesclang().getFe().add(feInsertIndex, feInsert);
				}else if (possibleOp.get(randomIndex).equals("dropout")) {
					int feInsertIndex = rd.nextInt(mutatedCnndl.getCnndesclang().getFe().size());
					FeatureExtraction feInsert = mutatedCnndl.getCnndesclang().getFe().get(feInsertIndex);
					if(feInsert.getDrop() == null)
						feInsert.setDrop(possibleOp.get(randomIndex));
					else if (feInsert.getMerge() != null) {
						int i = 0;
						boolean insertDrop = false;
						while(!insertDrop && i < feInsert.getMerge().getMergeBody().size()) {
							MergeBody mergebody = feInsert.getMerge().getMergeBody().get(i);
							int j =0;
							while(!insertDrop && j < mergebody.getLeft().getCom().getConvdrop().size()) {
								ConvDrop convdrop = mergebody.getLeft().getCom().getConvdrop().get(j);
								if(convdrop.getDrop() == null) {
									convdrop.setDrop(possibleOp.get(randomIndex));
									insertDrop = true;
								}
									
								j++;
							}
							while(!insertDrop && j < mergebody.getLeft().getCom().getMergeConv().size()) {
								 MergeConv mergeconv = mergebody.getLeft().getCom().getMergeConv().get(j);
								 int x =0;
									while(!insertDrop && x < mergeconv.getConvdrop().size()) {
										ConvDrop convdrop = mergeconv.getConvdrop().get(x);
										if(convdrop.getDrop() == null) {
											convdrop.setDrop(possibleOp.get(randomIndex));
											insertDrop = true;
										}
											
										x++;
									}
									
								j++;
							}
							
							i++;
						}
					}
				}else if(possibleOp.get(randomIndex).equals("avg_pooling") || possibleOp.get(randomIndex).equals("max_pooling")) {
					int feInsertIndex = rd.nextInt(mutatedCnndl.getCnndesclang().getFe().size());
					FeatureExtraction feInsert = mutatedCnndl.getCnndesclang().getFe().get(feInsertIndex);
					boolean insertPool = false;
					if(feInsert.getPool() == null)
						feInsert.setPool(possibleOp.get(randomIndex));
					else if (feInsert.getMerge() != null ){
						int j = 0;
						while(j < feInsert.getMerge().getMergeBody().size() && !insertPool) {
							MergeBody mergebody = feInsert.getMerge().getMergeBody().get(j);
							if(mergebody.getLeft().getP() == null) {
								mergebody.getLeft().setP(possibleOp.get(randomIndex));
								insertPool = true;
							}else if (mergebody.getLeft().getPool() == null) {
								mergebody.getLeft().setPool(possibleOp.get(randomIndex));
								insertPool = true;
							}else {
							 // do nothing
							}
							j++;
						}
					}
				}else if(possibleOp.get(randomIndex).equals("dense")) {
					int classInsertIndex = rd.nextInt(mutatedCnndl.getCnndesclang().getClass_().size());
					Classification classif = factory.createClassification();
					classif.setD(possibleOp.get(randomIndex));
					mutatedCnndl.getCnndesclang().getClass_().add(classInsertIndex, classif);
				}else if (possibleOp.get(randomIndex).equals("merge")) {
					int feInsertIndex = rd.nextInt(mutatedCnndl.getCnndesclang().getFe().size());
					FeatureExtraction feInsert = factory.createFeatureExtraction();
					Merge merge = ggu.mergeManagement(false);
					feInsert.setMerge(merge);
					mutatedCnndl.getCnndesclang().getFe().add(feInsertIndex, feInsert);
					
				}else {
					System.out.println("grammarmutation mutate : error add" + possibleOp.get(randomIndex));
				}
			}
		}
		
		System.out.println("\nmutation verification:");
		
		try {
			cav.checkValidity(mutatedCnndl);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		return mutatedCnndl;
	}

	@Override
	public double mutationProbability() {
		return this.probability;
	}
	
	private List<String> getOperation(Architecture archi) {
		List<String> listop = new ArrayList<>();
		for(FeatureExtraction fe : archi.getFe()) {
			if(fe.getConv() != null) {
				listop.add("conv");
			}else if(fe.getMerge() != null) {
				listop.add("merge");
			}else {
				System.out.println("getoperation : error");
			}
		}
		listop.add("gp");
		for(Classification dense : archi.getClass_()) {
			if(dense.getD() != null) {
				listop.add("dense");
			}
		}
		listop.add("drop");
		listop.add("pool");
		return listop;
	}
	
	private Convolution convManagement(String strConv){
		
		Convolution conv = factory.createConvolution();
		if(strConv == "conv"){
			conv.setConv(strConv);
		}else if (strConv == "bnconv"){
			conv.setBnconv(strConv);
			
		}else {
			conv.setConvbn(strConv);
		}
		return conv;
	}
	
	

}
