package View.Input;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Model.*;

/**
 *
 * @author wjlow
 */
public class InputFileSelection extends JPanel implements ActionListener
{
    JTextField  filePath;
    JTextField  file2Path;
    JButton     inputSelector1;
    JButton     inputSelector2;
    JCheckBox   pairedEnd;
    JLabel      label;
    JFileChooser    fc;
    JPanel      panel1;
    JPanel      panel2;
    JPanel      panel3;
    JPanel      panel4;
    JPanel      panel5;
    JButton     parseFiles;

    ModelInterface model;

    public InputFileSelection(ModelInterface model)
    {
        this.model = model;

        // Sets the layout of the screen
        setLayout(new GridLayout(5, 1));

        // Creates a new panel and adds it to the screen
        panel1 = new JPanel(new FlowLayout());
        add(panel1);

        // Creates a new label and adds it to panel1
        label = new JLabel("Please select one set of query files");
        panel1.add(label);

        // Creates a new panel and adds it to the screen
        panel2 = new JPanel(new FlowLayout());
        add(panel2);

        // Creates a textfield for the file pathname, and a browse button
        // Add them to panel2
        filePath        = new JTextField(40);
        inputSelector1  = new JButton("Browse...");
        panel2.add(filePath);
        panel2.add(inputSelector1);

        if (!model.isBLASTMode())
        {
            panel3 = new JPanel(new FlowLayout());
            add(panel3);
            file2Path        = new JTextField(40);
            file2Path.setEditable(false);
            inputSelector2  = new JButton("Browse...");
            inputSelector2.addActionListener(this);
            inputSelector2.setEnabled(false);
            panel3.add(file2Path);
            panel3.add(inputSelector2);

            panel4 = new JPanel(new FlowLayout());
            add(panel4);
            pairedEnd       = new JCheckBox("Paired end?");
            pairedEnd.addActionListener(this);
            panel4.add(pairedEnd);
        }
        panel5 = new JPanel(new FlowLayout());
        add(panel5);
        parseFiles = new JButton("Parse Selected Files");
        parseFiles.addActionListener(this);
        //panel5.add(parseFiles);


        fc = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Input Files (*.txt)", "TXT");
        fc.addChoosableFileFilter(txtFilter);
        fc.setAcceptAllFileFilterUsed(false);

        filePath.setEditable(false);
        
        inputSelector1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == pairedEnd)
        {
            if(pairedEnd.isSelected())
                inputSelector2.setEnabled(true);
            else
            {
                inputSelector2.setEnabled(false);
                model.setFile2(null);
                file2Path.setText("");
            }
        }

        if(e.getSource() == inputSelector1)
        {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                model.setInputFile(file);
                filePath.setText(model.getFile1().getPath());
            }
        }

        if(e.getSource() == inputSelector2)
        {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file2 = fc.getSelectedFile();
                model.setFile2(file2);
                file2Path.setText(model.getFile2().getPath());
            }
        }
    }

    public void addParseFilesListener (ActionListener pfl)
    {
        parseFiles.addActionListener(pfl);
    }
}
