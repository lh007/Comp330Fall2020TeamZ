//package src;

import java.util.HashMap;

public class GenealogyApp {
    public static void main(String[] args)
    {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed. 
        GeneDataBase gdb = new GeneDataBase();
        HashMap<String, Person> map = gdb.exportData();
        OutputFile op = new OutputFile(map);
        try {
            gdb.plantTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            op.writeResults();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // testing getGrandparents
        System.out.println(gdb.getGrandparents("P1"));
        System.out.println(gdb.getGrandparents("P2"));
        System.out.println(gdb.getGrandparents("P3"));
        System.out.println(gdb.getGrandparents("P6"));
        System.out.println(gdb.getGrandparents("P7"));
        System.out.println(gdb.getGrandparents("P8"));
        System.out.println(gdb.getGrandparents("P9"));
        System.out.println(gdb.getGrandparents("P10"));
        System.out.println(gdb.getGrandparents("P13"));
        System.out.println(gdb.getGrandparents("P15"));

        // for each person successfully placed in the database, print out their full name and their ID.
        /*for(Person p : gdb.geneMap.values()){
            System.out.println(p.rawData());
        }*/


      /*  // test cases
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