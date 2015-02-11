package View.Reference;

import Model.ModelInterface;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Controller.ReferenceFileParser;

public class RefFileSelection extends JPanel implements ActionListener
{

    JTextField  filePath;
    JTextField  file2Path;
    JButton refBrowse2;
    JButton refBrowse1;
    JFileChooser    fc;

    ModelInterface m;

    public RefFileSelection(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(4,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        JLabel label = new JLabel("Please select one set of query files");
        panel1.add(label);

        JPanel panel2 = new JPanel(new FlowLayout());
        add(panel2);
        filePath        = new JTextField(40);
        refBrowse1  = new JButton("Browse...");
        panel2.add(filePath);
        panel2.add(refBrowse1);

        JPanel panel3 = new JPanel(new FlowLayout());
        add(panel3);
        file2Path = new JTextField(40);
        refBrowse2  = new JButton("Browse...");
        panel3.add(file2Path);
        panel3.add(refBrowse2);

        JPanel panel5 = new JPanel(new FlowLayout());
        add(panel5);

        fc = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Reference Files (*.csv)", "CSV");
        fc.addChoosableFileFilter(txtFilter);
        fc.setAcceptAllFileFilterUsed(false);

        filePath.setEditable(false);
        file2Path.setEditable(false);
        refBrowse1.addActionListener(this);
        refBrowse2.addActionListener(this);
        refBrowse2.setEnabled(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == refBrowse1)
        {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                m.setRefFile(file);
                filePath.setText(m.getRefFile().getPath());
            }

            ReferenceFileParser refParser = new ReferenceFileParser(m);
            refParser.setReferenceHeaders();
        }
    }

    public void addReferenceFileParser(ActionListener rpl)
    {
        refBrowse1.addActionListener(rpl);
    }

    public void updateFilePath1(String string)
    {
        filePath.setText(string);
    }
}
