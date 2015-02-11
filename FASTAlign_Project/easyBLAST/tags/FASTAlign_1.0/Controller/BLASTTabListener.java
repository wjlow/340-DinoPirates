package Controller;

import Model.ModelInterface;
import View.View;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTabbedPane;

/**
 * This ChangeListener listens to tabs changing on the Alignment Panel.
 * @author wengn
 */
public class BLASTTabListener implements ChangeListener
{
    private ModelInterface model;
    private View view;

    private boolean[] count = {true, true, true};


    public BLASTTabListener(ModelInterface m, View v)
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


        if (currentTab.equals("Parameters Option"))
        {
            System.out.println("inhere");
        }

        if (currentTab.equals("Multi-threading BLAST"))
        {
            String newText = view.getBLASTPanel().getQueryBLASTPanel()
                    .getSummaryLines();
            view.getBLASTPanel().getQueryBLASTPanel().getSummaryArea()
                    .setText(newText);
        }
    }

    public void resetCount()
    {
        for (int i = 0; i < count.length; i++)
        {
            count[i] = false;
        }
    }
}
