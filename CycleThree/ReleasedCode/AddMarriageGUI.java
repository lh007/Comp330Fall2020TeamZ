package CycleThree.ReleasedCode;
//package main.java; //for VSCode fix
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AddMarriageGUI {
    boolean flag = false;
    String [] nm = new String[6];
    ArrayList<String> married = new ArrayList<>();
    public AddMarriageGUI() {}
    public void GUI(ArrayList<String> women, ArrayList<String> men, int label){

        JFrame f = new JFrame("Add Marriage");

        JLabel l1, l2, l3, l4, l5;
        l1 = new JLabel("Wife:", SwingConstants.RIGHT);
        l2 = new JLabel("Husband:", SwingConstants.RIGHT);
        l3 = new JLabel("Date of marriage:", SwingConstants.RIGHT);
        l4 = new JLabel("End date (if divorced):", SwingConstants.RIGHT);
        l5 = new JLabel("Location of marriage:", SwingConstants.RIGHT);
        l1.setBounds(25, 30, 200, 30); //(x-value of top left, y-value of top left, width, height)
        l2.setBounds(25, 70, 200, 30);
        l3.setBounds(25, 110, 200, 30);
        l4.setBounds(25, 150, 200, 30);
        l5.setBounds(25, 190, 200, 30);


        JTextField t3, t4, t5;
        JComboBox<Object> cb1, cb2;
        cb1 = new JComboBox<Object>(women.toArray());
        cb2 = new JComboBox<Object>(men.toArray());
        cb1.setBounds(250, 30, 200, 30);
        cb2.setBounds(250, 70, 200, 30);
        t3 = new JTextField("MM/DD/YYYY");
        t4 = new JTextField("MM/DD/YYYY");
        t5 = new JTextField();
        t3.setBounds(250, 110, 200, 30);
        t4.setBounds(250, 150, 200, 30);
        t5.setBounds(250, 190, 200, 30);

        JButton b1, b2, b3;
        b1 = new JButton("Enter");
        b2 = new JButton("Clear");
        b3 = new JButton("Cancel");
        b1.setBounds(110, 250, 100, 40);
        b2.setBounds(220, 250, 100, 40);
        b3.setBounds(330, 250, 100, 40);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame k = new JFrame();
                nm[0] = "R"+ Integer.toString(label);
                nm[1] = cb1.getItemAt(cb1.getSelectedIndex()).toString();
                nm[2] = cb2.getItemAt(cb2.getSelectedIndex()).toString();
                String [] splitWife = nm[1].split(":");
                married.add(splitWife[0]);
                String []  splitHus = nm[2].split(":");
                married.add(splitHus[0]);
                if(t3.getText().equals("MM/DD/YYYY")){
                    nm[3] = "N/a";
                }
                else {
                    nm[3] = t3.getText();
                }
                if(t4.getText().equals("MM/DD/YYYY")) {
                    nm[4] = "N/a";
                }
                else{
                    nm[4] = t4.getText();
                }
                if(t5.getText().equals("")) {
                    nm[5] = "N/a";
                }
                else {
                    nm[5] = t5.getText();
                }

                String out = "Marriage details: " +nm[0]+ "\n"+ nm[1]+ "\n"+nm[2]+ "\n"+nm[3]+ "\n"+nm[4]+ "\n"+nm[5];
                JOptionPane.showMessageDialog(k, out);
                flag = true;
                f.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t3.setText("MM/DD/YYYY");
                t4.setText("MM/DD/YYYY");
                t5.setText("");
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
        f.add(l4);
        f.add(l5);
        f.add(cb1);
        f.add(cb2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setSize(585, 385);
        f.setLayout(null);
        f.setVisible(true);

        while(flag == false){
            System.out.print("");
        }
    }
}
