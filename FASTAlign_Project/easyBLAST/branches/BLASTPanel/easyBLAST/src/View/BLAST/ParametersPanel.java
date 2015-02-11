package View.BLAST;

import Model.Model;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ParametersPanel extends JPanel implements ActionListener
{

    JTextField  expFailMarg;
    JTextField  winSize;
    JTextField  numHits;
    JTextField  qStrands;
    JTextField  numDBSeq1Ln;
    JTextField  numDBSeqAlig;

    JLabel      label1, label2, label3 ,label4 ,label5 ,label6 ,label7;

    JPanel      panel1 ,panel2 ,panel3 ,panel4 ,panel5 ,panel6 ,panel7, panel8;

    JFrame      frame;

    JButton     storeVals;

    Model m;

    public ParametersPanel(Model m)
    {
        this.m = m;

        setLayout(new GridLayout(8,1));
        panel1 = new JPanel(new FlowLayout());
        add(panel1);
        label1 = new JLabel("Please enter the values for the parameters");
        panel1.add(label1);

        panel2 = new JPanel(new FlowLayout());
        add(panel2);
        label2      = new JLabel("1. Expected Failure Margin, e:");
        expFailMarg = new JTextField(m.getExpFailureMargin().toString(), 3);
        panel2.add(label2);
        panel2.add(expFailMarg);

        panel3 = new JPanel(new FlowLayout());
        add(panel3);
        label3  = new JLabel("2. Window Size, W:");
        winSize = new JTextField(m.getWinSize().toString(), 3);
        panel3.add(label3);
        panel3.add(winSize);

        panel4 = new JPanel(new FlowLayout());
        add(panel4);
        label4  = new JLabel("3. Number of Hits, K:");
        numHits = new JTextField(m.getNumHits().toString(), 3);
        panel4.add(label4);
        panel4.add(numHits);

        panel5 = new JPanel(new FlowLayout());
        add(panel5);
        label5  = new JLabel("4. Query Strands to search against database, S:");
        qStrands = new JTextField(m.getQueryStrands().toString(), 3);
        panel5.add(label5);
        panel5.add(qStrands);

        panel6 = new JPanel(new FlowLayout());
        add(panel6);
        label6 = new JLabel("5. Number of database sequences to show " +
                "one-line descriptions for (V), v:");
        numDBSeq1Ln = new JTextField(m.getNumDBSeq1Ln().toString(), 3);
        panel6.add(label6);
        panel6.add(numDBSeq1Ln);

        panel7 = new JPanel(new FlowLayout());
        add(panel7);
        label7 = new JLabel("6. Number of database sequence to show " +
                "alignments for (B), b:");
        numDBSeqAlig = new JTextField(m.getNumDBSeqAlig().toString(), 3);
        panel7.add(label7);
        panel7.add(numDBSeqAlig);

        panel8 = new JPanel(new FlowLayout());
        add(panel8);
        storeVals = new JButton("Store");
        panel8.add(storeVals);

        storeVals.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == storeVals)
        {
            try
            {
                m.setExpFailMargin(Double.parseDouble(expFailMarg.getText()));
                m.setWinSize(Integer.parseInt(winSize.getText()));
                m.setNumHits(Integer.parseInt(numHits.getText()));
                m.setQueryStrands(Integer.parseInt(qStrands.getText()));
                m.setNumDBSeq1Ln(Integer.parseInt(numDBSeq1Ln.getText()));
                m.setNumDBSeqAlig(Integer.parseInt(numDBSeqAlig.getText()));

            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog( frame, "You should enter a " +
                        "value for each field!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
 
        }
        
    }


}
