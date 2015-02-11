package Controller;

import Model.ModelInterface;
import View.Input.InputGroupingPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * This GroupListener listens to the Add, Remove, Next and Back buttons in the
 * Grouping panel.
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

        if (e.getSource() == groupingPanel.getAddButton())
        {
            if (groupingPanel.getNoVisibleFields() < 10)
            {
                groupingPanel.getPanels()[groupingPanel.
                    getNoVisibleFields()].setVisible(true);
                groupingPanel.incrementVisibleFields();
            }
        }

        if (e.getSource() == groupingPanel.getRemoveButton())
        {
            if (groupingPanel.getNoVisibleFields() > 2)
            {
                groupingPanel.decrementVisibleFields();
                groupingPanel.getPanels()[groupingPanel.getNoVisibleFields()].
                        setVisible(false);
            }
        }

        if (e.getSource() == groupingPanel.getBackButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() - 1);
        }

        if (e.getSource() == groupingPanel.getNextButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            int currentIndex = inputTabMenu.getSelectedIndex();
            view.getInputPanel().getInputTabMenu()
                    .setEnabledAt(currentIndex + 1, true);
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() + 1);
        }
    }
}