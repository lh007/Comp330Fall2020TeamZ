package cycle1;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * Person class
 *
 * @version 3
 *
 * Storge place for all attributes and relationships for an individual, Person object
 *
 */

public class Person {

    private String indLabel; // individual label ex. P1
    public String givenName;
    public String familyName;
    public String suffix;
    private String dob;
    private String dod;
    private int age;
    private String birthPlace;
    private String deathPlace;
    private String famLabel; // realtionship label ex. R1
    private String spouse;
    public ArrayList<String> marriageDetails;
    public ArrayList<Person> siblings;
    public ArrayList<Person> children;
    public ArrayList<Person> parents;

    public Person(String indLabel, String familyName, String givenName, String suffix, String dob, String birthPlace, String dod, String deathPlace, String famLabel) {
        this.indLabel = indLabel;
        this.givenName = givenName;
        this.familyName = familyName;
        this.suffix = suffix;
        this.dob = dob;
        this.dod = dod;
        this.birthPlace = birthPlace;
        this.deathPlace = deathPlace;
        this.famLabel = famLabel;
        spouse = "";
        marriageDetails = new ArrayList<String>();
        siblings = new ArrayList<Person>();
        children = new ArrayList<Person>();
        parents = new ArrayList<Person>();
        age = calculateAge(dob);
        age = calculateDeathAge(dob, dod);
    }

    // converts a date given in the form of a String to a LocalDate
    // ref: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    //      https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    public LocalDate formatDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M/dd/yyyy");
        return LocalDate.parse(date, format);
    }
    // calculates the age of someone given a String dob
    // TODO: add dod, expand methods
    public int calculateAge(String dob) {
        LocalDate currDate = LocalDate.now();
        return Period.between(formatDate(dob), currDate).getYears();
    }
    // calculates how old someone was when they died given a String dob and a String dod
    // if the method returns -1, the person is still alive
    public int calculateDeathAge(String dob, String dod) {
        if (dod.equals("")) { return -1; }
        else { return Period.between(formatDate(dob), formatDate(dod)).getYears(); }
    }


    public void relateParents(Person parent1, Person parent2) {
        this.parents.add(Arrays.asList(parent1, parent2));
        parent1.children.add(child);
        parent2.children.add(child);
    }

    public void relateSiblings(Person sibling) {
        this.siblings.add(sibling);
        sibling.siblings.add(this);
    }

    public void relateSpouse(Person spouse2) {
        if (this.getSpouse().equals("") && spouse2.getSpouse().equals("")) {
            this.spouse = spouse2.getGivenName();
            spouse2.spouse = this.getGivenName();
        }
    }

    //getters
    public String getSpouse() { return spouse; }

    public String getFamilyName() { return familyName; }

    public String getGivenName() { return givenName; }

    public String getSuffix() { return suffix; }

    public String getDod() { return dod; }

    public String getDob() { return dob; }

    public int getAge() { return age; }

    public String getBirthPlace() { return birthPlace; }

    public String getDeathPlace() { return deathPlace; }

    public String getIndLabel() { return indLabel; }

    public String getFamLabel() { return famLabel; }

    public ArrayList<Person> getChildren() { return children; }

    public ArrayList<Person> getParents() { return parents; }

    public ArrayList<Person> getSiblings() { return siblings; }

    public ArrayList<String> getMarriageDetails() { return marriageDetails; }
}