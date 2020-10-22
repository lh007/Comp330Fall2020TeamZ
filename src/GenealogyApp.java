package src;

public class GenealogyApp {
    public static void main(String[] args)
    {
        GeneDataBase gdb = new GeneDataBase();
        try {
            gdb.plantTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Person p : gdb.geneMap.values()){
            System.out.println(p.rawData());
        }

       /* Person kendall = new Person("P1", "Phillips", "Kendall", "", "11/18/1998", "Rockwall", "", "", "");

        // formatDate, calculateAge and calculateDeathAge test
        // (temporary, will add to test class)
        System.out.println(kendall.formatDate("11/18/1998"));
        System.out.println(kendall.calculateAge("11/18/1998"));
        System.out.println(kendall.calculateDeathAge("11/18/1900", "9/30/2020"));*/
    }
}