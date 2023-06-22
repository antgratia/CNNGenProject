package utils;

import com.google.common.base.Objects;
import java.io.PrintWriter;
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
  
  private static List<String> flatOrGLo;
  
  private static List<String> poolOrNot;
  
  private static List<String> dropOrNot;
  
  private static List<String> convs;
  
  private static List<String> convOrMerge;
  
  private static List<String> convOrEmpty;
  
  private static List<Integer> nbFeatureExtraction;
  
  private static List<Integer> nbDense;
  
  private static List<Integer> nbOtherZero;
  
  private static List<Integer> nbOther;
  
  private CheckArchitectureValidity cav;
  
  public List<Integer> getconfig(final ProgramConfig programConfig) {
    List<Integer> _xblockexpression = null;
    {
      GeneratorUtils.flatOrGLo = programConfig.getFlattenOrGlobalPool();
      GeneratorUtils.poolOrNot = programConfig.getPoolingOrNot();
      GeneratorUtils.dropOrNot = programConfig.getDropout();
      GeneratorUtils.convs = programConfig.getConvolution();
      GeneratorUtils.convOrEmpty = programConfig.getConvOrEmpty();
      GeneratorUtils.convOrMerge = programConfig.getConvOrMerge();
      GeneratorUtils.nbFeatureExtraction = programConfig.getNbFeatureExtraction();
      GeneratorUtils.nbDense = programConfig.getNbDense();
      GeneratorUtils.nbOtherZero = programConfig.getNbOtherZero();
      _xblockexpression = GeneratorUtils.nbOther = programConfig.getNbOther();
    }
    return _xblockexpression;
  }
  
  public void generate(final String pyFilename, final String smlFilename, final String expDir, final String DBName, final ProgramConfig programConfig) {
    try {
      this.getconfig(programConfig);
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
      this.smlGenerator.generate(sml, pyFilename, expDir, DBName, programConfig);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void generate(final String pyFilename, final String smlFilename, final String expDir, final String DBName, final String strSML, final ProgramConfig programConfig) {
    try {
      this.getconfig(programConfig);
      CheckArchitectureValidity _checkArchitectureValidity = new CheckArchitectureValidity();
      this.cav = _checkArchitectureValidity;
      this.cav.checkValidity(strSML);
      ConvertSML csml = new ConvertSML();
      SML sml = csml.stringToSML(strSML);
      InputOutput.<String>println(smlFilename);
      PrintWriter writer = new PrintWriter(smlFilename, "UTF-8");
      writer.println(strSML);
      writer.close();
      this.smlGenerator.generate(sml, pyFilename, expDir, DBName, programConfig);
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
