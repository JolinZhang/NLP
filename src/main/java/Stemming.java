import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * Created by Jonelezhang on 1/29/17.
 */
public class Stemming {
    protected StanfordCoreNLP pipeline;
    public String stem(String line){
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        this.pipeline = new StanfordCoreNLP(props);

        StringBuilder lemmas = new StringBuilder();
        // create an empty Annotation just with the given text
        Annotation document = new Annotation(line);

        // run all Annotators on this text
        this.pipeline.annotate(document);

        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the list of lemmas
                lemmas.append(token.get(CoreAnnotations.LemmaAnnotation.class));
            }
        }
        return String.valueOf(lemmas);

    }

}
