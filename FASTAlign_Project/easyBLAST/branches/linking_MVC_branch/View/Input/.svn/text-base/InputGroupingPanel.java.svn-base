package View.Input;

import Controller.GroupListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;

public class InputGroupingPanel extends JPanel
{
    final int totalNumberOfGroups = 10;
    private JButton group_button, add_Group, remove_button;
    private ModelInterface m;
    private GroupListener groupListener;
    private JTextField[] groupTextField = new JTextField[totalNumberOfGroups];
    private JPanel[] panels = new JPanel[totalNumberOfGroups];
    private JLabel[] labels = new JLabel[totalNumberOfGroups];
    private int visibleGroups = 0;

    public InputGroupingPanel(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(12,1));

        addTextFields();
        panels[0].setVisible(true);
        incrementVisibleFields();

        JPanel panel11 = new JPanel(new FlowLayout());
        add(panel11);
        add_Group = new JButton("Add Group");
        add_Group.addActionListener(groupListener);
        panel11.add(add_Group);

        group_button = new JButton("Group");
        group_button.addActionListener(groupListener);
        panel11.add(group_button);

        remove_button = new JButton("Remove Groups");
        //remove_button.addActionListener(groupListener);
        panel11.add(remove_button);
    }

    public void addTextFields()
    {
        System.out.println("In addTextFields");
        for(int i = 0; i<totalNumberOfGroups; i++)
        {
            int j = i+1;
            
            groupTextField[i] = new JTextField(50);
            labels[i] = new JLabel("Group " + j + ": ");
            panels[i] = new JPanel(new FlowLayout());
            add(panels[i]);
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
        return add_Group;
    }

    public JButton getGroupButton()
    {
        return group_button;
    }

    public JButton getRemoveButton()
    {
        return remove_button;
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
        for(int i = 0; i< totalNumberOfGroups; i++)
        {
            groupTextField[i].addActionListener(al);
        }

        add_Group.addActionListener(al);
        group_button.addActionListener(al);
        remove_button.addActionListener(al);
    }
}
