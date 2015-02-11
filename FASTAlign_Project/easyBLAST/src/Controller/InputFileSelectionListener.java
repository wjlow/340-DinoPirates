package Controller;

import Model.ModelInterface;
import Model.opencsv.CSVReader;
import View.Input.InputFileSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * This ActionListener listens to the input file selected from the JFileChooser.
 * @author wengn
 */
public class InputFileSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;
    private CSVReader reader;

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

                String [] line = null;
                try
                {
                    reader = new CSVReader(new FileReader(inputFile), ':');
                    try
                    {
                        if ((line = reader.readNext()) == null
                                || line.length != 7)
                        {
                            JOptionPane.showMessageDialog(null, "The file is "
                                    + "not fomatted correctly. Please select "
                                    + "another file", "FASTAlign",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            model.setInputFile(inputFile);
                            inputFileSelection.setFilePath1(inputFile.
                                    getPath());

                            // Enable other tabs once the user has
                            //selected one file.
                           view.getInputPanel().getInputTabMenu().
                                    setEnabledAt(1, true);
                           if (!model.isBLASTMode())
                           {
                                view.getInputPanel().getInputTabMenu().
                                   setEnabledAt(2, true);
                           }

                            if (!model.isBLASTMode())
                            {
                                view.getInputPanel().getInputTabMenu().
                                        setEnabledAt(3, true);
                                view.getInputPanel().getInputTabMenu().
                                        setEnabledAt(4, true);
                            }
                            else if (model.isBLASTMode())
                            {
                                view.getInputPanel().getInputRegionSelection().
                                        generatePairedEndTextBoxes();
                                view.getInputPanel().getInputTabMenu().
                                   setEnabledAt(2, true);
                            }

                            view.getInputPanel().getInputIDSelection().
                                    generatePairedEndTextBoxes();

                            view.fillInTextBoxes();

                            view.getInputPanel().getInputFileSelection().
                                    getNextButton().setEnabled(true);
                        }
                    }
                    catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.toString(),
                                "FASTAlign", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (FileNotFoundException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.toString(),
                            "FASTAlign", JOptionPane.ERROR_MESSAGE);
                }
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

                String [] line = null;
                try
                {
                    reader = new CSVReader(new FileReader(pairedEndFile), ':');
                    try
                    {
                        if ((line = reader.readNext()) == null
                                || line.length != 7)
                        {
                            JOptionPane.showMessageDialog(null, "The file "
                                    + "cannot be read. "
                                    + "Please select another file",
                                    "FASTAlign", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            model.setPairedEndFile(pairedEndFile);
                            inputFileSelection.getFilePath2().
                                   setText(model.getPairedEndFile().getPath());

                            view.getInputPanel().getInputIDSelection().
                                    generatePairedEndTextBoxes();

                            view.getInputPanel().getInputTabMenu().
                                    setEnabledAt(1, true);
                            view.getInputPanel().getInputTabMenu().
                                    setEnabledAt(2, true);
                            view.getInputPanel().getInputTabMenu().
                                    setEnabledAt(3, true);
                            view.getInputPanel().getInputTabMenu().
                                    setEnabledAt(4, true);

                            view.getInputPanel().getInputFileSelection()
                                    .getNextButton().setEnabled(true);

                            view.fillInTextBoxes();

                            model.setInputIDRegion(0, 0);

                            view.getInputPanel().getInputFileSelection().
                                    repaint();
                        }
                    }
                     catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.toString(),
                                "FASTAlign", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (FileNotFoundException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.toString(),
                            "FASTAlign", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == inputFileSelection.getNextButton())
        {
            if((model.isPairedEnd()) && (model.getPairedEndFile() == null))
            {
                JOptionPane.showMessageDialog(null, "Please select a paired end"
                        + "file" ,"FASTAlign", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JTabbedPane inputTabMenu = view.getInputPanel().
                         getInputTabMenu();
                inputTabMenu.setSelectedIndex(inputTabMenu.
                        getSelectedIndex() + 1);

                view.getInputPanel().getInputTabMenu().
                        setEnabledAt(1, true);
                view.getInputPanel().getInputTabMenu().
                        setEnabledAt(2, true);

                if (!model.isBLASTMode())
                {
                    view.getInputPanel().getInputTabMenu().
                            setEnabledAt(3, true);
                    view.getInputPanel().getInputTabMenu().
                            setEnabledAt(4, true);
                }

                view.getInputPanel().getInputFileSelection()
                                    .getNextButton().setEnabled(true);
            }
        }
    }
}
