package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import static java.nio.file.Files.readAllBytes;

/**
 * GeneDataBase class
 *
 * @version 3
 *
 * reads in file, creates hash map to store and relate each Person
 */
public class GeneDataBase {


    public final HashMap<String, Person> geneMap = new HashMap<>();
    private final String fileName = "FamilyTreeInputTextFileV2.txt";
    ArrayList<String> males = new ArrayList<String>();
    ArrayList<String> females = new ArrayList<String>();
    ArrayList<String> allPeople = new ArrayList<String>();

    public GeneDataBase() {

    }

    // read-in method for FamilyTreeInputTextFileV2.txt. splits the lines given
    // in the file into either a new instance of a Person, a partnership, or parents.
    // when the end of the file is reached, "file successfully read in" is printed to the
    // console.
    void plantTree() throws Exception {
        String input;
        String[] inputArray;
        String[] splitLine;
        String line;

        input = new String(readAllBytes(Paths.get(fileName)));
        inputArray = input.split("\n");
        boolean done = false;
        do {
            for (int i = 1; i <= inputArray.length-1; i++) {
                line = inputArray[i];
                splitLine = line.split(",");
                if (i >= 1 && i <= 22) {
                    Person p = new Person(splitLine);
                    geneMap.put(p.getID(), p);
                    System.out.println(geneMap.get(p.getID()).toString() + " has been added");
                } else if (i >= 26 && i <= 34) {
                    createPartnership(splitLine);
                } else if (i >= 38 && i <= 57) {
                    createParents(splitLine);
                }
            }
            System.out.println("File successfully read in");
            done = true;
        } while (!done);
    }

    // creates a new partnership. a String array is passed in and iterated through.
    // for each element in the array, if it is not blank, either the geneMap retrieves
    // names of the members of the partnership and adds them into a new partnership or
    // sets marriage details depending on the index of the String array that is being
    // read.
    private void createPartnership(String[] data) {
        for (int i = 0; i < data.length; i++) {
            String d = data[i];
            if (d.equals(" ")) { data[i] = "N/a"; }
        }
        if (data[1].equals("N/a") && data[2].equals("N/a")) {
            System.out.println("There are no persons known in this partnership");
        } else if (!data[1].equals("N/a") && !data[2].equals("N/a")) {
            Person spouse1 = geneMap.get(data[1]);
            Person spouse2 = geneMap.get(data[2]);
            spouse1.newPartnership(data, spouse2);
            System.out.println(spouse1.toString() + " is married to " + spouse2.toString());
        } else if (data[1].equals("N/a") && !data[2].equals("N/a")) {
            Person spouse1 = geneMap.get(data[2]);
            spouse1.setMarriageDetails(data);
            System.out.println(spouse1.toString() + " has marriage details");
        } else {
            Person spouse1 = geneMap.get(data[1]);
            spouse1.setMarriageDetails(data);
            System.out.println(spouse1.toString() + " has marriage details");
        }
    }

    // given a String array, parents are set for a particular child if applicable. Children are also set
    // for parents. 
    private void createParents(String[] data) {
        String marriage = data[0];
        Person child = geneMap.get(data[1]);
        for (Person ind : geneMap.values()) {
            String[] dets = ind.getMarriageDetails();
            if (dets != null) {
                if (dets[0].equals(marriage)) {
                    // check if the child is already registered to this parent
                    if (!ind.getChildren().contains(child.getID())) {
                        ind.children.add(child.getID());
                        child.parents.add(ind.getID());
                        System.out.println(ind.getID() + " is parent to " + child.getID());
                    }
                }
            }
        }
    }
    //cycle 3 addition to construct new relationships

    public void createNewMarriage(ArrayList<String> partners,String [] details){
        Person mother = geneMap.get(partners.get(0));
        Person father = geneMap.get(partners.get(1));
        //neither married and no child
        if(mother.getMarriageDetails() == null && father.getMarriageDetails() == null){
            mother.setMarriageDetails(details);
            father.setMarriageDetails(details);
            mother.setSpouse(father.getID());
            father.setSpouse(mother.getID());
        }
        //hus married, not wife, no child
        else if (mother.getMarriageDetails() == null && father.getMarriageDetails()!=null){
            father.setNewMarriageDetails(details);
            mother.setMarriageDetails(details);
            mother.setSpouse(father.getID());
        }
        //wife married, not hus, no child
        else if (mother.getMarriageDetails() != null && father.getMarriageDetails()==null){
            mother.setNewMarriageDetails(details);
            father.setMarriageDetails(details);
            father.setSpouse(mother.getID());
        }
        //both married, no child
        else if (mother.getMarriageDetails() !=null && father.getMarriageDetails()!= null){
            mother.setNewMarriageDetails(details);
            father.setNewMarriageDetails(details);
        }
        else{
            System.out.println("Error in marriage");
        }
    }

    public HashMap<String, Person> exportData(){
        return geneMap;
    }

    public ArrayList<String> getMales(){
        for(Person p : geneMap.values()) {
            if (p.isMale() && !males.contains(p.getID()+": " + p.toString())) {
                males.add(p.getID() + ": " + p.toString());
            }
        }
        return males;
    }

