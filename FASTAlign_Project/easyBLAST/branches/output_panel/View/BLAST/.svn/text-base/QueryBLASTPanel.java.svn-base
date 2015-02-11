package View.BLAST;

import Model.ModelInterface;
import Model.Parameters;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel which displays a button which allows the user to BLAST.
 * blast = 
 * @author lawrence
 */
public class QueryBLASTPanel extends JPanel implements ActionListener
{

    private JButton blast;

    private ModelInterface m = new ModelInterface();

    private Parameters param = new Parameters();

    public QueryBLASTPanel(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(5,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        blast = new JButton("BLAST");
        panel1.add(blast);

        blast.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == blast)
        {
            Process p;
            Process p2;
            Runtime rt = Runtime.getRuntime();
            String s;

            //read the inputFile
            m.readInputFile();

            //produce InputFASTA file.
            m.convertInputToFASTA();

            //m.generateRefFASTAFile();

            try 
            {
                String familyCmd = "c:\\blast\\bin\\blastall.exe ";
                String progCmd = "-p blastn ";
                String inputFileArg = "-i c:\\blast\\data\\humrep-input.fsa ";
                String refFileArg = "-d c:\\blast\\data\\humrep.fsa ";
                String outArg = "-o C:\\BLAST_OUT.txt ";
                param = m.getParameters();
                String parameters = "-m 8 -e 10 -W 11 -K 1"; //param.toString();
                String formatdb = "c:\\blast\\bin\\formatdb -p F -i " +
                        "c:\\blast\\data\\humrep.fsa";
                String commands = familyCmd + progCmd + 
                        inputFileArg + refFileArg + outArg +
                        parameters ;

                System.out.println("The format commands are: " + formatdb);

                System.out.println("The commands are: " + commands);
                p = Runtime.getRuntime().exec(formatdb);

                /* Sleep for 4s to ensure that formatdb has finished
                 * running before we run BLAST */
                try
                {
                    Thread.sleep(4000);
                } 
                
                catch (InterruptedException ex)
                {
                    Logger.getLogger(QueryBLASTPanel.class.getName()).
                            log(Level.SEVERE, null, ex);
                }

                p2 = Runtime.getRuntime().exec(commands);

                BufferedReader stdInput = new BufferedReader(new
                         InputStreamReader(p2.getInputStream()));
                BufferedReader stdError = new BufferedReader(new
                         InputStreamReader(p2.getErrorStream()));

        
                while ((s = stdInput.readLine()) != null)
                {
                    System.out.println(s);
                }

                while ((s = stdError.readLine()) != null)
                {
                    System.out.println(s);
                }

              
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



            } 
            
            catch (IOException ex)
            {
                Logger.getLogger(QueryBLASTPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
