import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.zip.Inflater;

/**
 * Created by Jonelezhang on 1/31/17.
 */
public class GoodTuring{
    public void goodTuring(int[][] counts, ArrayList<String> title, Hashtable<String, Integer> typesCount){

        Bigram bigram = new Bigram();

        Hashtable<Integer, Integer> countsSum = new Hashtable<Integer, Integer>();
        int totalN = 0;
        for(int i =0; i< counts.length; i++){
            for(int j =0; j< counts[0].length; j++){
                //save the count sum for each counts'num
                if(countsSum.containsKey(counts[i][j])){
                    countsSum.put(counts[i][j], countsSum.get(counts[i][j])+1);
                }else{
                    countsSum.put(counts[i][j], 1);
                }
                //get the sum for all counts'num
                totalN += counts[i][j];
            }
        }

        float[][] result = new float[title.size()][title.size()];
        for(int i = 0; i< counts.length; i++){
            for(int j = 0; j< counts[0].length; j++){
                //case when Nc+1 = 0
                if(countsSum.get(counts[i][j]+1)== null){
                    countsSum.put(counts[i][j]+1,1);
                }
                //calculate
                DecimalFormat df = new DecimalFormat("#.#####");
                if(counts[i][j] == 0 ){
                    result[i][j] = Float.parseFloat(df.format((float)countsSum.get(counts[i][j]+1)/totalN));
                }else{
                    float temp1 = (float)countsSum.get(counts[i][j]+1)/ countsSum.get(counts[i][j]);
                    float temp2 = temp1 *(counts[i][j]+1);
                    result[i][j] = Float.parseFloat(df.format(temp2/totalN));
                }

            }
        }

        //print probabilities
        System.out.println("probabilities for good turing");
        bigram.print(result, title);

    }



}
