package xtext.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import xtext.services.SMLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSMLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'input'", "'output'", "'dropout'", "'avg_pooling'", "'max_pooling'", "'bnconv'", "'convbn'", "'conv'", "'upconv'", "'global_avg_pooling'", "'global_max_pooling'", "'flatten'", "'dense'", "'Empty'", "'('", "','", "')'", "'['", "']'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalSMLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSMLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSMLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalSML.g"; }



     	private SMLGrammarAccess grammarAccess;

        public InternalSMLParser(TokenStream input, SMLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "SML";
       	}

       	@Override
       	protected SMLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleSML"
    // InternalSML.g:64:1: entryRuleSML returns [EObject current=null] : iv_ruleSML= ruleSML EOF ;
    public final EObject entryRuleSML() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSML = null;


        try {
            // InternalSML.g:64:44: (iv_ruleSML= ruleSML EOF )
            // InternalSML.g:65:2: iv_ruleSML= ruleSML EOF
            {
             newCompositeNode(grammarAccess.getSMLRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSML=ruleSML();

            state._fsp--;

             current =iv_ruleSML; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSML"


    // $ANTLR start "ruleSML"
    // InternalSML.g:71:1: ruleSML returns [EObject current=null] : ( (lv_sml_0_0= ruleArchitecture ) ) ;
    public final EObject ruleSML() throws RecognitionException {
        EObject current = null;

        EObject lv_sml_0_0 = null;



        	enterRule();

        try {
            // InternalSML.g:77:2: ( ( (lv_sml_0_0= ruleArchitecture ) ) )
            // InternalSML.g:78:2: ( (lv_sml_0_0= ruleArchitecture ) )
            {
            // InternalSML.g:78:2: ( (lv_sml_0_0= ruleArchitecture ) )
            // InternalSML.g:79:3: (lv_sml_0_0= ruleArchitecture )
            {
            // InternalSML.g:79:3: (lv_sml_0_0= ruleArchitecture )
            // InternalSML.g:80:4: lv_sml_0_0= ruleArchitecture
            {

            				newCompositeNode(grammarAccess.getSMLAccess().getSmlArchitectureParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_sml_0_0=ruleArchitecture();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getSMLRule());
            				}
            				set(
            					current,
            					"sml",
            					lv_sml_0_0,
            					"xtext.SML.Architecture");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSML"


    // $ANTLR start "entryRuleArchitecture"
    // InternalSML.g:100:1: entryRuleArchitecture returns [EObject current=null] : iv_ruleArchitecture= ruleArchitecture EOF ;
    public final EObject entryRuleArchitecture() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArchitecture = null;


        try {
            // InternalSML.g:100:53: (iv_ruleArchitecture= ruleArchitecture EOF )
            // InternalSML.g:101:2: iv_ruleArchitecture= ruleArchitecture EOF
            {
             newCompositeNode(grammarAccess.getArchitectureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArchitecture=ruleArchitecture();

            state._fsp--;

             current =iv_ruleArchitecture; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArchitecture"


    // $ANTLR start "ruleArchitecture"
    // InternalSML.g:107:1: ruleArchitecture returns [EObject current=null] : ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+ )? ( (lv_output_4_0= 'output' ) ) ) ;
    public final EObject ruleArchitecture() throws RecognitionException {
        EObject current = null;

        Token lv_input_0_0=null;
        Token lv_output_4_0=null;
        EObject lv_fe_1_0 = null;

        EObject lv_inter_2_0 = null;

        EObject lv_class_3_0 = null;



        	enterRule();

        try {
            // InternalSML.g:113:2: ( ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+ )? ( (lv_output_4_0= 'output' ) ) ) )
            // InternalSML.g:114:2: ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+ )? ( (lv_output_4_0= 'output' ) ) )
            {
            // InternalSML.g:114:2: ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+ )? ( (lv_output_4_0= 'output' ) ) )
            // InternalSML.g:115:3: ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+ )? ( (lv_output_4_0= 'output' ) )
            {
            // InternalSML.g:115:3: ( (lv_input_0_0= 'input' ) )
            // InternalSML.g:116:4: (lv_input_0_0= 'input' )
            {
            // InternalSML.g:116:4: (lv_input_0_0= 'input' )
            // InternalSML.g:117:5: lv_input_0_0= 'input'
            {
            lv_input_0_0=(Token)match(input,11,FOLLOW_3); 

            					newLeafNode(lv_input_0_0, grammarAccess.getArchitectureAccess().getInputInputKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getArchitectureRule());
            					}
            					setWithLastConsumed(current, "input", lv_input_0_0, "input");
            				

            }


            }

            // InternalSML.g:129:3: ( (lv_fe_1_0= ruleFeatureExtraction ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=16 && LA1_0<=19)||LA1_0==28) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalSML.g:130:4: (lv_fe_1_0= ruleFeatureExtraction )
            	    {
            	    // InternalSML.g:130:4: (lv_fe_1_0= ruleFeatureExtraction )
            	    // InternalSML.g:131:5: lv_fe_1_0= ruleFeatureExtraction
            	    {

            	    					newCompositeNode(grammarAccess.getArchitectureAccess().getFeFeatureExtractionParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_fe_1_0=ruleFeatureExtraction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getArchitectureRule());
            	    					}
            	    					add(
            	    						current,
            	    						"fe",
            	    						lv_fe_1_0,
            	    						"xtext.SML.FeatureExtraction");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // InternalSML.g:148:3: ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+ )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=20 && LA3_0<=22)) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalSML.g:149:4: ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )+
                    {
                    // InternalSML.g:149:4: ( (lv_inter_2_0= ruleInterstice ) )
                    // InternalSML.g:150:5: (lv_inter_2_0= ruleInterstice )
                    {
                    // InternalSML.g:150:5: (lv_inter_2_0= ruleInterstice )
                    // InternalSML.g:151:6: lv_inter_2_0= ruleInterstice
                    {

                    						newCompositeNode(grammarAccess.getArchitectureAccess().getInterIntersticeParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_5);
                    lv_inter_2_0=ruleInterstice();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getArchitectureRule());
                    						}
                    						set(
                    							current,
                    							"inter",
                    							lv_inter_2_0,
                    							"xtext.SML.Interstice");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalSML.g:168:4: ( (lv_class_3_0= ruleClassification ) )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==13||LA2_0==23) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // InternalSML.g:169:5: (lv_class_3_0= ruleClassification )
                    	    {
                    	    // InternalSML.g:169:5: (lv_class_3_0= ruleClassification )
                    	    // InternalSML.g:170:6: lv_class_3_0= ruleClassification
                    	    {

                    	    						newCompositeNode(grammarAccess.getArchitectureAccess().getClassClassificationParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_6);
                    	    lv_class_3_0=ruleClassification();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getArchitectureRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"class",
                    	    							lv_class_3_0,
                    	    							"xtext.SML.Classification");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);


                    }
                    break;

            }

            // InternalSML.g:188:3: ( (lv_output_4_0= 'output' ) )
            // InternalSML.g:189:4: (lv_output_4_0= 'output' )
            {
            // InternalSML.g:189:4: (lv_output_4_0= 'output' )
            // InternalSML.g:190:5: lv_output_4_0= 'output'
            {
            lv_output_4_0=(Token)match(input,12,FOLLOW_2); 

            					newLeafNode(lv_output_4_0, grammarAccess.getArchitectureAccess().getOutputOutputKeyword_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getArchitectureRule());
            					}
            					setWithLastConsumed(current, "output", lv_output_4_0, "output");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArchitecture"


    // $ANTLR start "entryRuleDropout"
    // InternalSML.g:206:1: entryRuleDropout returns [String current=null] : iv_ruleDropout= ruleDropout EOF ;
    public final String entryRuleDropout() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDropout = null;


        try {
            // InternalSML.g:206:47: (iv_ruleDropout= ruleDropout EOF )
            // InternalSML.g:207:2: iv_ruleDropout= ruleDropout EOF
            {
             newCompositeNode(grammarAccess.getDropoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDropout=ruleDropout();

            state._fsp--;

             current =iv_ruleDropout.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDropout"


    // $ANTLR start "ruleDropout"
    // InternalSML.g:213:1: ruleDropout returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'dropout' ;
    public final AntlrDatatypeRuleToken ruleDropout() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:219:2: (kw= 'dropout' )
            // InternalSML.g:220:2: kw= 'dropout'
            {
            kw=(Token)match(input,13,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getDropoutAccess().getDropoutKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDropout"


    // $ANTLR start "entryRulePooling"
    // InternalSML.g:228:1: entryRulePooling returns [String current=null] : iv_rulePooling= rulePooling EOF ;
    public final String entryRulePooling() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePooling = null;


        try {
            // InternalSML.g:228:47: (iv_rulePooling= rulePooling EOF )
            // InternalSML.g:229:2: iv_rulePooling= rulePooling EOF
            {
             newCompositeNode(grammarAccess.getPoolingRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePooling=rulePooling();

            state._fsp--;

             current =iv_rulePooling.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePooling"


    // $ANTLR start "rulePooling"
    // InternalSML.g:235:1: rulePooling returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'avg_pooling' | kw= 'max_pooling' ) ;
    public final AntlrDatatypeRuleToken rulePooling() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:241:2: ( (kw= 'avg_pooling' | kw= 'max_pooling' ) )
            // InternalSML.g:242:2: (kw= 'avg_pooling' | kw= 'max_pooling' )
            {
            // InternalSML.g:242:2: (kw= 'avg_pooling' | kw= 'max_pooling' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==14) ) {
                alt4=1;
            }
            else if ( (LA4_0==15) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalSML.g:243:3: kw= 'avg_pooling'
                    {
                    kw=(Token)match(input,14,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPoolingAccess().getAvg_poolingKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalSML.g:249:3: kw= 'max_pooling'
                    {
                    kw=(Token)match(input,15,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPoolingAccess().getMax_poolingKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePooling"


    // $ANTLR start "entryRuleConvolution"
    // InternalSML.g:258:1: entryRuleConvolution returns [EObject current=null] : iv_ruleConvolution= ruleConvolution EOF ;
    public final EObject entryRuleConvolution() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConvolution = null;


        try {
            // InternalSML.g:258:52: (iv_ruleConvolution= ruleConvolution EOF )
            // InternalSML.g:259:2: iv_ruleConvolution= ruleConvolution EOF
            {
             newCompositeNode(grammarAccess.getConvolutionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConvolution=ruleConvolution();

            state._fsp--;

             current =iv_ruleConvolution; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConvolution"


    // $ANTLR start "ruleConvolution"
    // InternalSML.g:265:1: ruleConvolution returns [EObject current=null] : ( ( (lv_bnconv_0_0= 'bnconv' ) ) | ( (lv_convbn_1_0= 'convbn' ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) ) ;
    public final EObject ruleConvolution() throws RecognitionException {
        EObject current = null;

        Token lv_bnconv_0_0=null;
        Token lv_convbn_1_0=null;
        Token lv_conv_2_0=null;
        Token lv_upconv_3_0=null;


        	enterRule();

        try {
            // InternalSML.g:271:2: ( ( ( (lv_bnconv_0_0= 'bnconv' ) ) | ( (lv_convbn_1_0= 'convbn' ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) ) )
            // InternalSML.g:272:2: ( ( (lv_bnconv_0_0= 'bnconv' ) ) | ( (lv_convbn_1_0= 'convbn' ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) )
            {
            // InternalSML.g:272:2: ( ( (lv_bnconv_0_0= 'bnconv' ) ) | ( (lv_convbn_1_0= 'convbn' ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) )
            int alt5=4;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt5=1;
                }
                break;
            case 17:
                {
                alt5=2;
                }
                break;
            case 18:
                {
                alt5=3;
                }
                break;
            case 19:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalSML.g:273:3: ( (lv_bnconv_0_0= 'bnconv' ) )
                    {
                    // InternalSML.g:273:3: ( (lv_bnconv_0_0= 'bnconv' ) )
                    // InternalSML.g:274:4: (lv_bnconv_0_0= 'bnconv' )
                    {
                    // InternalSML.g:274:4: (lv_bnconv_0_0= 'bnconv' )
                    // InternalSML.g:275:5: lv_bnconv_0_0= 'bnconv'
                    {
                    lv_bnconv_0_0=(Token)match(input,16,FOLLOW_2); 

                    					newLeafNode(lv_bnconv_0_0, grammarAccess.getConvolutionAccess().getBnconvBnconvKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConvolutionRule());
                    					}
                    					setWithLastConsumed(current, "bnconv", lv_bnconv_0_0, "bnconv");
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSML.g:288:3: ( (lv_convbn_1_0= 'convbn' ) )
                    {
                    // InternalSML.g:288:3: ( (lv_convbn_1_0= 'convbn' ) )
                    // InternalSML.g:289:4: (lv_convbn_1_0= 'convbn' )
                    {
                    // InternalSML.g:289:4: (lv_convbn_1_0= 'convbn' )
                    // InternalSML.g:290:5: lv_convbn_1_0= 'convbn'
                    {
                    lv_convbn_1_0=(Token)match(input,17,FOLLOW_2); 

                    					newLeafNode(lv_convbn_1_0, grammarAccess.getConvolutionAccess().getConvbnConvbnKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConvolutionRule());
                    					}
                    					setWithLastConsumed(current, "convbn", lv_convbn_1_0, "convbn");
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSML.g:303:3: ( (lv_conv_2_0= 'conv' ) )
                    {
                    // InternalSML.g:303:3: ( (lv_conv_2_0= 'conv' ) )
                    // InternalSML.g:304:4: (lv_conv_2_0= 'conv' )
                    {
                    // InternalSML.g:304:4: (lv_conv_2_0= 'conv' )
                    // InternalSML.g:305:5: lv_conv_2_0= 'conv'
                    {
                    lv_conv_2_0=(Token)match(input,18,FOLLOW_2); 

                    					newLeafNode(lv_conv_2_0, grammarAccess.getConvolutionAccess().getConvConvKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConvolutionRule());
                    					}
                    					setWithLastConsumed(current, "conv", lv_conv_2_0, "conv");
                    				

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSML.g:318:3: ( (lv_upconv_3_0= 'upconv' ) )
                    {
                    // InternalSML.g:318:3: ( (lv_upconv_3_0= 'upconv' ) )
                    // InternalSML.g:319:4: (lv_upconv_3_0= 'upconv' )
                    {
                    // InternalSML.g:319:4: (lv_upconv_3_0= 'upconv' )
                    // InternalSML.g:320:5: lv_upconv_3_0= 'upconv'
                    {
                    lv_upconv_3_0=(Token)match(input,19,FOLLOW_2); 

                    					newLeafNode(lv_upconv_3_0, grammarAccess.getConvolutionAccess().getUpconvUpconvKeyword_3_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConvolutionRule());
                    					}
                    					setWithLastConsumed(current, "upconv", lv_upconv_3_0, "upconv");
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConvolution"


    // $ANTLR start "entryRuleGlobalPooling"
    // InternalSML.g:336:1: entryRuleGlobalPooling returns [String current=null] : iv_ruleGlobalPooling= ruleGlobalPooling EOF ;
    public final String entryRuleGlobalPooling() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleGlobalPooling = null;


        try {
            // InternalSML.g:336:53: (iv_ruleGlobalPooling= ruleGlobalPooling EOF )
            // InternalSML.g:337:2: iv_ruleGlobalPooling= ruleGlobalPooling EOF
            {
             newCompositeNode(grammarAccess.getGlobalPoolingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalPooling=ruleGlobalPooling();

            state._fsp--;

             current =iv_ruleGlobalPooling.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGlobalPooling"


    // $ANTLR start "ruleGlobalPooling"
    // InternalSML.g:343:1: ruleGlobalPooling returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'global_avg_pooling' | kw= 'global_max_pooling' ) ;
    public final AntlrDatatypeRuleToken ruleGlobalPooling() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:349:2: ( (kw= 'global_avg_pooling' | kw= 'global_max_pooling' ) )
            // InternalSML.g:350:2: (kw= 'global_avg_pooling' | kw= 'global_max_pooling' )
            {
            // InternalSML.g:350:2: (kw= 'global_avg_pooling' | kw= 'global_max_pooling' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==20) ) {
                alt6=1;
            }
            else if ( (LA6_0==21) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalSML.g:351:3: kw= 'global_avg_pooling'
                    {
                    kw=(Token)match(input,20,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getGlobalPoolingAccess().getGlobal_avg_poolingKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalSML.g:357:3: kw= 'global_max_pooling'
                    {
                    kw=(Token)match(input,21,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getGlobalPoolingAccess().getGlobal_max_poolingKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGlobalPooling"


    // $ANTLR start "entryRuleFlattenOrGlobal"
    // InternalSML.g:366:1: entryRuleFlattenOrGlobal returns [EObject current=null] : iv_ruleFlattenOrGlobal= ruleFlattenOrGlobal EOF ;
    public final EObject entryRuleFlattenOrGlobal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFlattenOrGlobal = null;


        try {
            // InternalSML.g:366:56: (iv_ruleFlattenOrGlobal= ruleFlattenOrGlobal EOF )
            // InternalSML.g:367:2: iv_ruleFlattenOrGlobal= ruleFlattenOrGlobal EOF
            {
             newCompositeNode(grammarAccess.getFlattenOrGlobalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFlattenOrGlobal=ruleFlattenOrGlobal();

            state._fsp--;

             current =iv_ruleFlattenOrGlobal; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFlattenOrGlobal"


    // $ANTLR start "ruleFlattenOrGlobal"
    // InternalSML.g:373:1: ruleFlattenOrGlobal returns [EObject current=null] : ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) ) ;
    public final EObject ruleFlattenOrGlobal() throws RecognitionException {
        EObject current = null;

        Token lv_flat_0_0=null;
        AntlrDatatypeRuleToken lv_gp_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:379:2: ( ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) ) )
            // InternalSML.g:380:2: ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) )
            {
            // InternalSML.g:380:2: ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==22) ) {
                alt7=1;
            }
            else if ( ((LA7_0>=20 && LA7_0<=21)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalSML.g:381:3: ( (lv_flat_0_0= 'flatten' ) )
                    {
                    // InternalSML.g:381:3: ( (lv_flat_0_0= 'flatten' ) )
                    // InternalSML.g:382:4: (lv_flat_0_0= 'flatten' )
                    {
                    // InternalSML.g:382:4: (lv_flat_0_0= 'flatten' )
                    // InternalSML.g:383:5: lv_flat_0_0= 'flatten'
                    {
                    lv_flat_0_0=(Token)match(input,22,FOLLOW_2); 

                    					newLeafNode(lv_flat_0_0, grammarAccess.getFlattenOrGlobalAccess().getFlatFlattenKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getFlattenOrGlobalRule());
                    					}
                    					setWithLastConsumed(current, "flat", lv_flat_0_0, "flatten");
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSML.g:396:3: ( (lv_gp_1_0= ruleGlobalPooling ) )
                    {
                    // InternalSML.g:396:3: ( (lv_gp_1_0= ruleGlobalPooling ) )
                    // InternalSML.g:397:4: (lv_gp_1_0= ruleGlobalPooling )
                    {
                    // InternalSML.g:397:4: (lv_gp_1_0= ruleGlobalPooling )
                    // InternalSML.g:398:5: lv_gp_1_0= ruleGlobalPooling
                    {

                    					newCompositeNode(grammarAccess.getFlattenOrGlobalAccess().getGpGlobalPoolingParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_gp_1_0=ruleGlobalPooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFlattenOrGlobalRule());
                    					}
                    					set(
                    						current,
                    						"gp",
                    						lv_gp_1_0,
                    						"xtext.SML.GlobalPooling");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFlattenOrGlobal"


    // $ANTLR start "entryRuleInterstice"
    // InternalSML.g:419:1: entryRuleInterstice returns [EObject current=null] : iv_ruleInterstice= ruleInterstice EOF ;
    public final EObject entryRuleInterstice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterstice = null;


        try {
            // InternalSML.g:419:51: (iv_ruleInterstice= ruleInterstice EOF )
            // InternalSML.g:420:2: iv_ruleInterstice= ruleInterstice EOF
            {
             newCompositeNode(grammarAccess.getIntersticeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInterstice=ruleInterstice();

            state._fsp--;

             current =iv_ruleInterstice; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterstice"


    // $ANTLR start "ruleInterstice"
    // InternalSML.g:426:1: ruleInterstice returns [EObject current=null] : ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) ;
    public final EObject ruleInterstice() throws RecognitionException {
        EObject current = null;

        EObject lv_fg_0_0 = null;



        	enterRule();

        try {
            // InternalSML.g:432:2: ( ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) )
            // InternalSML.g:433:2: ( (lv_fg_0_0= ruleFlattenOrGlobal ) )
            {
            // InternalSML.g:433:2: ( (lv_fg_0_0= ruleFlattenOrGlobal ) )
            // InternalSML.g:434:3: (lv_fg_0_0= ruleFlattenOrGlobal )
            {
            // InternalSML.g:434:3: (lv_fg_0_0= ruleFlattenOrGlobal )
            // InternalSML.g:435:4: lv_fg_0_0= ruleFlattenOrGlobal
            {

            				newCompositeNode(grammarAccess.getIntersticeAccess().getFgFlattenOrGlobalParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_fg_0_0=ruleFlattenOrGlobal();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getIntersticeRule());
            				}
            				set(
            					current,
            					"fg",
            					lv_fg_0_0,
            					"xtext.SML.FlattenOrGlobal");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInterstice"


    // $ANTLR start "entryRuleClassification"
    // InternalSML.g:455:1: entryRuleClassification returns [EObject current=null] : iv_ruleClassification= ruleClassification EOF ;
    public final EObject entryRuleClassification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassification = null;


        try {
            // InternalSML.g:455:55: (iv_ruleClassification= ruleClassification EOF )
            // InternalSML.g:456:2: iv_ruleClassification= ruleClassification EOF
            {
             newCompositeNode(grammarAccess.getClassificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClassification=ruleClassification();

            state._fsp--;

             current =iv_ruleClassification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassification"


    // $ANTLR start "ruleClassification"
    // InternalSML.g:462:1: ruleClassification returns [EObject current=null] : ( ( (lv_drop_0_0= ruleDropout ) )? ( (lv_d_1_0= 'dense' ) ) ) ;
    public final EObject ruleClassification() throws RecognitionException {
        EObject current = null;

        Token lv_d_1_0=null;
        AntlrDatatypeRuleToken lv_drop_0_0 = null;



        	enterRule();

        try {
            // InternalSML.g:468:2: ( ( ( (lv_drop_0_0= ruleDropout ) )? ( (lv_d_1_0= 'dense' ) ) ) )
            // InternalSML.g:469:2: ( ( (lv_drop_0_0= ruleDropout ) )? ( (lv_d_1_0= 'dense' ) ) )
            {
            // InternalSML.g:469:2: ( ( (lv_drop_0_0= ruleDropout ) )? ( (lv_d_1_0= 'dense' ) ) )
            // InternalSML.g:470:3: ( (lv_drop_0_0= ruleDropout ) )? ( (lv_d_1_0= 'dense' ) )
            {
            // InternalSML.g:470:3: ( (lv_drop_0_0= ruleDropout ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==13) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalSML.g:471:4: (lv_drop_0_0= ruleDropout )
                    {
                    // InternalSML.g:471:4: (lv_drop_0_0= ruleDropout )
                    // InternalSML.g:472:5: lv_drop_0_0= ruleDropout
                    {

                    					newCompositeNode(grammarAccess.getClassificationAccess().getDropDropoutParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_7);
                    lv_drop_0_0=ruleDropout();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getClassificationRule());
                    					}
                    					set(
                    						current,
                    						"drop",
                    						lv_drop_0_0,
                    						"xtext.SML.Dropout");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalSML.g:489:3: ( (lv_d_1_0= 'dense' ) )
            // InternalSML.g:490:4: (lv_d_1_0= 'dense' )
            {
            // InternalSML.g:490:4: (lv_d_1_0= 'dense' )
            // InternalSML.g:491:5: lv_d_1_0= 'dense'
            {
            lv_d_1_0=(Token)match(input,23,FOLLOW_2); 

            					newLeafNode(lv_d_1_0, grammarAccess.getClassificationAccess().getDDenseKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClassificationRule());
            					}
            					setWithLastConsumed(current, "d", lv_d_1_0, "dense");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassification"


    // $ANTLR start "entryRuleConvDrop"
    // InternalSML.g:507:1: entryRuleConvDrop returns [EObject current=null] : iv_ruleConvDrop= ruleConvDrop EOF ;
    public final EObject entryRuleConvDrop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConvDrop = null;


        try {
            // InternalSML.g:507:49: (iv_ruleConvDrop= ruleConvDrop EOF )
            // InternalSML.g:508:2: iv_ruleConvDrop= ruleConvDrop EOF
            {
             newCompositeNode(grammarAccess.getConvDropRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConvDrop=ruleConvDrop();

            state._fsp--;

             current =iv_ruleConvDrop; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConvDrop"


    // $ANTLR start "ruleConvDrop"
    // InternalSML.g:514:1: ruleConvDrop returns [EObject current=null] : ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? ) ;
    public final EObject ruleConvDrop() throws RecognitionException {
        EObject current = null;

        EObject lv_conv_0_0 = null;

        AntlrDatatypeRuleToken lv_drop_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:520:2: ( ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? ) )
            // InternalSML.g:521:2: ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            {
            // InternalSML.g:521:2: ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            // InternalSML.g:522:3: ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )?
            {
            // InternalSML.g:522:3: ( (lv_conv_0_0= ruleConvolution ) )
            // InternalSML.g:523:4: (lv_conv_0_0= ruleConvolution )
            {
            // InternalSML.g:523:4: (lv_conv_0_0= ruleConvolution )
            // InternalSML.g:524:5: lv_conv_0_0= ruleConvolution
            {

            					newCompositeNode(grammarAccess.getConvDropAccess().getConvConvolutionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_8);
            lv_conv_0_0=ruleConvolution();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConvDropRule());
            					}
            					set(
            						current,
            						"conv",
            						lv_conv_0_0,
            						"xtext.SML.Convolution");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:541:3: ( (lv_drop_1_0= ruleDropout ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==13) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalSML.g:542:4: (lv_drop_1_0= ruleDropout )
                    {
                    // InternalSML.g:542:4: (lv_drop_1_0= ruleDropout )
                    // InternalSML.g:543:5: lv_drop_1_0= ruleDropout
                    {

                    					newCompositeNode(grammarAccess.getConvDropAccess().getDropDropoutParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_drop_1_0=ruleDropout();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConvDropRule());
                    					}
                    					set(
                    						current,
                    						"drop",
                    						lv_drop_1_0,
                    						"xtext.SML.Dropout");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConvDrop"


    // $ANTLR start "entryRuleMergeConv"
    // InternalSML.g:564:1: entryRuleMergeConv returns [EObject current=null] : iv_ruleMergeConv= ruleMergeConv EOF ;
    public final EObject entryRuleMergeConv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMergeConv = null;


        try {
            // InternalSML.g:564:50: (iv_ruleMergeConv= ruleMergeConv EOF )
            // InternalSML.g:565:2: iv_ruleMergeConv= ruleMergeConv EOF
            {
             newCompositeNode(grammarAccess.getMergeConvRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMergeConv=ruleMergeConv();

            state._fsp--;

             current =iv_ruleMergeConv; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMergeConv"


    // $ANTLR start "ruleMergeConv"
    // InternalSML.g:571:1: ruleMergeConv returns [EObject current=null] : ( ( (lv_merge_0_0= ruleMerge ) ) ( (lv_convdrop_1_0= ruleConvDrop ) )* ) ;
    public final EObject ruleMergeConv() throws RecognitionException {
        EObject current = null;

        EObject lv_merge_0_0 = null;

        EObject lv_convdrop_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:577:2: ( ( ( (lv_merge_0_0= ruleMerge ) ) ( (lv_convdrop_1_0= ruleConvDrop ) )* ) )
            // InternalSML.g:578:2: ( ( (lv_merge_0_0= ruleMerge ) ) ( (lv_convdrop_1_0= ruleConvDrop ) )* )
            {
            // InternalSML.g:578:2: ( ( (lv_merge_0_0= ruleMerge ) ) ( (lv_convdrop_1_0= ruleConvDrop ) )* )
            // InternalSML.g:579:3: ( (lv_merge_0_0= ruleMerge ) ) ( (lv_convdrop_1_0= ruleConvDrop ) )*
            {
            // InternalSML.g:579:3: ( (lv_merge_0_0= ruleMerge ) )
            // InternalSML.g:580:4: (lv_merge_0_0= ruleMerge )
            {
            // InternalSML.g:580:4: (lv_merge_0_0= ruleMerge )
            // InternalSML.g:581:5: lv_merge_0_0= ruleMerge
            {

            					newCompositeNode(grammarAccess.getMergeConvAccess().getMergeMergeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_9);
            lv_merge_0_0=ruleMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeConvRule());
            					}
            					set(
            						current,
            						"merge",
            						lv_merge_0_0,
            						"xtext.SML.Merge");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:598:3: ( (lv_convdrop_1_0= ruleConvDrop ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=16 && LA10_0<=19)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalSML.g:599:4: (lv_convdrop_1_0= ruleConvDrop )
            	    {
            	    // InternalSML.g:599:4: (lv_convdrop_1_0= ruleConvDrop )
            	    // InternalSML.g:600:5: lv_convdrop_1_0= ruleConvDrop
            	    {

            	    					newCompositeNode(grammarAccess.getMergeConvAccess().getConvdropConvDropParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_convdrop_1_0=ruleConvDrop();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMergeConvRule());
            	    					}
            	    					add(
            	    						current,
            	    						"convdrop",
            	    						lv_convdrop_1_0,
            	    						"xtext.SML.ConvDrop");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMergeConv"


    // $ANTLR start "entryRuleConvOrMerge"
    // InternalSML.g:621:1: entryRuleConvOrMerge returns [EObject current=null] : iv_ruleConvOrMerge= ruleConvOrMerge EOF ;
    public final EObject entryRuleConvOrMerge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConvOrMerge = null;


        try {
            // InternalSML.g:621:52: (iv_ruleConvOrMerge= ruleConvOrMerge EOF )
            // InternalSML.g:622:2: iv_ruleConvOrMerge= ruleConvOrMerge EOF
            {
             newCompositeNode(grammarAccess.getConvOrMergeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConvOrMerge=ruleConvOrMerge();

            state._fsp--;

             current =iv_ruleConvOrMerge; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConvOrMerge"


    // $ANTLR start "ruleConvOrMerge"
    // InternalSML.g:628:1: ruleConvOrMerge returns [EObject current=null] : ( ( (lv_convdrop_0_0= ruleConvDrop ) )+ | ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ ) ) ;
    public final EObject ruleConvOrMerge() throws RecognitionException {
        EObject current = null;

        EObject lv_convdrop_0_0 = null;

        EObject lv_convdrop_1_0 = null;

        EObject lv_mergeConv_2_0 = null;



        	enterRule();

        try {
            // InternalSML.g:634:2: ( ( ( (lv_convdrop_0_0= ruleConvDrop ) )+ | ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ ) ) )
            // InternalSML.g:635:2: ( ( (lv_convdrop_0_0= ruleConvDrop ) )+ | ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ ) )
            {
            // InternalSML.g:635:2: ( ( (lv_convdrop_0_0= ruleConvDrop ) )+ | ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ ) )
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // InternalSML.g:636:3: ( (lv_convdrop_0_0= ruleConvDrop ) )+
                    {
                    // InternalSML.g:636:3: ( (lv_convdrop_0_0= ruleConvDrop ) )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>=16 && LA11_0<=19)) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalSML.g:637:4: (lv_convdrop_0_0= ruleConvDrop )
                    	    {
                    	    // InternalSML.g:637:4: (lv_convdrop_0_0= ruleConvDrop )
                    	    // InternalSML.g:638:5: lv_convdrop_0_0= ruleConvDrop
                    	    {

                    	    					newCompositeNode(grammarAccess.getConvOrMergeAccess().getConvdropConvDropParserRuleCall_0_0());
                    	    				
                    	    pushFollow(FOLLOW_9);
                    	    lv_convdrop_0_0=ruleConvDrop();

                    	    state._fsp--;


                    	    					if (current==null) {
                    	    						current = createModelElementForParent(grammarAccess.getConvOrMergeRule());
                    	    					}
                    	    					add(
                    	    						current,
                    	    						"convdrop",
                    	    						lv_convdrop_0_0,
                    	    						"xtext.SML.ConvDrop");
                    	    					afterParserOrEnumRuleCall();
                    	    				

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalSML.g:656:3: ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ )
                    {
                    // InternalSML.g:656:3: ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ )
                    // InternalSML.g:657:4: ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+
                    {
                    // InternalSML.g:657:4: ( (lv_convdrop_1_0= ruleConvDrop ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>=16 && LA12_0<=19)) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalSML.g:658:5: (lv_convdrop_1_0= ruleConvDrop )
                    	    {
                    	    // InternalSML.g:658:5: (lv_convdrop_1_0= ruleConvDrop )
                    	    // InternalSML.g:659:6: lv_convdrop_1_0= ruleConvDrop
                    	    {

                    	    						newCompositeNode(grammarAccess.getConvOrMergeAccess().getConvdropConvDropParserRuleCall_1_0_0());
                    	    					
                    	    pushFollow(FOLLOW_3);
                    	    lv_convdrop_1_0=ruleConvDrop();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getConvOrMergeRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"convdrop",
                    	    							lv_convdrop_1_0,
                    	    							"xtext.SML.ConvDrop");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // InternalSML.g:676:4: ( (lv_mergeConv_2_0= ruleMergeConv ) )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==28) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalSML.g:677:5: (lv_mergeConv_2_0= ruleMergeConv )
                    	    {
                    	    // InternalSML.g:677:5: (lv_mergeConv_2_0= ruleMergeConv )
                    	    // InternalSML.g:678:6: lv_mergeConv_2_0= ruleMergeConv
                    	    {

                    	    						newCompositeNode(grammarAccess.getConvOrMergeAccess().getMergeConvMergeConvParserRuleCall_1_1_0());
                    	    					
                    	    pushFollow(FOLLOW_10);
                    	    lv_mergeConv_2_0=ruleMergeConv();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getConvOrMergeRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"mergeConv",
                    	    							lv_mergeConv_2_0,
                    	    							"xtext.SML.MergeConv");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConvOrMerge"


    // $ANTLR start "entryRuleLeft"
    // InternalSML.g:700:1: entryRuleLeft returns [EObject current=null] : iv_ruleLeft= ruleLeft EOF ;
    public final EObject entryRuleLeft() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLeft = null;


        try {
            // InternalSML.g:700:45: (iv_ruleLeft= ruleLeft EOF )
            // InternalSML.g:701:2: iv_ruleLeft= ruleLeft EOF
            {
             newCompositeNode(grammarAccess.getLeftRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLeft=ruleLeft();

            state._fsp--;

             current =iv_ruleLeft; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLeft"


    // $ANTLR start "ruleLeft"
    // InternalSML.g:707:1: ruleLeft returns [EObject current=null] : ( ( (lv_p_0_0= rulePooling ) )? ( (lv_com_1_0= ruleConvOrMerge ) ) ( (lv_pool_2_0= rulePooling ) )? ) ;
    public final EObject ruleLeft() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_p_0_0 = null;

        EObject lv_com_1_0 = null;

        AntlrDatatypeRuleToken lv_pool_2_0 = null;



        	enterRule();

        try {
            // InternalSML.g:713:2: ( ( ( (lv_p_0_0= rulePooling ) )? ( (lv_com_1_0= ruleConvOrMerge ) ) ( (lv_pool_2_0= rulePooling ) )? ) )
            // InternalSML.g:714:2: ( ( (lv_p_0_0= rulePooling ) )? ( (lv_com_1_0= ruleConvOrMerge ) ) ( (lv_pool_2_0= rulePooling ) )? )
            {
            // InternalSML.g:714:2: ( ( (lv_p_0_0= rulePooling ) )? ( (lv_com_1_0= ruleConvOrMerge ) ) ( (lv_pool_2_0= rulePooling ) )? )
            // InternalSML.g:715:3: ( (lv_p_0_0= rulePooling ) )? ( (lv_com_1_0= ruleConvOrMerge ) ) ( (lv_pool_2_0= rulePooling ) )?
            {
            // InternalSML.g:715:3: ( (lv_p_0_0= rulePooling ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=14 && LA15_0<=15)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalSML.g:716:4: (lv_p_0_0= rulePooling )
                    {
                    // InternalSML.g:716:4: (lv_p_0_0= rulePooling )
                    // InternalSML.g:717:5: lv_p_0_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getLeftAccess().getPPoolingParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_3);
                    lv_p_0_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getLeftRule());
                    					}
                    					set(
                    						current,
                    						"p",
                    						lv_p_0_0,
                    						"xtext.SML.Pooling");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalSML.g:734:3: ( (lv_com_1_0= ruleConvOrMerge ) )
            // InternalSML.g:735:4: (lv_com_1_0= ruleConvOrMerge )
            {
            // InternalSML.g:735:4: (lv_com_1_0= ruleConvOrMerge )
            // InternalSML.g:736:5: lv_com_1_0= ruleConvOrMerge
            {

            					newCompositeNode(grammarAccess.getLeftAccess().getComConvOrMergeParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_11);
            lv_com_1_0=ruleConvOrMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLeftRule());
            					}
            					set(
            						current,
            						"com",
            						lv_com_1_0,
            						"xtext.SML.ConvOrMerge");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:753:3: ( (lv_pool_2_0= rulePooling ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=14 && LA16_0<=15)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalSML.g:754:4: (lv_pool_2_0= rulePooling )
                    {
                    // InternalSML.g:754:4: (lv_pool_2_0= rulePooling )
                    // InternalSML.g:755:5: lv_pool_2_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getLeftAccess().getPoolPoolingParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_pool_2_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getLeftRule());
                    					}
                    					set(
                    						current,
                    						"pool",
                    						lv_pool_2_0,
                    						"xtext.SML.Pooling");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLeft"


    // $ANTLR start "entryRuleRight"
    // InternalSML.g:776:1: entryRuleRight returns [EObject current=null] : iv_ruleRight= ruleRight EOF ;
    public final EObject entryRuleRight() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRight = null;


        try {
            // InternalSML.g:776:46: (iv_ruleRight= ruleRight EOF )
            // InternalSML.g:777:2: iv_ruleRight= ruleRight EOF
            {
             newCompositeNode(grammarAccess.getRightRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRight=ruleRight();

            state._fsp--;

             current =iv_ruleRight; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRight"


    // $ANTLR start "ruleRight"
    // InternalSML.g:783:1: ruleRight returns [EObject current=null] : ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () ( (lv_empty_2_0= 'Empty' ) ) ) ) ;
    public final EObject ruleRight() throws RecognitionException {
        EObject current = null;

        Token lv_empty_2_0=null;
        EObject lv_conv_0_0 = null;



        	enterRule();

        try {
            // InternalSML.g:789:2: ( ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () ( (lv_empty_2_0= 'Empty' ) ) ) ) )
            // InternalSML.g:790:2: ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () ( (lv_empty_2_0= 'Empty' ) ) ) )
            {
            // InternalSML.g:790:2: ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () ( (lv_empty_2_0= 'Empty' ) ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=16 && LA18_0<=19)) ) {
                alt18=1;
            }
            else if ( (LA18_0==24) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalSML.g:791:3: ( (lv_conv_0_0= ruleConvolution ) )+
                    {
                    // InternalSML.g:791:3: ( (lv_conv_0_0= ruleConvolution ) )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>=16 && LA17_0<=19)) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalSML.g:792:4: (lv_conv_0_0= ruleConvolution )
                    	    {
                    	    // InternalSML.g:792:4: (lv_conv_0_0= ruleConvolution )
                    	    // InternalSML.g:793:5: lv_conv_0_0= ruleConvolution
                    	    {

                    	    					newCompositeNode(grammarAccess.getRightAccess().getConvConvolutionParserRuleCall_0_0());
                    	    				
                    	    pushFollow(FOLLOW_9);
                    	    lv_conv_0_0=ruleConvolution();

                    	    state._fsp--;


                    	    					if (current==null) {
                    	    						current = createModelElementForParent(grammarAccess.getRightRule());
                    	    					}
                    	    					add(
                    	    						current,
                    	    						"conv",
                    	    						lv_conv_0_0,
                    	    						"xtext.SML.Convolution");
                    	    					afterParserOrEnumRuleCall();
                    	    				

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalSML.g:811:3: ( () ( (lv_empty_2_0= 'Empty' ) ) )
                    {
                    // InternalSML.g:811:3: ( () ( (lv_empty_2_0= 'Empty' ) ) )
                    // InternalSML.g:812:4: () ( (lv_empty_2_0= 'Empty' ) )
                    {
                    // InternalSML.g:812:4: ()
                    // InternalSML.g:813:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getRightAccess().getRightAction_1_0(),
                    						current);
                    				

                    }

                    // InternalSML.g:819:4: ( (lv_empty_2_0= 'Empty' ) )
                    // InternalSML.g:820:5: (lv_empty_2_0= 'Empty' )
                    {
                    // InternalSML.g:820:5: (lv_empty_2_0= 'Empty' )
                    // InternalSML.g:821:6: lv_empty_2_0= 'Empty'
                    {
                    lv_empty_2_0=(Token)match(input,24,FOLLOW_2); 

                    						newLeafNode(lv_empty_2_0, grammarAccess.getRightAccess().getEmptyEmptyKeyword_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getRightRule());
                    						}
                    						setWithLastConsumed(current, "empty", lv_empty_2_0, "Empty");
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRight"


    // $ANTLR start "entryRuleMergeBody"
    // InternalSML.g:838:1: entryRuleMergeBody returns [EObject current=null] : iv_ruleMergeBody= ruleMergeBody EOF ;
    public final EObject entryRuleMergeBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMergeBody = null;


        try {
            // InternalSML.g:838:50: (iv_ruleMergeBody= ruleMergeBody EOF )
            // InternalSML.g:839:2: iv_ruleMergeBody= ruleMergeBody EOF
            {
             newCompositeNode(grammarAccess.getMergeBodyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMergeBody=ruleMergeBody();

            state._fsp--;

             current =iv_ruleMergeBody; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMergeBody"


    // $ANTLR start "ruleMergeBody"
    // InternalSML.g:845:1: ruleMergeBody returns [EObject current=null] : (otherlv_0= '(' ( (lv_left_1_0= ruleLeft ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) otherlv_4= ')' ) ;
    public final EObject ruleMergeBody() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_virg_2_0=null;
        Token otherlv_4=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalSML.g:851:2: ( (otherlv_0= '(' ( (lv_left_1_0= ruleLeft ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) otherlv_4= ')' ) )
            // InternalSML.g:852:2: (otherlv_0= '(' ( (lv_left_1_0= ruleLeft ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) otherlv_4= ')' )
            {
            // InternalSML.g:852:2: (otherlv_0= '(' ( (lv_left_1_0= ruleLeft ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) otherlv_4= ')' )
            // InternalSML.g:853:3: otherlv_0= '(' ( (lv_left_1_0= ruleLeft ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) otherlv_4= ')'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getMergeBodyAccess().getLeftParenthesisKeyword_0());
            		
            // InternalSML.g:857:3: ( (lv_left_1_0= ruleLeft ) )
            // InternalSML.g:858:4: (lv_left_1_0= ruleLeft )
            {
            // InternalSML.g:858:4: (lv_left_1_0= ruleLeft )
            // InternalSML.g:859:5: lv_left_1_0= ruleLeft
            {

            					newCompositeNode(grammarAccess.getMergeBodyAccess().getLeftLeftParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_13);
            lv_left_1_0=ruleLeft();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeBodyRule());
            					}
            					set(
            						current,
            						"left",
            						lv_left_1_0,
            						"xtext.SML.Left");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:876:3: ( (lv_virg_2_0= ',' ) )
            // InternalSML.g:877:4: (lv_virg_2_0= ',' )
            {
            // InternalSML.g:877:4: (lv_virg_2_0= ',' )
            // InternalSML.g:878:5: lv_virg_2_0= ','
            {
            lv_virg_2_0=(Token)match(input,26,FOLLOW_14); 

            					newLeafNode(lv_virg_2_0, grammarAccess.getMergeBodyAccess().getVirgCommaKeyword_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMergeBodyRule());
            					}
            					setWithLastConsumed(current, "virg", lv_virg_2_0, ",");
            				

            }


            }

            // InternalSML.g:890:3: ( (lv_right_3_0= ruleRight ) )
            // InternalSML.g:891:4: (lv_right_3_0= ruleRight )
            {
            // InternalSML.g:891:4: (lv_right_3_0= ruleRight )
            // InternalSML.g:892:5: lv_right_3_0= ruleRight
            {

            					newCompositeNode(grammarAccess.getMergeBodyAccess().getRightRightParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_15);
            lv_right_3_0=ruleRight();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeBodyRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"xtext.SML.Right");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,27,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getMergeBodyAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMergeBody"


    // $ANTLR start "entryRuleMerge"
    // InternalSML.g:917:1: entryRuleMerge returns [EObject current=null] : iv_ruleMerge= ruleMerge EOF ;
    public final EObject entryRuleMerge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMerge = null;


        try {
            // InternalSML.g:917:46: (iv_ruleMerge= ruleMerge EOF )
            // InternalSML.g:918:2: iv_ruleMerge= ruleMerge EOF
            {
             newCompositeNode(grammarAccess.getMergeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMerge=ruleMerge();

            state._fsp--;

             current =iv_ruleMerge; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMerge"


    // $ANTLR start "ruleMerge"
    // InternalSML.g:924:1: ruleMerge returns [EObject current=null] : ( ( (lv_db_0_0= '[' ) ) ( (lv_mergeBody_1_0= ruleMergeBody ) )+ ( (lv_fm_2_0= ']' ) ) ) ;
    public final EObject ruleMerge() throws RecognitionException {
        EObject current = null;

        Token lv_db_0_0=null;
        Token lv_fm_2_0=null;
        EObject lv_mergeBody_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:930:2: ( ( ( (lv_db_0_0= '[' ) ) ( (lv_mergeBody_1_0= ruleMergeBody ) )+ ( (lv_fm_2_0= ']' ) ) ) )
            // InternalSML.g:931:2: ( ( (lv_db_0_0= '[' ) ) ( (lv_mergeBody_1_0= ruleMergeBody ) )+ ( (lv_fm_2_0= ']' ) ) )
            {
            // InternalSML.g:931:2: ( ( (lv_db_0_0= '[' ) ) ( (lv_mergeBody_1_0= ruleMergeBody ) )+ ( (lv_fm_2_0= ']' ) ) )
            // InternalSML.g:932:3: ( (lv_db_0_0= '[' ) ) ( (lv_mergeBody_1_0= ruleMergeBody ) )+ ( (lv_fm_2_0= ']' ) )
            {
            // InternalSML.g:932:3: ( (lv_db_0_0= '[' ) )
            // InternalSML.g:933:4: (lv_db_0_0= '[' )
            {
            // InternalSML.g:933:4: (lv_db_0_0= '[' )
            // InternalSML.g:934:5: lv_db_0_0= '['
            {
            lv_db_0_0=(Token)match(input,28,FOLLOW_16); 

            					newLeafNode(lv_db_0_0, grammarAccess.getMergeAccess().getDbLeftSquareBracketKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMergeRule());
            					}
            					setWithLastConsumed(current, "db", lv_db_0_0, "[");
            				

            }


            }

            // InternalSML.g:946:3: ( (lv_mergeBody_1_0= ruleMergeBody ) )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==25) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalSML.g:947:4: (lv_mergeBody_1_0= ruleMergeBody )
            	    {
            	    // InternalSML.g:947:4: (lv_mergeBody_1_0= ruleMergeBody )
            	    // InternalSML.g:948:5: lv_mergeBody_1_0= ruleMergeBody
            	    {

            	    					newCompositeNode(grammarAccess.getMergeAccess().getMergeBodyMergeBodyParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_17);
            	    lv_mergeBody_1_0=ruleMergeBody();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMergeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"mergeBody",
            	    						lv_mergeBody_1_0,
            	    						"xtext.SML.MergeBody");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);

            // InternalSML.g:965:3: ( (lv_fm_2_0= ']' ) )
            // InternalSML.g:966:4: (lv_fm_2_0= ']' )
            {
            // InternalSML.g:966:4: (lv_fm_2_0= ']' )
            // InternalSML.g:967:5: lv_fm_2_0= ']'
            {
            lv_fm_2_0=(Token)match(input,29,FOLLOW_2); 

            					newLeafNode(lv_fm_2_0, grammarAccess.getMergeAccess().getFmRightSquareBracketKeyword_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMergeRule());
            					}
            					setWithLastConsumed(current, "fm", lv_fm_2_0, "]");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMerge"


    // $ANTLR start "entryRuleFeatureExtraction"
    // InternalSML.g:983:1: entryRuleFeatureExtraction returns [EObject current=null] : iv_ruleFeatureExtraction= ruleFeatureExtraction EOF ;
    public final EObject entryRuleFeatureExtraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureExtraction = null;


        try {
            // InternalSML.g:983:58: (iv_ruleFeatureExtraction= ruleFeatureExtraction EOF )
            // InternalSML.g:984:2: iv_ruleFeatureExtraction= ruleFeatureExtraction EOF
            {
             newCompositeNode(grammarAccess.getFeatureExtractionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureExtraction=ruleFeatureExtraction();

            state._fsp--;

             current =iv_ruleFeatureExtraction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureExtraction"


    // $ANTLR start "ruleFeatureExtraction"
    // InternalSML.g:990:1: ruleFeatureExtraction returns [EObject current=null] : ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) ) ( (lv_drop_2_0= ruleDropout ) )? ( (lv_pool_3_0= rulePooling ) )? ) ;
    public final EObject ruleFeatureExtraction() throws RecognitionException {
        EObject current = null;

        EObject lv_conv_0_0 = null;

        EObject lv_merge_1_0 = null;

        AntlrDatatypeRuleToken lv_drop_2_0 = null;

        AntlrDatatypeRuleToken lv_pool_3_0 = null;



        	enterRule();

        try {
            // InternalSML.g:996:2: ( ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) ) ( (lv_drop_2_0= ruleDropout ) )? ( (lv_pool_3_0= rulePooling ) )? ) )
            // InternalSML.g:997:2: ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) ) ( (lv_drop_2_0= ruleDropout ) )? ( (lv_pool_3_0= rulePooling ) )? )
            {
            // InternalSML.g:997:2: ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) ) ( (lv_drop_2_0= ruleDropout ) )? ( (lv_pool_3_0= rulePooling ) )? )
            // InternalSML.g:998:3: ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) ) ( (lv_drop_2_0= ruleDropout ) )? ( (lv_pool_3_0= rulePooling ) )?
            {
            // InternalSML.g:998:3: ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=16 && LA20_0<=19)) ) {
                alt20=1;
            }
            else if ( (LA20_0==28) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // InternalSML.g:999:4: ( (lv_conv_0_0= ruleConvolution ) )
                    {
                    // InternalSML.g:999:4: ( (lv_conv_0_0= ruleConvolution ) )
                    // InternalSML.g:1000:5: (lv_conv_0_0= ruleConvolution )
                    {
                    // InternalSML.g:1000:5: (lv_conv_0_0= ruleConvolution )
                    // InternalSML.g:1001:6: lv_conv_0_0= ruleConvolution
                    {

                    						newCompositeNode(grammarAccess.getFeatureExtractionAccess().getConvConvolutionParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_18);
                    lv_conv_0_0=ruleConvolution();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    						}
                    						set(
                    							current,
                    							"conv",
                    							lv_conv_0_0,
                    							"xtext.SML.Convolution");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSML.g:1019:4: ( (lv_merge_1_0= ruleMerge ) )
                    {
                    // InternalSML.g:1019:4: ( (lv_merge_1_0= ruleMerge ) )
                    // InternalSML.g:1020:5: (lv_merge_1_0= ruleMerge )
                    {
                    // InternalSML.g:1020:5: (lv_merge_1_0= ruleMerge )
                    // InternalSML.g:1021:6: lv_merge_1_0= ruleMerge
                    {

                    						newCompositeNode(grammarAccess.getFeatureExtractionAccess().getMergeMergeParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_18);
                    lv_merge_1_0=ruleMerge();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    						}
                    						set(
                    							current,
                    							"merge",
                    							lv_merge_1_0,
                    							"xtext.SML.Merge");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalSML.g:1039:3: ( (lv_drop_2_0= ruleDropout ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==13) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalSML.g:1040:4: (lv_drop_2_0= ruleDropout )
                    {
                    // InternalSML.g:1040:4: (lv_drop_2_0= ruleDropout )
                    // InternalSML.g:1041:5: lv_drop_2_0= ruleDropout
                    {

                    					newCompositeNode(grammarAccess.getFeatureExtractionAccess().getDropDropoutParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_drop_2_0=ruleDropout();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    					}
                    					set(
                    						current,
                    						"drop",
                    						lv_drop_2_0,
                    						"xtext.SML.Dropout");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalSML.g:1058:3: ( (lv_pool_3_0= rulePooling ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=14 && LA22_0<=15)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalSML.g:1059:4: (lv_pool_3_0= rulePooling )
                    {
                    // InternalSML.g:1059:4: (lv_pool_3_0= rulePooling )
                    // InternalSML.g:1060:5: lv_pool_3_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getFeatureExtractionAccess().getPoolPoolingParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_pool_3_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    					}
                    					set(
                    						current,
                    						"pool",
                    						lv_pool_3_0,
                    						"xtext.SML.Pooling");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureExtraction"

    // Delegated rules


    protected DFA14 dfa14 = new DFA14(this);
    static final String dfa_1s = "\10\uffff";
    static final String dfa_2s = "\1\uffff\4\7\1\uffff\1\7\1\uffff";
    static final String dfa_3s = "\1\20\4\15\1\uffff\1\16\1\uffff";
    static final String dfa_4s = "\5\34\1\uffff\1\34\1\uffff";
    static final String dfa_5s = "\5\uffff\1\2\1\uffff\1\1";
    static final String dfa_6s = "\10\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\1\2\1\3\1\4\10\uffff\1\5",
            "\1\6\2\7\1\1\1\2\1\3\1\4\6\uffff\1\7\1\uffff\1\5",
            "\1\6\2\7\1\1\1\2\1\3\1\4\6\uffff\1\7\1\uffff\1\5",
            "\1\6\2\7\1\1\1\2\1\3\1\4\6\uffff\1\7\1\uffff\1\5",
            "\1\6\2\7\1\1\1\2\1\3\1\4\6\uffff\1\7\1\uffff\1\5",
            "",
            "\2\7\1\1\1\2\1\3\1\4\6\uffff\1\7\1\uffff\1\5",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "635:2: ( ( (lv_convdrop_0_0= ruleConvDrop ) )+ | ( ( (lv_convdrop_1_0= ruleConvDrop ) )* ( (lv_mergeConv_2_0= ruleMergeConv ) )+ ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000100F0000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000107F1000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000802000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000803000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000000F0002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000100F0002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x000000000000C002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000100FC000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000010F0000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000022000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000000000000E002L});

}