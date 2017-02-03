import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.prism.Graphics;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Jonelezhang on 1/30/17.
 */
public class NoSmoothing {
    public void noSmoothing(int[][] counts,  ArrayList<String> title,  Hashtable<String, Integer> typesCount) {


        //print counts
        Bigram bigram = new Bigram();
        System.out.println("counts for noSmoothing");
        bigram.print(counts, title);

        float[][] result = new float[title.size()][title.size()];
        DecimalFormat df = new DecimalFormat("#.#####");
        for (int i = 0; i < counts.length; i++) {
            float devide = typesCount.get(title.get(i));
            for (int j = 0; j < counts[0].length; j++) {
                result[i][j] = Float.parseFloat(df.format(counts[i][j] / devide));
            }
        }
        //print probabilities
        System.out.println("probabilities for nSmoothing");
        bigram.print(result, title);


    }
}
