package Controller;

import Model.opencsv.*;
import View.*;
import Model.ModelInterface;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

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
    
    JFileChooser fileChooser;
    private CSVReader reader;
    int max = Integer.MAX_VALUE; //int is the number of lines to read.

    public ReferenceFileParser(ModelInterface m)
    {
        model = m;
    }
    
    public ReferenceFileParser(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public ReferenceFileParser(ModelInterface m, int i)
    {
        model = m;
        max = i;
    }

    public ReferenceFileParser(ModelInterface m, View v, int i)
    {
        model = m;
        max = i;
        view = v;
    }

    /**
     * Adds the header row to the List in the ModelInterface
     */
    public void setReferenceHeaders()
    {
        int i = 0;

        if(model.getRefFile() != null && model.getRefFile().canRead())
        {
            try
            {
                reader = new CSVReader(new FileReader(model.getRefFile()));
                String cutLine[] = reader.readNext();

                for(i=0; i< cutLine.length; i++)
                {
                    model.getRefHeaders().add(cutLine[i]);
                    System.out.println("added " + cutLine[i] + " to ref headers list");
                }
            }
            catch(IOException ex)
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