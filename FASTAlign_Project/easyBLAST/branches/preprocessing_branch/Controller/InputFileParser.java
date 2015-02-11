package Controller;

import Model.*;
import Model.opencsv.CSVReader;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Collections;

public class InputFileParser implements ActionListener {

    ModelInterface m;
    int max = Integer.MAX_VALUE;

    public InputFileParser(ModelInterface m)
    {
        this.m = m;
    }

    public InputFileParser(ModelInterface m, int i)
    {
        this.m = m;
        this.max = i;
    }

    public void actionPerformed(ActionEvent e)
    {

    }

    public void fullParse()
    {
        int i = 0;
        m.getInputLineList().clear();

        if(m.getFile1() != null && m.getFile1().canRead())
        {
            if (m.getFile1() != null)
            {
                try
                {
                    CSVReader reader = new CSVReader(new FileReader(m.getFile1()));
                    String cutLine[] = null;
                    while ((cutLine = reader.readNext()) != null)
                    {
                        String key = cutLine[5].substring(m.getIDStart(), m.getIDEnd()) + cutLine[5].substring(m.getRegionStart(), m.getRegionEnd());
                        String id = cutLine[5].substring(m.getIDStart(), m.getIDEnd());
                        String region =  cutLine[5].substring(m.getRegionStart(), m.getRegionEnd());
                        ShortRead sr = new ShortRead(id, region);

                        if(m.getHT().get(key) == null)
                            m.getHT().put(key, sr);
                        else
                            ((ShortRead)m.getHT().get(key)).increaseCount();

                        i++;
                    }
                }
                catch(IOException ex)
                {
                    System.err.println(ex);
                }
            }

            for (Enumeration ee = m.getHT().keys(); ee.hasMoreElements() ; )
            {
                m.getShortReadList().add((ShortRead)m.getHT().get(ee.nextElement()));
            }

            for (int j =0; j<m.getShortReadList().size(); j++)
            {
                System.out.println("id: " +  m.getShortReadList().get(j).getID()
                        + " region: " +  m.getShortReadList().get(j).getRegion()
                        + " count: " +  m.getShortReadList().get(j).getCount());
            }
        }

        if(m.getFile2() != null && m.getFile2().canRead())
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(m.getFile2()));
                String line = null;
                while ((line = reader.readLine()) != null && i < max)
                {
                    String cutLine[] = line.split(":");
                    m.getInputLineList().add(new InputLine(cutLine[0],cutLine[1],cutLine[2],cutLine[3],cutLine[4],cutLine[5], cutLine[6]));
                    i++;
                }
            }
            catch(IOException ex)
            {
                System.err.println(ex);
            }
        }
        System.out.println("Added " + i + " DNA sequences to List.");
    }

    public void partialParse()
    {
        int i = 0;

        m.getInputLineList().clear();

        if(m.getFile1() != null && m.getFile1().canRead())
        {
            try
            {
                CSVReader reader = new CSVReader(new FileReader(m.getFile1()));
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

        if(m.getFile2() != null && m.getFile2().canRead())
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(m.getFile2()));
                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    String cutLine[] = line.split(":");
                    m.getInputLineList().add(new InputLine(cutLine[0],cutLine[1],cutLine[2],cutLine[3],cutLine[4],cutLine[5], cutLine[6]));
                    i++;
                }
            }
            catch(IOException ex)
            {
                System.err.println(ex);
            }
        }
        System.out.println("Added " + i + " DNA sequences to List.");
    }


    public void groupingProcess(String groups [][])
    {
        m.callMakeGroups(groups);
        m.callDoGrouping();
    }
}
