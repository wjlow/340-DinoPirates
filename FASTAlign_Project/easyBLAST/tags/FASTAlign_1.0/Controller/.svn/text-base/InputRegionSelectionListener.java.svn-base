package Controller;

import Model.ModelInterface;
import View.Input.InputRegionSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * This ActionListener listens to the user selection of the region.
 * @author Jack
 */
public class InputRegionSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public InputRegionSelectionListener(ModelInterface model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        InputRegionSelection inputRegionSelection = view.getInputPanel().
                getInputRegionSelection();

        if (e.getSource() == inputRegionSelection.getBackButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() - 1);
        }

        if (e.getSource() == inputRegionSelection.getNextButton())
        {
            // Move to next Main tab
            JTabbedPane mainTabMenu = view.getMainTabMenu();
            mainTabMenu.setSelectedIndex(mainTabMenu.getSelectedIndex() + 1);
        }
    }

}
