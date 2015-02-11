package View.BLAST;

import Model.ModelInterface;
//import Model.Parameters;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QueryBLASTPanel extends JPanel
{
    private JLabel      summaryLabel;
    private JButton     stopButton;
    private JButton     alignButton;
    private JButton     backButton;
    private JTextArea  summaryField;
    private JScrollPane summaryScrollPane;
    private final int columnsToDisplay = 60;
    private final int rowsToDisplay = 20;
    private ModelInterface model;

    /**
     * The constructor takes in a ModelInterface and attaches it to the current
     * view
     * @param m The ModelInterface to attach.
     */
    public QueryBLASTPanel(final ModelInterface m)
    {
        model = m;

        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        JPanel labelPanel = new JPanel(new FlowLayout());

        summaryLabel = new JLabel("Summary:");
        summaryLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        labelPanel.add(summaryLabel);
        northPanel.add(labelPanel);

        summaryField = new JTextArea(this.getSummaryLines(), rowsToDisplay,
                columnsToDisplay);
        summaryField.setLineWrap(true);
        summaryField.setRows(rowsToDisplay);
        summaryField.setEditable(false);

        /* Things in the centre */
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        summaryScrollPane = new JScrollPane(summaryField);
        JPanel summaryScrollPanePanel = new JPanel(new FlowLayout());
        summaryScrollPanePanel.add(summaryScrollPane);
        centerPanel.add(summaryScrollPanePanel);

        /* Navigation buttons */
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        stopButton = new JButton("Stop!");
        stopButton.setMnemonic('S');
        stopButton.setEnabled(false);
        alignButton = new JButton("Align!");
        alignButton.setMnemonic('A');
        backButton = new JButton("Back");
        backButton.setMnemonic('B');

        navigationPanel.add(stopButton);
        navigationPanel.add(backButton);
        navigationPanel.add(alignButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(navigationPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an ActionListener to the BLAST JButton and the tools JComboBox
     * @param l The ActionListener that is attached.
     */
    public final void addBLASTListener(final ActionListener l)
    {
        alignButton.addActionListener(l);
        backButton.addActionListener(l);
        stopButton.addActionListener(l);
    }

    public JButton getBackButton()
    {
        return backButton;
    }

    public JButton getStopButton()
    {
        return stopButton;
    }

    public JButton getBlastButton()
    {
        return alignButton;
    }

    public final String getSummaryLines()
    {
        String inputFile = null;

        if (model.getInputFile() != null)
        {
            inputFile = "Input File: " + model.getInputFile().getAbsolutePath()
                    + "\n";
        }
        else
        {
            inputFile = "Input File: null \n";
        }

        int inputIDStart = model.getInputIDStart();
        int inputIDEnd = model.getInputIDEnd();

        int inputBLASTStart = model.getInputBLASTRegionStart();
        int inputBLASTEnd = model.getInputBLASTRegionEnd();

        String inputIDRegion = "Input ID Region: [" + (inputIDStart + 1) + ", "
                 + inputIDEnd + "] \n";

        if (inputIDStart == 0 && inputIDEnd == 0)
        {
            inputIDRegion = "Input ID Region: [" + (inputIDStart + 1) +
                    ", " + inputIDEnd + "] \n";
        }

        String inputBLASTRegion = "Input Alignment Region: [" + inputBLASTStart
                + ", " + inputBLASTEnd + "] \n";

        if (inputIDStart == 0 && inputIDEnd == 0)
        {
            inputBLASTRegion = "Input Alignment Region: [" +
                    (inputBLASTStart + 1) + ", " + inputBLASTEnd + "] \n";
        }

        String refFiles = "";

        if (model.getRefFileList().size() == 0)
        {
            refFiles = "No reference files selected.\n";
        }
        for (int i = 0; i < model.getRefFileList().size(); i++)
        {
            int j = i + 1;
            refFiles += "Reference File " + j + ": "
                    + model.getRefFileList().get(i).getAbsolutePath() + "\n";
        }

        String refColumns = "Columns selected to appear in output: ";

        for (int i = 0; i < model.getRefColumnHeaderIndex().size(); i++)
        {
            refColumns += model.getRefHeaders()
                    .get(model.getRefColumnHeaderIndex().get(i)) + " ";
        }

        refColumns += "\n";

        String uniqueID = null;

        if (model.getRefHeaders().size() > model.getRefIDColumn())
        {
            uniqueID = "Reference ID Column: "
                    + model.getRefHeaders().get(model.getRefIDColumn()) + "\n";
        }
        else
        {
            uniqueID = "No Identification Column selected";
        }


        int refRegionStart = model.getBLASTRegionStart();
        int refRegionEnd = model.getBLASTRegionEnd();

        String refBLASTRegion = "Reference BLAST Region: [" + 
                (refRegionStart + 1) + ", " + refRegionEnd + "] \n";

        if (refRegionStart == 0 && refRegionEnd == 0)
        {
            refBLASTRegion = "Reference BLAST Region: [" + refRegionStart
                + ", " + refRegionEnd + "] \n";
        }

        //String alignmentRegion = model.get

        return inputFile + inputIDRegion + inputBLASTRegion + "\n"
               + refFiles + "\n" + refColumns + uniqueID + refBLASTRegion;
    }

    public JTextArea getSummaryArea()
    {
        return summaryField;
    }
}
