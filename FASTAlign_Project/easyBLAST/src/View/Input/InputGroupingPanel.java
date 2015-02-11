package View.Input;

import Controller.GroupListener;
import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class represents the screen used for putting IDs into groups.
 * @author wjlow
 */
public class InputGroupingPanel extends JPanel
{
    private final int totalNumberOfGroups = 10;
    private JButton groupButton, addGroupButton, removeButton;
    private ModelInterface m;
    private GroupListener groupListener;
    private JTextField[] groupTextField = new JTextField[totalNumberOfGroups];
    private JPanel[] panels = new JPanel[totalNumberOfGroups];
    private JLabel[] labels = new JLabel[totalNumberOfGroups];
    private int visibleGroups = 0;

    private JButton backButton;
    private JButton nextButton;
    private JPanel centerPanel;

    public InputGroupingPanel(ModelInterface m)
    {
        this.m = m;
        this.setLayout(new BorderLayout());

        /* Add the label at the top for instructions */
        JLabel label = new JLabel("Please specify the identifications "
                + "for each group");
        JPanel northPanel = new JPanel(new FlowLayout());
        label.setFont(new Font("Sans Serif", Font.BOLD, 20));
        northPanel.add(label);
        this.add(northPanel, BorderLayout.NORTH);

        /* Add the fields in the centre for the groups */
        centerPanel = new JPanel(new GridLayout(13, 1));
        this.add(centerPanel, BorderLayout.CENTER);

        addTextFields();
        panels[0].setVisible(true);
        incrementVisibleFields();

        panels[getNoVisibleFields()].setVisible(true);
        incrementVisibleFields();

        /* Add the buttons for adding and removing groups */
        JPanel groupButtonsPanel = new JPanel(new FlowLayout());
        centerPanel.add(groupButtonsPanel);

        addGroupButton = new JButton("Add Group");
        groupButtonsPanel.add(addGroupButton);

        removeButton = new JButton("Remove Group");
        groupButtonsPanel.add(removeButton);

        /* Add an instruction */
        JLabel noteLabel1 = new JLabel(
                "Note: Separate IDs with a comma (,). \n\n" +
                "One wildcard can be used for every ID, ");
        JLabel noteLabel2 = new JLabel(
                "e.g. *ATT can be used to represent AATT, CATT, GATT " +
                "and TATT.");
        JPanel notePanel = new JPanel(new FlowLayout());
        notePanel.add(noteLabel1);
        notePanel.add(noteLabel2);
        centerPanel.add(notePanel);

        /* Add Back and Next buttons */
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');

        JPanel navigationButtonsPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT));
        navigationButtonsPanel.add(backButton);
        navigationButtonsPanel.add(nextButton);

        this.add(navigationButtonsPanel, BorderLayout.SOUTH);
    }

    /**
     * This method adds the textfields for all the ten available groups.
     * All but the first two groups are hidden.
     */
    public void addTextFields()
    {
        for (int i = 0; i < totalNumberOfGroups; i++)
        {
            int j = i + 1;

            groupTextField[i] = new JTextField(50);
            groupTextField[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
            labels[i] = new JLabel("Group " + j + ": ");
            panels[i] = new JPanel(new FlowLayout());
            centerPanel.add(panels[i]);
            panels[i].add(labels[i]);
            panels[i].add(groupTextField[i]);
            panels[i].setVisible(false);
        }
    }


    public JTextField[] getGroupTextField()
    {
        return groupTextField;
    }
    public JPanel[] getPanels()
    {
        return panels;
    }

    public JButton getAddButton()
    {
        return addGroupButton;
    }

    public JButton getGroupButton()
    {
        return groupButton;
    }

    public JButton getRemoveButton()
    {
        return removeButton;
    }

    public int getNoVisibleFields()
    {
        return visibleGroups;
    }

    public void incrementVisibleFields()
    {
        visibleGroups++;
    }

    public void decrementVisibleFields()
    {
        visibleGroups--;
    }

    public void addGroupingListener(ActionListener al)
    {
        for (int i = 0; i <  totalNumberOfGroups; i++)
        {
            groupTextField[i].addActionListener(al);
        }

        addGroupButton.addActionListener(al);
        removeButton.addActionListener(al);
        backButton.addActionListener(al);
        nextButton.addActionListener(al);
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
