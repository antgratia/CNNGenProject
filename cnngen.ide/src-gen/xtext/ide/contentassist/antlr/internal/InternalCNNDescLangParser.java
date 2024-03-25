package xtext.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import xtext.services.CNNDescLangGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalCNNDescLangParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'dropout'", "'avg_pooling'", "'max_pooling'", "'global_avg_pooling'", "'global_max_pooling'", "'('", "')'", "'input'", "'output'", "'bnconv'", "'convbn'", "'conv'", "'upconv'", "'flatten'", "'dense'", "'Empty'", "','", "'['", "']'"
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


        public InternalCNNDescLangParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalCNNDescLangParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalCNNDescLangParser.tokenNames; }
    public String getGrammarFileName() { return "InternalCNNDescLang.g"; }


    	private CNNDescLangGrammarAccess grammarAccess;

    	public void setGrammarAccess(CNNDescLangGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleCNNDescLang"
    // InternalCNNDescLang.g:53:1: entryRuleCNNDescLang : ruleCNNDescLang EOF ;
    public final void entryRuleCNNDescLang() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:54:1: ( ruleCNNDescLang EOF )
            // InternalCNNDescLang.g:55:1: ruleCNNDescLang EOF
            {
             before(grammarAccess.getCNNDescLangRule()); 
            pushFollow(FOLLOW_1);
            ruleCNNDescLang();

            state._fsp--;

             after(grammarAccess.getCNNDescLangRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCNNDescLang"


    // $ANTLR start "ruleCNNDescLang"
    // InternalCNNDescLang.g:62:1: ruleCNNDescLang : ( ( rule__CNNDescLang__CnndesclangAssignment ) ) ;
    public final void ruleCNNDescLang() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:66:2: ( ( ( rule__CNNDescLang__CnndesclangAssignment ) ) )
            // InternalCNNDescLang.g:67:2: ( ( rule__CNNDescLang__CnndesclangAssignment ) )
            {
            // InternalCNNDescLang.g:67:2: ( ( rule__CNNDescLang__CnndesclangAssignment ) )
            // InternalCNNDescLang.g:68:3: ( rule__CNNDescLang__CnndesclangAssignment )
            {
             before(grammarAccess.getCNNDescLangAccess().getCnndesclangAssignment()); 
            // InternalCNNDescLang.g:69:3: ( rule__CNNDescLang__CnndesclangAssignment )
            // InternalCNNDescLang.g:69:4: rule__CNNDescLang__CnndesclangAssignment
            {
            pushFollow(FOLLOW_2);
            rule__CNNDescLang__CnndesclangAssignment();

            state._fsp--;


            }

             after(grammarAccess.getCNNDescLangAccess().getCnndesclangAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCNNDescLang"


    // $ANTLR start "entryRuleArchitecture"
    // InternalCNNDescLang.g:78:1: entryRuleArchitecture : ruleArchitecture EOF ;
    public final void entryRuleArchitecture() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:79:1: ( ruleArchitecture EOF )
            // InternalCNNDescLang.g:80:1: ruleArchitecture EOF
            {
             before(grammarAccess.getArchitectureRule()); 
            pushFollow(FOLLOW_1);
            ruleArchitecture();

            state._fsp--;

             after(grammarAccess.getArchitectureRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleArchitecture"


    // $ANTLR start "ruleArchitecture"
    // InternalCNNDescLang.g:87:1: ruleArchitecture : ( ( rule__Architecture__Group__0 ) ) ;
    public final void ruleArchitecture() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:91:2: ( ( ( rule__Architecture__Group__0 ) ) )
            // InternalCNNDescLang.g:92:2: ( ( rule__Architecture__Group__0 ) )
            {
            // InternalCNNDescLang.g:92:2: ( ( rule__Architecture__Group__0 ) )
            // InternalCNNDescLang.g:93:3: ( rule__Architecture__Group__0 )
            {
             before(grammarAccess.getArchitectureAccess().getGroup()); 
            // InternalCNNDescLang.g:94:3: ( rule__Architecture__Group__0 )
            // InternalCNNDescLang.g:94:4: rule__Architecture__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Architecture__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getArchitectureAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleArchitecture"


    // $ANTLR start "entryRuleDropout"
    // InternalCNNDescLang.g:103:1: entryRuleDropout : ruleDropout EOF ;
    public final void entryRuleDropout() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:104:1: ( ruleDropout EOF )
            // InternalCNNDescLang.g:105:1: ruleDropout EOF
            {
             before(grammarAccess.getDropoutRule()); 
            pushFollow(FOLLOW_1);
            ruleDropout();

            state._fsp--;

             after(grammarAccess.getDropoutRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDropout"


    // $ANTLR start "ruleDropout"
    // InternalCNNDescLang.g:112:1: ruleDropout : ( 'dropout' ) ;
    public final void ruleDropout() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:116:2: ( ( 'dropout' ) )
            // InternalCNNDescLang.g:117:2: ( 'dropout' )
            {
            // InternalCNNDescLang.g:117:2: ( 'dropout' )
            // InternalCNNDescLang.g:118:3: 'dropout'
            {
             before(grammarAccess.getDropoutAccess().getDropoutKeyword()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getDropoutAccess().getDropoutKeyword()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDropout"


    // $ANTLR start "entryRulePooling"
    // InternalCNNDescLang.g:128:1: entryRulePooling : rulePooling EOF ;
    public final void entryRulePooling() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:129:1: ( rulePooling EOF )
            // InternalCNNDescLang.g:130:1: rulePooling EOF
            {
             before(grammarAccess.getPoolingRule()); 
            pushFollow(FOLLOW_1);
            rulePooling();

            state._fsp--;

             after(grammarAccess.getPoolingRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePooling"


    // $ANTLR start "rulePooling"
    // InternalCNNDescLang.g:137:1: rulePooling : ( ( rule__Pooling__Alternatives ) ) ;
    public final void rulePooling() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:141:2: ( ( ( rule__Pooling__Alternatives ) ) )
            // InternalCNNDescLang.g:142:2: ( ( rule__Pooling__Alternatives ) )
            {
            // InternalCNNDescLang.g:142:2: ( ( rule__Pooling__Alternatives ) )
            // InternalCNNDescLang.g:143:3: ( rule__Pooling__Alternatives )
            {
             before(grammarAccess.getPoolingAccess().getAlternatives()); 
            // InternalCNNDescLang.g:144:3: ( rule__Pooling__Alternatives )
            // InternalCNNDescLang.g:144:4: rule__Pooling__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Pooling__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPoolingAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePooling"


    // $ANTLR start "entryRuleConvolution"
    // InternalCNNDescLang.g:153:1: entryRuleConvolution : ruleConvolution EOF ;
    public final void entryRuleConvolution() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:154:1: ( ruleConvolution EOF )
            // InternalCNNDescLang.g:155:1: ruleConvolution EOF
            {
             before(grammarAccess.getConvolutionRule()); 
            pushFollow(FOLLOW_1);
            ruleConvolution();

            state._fsp--;

             after(grammarAccess.getConvolutionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConvolution"


    // $ANTLR start "ruleConvolution"
    // InternalCNNDescLang.g:162:1: ruleConvolution : ( ( rule__Convolution__Alternatives ) ) ;
    public final void ruleConvolution() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:166:2: ( ( ( rule__Convolution__Alternatives ) ) )
            // InternalCNNDescLang.g:167:2: ( ( rule__Convolution__Alternatives ) )
            {
            // InternalCNNDescLang.g:167:2: ( ( rule__Convolution__Alternatives ) )
            // InternalCNNDescLang.g:168:3: ( rule__Convolution__Alternatives )
            {
             before(grammarAccess.getConvolutionAccess().getAlternatives()); 
            // InternalCNNDescLang.g:169:3: ( rule__Convolution__Alternatives )
            // InternalCNNDescLang.g:169:4: rule__Convolution__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Convolution__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getConvolutionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConvolution"


    // $ANTLR start "entryRuleGlobalPooling"
    // InternalCNNDescLang.g:178:1: entryRuleGlobalPooling : ruleGlobalPooling EOF ;
    public final void entryRuleGlobalPooling() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:179:1: ( ruleGlobalPooling EOF )
            // InternalCNNDescLang.g:180:1: ruleGlobalPooling EOF
            {
             before(grammarAccess.getGlobalPoolingRule()); 
            pushFollow(FOLLOW_1);
            ruleGlobalPooling();

            state._fsp--;

             after(grammarAccess.getGlobalPoolingRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGlobalPooling"


    // $ANTLR start "ruleGlobalPooling"
    // InternalCNNDescLang.g:187:1: ruleGlobalPooling : ( ( rule__GlobalPooling__Alternatives ) ) ;
    public final void ruleGlobalPooling() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:191:2: ( ( ( rule__GlobalPooling__Alternatives ) ) )
            // InternalCNNDescLang.g:192:2: ( ( rule__GlobalPooling__Alternatives ) )
            {
            // InternalCNNDescLang.g:192:2: ( ( rule__GlobalPooling__Alternatives ) )
            // InternalCNNDescLang.g:193:3: ( rule__GlobalPooling__Alternatives )
            {
             before(grammarAccess.getGlobalPoolingAccess().getAlternatives()); 
            // InternalCNNDescLang.g:194:3: ( rule__GlobalPooling__Alternatives )
            // InternalCNNDescLang.g:194:4: rule__GlobalPooling__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__GlobalPooling__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getGlobalPoolingAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGlobalPooling"


    // $ANTLR start "entryRuleFlattenOrGlobal"
    // InternalCNNDescLang.g:203:1: entryRuleFlattenOrGlobal : ruleFlattenOrGlobal EOF ;
    public final void entryRuleFlattenOrGlobal() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:204:1: ( ruleFlattenOrGlobal EOF )
            // InternalCNNDescLang.g:205:1: ruleFlattenOrGlobal EOF
            {
             before(grammarAccess.getFlattenOrGlobalRule()); 
            pushFollow(FOLLOW_1);
            ruleFlattenOrGlobal();

            state._fsp--;

             after(grammarAccess.getFlattenOrGlobalRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFlattenOrGlobal"


    // $ANTLR start "ruleFlattenOrGlobal"
    // InternalCNNDescLang.g:212:1: ruleFlattenOrGlobal : ( ( rule__FlattenOrGlobal__Alternatives ) ) ;
    public final void ruleFlattenOrGlobal() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:216:2: ( ( ( rule__FlattenOrGlobal__Alternatives ) ) )
            // InternalCNNDescLang.g:217:2: ( ( rule__FlattenOrGlobal__Alternatives ) )
            {
            // InternalCNNDescLang.g:217:2: ( ( rule__FlattenOrGlobal__Alternatives ) )
            // InternalCNNDescLang.g:218:3: ( rule__FlattenOrGlobal__Alternatives )
            {
             before(grammarAccess.getFlattenOrGlobalAccess().getAlternatives()); 
            // InternalCNNDescLang.g:219:3: ( rule__FlattenOrGlobal__Alternatives )
            // InternalCNNDescLang.g:219:4: rule__FlattenOrGlobal__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__FlattenOrGlobal__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getFlattenOrGlobalAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFlattenOrGlobal"


    // $ANTLR start "entryRuleInterstice"
    // InternalCNNDescLang.g:228:1: entryRuleInterstice : ruleInterstice EOF ;
    public final void entryRuleInterstice() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:229:1: ( ruleInterstice EOF )
            // InternalCNNDescLang.g:230:1: ruleInterstice EOF
            {
             before(grammarAccess.getIntersticeRule()); 
            pushFollow(FOLLOW_1);
            ruleInterstice();

            state._fsp--;

             after(grammarAccess.getIntersticeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInterstice"


    // $ANTLR start "ruleInterstice"
    // InternalCNNDescLang.g:237:1: ruleInterstice : ( ( rule__Interstice__FgAssignment ) ) ;
    public final void ruleInterstice() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:241:2: ( ( ( rule__Interstice__FgAssignment ) ) )
            // InternalCNNDescLang.g:242:2: ( ( rule__Interstice__FgAssignment ) )
            {
            // InternalCNNDescLang.g:242:2: ( ( rule__Interstice__FgAssignment ) )
            // InternalCNNDescLang.g:243:3: ( rule__Interstice__FgAssignment )
            {
             before(grammarAccess.getIntersticeAccess().getFgAssignment()); 
            // InternalCNNDescLang.g:244:3: ( rule__Interstice__FgAssignment )
            // InternalCNNDescLang.g:244:4: rule__Interstice__FgAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Interstice__FgAssignment();

            state._fsp--;


            }

             after(grammarAccess.getIntersticeAccess().getFgAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInterstice"


    // $ANTLR start "entryRuleClassification"
    // InternalCNNDescLang.g:253:1: entryRuleClassification : ruleClassification EOF ;
    public final void entryRuleClassification() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:254:1: ( ruleClassification EOF )
            // InternalCNNDescLang.g:255:1: ruleClassification EOF
            {
             before(grammarAccess.getClassificationRule()); 
            pushFollow(FOLLOW_1);
            ruleClassification();

            state._fsp--;

             after(grammarAccess.getClassificationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleClassification"


    // $ANTLR start "ruleClassification"
    // InternalCNNDescLang.g:262:1: ruleClassification : ( ( rule__Classification__Group__0 ) ) ;
    public final void ruleClassification() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:266:2: ( ( ( rule__Classification__Group__0 ) ) )
            // InternalCNNDescLang.g:267:2: ( ( rule__Classification__Group__0 ) )
            {
            // InternalCNNDescLang.g:267:2: ( ( rule__Classification__Group__0 ) )
            // InternalCNNDescLang.g:268:3: ( rule__Classification__Group__0 )
            {
             before(grammarAccess.getClassificationAccess().getGroup()); 
            // InternalCNNDescLang.g:269:3: ( rule__Classification__Group__0 )
            // InternalCNNDescLang.g:269:4: rule__Classification__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Classification__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getClassificationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleClassification"


    // $ANTLR start "entryRuleConvDrop"
    // InternalCNNDescLang.g:278:1: entryRuleConvDrop : ruleConvDrop EOF ;
    public final void entryRuleConvDrop() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:279:1: ( ruleConvDrop EOF )
            // InternalCNNDescLang.g:280:1: ruleConvDrop EOF
            {
             before(grammarAccess.getConvDropRule()); 
            pushFollow(FOLLOW_1);
            ruleConvDrop();

            state._fsp--;

             after(grammarAccess.getConvDropRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConvDrop"


    // $ANTLR start "ruleConvDrop"
    // InternalCNNDescLang.g:287:1: ruleConvDrop : ( ( rule__ConvDrop__Group__0 ) ) ;
    public final void ruleConvDrop() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:291:2: ( ( ( rule__ConvDrop__Group__0 ) ) )
            // InternalCNNDescLang.g:292:2: ( ( rule__ConvDrop__Group__0 ) )
            {
            // InternalCNNDescLang.g:292:2: ( ( rule__ConvDrop__Group__0 ) )
            // InternalCNNDescLang.g:293:3: ( rule__ConvDrop__Group__0 )
            {
             before(grammarAccess.getConvDropAccess().getGroup()); 
            // InternalCNNDescLang.g:294:3: ( rule__ConvDrop__Group__0 )
            // InternalCNNDescLang.g:294:4: rule__ConvDrop__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ConvDrop__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConvDropAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConvDrop"


    // $ANTLR start "entryRuleMergeConv"
    // InternalCNNDescLang.g:303:1: entryRuleMergeConv : ruleMergeConv EOF ;
    public final void entryRuleMergeConv() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:304:1: ( ruleMergeConv EOF )
            // InternalCNNDescLang.g:305:1: ruleMergeConv EOF
            {
             before(grammarAccess.getMergeConvRule()); 
            pushFollow(FOLLOW_1);
            ruleMergeConv();

            state._fsp--;

             after(grammarAccess.getMergeConvRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMergeConv"


    // $ANTLR start "ruleMergeConv"
    // InternalCNNDescLang.g:312:1: ruleMergeConv : ( ( rule__MergeConv__Group__0 ) ) ;
    public final void ruleMergeConv() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:316:2: ( ( ( rule__MergeConv__Group__0 ) ) )
            // InternalCNNDescLang.g:317:2: ( ( rule__MergeConv__Group__0 ) )
            {
            // InternalCNNDescLang.g:317:2: ( ( rule__MergeConv__Group__0 ) )
            // InternalCNNDescLang.g:318:3: ( rule__MergeConv__Group__0 )
            {
             before(grammarAccess.getMergeConvAccess().getGroup()); 
            // InternalCNNDescLang.g:319:3: ( rule__MergeConv__Group__0 )
            // InternalCNNDescLang.g:319:4: rule__MergeConv__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MergeConv__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMergeConvAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMergeConv"


    // $ANTLR start "entryRuleConvOrMerge"
    // InternalCNNDescLang.g:328:1: entryRuleConvOrMerge : ruleConvOrMerge EOF ;
    public final void entryRuleConvOrMerge() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:329:1: ( ruleConvOrMerge EOF )
            // InternalCNNDescLang.g:330:1: ruleConvOrMerge EOF
            {
             before(grammarAccess.getConvOrMergeRule()); 
            pushFollow(FOLLOW_1);
            ruleConvOrMerge();

            state._fsp--;

             after(grammarAccess.getConvOrMergeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConvOrMerge"


    // $ANTLR start "ruleConvOrMerge"
    // InternalCNNDescLang.g:337:1: ruleConvOrMerge : ( ( rule__ConvOrMerge__Alternatives ) ) ;
    public final void ruleConvOrMerge() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:341:2: ( ( ( rule__ConvOrMerge__Alternatives ) ) )
            // InternalCNNDescLang.g:342:2: ( ( rule__ConvOrMerge__Alternatives ) )
            {
            // InternalCNNDescLang.g:342:2: ( ( rule__ConvOrMerge__Alternatives ) )
            // InternalCNNDescLang.g:343:3: ( rule__ConvOrMerge__Alternatives )
            {
             before(grammarAccess.getConvOrMergeAccess().getAlternatives()); 
            // InternalCNNDescLang.g:344:3: ( rule__ConvOrMerge__Alternatives )
            // InternalCNNDescLang.g:344:4: rule__ConvOrMerge__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ConvOrMerge__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getConvOrMergeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConvOrMerge"


    // $ANTLR start "entryRuleLeft"
    // InternalCNNDescLang.g:353:1: entryRuleLeft : ruleLeft EOF ;
    public final void entryRuleLeft() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:354:1: ( ruleLeft EOF )
            // InternalCNNDescLang.g:355:1: ruleLeft EOF
            {
             before(grammarAccess.getLeftRule()); 
            pushFollow(FOLLOW_1);
            ruleLeft();

            state._fsp--;

             after(grammarAccess.getLeftRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLeft"


    // $ANTLR start "ruleLeft"
    // InternalCNNDescLang.g:362:1: ruleLeft : ( ( rule__Left__Group__0 ) ) ;
    public final void ruleLeft() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:366:2: ( ( ( rule__Left__Group__0 ) ) )
            // InternalCNNDescLang.g:367:2: ( ( rule__Left__Group__0 ) )
            {
            // InternalCNNDescLang.g:367:2: ( ( rule__Left__Group__0 ) )
            // InternalCNNDescLang.g:368:3: ( rule__Left__Group__0 )
            {
             before(grammarAccess.getLeftAccess().getGroup()); 
            // InternalCNNDescLang.g:369:3: ( rule__Left__Group__0 )
            // InternalCNNDescLang.g:369:4: rule__Left__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Left__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLeftAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLeft"


    // $ANTLR start "entryRuleRight"
    // InternalCNNDescLang.g:378:1: entryRuleRight : ruleRight EOF ;
    public final void entryRuleRight() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:379:1: ( ruleRight EOF )
            // InternalCNNDescLang.g:380:1: ruleRight EOF
            {
             before(grammarAccess.getRightRule()); 
            pushFollow(FOLLOW_1);
            ruleRight();

            state._fsp--;

             after(grammarAccess.getRightRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRight"


    // $ANTLR start "ruleRight"
    // InternalCNNDescLang.g:387:1: ruleRight : ( ( rule__Right__Alternatives ) ) ;
    public final void ruleRight() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:391:2: ( ( ( rule__Right__Alternatives ) ) )
            // InternalCNNDescLang.g:392:2: ( ( rule__Right__Alternatives ) )
            {
            // InternalCNNDescLang.g:392:2: ( ( rule__Right__Alternatives ) )
            // InternalCNNDescLang.g:393:3: ( rule__Right__Alternatives )
            {
             before(grammarAccess.getRightAccess().getAlternatives()); 
            // InternalCNNDescLang.g:394:3: ( rule__Right__Alternatives )
            // InternalCNNDescLang.g:394:4: rule__Right__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Right__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getRightAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRight"


    // $ANTLR start "entryRuleMergeBody"
    // InternalCNNDescLang.g:403:1: entryRuleMergeBody : ruleMergeBody EOF ;
    public final void entryRuleMergeBody() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:404:1: ( ruleMergeBody EOF )
            // InternalCNNDescLang.g:405:1: ruleMergeBody EOF
            {
             before(grammarAccess.getMergeBodyRule()); 
            pushFollow(FOLLOW_1);
            ruleMergeBody();

            state._fsp--;

             after(grammarAccess.getMergeBodyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMergeBody"


    // $ANTLR start "ruleMergeBody"
    // InternalCNNDescLang.g:412:1: ruleMergeBody : ( ( rule__MergeBody__Group__0 ) ) ;
    public final void ruleMergeBody() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:416:2: ( ( ( rule__MergeBody__Group__0 ) ) )
            // InternalCNNDescLang.g:417:2: ( ( rule__MergeBody__Group__0 ) )
            {
            // InternalCNNDescLang.g:417:2: ( ( rule__MergeBody__Group__0 ) )
            // InternalCNNDescLang.g:418:3: ( rule__MergeBody__Group__0 )
            {
             before(grammarAccess.getMergeBodyAccess().getGroup()); 
            // InternalCNNDescLang.g:419:3: ( rule__MergeBody__Group__0 )
            // InternalCNNDescLang.g:419:4: rule__MergeBody__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MergeBody__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMergeBodyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMergeBody"


    // $ANTLR start "entryRuleMerge"
    // InternalCNNDescLang.g:428:1: entryRuleMerge : ruleMerge EOF ;
    public final void entryRuleMerge() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:429:1: ( ruleMerge EOF )
            // InternalCNNDescLang.g:430:1: ruleMerge EOF
            {
             before(grammarAccess.getMergeRule()); 
            pushFollow(FOLLOW_1);
            ruleMerge();

            state._fsp--;

             after(grammarAccess.getMergeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMerge"


    // $ANTLR start "ruleMerge"
    // InternalCNNDescLang.g:437:1: ruleMerge : ( ( rule__Merge__Group__0 ) ) ;
    public final void ruleMerge() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:441:2: ( ( ( rule__Merge__Group__0 ) ) )
            // InternalCNNDescLang.g:442:2: ( ( rule__Merge__Group__0 ) )
            {
            // InternalCNNDescLang.g:442:2: ( ( rule__Merge__Group__0 ) )
            // InternalCNNDescLang.g:443:3: ( rule__Merge__Group__0 )
            {
             before(grammarAccess.getMergeAccess().getGroup()); 
            // InternalCNNDescLang.g:444:3: ( rule__Merge__Group__0 )
            // InternalCNNDescLang.g:444:4: rule__Merge__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Merge__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMergeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMerge"


    // $ANTLR start "entryRuleFeatureExtraction"
    // InternalCNNDescLang.g:453:1: entryRuleFeatureExtraction : ruleFeatureExtraction EOF ;
    public final void entryRuleFeatureExtraction() throws RecognitionException {
        try {
            // InternalCNNDescLang.g:454:1: ( ruleFeatureExtraction EOF )
            // InternalCNNDescLang.g:455:1: ruleFeatureExtraction EOF
            {
             before(grammarAccess.getFeatureExtractionRule()); 
            pushFollow(FOLLOW_1);
            ruleFeatureExtraction();

            state._fsp--;

             after(grammarAccess.getFeatureExtractionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFeatureExtraction"


    // $ANTLR start "ruleFeatureExtraction"
    // InternalCNNDescLang.g:462:1: ruleFeatureExtraction : ( ( rule__FeatureExtraction__Group__0 ) ) ;
    public final void ruleFeatureExtraction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:466:2: ( ( ( rule__FeatureExtraction__Group__0 ) ) )
            // InternalCNNDescLang.g:467:2: ( ( rule__FeatureExtraction__Group__0 ) )
            {
            // InternalCNNDescLang.g:467:2: ( ( rule__FeatureExtraction__Group__0 ) )
            // InternalCNNDescLang.g:468:3: ( rule__FeatureExtraction__Group__0 )
            {
             before(grammarAccess.getFeatureExtractionAccess().getGroup()); 
            // InternalCNNDescLang.g:469:3: ( rule__FeatureExtraction__Group__0 )
            // InternalCNNDescLang.g:469:4: rule__FeatureExtraction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FeatureExtraction__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFeatureExtractionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFeatureExtraction"


    // $ANTLR start "rule__Pooling__Alternatives"
    // InternalCNNDescLang.g:477:1: rule__Pooling__Alternatives : ( ( 'avg_pooling' ) | ( 'max_pooling' ) );
    public final void rule__Pooling__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:481:1: ( ( 'avg_pooling' ) | ( 'max_pooling' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            else if ( (LA1_0==13) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalCNNDescLang.g:482:2: ( 'avg_pooling' )
                    {
                    // InternalCNNDescLang.g:482:2: ( 'avg_pooling' )
                    // InternalCNNDescLang.g:483:3: 'avg_pooling'
                    {
                     before(grammarAccess.getPoolingAccess().getAvg_poolingKeyword_0()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getPoolingAccess().getAvg_poolingKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:488:2: ( 'max_pooling' )
                    {
                    // InternalCNNDescLang.g:488:2: ( 'max_pooling' )
                    // InternalCNNDescLang.g:489:3: 'max_pooling'
                    {
                     before(grammarAccess.getPoolingAccess().getMax_poolingKeyword_1()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getPoolingAccess().getMax_poolingKeyword_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Pooling__Alternatives"


    // $ANTLR start "rule__Convolution__Alternatives"
    // InternalCNNDescLang.g:498:1: rule__Convolution__Alternatives : ( ( ( rule__Convolution__BnconvAssignment_0 ) ) | ( ( rule__Convolution__ConvbnAssignment_1 ) ) | ( ( rule__Convolution__ConvAssignment_2 ) ) | ( ( rule__Convolution__UpconvAssignment_3 ) ) );
    public final void rule__Convolution__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:502:1: ( ( ( rule__Convolution__BnconvAssignment_0 ) ) | ( ( rule__Convolution__ConvbnAssignment_1 ) ) | ( ( rule__Convolution__ConvAssignment_2 ) ) | ( ( rule__Convolution__UpconvAssignment_3 ) ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt2=1;
                }
                break;
            case 21:
                {
                alt2=2;
                }
                break;
            case 22:
                {
                alt2=3;
                }
                break;
            case 23:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalCNNDescLang.g:503:2: ( ( rule__Convolution__BnconvAssignment_0 ) )
                    {
                    // InternalCNNDescLang.g:503:2: ( ( rule__Convolution__BnconvAssignment_0 ) )
                    // InternalCNNDescLang.g:504:3: ( rule__Convolution__BnconvAssignment_0 )
                    {
                     before(grammarAccess.getConvolutionAccess().getBnconvAssignment_0()); 
                    // InternalCNNDescLang.g:505:3: ( rule__Convolution__BnconvAssignment_0 )
                    // InternalCNNDescLang.g:505:4: rule__Convolution__BnconvAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Convolution__BnconvAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getConvolutionAccess().getBnconvAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:509:2: ( ( rule__Convolution__ConvbnAssignment_1 ) )
                    {
                    // InternalCNNDescLang.g:509:2: ( ( rule__Convolution__ConvbnAssignment_1 ) )
                    // InternalCNNDescLang.g:510:3: ( rule__Convolution__ConvbnAssignment_1 )
                    {
                     before(grammarAccess.getConvolutionAccess().getConvbnAssignment_1()); 
                    // InternalCNNDescLang.g:511:3: ( rule__Convolution__ConvbnAssignment_1 )
                    // InternalCNNDescLang.g:511:4: rule__Convolution__ConvbnAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Convolution__ConvbnAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getConvolutionAccess().getConvbnAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalCNNDescLang.g:515:2: ( ( rule__Convolution__ConvAssignment_2 ) )
                    {
                    // InternalCNNDescLang.g:515:2: ( ( rule__Convolution__ConvAssignment_2 ) )
                    // InternalCNNDescLang.g:516:3: ( rule__Convolution__ConvAssignment_2 )
                    {
                     before(grammarAccess.getConvolutionAccess().getConvAssignment_2()); 
                    // InternalCNNDescLang.g:517:3: ( rule__Convolution__ConvAssignment_2 )
                    // InternalCNNDescLang.g:517:4: rule__Convolution__ConvAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Convolution__ConvAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getConvolutionAccess().getConvAssignment_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalCNNDescLang.g:521:2: ( ( rule__Convolution__UpconvAssignment_3 ) )
                    {
                    // InternalCNNDescLang.g:521:2: ( ( rule__Convolution__UpconvAssignment_3 ) )
                    // InternalCNNDescLang.g:522:3: ( rule__Convolution__UpconvAssignment_3 )
                    {
                     before(grammarAccess.getConvolutionAccess().getUpconvAssignment_3()); 
                    // InternalCNNDescLang.g:523:3: ( rule__Convolution__UpconvAssignment_3 )
                    // InternalCNNDescLang.g:523:4: rule__Convolution__UpconvAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Convolution__UpconvAssignment_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getConvolutionAccess().getUpconvAssignment_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Convolution__Alternatives"


    // $ANTLR start "rule__GlobalPooling__Alternatives"
    // InternalCNNDescLang.g:531:1: rule__GlobalPooling__Alternatives : ( ( 'global_avg_pooling' ) | ( 'global_max_pooling' ) );
    public final void rule__GlobalPooling__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:535:1: ( ( 'global_avg_pooling' ) | ( 'global_max_pooling' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==15) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalCNNDescLang.g:536:2: ( 'global_avg_pooling' )
                    {
                    // InternalCNNDescLang.g:536:2: ( 'global_avg_pooling' )
                    // InternalCNNDescLang.g:537:3: 'global_avg_pooling'
                    {
                     before(grammarAccess.getGlobalPoolingAccess().getGlobal_avg_poolingKeyword_0()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getGlobalPoolingAccess().getGlobal_avg_poolingKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:542:2: ( 'global_max_pooling' )
                    {
                    // InternalCNNDescLang.g:542:2: ( 'global_max_pooling' )
                    // InternalCNNDescLang.g:543:3: 'global_max_pooling'
                    {
                     before(grammarAccess.getGlobalPoolingAccess().getGlobal_max_poolingKeyword_1()); 
                    match(input,15,FOLLOW_2); 
                     after(grammarAccess.getGlobalPoolingAccess().getGlobal_max_poolingKeyword_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GlobalPooling__Alternatives"


    // $ANTLR start "rule__FlattenOrGlobal__Alternatives"
    // InternalCNNDescLang.g:552:1: rule__FlattenOrGlobal__Alternatives : ( ( ( rule__FlattenOrGlobal__FlatAssignment_0 ) ) | ( ( rule__FlattenOrGlobal__GpAssignment_1 ) ) );
    public final void rule__FlattenOrGlobal__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:556:1: ( ( ( rule__FlattenOrGlobal__FlatAssignment_0 ) ) | ( ( rule__FlattenOrGlobal__GpAssignment_1 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==24) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=14 && LA4_0<=15)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalCNNDescLang.g:557:2: ( ( rule__FlattenOrGlobal__FlatAssignment_0 ) )
                    {
                    // InternalCNNDescLang.g:557:2: ( ( rule__FlattenOrGlobal__FlatAssignment_0 ) )
                    // InternalCNNDescLang.g:558:3: ( rule__FlattenOrGlobal__FlatAssignment_0 )
                    {
                     before(grammarAccess.getFlattenOrGlobalAccess().getFlatAssignment_0()); 
                    // InternalCNNDescLang.g:559:3: ( rule__FlattenOrGlobal__FlatAssignment_0 )
                    // InternalCNNDescLang.g:559:4: rule__FlattenOrGlobal__FlatAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FlattenOrGlobal__FlatAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getFlattenOrGlobalAccess().getFlatAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:563:2: ( ( rule__FlattenOrGlobal__GpAssignment_1 ) )
                    {
                    // InternalCNNDescLang.g:563:2: ( ( rule__FlattenOrGlobal__GpAssignment_1 ) )
                    // InternalCNNDescLang.g:564:3: ( rule__FlattenOrGlobal__GpAssignment_1 )
                    {
                     before(grammarAccess.getFlattenOrGlobalAccess().getGpAssignment_1()); 
                    // InternalCNNDescLang.g:565:3: ( rule__FlattenOrGlobal__GpAssignment_1 )
                    // InternalCNNDescLang.g:565:4: rule__FlattenOrGlobal__GpAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__FlattenOrGlobal__GpAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getFlattenOrGlobalAccess().getGpAssignment_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlattenOrGlobal__Alternatives"


    // $ANTLR start "rule__ConvOrMerge__Alternatives"
    // InternalCNNDescLang.g:573:1: rule__ConvOrMerge__Alternatives : ( ( ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) ) ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* ) ) | ( ( rule__ConvOrMerge__Group_1__0 ) ) );
    public final void rule__ConvOrMerge__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:577:1: ( ( ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) ) ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* ) ) | ( ( rule__ConvOrMerge__Group_1__0 ) ) )
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalCNNDescLang.g:578:2: ( ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) ) ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* ) )
                    {
                    // InternalCNNDescLang.g:578:2: ( ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) ) ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* ) )
                    // InternalCNNDescLang.g:579:3: ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) ) ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* )
                    {
                    // InternalCNNDescLang.g:579:3: ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) )
                    // InternalCNNDescLang.g:580:4: ( rule__ConvOrMerge__ConvdropAssignment_0 )
                    {
                     before(grammarAccess.getConvOrMergeAccess().getConvdropAssignment_0()); 
                    // InternalCNNDescLang.g:581:4: ( rule__ConvOrMerge__ConvdropAssignment_0 )
                    // InternalCNNDescLang.g:581:5: rule__ConvOrMerge__ConvdropAssignment_0
                    {
                    pushFollow(FOLLOW_3);
                    rule__ConvOrMerge__ConvdropAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getConvOrMergeAccess().getConvdropAssignment_0()); 

                    }

                    // InternalCNNDescLang.g:584:3: ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* )
                    // InternalCNNDescLang.g:585:4: ( rule__ConvOrMerge__ConvdropAssignment_0 )*
                    {
                     before(grammarAccess.getConvOrMergeAccess().getConvdropAssignment_0()); 
                    // InternalCNNDescLang.g:586:4: ( rule__ConvOrMerge__ConvdropAssignment_0 )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>=20 && LA5_0<=23)) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalCNNDescLang.g:586:5: rule__ConvOrMerge__ConvdropAssignment_0
                    	    {
                    	    pushFollow(FOLLOW_3);
                    	    rule__ConvOrMerge__ConvdropAssignment_0();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                     after(grammarAccess.getConvOrMergeAccess().getConvdropAssignment_0()); 

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:591:2: ( ( rule__ConvOrMerge__Group_1__0 ) )
                    {
                    // InternalCNNDescLang.g:591:2: ( ( rule__ConvOrMerge__Group_1__0 ) )
                    // InternalCNNDescLang.g:592:3: ( rule__ConvOrMerge__Group_1__0 )
                    {
                     before(grammarAccess.getConvOrMergeAccess().getGroup_1()); 
                    // InternalCNNDescLang.g:593:3: ( rule__ConvOrMerge__Group_1__0 )
                    // InternalCNNDescLang.g:593:4: rule__ConvOrMerge__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ConvOrMerge__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getConvOrMergeAccess().getGroup_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__Alternatives"


    // $ANTLR start "rule__Right__Alternatives"
    // InternalCNNDescLang.g:601:1: rule__Right__Alternatives : ( ( ( ( rule__Right__ConvAssignment_0 ) ) ( ( rule__Right__ConvAssignment_0 )* ) ) | ( ( rule__Right__Group_1__0 ) ) );
    public final void rule__Right__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:605:1: ( ( ( ( rule__Right__ConvAssignment_0 ) ) ( ( rule__Right__ConvAssignment_0 )* ) ) | ( ( rule__Right__Group_1__0 ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=20 && LA8_0<=23)) ) {
                alt8=1;
            }
            else if ( (LA8_0==26) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalCNNDescLang.g:606:2: ( ( ( rule__Right__ConvAssignment_0 ) ) ( ( rule__Right__ConvAssignment_0 )* ) )
                    {
                    // InternalCNNDescLang.g:606:2: ( ( ( rule__Right__ConvAssignment_0 ) ) ( ( rule__Right__ConvAssignment_0 )* ) )
                    // InternalCNNDescLang.g:607:3: ( ( rule__Right__ConvAssignment_0 ) ) ( ( rule__Right__ConvAssignment_0 )* )
                    {
                    // InternalCNNDescLang.g:607:3: ( ( rule__Right__ConvAssignment_0 ) )
                    // InternalCNNDescLang.g:608:4: ( rule__Right__ConvAssignment_0 )
                    {
                     before(grammarAccess.getRightAccess().getConvAssignment_0()); 
                    // InternalCNNDescLang.g:609:4: ( rule__Right__ConvAssignment_0 )
                    // InternalCNNDescLang.g:609:5: rule__Right__ConvAssignment_0
                    {
                    pushFollow(FOLLOW_3);
                    rule__Right__ConvAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRightAccess().getConvAssignment_0()); 

                    }

                    // InternalCNNDescLang.g:612:3: ( ( rule__Right__ConvAssignment_0 )* )
                    // InternalCNNDescLang.g:613:4: ( rule__Right__ConvAssignment_0 )*
                    {
                     before(grammarAccess.getRightAccess().getConvAssignment_0()); 
                    // InternalCNNDescLang.g:614:4: ( rule__Right__ConvAssignment_0 )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>=20 && LA7_0<=23)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalCNNDescLang.g:614:5: rule__Right__ConvAssignment_0
                    	    {
                    	    pushFollow(FOLLOW_3);
                    	    rule__Right__ConvAssignment_0();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                     after(grammarAccess.getRightAccess().getConvAssignment_0()); 

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:619:2: ( ( rule__Right__Group_1__0 ) )
                    {
                    // InternalCNNDescLang.g:619:2: ( ( rule__Right__Group_1__0 ) )
                    // InternalCNNDescLang.g:620:3: ( rule__Right__Group_1__0 )
                    {
                     before(grammarAccess.getRightAccess().getGroup_1()); 
                    // InternalCNNDescLang.g:621:3: ( rule__Right__Group_1__0 )
                    // InternalCNNDescLang.g:621:4: rule__Right__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Right__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRightAccess().getGroup_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__Alternatives"


    // $ANTLR start "rule__FeatureExtraction__Alternatives_0"
    // InternalCNNDescLang.g:629:1: rule__FeatureExtraction__Alternatives_0 : ( ( ( rule__FeatureExtraction__ConvAssignment_0_0 ) ) | ( ( rule__FeatureExtraction__MergeAssignment_0_1 ) ) );
    public final void rule__FeatureExtraction__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:633:1: ( ( ( rule__FeatureExtraction__ConvAssignment_0_0 ) ) | ( ( rule__FeatureExtraction__MergeAssignment_0_1 ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=20 && LA9_0<=23)) ) {
                alt9=1;
            }
            else if ( (LA9_0==28) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalCNNDescLang.g:634:2: ( ( rule__FeatureExtraction__ConvAssignment_0_0 ) )
                    {
                    // InternalCNNDescLang.g:634:2: ( ( rule__FeatureExtraction__ConvAssignment_0_0 ) )
                    // InternalCNNDescLang.g:635:3: ( rule__FeatureExtraction__ConvAssignment_0_0 )
                    {
                     before(grammarAccess.getFeatureExtractionAccess().getConvAssignment_0_0()); 
                    // InternalCNNDescLang.g:636:3: ( rule__FeatureExtraction__ConvAssignment_0_0 )
                    // InternalCNNDescLang.g:636:4: rule__FeatureExtraction__ConvAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FeatureExtraction__ConvAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getFeatureExtractionAccess().getConvAssignment_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalCNNDescLang.g:640:2: ( ( rule__FeatureExtraction__MergeAssignment_0_1 ) )
                    {
                    // InternalCNNDescLang.g:640:2: ( ( rule__FeatureExtraction__MergeAssignment_0_1 ) )
                    // InternalCNNDescLang.g:641:3: ( rule__FeatureExtraction__MergeAssignment_0_1 )
                    {
                     before(grammarAccess.getFeatureExtractionAccess().getMergeAssignment_0_1()); 
                    // InternalCNNDescLang.g:642:3: ( rule__FeatureExtraction__MergeAssignment_0_1 )
                    // InternalCNNDescLang.g:642:4: rule__FeatureExtraction__MergeAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__FeatureExtraction__MergeAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getFeatureExtractionAccess().getMergeAssignment_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Alternatives_0"


    // $ANTLR start "rule__Architecture__Group__0"
    // InternalCNNDescLang.g:650:1: rule__Architecture__Group__0 : rule__Architecture__Group__0__Impl rule__Architecture__Group__1 ;
    public final void rule__Architecture__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:654:1: ( rule__Architecture__Group__0__Impl rule__Architecture__Group__1 )
            // InternalCNNDescLang.g:655:2: rule__Architecture__Group__0__Impl rule__Architecture__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Architecture__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Architecture__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__0"


    // $ANTLR start "rule__Architecture__Group__0__Impl"
    // InternalCNNDescLang.g:662:1: rule__Architecture__Group__0__Impl : ( ( rule__Architecture__InputAssignment_0 ) ) ;
    public final void rule__Architecture__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:666:1: ( ( ( rule__Architecture__InputAssignment_0 ) ) )
            // InternalCNNDescLang.g:667:1: ( ( rule__Architecture__InputAssignment_0 ) )
            {
            // InternalCNNDescLang.g:667:1: ( ( rule__Architecture__InputAssignment_0 ) )
            // InternalCNNDescLang.g:668:2: ( rule__Architecture__InputAssignment_0 )
            {
             before(grammarAccess.getArchitectureAccess().getInputAssignment_0()); 
            // InternalCNNDescLang.g:669:2: ( rule__Architecture__InputAssignment_0 )
            // InternalCNNDescLang.g:669:3: rule__Architecture__InputAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Architecture__InputAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getArchitectureAccess().getInputAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__0__Impl"


    // $ANTLR start "rule__Architecture__Group__1"
    // InternalCNNDescLang.g:677:1: rule__Architecture__Group__1 : rule__Architecture__Group__1__Impl rule__Architecture__Group__2 ;
    public final void rule__Architecture__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:681:1: ( rule__Architecture__Group__1__Impl rule__Architecture__Group__2 )
            // InternalCNNDescLang.g:682:2: rule__Architecture__Group__1__Impl rule__Architecture__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Architecture__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Architecture__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__1"


    // $ANTLR start "rule__Architecture__Group__1__Impl"
    // InternalCNNDescLang.g:689:1: rule__Architecture__Group__1__Impl : ( ( ( rule__Architecture__FeAssignment_1 ) ) ( ( rule__Architecture__FeAssignment_1 )* ) ) ;
    public final void rule__Architecture__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:693:1: ( ( ( ( rule__Architecture__FeAssignment_1 ) ) ( ( rule__Architecture__FeAssignment_1 )* ) ) )
            // InternalCNNDescLang.g:694:1: ( ( ( rule__Architecture__FeAssignment_1 ) ) ( ( rule__Architecture__FeAssignment_1 )* ) )
            {
            // InternalCNNDescLang.g:694:1: ( ( ( rule__Architecture__FeAssignment_1 ) ) ( ( rule__Architecture__FeAssignment_1 )* ) )
            // InternalCNNDescLang.g:695:2: ( ( rule__Architecture__FeAssignment_1 ) ) ( ( rule__Architecture__FeAssignment_1 )* )
            {
            // InternalCNNDescLang.g:695:2: ( ( rule__Architecture__FeAssignment_1 ) )
            // InternalCNNDescLang.g:696:3: ( rule__Architecture__FeAssignment_1 )
            {
             before(grammarAccess.getArchitectureAccess().getFeAssignment_1()); 
            // InternalCNNDescLang.g:697:3: ( rule__Architecture__FeAssignment_1 )
            // InternalCNNDescLang.g:697:4: rule__Architecture__FeAssignment_1
            {
            pushFollow(FOLLOW_6);
            rule__Architecture__FeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getArchitectureAccess().getFeAssignment_1()); 

            }

            // InternalCNNDescLang.g:700:2: ( ( rule__Architecture__FeAssignment_1 )* )
            // InternalCNNDescLang.g:701:3: ( rule__Architecture__FeAssignment_1 )*
            {
             before(grammarAccess.getArchitectureAccess().getFeAssignment_1()); 
            // InternalCNNDescLang.g:702:3: ( rule__Architecture__FeAssignment_1 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=20 && LA10_0<=23)||LA10_0==28) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalCNNDescLang.g:702:4: rule__Architecture__FeAssignment_1
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Architecture__FeAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getArchitectureAccess().getFeAssignment_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__1__Impl"


    // $ANTLR start "rule__Architecture__Group__2"
    // InternalCNNDescLang.g:711:1: rule__Architecture__Group__2 : rule__Architecture__Group__2__Impl rule__Architecture__Group__3 ;
    public final void rule__Architecture__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:715:1: ( rule__Architecture__Group__2__Impl rule__Architecture__Group__3 )
            // InternalCNNDescLang.g:716:2: rule__Architecture__Group__2__Impl rule__Architecture__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Architecture__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Architecture__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__2"


    // $ANTLR start "rule__Architecture__Group__2__Impl"
    // InternalCNNDescLang.g:723:1: rule__Architecture__Group__2__Impl : ( ( rule__Architecture__Group_2__0 )? ) ;
    public final void rule__Architecture__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:727:1: ( ( ( rule__Architecture__Group_2__0 )? ) )
            // InternalCNNDescLang.g:728:1: ( ( rule__Architecture__Group_2__0 )? )
            {
            // InternalCNNDescLang.g:728:1: ( ( rule__Architecture__Group_2__0 )? )
            // InternalCNNDescLang.g:729:2: ( rule__Architecture__Group_2__0 )?
            {
             before(grammarAccess.getArchitectureAccess().getGroup_2()); 
            // InternalCNNDescLang.g:730:2: ( rule__Architecture__Group_2__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=14 && LA11_0<=15)||LA11_0==24) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalCNNDescLang.g:730:3: rule__Architecture__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Architecture__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getArchitectureAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__2__Impl"


    // $ANTLR start "rule__Architecture__Group__3"
    // InternalCNNDescLang.g:738:1: rule__Architecture__Group__3 : rule__Architecture__Group__3__Impl ;
    public final void rule__Architecture__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:742:1: ( rule__Architecture__Group__3__Impl )
            // InternalCNNDescLang.g:743:2: rule__Architecture__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Architecture__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__3"


    // $ANTLR start "rule__Architecture__Group__3__Impl"
    // InternalCNNDescLang.g:749:1: rule__Architecture__Group__3__Impl : ( ( rule__Architecture__OutputAssignment_3 ) ) ;
    public final void rule__Architecture__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:753:1: ( ( ( rule__Architecture__OutputAssignment_3 ) ) )
            // InternalCNNDescLang.g:754:1: ( ( rule__Architecture__OutputAssignment_3 ) )
            {
            // InternalCNNDescLang.g:754:1: ( ( rule__Architecture__OutputAssignment_3 ) )
            // InternalCNNDescLang.g:755:2: ( rule__Architecture__OutputAssignment_3 )
            {
             before(grammarAccess.getArchitectureAccess().getOutputAssignment_3()); 
            // InternalCNNDescLang.g:756:2: ( rule__Architecture__OutputAssignment_3 )
            // InternalCNNDescLang.g:756:3: rule__Architecture__OutputAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Architecture__OutputAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getArchitectureAccess().getOutputAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group__3__Impl"


    // $ANTLR start "rule__Architecture__Group_2__0"
    // InternalCNNDescLang.g:765:1: rule__Architecture__Group_2__0 : rule__Architecture__Group_2__0__Impl rule__Architecture__Group_2__1 ;
    public final void rule__Architecture__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:769:1: ( rule__Architecture__Group_2__0__Impl rule__Architecture__Group_2__1 )
            // InternalCNNDescLang.g:770:2: rule__Architecture__Group_2__0__Impl rule__Architecture__Group_2__1
            {
            pushFollow(FOLLOW_7);
            rule__Architecture__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Architecture__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group_2__0"


    // $ANTLR start "rule__Architecture__Group_2__0__Impl"
    // InternalCNNDescLang.g:777:1: rule__Architecture__Group_2__0__Impl : ( ( rule__Architecture__InterAssignment_2_0 ) ) ;
    public final void rule__Architecture__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:781:1: ( ( ( rule__Architecture__InterAssignment_2_0 ) ) )
            // InternalCNNDescLang.g:782:1: ( ( rule__Architecture__InterAssignment_2_0 ) )
            {
            // InternalCNNDescLang.g:782:1: ( ( rule__Architecture__InterAssignment_2_0 ) )
            // InternalCNNDescLang.g:783:2: ( rule__Architecture__InterAssignment_2_0 )
            {
             before(grammarAccess.getArchitectureAccess().getInterAssignment_2_0()); 
            // InternalCNNDescLang.g:784:2: ( rule__Architecture__InterAssignment_2_0 )
            // InternalCNNDescLang.g:784:3: rule__Architecture__InterAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__Architecture__InterAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getArchitectureAccess().getInterAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group_2__0__Impl"


    // $ANTLR start "rule__Architecture__Group_2__1"
    // InternalCNNDescLang.g:792:1: rule__Architecture__Group_2__1 : rule__Architecture__Group_2__1__Impl ;
    public final void rule__Architecture__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:796:1: ( rule__Architecture__Group_2__1__Impl )
            // InternalCNNDescLang.g:797:2: rule__Architecture__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Architecture__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group_2__1"


    // $ANTLR start "rule__Architecture__Group_2__1__Impl"
    // InternalCNNDescLang.g:803:1: rule__Architecture__Group_2__1__Impl : ( ( ( rule__Architecture__ClassAssignment_2_1 ) ) ( ( rule__Architecture__ClassAssignment_2_1 )* ) ) ;
    public final void rule__Architecture__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:807:1: ( ( ( ( rule__Architecture__ClassAssignment_2_1 ) ) ( ( rule__Architecture__ClassAssignment_2_1 )* ) ) )
            // InternalCNNDescLang.g:808:1: ( ( ( rule__Architecture__ClassAssignment_2_1 ) ) ( ( rule__Architecture__ClassAssignment_2_1 )* ) )
            {
            // InternalCNNDescLang.g:808:1: ( ( ( rule__Architecture__ClassAssignment_2_1 ) ) ( ( rule__Architecture__ClassAssignment_2_1 )* ) )
            // InternalCNNDescLang.g:809:2: ( ( rule__Architecture__ClassAssignment_2_1 ) ) ( ( rule__Architecture__ClassAssignment_2_1 )* )
            {
            // InternalCNNDescLang.g:809:2: ( ( rule__Architecture__ClassAssignment_2_1 ) )
            // InternalCNNDescLang.g:810:3: ( rule__Architecture__ClassAssignment_2_1 )
            {
             before(grammarAccess.getArchitectureAccess().getClassAssignment_2_1()); 
            // InternalCNNDescLang.g:811:3: ( rule__Architecture__ClassAssignment_2_1 )
            // InternalCNNDescLang.g:811:4: rule__Architecture__ClassAssignment_2_1
            {
            pushFollow(FOLLOW_8);
            rule__Architecture__ClassAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getArchitectureAccess().getClassAssignment_2_1()); 

            }

            // InternalCNNDescLang.g:814:2: ( ( rule__Architecture__ClassAssignment_2_1 )* )
            // InternalCNNDescLang.g:815:3: ( rule__Architecture__ClassAssignment_2_1 )*
            {
             before(grammarAccess.getArchitectureAccess().getClassAssignment_2_1()); 
            // InternalCNNDescLang.g:816:3: ( rule__Architecture__ClassAssignment_2_1 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==11||LA12_0==25) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalCNNDescLang.g:816:4: rule__Architecture__ClassAssignment_2_1
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Architecture__ClassAssignment_2_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getArchitectureAccess().getClassAssignment_2_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__Group_2__1__Impl"


    // $ANTLR start "rule__Classification__Group__0"
    // InternalCNNDescLang.g:826:1: rule__Classification__Group__0 : rule__Classification__Group__0__Impl rule__Classification__Group__1 ;
    public final void rule__Classification__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:830:1: ( rule__Classification__Group__0__Impl rule__Classification__Group__1 )
            // InternalCNNDescLang.g:831:2: rule__Classification__Group__0__Impl rule__Classification__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__Classification__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Classification__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Classification__Group__0"


    // $ANTLR start "rule__Classification__Group__0__Impl"
    // InternalCNNDescLang.g:838:1: rule__Classification__Group__0__Impl : ( ( rule__Classification__DropAssignment_0 )? ) ;
    public final void rule__Classification__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:842:1: ( ( ( rule__Classification__DropAssignment_0 )? ) )
            // InternalCNNDescLang.g:843:1: ( ( rule__Classification__DropAssignment_0 )? )
            {
            // InternalCNNDescLang.g:843:1: ( ( rule__Classification__DropAssignment_0 )? )
            // InternalCNNDescLang.g:844:2: ( rule__Classification__DropAssignment_0 )?
            {
             before(grammarAccess.getClassificationAccess().getDropAssignment_0()); 
            // InternalCNNDescLang.g:845:2: ( rule__Classification__DropAssignment_0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==11) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalCNNDescLang.g:845:3: rule__Classification__DropAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Classification__DropAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassificationAccess().getDropAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Classification__Group__0__Impl"


    // $ANTLR start "rule__Classification__Group__1"
    // InternalCNNDescLang.g:853:1: rule__Classification__Group__1 : rule__Classification__Group__1__Impl ;
    public final void rule__Classification__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:857:1: ( rule__Classification__Group__1__Impl )
            // InternalCNNDescLang.g:858:2: rule__Classification__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Classification__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Classification__Group__1"


    // $ANTLR start "rule__Classification__Group__1__Impl"
    // InternalCNNDescLang.g:864:1: rule__Classification__Group__1__Impl : ( ( rule__Classification__DAssignment_1 ) ) ;
    public final void rule__Classification__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:868:1: ( ( ( rule__Classification__DAssignment_1 ) ) )
            // InternalCNNDescLang.g:869:1: ( ( rule__Classification__DAssignment_1 ) )
            {
            // InternalCNNDescLang.g:869:1: ( ( rule__Classification__DAssignment_1 ) )
            // InternalCNNDescLang.g:870:2: ( rule__Classification__DAssignment_1 )
            {
             before(grammarAccess.getClassificationAccess().getDAssignment_1()); 
            // InternalCNNDescLang.g:871:2: ( rule__Classification__DAssignment_1 )
            // InternalCNNDescLang.g:871:3: rule__Classification__DAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Classification__DAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getClassificationAccess().getDAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Classification__Group__1__Impl"


    // $ANTLR start "rule__ConvDrop__Group__0"
    // InternalCNNDescLang.g:880:1: rule__ConvDrop__Group__0 : rule__ConvDrop__Group__0__Impl rule__ConvDrop__Group__1 ;
    public final void rule__ConvDrop__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:884:1: ( rule__ConvDrop__Group__0__Impl rule__ConvDrop__Group__1 )
            // InternalCNNDescLang.g:885:2: rule__ConvDrop__Group__0__Impl rule__ConvDrop__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ConvDrop__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConvDrop__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvDrop__Group__0"


    // $ANTLR start "rule__ConvDrop__Group__0__Impl"
    // InternalCNNDescLang.g:892:1: rule__ConvDrop__Group__0__Impl : ( ( rule__ConvDrop__ConvAssignment_0 ) ) ;
    public final void rule__ConvDrop__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:896:1: ( ( ( rule__ConvDrop__ConvAssignment_0 ) ) )
            // InternalCNNDescLang.g:897:1: ( ( rule__ConvDrop__ConvAssignment_0 ) )
            {
            // InternalCNNDescLang.g:897:1: ( ( rule__ConvDrop__ConvAssignment_0 ) )
            // InternalCNNDescLang.g:898:2: ( rule__ConvDrop__ConvAssignment_0 )
            {
             before(grammarAccess.getConvDropAccess().getConvAssignment_0()); 
            // InternalCNNDescLang.g:899:2: ( rule__ConvDrop__ConvAssignment_0 )
            // InternalCNNDescLang.g:899:3: rule__ConvDrop__ConvAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ConvDrop__ConvAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getConvDropAccess().getConvAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvDrop__Group__0__Impl"


    // $ANTLR start "rule__ConvDrop__Group__1"
    // InternalCNNDescLang.g:907:1: rule__ConvDrop__Group__1 : rule__ConvDrop__Group__1__Impl ;
    public final void rule__ConvDrop__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:911:1: ( rule__ConvDrop__Group__1__Impl )
            // InternalCNNDescLang.g:912:2: rule__ConvDrop__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConvDrop__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvDrop__Group__1"


    // $ANTLR start "rule__ConvDrop__Group__1__Impl"
    // InternalCNNDescLang.g:918:1: rule__ConvDrop__Group__1__Impl : ( ( rule__ConvDrop__DropAssignment_1 )? ) ;
    public final void rule__ConvDrop__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:922:1: ( ( ( rule__ConvDrop__DropAssignment_1 )? ) )
            // InternalCNNDescLang.g:923:1: ( ( rule__ConvDrop__DropAssignment_1 )? )
            {
            // InternalCNNDescLang.g:923:1: ( ( rule__ConvDrop__DropAssignment_1 )? )
            // InternalCNNDescLang.g:924:2: ( rule__ConvDrop__DropAssignment_1 )?
            {
             before(grammarAccess.getConvDropAccess().getDropAssignment_1()); 
            // InternalCNNDescLang.g:925:2: ( rule__ConvDrop__DropAssignment_1 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==11) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalCNNDescLang.g:925:3: rule__ConvDrop__DropAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ConvDrop__DropAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConvDropAccess().getDropAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvDrop__Group__1__Impl"


    // $ANTLR start "rule__MergeConv__Group__0"
    // InternalCNNDescLang.g:934:1: rule__MergeConv__Group__0 : rule__MergeConv__Group__0__Impl rule__MergeConv__Group__1 ;
    public final void rule__MergeConv__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:938:1: ( rule__MergeConv__Group__0__Impl rule__MergeConv__Group__1 )
            // InternalCNNDescLang.g:939:2: rule__MergeConv__Group__0__Impl rule__MergeConv__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__MergeConv__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MergeConv__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeConv__Group__0"


    // $ANTLR start "rule__MergeConv__Group__0__Impl"
    // InternalCNNDescLang.g:946:1: rule__MergeConv__Group__0__Impl : ( ( rule__MergeConv__MergeAssignment_0 ) ) ;
    public final void rule__MergeConv__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:950:1: ( ( ( rule__MergeConv__MergeAssignment_0 ) ) )
            // InternalCNNDescLang.g:951:1: ( ( rule__MergeConv__MergeAssignment_0 ) )
            {
            // InternalCNNDescLang.g:951:1: ( ( rule__MergeConv__MergeAssignment_0 ) )
            // InternalCNNDescLang.g:952:2: ( rule__MergeConv__MergeAssignment_0 )
            {
             before(grammarAccess.getMergeConvAccess().getMergeAssignment_0()); 
            // InternalCNNDescLang.g:953:2: ( rule__MergeConv__MergeAssignment_0 )
            // InternalCNNDescLang.g:953:3: rule__MergeConv__MergeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__MergeConv__MergeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMergeConvAccess().getMergeAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeConv__Group__0__Impl"


    // $ANTLR start "rule__MergeConv__Group__1"
    // InternalCNNDescLang.g:961:1: rule__MergeConv__Group__1 : rule__MergeConv__Group__1__Impl ;
    public final void rule__MergeConv__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:965:1: ( rule__MergeConv__Group__1__Impl )
            // InternalCNNDescLang.g:966:2: rule__MergeConv__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MergeConv__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeConv__Group__1"


    // $ANTLR start "rule__MergeConv__Group__1__Impl"
    // InternalCNNDescLang.g:972:1: rule__MergeConv__Group__1__Impl : ( ( rule__MergeConv__ConvdropAssignment_1 )* ) ;
    public final void rule__MergeConv__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:976:1: ( ( ( rule__MergeConv__ConvdropAssignment_1 )* ) )
            // InternalCNNDescLang.g:977:1: ( ( rule__MergeConv__ConvdropAssignment_1 )* )
            {
            // InternalCNNDescLang.g:977:1: ( ( rule__MergeConv__ConvdropAssignment_1 )* )
            // InternalCNNDescLang.g:978:2: ( rule__MergeConv__ConvdropAssignment_1 )*
            {
             before(grammarAccess.getMergeConvAccess().getConvdropAssignment_1()); 
            // InternalCNNDescLang.g:979:2: ( rule__MergeConv__ConvdropAssignment_1 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=20 && LA15_0<=23)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalCNNDescLang.g:979:3: rule__MergeConv__ConvdropAssignment_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__MergeConv__ConvdropAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getMergeConvAccess().getConvdropAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeConv__Group__1__Impl"


    // $ANTLR start "rule__ConvOrMerge__Group_1__0"
    // InternalCNNDescLang.g:988:1: rule__ConvOrMerge__Group_1__0 : rule__ConvOrMerge__Group_1__0__Impl rule__ConvOrMerge__Group_1__1 ;
    public final void rule__ConvOrMerge__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:992:1: ( rule__ConvOrMerge__Group_1__0__Impl rule__ConvOrMerge__Group_1__1 )
            // InternalCNNDescLang.g:993:2: rule__ConvOrMerge__Group_1__0__Impl rule__ConvOrMerge__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__ConvOrMerge__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConvOrMerge__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__Group_1__0"


    // $ANTLR start "rule__ConvOrMerge__Group_1__0__Impl"
    // InternalCNNDescLang.g:1000:1: rule__ConvOrMerge__Group_1__0__Impl : ( ( rule__ConvOrMerge__ConvdropAssignment_1_0 )* ) ;
    public final void rule__ConvOrMerge__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1004:1: ( ( ( rule__ConvOrMerge__ConvdropAssignment_1_0 )* ) )
            // InternalCNNDescLang.g:1005:1: ( ( rule__ConvOrMerge__ConvdropAssignment_1_0 )* )
            {
            // InternalCNNDescLang.g:1005:1: ( ( rule__ConvOrMerge__ConvdropAssignment_1_0 )* )
            // InternalCNNDescLang.g:1006:2: ( rule__ConvOrMerge__ConvdropAssignment_1_0 )*
            {
             before(grammarAccess.getConvOrMergeAccess().getConvdropAssignment_1_0()); 
            // InternalCNNDescLang.g:1007:2: ( rule__ConvOrMerge__ConvdropAssignment_1_0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=20 && LA16_0<=23)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalCNNDescLang.g:1007:3: rule__ConvOrMerge__ConvdropAssignment_1_0
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ConvOrMerge__ConvdropAssignment_1_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getConvOrMergeAccess().getConvdropAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__Group_1__0__Impl"


    // $ANTLR start "rule__ConvOrMerge__Group_1__1"
    // InternalCNNDescLang.g:1015:1: rule__ConvOrMerge__Group_1__1 : rule__ConvOrMerge__Group_1__1__Impl ;
    public final void rule__ConvOrMerge__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1019:1: ( rule__ConvOrMerge__Group_1__1__Impl )
            // InternalCNNDescLang.g:1020:2: rule__ConvOrMerge__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConvOrMerge__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__Group_1__1"


    // $ANTLR start "rule__ConvOrMerge__Group_1__1__Impl"
    // InternalCNNDescLang.g:1026:1: rule__ConvOrMerge__Group_1__1__Impl : ( ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 ) ) ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 )* ) ) ;
    public final void rule__ConvOrMerge__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1030:1: ( ( ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 ) ) ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 )* ) ) )
            // InternalCNNDescLang.g:1031:1: ( ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 ) ) ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 )* ) )
            {
            // InternalCNNDescLang.g:1031:1: ( ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 ) ) ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 )* ) )
            // InternalCNNDescLang.g:1032:2: ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 ) ) ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 )* )
            {
            // InternalCNNDescLang.g:1032:2: ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 ) )
            // InternalCNNDescLang.g:1033:3: ( rule__ConvOrMerge__MergeConvAssignment_1_1 )
            {
             before(grammarAccess.getConvOrMergeAccess().getMergeConvAssignment_1_1()); 
            // InternalCNNDescLang.g:1034:3: ( rule__ConvOrMerge__MergeConvAssignment_1_1 )
            // InternalCNNDescLang.g:1034:4: rule__ConvOrMerge__MergeConvAssignment_1_1
            {
            pushFollow(FOLLOW_6);
            rule__ConvOrMerge__MergeConvAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getConvOrMergeAccess().getMergeConvAssignment_1_1()); 

            }

            // InternalCNNDescLang.g:1037:2: ( ( rule__ConvOrMerge__MergeConvAssignment_1_1 )* )
            // InternalCNNDescLang.g:1038:3: ( rule__ConvOrMerge__MergeConvAssignment_1_1 )*
            {
             before(grammarAccess.getConvOrMergeAccess().getMergeConvAssignment_1_1()); 
            // InternalCNNDescLang.g:1039:3: ( rule__ConvOrMerge__MergeConvAssignment_1_1 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==28) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalCNNDescLang.g:1039:4: rule__ConvOrMerge__MergeConvAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__ConvOrMerge__MergeConvAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getConvOrMergeAccess().getMergeConvAssignment_1_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__Group_1__1__Impl"


    // $ANTLR start "rule__Left__Group__0"
    // InternalCNNDescLang.g:1049:1: rule__Left__Group__0 : rule__Left__Group__0__Impl rule__Left__Group__1 ;
    public final void rule__Left__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1053:1: ( rule__Left__Group__0__Impl rule__Left__Group__1 )
            // InternalCNNDescLang.g:1054:2: rule__Left__Group__0__Impl rule__Left__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__Left__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Left__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__Group__0"


    // $ANTLR start "rule__Left__Group__0__Impl"
    // InternalCNNDescLang.g:1061:1: rule__Left__Group__0__Impl : ( ( rule__Left__PAssignment_0 )? ) ;
    public final void rule__Left__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1065:1: ( ( ( rule__Left__PAssignment_0 )? ) )
            // InternalCNNDescLang.g:1066:1: ( ( rule__Left__PAssignment_0 )? )
            {
            // InternalCNNDescLang.g:1066:1: ( ( rule__Left__PAssignment_0 )? )
            // InternalCNNDescLang.g:1067:2: ( rule__Left__PAssignment_0 )?
            {
             before(grammarAccess.getLeftAccess().getPAssignment_0()); 
            // InternalCNNDescLang.g:1068:2: ( rule__Left__PAssignment_0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=12 && LA18_0<=13)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalCNNDescLang.g:1068:3: rule__Left__PAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Left__PAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLeftAccess().getPAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__Group__0__Impl"


    // $ANTLR start "rule__Left__Group__1"
    // InternalCNNDescLang.g:1076:1: rule__Left__Group__1 : rule__Left__Group__1__Impl rule__Left__Group__2 ;
    public final void rule__Left__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1080:1: ( rule__Left__Group__1__Impl rule__Left__Group__2 )
            // InternalCNNDescLang.g:1081:2: rule__Left__Group__1__Impl rule__Left__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Left__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Left__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__Group__1"


    // $ANTLR start "rule__Left__Group__1__Impl"
    // InternalCNNDescLang.g:1088:1: rule__Left__Group__1__Impl : ( ( rule__Left__ComAssignment_1 ) ) ;
    public final void rule__Left__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1092:1: ( ( ( rule__Left__ComAssignment_1 ) ) )
            // InternalCNNDescLang.g:1093:1: ( ( rule__Left__ComAssignment_1 ) )
            {
            // InternalCNNDescLang.g:1093:1: ( ( rule__Left__ComAssignment_1 ) )
            // InternalCNNDescLang.g:1094:2: ( rule__Left__ComAssignment_1 )
            {
             before(grammarAccess.getLeftAccess().getComAssignment_1()); 
            // InternalCNNDescLang.g:1095:2: ( rule__Left__ComAssignment_1 )
            // InternalCNNDescLang.g:1095:3: rule__Left__ComAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Left__ComAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLeftAccess().getComAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__Group__1__Impl"


    // $ANTLR start "rule__Left__Group__2"
    // InternalCNNDescLang.g:1103:1: rule__Left__Group__2 : rule__Left__Group__2__Impl ;
    public final void rule__Left__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1107:1: ( rule__Left__Group__2__Impl )
            // InternalCNNDescLang.g:1108:2: rule__Left__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Left__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__Group__2"


    // $ANTLR start "rule__Left__Group__2__Impl"
    // InternalCNNDescLang.g:1114:1: rule__Left__Group__2__Impl : ( ( rule__Left__PoolAssignment_2 )? ) ;
    public final void rule__Left__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1118:1: ( ( ( rule__Left__PoolAssignment_2 )? ) )
            // InternalCNNDescLang.g:1119:1: ( ( rule__Left__PoolAssignment_2 )? )
            {
            // InternalCNNDescLang.g:1119:1: ( ( rule__Left__PoolAssignment_2 )? )
            // InternalCNNDescLang.g:1120:2: ( rule__Left__PoolAssignment_2 )?
            {
             before(grammarAccess.getLeftAccess().getPoolAssignment_2()); 
            // InternalCNNDescLang.g:1121:2: ( rule__Left__PoolAssignment_2 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=12 && LA19_0<=13)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalCNNDescLang.g:1121:3: rule__Left__PoolAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Left__PoolAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLeftAccess().getPoolAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__Group__2__Impl"


    // $ANTLR start "rule__Right__Group_1__0"
    // InternalCNNDescLang.g:1130:1: rule__Right__Group_1__0 : rule__Right__Group_1__0__Impl rule__Right__Group_1__1 ;
    public final void rule__Right__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1134:1: ( rule__Right__Group_1__0__Impl rule__Right__Group_1__1 )
            // InternalCNNDescLang.g:1135:2: rule__Right__Group_1__0__Impl rule__Right__Group_1__1
            {
            pushFollow(FOLLOW_12);
            rule__Right__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Right__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__Group_1__0"


    // $ANTLR start "rule__Right__Group_1__0__Impl"
    // InternalCNNDescLang.g:1142:1: rule__Right__Group_1__0__Impl : ( () ) ;
    public final void rule__Right__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1146:1: ( ( () ) )
            // InternalCNNDescLang.g:1147:1: ( () )
            {
            // InternalCNNDescLang.g:1147:1: ( () )
            // InternalCNNDescLang.g:1148:2: ()
            {
             before(grammarAccess.getRightAccess().getRightAction_1_0()); 
            // InternalCNNDescLang.g:1149:2: ()
            // InternalCNNDescLang.g:1149:3: 
            {
            }

             after(grammarAccess.getRightAccess().getRightAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__Group_1__0__Impl"


    // $ANTLR start "rule__Right__Group_1__1"
    // InternalCNNDescLang.g:1157:1: rule__Right__Group_1__1 : rule__Right__Group_1__1__Impl ;
    public final void rule__Right__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1161:1: ( rule__Right__Group_1__1__Impl )
            // InternalCNNDescLang.g:1162:2: rule__Right__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Right__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__Group_1__1"


    // $ANTLR start "rule__Right__Group_1__1__Impl"
    // InternalCNNDescLang.g:1168:1: rule__Right__Group_1__1__Impl : ( ( rule__Right__EmptyAssignment_1_1 ) ) ;
    public final void rule__Right__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1172:1: ( ( ( rule__Right__EmptyAssignment_1_1 ) ) )
            // InternalCNNDescLang.g:1173:1: ( ( rule__Right__EmptyAssignment_1_1 ) )
            {
            // InternalCNNDescLang.g:1173:1: ( ( rule__Right__EmptyAssignment_1_1 ) )
            // InternalCNNDescLang.g:1174:2: ( rule__Right__EmptyAssignment_1_1 )
            {
             before(grammarAccess.getRightAccess().getEmptyAssignment_1_1()); 
            // InternalCNNDescLang.g:1175:2: ( rule__Right__EmptyAssignment_1_1 )
            // InternalCNNDescLang.g:1175:3: rule__Right__EmptyAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Right__EmptyAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRightAccess().getEmptyAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__Group_1__1__Impl"


    // $ANTLR start "rule__MergeBody__Group__0"
    // InternalCNNDescLang.g:1184:1: rule__MergeBody__Group__0 : rule__MergeBody__Group__0__Impl rule__MergeBody__Group__1 ;
    public final void rule__MergeBody__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1188:1: ( rule__MergeBody__Group__0__Impl rule__MergeBody__Group__1 )
            // InternalCNNDescLang.g:1189:2: rule__MergeBody__Group__0__Impl rule__MergeBody__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__MergeBody__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MergeBody__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__0"


    // $ANTLR start "rule__MergeBody__Group__0__Impl"
    // InternalCNNDescLang.g:1196:1: rule__MergeBody__Group__0__Impl : ( '(' ) ;
    public final void rule__MergeBody__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1200:1: ( ( '(' ) )
            // InternalCNNDescLang.g:1201:1: ( '(' )
            {
            // InternalCNNDescLang.g:1201:1: ( '(' )
            // InternalCNNDescLang.g:1202:2: '('
            {
             before(grammarAccess.getMergeBodyAccess().getLeftParenthesisKeyword_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getMergeBodyAccess().getLeftParenthesisKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__0__Impl"


    // $ANTLR start "rule__MergeBody__Group__1"
    // InternalCNNDescLang.g:1211:1: rule__MergeBody__Group__1 : rule__MergeBody__Group__1__Impl rule__MergeBody__Group__2 ;
    public final void rule__MergeBody__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1215:1: ( rule__MergeBody__Group__1__Impl rule__MergeBody__Group__2 )
            // InternalCNNDescLang.g:1216:2: rule__MergeBody__Group__1__Impl rule__MergeBody__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__MergeBody__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MergeBody__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__1"


    // $ANTLR start "rule__MergeBody__Group__1__Impl"
    // InternalCNNDescLang.g:1223:1: rule__MergeBody__Group__1__Impl : ( ( rule__MergeBody__LeftAssignment_1 ) ) ;
    public final void rule__MergeBody__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1227:1: ( ( ( rule__MergeBody__LeftAssignment_1 ) ) )
            // InternalCNNDescLang.g:1228:1: ( ( rule__MergeBody__LeftAssignment_1 ) )
            {
            // InternalCNNDescLang.g:1228:1: ( ( rule__MergeBody__LeftAssignment_1 ) )
            // InternalCNNDescLang.g:1229:2: ( rule__MergeBody__LeftAssignment_1 )
            {
             before(grammarAccess.getMergeBodyAccess().getLeftAssignment_1()); 
            // InternalCNNDescLang.g:1230:2: ( rule__MergeBody__LeftAssignment_1 )
            // InternalCNNDescLang.g:1230:3: rule__MergeBody__LeftAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__MergeBody__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMergeBodyAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__1__Impl"


    // $ANTLR start "rule__MergeBody__Group__2"
    // InternalCNNDescLang.g:1238:1: rule__MergeBody__Group__2 : rule__MergeBody__Group__2__Impl rule__MergeBody__Group__3 ;
    public final void rule__MergeBody__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1242:1: ( rule__MergeBody__Group__2__Impl rule__MergeBody__Group__3 )
            // InternalCNNDescLang.g:1243:2: rule__MergeBody__Group__2__Impl rule__MergeBody__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__MergeBody__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MergeBody__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__2"


    // $ANTLR start "rule__MergeBody__Group__2__Impl"
    // InternalCNNDescLang.g:1250:1: rule__MergeBody__Group__2__Impl : ( ( rule__MergeBody__VirgAssignment_2 ) ) ;
    public final void rule__MergeBody__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1254:1: ( ( ( rule__MergeBody__VirgAssignment_2 ) ) )
            // InternalCNNDescLang.g:1255:1: ( ( rule__MergeBody__VirgAssignment_2 ) )
            {
            // InternalCNNDescLang.g:1255:1: ( ( rule__MergeBody__VirgAssignment_2 ) )
            // InternalCNNDescLang.g:1256:2: ( rule__MergeBody__VirgAssignment_2 )
            {
             before(grammarAccess.getMergeBodyAccess().getVirgAssignment_2()); 
            // InternalCNNDescLang.g:1257:2: ( rule__MergeBody__VirgAssignment_2 )
            // InternalCNNDescLang.g:1257:3: rule__MergeBody__VirgAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__MergeBody__VirgAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMergeBodyAccess().getVirgAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__2__Impl"


    // $ANTLR start "rule__MergeBody__Group__3"
    // InternalCNNDescLang.g:1265:1: rule__MergeBody__Group__3 : rule__MergeBody__Group__3__Impl rule__MergeBody__Group__4 ;
    public final void rule__MergeBody__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1269:1: ( rule__MergeBody__Group__3__Impl rule__MergeBody__Group__4 )
            // InternalCNNDescLang.g:1270:2: rule__MergeBody__Group__3__Impl rule__MergeBody__Group__4
            {
            pushFollow(FOLLOW_16);
            rule__MergeBody__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MergeBody__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__3"


    // $ANTLR start "rule__MergeBody__Group__3__Impl"
    // InternalCNNDescLang.g:1277:1: rule__MergeBody__Group__3__Impl : ( ( rule__MergeBody__RightAssignment_3 ) ) ;
    public final void rule__MergeBody__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1281:1: ( ( ( rule__MergeBody__RightAssignment_3 ) ) )
            // InternalCNNDescLang.g:1282:1: ( ( rule__MergeBody__RightAssignment_3 ) )
            {
            // InternalCNNDescLang.g:1282:1: ( ( rule__MergeBody__RightAssignment_3 ) )
            // InternalCNNDescLang.g:1283:2: ( rule__MergeBody__RightAssignment_3 )
            {
             before(grammarAccess.getMergeBodyAccess().getRightAssignment_3()); 
            // InternalCNNDescLang.g:1284:2: ( rule__MergeBody__RightAssignment_3 )
            // InternalCNNDescLang.g:1284:3: rule__MergeBody__RightAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__MergeBody__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getMergeBodyAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__3__Impl"


    // $ANTLR start "rule__MergeBody__Group__4"
    // InternalCNNDescLang.g:1292:1: rule__MergeBody__Group__4 : rule__MergeBody__Group__4__Impl ;
    public final void rule__MergeBody__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1296:1: ( rule__MergeBody__Group__4__Impl )
            // InternalCNNDescLang.g:1297:2: rule__MergeBody__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MergeBody__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__4"


    // $ANTLR start "rule__MergeBody__Group__4__Impl"
    // InternalCNNDescLang.g:1303:1: rule__MergeBody__Group__4__Impl : ( ')' ) ;
    public final void rule__MergeBody__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1307:1: ( ( ')' ) )
            // InternalCNNDescLang.g:1308:1: ( ')' )
            {
            // InternalCNNDescLang.g:1308:1: ( ')' )
            // InternalCNNDescLang.g:1309:2: ')'
            {
             before(grammarAccess.getMergeBodyAccess().getRightParenthesisKeyword_4()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getMergeBodyAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__Group__4__Impl"


    // $ANTLR start "rule__Merge__Group__0"
    // InternalCNNDescLang.g:1319:1: rule__Merge__Group__0 : rule__Merge__Group__0__Impl rule__Merge__Group__1 ;
    public final void rule__Merge__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1323:1: ( rule__Merge__Group__0__Impl rule__Merge__Group__1 )
            // InternalCNNDescLang.g:1324:2: rule__Merge__Group__0__Impl rule__Merge__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Merge__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Merge__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__Group__0"


    // $ANTLR start "rule__Merge__Group__0__Impl"
    // InternalCNNDescLang.g:1331:1: rule__Merge__Group__0__Impl : ( ( rule__Merge__DbAssignment_0 ) ) ;
    public final void rule__Merge__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1335:1: ( ( ( rule__Merge__DbAssignment_0 ) ) )
            // InternalCNNDescLang.g:1336:1: ( ( rule__Merge__DbAssignment_0 ) )
            {
            // InternalCNNDescLang.g:1336:1: ( ( rule__Merge__DbAssignment_0 ) )
            // InternalCNNDescLang.g:1337:2: ( rule__Merge__DbAssignment_0 )
            {
             before(grammarAccess.getMergeAccess().getDbAssignment_0()); 
            // InternalCNNDescLang.g:1338:2: ( rule__Merge__DbAssignment_0 )
            // InternalCNNDescLang.g:1338:3: rule__Merge__DbAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Merge__DbAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMergeAccess().getDbAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__Group__0__Impl"


    // $ANTLR start "rule__Merge__Group__1"
    // InternalCNNDescLang.g:1346:1: rule__Merge__Group__1 : rule__Merge__Group__1__Impl rule__Merge__Group__2 ;
    public final void rule__Merge__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1350:1: ( rule__Merge__Group__1__Impl rule__Merge__Group__2 )
            // InternalCNNDescLang.g:1351:2: rule__Merge__Group__1__Impl rule__Merge__Group__2
            {
            pushFollow(FOLLOW_18);
            rule__Merge__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Merge__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__Group__1"


    // $ANTLR start "rule__Merge__Group__1__Impl"
    // InternalCNNDescLang.g:1358:1: rule__Merge__Group__1__Impl : ( ( ( rule__Merge__MergeBodyAssignment_1 ) ) ( ( rule__Merge__MergeBodyAssignment_1 )* ) ) ;
    public final void rule__Merge__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1362:1: ( ( ( ( rule__Merge__MergeBodyAssignment_1 ) ) ( ( rule__Merge__MergeBodyAssignment_1 )* ) ) )
            // InternalCNNDescLang.g:1363:1: ( ( ( rule__Merge__MergeBodyAssignment_1 ) ) ( ( rule__Merge__MergeBodyAssignment_1 )* ) )
            {
            // InternalCNNDescLang.g:1363:1: ( ( ( rule__Merge__MergeBodyAssignment_1 ) ) ( ( rule__Merge__MergeBodyAssignment_1 )* ) )
            // InternalCNNDescLang.g:1364:2: ( ( rule__Merge__MergeBodyAssignment_1 ) ) ( ( rule__Merge__MergeBodyAssignment_1 )* )
            {
            // InternalCNNDescLang.g:1364:2: ( ( rule__Merge__MergeBodyAssignment_1 ) )
            // InternalCNNDescLang.g:1365:3: ( rule__Merge__MergeBodyAssignment_1 )
            {
             before(grammarAccess.getMergeAccess().getMergeBodyAssignment_1()); 
            // InternalCNNDescLang.g:1366:3: ( rule__Merge__MergeBodyAssignment_1 )
            // InternalCNNDescLang.g:1366:4: rule__Merge__MergeBodyAssignment_1
            {
            pushFollow(FOLLOW_19);
            rule__Merge__MergeBodyAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMergeAccess().getMergeBodyAssignment_1()); 

            }

            // InternalCNNDescLang.g:1369:2: ( ( rule__Merge__MergeBodyAssignment_1 )* )
            // InternalCNNDescLang.g:1370:3: ( rule__Merge__MergeBodyAssignment_1 )*
            {
             before(grammarAccess.getMergeAccess().getMergeBodyAssignment_1()); 
            // InternalCNNDescLang.g:1371:3: ( rule__Merge__MergeBodyAssignment_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==16) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalCNNDescLang.g:1371:4: rule__Merge__MergeBodyAssignment_1
            	    {
            	    pushFollow(FOLLOW_19);
            	    rule__Merge__MergeBodyAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getMergeAccess().getMergeBodyAssignment_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__Group__1__Impl"


    // $ANTLR start "rule__Merge__Group__2"
    // InternalCNNDescLang.g:1380:1: rule__Merge__Group__2 : rule__Merge__Group__2__Impl ;
    public final void rule__Merge__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1384:1: ( rule__Merge__Group__2__Impl )
            // InternalCNNDescLang.g:1385:2: rule__Merge__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Merge__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__Group__2"


    // $ANTLR start "rule__Merge__Group__2__Impl"
    // InternalCNNDescLang.g:1391:1: rule__Merge__Group__2__Impl : ( ( rule__Merge__FmAssignment_2 ) ) ;
    public final void rule__Merge__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1395:1: ( ( ( rule__Merge__FmAssignment_2 ) ) )
            // InternalCNNDescLang.g:1396:1: ( ( rule__Merge__FmAssignment_2 ) )
            {
            // InternalCNNDescLang.g:1396:1: ( ( rule__Merge__FmAssignment_2 ) )
            // InternalCNNDescLang.g:1397:2: ( rule__Merge__FmAssignment_2 )
            {
             before(grammarAccess.getMergeAccess().getFmAssignment_2()); 
            // InternalCNNDescLang.g:1398:2: ( rule__Merge__FmAssignment_2 )
            // InternalCNNDescLang.g:1398:3: rule__Merge__FmAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Merge__FmAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMergeAccess().getFmAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__Group__2__Impl"


    // $ANTLR start "rule__FeatureExtraction__Group__0"
    // InternalCNNDescLang.g:1407:1: rule__FeatureExtraction__Group__0 : rule__FeatureExtraction__Group__0__Impl rule__FeatureExtraction__Group__1 ;
    public final void rule__FeatureExtraction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1411:1: ( rule__FeatureExtraction__Group__0__Impl rule__FeatureExtraction__Group__1 )
            // InternalCNNDescLang.g:1412:2: rule__FeatureExtraction__Group__0__Impl rule__FeatureExtraction__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__FeatureExtraction__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FeatureExtraction__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Group__0"


    // $ANTLR start "rule__FeatureExtraction__Group__0__Impl"
    // InternalCNNDescLang.g:1419:1: rule__FeatureExtraction__Group__0__Impl : ( ( rule__FeatureExtraction__Alternatives_0 ) ) ;
    public final void rule__FeatureExtraction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1423:1: ( ( ( rule__FeatureExtraction__Alternatives_0 ) ) )
            // InternalCNNDescLang.g:1424:1: ( ( rule__FeatureExtraction__Alternatives_0 ) )
            {
            // InternalCNNDescLang.g:1424:1: ( ( rule__FeatureExtraction__Alternatives_0 ) )
            // InternalCNNDescLang.g:1425:2: ( rule__FeatureExtraction__Alternatives_0 )
            {
             before(grammarAccess.getFeatureExtractionAccess().getAlternatives_0()); 
            // InternalCNNDescLang.g:1426:2: ( rule__FeatureExtraction__Alternatives_0 )
            // InternalCNNDescLang.g:1426:3: rule__FeatureExtraction__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__FeatureExtraction__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getFeatureExtractionAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Group__0__Impl"


    // $ANTLR start "rule__FeatureExtraction__Group__1"
    // InternalCNNDescLang.g:1434:1: rule__FeatureExtraction__Group__1 : rule__FeatureExtraction__Group__1__Impl rule__FeatureExtraction__Group__2 ;
    public final void rule__FeatureExtraction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1438:1: ( rule__FeatureExtraction__Group__1__Impl rule__FeatureExtraction__Group__2 )
            // InternalCNNDescLang.g:1439:2: rule__FeatureExtraction__Group__1__Impl rule__FeatureExtraction__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__FeatureExtraction__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FeatureExtraction__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Group__1"


    // $ANTLR start "rule__FeatureExtraction__Group__1__Impl"
    // InternalCNNDescLang.g:1446:1: rule__FeatureExtraction__Group__1__Impl : ( ( rule__FeatureExtraction__DropAssignment_1 )? ) ;
    public final void rule__FeatureExtraction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1450:1: ( ( ( rule__FeatureExtraction__DropAssignment_1 )? ) )
            // InternalCNNDescLang.g:1451:1: ( ( rule__FeatureExtraction__DropAssignment_1 )? )
            {
            // InternalCNNDescLang.g:1451:1: ( ( rule__FeatureExtraction__DropAssignment_1 )? )
            // InternalCNNDescLang.g:1452:2: ( rule__FeatureExtraction__DropAssignment_1 )?
            {
             before(grammarAccess.getFeatureExtractionAccess().getDropAssignment_1()); 
            // InternalCNNDescLang.g:1453:2: ( rule__FeatureExtraction__DropAssignment_1 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==11) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCNNDescLang.g:1453:3: rule__FeatureExtraction__DropAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__FeatureExtraction__DropAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureExtractionAccess().getDropAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Group__1__Impl"


    // $ANTLR start "rule__FeatureExtraction__Group__2"
    // InternalCNNDescLang.g:1461:1: rule__FeatureExtraction__Group__2 : rule__FeatureExtraction__Group__2__Impl ;
    public final void rule__FeatureExtraction__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1465:1: ( rule__FeatureExtraction__Group__2__Impl )
            // InternalCNNDescLang.g:1466:2: rule__FeatureExtraction__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FeatureExtraction__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Group__2"


    // $ANTLR start "rule__FeatureExtraction__Group__2__Impl"
    // InternalCNNDescLang.g:1472:1: rule__FeatureExtraction__Group__2__Impl : ( ( rule__FeatureExtraction__PoolAssignment_2 )? ) ;
    public final void rule__FeatureExtraction__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1476:1: ( ( ( rule__FeatureExtraction__PoolAssignment_2 )? ) )
            // InternalCNNDescLang.g:1477:1: ( ( rule__FeatureExtraction__PoolAssignment_2 )? )
            {
            // InternalCNNDescLang.g:1477:1: ( ( rule__FeatureExtraction__PoolAssignment_2 )? )
            // InternalCNNDescLang.g:1478:2: ( rule__FeatureExtraction__PoolAssignment_2 )?
            {
             before(grammarAccess.getFeatureExtractionAccess().getPoolAssignment_2()); 
            // InternalCNNDescLang.g:1479:2: ( rule__FeatureExtraction__PoolAssignment_2 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=12 && LA22_0<=13)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalCNNDescLang.g:1479:3: rule__FeatureExtraction__PoolAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__FeatureExtraction__PoolAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureExtractionAccess().getPoolAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__Group__2__Impl"


    // $ANTLR start "rule__CNNDescLang__CnndesclangAssignment"
    // InternalCNNDescLang.g:1488:1: rule__CNNDescLang__CnndesclangAssignment : ( ruleArchitecture ) ;
    public final void rule__CNNDescLang__CnndesclangAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1492:1: ( ( ruleArchitecture ) )
            // InternalCNNDescLang.g:1493:2: ( ruleArchitecture )
            {
            // InternalCNNDescLang.g:1493:2: ( ruleArchitecture )
            // InternalCNNDescLang.g:1494:3: ruleArchitecture
            {
             before(grammarAccess.getCNNDescLangAccess().getCnndesclangArchitectureParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleArchitecture();

            state._fsp--;

             after(grammarAccess.getCNNDescLangAccess().getCnndesclangArchitectureParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CNNDescLang__CnndesclangAssignment"


    // $ANTLR start "rule__Architecture__InputAssignment_0"
    // InternalCNNDescLang.g:1503:1: rule__Architecture__InputAssignment_0 : ( ( 'input' ) ) ;
    public final void rule__Architecture__InputAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1507:1: ( ( ( 'input' ) ) )
            // InternalCNNDescLang.g:1508:2: ( ( 'input' ) )
            {
            // InternalCNNDescLang.g:1508:2: ( ( 'input' ) )
            // InternalCNNDescLang.g:1509:3: ( 'input' )
            {
             before(grammarAccess.getArchitectureAccess().getInputInputKeyword_0_0()); 
            // InternalCNNDescLang.g:1510:3: ( 'input' )
            // InternalCNNDescLang.g:1511:4: 'input'
            {
             before(grammarAccess.getArchitectureAccess().getInputInputKeyword_0_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getArchitectureAccess().getInputInputKeyword_0_0()); 

            }

             after(grammarAccess.getArchitectureAccess().getInputInputKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__InputAssignment_0"


    // $ANTLR start "rule__Architecture__FeAssignment_1"
    // InternalCNNDescLang.g:1522:1: rule__Architecture__FeAssignment_1 : ( ruleFeatureExtraction ) ;
    public final void rule__Architecture__FeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1526:1: ( ( ruleFeatureExtraction ) )
            // InternalCNNDescLang.g:1527:2: ( ruleFeatureExtraction )
            {
            // InternalCNNDescLang.g:1527:2: ( ruleFeatureExtraction )
            // InternalCNNDescLang.g:1528:3: ruleFeatureExtraction
            {
             before(grammarAccess.getArchitectureAccess().getFeFeatureExtractionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleFeatureExtraction();

            state._fsp--;

             after(grammarAccess.getArchitectureAccess().getFeFeatureExtractionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__FeAssignment_1"


    // $ANTLR start "rule__Architecture__InterAssignment_2_0"
    // InternalCNNDescLang.g:1537:1: rule__Architecture__InterAssignment_2_0 : ( ruleInterstice ) ;
    public final void rule__Architecture__InterAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1541:1: ( ( ruleInterstice ) )
            // InternalCNNDescLang.g:1542:2: ( ruleInterstice )
            {
            // InternalCNNDescLang.g:1542:2: ( ruleInterstice )
            // InternalCNNDescLang.g:1543:3: ruleInterstice
            {
             before(grammarAccess.getArchitectureAccess().getInterIntersticeParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleInterstice();

            state._fsp--;

             after(grammarAccess.getArchitectureAccess().getInterIntersticeParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__InterAssignment_2_0"


    // $ANTLR start "rule__Architecture__ClassAssignment_2_1"
    // InternalCNNDescLang.g:1552:1: rule__Architecture__ClassAssignment_2_1 : ( ruleClassification ) ;
    public final void rule__Architecture__ClassAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1556:1: ( ( ruleClassification ) )
            // InternalCNNDescLang.g:1557:2: ( ruleClassification )
            {
            // InternalCNNDescLang.g:1557:2: ( ruleClassification )
            // InternalCNNDescLang.g:1558:3: ruleClassification
            {
             before(grammarAccess.getArchitectureAccess().getClassClassificationParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleClassification();

            state._fsp--;

             after(grammarAccess.getArchitectureAccess().getClassClassificationParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__ClassAssignment_2_1"


    // $ANTLR start "rule__Architecture__OutputAssignment_3"
    // InternalCNNDescLang.g:1567:1: rule__Architecture__OutputAssignment_3 : ( ( 'output' ) ) ;
    public final void rule__Architecture__OutputAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1571:1: ( ( ( 'output' ) ) )
            // InternalCNNDescLang.g:1572:2: ( ( 'output' ) )
            {
            // InternalCNNDescLang.g:1572:2: ( ( 'output' ) )
            // InternalCNNDescLang.g:1573:3: ( 'output' )
            {
             before(grammarAccess.getArchitectureAccess().getOutputOutputKeyword_3_0()); 
            // InternalCNNDescLang.g:1574:3: ( 'output' )
            // InternalCNNDescLang.g:1575:4: 'output'
            {
             before(grammarAccess.getArchitectureAccess().getOutputOutputKeyword_3_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getArchitectureAccess().getOutputOutputKeyword_3_0()); 

            }

             after(grammarAccess.getArchitectureAccess().getOutputOutputKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Architecture__OutputAssignment_3"


    // $ANTLR start "rule__Convolution__BnconvAssignment_0"
    // InternalCNNDescLang.g:1586:1: rule__Convolution__BnconvAssignment_0 : ( ( 'bnconv' ) ) ;
    public final void rule__Convolution__BnconvAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1590:1: ( ( ( 'bnconv' ) ) )
            // InternalCNNDescLang.g:1591:2: ( ( 'bnconv' ) )
            {
            // InternalCNNDescLang.g:1591:2: ( ( 'bnconv' ) )
            // InternalCNNDescLang.g:1592:3: ( 'bnconv' )
            {
             before(grammarAccess.getConvolutionAccess().getBnconvBnconvKeyword_0_0()); 
            // InternalCNNDescLang.g:1593:3: ( 'bnconv' )
            // InternalCNNDescLang.g:1594:4: 'bnconv'
            {
             before(grammarAccess.getConvolutionAccess().getBnconvBnconvKeyword_0_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getConvolutionAccess().getBnconvBnconvKeyword_0_0()); 

            }

             after(grammarAccess.getConvolutionAccess().getBnconvBnconvKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Convolution__BnconvAssignment_0"


    // $ANTLR start "rule__Convolution__ConvbnAssignment_1"
    // InternalCNNDescLang.g:1605:1: rule__Convolution__ConvbnAssignment_1 : ( ( 'convbn' ) ) ;
    public final void rule__Convolution__ConvbnAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1609:1: ( ( ( 'convbn' ) ) )
            // InternalCNNDescLang.g:1610:2: ( ( 'convbn' ) )
            {
            // InternalCNNDescLang.g:1610:2: ( ( 'convbn' ) )
            // InternalCNNDescLang.g:1611:3: ( 'convbn' )
            {
             before(grammarAccess.getConvolutionAccess().getConvbnConvbnKeyword_1_0()); 
            // InternalCNNDescLang.g:1612:3: ( 'convbn' )
            // InternalCNNDescLang.g:1613:4: 'convbn'
            {
             before(grammarAccess.getConvolutionAccess().getConvbnConvbnKeyword_1_0()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getConvolutionAccess().getConvbnConvbnKeyword_1_0()); 

            }

             after(grammarAccess.getConvolutionAccess().getConvbnConvbnKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Convolution__ConvbnAssignment_1"


    // $ANTLR start "rule__Convolution__ConvAssignment_2"
    // InternalCNNDescLang.g:1624:1: rule__Convolution__ConvAssignment_2 : ( ( 'conv' ) ) ;
    public final void rule__Convolution__ConvAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1628:1: ( ( ( 'conv' ) ) )
            // InternalCNNDescLang.g:1629:2: ( ( 'conv' ) )
            {
            // InternalCNNDescLang.g:1629:2: ( ( 'conv' ) )
            // InternalCNNDescLang.g:1630:3: ( 'conv' )
            {
             before(grammarAccess.getConvolutionAccess().getConvConvKeyword_2_0()); 
            // InternalCNNDescLang.g:1631:3: ( 'conv' )
            // InternalCNNDescLang.g:1632:4: 'conv'
            {
             before(grammarAccess.getConvolutionAccess().getConvConvKeyword_2_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getConvolutionAccess().getConvConvKeyword_2_0()); 

            }

             after(grammarAccess.getConvolutionAccess().getConvConvKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Convolution__ConvAssignment_2"


    // $ANTLR start "rule__Convolution__UpconvAssignment_3"
    // InternalCNNDescLang.g:1643:1: rule__Convolution__UpconvAssignment_3 : ( ( 'upconv' ) ) ;
    public final void rule__Convolution__UpconvAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1647:1: ( ( ( 'upconv' ) ) )
            // InternalCNNDescLang.g:1648:2: ( ( 'upconv' ) )
            {
            // InternalCNNDescLang.g:1648:2: ( ( 'upconv' ) )
            // InternalCNNDescLang.g:1649:3: ( 'upconv' )
            {
             before(grammarAccess.getConvolutionAccess().getUpconvUpconvKeyword_3_0()); 
            // InternalCNNDescLang.g:1650:3: ( 'upconv' )
            // InternalCNNDescLang.g:1651:4: 'upconv'
            {
             before(grammarAccess.getConvolutionAccess().getUpconvUpconvKeyword_3_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getConvolutionAccess().getUpconvUpconvKeyword_3_0()); 

            }

             after(grammarAccess.getConvolutionAccess().getUpconvUpconvKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Convolution__UpconvAssignment_3"


    // $ANTLR start "rule__FlattenOrGlobal__FlatAssignment_0"
    // InternalCNNDescLang.g:1662:1: rule__FlattenOrGlobal__FlatAssignment_0 : ( ( 'flatten' ) ) ;
    public final void rule__FlattenOrGlobal__FlatAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1666:1: ( ( ( 'flatten' ) ) )
            // InternalCNNDescLang.g:1667:2: ( ( 'flatten' ) )
            {
            // InternalCNNDescLang.g:1667:2: ( ( 'flatten' ) )
            // InternalCNNDescLang.g:1668:3: ( 'flatten' )
            {
             before(grammarAccess.getFlattenOrGlobalAccess().getFlatFlattenKeyword_0_0()); 
            // InternalCNNDescLang.g:1669:3: ( 'flatten' )
            // InternalCNNDescLang.g:1670:4: 'flatten'
            {
             before(grammarAccess.getFlattenOrGlobalAccess().getFlatFlattenKeyword_0_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getFlattenOrGlobalAccess().getFlatFlattenKeyword_0_0()); 

            }

             after(grammarAccess.getFlattenOrGlobalAccess().getFlatFlattenKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlattenOrGlobal__FlatAssignment_0"


    // $ANTLR start "rule__FlattenOrGlobal__GpAssignment_1"
    // InternalCNNDescLang.g:1681:1: rule__FlattenOrGlobal__GpAssignment_1 : ( ruleGlobalPooling ) ;
    public final void rule__FlattenOrGlobal__GpAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1685:1: ( ( ruleGlobalPooling ) )
            // InternalCNNDescLang.g:1686:2: ( ruleGlobalPooling )
            {
            // InternalCNNDescLang.g:1686:2: ( ruleGlobalPooling )
            // InternalCNNDescLang.g:1687:3: ruleGlobalPooling
            {
             before(grammarAccess.getFlattenOrGlobalAccess().getGpGlobalPoolingParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleGlobalPooling();

            state._fsp--;

             after(grammarAccess.getFlattenOrGlobalAccess().getGpGlobalPoolingParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FlattenOrGlobal__GpAssignment_1"


    // $ANTLR start "rule__Interstice__FgAssignment"
    // InternalCNNDescLang.g:1696:1: rule__Interstice__FgAssignment : ( ruleFlattenOrGlobal ) ;
    public final void rule__Interstice__FgAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1700:1: ( ( ruleFlattenOrGlobal ) )
            // InternalCNNDescLang.g:1701:2: ( ruleFlattenOrGlobal )
            {
            // InternalCNNDescLang.g:1701:2: ( ruleFlattenOrGlobal )
            // InternalCNNDescLang.g:1702:3: ruleFlattenOrGlobal
            {
             before(grammarAccess.getIntersticeAccess().getFgFlattenOrGlobalParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleFlattenOrGlobal();

            state._fsp--;

             after(grammarAccess.getIntersticeAccess().getFgFlattenOrGlobalParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interstice__FgAssignment"


    // $ANTLR start "rule__Classification__DropAssignment_0"
    // InternalCNNDescLang.g:1711:1: rule__Classification__DropAssignment_0 : ( ruleDropout ) ;
    public final void rule__Classification__DropAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1715:1: ( ( ruleDropout ) )
            // InternalCNNDescLang.g:1716:2: ( ruleDropout )
            {
            // InternalCNNDescLang.g:1716:2: ( ruleDropout )
            // InternalCNNDescLang.g:1717:3: ruleDropout
            {
             before(grammarAccess.getClassificationAccess().getDropDropoutParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleDropout();

            state._fsp--;

             after(grammarAccess.getClassificationAccess().getDropDropoutParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Classification__DropAssignment_0"


    // $ANTLR start "rule__Classification__DAssignment_1"
    // InternalCNNDescLang.g:1726:1: rule__Classification__DAssignment_1 : ( ( 'dense' ) ) ;
    public final void rule__Classification__DAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1730:1: ( ( ( 'dense' ) ) )
            // InternalCNNDescLang.g:1731:2: ( ( 'dense' ) )
            {
            // InternalCNNDescLang.g:1731:2: ( ( 'dense' ) )
            // InternalCNNDescLang.g:1732:3: ( 'dense' )
            {
             before(grammarAccess.getClassificationAccess().getDDenseKeyword_1_0()); 
            // InternalCNNDescLang.g:1733:3: ( 'dense' )
            // InternalCNNDescLang.g:1734:4: 'dense'
            {
             before(grammarAccess.getClassificationAccess().getDDenseKeyword_1_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getClassificationAccess().getDDenseKeyword_1_0()); 

            }

             after(grammarAccess.getClassificationAccess().getDDenseKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Classification__DAssignment_1"


    // $ANTLR start "rule__ConvDrop__ConvAssignment_0"
    // InternalCNNDescLang.g:1745:1: rule__ConvDrop__ConvAssignment_0 : ( ruleConvolution ) ;
    public final void rule__ConvDrop__ConvAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1749:1: ( ( ruleConvolution ) )
            // InternalCNNDescLang.g:1750:2: ( ruleConvolution )
            {
            // InternalCNNDescLang.g:1750:2: ( ruleConvolution )
            // InternalCNNDescLang.g:1751:3: ruleConvolution
            {
             before(grammarAccess.getConvDropAccess().getConvConvolutionParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleConvolution();

            state._fsp--;

             after(grammarAccess.getConvDropAccess().getConvConvolutionParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvDrop__ConvAssignment_0"


    // $ANTLR start "rule__ConvDrop__DropAssignment_1"
    // InternalCNNDescLang.g:1760:1: rule__ConvDrop__DropAssignment_1 : ( ruleDropout ) ;
    public final void rule__ConvDrop__DropAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1764:1: ( ( ruleDropout ) )
            // InternalCNNDescLang.g:1765:2: ( ruleDropout )
            {
            // InternalCNNDescLang.g:1765:2: ( ruleDropout )
            // InternalCNNDescLang.g:1766:3: ruleDropout
            {
             before(grammarAccess.getConvDropAccess().getDropDropoutParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleDropout();

            state._fsp--;

             after(grammarAccess.getConvDropAccess().getDropDropoutParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvDrop__DropAssignment_1"


    // $ANTLR start "rule__MergeConv__MergeAssignment_0"
    // InternalCNNDescLang.g:1775:1: rule__MergeConv__MergeAssignment_0 : ( ruleMerge ) ;
    public final void rule__MergeConv__MergeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1779:1: ( ( ruleMerge ) )
            // InternalCNNDescLang.g:1780:2: ( ruleMerge )
            {
            // InternalCNNDescLang.g:1780:2: ( ruleMerge )
            // InternalCNNDescLang.g:1781:3: ruleMerge
            {
             before(grammarAccess.getMergeConvAccess().getMergeMergeParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleMerge();

            state._fsp--;

             after(grammarAccess.getMergeConvAccess().getMergeMergeParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeConv__MergeAssignment_0"


    // $ANTLR start "rule__MergeConv__ConvdropAssignment_1"
    // InternalCNNDescLang.g:1790:1: rule__MergeConv__ConvdropAssignment_1 : ( ruleConvDrop ) ;
    public final void rule__MergeConv__ConvdropAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1794:1: ( ( ruleConvDrop ) )
            // InternalCNNDescLang.g:1795:2: ( ruleConvDrop )
            {
            // InternalCNNDescLang.g:1795:2: ( ruleConvDrop )
            // InternalCNNDescLang.g:1796:3: ruleConvDrop
            {
             before(grammarAccess.getMergeConvAccess().getConvdropConvDropParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleConvDrop();

            state._fsp--;

             after(grammarAccess.getMergeConvAccess().getConvdropConvDropParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeConv__ConvdropAssignment_1"


    // $ANTLR start "rule__ConvOrMerge__ConvdropAssignment_0"
    // InternalCNNDescLang.g:1805:1: rule__ConvOrMerge__ConvdropAssignment_0 : ( ruleConvDrop ) ;
    public final void rule__ConvOrMerge__ConvdropAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1809:1: ( ( ruleConvDrop ) )
            // InternalCNNDescLang.g:1810:2: ( ruleConvDrop )
            {
            // InternalCNNDescLang.g:1810:2: ( ruleConvDrop )
            // InternalCNNDescLang.g:1811:3: ruleConvDrop
            {
             before(grammarAccess.getConvOrMergeAccess().getConvdropConvDropParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleConvDrop();

            state._fsp--;

             after(grammarAccess.getConvOrMergeAccess().getConvdropConvDropParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__ConvdropAssignment_0"


    // $ANTLR start "rule__ConvOrMerge__ConvdropAssignment_1_0"
    // InternalCNNDescLang.g:1820:1: rule__ConvOrMerge__ConvdropAssignment_1_0 : ( ruleConvDrop ) ;
    public final void rule__ConvOrMerge__ConvdropAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1824:1: ( ( ruleConvDrop ) )
            // InternalCNNDescLang.g:1825:2: ( ruleConvDrop )
            {
            // InternalCNNDescLang.g:1825:2: ( ruleConvDrop )
            // InternalCNNDescLang.g:1826:3: ruleConvDrop
            {
             before(grammarAccess.getConvOrMergeAccess().getConvdropConvDropParserRuleCall_1_0_0()); 
            pushFollow(FOLLOW_2);
            ruleConvDrop();

            state._fsp--;

             after(grammarAccess.getConvOrMergeAccess().getConvdropConvDropParserRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__ConvdropAssignment_1_0"


    // $ANTLR start "rule__ConvOrMerge__MergeConvAssignment_1_1"
    // InternalCNNDescLang.g:1835:1: rule__ConvOrMerge__MergeConvAssignment_1_1 : ( ruleMergeConv ) ;
    public final void rule__ConvOrMerge__MergeConvAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1839:1: ( ( ruleMergeConv ) )
            // InternalCNNDescLang.g:1840:2: ( ruleMergeConv )
            {
            // InternalCNNDescLang.g:1840:2: ( ruleMergeConv )
            // InternalCNNDescLang.g:1841:3: ruleMergeConv
            {
             before(grammarAccess.getConvOrMergeAccess().getMergeConvMergeConvParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMergeConv();

            state._fsp--;

             after(grammarAccess.getConvOrMergeAccess().getMergeConvMergeConvParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConvOrMerge__MergeConvAssignment_1_1"


    // $ANTLR start "rule__Left__PAssignment_0"
    // InternalCNNDescLang.g:1850:1: rule__Left__PAssignment_0 : ( rulePooling ) ;
    public final void rule__Left__PAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1854:1: ( ( rulePooling ) )
            // InternalCNNDescLang.g:1855:2: ( rulePooling )
            {
            // InternalCNNDescLang.g:1855:2: ( rulePooling )
            // InternalCNNDescLang.g:1856:3: rulePooling
            {
             before(grammarAccess.getLeftAccess().getPPoolingParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            rulePooling();

            state._fsp--;

             after(grammarAccess.getLeftAccess().getPPoolingParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__PAssignment_0"


    // $ANTLR start "rule__Left__ComAssignment_1"
    // InternalCNNDescLang.g:1865:1: rule__Left__ComAssignment_1 : ( ruleConvOrMerge ) ;
    public final void rule__Left__ComAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1869:1: ( ( ruleConvOrMerge ) )
            // InternalCNNDescLang.g:1870:2: ( ruleConvOrMerge )
            {
            // InternalCNNDescLang.g:1870:2: ( ruleConvOrMerge )
            // InternalCNNDescLang.g:1871:3: ruleConvOrMerge
            {
             before(grammarAccess.getLeftAccess().getComConvOrMergeParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleConvOrMerge();

            state._fsp--;

             after(grammarAccess.getLeftAccess().getComConvOrMergeParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__ComAssignment_1"


    // $ANTLR start "rule__Left__PoolAssignment_2"
    // InternalCNNDescLang.g:1880:1: rule__Left__PoolAssignment_2 : ( rulePooling ) ;
    public final void rule__Left__PoolAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1884:1: ( ( rulePooling ) )
            // InternalCNNDescLang.g:1885:2: ( rulePooling )
            {
            // InternalCNNDescLang.g:1885:2: ( rulePooling )
            // InternalCNNDescLang.g:1886:3: rulePooling
            {
             before(grammarAccess.getLeftAccess().getPoolPoolingParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            rulePooling();

            state._fsp--;

             after(grammarAccess.getLeftAccess().getPoolPoolingParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Left__PoolAssignment_2"


    // $ANTLR start "rule__Right__ConvAssignment_0"
    // InternalCNNDescLang.g:1895:1: rule__Right__ConvAssignment_0 : ( ruleConvolution ) ;
    public final void rule__Right__ConvAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1899:1: ( ( ruleConvolution ) )
            // InternalCNNDescLang.g:1900:2: ( ruleConvolution )
            {
            // InternalCNNDescLang.g:1900:2: ( ruleConvolution )
            // InternalCNNDescLang.g:1901:3: ruleConvolution
            {
             before(grammarAccess.getRightAccess().getConvConvolutionParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleConvolution();

            state._fsp--;

             after(grammarAccess.getRightAccess().getConvConvolutionParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__ConvAssignment_0"


    // $ANTLR start "rule__Right__EmptyAssignment_1_1"
    // InternalCNNDescLang.g:1910:1: rule__Right__EmptyAssignment_1_1 : ( ( 'Empty' ) ) ;
    public final void rule__Right__EmptyAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1914:1: ( ( ( 'Empty' ) ) )
            // InternalCNNDescLang.g:1915:2: ( ( 'Empty' ) )
            {
            // InternalCNNDescLang.g:1915:2: ( ( 'Empty' ) )
            // InternalCNNDescLang.g:1916:3: ( 'Empty' )
            {
             before(grammarAccess.getRightAccess().getEmptyEmptyKeyword_1_1_0()); 
            // InternalCNNDescLang.g:1917:3: ( 'Empty' )
            // InternalCNNDescLang.g:1918:4: 'Empty'
            {
             before(grammarAccess.getRightAccess().getEmptyEmptyKeyword_1_1_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getRightAccess().getEmptyEmptyKeyword_1_1_0()); 

            }

             after(grammarAccess.getRightAccess().getEmptyEmptyKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Right__EmptyAssignment_1_1"


    // $ANTLR start "rule__MergeBody__LeftAssignment_1"
    // InternalCNNDescLang.g:1929:1: rule__MergeBody__LeftAssignment_1 : ( ruleLeft ) ;
    public final void rule__MergeBody__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1933:1: ( ( ruleLeft ) )
            // InternalCNNDescLang.g:1934:2: ( ruleLeft )
            {
            // InternalCNNDescLang.g:1934:2: ( ruleLeft )
            // InternalCNNDescLang.g:1935:3: ruleLeft
            {
             before(grammarAccess.getMergeBodyAccess().getLeftLeftParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleLeft();

            state._fsp--;

             after(grammarAccess.getMergeBodyAccess().getLeftLeftParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__LeftAssignment_1"


    // $ANTLR start "rule__MergeBody__VirgAssignment_2"
    // InternalCNNDescLang.g:1944:1: rule__MergeBody__VirgAssignment_2 : ( ( ',' ) ) ;
    public final void rule__MergeBody__VirgAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1948:1: ( ( ( ',' ) ) )
            // InternalCNNDescLang.g:1949:2: ( ( ',' ) )
            {
            // InternalCNNDescLang.g:1949:2: ( ( ',' ) )
            // InternalCNNDescLang.g:1950:3: ( ',' )
            {
             before(grammarAccess.getMergeBodyAccess().getVirgCommaKeyword_2_0()); 
            // InternalCNNDescLang.g:1951:3: ( ',' )
            // InternalCNNDescLang.g:1952:4: ','
            {
             before(grammarAccess.getMergeBodyAccess().getVirgCommaKeyword_2_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getMergeBodyAccess().getVirgCommaKeyword_2_0()); 

            }

             after(grammarAccess.getMergeBodyAccess().getVirgCommaKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__VirgAssignment_2"


    // $ANTLR start "rule__MergeBody__RightAssignment_3"
    // InternalCNNDescLang.g:1963:1: rule__MergeBody__RightAssignment_3 : ( ruleRight ) ;
    public final void rule__MergeBody__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1967:1: ( ( ruleRight ) )
            // InternalCNNDescLang.g:1968:2: ( ruleRight )
            {
            // InternalCNNDescLang.g:1968:2: ( ruleRight )
            // InternalCNNDescLang.g:1969:3: ruleRight
            {
             before(grammarAccess.getMergeBodyAccess().getRightRightParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleRight();

            state._fsp--;

             after(grammarAccess.getMergeBodyAccess().getRightRightParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MergeBody__RightAssignment_3"


    // $ANTLR start "rule__Merge__DbAssignment_0"
    // InternalCNNDescLang.g:1978:1: rule__Merge__DbAssignment_0 : ( ( '[' ) ) ;
    public final void rule__Merge__DbAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:1982:1: ( ( ( '[' ) ) )
            // InternalCNNDescLang.g:1983:2: ( ( '[' ) )
            {
            // InternalCNNDescLang.g:1983:2: ( ( '[' ) )
            // InternalCNNDescLang.g:1984:3: ( '[' )
            {
             before(grammarAccess.getMergeAccess().getDbLeftSquareBracketKeyword_0_0()); 
            // InternalCNNDescLang.g:1985:3: ( '[' )
            // InternalCNNDescLang.g:1986:4: '['
            {
             before(grammarAccess.getMergeAccess().getDbLeftSquareBracketKeyword_0_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getMergeAccess().getDbLeftSquareBracketKeyword_0_0()); 

            }

             after(grammarAccess.getMergeAccess().getDbLeftSquareBracketKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__DbAssignment_0"


    // $ANTLR start "rule__Merge__MergeBodyAssignment_1"
    // InternalCNNDescLang.g:1997:1: rule__Merge__MergeBodyAssignment_1 : ( ruleMergeBody ) ;
    public final void rule__Merge__MergeBodyAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:2001:1: ( ( ruleMergeBody ) )
            // InternalCNNDescLang.g:2002:2: ( ruleMergeBody )
            {
            // InternalCNNDescLang.g:2002:2: ( ruleMergeBody )
            // InternalCNNDescLang.g:2003:3: ruleMergeBody
            {
             before(grammarAccess.getMergeAccess().getMergeBodyMergeBodyParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMergeBody();

            state._fsp--;

             after(grammarAccess.getMergeAccess().getMergeBodyMergeBodyParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__MergeBodyAssignment_1"


    // $ANTLR start "rule__Merge__FmAssignment_2"
    // InternalCNNDescLang.g:2012:1: rule__Merge__FmAssignment_2 : ( ( ']' ) ) ;
    public final void rule__Merge__FmAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:2016:1: ( ( ( ']' ) ) )
            // InternalCNNDescLang.g:2017:2: ( ( ']' ) )
            {
            // InternalCNNDescLang.g:2017:2: ( ( ']' ) )
            // InternalCNNDescLang.g:2018:3: ( ']' )
            {
             before(grammarAccess.getMergeAccess().getFmRightSquareBracketKeyword_2_0()); 
            // InternalCNNDescLang.g:2019:3: ( ']' )
            // InternalCNNDescLang.g:2020:4: ']'
            {
             before(grammarAccess.getMergeAccess().getFmRightSquareBracketKeyword_2_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getMergeAccess().getFmRightSquareBracketKeyword_2_0()); 

            }

             after(grammarAccess.getMergeAccess().getFmRightSquareBracketKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Merge__FmAssignment_2"


    // $ANTLR start "rule__FeatureExtraction__ConvAssignment_0_0"
    // InternalCNNDescLang.g:2031:1: rule__FeatureExtraction__ConvAssignment_0_0 : ( ruleConvolution ) ;
    public final void rule__FeatureExtraction__ConvAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:2035:1: ( ( ruleConvolution ) )
            // InternalCNNDescLang.g:2036:2: ( ruleConvolution )
            {
            // InternalCNNDescLang.g:2036:2: ( ruleConvolution )
            // InternalCNNDescLang.g:2037:3: ruleConvolution
            {
             before(grammarAccess.getFeatureExtractionAccess().getConvConvolutionParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_2);
            ruleConvolution();

            state._fsp--;

             after(grammarAccess.getFeatureExtractionAccess().getConvConvolutionParserRuleCall_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__ConvAssignment_0_0"


    // $ANTLR start "rule__FeatureExtraction__MergeAssignment_0_1"
    // InternalCNNDescLang.g:2046:1: rule__FeatureExtraction__MergeAssignment_0_1 : ( ruleMerge ) ;
    public final void rule__FeatureExtraction__MergeAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:2050:1: ( ( ruleMerge ) )
            // InternalCNNDescLang.g:2051:2: ( ruleMerge )
            {
            // InternalCNNDescLang.g:2051:2: ( ruleMerge )
            // InternalCNNDescLang.g:2052:3: ruleMerge
            {
             before(grammarAccess.getFeatureExtractionAccess().getMergeMergeParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMerge();

            state._fsp--;

             after(grammarAccess.getFeatureExtractionAccess().getMergeMergeParserRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__MergeAssignment_0_1"


    // $ANTLR start "rule__FeatureExtraction__DropAssignment_1"
    // InternalCNNDescLang.g:2061:1: rule__FeatureExtraction__DropAssignment_1 : ( ruleDropout ) ;
    public final void rule__FeatureExtraction__DropAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:2065:1: ( ( ruleDropout ) )
            // InternalCNNDescLang.g:2066:2: ( ruleDropout )
            {
            // InternalCNNDescLang.g:2066:2: ( ruleDropout )
            // InternalCNNDescLang.g:2067:3: ruleDropout
            {
             before(grammarAccess.getFeatureExtractionAccess().getDropDropoutParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleDropout();

            state._fsp--;

             after(grammarAccess.getFeatureExtractionAccess().getDropDropoutParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__DropAssignment_1"


    // $ANTLR start "rule__FeatureExtraction__PoolAssignment_2"
    // InternalCNNDescLang.g:2076:1: rule__FeatureExtraction__PoolAssignment_2 : ( rulePooling ) ;
    public final void rule__FeatureExtraction__PoolAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalCNNDescLang.g:2080:1: ( ( rulePooling ) )
            // InternalCNNDescLang.g:2081:2: ( rulePooling )
            {
            // InternalCNNDescLang.g:2081:2: ( rulePooling )
            // InternalCNNDescLang.g:2082:3: rulePooling
            {
             before(grammarAccess.getFeatureExtractionAccess().getPoolPoolingParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            rulePooling();

            state._fsp--;

             after(grammarAccess.getFeatureExtractionAccess().getPoolPoolingParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FeatureExtraction__PoolAssignment_2"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    static final String dfa_1s = "\15\uffff";
    static final String dfa_2s = "\1\uffff\4\13\1\uffff\5\13\1\uffff\1\13";
    static final String dfa_3s = "\1\24\4\13\1\uffff\1\14\4\13\1\uffff\1\14";
    static final String dfa_4s = "\5\34\1\uffff\5\34\1\uffff\1\34";
    static final String dfa_5s = "\5\uffff\1\2\5\uffff\1\1\1\uffff";
    static final String dfa_6s = "\15\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\1\2\1\3\1\4\4\uffff\1\5",
            "\1\6\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\6\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\6\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\6\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "",
            "\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\14\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\14\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\14\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "\1\14\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5",
            "",
            "\2\13\6\uffff\1\7\1\10\1\11\1\12\3\uffff\1\13\1\5"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "573:1: rule__ConvOrMerge__Alternatives : ( ( ( ( rule__ConvOrMerge__ConvdropAssignment_0 ) ) ( ( rule__ConvOrMerge__ConvdropAssignment_0 )* ) ) | ( ( rule__ConvOrMerge__Group_1__0 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000F00002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000010F00000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000000108C000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000010F00002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000002000800L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000002000802L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000F00000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000F03000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000004F00000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000003800L});

}