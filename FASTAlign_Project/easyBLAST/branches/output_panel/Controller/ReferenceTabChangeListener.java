package Controller;

import Model.*;
import View.*;
import javax.swing.event.*;
import javax.swing.*;

public class ReferenceTabChangeListener implements ChangeListener
{
    ModelInterface model;
    View view;

    public ReferenceTabChangeListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    /**
     * Does specific functions based on the String title of the current tab.
     * @param event
     */
    public void stateChanged(ChangeEvent event)
    {
        String currentTab = ((JTabbedPane)event.getSource()).
                getTitleAt(((JTabbedPane)event.getSource()).getSelectedIndex());

        if(currentTab.equals("Column Chooser"))
        {
            view.addReferenceCheckBoxes();
        }

        if(currentTab.equals("ID Selection"))
        {
            view.fillInRefDropDown();
        }

        if(currentTab.equals("BLAST Selection"))
        {
            view.refShowPreviewSequences();
        }
    }
}
