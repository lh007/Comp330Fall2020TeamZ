package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GenealogyApp {
    public static void main(String[] args) {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed.
        Scanner keyboard = new Scanner(System.in);
        GeneDataBase gdb = new GeneDataBase();
        HashMap<String, Person> map = gdb.exportData();
        OutputFile op = new OutputFile(map);
        AddPersonGUI ap = new AddPersonGUI();
        AddMarriageGUI am = new AddMarriageGUI();
        ArrayList<String> emptyParents = new ArrayList<>();
        int currentID = 32;
        int label = 10;
        Person lastAdded = null;
        boolean done = false;

        try {
            gdb.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        do {
        System.out.println("What would you like to do? Type a number" + "\n" + "1. Add a person" + "\n"+ "2. Add a marriage" + "\n" + "3. Search in tree" + "\n" + "4. Exit app");
        String response = keyboard.next();
        //statements for "Search", "Add", "Exit"; "edit" to come in cycle 3, will also change to switch statements

            if (response.equals("1")) {
                ap.GUI(gdb.getMales(), gdb.getFemales(), gdb.getAllPeople(), currentID);
                lastAdded = ap.newPerson;
                gdb.geneMap.put(lastAdded.getID(), lastAdded);
                currentID++;
            } else if (response.equals("2")){
                //make more general version of createNewMarriage
                if(lastAdded!=null) {
                    am.GUI(gdb.getFemales(), gdb.getMales(), label);
                    String[] details = am.nm;
                    gdb.createNewMarriage(lastAdded.getParents(), lastAdded.getID(), details);
                    label++;
                }
                else{
                    am.GUI(gdb.getFemales(), gdb.getMales(), label);
                    String[] details = am.nm;
                    gdb.createNewMarriage(am.married, "", details);
                    label++;
                }
            } else if (response.equals("3")) {
                //for cycle 3, will be sent to SearchGUI instead of console
                System.out.println("What are you looking for?" + "\n" + "1. All data about one person" + "\n" + "2. Siblings of a person" + "\n" + "3. Parents of a person" + "\n" + "4. Grandparents of a person");
                String option = keyboard.next();
                System.out.println("Enter person ID number, formatted as 'P#'");
                String idnum = keyboard.next();
                if (option.equals("1")) {
                    System.out.println(gdb.geneMap.get(idnum).rawData());
                } else if (option.equals("2")) {
                    Object[] sibs = gdb.getSiblings(idnum).toArray();
                    System.out.println("This persons siblings are: ");
                    for (Object o : sibs) {
                        System.out.println(o.toString());
                    }
                } else if (option.equals("3")) {
                    System.out.println("This persons parents are: ");
                    System.out.println(gdb.getParents(idnum).toString());
                } else if (option.equals("4")) {
                    System.out.println("This persons grandparents are: ");
                    System.out.println(gdb.getGrandparents(idnum).toString());
                }
            } else if (response.equals("4")) {
                try {
                    op.writeResults();
                    done = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid selection, please retype");
            }
        } while(!done);
        
        // close keyboard after done using it
        keyboard.close();
    }

}