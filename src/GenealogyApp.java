package src;

public class GenealogyApp {
    public static void main(String[] args)
    {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed. 
        GeneDataBase gdb = new GeneDataBase();
        try {
            gdb.plantTree();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // for each person successfully placed in the database, print out their full name and their ID.
        for(Person p : gdb.geneMap.values()){
            System.out.println(p.rawData());
        }

        // test cases
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
        System.out.println("P26's name suffix is: " + gdb.geneMap.get("P26").getSuffix());
    }
}