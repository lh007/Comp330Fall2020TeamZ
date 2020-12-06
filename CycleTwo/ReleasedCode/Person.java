package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Person class
 *
 * @version 3
 *
 * Storge place for all attributes and relationships for an individual, Person object
 *
 */

public class Person {

    private String ID ;
    private String givenName;
    private String familyName;
    private String suffix;
    private String dob;
    private String dod;
    private int age;
    private String birthPlace;
    private String deathPlace;
    private String parentRelationshipID; // returns relationship ID of parents
    private String spouse;
    private String[] marriageDetails; // includes all information about a marriage
    public ArrayList<String> children;
    public ArrayList<String> parents;
    private boolean male;
    private String [] newMarriageDetails; //new marriage created after new people are added to hash map

    // constructor. reads in the data given for each element (line) in a String arrray and separates and 
    // uses this information to create a new Person in the database.
    public Person(String[] personalData){
        String d;
        for (int i = 0; i < personalData.length; i++) {
            d = personalData[i];
            if (d.equals(" ")) { personalData[i] = "N/a"; }
        }
        ID = personalData[0];
        familyName = personalData[1];
        givenName = personalData[2];
        suffix = personalData[3];
        dob = personalData[4];
        birthPlace = personalData[5];
        dod = personalData[6];
        deathPlace = personalData[7];
        parentRelationshipID = personalData[8];
        if (personalData[9].equals("M")) { male = true; }
        else { male = false; }
        spouse = "N/a";
        children = new ArrayList<>();
        parents = new ArrayList<>();
        if (!dob.equals("N/a")) {
            if (dod.equals("N/a")) { age = calculateAge(dob); } 
            else { age = calculateDeathAge(dob, dod); }
        }
    }

    public Person(String[] personalData, int currentID, String MID, String DID){
        System.out.println("New person created");
        String personID = "P" + Integer.toString(currentID);
        ID = personID;
        familyName = personalData[1];
        givenName = personalData[2];
        suffix = personalData[3];
        dob = personalData[4];
        birthPlace = personalData[5];
        dod = personalData[6];
        deathPlace = personalData[7];
        parentRelationshipID = personalData[8];
        if (personalData[9].equals("M")) { male = true; }
        else { male = false; }
        spouse = "N/a";
        children = new ArrayList<>();
        parents = new ArrayList<>();
        newMarriageDetails = new String[6];
        marriageDetails = new String[6];
        parents.add(MID);
        parents.add(DID);
        if (!dob.equals("N/a")) {
            if (dod.equals("N/a")) { age = calculateAge(dob); }
            else { age = calculateDeathAge(dob, dod); }
        }
    }

    // converts a date given in the form of a String to a LocalDate
    // ref: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    //      https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    public LocalDate formatDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M/dd/yyyy");
        return LocalDate.parse(date, format);
    }
    // calculates the age of someone given a String dob
    public int calculateAge(String dob) {
        LocalDate currDate = LocalDate.now();
        if (dob.equals("N/a")) { return -1; }
        else { return Period.between(formatDate(dob), currDate).getYears(); }
    }
    // calculates how old someone was when they died given a String dob and a String dod
    // if the method returns -1, the person is still alive
    public int calculateDeathAge(String dob, String dod) {
        if (dod.equals("N/a")) { return -1; }
        else { return Period.between(formatDate(dob), formatDate(dod)).getYears(); }
    }

    // creates a new partnership for a Person given a String array and a Person (spouse),
    // including their marriage details, their spouse, and their children.
    public void newPartnership(String[] data, Person s){
        this.marriageDetails = data;
        s.marriageDetails = data;
        this.spouse = s.getID();
        s.spouse = this.getID();
    }

    // prints out the full name for a Person in the database.
    public String toString() {
        if (!this.suffix.equals("N/a")) {
            return this.givenName + " " + this.familyName + " " + this.suffix;
        } else {
            return this.givenName + " " + this.familyName;
        }
    }

    // prints out the full name and ID for a Person in the database.
    public String rawData(){
        List<String> newMarDets = null;
        String out = this.toString() + "--";
        out += "ID: " + ID;
        out += ", DOB: " + dob;
        out+= ", Birthplace: " + birthPlace;
        out+= ", Parents: "+ parents.toString();
        out+=  ", DOD: " + dod;
        out+= ", Death place: " + deathPlace;
        out += ", Spouse: " + spouse;
        out+= ", Children: " + children.toString();
        if(newMarriageDetails!=null) {
            newMarDets = Arrays.asList(newMarriageDetails);
            out += ", Other marriage: " + newMarDets.toString();
        }
        return out;
    }

    // sets marriage details, including relationship ID, people in the marriage, location, and date
    public void setMarriageDetails(String[] marriageDetails) { this.marriageDetails = marriageDetails; }

    //public void setID(String id) { this.ID = id; }

    public void setGivenName(String givenName) { this.givenName = givenName; }

    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public void setSuffix(String suffix) { this.suffix = suffix; }

    public void setDob(String dob) { this.dob = dob; }

    public void setDod(String dod) { this.dod = dod; }

   // public void setAge(int age) { this.age = age; }

    public void setBirthPlace(String birthPlace) { this.birthPlace = birthPlace; }

    public void setDeathPlace(String deathPlace) { this.deathPlace = deathPlace; }

   // public void setParentRelationshipID(String parentRelationshipID) { this.parentRelationshipID = parentRelationshipID; }

    public void setSpouse(String spouse) { this.spouse = spouse; }

   // public void setMale(boolean male) { this.male = male; }

   // public void setChildren(ArrayList<String> children) { this.children = children; }

   // public void setParents(ArrayList<String> parents) { this.parents = parents; }

    public void setNewMarriageDetails(String [] details){this.newMarriageDetails = details;}

    public String getSpouse() { return spouse; }

    public String getFamilyName() { return familyName; }

    public String getGivenName() { return givenName; }

    public String getSuffix() { return suffix; }

    public String getDod() { return dod; }

    public String getDob() { return dob; }

    public int getAge() { return age; }

    public String getBirthPlace() { return birthPlace; }

    public String getDeathPlace() { return deathPlace; }

    public String getID() { return ID; }

    public String getParentRelationshipID() { return parentRelationshipID; }

    public ArrayList<String> getChildren() { return children; }

    public ArrayList<String> getParents() { return parents; }

    public String[] getMarriageDetails() { return marriageDetails; }

    public boolean isMale() { return male; }

    public String [] getNewMarriageDetails(){return newMarriageDetails;}
}