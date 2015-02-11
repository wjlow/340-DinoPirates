package View.Reference;

import Model.Model;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RefFileSelection extends JPanel implements ActionListener {

    JTextField  filePath;
    JTextField  file2Path;
    JButton     inputSelector1;
    JButton     inputSelector2;
    JLabel      label;
    JFileChooser    fc;
    JPanel      panel1;
    JPanel      panel2;
    JPanel      panel3;
    JPanel      panel5;
    JButton     parseFiles;

    Model m;

    public RefFileSelection(Model m)
    {
        this.m = m;

        setLayout(new GridLayout(4,1));
        panel1 = new JPanel(new FlowLayout());
        add(panel1);
        label = new JLabel("Please select one set of query files");
        panel1.add(label);

        panel2 = new JPanel(new FlowLayout());
        add(panel2);
        filePath        = new JTextField(40);
        inputSelector1  = new JButton("Browse...");
        panel2.add(filePath);
        panel2.add(inputSelector1);

        panel3 = new JPanel(new FlowLayout());
        add(panel3);
        file2Path        = new JTextField(40);
        inputSelector2  = new JButton("Browse...");
        panel3.add(file2Path);
        panel3.add(inputSelector2);

        panel5 = new JPanel(new FlowLayout());
        add(panel5);
        parseFiles = new JButton("Parse Selected Files");
        parseFiles.addActionListener(this);
        panel5.add(parseFiles);


        fc = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Reference Files (*.csv)", "CSV");
        fc.addChoosableFileFilter(txtFilter);
        fc.setAcceptAllFileFilterUsed(false);

        filePath.setEditable(false);
        file2Path.setEditable(false);
        inputSelector1.addActionListener(this);
        inputSelector2.addActionListener(this);
        inputSelector2.setEnabled(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == inputSelector1)
        {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                m.setRefFile(file);
                filePath.setText(m.getRefFile().getPath());
            }
        }
    }

    public void addReferenceHeaderParser(ActionListener rpl)
    {
        parseFiles.addActionListener(rpl);
    }
}
