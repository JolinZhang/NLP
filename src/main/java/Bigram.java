
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Jonelezhang on 1/29/17.
 */
public class Bigram {
    String[] text;
    HashSet<String> types;
    Hashtable<String, Integer> typesCount;
    int[][] counts;
    ArrayList<String> title;
    Hashtable<String, Integer> countsTable;

    //read file keep all word and .
    public void readFile(){
        BufferedReader file = null;
        //read file
        String path = Bigram.class.getClassLoader().getResource("Corpus.txt").getPath();
        try{
            file = new BufferedReader(new FileReader(path));
        }catch (Exception e){
            System.out.print("read file failed");
        }

        //keep all word in content.
        String line ="";
        StringBuilder content= new StringBuilder();
        try{
            while(line != null){
                line = file.readLine().replaceAll("[^a-zA-Z]"," ").toLowerCase();
                content.append(line+" ");
            }
        }catch (Exception e) {

        }
        //split by space keep all word in text array.
        text = String.valueOf(content).split("\\s+");
//                for(String s: text){
//            System.out.println(s);
//        }

    }

    //get types all distinct word
    public void types(String[] text){
        types = new HashSet<String>();
        typesCount = new Hashtable<String, Integer>();
        for(String s: text){
            if(!types.contains(s) && s.length()!=0){
                types.add(s);
                typesCount.put(s,1);
            }else if(types.contains(s)){
                typesCount.put(s, typesCount.get(s)+1);
            }
        }
//        for(String s: types){
//            System.out.println(s);
//        }
//
//        for(Map.Entry<String, Integer> entry: typesCount.entrySet()){
//            System.out.print(entry.getKey()+" ");
//            System.out.print(entry.getValue()+" ");
//            System.out.println();
//        }
    }


    //get count for pair
    public void slideTwo(String[] text){
        int typeNum = types.size();
        //define the counts array.
        counts = new int[typeNum][typeNum];
        Hashtable<String, Integer> typeTable = new Hashtable<String, Integer>();
        //keep counts pair info;
        countsTable = new Hashtable<String, Integer>();
        title = new ArrayList<String>();
        //keep the types and their id in typeTable Hashtable.
        int id =0;
        for(String s: types){
            typeTable.put(s, id);
            title.add(s);
            id++;
        }

        int tokenNum = text.length;
        String token1 = "";
        String token2 = "";
        int i =0;

        while(i< tokenNum) {
            if (tokenNum > 0) {
                token1 = text[i];
                i++;
            }
            while (i < tokenNum) {
                token2 = text[i];
                if (token2.length() == 0) {
                    i++;
                    break;
                }
                int x = typeTable.get(token1);
                int y = typeTable.get(token2);
                counts[x][y] += 1;
                //write pair into count table
                countsTable.put(token1+" "+token2, counts[x][y]);


                token1 = token2;
                i++;


            }
        }

    }


    public void outputPair(Hashtable<String ,Integer> countsTable){

        try{
            PrintWriter writer = new PrintWriter("corpus-counts.txt", "UTF-8");
            for(String key: countsTable.keySet()){
                writer.print(key+": "+countsTable.get(key)+"\n");
            }
        }catch (IOException e){
        }
    }

    public void outputWriteIntext(int[][] counts, ArrayList<String> title){
        try{
            PrintWriter writer = new PrintWriter("corpus-counts-table.txt", "UTF-8");
            //print counts
            writer.format("%15s", " " );
            for (String s : title) {
                writer.format("%15s", s+"" );
            }
            writer.println();
            for (int p = 0; p < title.size(); p++) {
                writer.format("%15s", title.get(p) + "");
                for (int q = 0; q < title.size(); q++) {
                    writer.format("%15s", counts[p][q] + "");
                }
                writer.println();
            }
        } catch (IOException e) {
            // do something
        }
    }

    public void print ( int[][] counts, ArrayList<String> title){
        //print counts
        System.out.format("%15s", " " );
        for (String s : title) {
            System.out.format("%15s", s+"" );
        }
        System.out.println();
        for (int p = 0; p < title.size(); p++) {
            System.out.format("%15s", title.get(p) + "");
            for (int q = 0; q < title.size(); q++) {
                System.out.format("%15s", counts[p][q] + "");
            }
            System.out.println();
        }
    }


    public void print ( float[][] prob, ArrayList<String> title){
        //print counts
        System.out.format("%15s", " " );
        for (String s : title) {
            System.out.format("%15s", s+"" );
        }
        System.out.println();
        for (int p = 0; p < title.size(); p++) {
            System.out.format("%15s", title.get(p) + "");
            for (int q = 0; q < title.size(); q++) {
                System.out.format("%15s", prob[p][q] + "");
            }
            System.out.println();
        }
    }



    public void run(){
        readFile();
        types(text);
        slideTwo(text);
        outputPair(countsTable);
//        NoSmoothing noSmoothing = new NoSmoothing();
//        noSmoothing.noSmoothing(counts,title,typesCount);
//        LaplaceSmoothing laplaceSmoothing = new LaplaceSmoothing();
//        laplaceSmoothing.laplaceSmoothing(counts,title,typesCount);
//        GoodTuring goodTuring = new GoodTuring();
//        goodTuring.goodTuring(counts, title, typesCount);
    }


    public static void main(String[] args){
        Bigram bigram = new Bigram();
        bigram.run();
    }
}
