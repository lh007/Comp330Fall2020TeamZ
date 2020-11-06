package ReleasedCode;

import java.util.HashMap;
import java.util.*;

public class GenealogyApp {
    public static void main(String[] args) {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed.
        Scanner keyboard = new Scanner(System.in);
        GeneDataBase gdb = new GeneDataBase();
        HashMap<String, Person> map = gdb.exportData();
        OutputFile op = new OutputFile(map);
        AddPersonGUI ap = new AddPersonGUI();
        int currentID = 32;
        Person p = null;

        try {
            gdb.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Would you like to add a person?");
        String response = keyboard.next();
        //statements for "Edit", "Search", "Add", "Exit"
        if (response.equals("yes")) {
                ap.GUI(gdb.getMales(), gdb.getFemales(), gdb.getAllPeople(), currentID);
                p = ap.newPerson;
                gdb.geneMap.put(p.getID(), p);
                currentID++;
        }
            System.out.println("Person successfully added to database!");
            System.out.println("Are you done?");
            String exit = keyboard.next();
            if (exit.equals("yes")) {
                try {
                    op.writeResults();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }