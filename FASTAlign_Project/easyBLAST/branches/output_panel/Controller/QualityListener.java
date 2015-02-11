package Controller;

import Model.opencsv.CSVReader;
import Model.*;
import Model.InputLine;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class QualityListener implements ActionListener
{
    private ModelInterface model;
    private JFileChooser fileChooser = new JFileChooser();

    public QualityListener(ModelInterface model)
    {
        this.model = model;
    }

    public void actionPerformed(ActionEvent e)
    {
        parseFiles();
        removeUnwanted();
        saveFile();
    }

    /**
     * This function parses the input files, adding lines to the inputLineList
     */
    public void parseFiles()
    {
        int i = 0;
        model.getInputLineList().clear();

        if(model.getInputFile() != null)
        {
            try
            {
                CSVReader reader = new CSVReader(
                        new FileReader(model.getInputFile()), ':');
                String cutLine[] = null;
                while ((cutLine = reader.readNext()) != null)
                {
                    model.getInputLineList().add(
                            new InputLine(cutLine[0],cutLine[1],cutLine[2],
                            cutLine[3],cutLine[4],cutLine[5], cutLine[6]));
                    i++;
                }
            }
            catch(IOException ex)
            {
                System.err.println(ex);
            }
        }
        System.out.println("Read " + i + " DNA sequences to List.");
        //m.printDetailedShortReadList();
        System.out.println("removing those below "  + model.getMinASCII() + ", "
                                + model.getMinSum());
    }

    /**
     * This function removes the lines which are below the threshold
     */
    public void removeUnwanted()
    {
        int removedCount = 0;
        for(int i =0; i<model.getInputLineList().size(); i++)
        {
            if (model.getInputLineList().get(i).getQualityMin() < model.getMinASCII()
                || model.getInputLineList().get(i).getQualitySum() < model.getMinSum())
            {
                model.getInputLineList().remove(i);
                i--;
                removedCount++;
            }
        }
        System.out.println("Removed " + removedCount +
                                " DNA sequences to List.");
    }

    public void saveFile()
    {
        //String filename = m.getInputFile().getName();

        int returnValue = fileChooser.showSaveDialog(fileChooser);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            System.out.println(fileChooser.getSelectedFile());
            model.setQualitySaveLocation(fileChooser.getSelectedFile());
        }

        try {
            FileWriter f0 = new FileWriter(model.getQualitySaveLocation(), false);
            for (int i =0; i<model.getInputLineList().size(); i++)
            {
                //System.out.print(m.printInputLine(i));
                f0.write(model.printInputLine(i) + '\n');
            }
            f0.close();
        }
        catch(IOException ioe)
        {
            
        }
    }
}
