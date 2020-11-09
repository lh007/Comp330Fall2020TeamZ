package main.java;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private String parentID;
    private String spouse;
    private String[] marriageDetails;
    public ArrayList<String> children;
    public ArrayList<String> parents;

    // constructor. reads in the data given for each element (line) in a String arrray and separates and 
    // uses this information to create a new Person in the database.
    public Person(String [] personalData){
        String d;
        for (int i=0; i<personalData.length; i++) {
            d = personalData[i];
            if(d.equals(" ")){
                personalData[i] = "N/a";
            }
        }
        ID = personalData[0];
        familyName = personalData[1];
        givenName = personalData[2];
        suffix = personalData[3];
        dob = personalData[4];
        birthPlace = personalData[5];
        dod = personalData[6];
        deathPlace = personalData[7];
        parentID = personalData[8];
        spouse = "N/a";
        children = new ArrayList<>();
        parents = new ArrayList<>();
        if(!dob.equals("N/a")) {
            if (dod.equals("N/a")) {
                age = calculateAge(dob);
            } else {
                age = calculateDeathAge(dob, dod);
            }
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
    // TODO: possibly stop counting age if someone is dead?
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
        return this.toString() + ", ID: " + this.ID + ", DOB: " + this.dob + ", Birthplace: " + this.birthPlace + ", DOD: " + this.dod + ", Death place: " + this.deathPlace + ", Parents: " + this.parents.toString() + ", Spouse: " + this.spouse + ", Children: " + this.children.toString();
    }

    //setters add the rest
    public void setMarriageDetails(String [] marriageDetails) {
        this.marriageDetails = marriageDetails;
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

    public String getID() { return ID; }

    public String getParentID() { return parentID; }

    public ArrayList<String> getChildren() { return children; }

    public ArrayList<String> getParents() { return parents; }

    public String[] getMarriageDetails() { return marriageDetails; }
}