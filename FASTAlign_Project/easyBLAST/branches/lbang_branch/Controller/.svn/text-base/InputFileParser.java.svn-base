package Controller;

import Model.*;
import java.awt.event.*;
import java.io.*;

public class InputFileParser implements ActionListener {

    Model m;

    public InputFileParser(Model m)
    {
        this.m = m;
    }

    public void actionPerformed(ActionEvent e)
    {
        int i = 0;

        m.getInputLineList().clear();

        if(m.getFile1() != null && m.getFile1().canRead())
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(m.getFile1()));
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
}
