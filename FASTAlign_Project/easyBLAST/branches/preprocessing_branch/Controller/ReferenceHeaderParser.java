package Controller;

import Model.opencsv.CSVReader;
import Model.*;
import java.awt.event.*;
import java.io.*;

public class ReferenceHeaderParser implements ActionListener
{
    ModelInterface m;

    public ReferenceHeaderParser(ModelInterface m)
    {
        this.m = m;
    }

    public void actionPerformed(ActionEvent e)
    {
        int i = 0;

        if(m.getRefFile() != null && m.getRefFile().canRead())
        {
            try
            {
                CSVReader reader = new CSVReader(new FileReader(m.getRefFile()));

                String cutLine[] = reader.readNext();
                
                for(i=0; i< cutLine.length; i++)
                {
                    m.getRefHeaders().add(cutLine[i]);
                    System.out.println("added " + cutLine[i] + " to ref headers list");
                }

            }
            catch(IOException ex)
            {
                System.err.println(ex);
            }
        }
    }
}
