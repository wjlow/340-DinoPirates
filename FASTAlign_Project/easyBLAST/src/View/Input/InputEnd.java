package View.Input;

import Controller.TagFilePreprocessListener;
import Model.Group;
import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class InputEnd extends JPanel implements Runnable
{
    private JLabel      summaryLabel1;
    private JButton     generateTag;
    private JButton     preProcessButton;
    private JButton     backButton;
    private JFileChooser fileChooser;

    // For Summary
    private JTextArea  summaryField;
    private JScrollPane summaryScrollPane;
    private final int columnsToDisplay = 60;
    private final int rowsToDisplay = 25;

    private TagFilePreprocessListener tagFilePreprocess;
    private ModelInterface model;

    private JLabel  progressLabel;
    private JLabel  progressLabel2;
    private JPanel  progressLabelPanel;
    private int     count;
    private boolean updatingProgressBar;

    public InputEnd(ModelInterface model)
    {
        this.model = model;

        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        JPanel labelPanel = new JPanel(new FlowLayout());

        summaryLabel1 = new JLabel("Summary:");
        summaryLabel1.setFont(new Font("Sans Serif", Font.BOLD, 20));
        labelPanel.add(summaryLabel1);
        northPanel.add(labelPanel);

        summaryField = new JTextArea(this.getSummaryLines(), rowsToDisplay,
                columnsToDisplay);
        summaryField.setLineWrap(true);
        summaryField.setEditable(false);

        /* Things in the centre */
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        summaryScrollPane = new JScrollPane(summaryField);
        summaryScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        JPanel summaryScrollPanePanel = new JPanel(new FlowLayout());
        summaryScrollPanePanel.add(summaryScrollPane);
        centerPanel.add(summaryScrollPanePanel);

        progressLabel = new JLabel("");
        progressLabel2 = new JLabel("");

        progressLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        progressLabel2.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        progressLabel2.setForeground(Color.red);


        progressLabelPanel = new JPanel(new FlowLayout());
        progressLabelPanel.add(progressLabel);
        progressLabelPanel.add(progressLabel2);

        centerPanel.add(progressLabelPanel);

        JPanel preProcessButtonPanel = new JPanel(new FlowLayout());
        preProcessButton = new JButton("Pre-Process");

        /* Navigation buttons */
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        navigationPanel.add(backButton);
        navigationPanel.add(preProcessButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(navigationPanel, BorderLayout.SOUTH);
    }

    public void run()
    {
        while(updatingProgressBar)
        {
            progressLabel.setText("These many lines have been " +
                    "pre-processed: ");
            progressLabel2.setText(String.valueOf(count));


            SwingUtilities.updateComponentTreeUI(progressLabel);
            try
            {
                Thread.sleep(1000);
            } 
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public String getSummaryLines()
    {
        int idStart = 0;
        int idEnd = 0;
        boolean rangeSelected = false;
        // Input file selection

        String inputFilePath;
        String pairedEndFilePath;

        if (model.getInputFile() != null)
        {
            inputFilePath = model.getInputFile().getPath();
        }
        else
        {
            inputFilePath = "No input file selected.";
        }

        if (model.getPairedEndFile() != null)
        {
            pairedEndFilePath = model.getPairedEndFile().getPath();
        }
        else
        {
            pairedEndFilePath = "No paired end file selected.";
        }

        // Quality threshold

        int qualityValue = model.getQualityASCII();
        int qualitySum = model.getQSum();

        // ID Selection
        // +1 and -1 to format the index properly for the reader.
        if(model.getInputIDEnd() != model.getInputIDStart())
        {
             idStart = model.getInputIDStart() + 1;
             idEnd = model.getInputIDEnd();
             rangeSelected = true;
        }
       

        // Grouping

        List<Group> groups = model.getPreProcessingHandler().getGroupList();
        String groupSummary = "\n";

        for (int i = 0; i < groups.size(); i++)
        {
            groupSummary = groupSummary + "\n" + groups.get(i).getGroupName()
                    + ": " + groups.get(i).idToString();
        }

        if(rangeSelected)
        {
            return "Input File: " + inputFilePath + "\nPaired End File: "
                + pairedEndFilePath
                + "\n\nASCII Decimal Character Value Threshold: " + qualityValue
                + "\nSum of ASCII Decimal Character Values Threshold: "
                + qualitySum + "\n\nSelected ID Region: [" + idStart
                + ", " + (idEnd) + "]" + groupSummary;
        }
        else
        {
            return "Input File: " + inputFilePath + "\nPaired End File: "
                + pairedEndFilePath
                + "\n\nASCII Decimal Character Value Threshold: " + qualityValue
                + "\nSum of ASCII Decimal Character Values Threshold: "
                + qualitySum + "\n\nSelected ID Region: No ID region selected "
                + groupSummary;
        }
        
    }

    public void addTagActionListeners(ActionListener al)
    {
        preProcessButton.addActionListener(al);
        backButton.addActionListener(al);
    }

    public JButton getGenerateTag()
    {
        return generateTag;
    }

    public JButton getPreProcess()
    {
        return preProcessButton;
    }

    public JFileChooser getFileChooser()
    {
        return fileChooser;
    }

    public JTextArea getSummaryField()
    {
        return summaryField;
    }

    public JButton getBackButton()
    {
        return backButton;
    }

    public JLabel getProgressLabel()
    {
        return progressLabel;
    }

    public JPanel getProgressLabelPanel()
    {
        return progressLabelPanel;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getCount()
    {
        return count;
    }

    public void setUpdatingProgressBar(boolean updatingProgressBar)
    {
        this.updatingProgressBar = updatingProgressBar;
    }

    public boolean getUpdatingProgressBar()
    {
        return updatingProgressBar;
    }
}
