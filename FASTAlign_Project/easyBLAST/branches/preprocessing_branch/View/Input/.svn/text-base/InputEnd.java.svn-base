package View.Input;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Collections;
import Model.*;

public class InputEnd extends JPanel implements ActionListener
{
    JPanel      jPanel1;
    JPanel      jPanel2;
    JPanel      jPanel3;

    JButton     parseFiles;
    JButton     generateTag;
    JButton     generateFasta;

    ModelInterface       m;

    public InputEnd(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(3,1));

        jPanel1 = new JPanel(new FlowLayout());
        parseFiles = new JButton("Parse Files");
        parseFiles.addActionListener(this);
        //jPanel1.add(parseFiles);

        jPanel2 = new JPanel(new FlowLayout());
        generateTag = new JButton("Generate tag Files");
        generateTag.addActionListener(this);
        jPanel2.add(generateTag);

        jPanel3 = new JPanel(new FlowLayout());
        generateFasta = new JButton("Generate FASTA Files");
        generateFasta.addActionListener(this);
        jPanel3.add(generateFasta);

        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == parseFiles)
        {
            if (m.getFile1() != null)
            {
                int i=0;
                try
                {
                    BufferedReader reader = new BufferedReader(new FileReader(m.getFile1()));
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        String cutLine[] = line.split(":");
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

            for (int i =0; i<m.getShortReadList().size(); i++)
            {
                System.out.println("id: " +  m.getShortReadList().get(i).getID()
                        + " region: " +  m.getShortReadList().get(i).getRegion()
                        + " count: " +  m.getShortReadList().get(i).getCount());
            }
        }

        if(e.getSource() == generateTag)
        {
            System.out.println("===== sorted =====");
            ShortReadScoreComparator src = new ShortReadScoreComparator();
            Collections.sort(m.getShortReadList(), src);
            
            for (int i =0; i<m.getShortReadList().size(); i++)
            {
                System.out.println("id: " +  m.getShortReadList().get(i).getID()
                        + "\tregion: " +  m.getShortReadList().get(i).getRegion()
                        + "\t\tcount: " +  m.getShortReadList().get(i).getCount());
            }

            String filename = m.getFile1().getName();
            try {
                FileWriter f0 = new FileWriter("C:\\" + filename + ".tag", false);
                for (int i =0; i<m.getShortReadList().size(); i++)
                {
                    System.out.print(m.getShortReadList().get(i).getCount() + "\t\t" + m.getShortReadList().get(i).getRegion() + "\n");
                    f0.write(m.getShortReadList().get(i).getCount() + "\t\t" + m.getShortReadList().get(i).getRegion() + "\n");
                }
                f0.close();
            }
            catch(IOException ioe)
            {

            }
        }

        if(e.getSource() == generateFasta)
        {
            System.out.println("===== sorted =====");
            ShortReadComparator src = new ShortReadComparator();
            Collections.sort(m.getShortReadList(), src);

            for (int i =0; i<m.getShortReadList().size(); i++)
            {
                System.out.println("id: " +  m.getShortReadList().get(i).getID()
                        + "\tregion: " +  m.getShortReadList().get(i).getRegion()
                        + "\t\tcount: " +  m.getShortReadList().get(i).getCount());
            }


            try {

                String id = m.getShortReadList().get(0).getID();
                for (int i =0; i<m.getShortReadList().size(); i++)
                {
                    if (!id.equals(m.getShortReadList().get(i).getID()))
                    {
                        id = m.getShortReadList().get(i).getID();
                    }
                    FileWriter f0 = new FileWriter("C:\\" + id + ".fa", true);
                    System.out.print(">" +m.getShortReadList().get(i).getID() + "_" + m.getShortReadList().get(i).getCount() + "_" + m.getShortReadList().get(i).getRegion() + "\n");
                    System.out.print(m.getShortReadList().get(i).getRegion() + "\n");
                    f0.write(">" +m.getShortReadList().get(i).getID() + "_" + m.getShortReadList().get(i).getCount() + "_" + m.getShortReadList().get(i).getRegion() + "\n");
                    f0.write(m.getShortReadList().get(i).getRegion() + "\n");
                    f0.close();
                }

            }
            catch(IOException ioe)
            {

            }
        }
    }
}
