/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ModelInterface;
import View.ModeSelect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author wjlow
 */
public class ModeSelectListener implements ActionListener
{

    private ModeSelect modeSelect;

    public ModeSelectListener(ModeSelect modeSelect)
    {
        this.modeSelect = modeSelect;
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
