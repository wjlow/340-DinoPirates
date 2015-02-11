package Controller;

import Model.ModelInterface;
import View.BLAST.AlignmentToolSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * This ActionListener listens to the user selection of the Alignment Tool
 * in the JComboBox.
 */
public class AlignmentSelectListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public AlignmentSelectListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        AlignmentToolSelection boxSelect =
                view.getBLASTPanel().getAlignmentToolSelect();

        if (e.getSource() == boxSelect.getToolsComboBox())
        {
            int index = boxSelect.getToolsComboBox().getSelectedIndex();

            model.setAlgorithmIndex(index);
        }

        if (e.getSource() == boxSelect.getNextButton())
        {
            JTabbedPane blastTabMenu = view.getBLASTPanel().getBlastTabMenu();
            blastTabMenu.setSelectedIndex(blastTabMenu.getSelectedIndex() + 1);
        }
    }

}
