//package src;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.util.List;

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


    public final HashMap<String, Person> geneMap = new HashMap<>();
    private final String fileName = "FamilyTreeInputTextFileV2.txt";

    public GeneDataBase() {

    }

    // read-in method for FamilyTreeInputTextFileV2.txt. splits the lines given
    // in the file into either a new instance of a Person, a partnership, or parents.
    // when the end of the file is reached, "file successfully read in" is printed to the
    // console.
    void plantTree() throws Exception {
        String input;
        String[] inputArray;
        String[] splitLine;
        String line;
        input = new String(readAllBytes(Paths.get(fileName)));
        inputArray = input.split("\n");
        boolean done = false;
        do {
            for (int i = 1; i <= inputArray.length-1; i++) {
                line = inputArray[i];
                splitLine = line.split(",");
                if (i >= 1 && i <= 22) {
                    Person p = new Person(splitLine);
                    geneMap.put(p.getID(), p);
                    System.out.println(geneMap.get(p.getID()).toString() + " has been added");
                } else if (i >= 26 && i <= 33) {
                    createPartnership(splitLine);
                } else if (i >= 38 && i <= 57) {
                    createParents(splitLine);
                }
            }
            System.out.println("File successfully read in");
            done = true;
        } while (!done);
    }

    // creates a new partnership. a String array is passed in and iterated through.
    // for each element in the array, if it is not blank, either the geneMap retrieves
    // names of the members of the partnership and adds them into a new partnership or
    // sets marriage details depending on the index of the String array that is being
    // read.
    private void createPartnership(String[] data) {
        for (int i = 0; i < data.length; i++) {
            String d = data[i];
            if (d.equals(" ")) {
                data[i] = "N/a";
            }
        }
        if (data[1].equals("N/a") && data[2].equals("N/a")) {
            System.out.println("There are no persons known in this partnership");
        } else if (!data[1].equals("N/a") && !data[2].equals("N/a")) {
            Person spouse1 = geneMap.get(data[1]);
            Person spouse2 = geneMap.get(data[2]);
            spouse1.newPartnership(data, spouse2);
            System.out.println(spouse1.toString() + " is married to " + spouse2.toString());
        } else if (data[1].equals("N/a") && !data[2].equals("N/a")) {
            Person spouse1 = geneMap.get(data[2]);
            spouse1.setMarriageDetails(data);
            System.out.println(spouse1.toString() + " has marriage details");
        } else {
            Person spouse1 = geneMap.get(data[1]);
            spouse1.setMarriageDetails(data);
            System.out.println(spouse1.toString() + " has marriage details");
        }
    }

    // given a String array, parents are set for a particular child if applicable. Children are also set
    // for parents. 
    private void createParents(String[] data) {
        String marriage = data[0];
        Person child = geneMap.get(data[1]);
        for (Person ind : geneMap.values()) {
            String[] dets = ind.getMarriageDetails();
            if (dets != null) {
                if (dets[0].equals(marriage)) {
                    ind.children.add(child.getID());
                    child.parents.add(ind.getID());
                    System.out.println(ind.getID() + " is parent to " + child.getID());
                }
            }
        }
    }

    public HashMap<String, Person> exportData(){
        return geneMap;
    }

    // basic search method for GUI. from then on we can call Person methods on result
    public Person findPerson(String id) {
        return geneMap.get(id);
    }

    /* add get family member methods here (bio, no in laws) */

    // returns IDs of all grandparents
    public ArrayList<String> getGrandparents(String id) {
        if (geneMap.get(id) == null) { throw new NullPointerException("Error: this person cannot be found in the database."); };
        ArrayList<String> parents = geneMap.get(id).getParents();
        ArrayList<String> grandparents = new ArrayList<String>();
        for (String parent : parents) {
            for (String grandparent : geneMap.get(parent).getParents()) {
                if (!grandparents.contains(grandparent)) { grandparents.add(grandparent); }
            }
        } 
        if (grandparents.isEmpty()) { 
            System.out.println("No grandparents found in the database " + "for person " + id + ".");
        }
        return grandparents;
    }

    public ArrayList<String> getSiblings() {
        // TODO
        // kind of like grandparents, but instead of finding their parents we find their children and
        // skip over current person's ID in resulting list
    }

    public ArrayList<String> getCousins() {
        // TODO
    }

    /* extras, if we have time */
    public ArrayList<String> getGrandchildren() {
        // TODO
    }

    public ArrayList<String> getAunts() {
        // TODO
    }

    public ArrayList<String> getUncles() {
        // TODO
    }

    public ArrayList<String> getNieces() {
        // TODO
    }

    public ArrayList<String> getNephews() {
        // TODO
    }

}