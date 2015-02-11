package Controller;

import Model.ModelInterface;
import View.Reference.RefColumnChooser;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * The class is responsible for storing the header selected by the user
 * to appear in the output . The user selects headers by selecting the
 * checkboxes. This class listens to the checkboxes and stores the integer
 * associated with the header.
 *
 * @author wengn
 */
public class RefColumnHeaderChooserListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public RefColumnHeaderChooserListener(ModelInterface model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        model.getRefColumnHeaderIndex().clear();

        RefColumnChooser refCol =
                view.getReferencePanel().getRefColumnChooser();

        for (int i = 0; i < refCol.getCheckBox().length; i++)
        {
            if (e.getSource() == refCol.getCheckBox()[i])
            {
                if (refCol.getCheckBox()[i].isSelected())
                {
                    model.getRefColumnHeaderIndex().add(new Integer(i));
                }
                else
                {
                    if (model.getRefColumnHeaderIndex().
                            contains(new Integer(i)))
                    {
                        model.getRefColumnHeaderIndex().remove(new Integer(i));
                    }
                }
            }
        }

        if (e.getSource() == refCol.getBackButton())
        {
            JTabbedPane refTabPane = view.getReferencePanel().getRefTabPane();
            refTabPane.setSelectedIndex(refTabPane.getSelectedIndex() - 1);
        }

        if (e.getSource() == refCol.getNextButton())
        {
            JTabbedPane refTabPane = view.getReferencePanel().getRefTabPane();
            refTabPane.setSelectedIndex(refTabPane.getSelectedIndex() + 1);
        }
    }
}
