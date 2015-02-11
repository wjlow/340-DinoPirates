package Controller;

import Model.ModelInterface;
import View.Reference.RefBLASTRegionSelection;
import View.View;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

/**
 * This class is responsible for listening to the Back button displayed on the
 * BLAST Region screen. The action listener will return to Column Header
 * Selection tab.
 *
 * @author Jack
 */
public class RefBLASTRegionSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public RefBLASTRegionSelectionListener(ModelInterface model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        RefBLASTRegionSelection refBLASTRegionSelection =
                view.getReferencePanel().getRefBLASTRegionSelection();

        if (e.getSource() == refBLASTRegionSelection.getBackButton())
        {
            JTabbedPane refTabPane = view.getReferencePanel().getRefTabPane();
            refTabPane.setSelectedIndex(refTabPane.getSelectedIndex() - 1);
        }

        if (e.getSource() == refBLASTRegionSelection.getNextButton())
        {
            // Move to next Main tab
            JTabbedPane mainTabMenu = view.getMainTabMenu();
            mainTabMenu.setSelectedIndex(mainTabMenu.getSelectedIndex() + 1);
        }
    }
}
