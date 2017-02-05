import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Jonelezhang on 1/30/17.
 */
public class NoSmoothing {
    public void noSmoothing(String[] text, int[][] counts,  ArrayList<String> title,  HashMap<String, Integer> typesCount) {

        float[][] result = new float[title.size()][title.size()];
        DecimalFormat df = new DecimalFormat("#.#####");
        for (int i = 0; i < counts.length; i++) {
            float devide = typesCount.get(title.get(i));
            for (int j = 0; j < counts[0].length; j++) {
                result[i][j] = Float.parseFloat(df.format(counts[i][j] / devide));
            }
        }

        //print counts
        ReadFile readFile = new ReadFile();
        System.out.println("counts for noSmoothing");
        readFile.print(counts, title);

        //print probabilities
        System.out.println("probabilities for nSmoothing");
        readFile.print(result, title);

        //print sentence probabilities
        float senPro = readFile.slidTwoProb(text, result, title);
        System.out.println("probabilities for sentence of nSmoothing:" + senPro);

    }
}
