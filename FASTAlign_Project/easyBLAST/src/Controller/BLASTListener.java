package Controller;

import Model.AbstractAlignmentTool;
import Model.ModelInterface;
import View.BLAST.QueryBLASTPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FilenameFilter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * This ActionListener listens to the Align button on Alignment Panel.
 */
public class BLASTListener implements ActionListener
{
    private ModelInterface model;
    private View view;
    private Process p2;

    public BLASTListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        QueryBLASTPanel queryBlastPanel =
                view.getBLASTPanel().getQueryBLASTPanel();

        if (e.getSource() == queryBlastPanel.getBackButton())
        {
            JTabbedPane blastTabMenu = view.getBLASTPanel().getBlastTabMenu();
            blastTabMenu.setSelectedIndex(blastTabMenu.getSelectedIndex() - 1);
        }
        else if (e.getSource() == queryBlastPanel.getStopButton())
        {
            int result = JOptionPane.showConfirmDialog(null,
                    "Do you really want to stop aligning?", "FASTAlign",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                p2.destroy();
            }
        }
        else if (e.getSource() == queryBlastPanel.getBlastButton())
        {
            final Process p;

            model.generateRefFASTAFile();

            try
            {
                final int algorithmIndex;

                view.getBLASTPanel().getQueryBLASTPanel().
                        getBlastButton().setEnabled(false);

                view.getBLASTPanel().getQueryBLASTPanel().
                        getStopButton().setEnabled(true);

                JOptionPane.showMessageDialog(null,
                        "Aligning has begun!", "FASTAlign",
                        JOptionPane.INFORMATION_MESSAGE);

                algorithmIndex = model.getAlgorithmIndex();

                final File align = new File(System.getProperty("user.home")
                        + File.separator + "FASTAlign" + File.separator
                        + "bin" + File.separator + model.getListofTools().
                        get(algorithmIndex).getAlignmentTool());
                File format = new File(System.getProperty("user.home")
                        + File.separator + "FASTAlign" + File.separator
                        + "bin" + File.separator + model.getListofTools().
                        get(algorithmIndex).getFormatReferenceFileTool());

                if (!align.exists() || !format.exists())
                {
                    JOptionPane.showMessageDialog(null, "The "
                            + "system can not find the correct "
                            + "algorithms. Please place "
                            + model.getListofTools().get(algorithmIndex).
                            getAlignmentTool() + " and "
                            + model.getListofTools().get(algorithmIndex).
                            getFormatReferenceFileTool() + " in "
                            + System.getProperty("user.home")
                            + File.separator + "FASTAlign"
                            + File.separator + "bin", "FASTAlign",
                            JOptionPane.ERROR_MESSAGE);
                }

                String format_exec = "\"" + format.toString() + "\"";

                for (int i = 0; i < model.getListofTools().get(algorithmIndex).
                        getFormatParams().length; i++)
                {
                    format_exec += " " + model.getListofTools().
                            get(algorithmIndex).
                            getFormatParams()[i].getFlag() + " "
                            + model.getListofTools().get(algorithmIndex).
                            getFormatParams()[i].getValue();
                }

                System.out.println("The format commands are: " + format_exec);

                try
                {
                    p = Runtime.getRuntime().exec(format_exec, null,
                            new File(System.getProperty("user.dir")));
                    p.waitFor();
                }
                catch (Exception ex)
                {
                    Logger.getLogger(BLASTListener.class.getName()).
                            log(Level.SEVERE, null, ex);
                }

                model.generateInputFASTAFile();

                Runnable r = new Runnable()
                {
                    public void run()
                    {
                        String align_exec = "\"" + align.toString() + "\"";

                        // append parameters here.
                        for (int i = 0; i < model.getListofTools().
                                get(algorithmIndex).getAlignParameters().
                                length; i++)
                        {
                            align_exec += " " + model.getListofTools().
                                    get(algorithmIndex).
                                    getAlignParameters()[i].getFlag()
                                    + " " + model.getListofTools().
                                    get(algorithmIndex).
                                    getAlignParameters()[i].getValue();
                        }

                        System.out.println("The align commands are: "
                                + align_exec);

                        try
                        {
                            p2 = Runtime.getRuntime().exec(
                                    align_exec, null, new File(
                                    System.getProperty("user.dir"))
                            );
                            view.getMainTabMenu().setEnabledAt(3, true);
                            p2.waitFor();
                            JOptionPane.showMessageDialog(null,
                                    "Aligning has finished!", "FASTAlign",
                                    JOptionPane.INFORMATION_MESSAGE);

                            view.getBLASTPanel().getQueryBLASTPanel().
                                getStopButton().setEnabled(false);

                            // append outout to the table.
                            view.getOutputPanel().addData(new File(
                                    System.getProperty("user.dir")
                                    + File.separator + "align_output.txt"));
                        }

                        catch (Exception ex)
                        {
                            System.out.println(
                                    "Tool did not execute properly.");
                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();
            }
            catch (Exception ee)
            {
                System.out.println(ee);
            }
        }
    }

    class ExtensionFilter implements FilenameFilter
    {
      private String extension;
      public ExtensionFilter(String extension)
      {
        this.extension = extension;
      }

      public boolean accept(File dir, String name)
      {
        return (name.endsWith(extension));
      }
    }
}
