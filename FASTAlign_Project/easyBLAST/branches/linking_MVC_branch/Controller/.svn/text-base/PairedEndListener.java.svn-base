package Controller;

import Model.ModelInterface;
import View.View;
import View.Input.InputFileSelection;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * This ChangeListener listens for the user selecting the paired end checkbox.
 * @author lawrence
 */
public class PairedEndListener implements ChangeListener
{
    private ModelInterface model;
    private View view;

    /**
     * The constructor takes in a ModelInterface and a View and grants it access
     * from the object.
     * @param m The ModelInterface which the object will have access to.
     * @param v The View which the object will have access to.
     */
    public PairedEndListener(final ModelInterface m, final View v)
    {
        model = m;
        view = v;
    }

    public void stateChanged(ChangeEvent e)
    {
        InputFileSelection inputFileSelection =
                view.getInputPanel().getInputFileSelection();

        if (e.getSource() == inputFileSelection.getPairedEnd())
        {
            if (inputFileSelection.getPairedEnd().isSelected())
            {
                System.out.println("Paired end file selected");
                inputFileSelection.getInputSelector2().setEnabled(true);
                model.setPairedEnd(true);
            }
            else
            {
                System.out.println("Paired end file NOT selected");
                inputFileSelection.getInputSelector2().setEnabled(false);
                model.setPairedEndFile(null);
                model.setPairedEnd(false);
                inputFileSelection.getFilePath2().setText("");
            }
        }
    }
}
