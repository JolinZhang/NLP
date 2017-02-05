import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Jonelezhang on 1/30/17.
 */
public class LaplaceSmoothing {
    public void laplaceSmoothing(String[] text, int[][] counts, ArrayList<String> title, HashMap<String, Integer> typesCount){

        float[][] countResult = new float[title.size()][title.size()];
        float[][] result = new float[title.size()][title.size()];
        DecimalFormat df = new DecimalFormat("#.#####");
        for(int i=0; i< counts.length; i++){
            float devide = typesCount.get(title.get(i));
            for(int j =0; j< counts[0].length;j++){
                countResult[i][j] = counts[i][j]+1;
                result[i][j] = Float.parseFloat(df.format( countResult[i][j] / (devide+title.size()) ));
            }
        }

        //print counts
        ReadFile readFile = new ReadFile();
        System.out.println("counts for laplaceSmoothing");
        readFile.print(countResult, title);

        //print probabilities
        System.out.println("probabilities for laplaceSmoothing");
        readFile.print(result, title);

        //print sentence probabilities
        float senPro = readFile.slidTwoProb(text, result, title);
        System.out.println("probabilities for the sentence of nSmoothing:" + senPro);

    }
}
