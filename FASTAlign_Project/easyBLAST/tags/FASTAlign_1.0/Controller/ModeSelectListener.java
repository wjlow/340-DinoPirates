package Controller;

import View.ModeSelect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class listens to the Pre-Processing Flow and the Alignment Flow buttons. 
 * If the user clicks on the Pre-Processing Flow button, blastMode is set to
 * false. If the user clicks on the Alignment Flow button, blastMode is set to
 * true.
 *
 * @author wjlow
 */
public class ModeSelectListener implements ActionListener
{

    private ModeSelect modeSelect;

    public ModeSelectListener(ModeSelect ms)
    {
        modeSelect = ms;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == modeSelect.getPreprocessingFlowButton())
        {
            modeSelect.getModelInterface().setBLASTMode(false);
        }

        if (e.getSource() == modeSelect.getBlastFlowButton())
        {
            modeSelect.getModelInterface().setBLASTMode(true);
        }

        modeSelect.setVisible(false);

        // Initiate the View
        modeSelect.getView().initView();

        // Initiate the Controller
        modeSelect.getController().initController();
    }

}
