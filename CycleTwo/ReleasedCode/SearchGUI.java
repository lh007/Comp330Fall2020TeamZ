package CycleTwo.ReleasedCode;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SearchGUI {  
void GUI(ArrayList<String> people, ArrayList<String> result) {  
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
        Object chosen = cb1.getSelectedItem();
        String [] splitChoice = chosen.toString().split(":");
        String ID = splitChoice[0];
        public void actionPerformed(ActionEvent e){
            if(cb2.getSelectedItem().equals("children")){

            }
            if(cb2.getSelectedItem().equals("parents")){

            }
            if(cb2.getSelectedItem().equals("grandparents")){

            }
            if(cb2.getSelectedItem().equals("siblings")){

            }
        }
    });
    b2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            f.dispose(); 
        }
    });

    f.add(l1); f.add(l2);
    f.add(cb1); f.add(cb2);
    f.add(b1); f.add(b2);
    f.setSize(390,210);  
    f.setLayout(null);  
    f.setVisible(true);   
}  
}  
