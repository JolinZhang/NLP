
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Jonelezhang on 1/29/17.
 */
public class ReadFile {
    public ArrayList<String> title;
    public int[][] counts;
    public Hashtable<String, Integer> countsTable;

    //read file keep all word
    public String[] readFile(String route){
        BufferedReader file = null;
        //read file
        String path = ReadFile.class.getClassLoader().getResource(route).getPath();
        try{
            file = new BufferedReader(new FileReader(path));
        }catch (Exception e){
            System.out.print("read file failed");
        }

        //keep all word in content, do not include any other characters.
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
        String[] text = String.valueOf(content).split("\\s+");
//                for(String s: text){
//            System.out.println(s);
//        }
        return text;
    }


    //get types all distinct word and the counts for all distinct word
    public HashMap<String, Integer> types(String[] text){
        HashMap<String, Integer> typesCount = new HashMap<String, Integer>();
        for(String s: text){
            if(!typesCount.containsKey(s) && s.length()!=0){
                typesCount.put(s,1);
            }else if(typesCount.containsKey(s)){
                typesCount.put(s, typesCount.get(s)+1);
            }
        }
//        for(Map.Entry<String, Integer> entry: typesCount.entrySet()){
//            System.out.print(entry.getKey()+" ");
//            System.out.print(entry.getValue()+" ");
//            System.out.println();
//        }
        return typesCount;
    }



    //get title and counts size info
    public void createTable(HashMap<String, Integer> typesCount){
        Set<String> types = typesCount.keySet();
        int typeNum = types.size();
        //define the counts array.
        counts = new int[typeNum][typeNum];
        //keep title info
        title = new ArrayList<String>();
        //keep the types and their id in typeTable Hashtable.
        int id =0;
        for(String s: types){
            title.add(s);
            id++;
        }
    }



    //get count for pair
    public void slideTwo(String[] text, ArrayList<String> title){
        //keep counts pair info;
        countsTable = new Hashtable<String, Integer>();
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
                int x = title.indexOf(token1);
                int y = title.indexOf(token2);
                counts[x][y] += 1;
                //write pair into count table
                countsTable.put(token1+" "+token2, counts[x][y]);
                token1 = token2;
                i++;
            }
        }

    }

    public void slideTwoTest(String[] text, ArrayList<String> title, Hashtable<String, Integer> countsTable){
        //keep counts pair info;
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
                int x = title.indexOf(token1);
                int y = title.indexOf(token2);
                if(countsTable.containsKey(token1+" "+token2)) {
                    counts[x][y] = countsTable.get(token1 + " " + token2);
                }
                token1 = token2;
                i++;
            }
        }
    }

    public float slidTwoProb(String[] text, float[][] prob, ArrayList<String> title){
        //keep counts pair info;
        float probability = (float) 0.5;
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
                int x = title.indexOf(token1);
                int y = title.indexOf(token2);
                probability *= prob[x][y];
                token1 = token2;
                i++;
            }
        }
        return probability;
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

}
