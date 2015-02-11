package Controller;

import Model.opencsv.CSVReader;
import View.View;
import Model.ModelInterface;
import java.io.FileReader;
import java.io.IOException;

/**
 * ReferenceFileParser is the class which will parse Reference Files.
 * It will also implement an ActionListener, so that when users select a file,
 * only the first row (header) is read.
 * @author lawrence
 */
public class ReferenceFileParser
{
    private ModelInterface model;
    private View view;

    private CSVReader reader;
    private int max = Integer.MAX_VALUE; //max is the number of lines to read.

    /**
     *
     * @param m
     */
    public ReferenceFileParser(ModelInterface m)
    {
        model = m;
    }

    /**
     *
     * @param m
     * @param v
     */
    public ReferenceFileParser(final ModelInterface m, final View v)
    {
        model = m;
        view = v;
    }

    /**
     * 
     * @param m
     * @param i
     */
    public ReferenceFileParser(final ModelInterface m, final int i)
    {
        model = m;
        max = i;
    }

    /**
     * 
     * @param m
     * @param v
     * @param i
     */
    public ReferenceFileParser(ModelInterface m, View v, int i)
    {
        model = m;
        max = i;
        view = v;
    }

    /**
     * Adds the header row to the List in the ModelInterface
     */
    public final void setReferenceHeaders()
    {
        int i = 0;

        if (model.getRefFile() != null && model.getRefFile().canRead())
        {
            try
            {
                reader = new CSVReader(new FileReader(model.getRefFile()));
                String[] cutLine = reader.readNext();

                for (i = 0; i < cutLine.length; i++)
                {
                    model.getRefHeaders().add(cutLine[i]);
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

    /**
     * This function shall parse all the reference files.
     */
    public void parse()
    {

    }
}