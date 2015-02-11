package Controller;

import Model.ModelInterface;
import View.Reference.RefIDRegionSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * The class listens to Column Header Selection tab which is in the Reference
 * Files tab. It listens to the drop down boxes to obtain the Unique ID and
 * Full Hairpin Sequence.
 * 
 * @author lawrence
 */
public class ReferenceHeaderSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public ReferenceHeaderSelectionListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        RefIDRegionSelection header =
                view.getReferencePanel().getRefIDRegionSelection();

        if (e.getSource() == header.getRegionCheckbox())
        {
            model.setRefRegionColumn(
                    header.getRegionCheckbox().getSelectedIndex());

            view.getReferencePanel().getRefTabPane().setEnabledAt(3, true);
            view.getReferencePanel().
                    getRefBLASTRegionSelection().showPreviewSequences();
        }

        if (e.getSource() == header.getIDCheckbox())
        {
            model.setRefIDColumn(header.getIDCheckbox().getSelectedIndex());

            view.getReferencePanel().getRefTabPane().setEnabledAt(3, true);
            view.getReferencePanel().
                    getRefBLASTRegionSelection().showPreviewSequences();
        }

        if (e.getSource() == header.getBackButton())
        {
            JTabbedPane refTabPane = view.getReferencePanel().getRefTabPane();
            refTabPane.setSelectedIndex(refTabPane.getSelectedIndex() - 1);
        }

        if (e.getSource() == header.getNextButton())
        {
            JTabbedPane refTabPane = view.getReferencePanel().getRefTabPane();
            refTabPane.setSelectedIndex(refTabPane.getSelectedIndex() + 1);
        }
    }
}
