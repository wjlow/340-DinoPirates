package View.Input;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;
import Model.*;
import java.util.StringTokenizer;

public class InputGroupingPanel extends JPanel implements ActionListener
{
    private JTextField group1, group2;
    private JButton group_button;
    private ModelInterface m;

    public InputGroupingPanel(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(3,1));

        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        JLabel jlabel1 = new JLabel("Group 1:");
        group1 = new JTextField(50);
        group1.setName("group1");
        panel1.add(jlabel1);
        panel1.add(group1);

        JPanel panel2 = new JPanel(new FlowLayout());
        add(panel2);
        JLabel jlabel2 = new JLabel("Group 2:");
        group2 = new JTextField(50);
        group2.setName("group2");
        panel2.add(jlabel2);
        panel2.add(group2);

        JPanel panel3 = new JPanel(new FlowLayout());
        add(panel3);
        group_button = new JButton("Group!");
        group_button.addActionListener(this);
        panel3.add(group_button);

    }

    /**
     * The method is an actionListener to the Grouping interface. IDs entered
     * by the user for each group is extracted and stored in a 2-D array.
     * Appropriate function from the model are called to carry out the grouping
     * process.
     *
     * @param e
     */
     public void actionPerformed(ActionEvent e)
     {
         //if the Group! button is pressed, do the following
        if (e.getSource() == group_button)
        {
            //Text entered in each group is extracted
            String group1Ids = group1.getText();
            String group2Ids = group2.getText();

            System.out.println("Group1: " + group1Ids + "\nGroup2:"
                    + group2Ids);

            StringTokenizer str2 = new StringTokenizer(group2Ids, ",");
            StringTokenizer str1 = new StringTokenizer(group1Ids, ",");

            //The string is split and stored in an array of strings
            //String splitIDsGroup1 [] = group1Ids.split(",");
            //String splitIDsGroup2 [] = group2Ids.split(",");


            String[][] groupArray;
            //determining the length of the array
            if (str1.countTokens() <= str2.countTokens())
            {
                int len1 = str2.countTokens();
                groupArray = new String [2][len1];
            }
            else
            {
               int len1 = str1.countTokens();
               groupArray = new String [2][len1];
            }
            System.out.println(str1.countTokens() + "  " + str2.countTokens());
            int count = 0;
            while (str1.hasMoreElements())
            {
                groupArray[0][count] = str1.nextToken();
                System.out.println("Group1" + groupArray[0][count]);
                count++;
            }
            while (str1.hasMoreElements())
            {
                groupArray[0][count] = str1.nextToken();
                System.out.println("Group1" + groupArray[0][count]);
                count++;
            }

             m.readInputFile();

             //System.out.println("I am before make groups");
             m.callMakeGroups(groupArray);
             //System.out.println("After the make groups");
             m.callDoGrouping();
             System.out.println("After the grouping");

            /*
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
           */
        }
     }
}
