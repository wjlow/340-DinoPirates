package Controller;

import Model.ModelInterface;
import View.Input.InputFileSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author jpatel
 */
public class InputFileSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    /**
     * 
     * @param m
     * @param v
     */
    public InputFileSelectionListener(final ModelInterface m, final View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        InputFileSelection inputFileSelection =
                view.getInputPanel().getInputFileSelection();

        if (e.getSource() == inputFileSelection.getInputSelector1())
        {
            int returnVal = inputFileSelection.
                    getFileChooser().showOpenDialog(inputFileSelection);

            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File inputFile =
                        inputFileSelection.getFileChooser().getSelectedFile();
                model.setInputFile(inputFile);
                inputFileSelection.setFilePath1(inputFile.getPath());
                System.out.println(inputFile.getPath()
                        + " is chosen for the input file.");
            }
        }

        if (e.getSource() == inputFileSelection.getInputSelector2())
        {
            int returnVal = inputFileSelection.
                    getFileChooser().showOpenDialog(inputFileSelection);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File pairedEndFile =
                        inputFileSelection.getFileChooser().getSelectedFile();
                model.setPairedEndFile(pairedEndFile);
                inputFileSelection.getFilePath2().
                       setText(model.getPairedEndFile().getPath());
                System.out.println(model.getPairedEndFile().getPath()
                        + " is chosen for the paired end file.");
            }
        }
    }
}
