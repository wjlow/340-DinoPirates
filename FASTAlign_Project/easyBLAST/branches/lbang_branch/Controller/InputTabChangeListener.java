package Controller;

import Model.*;
import javax.swing.event.*;
import javax.swing.*;

public class InputTabChangeListener implements ChangeListener
{
    Model m;

    public InputTabChangeListener(Model m)
    {
        this.m = m;
    }

    public void stateChanged(ChangeEvent e)
    {
        if(((JTabbedPane)e.getSource()).getSelectedIndex() == 2
                ||((JTabbedPane)e.getSource()).getSelectedIndex() == 3)
        {
            //function to read the first XX lines from input files.
        }
    }
}
