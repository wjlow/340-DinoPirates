package View.BLAST;

import Model.Model;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class QueryBLASTPanel extends JPanel implements ActionListener
{
    JLabel label1;

    JPanel panel1;

    JFrame frame;

    JButton blast;

    Model m;

    public QueryBLASTPanel(Model m)
    {
        this.m = m;

        setLayout(new GridLayout(5,1));
        panel1 = new JPanel(new FlowLayout());
        add(panel1);
        blast = new JButton("BLAST");
        panel1.add(blast);

        blast.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == blast)
        {
            Process p;
            Runtime rt = Runtime.getRuntime();

            try 
            {
                String familyCmd = "c:\\blast\\bin\\blastall.exe ";
                String progCmd = "-p blastn ";
                String inputFileArg = "-i c:\\blast\\data\\humrep-input.fsa ";
                String refFileArg = "-d c:\\blast\\data\\humrep.fsa ";
                String outArg = "-o c:\\out.txt ";
                String parameters = "-m 8 -e 10 -W 11 -K 1";
                String formatdb = "c:\\blast\\bin\\formatdb -p F -i " +
                        "c:\\blast\\data\\humrep.fsa";
                String commands = familyCmd + progCmd + 
                        inputFileArg + refFileArg + outArg +
                        parameters ;
                System.out.println("The commands are: " + commands);
                p = rt.exec(commands);
                /*
                try {
                    // Generates .nhr .nin .nsq files of reference file
                    p = rt.exec(formatdb);
                    p.wait(500);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(QueryBLASTPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
                System.out.println("Success!");
            } catch (IOException ex) {
                Logger.getLogger(QueryBLASTPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
