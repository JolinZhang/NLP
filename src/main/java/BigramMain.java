import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Jonelezhang on 2/4/17.
 */
public class BigramMain {



    public static void main(String[] args){
        ReadFile readFile = new ReadFile();

        //read corpus.txt
        String corpus = "Corpus.txt";
        String[]  corpusText = readFile.readFile(corpus);
        HashMap<String, Integer> corpusTypesCount =  readFile.types(corpusText);

        readFile.createTable(corpusTypesCount);
        ArrayList<String> corpusTitle = readFile.title;
        readFile.slideTwo(corpusText, corpusTitle);
        Hashtable<String, Integer> corpusCountsTable = readFile.countsTable;
//        readFile.outputPair(corpusCountsTable);


        //read test1.txt
        String sentence1 = "test1.txt";
        String[] sentence1Text = readFile.readFile(sentence1);
        HashMap<String, Integer> sentence1TypesCount =  readFile.types(sentence1Text);

        readFile.createTable(sentence1TypesCount);
        ArrayList<String> sentence1Title = readFile.title;
        readFile.slideTwoTest(sentence1Text, sentence1Title, corpusCountsTable);
        int[][] sentence1Counts = readFile.counts;



        //read test2.txt
        String sentence2 = "test2.txt";
        String[] sentence2Text = readFile.readFile(sentence2);
        HashMap<String, Integer> sentence2TypesCount =  readFile.types(sentence2Text);

        readFile.createTable(sentence2TypesCount);
        ArrayList<String> sentence2Title = readFile.title;
        readFile.slideTwoTest(sentence2Text, sentence2Title, corpusCountsTable);
        int[][] sentence2Counts = readFile.counts;



        //no smoothing
        NoSmoothing noSmoothing = new NoSmoothing();
        noSmoothing.noSmoothing(sentence1Text, sentence1Counts, sentence1Title, corpusTypesCount );
        noSmoothing.noSmoothing(sentence2Text, sentence2Counts, sentence2Title, corpusTypesCount );


        //laplace smoothing
        LaplaceSmoothing laplaceSmoothing = new LaplaceSmoothing();
        laplaceSmoothing.laplaceSmoothing(sentence1Text, sentence1Counts, sentence1Title, corpusTypesCount);
        laplaceSmoothing.laplaceSmoothing(sentence2Text, sentence2Counts, sentence2Title, corpusTypesCount);

        GoodTuring goodTuring = new GoodTuring();
        goodTuring.goodTuring(sentence1Text, sentence1Counts, sentence1Title, corpusCountsTable);

    }



}
