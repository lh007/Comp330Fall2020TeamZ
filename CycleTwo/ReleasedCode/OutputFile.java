package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private HashMap<String, Person> gm;


    public OutputFile(HashMap<String, Person> data){
        gm = data;
    }




    public void writeResults() throws IOException {
        File output = File.createTempFile("TreeResults", null);
        FileWriter fw = new FileWriter("TreeResults.tmp");
        BufferedWriter bf = new BufferedWriter(fw);
        try {
            for (Person p : gm.values()) {
                System.out.println(p.rawData());
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
