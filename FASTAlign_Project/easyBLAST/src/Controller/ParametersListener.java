package Controller;

import Model.ModelInterface;
import View.BLAST.ParametersPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * This class listens to the textboxes where the quality thresholds are entered
 * and the next and back buttons. A focus listener is used to store the values
 * immediately the user mouses away from the textboxes.
 *
 * @author Jinita
 */
public class ParametersListener implements ActionListener, FocusListener
{
    private ModelInterface model;
    private View view;

    public ParametersListener(final ModelInterface model, final View view)
    {
        this.model = model;
        this.view = view;
    }

    /**
     * The methods executes actions according to the values entered and buttons
     * pressed. The asciiMin and asciiSum values are stored in the
     * ModelInterface. When ther user presses the saveResults, a file choosers
     * is displayed for the user to select a location and the name of the file.
     * @param e
     */
    public void actionPerformed(final ActionEvent e)
    {
        ParametersPanel paramPanel = view.getBLASTPanel().
                getParametersPanel();
        JTabbedPane blastTabMenu = view.getBLASTPanel().getBlastTabMenu();

        if (e.getSource() == paramPanel.getBackButton())
        {
            blastTabMenu.setSelectedIndex(blastTabMenu.getSelectedIndex() - 1);
        }

        if (e.getSource() == paramPanel.getNextButton())
        {
            /*
            JOptionPane.showMessageDialog(null,
                    "The specified parameters have now been stored.",
                    "FASTAlign", JOptionPane.PLAIN_MESSAGE); */
            blastTabMenu.setSelectedIndex(blastTabMenu.getSelectedIndex() + 1);
        }
    }

    public void focusGained(FocusEvent e)
    {
        
    }


    public void focusLost(FocusEvent e)
    {
        int numberParams = model.getListofTools().get(model.getAlgorithmIndex())
                .getAlignParameters().length;
        ParametersPanel p = view.getBLASTPanel().getParametersPanel();

        for (int i = 0; i < numberParams; i++)
        {
            if (e.getSource() == p.getJPanels()[i])
            {
                model.getListofTools().get(model.getAlgorithmIndex())
                        .getAlignParameters()[i].setValue(p.getValues(i));
            }
        }
    }
}
