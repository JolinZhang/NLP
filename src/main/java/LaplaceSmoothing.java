import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Jonelezhang on 1/30/17.
 */
public class LaplaceSmoothing {
    public void laplaceSmoothing(int[][] counts, ArrayList<String> title, Hashtable<String, Integer> typesCount){

        //print counts
        Bigram bigram = new Bigram();
        System.out.println("counts for laplaceSmoothing");
        bigram.print(counts, title);

        float[][] result = new float[title.size()][title.size()];
        DecimalFormat df = new DecimalFormat("#.#####");
        for(int i=0; i< counts.length; i++){
            float devide = typesCount.get(title.get(i));
            for(int j =0; j< counts[0].length;j++){
                result[i][j] = Float.parseFloat(df.format( (counts[i][j]+1)/(devide+title.size()) ));
            }
        }

        //print probabilities
        System.out.println("probabilities for laplaceSmoothing");
        bigram.print(result, title);

    }
}
