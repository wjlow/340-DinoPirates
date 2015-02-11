package Controller;

import Model.ModelInterface;
import View.Input.InputQualityPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * This class listens to the textboxes where the quality thresholds are entered
 * and the next and back buttons. A focus listener is used to store the values
 * immediately the user mouses away from the textboxes.
 *
 * @author Jinita
 */
public class QualityListener implements ActionListener, FocusListener
{
    private ModelInterface model;
    private View view;

    public QualityListener(final ModelInterface model, final View view)
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
        InputQualityPanel qualityPanel = view.getInputPanel().
                getInputQualityPanel();

        if (e.getSource() == qualityPanel.getSaveFile())
        {
            JOptionPane.showMessageDialog(qualityPanel,
                    "A quality filtered query file will be generated at the "
                    + "end of the flow.", "FASTAlign",
                    JOptionPane.PLAIN_MESSAGE);
            model.setSaveQualityFile(true);
        }

        if (e.getSource() == qualityPanel.getBackButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() - 1);
        }

        if (e.getSource() == qualityPanel.getNextButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() + 1);
        }
    }

    public void focusGained(FocusEvent e)
    {
       // System.out.println("Focus Gained");
    }


    public void focusLost(FocusEvent e)
    {
        InputQualityPanel qualityPanel = view.getInputPanel().
                getInputQualityPanel();

        if (e.getSource() == qualityPanel.getASCIIMinLimit())
        {
            if (!Pattern.matches("[0-9]+", qualityPanel.getASCIIMinLimit().
                    getText()))
            {
                 JOptionPane.showMessageDialog(null, "Letters or characters are"
                        + " not accepted. Please re-enter the values ",
                           "FASTAlign", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int qASCII  = Integer.parseInt(qualityPanel
                    .getASCIIMinLimit().getText());
                model.setQualityASCII(qASCII);
            }
        }

        if (e.getSource() == qualityPanel.getASCIISumLimit())
        {
            if (!Pattern.matches("[0-9]+", qualityPanel.getASCIISumLimit().
                    getText()))
            {
                 JOptionPane.showMessageDialog(null, "Letters or characters are"
                        + " not accepted. Please re-enter the values ",
                           "easyBLAST", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int qSum = Integer.parseInt(qualityPanel
                     .getASCIISumLimit().getText());
                model.setQualitySum(qSum);
            }
        }
    }
}
