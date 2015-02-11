package View.BLAST;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;

public class ParametersPanel extends JPanel {

    JTextField  expFailMarg;
    JTextField  winSize;
    JTextField  numHits;
    JTextField  qStrand;
    JTextField  numDBSeq;
    JTextField  numDBSeqAlig;

    JLabel      label1;
    JLabel      label2;
    JLabel      label3;
    JLabel      label4;
    JLabel      label5;
    JLabel      label6;
    JLabel      label7;

    JPanel      panel1;
    JPanel      panel2;
    JPanel      panel3;
    JPanel      panel4;
    JPanel      panel5;
    JPanel      panel6;
    JPanel      panel7;

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
        label2      = new JLabel("Expected Failure Margin, e:");
        expFailMarg = new JTextField(m.getExpFailureMargin().toString(), 3);
        panel2.add(label2);
        panel2.add(expFailMarg);

        panel3 = new JPanel(new FlowLayout());
        add(panel3);
        label3  = new JLabel("Window Size, W:");
        winSize = new JTextField(m.getWinSize().toString(), 3);
        panel3.add(label3);
        panel3.add(winSize);

        panel4 = new JPanel(new FlowLayout());
        add(panel4);
        label4  = new JLabel("Number of Hits, K:");
        numHits = new JTextField(m.getNumHits().toString(), 3);
        panel4.add(label4);
        panel4.add(numHits);

        panel5 = new JPanel(new FlowLayout());
        add(panel5);
        label5  = new JLabel("Query Strands to search against database, S:");
        qStrand = new JTextField(m.getQueryStrands().toString(), 3);
        panel5.add(label5);
        panel5.add(qStrand);

        panel6 = new JPanel(new FlowLayout());
        add(panel6);
        label6 = new JLabel("Number of database sequences to show " +
                "one-line descriptions for (V), v:");
        numDBSeq = new JTextField(m.getNumDBSeq1Ln().toString(), 3);
        panel6.add(label6);
        panel6.add(numDBSeq);

        panel7 = new JPanel(new FlowLayout());
        add(panel7);
        label7 = new JLabel("Number of database sequence to show " +
                "alignments for (B), b:");
        numDBSeqAlig = new JTextField(m.getNumDBSeqAlig().toString(), 3);
        panel7.add(label7);
        panel7.add(numDBSeqAlig);

    }

}
