/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ModelInterface;
import View.Input.InputGroupingPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpatel
 */
public class GroupListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public GroupListener(final ModelInterface m, final View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        InputGroupingPanel groupingPanel = view.getInputPanel()
                .getInputGroupingPanel();

        if (e.getSource()== groupingPanel.getAddButton())
        {
            if (groupingPanel.getNoVisibleFields() < 10)
            {
                groupingPanel.getPanels()[groupingPanel.
                    getNoVisibleFields()].setVisible(true);

                System.out.println("Add group Button " + groupingPanel
                        .getNoVisibleFields());
                System.out.println("State: " + groupingPanel.getPanels()
                        [groupingPanel.getNoVisibleFields()].isVisible());
                groupingPanel.incrementVisibleFields();
            }
        }
        
        if(e.getSource() == groupingPanel.getGroupButton())
        {
            System.out.println("Group Button");

            int numberOfGroups = groupingPanel.getNoVisibleFields();
            List<List<String>> groups = new ArrayList<List<String>>();

            for(int i = 0; i<numberOfGroups; i++)
            {
                String iDs = groupingPanel.getGroupTextField()[i].getText();
                String[] iDList = iDs.split(",");

                List<String> tempIDs = new ArrayList<String>();
                
                for(int j = 0; j<iDList.length; j++)
                {
                    tempIDs.add(iDList[j]);
                    //System.out.println("Group" + i + " String: " + tempIDs.get(j));
                }
                groups.add(tempIDs);
            }
            model.callMakeGroups(groups);
        }
        if(e.getSource() == groupingPanel.getRemoveButton())
        {
            if(groupingPanel.getNoVisibleFields() > 1)
            {
                groupingPanel.decrementVisibleFields();
                groupingPanel.getPanels()[groupingPanel.getNoVisibleFields()].
                        setVisible(false);
            }
        }
    }
}
