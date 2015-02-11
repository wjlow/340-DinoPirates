package Controller;

import Model.ModelInterface;
import View.View;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTabbedPane;

/**
 * The class is resposible for listening and executing actions when the user
 * changes to the reference tab.
 * @author lawrence
 */
public class ReferenceTabChangeListener implements ChangeListener
{
    private ModelInterface model;
    private View view;

    private boolean[] count = {true, true, true};


    public ReferenceTabChangeListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    /**
     * Does specific functions based on the String title of the current tab.
     */
    public void stateChanged(ChangeEvent event)
    {
        String currentTab = ((JTabbedPane) event.getSource()).getTitleAt(
                ((JTabbedPane) event.getSource()).getSelectedIndex());

//        if (currentTab.equals("Column Chooser"))
//        {
//            view.getReferencePanel().getRefFileSelection().
//                getReferenceFileParser().setReferenceHeaders();
//        }
    }

    public void resetCount()
    {
        for (int i = 0; i < count.length; i++)
        {
            count[i] = false;
        }
    }
}
