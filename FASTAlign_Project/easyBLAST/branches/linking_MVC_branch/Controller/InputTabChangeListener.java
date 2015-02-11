package Controller;

import Model.ModelInterface;
import View.View;
import java.util.List;
import javax.swing.event.*;
import javax.swing.*;

/**
 * 
 * @author lawrence
 */
public class InputTabChangeListener implements ChangeListener
{
    private ModelInterface model;
    private View view;

    /**
     *
     * @param m The ModelInterface that the ChangeListener has access to.
     */
    public InputTabChangeListener(final ModelInterface m)
    {
        this.model = m;
    }

    /**
     *
     * @param m The ModelInterface that the ChangeListener has access to.
     * @param v The View that the ChangeListener has access to.
     */
    public InputTabChangeListener(final ModelInterface m, final View v)
    {
        this.model = m;
        this.view = v;
    }

    public void stateChanged(ChangeEvent event)
    {
        String currentTab = ((JTabbedPane) event.getSource()).getTitleAt(
                ((JTabbedPane) event.getSource()).getSelectedIndex());

        if (currentTab.equals("ID Selection") ||
                currentTab.equals("Region Selection"))
        {
          //  InputFileParser ifp = new InputFileParser(m, 5);
          //  ifp.actionPerformed(null);

            //System.out.println(model.getHasGeneratedPairedEndTextBoxes());

            view.getInputPanel().getInputIDSelection().
                    generatePairedEndTextBoxes();
            view.getInputPanel().getInputRegionSelection().
                    generatePairedEndTextBoxes();

            view.fillInTextBoxes();
        }

        if (currentTab.equals("Input End"))
        {
            InputFileParser ifp = new InputFileParser(model);
            ifp.actionPerformed(null);
        }
    }
}
