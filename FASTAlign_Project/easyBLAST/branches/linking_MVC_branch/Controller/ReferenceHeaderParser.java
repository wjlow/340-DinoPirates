package Controller;

import Model.*;
import Model.opencsv.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author lawrence
 */
public class ReferenceHeaderParser implements ActionListener
{
    private ModelInterface m;

    /**
     * 
     * @param m
     */
    public ReferenceHeaderParser(final ModelInterface m)
    {
        this.m = m;
    }

    public void actionPerformed(ActionEvent e)
    {
        int i = 0;

        if (m.getRefFile() != null && m.getRefFile().canRead())
        {
            try
            {
                CSVReader reader = new CSVReader(
                        new FileReader(m.getRefFile()));

                String[] cutLine = reader.readNext();

                for (i = 0; i < cutLine.length; i++)
                {
                    m.getRefHeaders().add(cutLine[i]);
                    System.out.println("added " + cutLine[i]
                            + " to ref headers list");
                }

            }
            catch (IOException ex)
            {
                System.err.println(ex);
            }
        }
    }
}
