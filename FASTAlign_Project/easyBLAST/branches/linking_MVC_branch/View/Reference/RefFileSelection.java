package View.Reference;

import Model.ModelInterface;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Controller.ReferenceFileParser;

/**
 * 
 * @author lawrence
 */
public class RefFileSelection extends JPanel implements ActionListener
{
    private JTextField filePath;
    private JTextField file2Path;
    private JButton refBrowse2;
    private JButton refBrowse1;
    private JFileChooser fc;

    private final int textfieldLength = 50;

    private ModelInterface m;

    /**
     *
     * @param m
     */
    public RefFileSelection(final ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(4, 1));
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        JLabel label = new JLabel("Please select one set of query files");
        panel1.add(label);

        JPanel panel2 = new JPanel(new FlowLayout());
        add(panel2);
        filePath = new JTextField(textfieldLength);
        refBrowse1 = new JButton("Browse...");
        panel2.add(filePath);
        panel2.add(refBrowse1);

        JPanel panel3 = new JPanel(new FlowLayout());
        add(panel3);
        file2Path = new JTextField(textfieldLength);
        refBrowse2  = new JButton("Browse...");
        panel3.add(file2Path);
        panel3.add(refBrowse2);

        JPanel panel5 = new JPanel(new FlowLayout());
        add(panel5);

        fc = new JFileChooser();
        FileNameExtensionFilter csvFilter =
                new FileNameExtensionFilter("Reference Files (*.csv)", "CSV");
        fc.addChoosableFileFilter(csvFilter);
        fc.setAcceptAllFileFilterUsed(false);

        filePath.setEditable(false);
        file2Path.setEditable(false);
        refBrowse1.addActionListener(this);
        refBrowse2.addActionListener(this);
        refBrowse2.setEnabled(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == refBrowse1)
        {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                m.setRefFile(file);
                filePath.setText(m.getRefFile().getPath());
            }

            /* Store the headers of the first reference file in a list in
             * ModelInterface. */
            ReferenceFileParser refParser = new ReferenceFileParser(m);
            refParser.setReferenceHeaders();
        }
    }

/*    public final void addReferenceFileParser(ActionListener rpl)
    {
        refBrowse1.addActionListener(rpl);
    }

    public final void updateFilePath1(String string)
    {
        filePath.setText(string);
    }
*/
}
