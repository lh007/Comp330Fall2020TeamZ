package src.main;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * GeneDataBase class
 *
 * @version 3
 *
 *
 * reads in file, creates hash map to store and relate each Person
 */
public class GeneDataBase {

    private HashMap<String, Person> geneMap;
    String input;
    String line;
    String[] inputArray;
    String[] personArray;
    String[] partnershipArray;
    String indLabel;
    String familyName;       
    String givenName;
    String suffix;
    String dob;
    String birthPlace;
    String dod;
    String deathPlace;
    String famLabel;
    String partnership;
    String wife;
    String husband;
    String marriageDate;
    String divorceDate;
    String mLocation;
    
        private GeneDataBase(String fileName) throws Exception {
        input = new String(Files.readAllBytes(Paths.get(fileName)));
        inputArray = input.split("\n");
        do {
            for (int i = 0; i <= inputArray.length; i++) {
                line = inputArray[i];
                if (line.contains("Johnson" || "Smith" || "Lopez" || "Jones")) { //portion for file that is Persons
                    personArray = line.split(","); //splits array i line by comma to get individaul pieces of info out
                    indLabel = personArray[0];
                    familyName = personArray[1];
                    givenName = personArray[2];
                    suffix = personArray[3];
                    dob = personArray[4];
                    birthPlace = personArray[5];
                    dod = personArray[6];
                    deathPlace = personArray[7];
                    famLabel = personArray[8];
                    Person newPerson = new Person(indLabel, familyName, givenName, suffix, dob, birthPlace, dod, deathPlace, famLabel); //creating new person
                    geneMap.put(newPerson.getIndLabel(), newPerson); //key is indLabel, value is Person
                } else if (line.startsWith("R")) {//portion of input file that describes partnerships
                    partnershipArray = line.split(",");
                    partnership = partnershipArray[0];
                    wife = partnershipArray[1];
                    husband = partnershipArray[2];
                    marriageDate = partnershipArray[3];
                    divorceDate = partnershipArray[4];
                    mLocation = partnershipArray[5];
                    if (!wife.equals("") && !husband.equals("")) {
                        createSpouse(partnership, wife, husband, marriageDate, divorceDate, mLocation);
                    }
                }
            }
        }
        while (!line.contains("Children")); //stop reading in lines when Children appears, don't use them for read in
    }

    public void createSpouse(String partnership, String wife, String husband, String marriageDate, String divorceDate, String mLocation) {
        String compare;
        Person p1 = geneMap.get(wife);
        Person p2 = geneMap.get(husband);
        p1.relateSpouse(p2);
        p1.marriageDetails.add(partnership, wife, husband, "Married: " + marriageDate, "Divorced: " + divorceDate, mLocation);
        p2.marriageDetails.add(partnership, wife, husband, "Married: " + marriageDate, "Divorced: " + divorceDate, mLocation);
        for (Person p : geneMap.values()) {
            compare = p.getFamLabel();
            if (compare.equals(partnership)) {
                p.relateParents(p1, p2);
            }
        }
    }

    public void createSiblings() {
        ArrayList<Person> s1, s2;
        for (Person p1 : geneMap.values()) {
            s1 = p1.getParents();
            for (Person p2 : geneMap.values()) {
                s2 = p2.getParents();
                if (s1.equals(s2)) {
                    p1.relateSiblings(p2);
                }
            }
        }
    }

}