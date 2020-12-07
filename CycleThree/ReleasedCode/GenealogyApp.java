package CycleThree.ReleasedCode;
//package main.java; //for VSCode fix
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

import static java.lang.System.*;

public class GenealogyApp {
    public static void main(String[] args) {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed.
        GeneDataBase gdb = new GeneDataBase("FamilyTreeInputTextFileV2.txt");
        HashMap<String, Person> map = gdb.exportData();
        OutputFile op = new OutputFile(map);
        AddPersonGUI ap = new AddPersonGUI();
        AddMarriageGUI am = new AddMarriageGUI();
        MainMenuGUI mm = new MainMenuGUI();
        EditGUI edit = new EditGUI();
        SearchGUI search = new SearchGUI();
        int currentID = 32;
        int label = 10;
        Person lastAdded;
        boolean done = false;
        ArrayList<String> searchResult = new ArrayList<String>();

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
                            out.println();
                        }
                        lastAdded = ap.newPerson;
                        gdb.geneMap.put(lastAdded.getID(), lastAdded);
                        for(String par: lastAdded.getParents()){
                            Person pare = gdb.geneMap.get(par);
                            pare.children.add(lastAdded.getID());
                        }
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
                        am.resetMarriage();
                        break;
                    //search family tree
                    case 3:
                        //search gui and processing
                        search.GUI(gdb.getAllPeople());
                        while(search.personID==null){
                            out.println();
                        }
                        if(search.searchType.equals("children")){
                            searchResult = gdb.getChildren(search.personID);
                        }
                        if(search.searchType.equals("parents")){
                            searchResult = gdb.getParents(search.personID);
                        }
                        if(search.searchType.equals("grandparents")){
                            searchResult = gdb.getGrandparents(search.personID);
                        }
                        if(search.searchType.equals("siblings")){
                            searchResult = gdb.getSiblings(search.personID);
                        }
                        search.searchDisplay(searchResult);
                        search.resetSearch();
                        break;
                    //edit an entry
                    case 4:
                        //finished
                        edit.GUI(gdb.getAllPeople());
                        while(edit.option == 7){
                            out.println();
                        }
                        gdb.editEntry(edit.person, edit.option, edit.update);
                        edit.resetEdits();
                        break;
                    //exit app
                    case 5:
                        done =true;
                }
            }
            else{
                out.println();
            }
            if(mm.currentSelection!=0){
                mm.resetSelection();
            }
        }while(!done);

        try {
            op.writeResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            out.println("Save TreeResults.tmp file if you would like, the program will time after 1 minute");
            exitAppNow();
        }
    }

    public static void exitAppNow(){
        Timer timer = new Timer();
        TimerTask exitApp = new TimerTask() {
            public void run() {
                System.exit(0);

            }
        };
        Date time = new Date(currentTimeMillis()+60*1000);
        timer.schedule(exitApp, time);
    }
}