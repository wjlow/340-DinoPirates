package View.Reference;

import Model.ModelInterface;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import Controller.ReferenceFileParser;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

/**
 * This RefFileSelection panel contains the structure and layout of the panel.
 * @author lbang
 */
public class RefFileSelection extends JPanel
{
    private final int totalNumberOfRefFiles = 5;
    private JPanel filesPanel;
    private JPanel [] panels = new JPanel[totalNumberOfRefFiles];
    private JLabel[] labels = new JLabel[totalNumberOfRefFiles];
    private JButton[] refBrowseButtons = new JButton[totalNumberOfRefFiles];
    private JTextField[] refFilePaths = new JTextField[totalNumberOfRefFiles];
    private ReferenceFileParser referenceFileParser;

    private JButton removeButton;
    private JButton addButton, nextButton;
    private final int textfieldLength = 40;
    private int visibleRefPaths = 0;

    private ModelInterface model;

    public RefFileSelection(final ModelInterface model)
    {
        this.model = model;

        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1, BorderLayout.NORTH);
        JLabel label = new JLabel("Please select a set of reference files to"
                + " align against");
        label.setFont(new Font("Sans Serif", Font.BOLD, 18));
        panel1.add(label);

        filesPanel = new JPanel(new GridLayout(totalNumberOfRefFiles + 1, 2));
        add(filesPanel, BorderLayout.CENTER);

        for (int i = 0; i < totalNumberOfRefFiles; i++)
        {
            int j = i + 1;
            refFilePaths[i] = new JTextField(textfieldLength);
            labels[i] = new JLabel("Reference File " + j + ": ");
            refBrowseButtons[i] = new JButton("Browse...");
            refFilePaths[i].setEditable(false);
            panels[i] = new JPanel(new FlowLayout());
            panels[i].add(labels[i]);
            panels[i].add(refFilePaths[i]);
            panels[i].add(refBrowseButtons[i]);
            panels[i].setVisible(false);
            filesPanel.add(panels[i]);
        }

        setTextFieldVisible();

        JPanel panel2 = new JPanel(new FlowLayout());
        addButton = new JButton("Add Reference File");
        removeButton = new JButton("Remove Reference File");
        panel2.add(addButton);
        panel2.add(removeButton);
        filesPanel.add(panel2);

        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel3.add(nextButton);
        add(panel3, BorderLayout.SOUTH);
        nextButton.setEnabled(false);
    }


    public void incrementVisibleFields()
    {
        visibleRefPaths++;
    }

    public void decrementalVisibleFields()
    {
        visibleRefPaths--;
    }

    public void setTextFieldVisible()
    {
        panels[visibleRefPaths].setVisible(true);
        incrementVisibleFields();
    }

    public void setTextFieldInVisible()
    {
        decrementalVisibleFields();
        panels[visibleRefPaths].setVisible(false);
    }

    public void setRefFileParser(ReferenceFileParser rfp)
    {
        referenceFileParser = rfp;
    }

    public JButton getNextButton()
    {
        return nextButton;
    }

    public JButton getRemoveButton()
    {
        return removeButton;
    }

    public JButton getAddButton()
    {
        return addButton;
    }

    public JButton[] getRefBrowseButtons()
    {
        return refBrowseButtons;
    }

    public JTextField[] getRefFilePaths()
    {
        return refFilePaths;
    }

    public ReferenceFileParser getReferenceFileParser()
    {
        return referenceFileParser;
    }

    public int getVisibleRefPaths()
    {
        return visibleRefPaths;
    }

    public void addRefFileSelectionListener(ActionListener al)
    {
        addButton.addActionListener(al);
        removeButton.addActionListener(al);
        nextButton.addActionListener(al);

        for (int i = 0; i < totalNumberOfRefFiles; i++)
        {
            refBrowseButtons[i].addActionListener(al);

        }
    }
}
