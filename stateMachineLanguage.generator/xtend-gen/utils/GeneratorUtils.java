package utils;

import com.google.common.base.Objects;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import xtext.generator.SMLGenerator;
import xtext.sML.Architecture;
import xtext.sML.Classification;
import xtext.sML.ConvDrop;
import xtext.sML.ConvOrMerge;
import xtext.sML.Convolution;
import xtext.sML.FeatureExtraction;
import xtext.sML.FlattenOrGlobal;
import xtext.sML.Interstice;
import xtext.sML.Left;
import xtext.sML.Merge;
import xtext.sML.MergeBody;
import xtext.sML.MergeConv;
import xtext.sML.Right;
import xtext.sML.SML;
import xtext.sML.impl.SMLFactoryImpl;

@SuppressWarnings("all")
public class GeneratorUtils {
  private SMLFactoryImpl factory = new SMLFactoryImpl();
  
  private SMLGenerator smlGenerator = new SMLGenerator();
  
  private static Random rand = new Random();
  
  private static List<String> flatOrGLo = new ArrayList<String>(List.<String>of("flatten", "global_avg_pooling", "global_max_pooling"));
  
  private static List<String> poolOrNot = new ArrayList<String>(List.<String>of("", "avg_pooling", "max_pooling"));
  
  private static List<String> dropOrNot = new ArrayList<String>(List.<String>of("", "", "", "", "dropout"));
  
  private static List<String> convs = new ArrayList<String>(List.<String>of("conv", "convbn", "bnconv"));
  
  private static List<String> convOrMerge = new ArrayList<String>(List.<String>of("conv", "conv", "merge"));
  
  private static List<String> convOrEmpty = new ArrayList<String>(List.<String>of("conv", "Empty"));
  
