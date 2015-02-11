package Controller;

import Model.*;
import View.*;
import javax.swing.event.*;
import javax.swing.*;

public class InputTabChangeListener implements ChangeListener
{
    Model m;
    View v;

    public InputTabChangeListener(Model m)
    {
        this.m = m;
    }

    public InputTabChangeListener(Model m, View v)
    {
        this.m = m;
        this.v = v;
    }


    public void stateChanged(ChangeEvent e)
    {
        if(((JTabbedPane)e.getSource()).getTitleAt(((JTabbedPane)e.getSource()).getSelectedIndex()).equals("ID Selection"))
        {
            InputFileParser ifp = new InputFileParser(m, 5);
            ifp.actionPerformed(null);

            v.fillInValues();
        }

        if(((JTabbedPane)e.getSource()).getTitleAt(((JTabbedPane)e.getSource()).getSelectedIndex()).equals("Input End"))
        {
            InputFileParser ifp = new InputFileParser(m);
            ifp.actionPerformed(null);
        }
    }
}
