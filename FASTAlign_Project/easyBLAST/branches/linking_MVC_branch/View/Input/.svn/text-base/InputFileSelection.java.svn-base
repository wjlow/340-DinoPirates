package View.Input;

import Controller.InputFileSelectionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Model.*;
import javax.swing.event.ChangeListener;

/**
 *
 * @author wjlow
 */
public class InputFileSelection extends JPanel
{
    private ModelInterface model;

    private JTextField filePath;
    private JTextField file2Path;
    private JButton inputSelector1;
    private JButton inputSelector2;
    private JCheckBox pairedEnd;
    private JLabel label;
    private JFileChooser fileChooser;

    JButton     parseFiles;

    InputFileSelectionListener inputFileListener;

    public InputFileSelection(ModelInterface model)
    {
        this.model = model;

        //inputFileListener = new InputFileSelectionListener(this, model);

        // Sets the layout of the screen
        setLayout(new GridLayout(5, 1));

        // Creates a new panel and adds it to the screen
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);

        // Creates a new label and adds it to panel1
        label = new JLabel("Please select one set of query files");
        panel1.add(label);

        // Creates a new panel and adds it to the screen
        JPanel panel2 = new JPanel(new FlowLayout());
        add(panel2);

        // Creates a textfield for the file pathname, and a browse button
        // Add them to panel2
        filePath        = new JTextField(40);
        filePath.setName("filePath1");
        inputSelector1  = new JButton("Browse...");
        panel2.add(filePath);
        panel2.add(inputSelector1);

        if (!model.isBLASTMode())
        {
            JPanel panel3 = new JPanel(new FlowLayout());
            add(panel3);
            file2Path        = new JTextField(40);
            file2Path.setName("file2Path");
            file2Path.setEditable(false);
            inputSelector2  = new JButton("Browse...");
            inputSelector2.addActionListener(inputFileListener);
            inputSelector2.setName("Browse2");
            inputSelector2.setEnabled(false);
            panel3.add(file2Path);
            panel3.add(inputSelector2);

            JPanel panel4 = new JPanel(new FlowLayout());
            add(panel4);
            pairedEnd       = new JCheckBox("Paired end?");
            pairedEnd.addActionListener(inputFileListener);
            panel4.add(pairedEnd);
        }
        JPanel panel5 = new JPanel(new FlowLayout());
        add(panel5);
        parseFiles = new JButton("Parse Selected Files");
        parseFiles.addActionListener(inputFileListener);
        //panel5.add(parseFiles);

        fileChooser = new JFileChooser();
        FileNameExtensionFilter txtFilter =
                new FileNameExtensionFilter("Input Files (*.txt)", "TXT");
        fileChooser.addChoosableFileFilter(txtFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        filePath.setEditable(false);
        
        inputSelector1.addActionListener(inputFileListener);
        inputSelector1.setName("Browse1");
    }

    public JButton getInputSelector1()
    {
        return inputSelector1;
    }
    public JButton getInputSelector2()
    {
        return inputSelector2;
    }

    public JFileChooser getFileChooser()
    {
        return fileChooser;
    }

    public JCheckBox getPairedEnd()
    {
        return pairedEnd;
    }

    public JTextField getFilePath()
    {
        return filePath;
    }

    public JTextField getFilePath2()
    {
        return file2Path;
    }

    public void setFilePath1(String s)
    {
        filePath.setText(s);
    }
    public void addParseFilesListener (ActionListener pfl)
    {
        parseFiles.addActionListener(pfl);
    }

    public void addInputFileListener(ActionListener al)
    {
        inputSelector1.addActionListener(al);
        inputSelector2.addActionListener(al);
    }

    public final void addPairedEndListener(ChangeListener cl)
    {
        pairedEnd.addChangeListener(cl);
    }
}
