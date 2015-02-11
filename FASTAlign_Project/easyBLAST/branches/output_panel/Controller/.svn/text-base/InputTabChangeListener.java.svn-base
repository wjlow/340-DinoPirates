package Controller;

import Model.*;
import View.*;
import javax.swing.event.*;
import javax.swing.*;

public class InputTabChangeListener implements ChangeListener
{
    ModelInterface m;
    View v;

    public InputTabChangeListener(ModelInterface m)
    {
        this.m = m;
    }

    public InputTabChangeListener(ModelInterface m, View v)
    {
        this.m = m;
        this.v = v;
    }

    public void stateChanged(ChangeEvent event)
    {
        String currentTab = ((JTabbedPane)event.getSource()).
                getTitleAt(((JTabbedPane)event.getSource()).getSelectedIndex());
        
        if(currentTab.equals("ID Selection") || currentTab.equals("Region Selection"))
        {
          //  InputFileParser ifp = new InputFileParser(m, 5);
          //  ifp.actionPerformed(null);

            v.fillInValues();
        }

        if(currentTab.equals("Input End"))
        {
            InputFileParser ifp = new InputFileParser(m);
            ifp.actionPerformed(null);
        }
    }
}
