package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class EditGUI {

    private final ArrayList<String> options = new ArrayList<String>();
    public int option = 7;
    public Object person;
    public String update;
    public boolean flag;


    public EditGUI(){}

    public void GUI(ArrayList<String> allPeople){
        options.add("Given name"); //0
        options.add("Family name"); //1
        options.add("Suffix"); //2
        options.add("Birthplace"); //3
        options.add("Date of birth"); //4
        options.add("Death place"); //5
        options.add("Date of death"); //6
        flag = false;

        JFrame f = new JFrame("Edit entry");

        JLabel l1, l2, l3;
        l1 = new JLabel("Editing entry: ");
        l2 = new JLabel("Make change to: ");
        l3 = new JLabel("Update to: ");
        l1.setBounds(25, 30, 150, 30);
        l2.setBounds(25, 70, 150, 30);
        l3.setBounds(25, 110, 150, 30);

        JComboBox cb1, cb2;
        cb1 = new JComboBox<Object>(allPeople.toArray());
        cb2 = new JComboBox<Object>(options.toArray());
        cb1.setBounds(190, 30, 200, 30);
        cb2.setBounds(190, 70, 200, 30);

        JTextField t1;
        t1 = new JTextField();
        t1.setBounds(190, 110, 200, 30);
        t1.setEditable(true);

        JButton b1, b2, b3;
        b1 = new JButton("Okay");
        b2 = new JButton("Clear");
        b3 = new JButton("Cancel");
        b1.setBounds(30, 150, 100, 40);
        b2.setBounds(135, 150, 100, 40);
        b3.setBounds(240, 150, 100, 40);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msg ="";
                JFrame h = new JFrame();
                person = cb1.getItemAt(cb1.getSelectedIndex());
                option = cb2.getSelectedIndex();
                update = t1.getText();
                msg+="Changes to: " + person.toString()+"\n";
                msg+="Edited: " + cb2.getSelectedItem().toString()+"\n";
                msg+="Now: " + update+"\n";
                JOptionPane.showMessageDialog(h, msg);
                flag = true;
                f.dispose();
            }
        });


        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                flag = true;
            }
        });

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(cb1);
        f.add(cb2);
        f.add(t1);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setSize(500, 300);
        f.setLayout(null);
        f.setVisible(true);

        while(flag == false){
            System.out.print("");
        }
    }

    public void resetEdits(){
        option = 7;
        update = "";
        person = null;
    }
}
