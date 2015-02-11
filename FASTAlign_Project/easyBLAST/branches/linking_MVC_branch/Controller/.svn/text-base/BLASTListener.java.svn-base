/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.opencsv.CSVReader;
import Model.ModelInterface;
import Model.InputLine;
import View.View;
import java.io.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class BLASTListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public BLASTListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        final Process p;
        final Process p2;
        final Runtime rt = Runtime.getRuntime();
        final String s;

        //read the inputFile
        model.readInputFile();

        //produce InputFASTA file.
        model.convertInputToFASTA();

        model.generateRefFASTAFile();

        try
        {
            final File dir = new File("C:\\temp\\");

            final String[] listOfInputFiles = dir.list(new ExtensionFilter("fsa"));

            String formatdb = "c:\\blast\\bin\\formatdb -p F -i " +
                    "C:\\temp\\ref.fa";
            p = Runtime.getRuntime().exec(formatdb);
            System.out.println("The format commands are: " + formatdb);

            p.waitFor();

            for (int i = 0; i < listOfInputFiles.length; i++ )
            {
                final int j = i;
                Runnable r = new Runnable()
                {
                    public void run()
                    {
                        System.out.println(listOfInputFiles[j]);

                        String commands = "c:\\blast\\bin\\blastall.exe -p blastn "
                                + "-i " + dir +  "\\" + listOfInputFiles[j]
                                + " -d C:\\temp\\ref.fa -o " + dir + "\\"
                                + listOfInputFiles[j] + "OUT.txt -m 8 -e 10 -W 11 -K 1";

                        System.out.println("The commands are: " + commands);

                        try
                        {
                            Process p2 = Runtime.getRuntime().exec(commands);
                            System.out.println(listOfInputFiles[j] + " has finished.");

                        }

                        catch (Exception ex)
                        {

                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();
            }
            
            view.getOutputPanel().addData(new File("C:\\out.txt"));

        }
        catch (Exception ee)
        {

        }
    }

    class ExtensionFilter implements FilenameFilter {
      private String extension;
      public ExtensionFilter( String extension ) {
        this.extension = extension;
      }

      public boolean accept(File dir, String name) {
        return (name.endsWith(extension));
      }
    }
}
