import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Jonelezhang on 1/31/17.
 */
public class GoodTuring{
    public void goodTuring(String[] text, int[][] counts, ArrayList<String> title,  Hashtable<String, Integer> corpusCountsTable){


        Hashtable<Integer, Integer> countsSum = new Hashtable<Integer, Integer>();

        int totalN =0;
        for(Integer v: corpusCountsTable.values()){
            //save the count sum for each num, for N-gram that occur c times.
            if(countsSum.containsKey(v)){
                countsSum.put(v, countsSum.get(v)+1);
            }else{
                countsSum.put(v, 1);
            }
            //get the sum for all num
            totalN += v;
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
                //count n-gram that occurs 0 time.
                if(counts[i][j] == 0 ){
                    result[i][j] = Float.parseFloat(df.format((float)countsSum.get(counts[i][j]+1)/totalN));
                }else{
                    //count n-gram that occurs > 0 times.
                    float temp1 = (float)countsSum.get(counts[i][j]+1)/ countsSum.get(counts[i][j]);
                    float temp2 = temp1 *(counts[i][j]+1);
                    float temp3 = temp2/totalN;
                    result[i][j] = Float.parseFloat(df.format(temp3));
                }

            }
        }


        ReadFile readFile = new ReadFile();
        //print probabilities
        System.out.println("probabilities for good turing");
        readFile.print(result, title);

        //print sentence probabilities
        float senPro = readFile.slidTwoProb(text, result, title);
        System.out.println("probabilities for the sentence of nSmoothing:" + senPro);

    }



}
