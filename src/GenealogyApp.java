package src;

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
        boolean b = false;
        Person p = null;

        try {
            gdb.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Would you like to add a person?");
        String response = keyboard.next();
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

        // testing findPerson
        // shows duplicate of children?
        System.out.println("Testing findPerson: " + gdb.findPerson("P1").getChildren());

        //testing finding ID methods
        System.out.println("Testing findExactNameID: " + gdb.findExactNameID("Johnson", "Dick"));
        System.out.println("Testing findExactNameID: " + gdb.findExactNameID("Biden", "Joe"));
        System.out.println("Testing findExactSurnameID: " + gdb.findExactSurnameID("Johnson"));
        System.out.println("Testing findExactSurnameID: " + gdb.findExactSurnameID("Trump"));
        System.out.println("Testing findExactFirstNameID: " + gdb.findExactFirstNameID("John J"));
        System.out.println("Testing findExactFirstNameID: " + gdb.findExactFirstNameID("Donald"));

        // testing getGrandparents
        // System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P1")); // error - P8 is missing
        // System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P2")); // error - P8 is missing
        //System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P3")); // error - P8 is missing
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P6"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P7"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P8"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P9"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P10"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P13"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P15"));
        // System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P16")); // error - P8 missing
        // System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P17")); // error - P8 missing
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P18")); 
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P19"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P20"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P21"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P23"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P25"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P30"));
        System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P31"));
        // System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P26")); // error - should be P1 and P17
        // System.out.println("Testing getGrandparents: " + gdb.getGrandparents("P27")); // error - should be P1 and P17

        System.out.println("Testing getSiblings: " + gdb.getSiblings("P1"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P2"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P3"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P6"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P7"));
        //System.out.println("Testing getSiblings: " + gdb.getSiblings("P8")); // error - bc parents unknown
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P9")); 
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P10"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P13"));
        //System.out.println("Testing getSiblings: " + gdb.getSiblings("P15")); // error - bc parents unknown
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P16"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P17"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P18"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P19"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P20"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P21"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P23"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P25"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P30"));
        System.out.println("Testing getSiblings: " + gdb.getSiblings("P31"));
        //System.out.println("Testing getSiblings: " + gdb.getSiblings("P26")); //error - sibling should be P27
        //System.out.println("Testing getSiblings: " + gdb.getSiblings("P27")); // error - sibling should be P26

        // for each person successfully placed in the database, print out their full name and their ID.
        /*for(Person p : gdb.geneMap.values()){
            System.out.println(p.rawData());
        }*//*


             *//*  // test cases
        System.out.println();
        System.out.println("P19's spouse is: " + gdb.geneMap.get("P19").getSpouse());
        System.out.println("P1's spouse is: " + gdb.geneMap.get("P1").getSpouse());
        System.out.println("P1's parents are: " + gdb.geneMap.get("P1").getParents());
        System.out.println("P17's children are: " + gdb.geneMap.get("P17").getChildren());
        System.out.println("P31's parents are: " + gdb.geneMap.get("P31").getParents());
        System.out.println("P31's place of birth is: " + gdb.geneMap.get("P31").getBirthPlace());
        System.out.println("P31's age is: " + gdb.geneMap.get("P31").getAge());
        System.out.println("P19's age is: " + gdb.geneMap.get("P19").getAge());
        System.out.println("P19's date of death is: " + gdb.geneMap.get("P19").getDod());
        System.out.println("P26's age is: " + gdb.geneMap.get("P26").getAge());
        System.out.println("P26's date of death is: " + gdb.geneMap.get("P26").getDod());
        System.out.println("P26's date of birth is: " + gdb.geneMap.get("P26").getDob());
        System.out.println("P26's name is: " + gdb.geneMap.get("P26").toString());
        System.out.println("P26's name suffix is: " + gdb.geneMap.get("P26").getSuffix());*/
        }
    }