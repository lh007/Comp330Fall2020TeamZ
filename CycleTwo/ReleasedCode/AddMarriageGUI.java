package CycleTwo.ReleasedCode;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AddMarriageGUI {
    String [] nm = new String[6];
    public AddMarriageGUI() {}
    public void GUI(ArrayList<String> parents, int label){
        String mom = parents.get(0);
        String dad = parents.get(1);
        JFrame f = new JFrame("Add Marriage");

        JLabel l1, l2, l3, l4, l5;
        l1 = new JLabel("Mother:", SwingConstants.RIGHT);
        l2 = new JLabel("Father:", SwingConstants.RIGHT);
        l3 = new JLabel("Date of marriage:", SwingConstants.RIGHT);
        l4 = new JLabel("End date (if divorced):", SwingConstants.RIGHT);
        l5 = new JLabel("Location of marriage:", SwingConstants.RIGHT);
        l1.setBounds(25, 30, 100, 30); //(x-value of top left, y-value of top left, width, height)
        l2.setBounds(25, 70, 100, 30);
        l3.setBounds(25, 110, 100, 30);
        l4.setBounds(25, 190, 100, 30);
        l5.setBounds(25, 230, 100, 30);

        JTextField t1, t2, t3, t4, t5;
        t1 = new JTextField(mom);
        t2 = new JTextField(dad);
        t3 = new JTextField("MM/DD/YYYY");
        t4 = new JTextField("MM/DD/YYYY");
        t5 = new JTextField();
        t1.setBounds(150, 30, 200, 30);
        t2.setBounds(150, 70, 200, 30);
        t3.setBounds(150, 110, 200, 30);
        t4.setBounds(150, 190, 200, 30);
        t5.setBounds(150, 230, 200, 30);

        JButton b1, b2, b3;
        b1 = new JButton("Enter");
        b2 = new JButton("Clear");
        b3 = new JButton("Cancel");
        b1.setBounds(30, 490, 100, 40);
        b2.setBounds(135, 490, 100, 40);
        b3.setBounds(240, 490, 100, 40);

        b1.addActionListener(new ActionListener() {
            boolean flag = false;
            public void actionPerformed(ActionEvent e) {
                JFrame k = new JFrame();
                do{
                    nm[0] = "R"+ Integer.toString(label);
                    nm[1] = t1.getText();
                    nm[2] = t2.getText();
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

                }while (flag == false);
                f.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText(mom);
                t2.setText(dad);
                t3.setText("MM/DD/YYYY");
                t4.setText("MM/DD/YYYY");
                t5.setText("");
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setSize(385, 590);
        f.setLayout(null);
        f.setVisible(true);


    }
}