    public ArrayList<String> getFemales(){
        for(Person p : geneMap.values()){
            if(!p.isMale() && !females.contains(p.getID()+": " + p.toString())){
                females.add(p.getID() + ": " + p.toString());
            }
        }
        return females;
    }

    public ArrayList<String> getAllPeople(){
        for(Person p : geneMap.values()){
            if(!allPeople.contains(p.getID() +": " + p.toString()))
            allPeople.add(p.getID() + ": " + p.toString());
        }
        return allPeople;
    }

    // basic search method for GUI. from then on we can call Person methods on result
    public Person findPerson(String id) {
        if (geneMap.get(id) == null) {
            return null;
        }
        else {
            return geneMap.get(id);
        }
    }

    public ArrayList<Person> getAllPersons(){
        return (ArrayList<Person>) geneMap.values();
    }
    // search method for finding any ID that has the exact first and last name
    public ArrayList<String> findExactNameID(String familyName, String givenName) {
        ArrayList<String> result = new ArrayList<String>();
        for (String id : geneMap.keySet()) {
            Person current = geneMap.get(id);
            if (current.getFamilyName().equals(familyName)) {
                if (current.getGivenName().equals(givenName)) {
                    result.add(id);
                }
            }
        }
        if (result.isEmpty()) { System.out.println("This name was not found in the database."); };
        return result;
    }

    // search method for finding any ID that has the exact family name
    public ArrayList<String> findExactSurnameID(String familyName) {
        ArrayList<String> result = new ArrayList<String>();
        for (String id : geneMap.keySet()) {
            Person current = geneMap.get(id);
            if (current.getFamilyName().equals(familyName)) {
                result.add(id);
            }
        }
        if (result.isEmpty()) { System.out.println("This name was not found in the database."); };
        return result;
    }

    // search method for finding any ID that has the exact first name
    public ArrayList<String> findExactFirstNameID(String givenName) {
        ArrayList<String> result = new ArrayList<String>();
        for (String id : geneMap.keySet()) {
            Person current = geneMap.get(id);
            if (current.getGivenName().equals(givenName)) {
                result.add(id);
            }
        }
        if (result.isEmpty()) { System.out.println("This name was not found in the database."); };
        return result;
    }

    /* add get family member methods here (bio, no in laws) */

    // returns IDs of all children
    public ArrayList<String> getChildren(String id) {
        if (geneMap.get(id) == null) {
            System.out.println("This person was not found in the database.");
            return null;
        }
        ArrayList<String> children = geneMap.get(id).getChildren();
        if (children.isEmpty()) {
            System.out.println("No parents found in the database " + "for person " + id + ".");
        }
        return children;
    }
    // returns IDs of all parents
    public ArrayList<String> getParents(String id) {
        if (geneMap.get(id) == null) {
            System.out.println("This person was not found in the database.");
            return null;
        }
        ArrayList<String> parents = geneMap.get(id).getParents();
        if (parents.isEmpty()) {
            System.out.println("No parents found in the database " + "for person " + id + ".");
        }
        return parents;
    }

    // returns IDs of all grandparents
    public ArrayList<String> getGrandparents(String id) {
        if (geneMap.get(id) == null) {
            System.out.println("This person was not found in the database.");
            return null;
        }
        ArrayList<String> parents = geneMap.get(id).getParents();
        ArrayList<String> grandparents = new ArrayList<String>();
        for (String parent : parents) {
            for (String grandparent : geneMap.get(parent).getParents()) {
                if (!grandparents.contains(grandparent)) { grandparents.add(grandparent); }
            }
        }
        if (grandparents.isEmpty()) {
            System.out.println("No grandparents found in the database " + "for person " + id + ".");
        }
        return grandparents;
    }

    public ArrayList<String> getSiblings(String id) {
        // kind of like grandparents, but instead of finding their parents we find their children and
        // skip over current person's ID in resulting list
        if (geneMap.get(id) == null) {
            System.out.println("This person was not found in the database.");
            return null;
        }
        ArrayList<String> parents = geneMap.get(id).getParents();
        ArrayList<String> siblings = new ArrayList<String>();
        for (String parent : parents) {
            for (String sibling : geneMap.get(parent).getChildren()) {
                if (!siblings.contains(sibling) && !sibling.equals(id)) { siblings.add(sibling); }
            }
        }
        if (siblings.isEmpty()) {
            System.out.println("No siblings found in the database " + "for person " + id + ".");
        }
        return siblings;
    }

    public void editEntry(Object person, int option, String update){
        String [] input = person.toString().split(":");
        Person p = geneMap.get(input[0]);
        switch(option){
            case 0: //given name
                p.setGivenName(update);
                break;
            case 1: //family name
                p.setFamilyName(update);
                break;
            case 2: //suffix
                p.setSuffix(update);
                break;
            case 3: //birthplace
                p.setBirthPlace(update);
                break;
            case 4: //dob
                p.setDob(update);
                break;
            case 5: //death place
                p.setDeathPlace(update);
                break;
            case 6: //dod
                p.setDod(update);
                break;
        }
    }

}