import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Jonelezhang on 2/4/17.
 */


public class ReadFileTest {

    @Test
    public void readFile() throws Exception {
        ReadFile read = new ReadFile();
        String corpus = "Corpus.txt";
        read.readFile(corpus);
        assertTrue(true);
    }

    @Test
    public void types() throws Exception {
        ReadFile read = new ReadFile();

    }

    @Test
    public void createTable() throws Exception {

    }

    @Test
    public void slideTwo() throws Exception {

    }

}