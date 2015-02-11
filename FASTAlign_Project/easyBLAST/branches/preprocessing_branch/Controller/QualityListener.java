package Controller;

import Model.opencsv.CSVReader;
import Model.*;
import Model.InputLine;
import java.awt.event.*;
import java.io.*;

public class QualityListener implements ActionListener
{
    ModelInterface m;

    public QualityListener(ModelInterface m)
    {
        this.m = m;
    }

    public void actionPerformed(ActionEvent e)
    {
        parseFiles();
        removeUnwanted();
        saveFile();
    }

    public void parseFiles()
    {
        int i = 0;
        m.getInputLineList().clear();

        if(m.getFile1() != null )
        {
            try
            {
                CSVReader reader = new CSVReader(new FileReader(m.getFile1()), ':');
                String cutLine[] = null;
                while ((cutLine = reader.readNext()) != null)
                {
                    m.getInputLineList().add(new InputLine(cutLine[0],cutLine[1],cutLine[2],cutLine[3],cutLine[4],cutLine[5], cutLine[6]));
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
        System.out.println("removing those below "  + m.getMinASCII() + ", " + m.getMinSum());
    }

    public void removeUnwanted()
    {
        int removedCount = 0;
        for(int i =0; i<m.getInputLineList().size(); i++)
        {
            if (m.getInputLineList().get(i).getQualityMin() < m.getMinASCII()
                    || m.getInputLineList().get(i).getQualitySum() < m.getMinSum())
            {
                m.getInputLineList().remove(i);
                i--;
                removedCount++;
            }
        }
        System.out.println("Removed " + removedCount + " DNA sequences to List.");
    }

    public void saveFile()
    {
        String filename = m.getFile1().getName();

        try {
            FileWriter f0 = new FileWriter("C:\\" + filename + "_QUALITY_.txt", false);
            for (int i =0; i<m.getInputLineList().size(); i++)
            {
                //System.out.print(m.printInputLine(i));
                f0.write(m.printInputLine(i) + '\n');
            }
            f0.close();
        }
        catch(IOException ioe)
        {

        }
    }
}
