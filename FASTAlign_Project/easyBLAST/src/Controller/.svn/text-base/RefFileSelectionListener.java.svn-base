package Controller;

import Model.ModelInterface;
import Model.opencsv.CSVReader;
import View.Reference.RefFileSelection;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import Model.FileNameExtensionFilter;
import java.util.ArrayList;


/**
 * The class retrieves the reference files selected by the user. If the files
 * are readable and of correct format, they are added to a list in the model.
 *
 * @author Jack
 */
public class RefFileSelectionListener implements ActionListener
{
    private ModelInterface model;
    private View view;
    private CSVReader reader;

    private JFileChooser fc;
    private final int totalNumberOfRefFiles = 5;
    ArrayList<File> refFileList = new ArrayList<File>();
    private int firstRun = 0;

    public RefFileSelectionListener(final ModelInterface m, final View v)
    {
        model = m;
        view = v;

        fc = new JFileChooser();
        FileNameExtensionFilter csvFilter =
                new FileNameExtensionFilter("Reference Files (*.csv)", "CSV");
        fc.addChoosableFileFilter(csvFilter);
        fc.setAcceptAllFileFilterUsed(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        boolean found = false;
        RefFileSelection refFileSelection =
                view.getReferencePanel().getRefFileSelection();
        JButton[] refBrowseButtons = refFileSelection.getRefBrowseButtons();

        for (int i = 0; i < refBrowseButtons.length; i++)
        {
            if (e.getSource() == refBrowseButtons[i])
            {
                int returnVal = fc.showOpenDialog(refFileSelection);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    if (!file.canRead())
                    {
                        JOptionPane.showMessageDialog(null, "The file cannot be"
                            + " read. " + "Please select another file",
                           "FASTAlign", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        for(int j = 0; j < refFileList.size(); j++)
                        {
                            if(file.getPath().compareTo(refFileList.get(j)
                                    .getPath()) == 0)
                            {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                        {
                            if(refFileList.size() > i &&
                                    refFileList.get(i) != null)
                            {
                                refFileList.remove(i);
                            }

                            refFileList.add(i, file);
                            refFileSelection.getRefFilePaths()[i].setText(file
                                    .getPath());
                           refFileSelection.getNextButton().setEnabled(true);
                           JTabbedPane refTabMenu = view.getReferencePanel()
                                   .getRefTabPane();
                           refTabMenu.setEnabledAt(1, true);
                           
                           model.setReferenceFileList(refFileList);

                           if(i==0)
                           {
                               view.getReferencePanel().getRefColumnChooser()
                                       .removeAllCheckboxes();
                               refFileSelection.getReferenceFileParser()
                                       .setReferenceHeaders();
                           }
                           
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "The file has "
                            + "previously been selected. "
                            + "Please select another file.",
                           "FASTAlign", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
        if (e.getSource() == refFileSelection.getRemoveButton())
         {
            if (refFileSelection.getVisibleRefPaths() > 1)
            {
                refFileSelection.setTextFieldInVisible();
                if (refFileList.size()==
                        refFileSelection.getVisibleRefPaths())
                {

                    refFileList.remove(refFileSelection.
                            getVisibleRefPaths() -1);
                    System.out.println("I am removing file\t Size = " +
                            refFileList.size());
                }
            }
         }

        if (e.getSource() == refFileSelection.getAddButton())
        {
            if (refFileSelection.getVisibleRefPaths() < totalNumberOfRefFiles)
            {
                refFileSelection.setTextFieldVisible();
            }
        }
        if (e.getSource() == refFileSelection.getNextButton())
        {
            JTabbedPane refTabMenu = view.getReferencePanel().getRefTabPane();
            refTabMenu.setSelectedIndex(refTabMenu.getSelectedIndex() + 1);
        }
    }
}
