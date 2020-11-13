package CycleTwo.ReleasedCode;
//package main.java; //for VSCode fix
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class AddPersonGUI{
    public Person newPerson;
    public boolean flag = false;
    int k = 1;
    public AddPersonGUI(){

    }
    String[] dataPull = new String[10];
    public void GUI(ArrayList<String> men, ArrayList<String> women, ArrayList<String> all, int currentID) {
        JFrame f = new JFrame("Add Person");

//Creating text labels
        JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
        l1 = new JLabel("*Given Name:", SwingConstants.RIGHT);
        l2 = new JLabel("*Family Name:", SwingConstants.RIGHT);
        l3 = new JLabel("Suffix:", SwingConstants.RIGHT);
        l4 = new JLabel("*Date of Birth:", SwingConstants.RIGHT);
        l5 = new JLabel("*Place of Birth:", SwingConstants.RIGHT);
        l6 = new JLabel("Date of Death:", SwingConstants.RIGHT);
        l7 = new JLabel("Place of Death:", SwingConstants.RIGHT);
        l8 = new JLabel("*Mother:", SwingConstants.RIGHT);
        l9 = new JLabel("*Father:", SwingConstants.RIGHT);
        l1.setBounds(25, 30, 100, 30); //(x-value of top left, y-value of top left, width, height)
        l2.setBounds(25, 70, 100, 30);
        l3.setBounds(25, 110, 100, 30);
        l4.setBounds(25, 190, 100, 30);
        l5.setBounds(25, 230, 100, 30);
        l6.setBounds(25, 310, 100, 30);
        l7.setBounds(25, 350, 100, 30);
        l8.setBounds(25, 390, 100, 30);
        l9.setBounds(25, 430, 100, 30);

//Creating text boxes to write information in
        JTextField t1, t2, t3, t4, t5, t6, t7;
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField("DD/MM/YYYY");
        t5 = new JTextField("City, State");
        t6 = new JTextField("DD/MM/YYYY");
        t7 = new JTextField("City, State");
        t1.setBounds(150, 30, 200, 30);
        t2.setBounds(150, 70, 200, 30);
        t3.setBounds(150, 110, 200, 30);
        t4.setBounds(150, 190, 200, 30);
        t5.setBounds(150, 230, 200, 30);
//t6 (Date of Death) and t7 (Place of Death) start off greyed out and unable to edit, until user indicates the person is deceased
        t6.setBounds(150, 310, 200, 30);
        t6.setEditable(false);
        t7.setBounds(150, 350, 200, 30);
        t7.setEditable(false);

//Adds radio button to select either male or female
        JRadioButton r1, r2;
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r1.setBounds(150, 150, 100, 30);
        r2.setBounds(250, 150, 100, 30);
//Putting r1 and r2 in the same ButtonGroup indicates only one can be selected at a time
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

//Add checkbox to indicate if person is decesed
        JCheckBox c1 = new JCheckBox("Deceased");
        c1.setBounds(150, 270, 100, 30);

//This handles what happens when the box is either checked or unchecked
        c1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //if the box was checked, enable the two text boxes and mark them as required
                if (e.getStateChange() == 1) {
                    t6.setEditable(true);
                    t7.setEditable(true);
                    l6.setText("*Date of Death:");
                    l7.setText("*Place of Death:");
                }
                //if the box was unchecked, disable the text boxes, clear any info put in them, and mark them as no longer required
                else {
                    t6.setEditable(false);
                    t7.setEditable(false);
                    t6.setText("");
                    t7.setText("");
                    l6.setText("Date of Death:");
                    l7.setText("Place of Death:");
                }
            }
        });

//Adding two new combo boxes
        JComboBox<Object> cb8, cb9;
//One contains all the women in our family tree (potential mothers)
        cb8 = new JComboBox<Object>(women.toArray());
//The other contains all the men (potential fathers)
        cb9 = new JComboBox<Object>(men.toArray());
        cb8.setBounds(150, 390, 200, 30);
        cb9.setBounds(150, 430, 200, 30);

