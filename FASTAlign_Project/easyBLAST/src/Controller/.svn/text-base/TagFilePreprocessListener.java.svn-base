package Controller;

import Model.ModelInterface;
import View.Input.InputEnd;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * The class is responsible for calling functions from the Model package
 * to pre-process data and create tag files. Once the pre-processing is
 * complete, a pop up is dislayed to let the user know, preprocessing is done.
 *
 * @author Jinita
 */
public class TagFilePreprocessListener implements ActionListener
{
    private ModelInterface model;
    private View view;

    public TagFilePreprocessListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        InputEnd inputEnd = view.getInputPanel().getInputEnd();

        if (e.getSource() == inputEnd.getBackButton())
        {
            JTabbedPane inputTabMenu = view.getInputPanel().getInputTabMenu();
            inputTabMenu.setSelectedIndex(inputTabMenu.getSelectedIndex() - 1);
        }

        if (e.getSource() == inputEnd.getPreProcess())
        {
            inputEnd.getPreProcess().setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "Pre-processing is being carried out right now. "
                    + "A message dialogue will pop-up once Pre-processing is "
                    + "done.", "FASTAlign", JOptionPane.PLAIN_MESSAGE);

            /* Run the progress label in a thread 
             * Note: Set updatingProgressBar as false to stop the thread later,
             * but since the system exits right after, this is not necessary. */
            view.getInputPanel().getInputEnd().setUpdatingProgressBar(true);
            Thread thread = new Thread(view.getInputPanel().getInputEnd());
            thread.start();

            Runnable r = new Runnable()
            {
                public void run()
                {
                    model.processInputFile();
                    JOptionPane.showMessageDialog(null,
                    "Preprocessing Flow is complete.",
                    "FASTAlign", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
            };

            Thread thread2 = new Thread(r);
            thread2.start();
        }
    }
}
