package View.Input;

import Controller.InputFileSelectionListener;
import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Model.FileNameExtensionFilter;
import javax.swing.event.ChangeListener;

public class InputFileSelection extends JPanel
{
    private ModelInterface model;

    private JTextField filePath;
    private JTextField file2Path;
    private JButton inputSelector1;
    private JButton inputSelector2;
    private JButton nextButton;
    private JCheckBox pairedEnd;
    private JFileChooser fileChooser;

    private InputFileSelectionListener inputFileListener;

    public InputFileSelection(ModelInterface model)
    {
        this.model = model;

        // Sets the layout of the screen
        setLayout(new BorderLayout());

        // Creates a new panel and adds it to the screen
        JPanel northPanel = new JPanel(new GridLayout(5, 1));        
        JPanel labelPanel = new JPanel(new FlowLayout());

        northPanel.add(labelPanel);
        add(northPanel, BorderLayout.NORTH);
        // Creates a new label and adds it to labelPanel

        JLabel label = new JLabel("");
        label.setFont(new Font("Sans Serif", Font.BOLD, 20));
        labelPanel.add(label);

        if (!model.isBLASTMode())
        {
            label.setText("Please select one set of query files");
        }
        else
        {
            label.setText("Please select a query file");
        }

        // Creates a new panel and adds it to the screen
        JPanel file1Panel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new GridLayout(5,2));
        centerPanel.add(file1Panel);

        // Creates a textfield for the file pathname, and a browse button
        // Add them to panel2
        filePath = new JTextField(40);
        filePath.setName("filePath1");
        inputSelector1  = new JButton("Browse...");
        JLabel file1Label = new JLabel("Query File 1: ");
        file1Label.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        file1Panel.add(file1Label);
        file1Panel.add(filePath);
        file1Panel.add(inputSelector1);

        // If not in BLAST mode, there will be two files selectable.
        // Otherwise, only one file.
        if (!model.isBLASTMode())
        {
            JPanel file2Panel = new JPanel(new FlowLayout());
            centerPanel.add(file2Panel);
            file2Path = new JTextField(40);
            file2Path.setName("file2Path");
            file2Path.setEditable(false);
            inputSelector2 = new JButton("Browse...");
            inputSelector2.setName("Browse2");
            inputSelector2.setEnabled(false);

            JLabel file2Label = new JLabel("Query File 2: ");
            file2Label.setFont(new Font("Sans Serif", Font.PLAIN, 16));
            file2Panel.add(file2Label);
            file2Panel.add(file2Path);
            file2Panel.add(inputSelector2);

            JPanel checkBoxPanel = new JPanel(new FlowLayout());
            centerPanel.add(checkBoxPanel);
            pairedEnd = new JCheckBox("Does a paired end file exist?");
            pairedEnd.setFont(new Font("Sans Serif", Font.PLAIN, 16));
            pairedEnd.addActionListener(inputFileListener);
            checkBoxPanel.add(pairedEnd);
        }

        this.add(centerPanel, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        FileNameExtensionFilter txtFilter =
                new FileNameExtensionFilter("Input Files (*.txt)", "TXT");
        fileChooser.addChoosableFileFilter(txtFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        filePath.setEditable(false);

        inputSelector1.setName("Browse1");

        // Add 'Next' button
        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
        nextButton.setMnemonic('N');
        JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextButtonPanel.add(nextButton);

        this.add(nextButtonPanel, BorderLayout.SOUTH);
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

    public JButton getNextButton()
    {
        return nextButton;
    }

    public void addInputFileListener(ActionListener al)
    {
        inputSelector1.addActionListener(al);
        nextButton.addActionListener(al);

        if (!model.isBLASTMode())
        {
            inputSelector2.addActionListener(al);
        }
    }

    public final void addPairedEndListener(ChangeListener cl)
    {
        pairedEnd.addChangeListener(cl);
    }
}