//Adds three buttons
        JButton b1, b2, b3;
        b1 = new JButton("Okay");
        b2 = new JButton("Clear");
        b3 = new JButton("Cancel");
        b1.setBounds(30, 490, 100, 40);
        b2.setBounds(135, 490, 100, 40);
        b3.setBounds(240, 490, 100, 40);

//Button 1 (The "Okay" Button) Compiles all input info into an output statement
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do {
                    //This new frame will be used to display either "Please fill in the rest of the info" or to show the user their info
                    JFrame h = new JFrame();
                    //A long list of checks to ensure the user has filled out all necessary info
                    if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("") || t5.getText().equals("")
                            || (c1.isSelected() && t6.getText().equals("")) || (c1.isSelected() && t7.getText().equals("")) || (!r1.isSelected() && !r2.isSelected())) {
                        //If they havent. They are shown a message asking them to complete the form
                        JOptionPane.showMessageDialog(h, "This form is incomplete. Please fill out all required fields before submitting.");
                    }
                    //Otherwise, we create the new Person and go through and start to add all information to a String
                    else {
                        Object momID = "";
                        Object dadID ="";

                        if (t3.getText().equals("")) {
                            t3.setText("N/a");
                        }
                        if (!c1.isSelected()) {
                            t6.setText("N/a");
                            t7.setText("N/a");
                        }
                        String sex;
                        if (r1.isSelected()) {
                            sex = "M";
                        } else {
                            sex = "F";
                        }
                        momID = cb8.getItemAt(cb8.getSelectedIndex());
                        dadID = cb9.getItemAt(cb9.getSelectedIndex());

                        String [] splitMom = momID.toString().split(":");
                        String MID = splitMom[0];
                        String [] splitDad = dadID.toString().split(":");
                        String DID = splitDad[0];

                        dataPull[0] = "N/a";
                        dataPull[1] = t2.getText();
                        dataPull[2] = t1.getText();
                        dataPull[3] = t3.getText();
                        dataPull[4] = t4.getText();
                        dataPull[5] = t5.getText();
                        dataPull[6] = t6.getText();
                        dataPull[7] = t7.getText();
                        dataPull[8] = "N/a";
                        dataPull[9] = sex;
                        newPerson = new Person(dataPull, currentID, MID, DID);
                        String msg = "";
                        msg += "Given Name: " + t1.getText() + "\n";
                        msg += "Family Name: " + t2.getText() + "\n";
                        if (!t3.getText().equals("")) {
                            msg += "Suffix" + t3.getText() + "\n";
                        }
                        msg += "Date of Birth: " + t4.getText() + "\n";
                        msg += "Place of Birth: " + t5.getText() + "\n";
                        if (r1.isSelected()) {
                            msg += "Sex: Male\n";
                        } else {
                            msg += "Sex: Female\n";
                        }
                        if (!c1.isSelected()) {
                            msg += "Deceased: No\n";
                        } else {
                            msg += "Deceased: Yes\n";
                            msg += "Date of Death: " + t6.getText() + "\n";
                            msg += "Place of Death: " + t7.getText() + "\n";
                        }
                        msg += "Mother: " + cb8.getItemAt(cb8.getSelectedIndex()) + "\n";
                        msg += "Father: " + cb9.getItemAt(cb9.getSelectedIndex()) + "\n";

                        //This string is then added to the dialog box and shown to the user.
                        JOptionPane.showMessageDialog(h, msg);
                        flag = true;
                    }
                }while(flag==false);
                f.dispose();
            }
        });

//Button 2 (The "Clear" Button) clears all text fields and deselects all boxes/buttons.
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                c1.setSelected(false);
                r1.setSelected(false);
                r2.setSelected(false);
            }
        });

//Button 3 (The "Cancel" Button) closes the window
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

//We add all these labels, buttons, and boxes into our Frame
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(l7);
        f.add(l8);
        f.add(l9);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(t6);
        f.add(t7);
        f.add(r1);
        f.add(r2);
        f.add(c1);
        f.add(cb8);
        f.add(cb9);
        f.add(b1);
        f.add(b2);
        f.add(b3);
//Then we set the sixe of this frame (width,height)
        f.setSize(385, 590);
        f.setLayout(null);
        f.setVisible(true);

       while(flag == false){
           System.out.println();
       }
    }
} 