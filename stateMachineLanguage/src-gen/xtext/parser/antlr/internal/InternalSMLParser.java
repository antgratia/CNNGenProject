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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'input'", "'output'", "'dropout'", "'avg_pooling'", "'max_pooling'", "'['", "']'", "'('", "'bn'", "'conv'", "')'", "'upconv'", "'global_avg_pooling'", "'global_max_pooling'", "'flatten'", "'dense'", "','", "'Empty'", "'|'"
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
    // InternalSML.g:107:1: ruleArchitecture returns [EObject current=null] : ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )* )? ( (lv_output_4_0= 'output' ) ) ) ;
    public final EObject ruleArchitecture() throws RecognitionException {
        EObject current = null;

        Token lv_input_0_0=null;
        Token lv_output_4_0=null;
        EObject lv_fe_1_0 = null;

        EObject lv_inter_2_0 = null;

        EObject lv_class_3_0 = null;



        	enterRule();

        try {
            // InternalSML.g:113:2: ( ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )* )? ( (lv_output_4_0= 'output' ) ) ) )
            // InternalSML.g:114:2: ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )* )? ( (lv_output_4_0= 'output' ) ) )
            {
            // InternalSML.g:114:2: ( ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )* )? ( (lv_output_4_0= 'output' ) ) )
            // InternalSML.g:115:3: ( (lv_input_0_0= 'input' ) ) ( (lv_fe_1_0= ruleFeatureExtraction ) )+ ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )* )? ( (lv_output_4_0= 'output' ) )
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

                if ( (LA1_0==16||LA1_0==18||LA1_0==20||LA1_0==22) ) {
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

            // InternalSML.g:148:3: ( ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=23 && LA3_0<=25)) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalSML.g:149:4: ( (lv_inter_2_0= ruleInterstice ) ) ( (lv_class_3_0= ruleClassification ) )*
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

                    // InternalSML.g:168:4: ( (lv_class_3_0= ruleClassification ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==26) ) {
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
                    	    					
                    	    pushFollow(FOLLOW_5);
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
                    	    break loop2;
                        }
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


    // $ANTLR start "entryRuleDebutMerge"
    // InternalSML.g:258:1: entryRuleDebutMerge returns [String current=null] : iv_ruleDebutMerge= ruleDebutMerge EOF ;
    public final String entryRuleDebutMerge() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDebutMerge = null;


        try {
            // InternalSML.g:258:50: (iv_ruleDebutMerge= ruleDebutMerge EOF )
            // InternalSML.g:259:2: iv_ruleDebutMerge= ruleDebutMerge EOF
            {
             newCompositeNode(grammarAccess.getDebutMergeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDebutMerge=ruleDebutMerge();

            state._fsp--;

             current =iv_ruleDebutMerge.getText(); 
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
    // $ANTLR end "entryRuleDebutMerge"


    // $ANTLR start "ruleDebutMerge"
    // InternalSML.g:265:1: ruleDebutMerge returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '[' ;
    public final AntlrDatatypeRuleToken ruleDebutMerge() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:271:2: (kw= '[' )
            // InternalSML.g:272:2: kw= '['
            {
            kw=(Token)match(input,16,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getDebutMergeAccess().getLeftSquareBracketKeyword());
            	

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
    // $ANTLR end "ruleDebutMerge"


    // $ANTLR start "entryRuleFinMerge"
    // InternalSML.g:280:1: entryRuleFinMerge returns [String current=null] : iv_ruleFinMerge= ruleFinMerge EOF ;
    public final String entryRuleFinMerge() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFinMerge = null;


        try {
            // InternalSML.g:280:48: (iv_ruleFinMerge= ruleFinMerge EOF )
            // InternalSML.g:281:2: iv_ruleFinMerge= ruleFinMerge EOF
            {
             newCompositeNode(grammarAccess.getFinMergeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFinMerge=ruleFinMerge();

            state._fsp--;

             current =iv_ruleFinMerge.getText(); 
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
    // $ANTLR end "entryRuleFinMerge"


    // $ANTLR start "ruleFinMerge"
    // InternalSML.g:287:1: ruleFinMerge returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= ']' ;
    public final AntlrDatatypeRuleToken ruleFinMerge() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:293:2: (kw= ']' )
            // InternalSML.g:294:2: kw= ']'
            {
            kw=(Token)match(input,17,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getFinMergeAccess().getRightSquareBracketKeyword());
            	

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
    // $ANTLR end "ruleFinMerge"


    // $ANTLR start "entryRuleBnConv"
    // InternalSML.g:302:1: entryRuleBnConv returns [String current=null] : iv_ruleBnConv= ruleBnConv EOF ;
    public final String entryRuleBnConv() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBnConv = null;


        try {
            // InternalSML.g:302:46: (iv_ruleBnConv= ruleBnConv EOF )
            // InternalSML.g:303:2: iv_ruleBnConv= ruleBnConv EOF
            {
             newCompositeNode(grammarAccess.getBnConvRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBnConv=ruleBnConv();

            state._fsp--;

             current =iv_ruleBnConv.getText(); 
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
    // $ANTLR end "entryRuleBnConv"


    // $ANTLR start "ruleBnConv"
    // InternalSML.g:309:1: ruleBnConv returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '(' kw= 'bn' kw= 'conv' kw= ')' ) ;
    public final AntlrDatatypeRuleToken ruleBnConv() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:315:2: ( (kw= '(' kw= 'bn' kw= 'conv' kw= ')' ) )
            // InternalSML.g:316:2: (kw= '(' kw= 'bn' kw= 'conv' kw= ')' )
            {
            // InternalSML.g:316:2: (kw= '(' kw= 'bn' kw= 'conv' kw= ')' )
            // InternalSML.g:317:3: kw= '(' kw= 'bn' kw= 'conv' kw= ')'
            {
            kw=(Token)match(input,18,FOLLOW_6); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getBnConvAccess().getLeftParenthesisKeyword_0());
            		
            kw=(Token)match(input,19,FOLLOW_7); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getBnConvAccess().getBnKeyword_1());
            		
            kw=(Token)match(input,20,FOLLOW_8); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getBnConvAccess().getConvKeyword_2());
            		
            kw=(Token)match(input,21,FOLLOW_2); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getBnConvAccess().getRightParenthesisKeyword_3());
            		

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
    // $ANTLR end "ruleBnConv"


    // $ANTLR start "entryRuleConvBn"
    // InternalSML.g:341:1: entryRuleConvBn returns [String current=null] : iv_ruleConvBn= ruleConvBn EOF ;
    public final String entryRuleConvBn() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleConvBn = null;


        try {
            // InternalSML.g:341:46: (iv_ruleConvBn= ruleConvBn EOF )
            // InternalSML.g:342:2: iv_ruleConvBn= ruleConvBn EOF
            {
             newCompositeNode(grammarAccess.getConvBnRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConvBn=ruleConvBn();

            state._fsp--;

             current =iv_ruleConvBn.getText(); 
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
    // $ANTLR end "entryRuleConvBn"


    // $ANTLR start "ruleConvBn"
    // InternalSML.g:348:1: ruleConvBn returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '(' kw= 'conv' kw= 'bn' kw= ')' ) ;
    public final AntlrDatatypeRuleToken ruleConvBn() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:354:2: ( (kw= '(' kw= 'conv' kw= 'bn' kw= ')' ) )
            // InternalSML.g:355:2: (kw= '(' kw= 'conv' kw= 'bn' kw= ')' )
            {
            // InternalSML.g:355:2: (kw= '(' kw= 'conv' kw= 'bn' kw= ')' )
            // InternalSML.g:356:3: kw= '(' kw= 'conv' kw= 'bn' kw= ')'
            {
            kw=(Token)match(input,18,FOLLOW_7); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getConvBnAccess().getLeftParenthesisKeyword_0());
            		
            kw=(Token)match(input,20,FOLLOW_6); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getConvBnAccess().getConvKeyword_1());
            		
            kw=(Token)match(input,19,FOLLOW_8); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getConvBnAccess().getBnKeyword_2());
            		
            kw=(Token)match(input,21,FOLLOW_2); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getConvBnAccess().getRightParenthesisKeyword_3());
            		

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
    // $ANTLR end "ruleConvBn"


    // $ANTLR start "entryRuleConvolution"
    // InternalSML.g:380:1: entryRuleConvolution returns [EObject current=null] : iv_ruleConvolution= ruleConvolution EOF ;
    public final EObject entryRuleConvolution() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConvolution = null;


        try {
            // InternalSML.g:380:52: (iv_ruleConvolution= ruleConvolution EOF )
            // InternalSML.g:381:2: iv_ruleConvolution= ruleConvolution EOF
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
    // InternalSML.g:387:1: ruleConvolution returns [EObject current=null] : ( ( (lv_bnconv_0_0= ruleBnConv ) ) | ( (lv_convbn_1_0= ruleConvBn ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) ) ;
    public final EObject ruleConvolution() throws RecognitionException {
        EObject current = null;

        Token lv_conv_2_0=null;
        Token lv_upconv_3_0=null;
        AntlrDatatypeRuleToken lv_bnconv_0_0 = null;

        AntlrDatatypeRuleToken lv_convbn_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:393:2: ( ( ( (lv_bnconv_0_0= ruleBnConv ) ) | ( (lv_convbn_1_0= ruleConvBn ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) ) )
            // InternalSML.g:394:2: ( ( (lv_bnconv_0_0= ruleBnConv ) ) | ( (lv_convbn_1_0= ruleConvBn ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) )
            {
            // InternalSML.g:394:2: ( ( (lv_bnconv_0_0= ruleBnConv ) ) | ( (lv_convbn_1_0= ruleConvBn ) ) | ( (lv_conv_2_0= 'conv' ) ) | ( (lv_upconv_3_0= 'upconv' ) ) )
            int alt5=4;
            switch ( input.LA(1) ) {
            case 18:
                {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==19) ) {
                    alt5=1;
                }
                else if ( (LA5_1==20) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case 20:
                {
                alt5=3;
                }
                break;
            case 22:
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
                    // InternalSML.g:395:3: ( (lv_bnconv_0_0= ruleBnConv ) )
                    {
                    // InternalSML.g:395:3: ( (lv_bnconv_0_0= ruleBnConv ) )
                    // InternalSML.g:396:4: (lv_bnconv_0_0= ruleBnConv )
                    {
                    // InternalSML.g:396:4: (lv_bnconv_0_0= ruleBnConv )
                    // InternalSML.g:397:5: lv_bnconv_0_0= ruleBnConv
                    {

                    					newCompositeNode(grammarAccess.getConvolutionAccess().getBnconvBnConvParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_bnconv_0_0=ruleBnConv();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConvolutionRule());
                    					}
                    					set(
                    						current,
                    						"bnconv",
                    						lv_bnconv_0_0,
                    						"xtext.SML.BnConv");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSML.g:415:3: ( (lv_convbn_1_0= ruleConvBn ) )
                    {
                    // InternalSML.g:415:3: ( (lv_convbn_1_0= ruleConvBn ) )
                    // InternalSML.g:416:4: (lv_convbn_1_0= ruleConvBn )
                    {
                    // InternalSML.g:416:4: (lv_convbn_1_0= ruleConvBn )
                    // InternalSML.g:417:5: lv_convbn_1_0= ruleConvBn
                    {

                    					newCompositeNode(grammarAccess.getConvolutionAccess().getConvbnConvBnParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_convbn_1_0=ruleConvBn();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConvolutionRule());
                    					}
                    					set(
                    						current,
                    						"convbn",
                    						lv_convbn_1_0,
                    						"xtext.SML.ConvBn");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSML.g:435:3: ( (lv_conv_2_0= 'conv' ) )
                    {
                    // InternalSML.g:435:3: ( (lv_conv_2_0= 'conv' ) )
                    // InternalSML.g:436:4: (lv_conv_2_0= 'conv' )
                    {
                    // InternalSML.g:436:4: (lv_conv_2_0= 'conv' )
                    // InternalSML.g:437:5: lv_conv_2_0= 'conv'
                    {
                    lv_conv_2_0=(Token)match(input,20,FOLLOW_2); 

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
                    // InternalSML.g:450:3: ( (lv_upconv_3_0= 'upconv' ) )
                    {
                    // InternalSML.g:450:3: ( (lv_upconv_3_0= 'upconv' ) )
                    // InternalSML.g:451:4: (lv_upconv_3_0= 'upconv' )
                    {
                    // InternalSML.g:451:4: (lv_upconv_3_0= 'upconv' )
                    // InternalSML.g:452:5: lv_upconv_3_0= 'upconv'
                    {
                    lv_upconv_3_0=(Token)match(input,22,FOLLOW_2); 

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
    // InternalSML.g:468:1: entryRuleGlobalPooling returns [String current=null] : iv_ruleGlobalPooling= ruleGlobalPooling EOF ;
    public final String entryRuleGlobalPooling() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleGlobalPooling = null;


        try {
            // InternalSML.g:468:53: (iv_ruleGlobalPooling= ruleGlobalPooling EOF )
            // InternalSML.g:469:2: iv_ruleGlobalPooling= ruleGlobalPooling EOF
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
    // InternalSML.g:475:1: ruleGlobalPooling returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'global_avg_pooling' | kw= 'global_max_pooling' ) ;
    public final AntlrDatatypeRuleToken ruleGlobalPooling() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalSML.g:481:2: ( (kw= 'global_avg_pooling' | kw= 'global_max_pooling' ) )
            // InternalSML.g:482:2: (kw= 'global_avg_pooling' | kw= 'global_max_pooling' )
            {
            // InternalSML.g:482:2: (kw= 'global_avg_pooling' | kw= 'global_max_pooling' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==23) ) {
                alt6=1;
            }
            else if ( (LA6_0==24) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalSML.g:483:3: kw= 'global_avg_pooling'
                    {
                    kw=(Token)match(input,23,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getGlobalPoolingAccess().getGlobal_avg_poolingKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalSML.g:489:3: kw= 'global_max_pooling'
                    {
                    kw=(Token)match(input,24,FOLLOW_2); 

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
    // InternalSML.g:498:1: entryRuleFlattenOrGlobal returns [EObject current=null] : iv_ruleFlattenOrGlobal= ruleFlattenOrGlobal EOF ;
    public final EObject entryRuleFlattenOrGlobal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFlattenOrGlobal = null;


        try {
            // InternalSML.g:498:56: (iv_ruleFlattenOrGlobal= ruleFlattenOrGlobal EOF )
            // InternalSML.g:499:2: iv_ruleFlattenOrGlobal= ruleFlattenOrGlobal EOF
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
    // InternalSML.g:505:1: ruleFlattenOrGlobal returns [EObject current=null] : ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) ) ;
    public final EObject ruleFlattenOrGlobal() throws RecognitionException {
        EObject current = null;

        Token lv_flat_0_0=null;
        AntlrDatatypeRuleToken lv_gp_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:511:2: ( ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) ) )
            // InternalSML.g:512:2: ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) )
            {
            // InternalSML.g:512:2: ( ( (lv_flat_0_0= 'flatten' ) ) | ( (lv_gp_1_0= ruleGlobalPooling ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==25) ) {
                alt7=1;
            }
            else if ( ((LA7_0>=23 && LA7_0<=24)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalSML.g:513:3: ( (lv_flat_0_0= 'flatten' ) )
                    {
                    // InternalSML.g:513:3: ( (lv_flat_0_0= 'flatten' ) )
                    // InternalSML.g:514:4: (lv_flat_0_0= 'flatten' )
                    {
                    // InternalSML.g:514:4: (lv_flat_0_0= 'flatten' )
                    // InternalSML.g:515:5: lv_flat_0_0= 'flatten'
                    {
                    lv_flat_0_0=(Token)match(input,25,FOLLOW_2); 

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
                    // InternalSML.g:528:3: ( (lv_gp_1_0= ruleGlobalPooling ) )
                    {
                    // InternalSML.g:528:3: ( (lv_gp_1_0= ruleGlobalPooling ) )
                    // InternalSML.g:529:4: (lv_gp_1_0= ruleGlobalPooling )
                    {
                    // InternalSML.g:529:4: (lv_gp_1_0= ruleGlobalPooling )
                    // InternalSML.g:530:5: lv_gp_1_0= ruleGlobalPooling
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
    // InternalSML.g:551:1: entryRuleInterstice returns [EObject current=null] : iv_ruleInterstice= ruleInterstice EOF ;
    public final EObject entryRuleInterstice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterstice = null;


        try {
            // InternalSML.g:551:51: (iv_ruleInterstice= ruleInterstice EOF )
            // InternalSML.g:552:2: iv_ruleInterstice= ruleInterstice EOF
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
    // InternalSML.g:558:1: ruleInterstice returns [EObject current=null] : ( ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) ( (lv_drop_1_0= ruleDropout ) )? ) ;
    public final EObject ruleInterstice() throws RecognitionException {
        EObject current = null;

        EObject lv_fg_0_0 = null;

        AntlrDatatypeRuleToken lv_drop_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:564:2: ( ( ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) ( (lv_drop_1_0= ruleDropout ) )? ) )
            // InternalSML.g:565:2: ( ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            {
            // InternalSML.g:565:2: ( ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            // InternalSML.g:566:3: ( (lv_fg_0_0= ruleFlattenOrGlobal ) ) ( (lv_drop_1_0= ruleDropout ) )?
            {
            // InternalSML.g:566:3: ( (lv_fg_0_0= ruleFlattenOrGlobal ) )
            // InternalSML.g:567:4: (lv_fg_0_0= ruleFlattenOrGlobal )
            {
            // InternalSML.g:567:4: (lv_fg_0_0= ruleFlattenOrGlobal )
            // InternalSML.g:568:5: lv_fg_0_0= ruleFlattenOrGlobal
            {

            					newCompositeNode(grammarAccess.getIntersticeAccess().getFgFlattenOrGlobalParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_9);
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

            // InternalSML.g:585:3: ( (lv_drop_1_0= ruleDropout ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==13) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalSML.g:586:4: (lv_drop_1_0= ruleDropout )
                    {
                    // InternalSML.g:586:4: (lv_drop_1_0= ruleDropout )
                    // InternalSML.g:587:5: lv_drop_1_0= ruleDropout
                    {

                    					newCompositeNode(grammarAccess.getIntersticeAccess().getDropDropoutParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_drop_1_0=ruleDropout();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getIntersticeRule());
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
    // $ANTLR end "ruleInterstice"


    // $ANTLR start "entryRuleClassification"
    // InternalSML.g:608:1: entryRuleClassification returns [EObject current=null] : iv_ruleClassification= ruleClassification EOF ;
    public final EObject entryRuleClassification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassification = null;


        try {
            // InternalSML.g:608:55: (iv_ruleClassification= ruleClassification EOF )
            // InternalSML.g:609:2: iv_ruleClassification= ruleClassification EOF
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
    // InternalSML.g:615:1: ruleClassification returns [EObject current=null] : ( ( (lv_d_0_0= 'dense' ) ) ( (lv_drop_1_0= ruleDropout ) )? ) ;
    public final EObject ruleClassification() throws RecognitionException {
        EObject current = null;

        Token lv_d_0_0=null;
        AntlrDatatypeRuleToken lv_drop_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:621:2: ( ( ( (lv_d_0_0= 'dense' ) ) ( (lv_drop_1_0= ruleDropout ) )? ) )
            // InternalSML.g:622:2: ( ( (lv_d_0_0= 'dense' ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            {
            // InternalSML.g:622:2: ( ( (lv_d_0_0= 'dense' ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            // InternalSML.g:623:3: ( (lv_d_0_0= 'dense' ) ) ( (lv_drop_1_0= ruleDropout ) )?
            {
            // InternalSML.g:623:3: ( (lv_d_0_0= 'dense' ) )
            // InternalSML.g:624:4: (lv_d_0_0= 'dense' )
            {
            // InternalSML.g:624:4: (lv_d_0_0= 'dense' )
            // InternalSML.g:625:5: lv_d_0_0= 'dense'
            {
            lv_d_0_0=(Token)match(input,26,FOLLOW_9); 

            					newLeafNode(lv_d_0_0, grammarAccess.getClassificationAccess().getDDenseKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClassificationRule());
            					}
            					setWithLastConsumed(current, "d", lv_d_0_0, "dense");
            				

            }


            }

            // InternalSML.g:637:3: ( (lv_drop_1_0= ruleDropout ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==13) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalSML.g:638:4: (lv_drop_1_0= ruleDropout )
                    {
                    // InternalSML.g:638:4: (lv_drop_1_0= ruleDropout )
                    // InternalSML.g:639:5: lv_drop_1_0= ruleDropout
                    {

                    					newCompositeNode(grammarAccess.getClassificationAccess().getDropDropoutParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_drop_1_0=ruleDropout();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getClassificationRule());
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
    // $ANTLR end "ruleClassification"


    // $ANTLR start "entryRuleConvDrop"
    // InternalSML.g:660:1: entryRuleConvDrop returns [EObject current=null] : iv_ruleConvDrop= ruleConvDrop EOF ;
    public final EObject entryRuleConvDrop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConvDrop = null;


        try {
            // InternalSML.g:660:49: (iv_ruleConvDrop= ruleConvDrop EOF )
            // InternalSML.g:661:2: iv_ruleConvDrop= ruleConvDrop EOF
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
    // InternalSML.g:667:1: ruleConvDrop returns [EObject current=null] : ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? ) ;
    public final EObject ruleConvDrop() throws RecognitionException {
        EObject current = null;

        EObject lv_conv_0_0 = null;

        AntlrDatatypeRuleToken lv_drop_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:673:2: ( ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? ) )
            // InternalSML.g:674:2: ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            {
            // InternalSML.g:674:2: ( ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )? )
            // InternalSML.g:675:3: ( (lv_conv_0_0= ruleConvolution ) ) ( (lv_drop_1_0= ruleDropout ) )?
            {
            // InternalSML.g:675:3: ( (lv_conv_0_0= ruleConvolution ) )
            // InternalSML.g:676:4: (lv_conv_0_0= ruleConvolution )
            {
            // InternalSML.g:676:4: (lv_conv_0_0= ruleConvolution )
            // InternalSML.g:677:5: lv_conv_0_0= ruleConvolution
            {

            					newCompositeNode(grammarAccess.getConvDropAccess().getConvConvolutionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_9);
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

            // InternalSML.g:694:3: ( (lv_drop_1_0= ruleDropout ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==13) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalSML.g:695:4: (lv_drop_1_0= ruleDropout )
                    {
                    // InternalSML.g:695:4: (lv_drop_1_0= ruleDropout )
                    // InternalSML.g:696:5: lv_drop_1_0= ruleDropout
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


    // $ANTLR start "entryRuleLeftRecu"
    // InternalSML.g:717:1: entryRuleLeftRecu returns [EObject current=null] : iv_ruleLeftRecu= ruleLeftRecu EOF ;
    public final EObject entryRuleLeftRecu() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLeftRecu = null;


        try {
            // InternalSML.g:717:49: (iv_ruleLeftRecu= ruleLeftRecu EOF )
            // InternalSML.g:718:2: iv_ruleLeftRecu= ruleLeftRecu EOF
            {
             newCompositeNode(grammarAccess.getLeftRecuRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLeftRecu=ruleLeftRecu();

            state._fsp--;

             current =iv_ruleLeftRecu; 
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
    // $ANTLR end "entryRuleLeftRecu"


    // $ANTLR start "ruleLeftRecu"
    // InternalSML.g:724:1: ruleLeftRecu returns [EObject current=null] : ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdropbegin_1_0= ruleConvDrop ) )* ( (lv_merge_2_0= ruleMerge ) ) ( (lv_convdropend_3_0= ruleConvDrop ) )* ( (lv_pool_4_0= rulePooling ) )? ) ;
    public final EObject ruleLeftRecu() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_p_0_0 = null;

        EObject lv_convdropbegin_1_0 = null;

        EObject lv_merge_2_0 = null;

        EObject lv_convdropend_3_0 = null;

        AntlrDatatypeRuleToken lv_pool_4_0 = null;



        	enterRule();

        try {
            // InternalSML.g:730:2: ( ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdropbegin_1_0= ruleConvDrop ) )* ( (lv_merge_2_0= ruleMerge ) ) ( (lv_convdropend_3_0= ruleConvDrop ) )* ( (lv_pool_4_0= rulePooling ) )? ) )
            // InternalSML.g:731:2: ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdropbegin_1_0= ruleConvDrop ) )* ( (lv_merge_2_0= ruleMerge ) ) ( (lv_convdropend_3_0= ruleConvDrop ) )* ( (lv_pool_4_0= rulePooling ) )? )
            {
            // InternalSML.g:731:2: ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdropbegin_1_0= ruleConvDrop ) )* ( (lv_merge_2_0= ruleMerge ) ) ( (lv_convdropend_3_0= ruleConvDrop ) )* ( (lv_pool_4_0= rulePooling ) )? )
            // InternalSML.g:732:3: ( (lv_p_0_0= rulePooling ) )? ( (lv_convdropbegin_1_0= ruleConvDrop ) )* ( (lv_merge_2_0= ruleMerge ) ) ( (lv_convdropend_3_0= ruleConvDrop ) )* ( (lv_pool_4_0= rulePooling ) )?
            {
            // InternalSML.g:732:3: ( (lv_p_0_0= rulePooling ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=14 && LA11_0<=15)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalSML.g:733:4: (lv_p_0_0= rulePooling )
                    {
                    // InternalSML.g:733:4: (lv_p_0_0= rulePooling )
                    // InternalSML.g:734:5: lv_p_0_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getLeftRecuAccess().getPPoolingParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_3);
                    lv_p_0_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getLeftRecuRule());
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

            // InternalSML.g:751:3: ( (lv_convdropbegin_1_0= ruleConvDrop ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==18||LA12_0==20||LA12_0==22) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalSML.g:752:4: (lv_convdropbegin_1_0= ruleConvDrop )
            	    {
            	    // InternalSML.g:752:4: (lv_convdropbegin_1_0= ruleConvDrop )
            	    // InternalSML.g:753:5: lv_convdropbegin_1_0= ruleConvDrop
            	    {

            	    					newCompositeNode(grammarAccess.getLeftRecuAccess().getConvdropbeginConvDropParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_convdropbegin_1_0=ruleConvDrop();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getLeftRecuRule());
            	    					}
            	    					add(
            	    						current,
            	    						"convdropbegin",
            	    						lv_convdropbegin_1_0,
            	    						"xtext.SML.ConvDrop");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // InternalSML.g:770:3: ( (lv_merge_2_0= ruleMerge ) )
            // InternalSML.g:771:4: (lv_merge_2_0= ruleMerge )
            {
            // InternalSML.g:771:4: (lv_merge_2_0= ruleMerge )
            // InternalSML.g:772:5: lv_merge_2_0= ruleMerge
            {

            					newCompositeNode(grammarAccess.getLeftRecuAccess().getMergeMergeParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_10);
            lv_merge_2_0=ruleMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLeftRecuRule());
            					}
            					set(
            						current,
            						"merge",
            						lv_merge_2_0,
            						"xtext.SML.Merge");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:789:3: ( (lv_convdropend_3_0= ruleConvDrop ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==18||LA13_0==20||LA13_0==22) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalSML.g:790:4: (lv_convdropend_3_0= ruleConvDrop )
            	    {
            	    // InternalSML.g:790:4: (lv_convdropend_3_0= ruleConvDrop )
            	    // InternalSML.g:791:5: lv_convdropend_3_0= ruleConvDrop
            	    {

            	    					newCompositeNode(grammarAccess.getLeftRecuAccess().getConvdropendConvDropParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_convdropend_3_0=ruleConvDrop();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getLeftRecuRule());
            	    					}
            	    					add(
            	    						current,
            	    						"convdropend",
            	    						lv_convdropend_3_0,
            	    						"xtext.SML.ConvDrop");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // InternalSML.g:808:3: ( (lv_pool_4_0= rulePooling ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=14 && LA14_0<=15)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalSML.g:809:4: (lv_pool_4_0= rulePooling )
                    {
                    // InternalSML.g:809:4: (lv_pool_4_0= rulePooling )
                    // InternalSML.g:810:5: lv_pool_4_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getLeftRecuAccess().getPoolPoolingParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_pool_4_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getLeftRecuRule());
                    					}
                    					set(
                    						current,
                    						"pool",
                    						lv_pool_4_0,
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
    // $ANTLR end "ruleLeftRecu"


    // $ANTLR start "entryRuleMergeRecu"
    // InternalSML.g:831:1: entryRuleMergeRecu returns [EObject current=null] : iv_ruleMergeRecu= ruleMergeRecu EOF ;
    public final EObject entryRuleMergeRecu() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMergeRecu = null;


        try {
            // InternalSML.g:831:50: (iv_ruleMergeRecu= ruleMergeRecu EOF )
            // InternalSML.g:832:2: iv_ruleMergeRecu= ruleMergeRecu EOF
            {
             newCompositeNode(grammarAccess.getMergeRecuRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMergeRecu=ruleMergeRecu();

            state._fsp--;

             current =iv_ruleMergeRecu; 
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
    // $ANTLR end "entryRuleMergeRecu"


    // $ANTLR start "ruleMergeRecu"
    // InternalSML.g:838:1: ruleMergeRecu returns [EObject current=null] : ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_left_1_0= ruleLeftRecu ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) ) ;
    public final EObject ruleMergeRecu() throws RecognitionException {
        EObject current = null;

        Token lv_virg_2_0=null;
        AntlrDatatypeRuleToken lv_db_0_0 = null;

        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;

        AntlrDatatypeRuleToken lv_fm_4_0 = null;



        	enterRule();

        try {
            // InternalSML.g:844:2: ( ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_left_1_0= ruleLeftRecu ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) ) )
            // InternalSML.g:845:2: ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_left_1_0= ruleLeftRecu ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) )
            {
            // InternalSML.g:845:2: ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_left_1_0= ruleLeftRecu ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) )
            // InternalSML.g:846:3: ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_left_1_0= ruleLeftRecu ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) )
            {
            // InternalSML.g:846:3: ( (lv_db_0_0= ruleDebutMerge ) )
            // InternalSML.g:847:4: (lv_db_0_0= ruleDebutMerge )
            {
            // InternalSML.g:847:4: (lv_db_0_0= ruleDebutMerge )
            // InternalSML.g:848:5: lv_db_0_0= ruleDebutMerge
            {

            					newCompositeNode(grammarAccess.getMergeRecuAccess().getDbDebutMergeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_11);
            lv_db_0_0=ruleDebutMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeRecuRule());
            					}
            					set(
            						current,
            						"db",
            						lv_db_0_0,
            						"xtext.SML.DebutMerge");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:865:3: ( (lv_left_1_0= ruleLeftRecu ) )
            // InternalSML.g:866:4: (lv_left_1_0= ruleLeftRecu )
            {
            // InternalSML.g:866:4: (lv_left_1_0= ruleLeftRecu )
            // InternalSML.g:867:5: lv_left_1_0= ruleLeftRecu
            {

            					newCompositeNode(grammarAccess.getMergeRecuAccess().getLeftLeftRecuParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_12);
            lv_left_1_0=ruleLeftRecu();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeRecuRule());
            					}
            					set(
            						current,
            						"left",
            						lv_left_1_0,
            						"xtext.SML.LeftRecu");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:884:3: ( (lv_virg_2_0= ',' ) )
            // InternalSML.g:885:4: (lv_virg_2_0= ',' )
            {
            // InternalSML.g:885:4: (lv_virg_2_0= ',' )
            // InternalSML.g:886:5: lv_virg_2_0= ','
            {
            lv_virg_2_0=(Token)match(input,27,FOLLOW_13); 

            					newLeafNode(lv_virg_2_0, grammarAccess.getMergeRecuAccess().getVirgCommaKeyword_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMergeRecuRule());
            					}
            					setWithLastConsumed(current, "virg", lv_virg_2_0, ",");
            				

            }


            }

            // InternalSML.g:898:3: ( (lv_right_3_0= ruleRight ) )
            // InternalSML.g:899:4: (lv_right_3_0= ruleRight )
            {
            // InternalSML.g:899:4: (lv_right_3_0= ruleRight )
            // InternalSML.g:900:5: lv_right_3_0= ruleRight
            {

            					newCompositeNode(grammarAccess.getMergeRecuAccess().getRightRightParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_14);
            lv_right_3_0=ruleRight();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeRecuRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"xtext.SML.Right");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:917:3: ( (lv_fm_4_0= ruleFinMerge ) )
            // InternalSML.g:918:4: (lv_fm_4_0= ruleFinMerge )
            {
            // InternalSML.g:918:4: (lv_fm_4_0= ruleFinMerge )
            // InternalSML.g:919:5: lv_fm_4_0= ruleFinMerge
            {

            					newCompositeNode(grammarAccess.getMergeRecuAccess().getFmFinMergeParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_4_0=ruleFinMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeRecuRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_4_0,
            						"xtext.SML.FinMerge");
            					afterParserOrEnumRuleCall();
            				

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
    // $ANTLR end "ruleMergeRecu"


    // $ANTLR start "entryRuleLeftNonRecursive"
    // InternalSML.g:940:1: entryRuleLeftNonRecursive returns [EObject current=null] : iv_ruleLeftNonRecursive= ruleLeftNonRecursive EOF ;
    public final EObject entryRuleLeftNonRecursive() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLeftNonRecursive = null;


        try {
            // InternalSML.g:940:57: (iv_ruleLeftNonRecursive= ruleLeftNonRecursive EOF )
            // InternalSML.g:941:2: iv_ruleLeftNonRecursive= ruleLeftNonRecursive EOF
            {
             newCompositeNode(grammarAccess.getLeftNonRecursiveRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLeftNonRecursive=ruleLeftNonRecursive();

            state._fsp--;

             current =iv_ruleLeftNonRecursive; 
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
    // $ANTLR end "entryRuleLeftNonRecursive"


    // $ANTLR start "ruleLeftNonRecursive"
    // InternalSML.g:947:1: ruleLeftNonRecursive returns [EObject current=null] : ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdrop_1_0= ruleConvDrop ) )+ ( (lv_pool_2_0= rulePooling ) )? ) ;
    public final EObject ruleLeftNonRecursive() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_p_0_0 = null;

        EObject lv_convdrop_1_0 = null;

        AntlrDatatypeRuleToken lv_pool_2_0 = null;



        	enterRule();

        try {
            // InternalSML.g:953:2: ( ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdrop_1_0= ruleConvDrop ) )+ ( (lv_pool_2_0= rulePooling ) )? ) )
            // InternalSML.g:954:2: ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdrop_1_0= ruleConvDrop ) )+ ( (lv_pool_2_0= rulePooling ) )? )
            {
            // InternalSML.g:954:2: ( ( (lv_p_0_0= rulePooling ) )? ( (lv_convdrop_1_0= ruleConvDrop ) )+ ( (lv_pool_2_0= rulePooling ) )? )
            // InternalSML.g:955:3: ( (lv_p_0_0= rulePooling ) )? ( (lv_convdrop_1_0= ruleConvDrop ) )+ ( (lv_pool_2_0= rulePooling ) )?
            {
            // InternalSML.g:955:3: ( (lv_p_0_0= rulePooling ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=14 && LA15_0<=15)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalSML.g:956:4: (lv_p_0_0= rulePooling )
                    {
                    // InternalSML.g:956:4: (lv_p_0_0= rulePooling )
                    // InternalSML.g:957:5: lv_p_0_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getLeftNonRecursiveAccess().getPPoolingParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_15);
                    lv_p_0_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getLeftNonRecursiveRule());
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

            // InternalSML.g:974:3: ( (lv_convdrop_1_0= ruleConvDrop ) )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==18||LA16_0==20||LA16_0==22) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalSML.g:975:4: (lv_convdrop_1_0= ruleConvDrop )
            	    {
            	    // InternalSML.g:975:4: (lv_convdrop_1_0= ruleConvDrop )
            	    // InternalSML.g:976:5: lv_convdrop_1_0= ruleConvDrop
            	    {

            	    					newCompositeNode(grammarAccess.getLeftNonRecursiveAccess().getConvdropConvDropParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_convdrop_1_0=ruleConvDrop();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getLeftNonRecursiveRule());
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
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);

            // InternalSML.g:993:3: ( (lv_pool_2_0= rulePooling ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=14 && LA17_0<=15)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalSML.g:994:4: (lv_pool_2_0= rulePooling )
                    {
                    // InternalSML.g:994:4: (lv_pool_2_0= rulePooling )
                    // InternalSML.g:995:5: lv_pool_2_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getLeftNonRecursiveAccess().getPoolPoolingParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_pool_2_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getLeftNonRecursiveRule());
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
    // $ANTLR end "ruleLeftNonRecursive"


    // $ANTLR start "entryRuleRight"
    // InternalSML.g:1016:1: entryRuleRight returns [EObject current=null] : iv_ruleRight= ruleRight EOF ;
    public final EObject entryRuleRight() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRight = null;


        try {
            // InternalSML.g:1016:46: (iv_ruleRight= ruleRight EOF )
            // InternalSML.g:1017:2: iv_ruleRight= ruleRight EOF
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
    // InternalSML.g:1023:1: ruleRight returns [EObject current=null] : ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () otherlv_2= 'Empty' ) ) ;
    public final EObject ruleRight() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_conv_0_0 = null;



        	enterRule();

        try {
            // InternalSML.g:1029:2: ( ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () otherlv_2= 'Empty' ) ) )
            // InternalSML.g:1030:2: ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () otherlv_2= 'Empty' ) )
            {
            // InternalSML.g:1030:2: ( ( (lv_conv_0_0= ruleConvolution ) )+ | ( () otherlv_2= 'Empty' ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==18||LA19_0==20||LA19_0==22) ) {
                alt19=1;
            }
            else if ( (LA19_0==28) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalSML.g:1031:3: ( (lv_conv_0_0= ruleConvolution ) )+
                    {
                    // InternalSML.g:1031:3: ( (lv_conv_0_0= ruleConvolution ) )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==18||LA18_0==20||LA18_0==22) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalSML.g:1032:4: (lv_conv_0_0= ruleConvolution )
                    	    {
                    	    // InternalSML.g:1032:4: (lv_conv_0_0= ruleConvolution )
                    	    // InternalSML.g:1033:5: lv_conv_0_0= ruleConvolution
                    	    {

                    	    					newCompositeNode(grammarAccess.getRightAccess().getConvConvolutionParserRuleCall_0_0());
                    	    				
                    	    pushFollow(FOLLOW_16);
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
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalSML.g:1051:3: ( () otherlv_2= 'Empty' )
                    {
                    // InternalSML.g:1051:3: ( () otherlv_2= 'Empty' )
                    // InternalSML.g:1052:4: () otherlv_2= 'Empty'
                    {
                    // InternalSML.g:1052:4: ()
                    // InternalSML.g:1053:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getRightAccess().getRightAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_2=(Token)match(input,28,FOLLOW_2); 

                    				newLeafNode(otherlv_2, grammarAccess.getRightAccess().getEmptyKeyword_1_1());
                    			

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


    // $ANTLR start "entryRuleMergeNonRecu"
    // InternalSML.g:1068:1: entryRuleMergeNonRecu returns [EObject current=null] : iv_ruleMergeNonRecu= ruleMergeNonRecu EOF ;
    public final EObject entryRuleMergeNonRecu() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMergeNonRecu = null;


        try {
            // InternalSML.g:1068:53: (iv_ruleMergeNonRecu= ruleMergeNonRecu EOF )
            // InternalSML.g:1069:2: iv_ruleMergeNonRecu= ruleMergeNonRecu EOF
            {
             newCompositeNode(grammarAccess.getMergeNonRecuRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMergeNonRecu=ruleMergeNonRecu();

            state._fsp--;

             current =iv_ruleMergeNonRecu; 
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
    // $ANTLR end "entryRuleMergeNonRecu"


    // $ANTLR start "ruleMergeNonRecu"
    // InternalSML.g:1075:1: ruleMergeNonRecu returns [EObject current=null] : ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) ) ;
    public final EObject ruleMergeNonRecu() throws RecognitionException {
        EObject current = null;

        Token lv_virg_2_0=null;
        AntlrDatatypeRuleToken lv_db_0_0 = null;

        EObject lv_leftNonRec_1_0 = null;

        EObject lv_right_3_0 = null;

        AntlrDatatypeRuleToken lv_fm_4_0 = null;



        	enterRule();

        try {
            // InternalSML.g:1081:2: ( ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) ) )
            // InternalSML.g:1082:2: ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) )
            {
            // InternalSML.g:1082:2: ( ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) ) )
            // InternalSML.g:1083:3: ( (lv_db_0_0= ruleDebutMerge ) ) ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) ( (lv_virg_2_0= ',' ) ) ( (lv_right_3_0= ruleRight ) ) ( (lv_fm_4_0= ruleFinMerge ) )
            {
            // InternalSML.g:1083:3: ( (lv_db_0_0= ruleDebutMerge ) )
            // InternalSML.g:1084:4: (lv_db_0_0= ruleDebutMerge )
            {
            // InternalSML.g:1084:4: (lv_db_0_0= ruleDebutMerge )
            // InternalSML.g:1085:5: lv_db_0_0= ruleDebutMerge
            {

            					newCompositeNode(grammarAccess.getMergeNonRecuAccess().getDbDebutMergeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_17);
            lv_db_0_0=ruleDebutMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeNonRecuRule());
            					}
            					set(
            						current,
            						"db",
            						lv_db_0_0,
            						"xtext.SML.DebutMerge");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:1102:3: ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) )
            // InternalSML.g:1103:4: (lv_leftNonRec_1_0= ruleLeftNonRecursive )
            {
            // InternalSML.g:1103:4: (lv_leftNonRec_1_0= ruleLeftNonRecursive )
            // InternalSML.g:1104:5: lv_leftNonRec_1_0= ruleLeftNonRecursive
            {

            					newCompositeNode(grammarAccess.getMergeNonRecuAccess().getLeftNonRecLeftNonRecursiveParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_12);
            lv_leftNonRec_1_0=ruleLeftNonRecursive();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeNonRecuRule());
            					}
            					set(
            						current,
            						"leftNonRec",
            						lv_leftNonRec_1_0,
            						"xtext.SML.LeftNonRecursive");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:1121:3: ( (lv_virg_2_0= ',' ) )
            // InternalSML.g:1122:4: (lv_virg_2_0= ',' )
            {
            // InternalSML.g:1122:4: (lv_virg_2_0= ',' )
            // InternalSML.g:1123:5: lv_virg_2_0= ','
            {
            lv_virg_2_0=(Token)match(input,27,FOLLOW_13); 

            					newLeafNode(lv_virg_2_0, grammarAccess.getMergeNonRecuAccess().getVirgCommaKeyword_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMergeNonRecuRule());
            					}
            					setWithLastConsumed(current, "virg", lv_virg_2_0, ",");
            				

            }


            }

            // InternalSML.g:1135:3: ( (lv_right_3_0= ruleRight ) )
            // InternalSML.g:1136:4: (lv_right_3_0= ruleRight )
            {
            // InternalSML.g:1136:4: (lv_right_3_0= ruleRight )
            // InternalSML.g:1137:5: lv_right_3_0= ruleRight
            {

            					newCompositeNode(grammarAccess.getMergeNonRecuAccess().getRightRightParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_14);
            lv_right_3_0=ruleRight();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeNonRecuRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"xtext.SML.Right");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:1154:3: ( (lv_fm_4_0= ruleFinMerge ) )
            // InternalSML.g:1155:4: (lv_fm_4_0= ruleFinMerge )
            {
            // InternalSML.g:1155:4: (lv_fm_4_0= ruleFinMerge )
            // InternalSML.g:1156:5: lv_fm_4_0= ruleFinMerge
            {

            					newCompositeNode(grammarAccess.getMergeNonRecuAccess().getFmFinMergeParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_4_0=ruleFinMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeNonRecuRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_4_0,
            						"xtext.SML.FinMerge");
            					afterParserOrEnumRuleCall();
            				

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
    // $ANTLR end "ruleMergeNonRecu"


    // $ANTLR start "entryRuleMerge"
    // InternalSML.g:1177:1: entryRuleMerge returns [EObject current=null] : iv_ruleMerge= ruleMerge EOF ;
    public final EObject entryRuleMerge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMerge = null;


        try {
            // InternalSML.g:1177:46: (iv_ruleMerge= ruleMerge EOF )
            // InternalSML.g:1178:2: iv_ruleMerge= ruleMerge EOF
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
    // InternalSML.g:1184:1: ruleMerge returns [EObject current=null] : ( ( (lv_mnr_0_0= ruleMergeNonRecu ) ) | ( (lv_mr_1_0= ruleMergeRecu ) ) ) ;
    public final EObject ruleMerge() throws RecognitionException {
        EObject current = null;

        EObject lv_mnr_0_0 = null;

        EObject lv_mr_1_0 = null;



        	enterRule();

        try {
            // InternalSML.g:1190:2: ( ( ( (lv_mnr_0_0= ruleMergeNonRecu ) ) | ( (lv_mr_1_0= ruleMergeRecu ) ) ) )
            // InternalSML.g:1191:2: ( ( (lv_mnr_0_0= ruleMergeNonRecu ) ) | ( (lv_mr_1_0= ruleMergeRecu ) ) )
            {
            // InternalSML.g:1191:2: ( ( (lv_mnr_0_0= ruleMergeNonRecu ) ) | ( (lv_mr_1_0= ruleMergeRecu ) ) )
            int alt20=2;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // InternalSML.g:1192:3: ( (lv_mnr_0_0= ruleMergeNonRecu ) )
                    {
                    // InternalSML.g:1192:3: ( (lv_mnr_0_0= ruleMergeNonRecu ) )
                    // InternalSML.g:1193:4: (lv_mnr_0_0= ruleMergeNonRecu )
                    {
                    // InternalSML.g:1193:4: (lv_mnr_0_0= ruleMergeNonRecu )
                    // InternalSML.g:1194:5: lv_mnr_0_0= ruleMergeNonRecu
                    {

                    					newCompositeNode(grammarAccess.getMergeAccess().getMnrMergeNonRecuParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_mnr_0_0=ruleMergeNonRecu();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getMergeRule());
                    					}
                    					set(
                    						current,
                    						"mnr",
                    						lv_mnr_0_0,
                    						"xtext.SML.MergeNonRecu");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSML.g:1212:3: ( (lv_mr_1_0= ruleMergeRecu ) )
                    {
                    // InternalSML.g:1212:3: ( (lv_mr_1_0= ruleMergeRecu ) )
                    // InternalSML.g:1213:4: (lv_mr_1_0= ruleMergeRecu )
                    {
                    // InternalSML.g:1213:4: (lv_mr_1_0= ruleMergeRecu )
                    // InternalSML.g:1214:5: lv_mr_1_0= ruleMergeRecu
                    {

                    					newCompositeNode(grammarAccess.getMergeAccess().getMrMergeRecuParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_mr_1_0=ruleMergeRecu();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getMergeRule());
                    					}
                    					set(
                    						current,
                    						"mr",
                    						lv_mr_1_0,
                    						"xtext.SML.MergeRecu");
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
    // $ANTLR end "ruleMerge"


    // $ANTLR start "entryRuleHighway"
    // InternalSML.g:1235:1: entryRuleHighway returns [EObject current=null] : iv_ruleHighway= ruleHighway EOF ;
    public final EObject entryRuleHighway() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHighway = null;


        try {
            // InternalSML.g:1235:48: (iv_ruleHighway= ruleHighway EOF )
            // InternalSML.g:1236:2: iv_ruleHighway= ruleHighway EOF
            {
             newCompositeNode(grammarAccess.getHighwayRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHighway=ruleHighway();

            state._fsp--;

             current =iv_ruleHighway; 
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
    // $ANTLR end "entryRuleHighway"


    // $ANTLR start "ruleHighway"
    // InternalSML.g:1242:1: ruleHighway returns [EObject current=null] : ( ( (lv_db_0_0= ruleDebutMerge ) ) ( ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ',' )+ ( (lv_fm_5_0= ruleFinMerge ) ) ) ;
    public final EObject ruleHighway() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_db_0_0 = null;

        EObject lv_leftNonRec_1_0 = null;

        EObject lv_conv_3_0 = null;

        AntlrDatatypeRuleToken lv_fm_5_0 = null;



        	enterRule();

        try {
            // InternalSML.g:1248:2: ( ( ( (lv_db_0_0= ruleDebutMerge ) ) ( ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ',' )+ ( (lv_fm_5_0= ruleFinMerge ) ) ) )
            // InternalSML.g:1249:2: ( ( (lv_db_0_0= ruleDebutMerge ) ) ( ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ',' )+ ( (lv_fm_5_0= ruleFinMerge ) ) )
            {
            // InternalSML.g:1249:2: ( ( (lv_db_0_0= ruleDebutMerge ) ) ( ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ',' )+ ( (lv_fm_5_0= ruleFinMerge ) ) )
            // InternalSML.g:1250:3: ( (lv_db_0_0= ruleDebutMerge ) ) ( ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ',' )+ ( (lv_fm_5_0= ruleFinMerge ) )
            {
            // InternalSML.g:1250:3: ( (lv_db_0_0= ruleDebutMerge ) )
            // InternalSML.g:1251:4: (lv_db_0_0= ruleDebutMerge )
            {
            // InternalSML.g:1251:4: (lv_db_0_0= ruleDebutMerge )
            // InternalSML.g:1252:5: lv_db_0_0= ruleDebutMerge
            {

            					newCompositeNode(grammarAccess.getHighwayAccess().getDbDebutMergeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_17);
            lv_db_0_0=ruleDebutMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getHighwayRule());
            					}
            					add(
            						current,
            						"db",
            						lv_db_0_0,
            						"xtext.SML.DebutMerge");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSML.g:1269:3: ( ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ',' )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=14 && LA21_0<=15)||LA21_0==18||LA21_0==20||LA21_0==22) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalSML.g:1270:4: ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) ) otherlv_2= '|' ( (lv_conv_3_0= ruleConvolution ) ) otherlv_4= ','
            	    {
            	    // InternalSML.g:1270:4: ( (lv_leftNonRec_1_0= ruleLeftNonRecursive ) )
            	    // InternalSML.g:1271:5: (lv_leftNonRec_1_0= ruleLeftNonRecursive )
            	    {
            	    // InternalSML.g:1271:5: (lv_leftNonRec_1_0= ruleLeftNonRecursive )
            	    // InternalSML.g:1272:6: lv_leftNonRec_1_0= ruleLeftNonRecursive
            	    {

            	    						newCompositeNode(grammarAccess.getHighwayAccess().getLeftNonRecLeftNonRecursiveParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_leftNonRec_1_0=ruleLeftNonRecursive();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getHighwayRule());
            	    						}
            	    						add(
            	    							current,
            	    							"leftNonRec",
            	    							lv_leftNonRec_1_0,
            	    							"xtext.SML.LeftNonRecursive");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_2=(Token)match(input,29,FOLLOW_15); 

            	    				newLeafNode(otherlv_2, grammarAccess.getHighwayAccess().getVerticalLineKeyword_1_1());
            	    			
            	    // InternalSML.g:1293:4: ( (lv_conv_3_0= ruleConvolution ) )
            	    // InternalSML.g:1294:5: (lv_conv_3_0= ruleConvolution )
            	    {
            	    // InternalSML.g:1294:5: (lv_conv_3_0= ruleConvolution )
            	    // InternalSML.g:1295:6: lv_conv_3_0= ruleConvolution
            	    {

            	    						newCompositeNode(grammarAccess.getHighwayAccess().getConvConvolutionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_conv_3_0=ruleConvolution();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getHighwayRule());
            	    						}
            	    						add(
            	    							current,
            	    							"conv",
            	    							lv_conv_3_0,
            	    							"xtext.SML.Convolution");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,27,FOLLOW_19); 

            	    				newLeafNode(otherlv_4, grammarAccess.getHighwayAccess().getCommaKeyword_1_3());
            	    			

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);

            // InternalSML.g:1317:3: ( (lv_fm_5_0= ruleFinMerge ) )
            // InternalSML.g:1318:4: (lv_fm_5_0= ruleFinMerge )
            {
            // InternalSML.g:1318:4: (lv_fm_5_0= ruleFinMerge )
            // InternalSML.g:1319:5: lv_fm_5_0= ruleFinMerge
            {

            					newCompositeNode(grammarAccess.getHighwayAccess().getFmFinMergeParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_5_0=ruleFinMerge();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getHighwayRule());
            					}
            					add(
            						current,
            						"fm",
            						lv_fm_5_0,
            						"xtext.SML.FinMerge");
            					afterParserOrEnumRuleCall();
            				

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
    // $ANTLR end "ruleHighway"


    // $ANTLR start "entryRuleFeatureExtraction"
    // InternalSML.g:1340:1: entryRuleFeatureExtraction returns [EObject current=null] : iv_ruleFeatureExtraction= ruleFeatureExtraction EOF ;
    public final EObject entryRuleFeatureExtraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureExtraction = null;


        try {
            // InternalSML.g:1340:58: (iv_ruleFeatureExtraction= ruleFeatureExtraction EOF )
            // InternalSML.g:1341:2: iv_ruleFeatureExtraction= ruleFeatureExtraction EOF
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
    // InternalSML.g:1347:1: ruleFeatureExtraction returns [EObject current=null] : ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) ) ( (lv_drop_3_0= ruleDropout ) )? ( (lv_pool_4_0= rulePooling ) )? ) ;
    public final EObject ruleFeatureExtraction() throws RecognitionException {
        EObject current = null;

        EObject lv_conv_0_0 = null;

        EObject lv_merge_1_0 = null;

        EObject lv_hw_2_0 = null;

        AntlrDatatypeRuleToken lv_drop_3_0 = null;

        AntlrDatatypeRuleToken lv_pool_4_0 = null;



        	enterRule();

        try {
            // InternalSML.g:1353:2: ( ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) ) ( (lv_drop_3_0= ruleDropout ) )? ( (lv_pool_4_0= rulePooling ) )? ) )
            // InternalSML.g:1354:2: ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) ) ( (lv_drop_3_0= ruleDropout ) )? ( (lv_pool_4_0= rulePooling ) )? )
            {
            // InternalSML.g:1354:2: ( ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) ) ( (lv_drop_3_0= ruleDropout ) )? ( (lv_pool_4_0= rulePooling ) )? )
            // InternalSML.g:1355:3: ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) ) ( (lv_drop_3_0= ruleDropout ) )? ( (lv_pool_4_0= rulePooling ) )?
            {
            // InternalSML.g:1355:3: ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) )
            int alt22=3;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // InternalSML.g:1356:4: ( (lv_conv_0_0= ruleConvolution ) )
                    {
                    // InternalSML.g:1356:4: ( (lv_conv_0_0= ruleConvolution ) )
                    // InternalSML.g:1357:5: (lv_conv_0_0= ruleConvolution )
                    {
                    // InternalSML.g:1357:5: (lv_conv_0_0= ruleConvolution )
                    // InternalSML.g:1358:6: lv_conv_0_0= ruleConvolution
                    {

                    						newCompositeNode(grammarAccess.getFeatureExtractionAccess().getConvConvolutionParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_20);
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
                    // InternalSML.g:1376:4: ( (lv_merge_1_0= ruleMerge ) )
                    {
                    // InternalSML.g:1376:4: ( (lv_merge_1_0= ruleMerge ) )
                    // InternalSML.g:1377:5: (lv_merge_1_0= ruleMerge )
                    {
                    // InternalSML.g:1377:5: (lv_merge_1_0= ruleMerge )
                    // InternalSML.g:1378:6: lv_merge_1_0= ruleMerge
                    {

                    						newCompositeNode(grammarAccess.getFeatureExtractionAccess().getMergeMergeParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_20);
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
                case 3 :
                    // InternalSML.g:1396:4: ( (lv_hw_2_0= ruleHighway ) )
                    {
                    // InternalSML.g:1396:4: ( (lv_hw_2_0= ruleHighway ) )
                    // InternalSML.g:1397:5: (lv_hw_2_0= ruleHighway )
                    {
                    // InternalSML.g:1397:5: (lv_hw_2_0= ruleHighway )
                    // InternalSML.g:1398:6: lv_hw_2_0= ruleHighway
                    {

                    						newCompositeNode(grammarAccess.getFeatureExtractionAccess().getHwHighwayParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_20);
                    lv_hw_2_0=ruleHighway();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    						}
                    						set(
                    							current,
                    							"hw",
                    							lv_hw_2_0,
                    							"xtext.SML.Highway");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalSML.g:1416:3: ( (lv_drop_3_0= ruleDropout ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==13) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalSML.g:1417:4: (lv_drop_3_0= ruleDropout )
                    {
                    // InternalSML.g:1417:4: (lv_drop_3_0= ruleDropout )
                    // InternalSML.g:1418:5: lv_drop_3_0= ruleDropout
                    {

                    					newCompositeNode(grammarAccess.getFeatureExtractionAccess().getDropDropoutParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_21);
                    lv_drop_3_0=ruleDropout();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    					}
                    					set(
                    						current,
                    						"drop",
                    						lv_drop_3_0,
                    						"xtext.SML.Dropout");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalSML.g:1435:3: ( (lv_pool_4_0= rulePooling ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=14 && LA24_0<=15)) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalSML.g:1436:4: (lv_pool_4_0= rulePooling )
                    {
                    // InternalSML.g:1436:4: (lv_pool_4_0= rulePooling )
                    // InternalSML.g:1437:5: lv_pool_4_0= rulePooling
                    {

                    					newCompositeNode(grammarAccess.getFeatureExtractionAccess().getPoolPoolingParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_pool_4_0=rulePooling();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFeatureExtractionRule());
                    					}
                    					set(
                    						current,
                    						"pool",
                    						lv_pool_4_0,
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


    protected DFA20 dfa20 = new DFA20(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String dfa_1s = "\20\uffff";
    static final String dfa_2s = "\1\20\1\16\2\20\1\23\2\15\1\uffff\1\23\1\24\1\16\1\uffff\2\25\2\15";
    static final String dfa_3s = "\1\20\3\26\1\24\2\33\1\uffff\1\23\1\24\1\33\1\uffff\2\25\2\33";
    static final String dfa_4s = "\7\uffff\1\2\3\uffff\1\1\4\uffff";
    static final String dfa_5s = "\20\uffff}>";
    static final String[] dfa_6s = {
            "\1\1",
            "\1\2\1\3\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6",
            "\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6",
            "\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6",
            "\1\11\1\10",
            "\1\12\2\13\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6\4\uffff\1\13",
            "\1\12\2\13\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6\4\uffff\1\13",
            "",
            "\1\14",
            "\1\15",
            "\2\13\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6\4\uffff\1\13",
            "",
            "\1\16",
            "\1\17",
            "\1\12\2\13\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6\4\uffff\1\13",
            "\1\12\2\13\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\6\4\uffff\1\13"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1191:2: ( ( (lv_mnr_0_0= ruleMergeNonRecu ) ) | ( (lv_mr_1_0= ruleMergeRecu ) ) )";
        }
    }
    static final String dfa_7s = "\23\uffff";
    static final String dfa_8s = "\1\20\1\uffff\1\16\2\20\1\23\2\15\1\uffff\1\24\1\23\1\16\2\33\1\uffff\2\25\2\15";
    static final String dfa_9s = "\1\26\1\uffff\3\26\1\24\2\35\1\uffff\1\24\1\23\3\35\1\uffff\2\25\2\35";
    static final String dfa_10s = "\1\uffff\1\1\6\uffff\1\2\5\uffff\1\3\4\uffff";
    static final String dfa_11s = "\23\uffff}>";
    static final String[] dfa_12s = {
            "\1\2\1\uffff\1\1\1\uffff\1\1\1\uffff\1\1",
            "",
            "\1\3\1\4\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7",
            "\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7",
            "\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7",
            "\1\11\1\12",
            "\1\13\1\14\1\15\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7\4\uffff\1\10\1\uffff\1\16",
            "\1\13\1\14\1\15\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7\4\uffff\1\10\1\uffff\1\16",
            "",
            "\1\17",
            "\1\20",
            "\1\14\1\15\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7\4\uffff\1\10\1\uffff\1\16",
            "\1\10\1\uffff\1\16",
            "\1\10\1\uffff\1\16",
            "",
            "\1\21",
            "\1\22",
            "\1\13\1\14\1\15\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7\4\uffff\1\10\1\uffff\1\16",
            "\1\13\1\14\1\15\1\10\1\uffff\1\5\1\uffff\1\6\1\uffff\1\7\4\uffff\1\10\1\uffff\1\16"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "1355:3: ( ( (lv_conv_0_0= ruleConvolution ) ) | ( (lv_merge_1_0= ruleMerge ) ) | ( (lv_hw_2_0= ruleHighway ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000550000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000003D51000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000004001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x000000000054C002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x000000000055C000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000010540000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000540000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000540002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000000054C000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000000000056C000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000000000E002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000000000C002L});

}