package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GenealogyApp {
    public static void main(String[] args) {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed.
        GeneDataBase gdb = new GeneDataBase(new HashMap<String, Person>(), "FamilyTreeInputTextFileV2.txt",
         new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        HashMap<String, Person> map = gdb.exportData();
        OutputFile op = new OutputFile(map);
        AddPersonGUI ap = new AddPersonGUI();
        AddMarriageGUI am = new AddMarriageGUI();
        MainMenuGUI mm = new MainMenuGUI();
        EditGUI edit = new EditGUI();
        int currentID = 32;
        int label = 10;
        Person lastAdded;
        boolean done = false;
        ArrayList<String> searchResult = new ArrayList<String>();
        SearchGUI s = new SearchGUI();

        try {
            gdb.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }

        do {
            //launch the main menu
            mm.GUI();
            //check to see if any selection has been made yet, otherwise just print blank line to terminal
            if(mm.selected){
                //pass in the current selection to switch conditional
                switch (mm.currentSelection) {
                    //add new person
                    case 1:
                        //finished
                        ap.GUI(gdb.getMales(), gdb.getFemales(), currentID);
                        while(ap.newPerson==null){
                            System.out.println();
                        }
                        lastAdded = ap.newPerson;
                        gdb.geneMap.put(lastAdded.getID(), lastAdded);
                        currentID++;
                        ap.resetPerson();
                        break;
                    //create new marriage
                    case 2:
                        //finished
                        am.GUI(gdb.getFemales(), gdb.getMales(), label);
                        String[] details = am.nm;
                        gdb.createNewMarriage(am.married, details);
                        label++;
                        break;
                    //search family tree
                    case 3:
                        //search gui and processing
                        s.GUI(gdb.getAllPeople());
                        while(s.personID==null){
                            System.out.println();
                        }
                        if(s.searchType.equals("children")){
                            searchResult = gdb.getChildren(s.personID);
                        }
                        if(s.searchType.equals("parents")){
                            searchResult = gdb.getParents(s.personID);
                        }
                        if(s.searchType.equals("grandparents")){
                            searchResult = gdb.getGrandparents(s.personID);
                        }
                        if(s.searchType.equals("siblings")){
                            searchResult = gdb.getSiblings(s.personID);
                        }
                        s.searchDisplay(searchResult);
                        break;
                    //edit an entry
                    case 4:
                        //finished
                        edit.GUI(gdb.getAllPeople());
                        while(edit.option == 7){
                            System.out.println();
                        }
                        gdb.editEntry(edit.person, edit.option, edit.update);
                        edit.resetEdits();
                        break;
                    //exit app
                    case 5:
                        //needs to be fixed to close program
                        try {
                            op.writeResults();
                            done = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
            else{
                System.out.println();
            }
            if(mm.currentSelection!=0){
                mm.resetSelection();
            }
        }while(!done);
    }
}