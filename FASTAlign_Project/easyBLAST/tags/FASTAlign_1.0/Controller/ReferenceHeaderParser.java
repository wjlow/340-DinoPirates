package Controller;

import Model.ModelInterface;
import Model.opencsv.CSVReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;


/**
 * The class is reponsible for extracting column names of the selected reference
 * files. The headers are obtained and displayed on the screen for the user to
 * select. Data in the selected fields are later displayed in the output to with
 * its corresponding ID.
 * @author lawrence
 */
public class ReferenceHeaderParser implements ActionListener
{
    private ModelInterface m;

    /**
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
                }

            }
            catch (IOException ex)
            {
                System.err.println(ex);
            }
        }
    }
}
