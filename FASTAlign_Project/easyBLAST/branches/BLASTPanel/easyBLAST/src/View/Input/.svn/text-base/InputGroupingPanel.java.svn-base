package View.Input;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import Model.*;

public class InputGroupingPanel extends JPanel implements ActionListener
{
    JPanel      panel1, panel2, panel3;
    JLabel      jlabel1, jlabel2;
    JTextField  group1, group2;
    JButton     group_button;
    Model m;

    public InputGroupingPanel(Model m)
    {
        this.m = m;

        setLayout(new GridLayout(3,1));

        panel1 = new JPanel(new FlowLayout());
        add(panel1);
        jlabel1 = new JLabel("Group 1:");
        group1 = new JTextField(50);
        panel1.add(jlabel1);
        panel1.add(group1);

        panel2 = new JPanel(new FlowLayout());
        add(panel2);
        jlabel2 = new JLabel("Group 2:");
        group2 = new JTextField(50);
        panel2.add(jlabel2);
        panel2.add(group2);

        panel3 = new JPanel(new FlowLayout());
        add(panel3);
        group_button = new JButton("Group!");
        group_button.addActionListener(this);
        panel3.add(group_button);

    }

     public void actionPerformed(ActionEvent e)
     {
        if(e.getSource() == group_button)
        {
            try {
                FileWriter f0 = new FileWriter("C:\\" + group1.getText() + "_group.txt");
                FileWriter f1 = new FileWriter("C:\\" + group2.getText() + "_group.txt");

                for(int i =0; i<m.getInputLineList().size(); i++)
                {
                    if(m.getInputLineList().get(i).getShortRead().substring(m.getIDStart(), m.getIDEnd()).equals(group1.getText()))
                    {
                        f0.write(m.getInputLineList().get(i).toString() + "\n");
                    }

                    if(m.getInputLineList().get(i).getShortRead().substring(m.getIDStart(), m.getIDEnd()).equals(group2.getText()))
                    {
                        f1.write(m.getInputLineList().get(i).toString() + "\n");
                    }
                }
                f0.close();
                f1.close();

            }
            catch(IOException ie)
            {

            }
        }
     }
}
