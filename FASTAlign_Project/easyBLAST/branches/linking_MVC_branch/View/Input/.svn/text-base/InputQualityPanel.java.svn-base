package View.Input;

import Controller.QualityListener;
import Model.ModelInterface;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
//import javax.swing.event.ChangeListener;

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
    private JButton saveFile;
    private JFileChooser fileChooser;
    private JButton saveChanges;
    private ModelInterface model;

    /**
     * This constructor generates the components necessary for the View, and to
     * have it connected to a ModelInterface.
     * @param m The ModelInterface which connects this View component.
     */
    public InputQualityPanel(final ModelInterface m)
    {
        this.model = m;
        setLayout(new GridLayout(4, 1));

        fileChooser = new JFileChooser();
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        JLabel mainLabel = new JLabel("Please specify the minimum thresholds " +
                "for the following quality values of each short read: ");
        panel1.add(mainLabel);

        JPanel panel2 = new JPanel(new FlowLayout());
        add(panel2);
        asciiMinLabel = new JLabel("ASCII Decimal Character Value:");
        rangeMinLabel = new JLabel("(Range: 57 to 90)");
        asciiMinLimit = new JTextField(m.getMinASCII().toString(), 3);
        panel2.add(asciiMinLabel);
        panel2.add(asciiMinLimit);
        panel2.add(rangeMinLabel);

        JPanel panel3 = new JPanel(new FlowLayout());
        add(panel3);
        asciiStringLabel = new JLabel("Sum of ASCII Decimal Character Values:");
        asciiSumLimit = new JTextField(m.getMinSum().toString(), 3);
        rangeSumLabel = new JLabel("Range: 2000 to 3000");

        asciiMinLimit.setName("asciiMin");
        asciiSumLimit.setName("asciiSum");
        asciiMinLimit.addActionListener(qualityListener);
        asciiSumLimit.addActionListener(qualityListener);

        panel3.add(asciiStringLabel);
        panel3.add(asciiSumLimit);
        panel3.add(rangeSumLabel);

        JPanel panel4 = new JPanel(new FlowLayout());
        add(panel4);

        saveChanges = new JButton("Store Thresholds");
        //removeBelowThreshold = new JButton("Trim!");
        saveFile = new JButton("Save Results");

        saveChanges.addActionListener(qualityListener);

        //panel4.add(removeBelowThreshold);
        panel4.add(saveChanges);
        panel4.add(saveFile);

       // removeBelowThreshold.addActionListener(this);
        //saveFile.addActionListener(this);
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
     * The method returns the button Store thresholds
     * @return saveChanges
     */
    public JButton getSaveChanges()
    {
        return saveChanges;
    }

    /**
     * The method returns the button save file
     * @return saveFile
     */
    public JButton getSaveFile()
    {
        return saveFile;
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
        System.out.println("I am in addQualityListener");
        asciiMinLimit.addActionListener(ae);
        asciiSumLimit.addActionListener(ae);
        saveFile.addActionListener(ae);
        saveChanges.addActionListener(ae);
    }
}