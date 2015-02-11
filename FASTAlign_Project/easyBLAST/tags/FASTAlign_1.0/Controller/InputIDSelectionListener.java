package Controller;

import Model.ModelInterface;
import View.Input.InputIDSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * This ActionListener listens to the mouse selection on InputIDSelection
 * panel
 * @author Jack
 */
public class InputIDSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public InputIDSelectionListener(ModelInterface model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        InputIDSelection inputIDSelection = view.getInputPanel().
                getInputIDSelection();

        if (e.getSource() == inputIDSelection.getBackButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() - 1);
        }

        if (e.getSource() == inputIDSelection.getNextButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            int currentIndex = inputTabMenu.getSelectedIndex();

            view.getInputPanel().getInputTabMenu()
                    .setEnabledAt(currentIndex + 1, true);
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() + 1);

        }
    }
}
