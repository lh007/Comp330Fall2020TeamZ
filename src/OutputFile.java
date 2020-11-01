package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * OutputFile class
 *
 * @version 1
 *
 * Generates written report of family tree in temp file when user finishes interaction
 *
 */



public class OutputFile {

    HashMap<String, Person> gm;


    public OutputFile(HashMap<String, Person> data){
        gm = data;
    }

    public void writeResults() throws IOException {
        File output = File.createTempFile("TreeResults", null);
        BufferedWriter bf = new BufferedWriter(new FileWriter(output));
        try {
            for (Person p : gm.values()) {
                bf.write(p.rawData());
                bf.newLine();
            }
            bf.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            bf.close();
        }

    }
}
