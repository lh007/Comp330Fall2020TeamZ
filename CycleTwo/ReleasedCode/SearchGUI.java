package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SearchGUI {  
    public String personID;
    public String searchType;
    public String name;
    public boolean flag = false;
void GUI(ArrayList<String> people) {  
    JFrame f=new JFrame("Search");  

    JLabel l1,l2;
    l1 = new JLabel("Person:", SwingConstants.RIGHT);
    l2 = new JLabel("Search For:", SwingConstants.RIGHT);
    l1.setBounds(15,30,100,30); //(x-value of top left, y-value of top left, width, height)
    l2.setBounds(15,70,100,30);
    
    String[] categories = {"children", "parents", "grandparents", "siblings"};

    JComboBox<Object> cb1;
    JComboBox<String> cb2;
    cb1 = new JComboBox<Object>(people.toArray()); 
    cb2 = new JComboBox<String>(categories);
    cb1.setBounds(140,30,200,30);
    cb2.setBounds(140,70,200,30);

    JButton b1, b2;
    b1 = new JButton("Okay");
    b2 = new JButton("Cancel");
    b1.setBounds(90,120,100,40);
    b2.setBounds(200,120,100,40);

    b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Object chosen = cb1.getSelectedItem();
            String [] splitChoice = chosen.toString().split(":");
            personID = splitChoice[0];
            name = splitChoice[2];

            searchType = cb2.getItemAt(cb2.getSelectedIndex());
            flag = true;
        }
    });
    b2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            flag = true;
            f.dispose(); 
        }
    });

    f.add(l1); f.add(l2);
    f.add(cb1); f.add(cb2);
    f.add(b1); f.add(b2);
    f.setSize(390,210);  
    f.setLayout(null);  
    f.setVisible(true); 
    
    while(flag == false){
        System.out.print("");
    }
} 

public void searchDisplay(ArrayList<String> results){
    JFrame f=new JFrame("Search Results");
    String msg = "";
    if(results.isEmpty()){
        msg += name + " has no related " + searchType + ".";
    }
    else{
        msg += name + " has the following " + searchType + ":";
        for(String i:results){
            msg+= "\n";
            msg += i;
        }
    }
    JOptionPane.showMessageDialog(f, msg);
}
}  
