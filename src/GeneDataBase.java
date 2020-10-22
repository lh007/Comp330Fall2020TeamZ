package src;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readAllBytes;

/**
 * GeneDataBase class
 *
 * @version 3
 *
 *
 * reads in file, creates hash map to store and relate each Person
 */
public class GeneDataBase {


    private final HashMap<String, Person> geneMap = new HashMap<>();

    public GeneDataBase() {

    }

    void plantTree() throws Exception {
        String input;
        String [] inputArray;
        String[] splitLine;
        String line;
        String fileName = "FamilyTreeInputTextFileV2.txt";
        input = new String(readAllBytes(Paths.get(fileName)));
        inputArray = input.split("\n");
        boolean done;
        do {
            for (int i = 1; i <= inputArray.length; i++) {
                line = inputArray[i];
                splitLine = line.split(",");
                System.out.println(i);
                if (i >= 1 && i <= 22) {
                    Person p = new Person(splitLine);
                    geneMap.put(p.getID(), p);
                    System.out.println(geneMap.get(p.getID()));
                } else if (i >= 26 && i <= 33) {
                    createPartnership(splitLine);
                } else if (i >= 38 && i <= 57) {
                    createParents(splitLine);
                }
            }
            System.out.println("File successfully read in");
            done = false;
        } while (done);
    }


    private void createPartnership(String[] data) {
        for (int i=0; i<data.length; i++) {
            String d = data[i];
            if(d.equals(" ")){
                data[i] = "N/a";
            }
        }
        if(data[1].equals("N/a") && data[2].equals("N/a")){
            System.out.println("There are no persons known in this partnership");
        }
        else if(!data[1].equals("N/a") && !data[2].equals("N/a")){
            Person spouse1 = geneMap.get(data[1]);
            Person spouse2 = geneMap.get(data[2]);
            spouse1.newPartnership(data, spouse2);
            System.out.println(spouse1.toString() + " is married to " + spouse2.toString());
        }
        else if(data[1].equals("N/a") && !data[2].equals("N/a")){
            Person spouse1 = geneMap.get(data[2]);
            spouse1.setMarriageDetails(data);
            System.out.println(spouse1.toString() + " has marriage details");
        }
        else {
            Person spouse1 = geneMap.get(data[1]);
            spouse1.setMarriageDetails(data);
            System.out.println(spouse1.toString() + " has marriage details");
        }
    }

    private void createParents(String[] data){
        String partnership = data[0];
        Person child = geneMap.get(data[1]);
        String[] parentIDs = new String[2];
        if (partnership.equals("R5")){
            parentIDs[0] = "N/a";
            parentIDs[1] = "N/a";
            child.setParents(parentIDs);
            System.out.println(child.toString() + " has unknown parents");
        }else {
            String [] kids;
            String[] info;
            for (Person p : geneMap.values()) {
                info = p.getMarriageDetails();
                if (info[0].equals(partnership)) {
                    if(parentIDs[0].isEmpty()){
                        parentIDs[0] = p.getID();
                        kids =  p.getChildren();
                        int num = kids.length -1;
                        if (num==-1){
                            kids[0] = child.getID();
                        }else {
                            kids[num+1] = child.getID();
                        }
                    }
                    else{
                        parentIDs[1] = p.getID();
                        kids =  p.getChildren();
                        int num = kids.length -1;
                        if (num==-1){
                            kids[0] = child.getID();
                        }else {
                            kids[num+1] = child.getID();
                        }
                    }
                }
            }
            System.out.println(child.toString() + " has parents");
        }
        child.setParents(parentIDs);
    }
}