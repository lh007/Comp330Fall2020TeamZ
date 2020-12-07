package CycleThree.ReleasedCode.src.main.java;
//package main.java; //for VSCode fix
import java.awt.event.*;
import javax.swing.*;


public class MainMenuGUI {
    public int currentSelection;
    public boolean selected = false;

    public MainMenuGUI(){ }

    public void GUI(){

        JFrame f = new JFrame("Main Menu");

        JLabel l1, l2;
        l1 = new JLabel("What would you like to do?");
        l1.setBounds(25, 30, 250, 30);
        l2 = new JLabel("*both partners must be in tree to add");
        l2.setBounds(40, 150, 250, 30);

        //create selection buttons
        JRadioButton r1, r2, r3, r4, r5;
        r1 = new JRadioButton("Add a new person");
        r2 = new JRadioButton("Add a new partnership");
        r3 = new JRadioButton("Search family tree");
        r4 = new JRadioButton("Edit an existing entry");
        r5 = new JRadioButton("Exit app");
        r1.setBounds(25, 70, 250, 30);
        r2.setBounds(25, 110, 250, 30);
        r3.setBounds(25, 190, 250, 30);
        r4.setBounds(25, 230, 250, 30);
        r5.setBounds(25, 270, 250, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);
        bg.add(r5);

        JButton b1, b2;
        b1 = new JButton("Okay");
        b2 = new JButton("Clear");
        b1.setBounds(60, 310, 100, 30);
        b2.setBounds(170, 310, 100, 30);

        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (r1.isSelected()) {
                        currentSelection = 1;
                    } else if (r2.isSelected()) {
                        currentSelection = 2;
                    } else if (r3.isSelected()) {
                        currentSelection = 3;

                    } else if (r4.isSelected()) {
                        currentSelection = 4;

                    } else if (r5.isSelected()) {
                        currentSelection = 5;
                    }
                    selected = true;
                f.dispose();
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                r1.setSelected(false);
                r2.setSelected(false);
                r3.setSelected(false);
                r4.setSelected(false);
                r5.setSelected(false);
            }
        });

        f.add(l1);
        f.add(l2);
        f.add(r1);
        f.add(r2);
        f.add(r3);
        f.add(r4);
        f.add(r5);
        f.add(b1);
        f.add(b2);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);

        do{
            System.out.println("");
        }while(selected == false);
    }

    public void resetSelection(){
        currentSelection = 0;
        selected = false;
    }
}