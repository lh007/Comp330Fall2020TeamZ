package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GenealogyApp {
    public static void main(String[] args) {
        // creates new GeneDataBase, and tries to read in the file using plantTree(). If there is a problem,
        // an exception will be printed.
        GeneDataBase gdb = new GeneDataBase();
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


        try {
            gdb.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }

        do {
            mm.GUI();
            if(mm.selected){
                switch (mm.currentSelection) {
                    case 1:
                        //add new person
                        //finished
                        ap.GUI(gdb.getMales(), gdb.getFemales(), gdb.getAllPeople(), currentID);
                        while(ap.newPerson==null){
                            System.out.println();
                        }
                        lastAdded = ap.newPerson;
                        gdb.geneMap.put(lastAdded.getID(), lastAdded);
                        currentID++;
                        ap.resetPerson();
                        break;
                    case 2:
                        //create new marriage
                        //finished
                        am.GUI(gdb.getFemales(), gdb.getMales(), label);
                        String[] details = am.nm;
                        gdb.createNewMarriage(am.married, details);
                        label++;
                        break;
                    case 3:
                        //search family tree




                        break;
                    case 4:
                        //edit an entry
                        //finished
                        edit.GUI(gdb.getAllPeople());
                        while(edit.option == 7){
                            System.out.println();
                        }
                        gdb.editEntry(edit.person, edit.option, edit.update);
                        edit.resetEdits();
                        break;
                    case 5:
                        //exit app
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