  private static List<Integer> nbFeatureExtraction = new ArrayList<Integer>(List.<Integer>of(Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)));
  
  private static List<Integer> nbDense = new ArrayList<Integer>(List.<Integer>of(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)));
  
  private static List<Integer> nbOtherZero = new ArrayList<Integer>(List.<Integer>of(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)));
  
  private static List<Integer> nbOther = new ArrayList<Integer>(List.<Integer>of(Integer.valueOf(1), Integer.valueOf(2)));
  
  private CheckArchitectureValidity cav;
  
  public void generate(final String pyFilename, final String smlFilename, final String expDir, final String DBName) {
    try {
      SML sml = this.factory.createSML();
      Architecture archi = this.factory.createArchitecture();
      archi.setInput("input");
      archi.setOutput("output");
      sml.setSml(archi);
      Integer nbfe = GeneratorUtils.nbFeatureExtraction.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbFeatureExtraction.size()));
      for (int i = 0; (i < (nbfe).intValue()); i++) {
        archi.getFe().add(this.featureExtractionManagement());
      }
      archi.setInter(this.IntersticeManagement());
      Integer nbCla = GeneratorUtils.nbDense.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbDense.size()));
      for (int j = 0; (j < (nbCla).intValue()); j++) {
        archi.getClass_().add(this.classificationManagement());
      }
      CheckArchitectureValidity _checkArchitectureValidity = new CheckArchitectureValidity();
      this.cav = _checkArchitectureValidity;
      String strSML = this.cav.checkValidity(smlFilename, sml);
      PrintWriter writer = new PrintWriter(smlFilename, "UTF-8");
      writer.println(strSML);
      writer.close();
      this.smlGenerator.generate(sml, pyFilename, expDir, DBName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void generate(final String pyFilename, final String smlFilename, final String expDir, final String DBName, final String strSML) {
    try {
      CheckArchitectureValidity _checkArchitectureValidity = new CheckArchitectureValidity();
      this.cav = _checkArchitectureValidity;
      this.cav.checkValidity(strSML);
      ConvertSML csml = new ConvertSML();
      SML sml = csml.stringToSML(strSML);
      InputOutput.<String>println(smlFilename);
      PrintWriter writer = new PrintWriter(smlFilename, "UTF-8");
      writer.println(strSML);
      writer.close();
      this.smlGenerator.generate(sml, pyFilename, expDir, DBName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public FeatureExtraction featureExtractionManagement() {
    FeatureExtraction fe = this.factory.createFeatureExtraction();
    String strConvOrMerge = GeneratorUtils.convOrMerge.get(GeneratorUtils.rand.nextInt(GeneratorUtils.convOrMerge.size()));
    boolean _equals = Objects.equal(strConvOrMerge, "conv");
    if (_equals) {
      fe.setConv(this.convManagement());
    } else {
      fe.setMerge(this.mergeManagement(false));
    }
    String strDropOrNot = GeneratorUtils.dropOrNot.get(GeneratorUtils.rand.nextInt(GeneratorUtils.dropOrNot.size()));
    if ((strDropOrNot == "")) {
    } else {
      fe.setDrop(strDropOrNot);
    }
    String strPoolOrNot = GeneratorUtils.poolOrNot.get(GeneratorUtils.rand.nextInt(GeneratorUtils.poolOrNot.size()));
    if ((strPoolOrNot == "")) {
    } else {
      fe.setPool(strPoolOrNot);
    }
    return fe;
  }
  
  public Interstice IntersticeManagement() {
    Interstice inter = this.factory.createInterstice();
    FlattenOrGlobal flatorGlo = this.factory.createFlattenOrGlobal();
    String strflatOrGLo = GeneratorUtils.flatOrGLo.get(GeneratorUtils.rand.nextInt(GeneratorUtils.flatOrGLo.size()));
    boolean _equals = Objects.equal(strflatOrGLo, "flatten");
    if (_equals) {
      flatorGlo.setFlat(strflatOrGLo);
    } else {
      flatorGlo.setGp(strflatOrGLo);
    }
    inter.setFg(flatorGlo);
    return inter;
  }
  
  public Classification classificationManagement() {
    Classification classif = this.factory.createClassification();
    String strDropOrNot = GeneratorUtils.dropOrNot.get(GeneratorUtils.rand.nextInt(GeneratorUtils.dropOrNot.size()));
    if ((strDropOrNot == "")) {
    } else {
      classif.setDrop(strDropOrNot);
    }
    classif.setD("dense");
    return classif;
  }
  
  public Convolution convManagement() {
    String strConv = GeneratorUtils.convs.get(GeneratorUtils.rand.nextInt(GeneratorUtils.convs.size()));
    Convolution conv = this.factory.createConvolution();
    boolean _equals = Objects.equal(strConv, "conv");
    if (_equals) {
      conv.setConv(strConv);
    } else {
      boolean _equals_1 = Objects.equal(strConv, "bnconv");
      if (_equals_1) {
        conv.setBnconv(strConv);
      } else {
        conv.setConvbn(strConv);
      }
    }
    return conv;
  }
  
  public Merge mergeManagement(final boolean recu) {
    Merge merge = this.factory.createMerge();
    if (recu) {
      merge.getMergeBody().add(this.mergeBodyManagement(1));
    } else {
      Integer nbMerge = GeneratorUtils.nbOther.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbOther.size()));
      for (int i = 0; (i < (nbMerge).intValue()); i++) {
        merge.getMergeBody().add(this.mergeBodyManagement((nbMerge).intValue()));
      }
    }
    return merge;
  }
  
  public MergeBody mergeBodyManagement(final int nbMerge) {
    MergeBody mergeBody = this.factory.createMergeBody();
    mergeBody.setLeft(this.leftManagement());
    mergeBody.setRight(this.rightManagement(nbMerge));
    return mergeBody;
  }
  
  public Left leftManagement() {
    Left left = this.factory.createLeft();
    String strPoolOrNot = GeneratorUtils.poolOrNot.get(GeneratorUtils.rand.nextInt(GeneratorUtils.poolOrNot.size()));
    if ((strPoolOrNot == "")) {
    } else {
      left.setP(strPoolOrNot);
    }
    left.setCom(this.convOrMergeManagement());
    strPoolOrNot = GeneratorUtils.poolOrNot.get(GeneratorUtils.rand.nextInt(GeneratorUtils.poolOrNot.size()));
    if ((strPoolOrNot == "")) {
    } else {
      left.setPool(strPoolOrNot);
    }
    return left;
  }
  
  public Right rightManagement(final int nbMerge) {
    Right right = this.factory.createRight();
    String strConvOrEmpty = "";
    if ((nbMerge > 1)) {
      strConvOrEmpty = "conv";
    } else {
      strConvOrEmpty = GeneratorUtils.convOrEmpty.get(GeneratorUtils.rand.nextInt(GeneratorUtils.convOrEmpty.size()));
    }
    Integer nb = GeneratorUtils.nbOther.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbOther.size()));
    if ((strConvOrEmpty == "conv")) {
      for (int i = 0; (i < (nb).intValue()); i++) {
        right.getConv().add(this.convManagement());
      }
    } else {
      right.setEmpty(strConvOrEmpty);
    }
    return right;
  }
  
  public ConvOrMerge convOrMergeManagement() {
    ConvOrMerge com = this.factory.createConvOrMerge();
    String strConvOrMerge = GeneratorUtils.convOrMerge.get(GeneratorUtils.rand.nextInt(GeneratorUtils.convOrMerge.size()));
    boolean _equals = Objects.equal(strConvOrMerge, "conv");
    if (_equals) {
      Integer nb = GeneratorUtils.nbOther.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbOther.size()));
      for (int i = 0; (i < (nb).intValue()); i++) {
        com.getConvdrop().add(this.convDropManagement());
      }
    } else {
      Integer nbConv = GeneratorUtils.nbOther.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbOther.size()));
      Integer nbMerge = GeneratorUtils.nbOther.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbOther.size()));
      for (int i = 0; (i < (nbConv).intValue()); i++) {
        com.getConvdrop().add(this.convDropManagement());
      }
      for (int i = 0; (i < (nbMerge).intValue()); i++) {
        com.getMergeConv().add(this.mergeConvManagement());
      }
    }
    return com;
  }
  
  public ConvDrop convDropManagement() {
    ConvDrop convDrop = this.factory.createConvDrop();
    convDrop.setConv(this.convManagement());
    String strDropOrNot = GeneratorUtils.dropOrNot.get(GeneratorUtils.rand.nextInt(GeneratorUtils.dropOrNot.size()));
    if ((strDropOrNot == "")) {
    } else {
      convDrop.setDrop(strDropOrNot);
    }
    return convDrop;
  }
  
  public MergeConv mergeConvManagement() {
    MergeConv mc = this.factory.createMergeConv();
    Object _mergeManagement = this.mergeManagement(true);
    mc.setMerge(((Merge) _mergeManagement));
    Integer nbConv = GeneratorUtils.nbOtherZero.get(GeneratorUtils.rand.nextInt(GeneratorUtils.nbOtherZero.size()));
    for (int i = 0; (i < (nbConv).intValue()); i++) {
      mc.getConvdrop().add(this.convDropManagement());
    }
    return mc;
  }
}
