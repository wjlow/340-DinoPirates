/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ModelInterface;
import View.Input.InputEnd;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jpatel
 */
public class TagFilePreprocessListener implements ActionListener
{
    ModelInterface model;
    View view;

    public TagFilePreprocessListener(ModelInterface m, View v)
    {
        model = m;
        view = v;
    }

    public void actionPerformed(ActionEvent e)
    {
        InputEnd inputEnd = view.getInputPanel().getInputEnd();

        if(e.getSource()== inputEnd.getPreProcess())
        {
            model.processInputFile();
        }
    }
}