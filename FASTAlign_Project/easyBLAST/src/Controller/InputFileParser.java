package Controller;

import Model.InputLine;
import Model.ModelInterface;
import Model.ShortRead;
import Model.opencsv.CSVReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * This InputFileParser listens to the PreProcess button in the Summary panel.
 */
public class InputFileParser implements ActionListener
{
    private ModelInterface model;
    private int max = Integer.MAX_VALUE;

    public InputFileParser(ModelInterface m)
    {
        this.model = m;
    }

    public InputFileParser(ModelInterface m, int i)
    {
        this.model = m;
        this.max = i;
    }

    public void actionPerformed(ActionEvent e)
    {

    }

    public void fullParse()
    {
        int i = 0;
        model.getInputLineList().clear();

        if (model.getInputFile() != null && model.getInputFile().canRead())
        {
            if (model.getInputFile() != null)
            {
                try
                {
                    CSVReader reader =
                            new CSVReader(new FileReader(model.getInputFile()));
                    String[] cutLine = null;
                    while ((cutLine = reader.readNext()) != null)
                    {
                        String id = cutLine[5].substring(
                                model.getInputIDStart(), model.getInputIDEnd());
                        String region = cutLine[5].substring(
                                model.getInputBLASTRegionStart(),
                                model.getInputBLASTRegionEnd());

                        String key = id + region;

                        ShortRead sr = new ShortRead(id, region);

                        if (model.getHT().get(key) == null)
                        {
                            model.getHT().put(key, sr);
                        }
                        else
                        {
                           ((ShortRead) model.getHT().get(key)).increaseCount();
                        }
                        i++;
                    }
                }
                catch (IOException ex)
                {
                    System.err.println(ex);
                }
            }

            for (Enumeration ee = model.getHT().keys(); ee.hasMoreElements();)
            {
                model.getShortReadList().
                        add((ShortRead) model.getHT().get(ee.nextElement()));
            }
        }

        if (model.getPairedEndFile() != null
                && model.getPairedEndFile().canRead())
        {
            try
            {
                CSVReader reader =
                        new CSVReader(new FileReader(model.getPairedEndFile()));
                String[] line = null;
                while ((line = reader.readNext()) != null && i < max)
                {
                    model.getInputLineList().add(
                            new InputLine(line[0], line[1], line[2], line[3],
                                            line[4], line[5], line[6]));
                    i++;
                }
            }
            catch (IOException ex)
            {
                System.err.println(ex);
            }
        }
    }

    public void partialParse()
    {
        int i = 0;

        model.getInputLineList().clear();

        if (model.getInputFile() != null && model.getInputFile().canRead())
        {
            try
            {
                CSVReader reader =
                        new CSVReader(new FileReader(model.getInputFile()));
                String[] cutLine = null;
                while ((cutLine = reader.readNext()) != null)
                {
                    model.getInputLineList().add(
                            new InputLine(cutLine[0], cutLine[1], cutLine[2],
                               cutLine[3], cutLine[4], cutLine[5], cutLine[6]));
                    i++;
                }
            }
            catch (IOException ex)
            {
                System.err.println(ex);
            }
        }

        if (model.getPairedEndFile() != null
                && model.getPairedEndFile().canRead())
        {
            try
            {
                CSVReader reader =
                        new CSVReader(new FileReader(model.getPairedEndFile()));

                String[] cutLine = null;
                while ((cutLine = reader.readNext()) != null)
                {
                    model.getInputLineList().add(
                            new InputLine(cutLine[0], cutLine[1], cutLine[2],
                               cutLine[3], cutLine[4], cutLine[5], cutLine[6]));
                    i++;
                }
            }
            catch (IOException ex)
            {
                System.err.println(ex);
            }
        }
    }
}
