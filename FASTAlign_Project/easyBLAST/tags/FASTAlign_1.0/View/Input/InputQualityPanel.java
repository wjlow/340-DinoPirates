package View.Input;

import Controller.QualityListener;
import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import Model.FileNameExtensionFilter;
import javax.swing.JSlider;

/**
 * A JPanel which allows the user to filter out DNA sequences of insufficient
 * quality.
 * @author lawrence
 */
public class InputQualityPanel extends JPanel
{
    private QualityListener qualityListener;
    private JLabel asciiStringLabel;
    private JTextField asciiSumLimit;
    private JLabel asciiMinLabel;
    private JTextField asciiMinLimit;
    private JLabel rangeMinLabel;
    private JLabel rangeSumLabel;
    private JButton saveButton;
    private JButton backButton;
    private JButton nextButton;
    private JFileChooser fileChooser;
    private ModelInterface model;

    /**
     * This constructor generates the components necessary for the View, and to
     * have it connected to a ModelInterface.
     * @param m The ModelInterface which connects this View component.
     */
    public InputQualityPanel(final ModelInterface m)
    {
        model = m;
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(5, 1));
        JPanel labelPanel = new JPanel(new FlowLayout());
        northPanel.add(labelPanel);
        this.add(northPanel, BorderLayout.NORTH);

        fileChooser = new JFileChooser();
        FileNameExtensionFilter txtFilter =
                new FileNameExtensionFilter("Input Files (*.txt)", "TXT");
        fileChooser.addChoosableFileFilter(txtFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        JPanel centerPanel = new JPanel(new GridLayout(6, 1));
        this.add(centerPanel, BorderLayout.CENTER);

        JLabel mainLabel = new JLabel("Please specify the minimum thresholds "
                + "for the following quality values of each short read: ");
        mainLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        mainLabel.setAlignmentX(JLabel.CENTER);
        labelPanel.add(mainLabel);

        JPanel asciiValuePanel = new JPanel(new FlowLayout());
        asciiMinLabel = new JLabel("ASCII Decimal Character Value:");
        rangeMinLabel = new JLabel("(Usual Range: 57 to 90)");
        asciiMinLimit = new JTextField(String.valueOf(m.getQualityASCII()), 3);
        asciiMinLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        asciiValuePanel.add(asciiMinLabel);
        asciiValuePanel.add(asciiMinLimit);
        asciiValuePanel.add(rangeMinLabel);

        JPanel sumOfAsciiValuePanel = new JPanel(new FlowLayout());
        asciiStringLabel = new JLabel("Sum of ASCII Decimal Character Values:");
        asciiStringLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        asciiSumLimit = new JTextField(String.valueOf(m.getQSum()), 3);
        rangeSumLabel = new JLabel("(Range: 2000 to 4000)");

        asciiMinLimit.setName("asciiMin");
        asciiSumLimit.setName("asciiSum");
        asciiMinLimit.addActionListener(qualityListener);
        asciiSumLimit.addActionListener(qualityListener);

        sumOfAsciiValuePanel.add(asciiStringLabel);
        sumOfAsciiValuePanel.add(asciiSumLimit);
        sumOfAsciiValuePanel.add(rangeSumLabel);

        saveButton = new JButton("Save Results");

        JPanel saveButtonPanel = new JPanel(new FlowLayout());
        saveButtonPanel.add(saveButton);

        JPanel saveLabelPanel = new JPanel(new FlowLayout());
        JLabel saveLabel = new JLabel("Note: Press 'Save Results' to save the "
                + "query file(s) after filtering.");

        saveLabelPanel.add(saveLabel);

        centerPanel.add(asciiValuePanel);
        centerPanel.add(sumOfAsciiValuePanel);
        centerPanel.add(saveButtonPanel);
        centerPanel.add(saveLabelPanel);

        this.add(centerPanel, BorderLayout.CENTER);

        // Add 'Back' button
        backButton = new JButton("Back");
        backButton.setMnemonic('B');

        // Add 'Next' button
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(backButton);
        buttonsPanel.add(nextButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * The method returns the textfield containing the asciiMin
     * @return asciiMinLimit
     */
    public JTextField getASCIIMinLimit()
    {
        return asciiMinLimit;
    }

    /**
     * asciiSumLimit containing the ascii sum
     * @return asciiSumLimit
     */
    public JTextField getASCIISumLimit()
    {
        return asciiSumLimit;
    }

    /**
     * The method returns the button save file
     * @return saveFile
     */
    public JButton getSaveFile()
    {
        return saveButton;
    }

    /**
     * The methods returns the fileChooser
     * @return fileChooser
     */
    public JFileChooser getFileChooser()
    {
        return fileChooser;
    }

    /**
     * The methods adds ActionListeners of JTextFields and JButtons in
     * the screen. ActionListeners are added for JtextField asciiMinlimit and
     * asciiSumLimit and JButton saveFile and saveChanges.
     * @param ae
     */
    public final void addQualityListener(final ActionListener ae)
    {
        backButton.addActionListener(ae);
        saveButton.addActionListener(ae);
        nextButton.addActionListener(ae);
    }

    public final void addQualityThresholdFocusListener(final FocusListener e)
    {
        asciiMinLimit.addFocusListener(e);
        asciiSumLimit.addFocusListener(e);
    }

    public JButton getNextButton()
    {
        return nextButton;
    }

    public JButton getBackButton()
    {
        return backButton;
    }
}
