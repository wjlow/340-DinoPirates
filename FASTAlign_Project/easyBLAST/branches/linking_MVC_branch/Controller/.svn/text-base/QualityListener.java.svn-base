package Controller;

import Model.ModelInterface;
import View.Input.InputQualityPanel;
import View.View;
import java.awt.event.*;
import java.io.File;
import javax.swing.JFileChooser;
//import javax.swing.*;

public class QualityListener implements ActionListener
{
    private ModelInterface model;
    private View view;
    //private JFileChooser fileChooser = new JFileChooser();

    public QualityListener(final ModelInterface model, final View view)
    {
        this.model = model;
        this.view = view;
    }
    
    /**
     * The methods executes actions according to the values entered and buttons
     * pressed. The asciiMin and asciiSum values are stored in the 
     * ModelInterface. When ther user presses the saveResults, a file choosers
     * is displayed for the user to select a location and the name of the file.
     * @param e
     */
    public void actionPerformed(final ActionEvent e)
    {
        InputQualityPanel qualityPanel = view.getInputPanel().
                getInputQualityPanel();
        
        if(e.getSource() == qualityPanel.getASCIIMinLimit())
        {
            model.setMinASCII(Integer.parseInt(qualityPanel.getASCIIMinLimit().getText()));
            System.out.println("Min ASCII Value is: " + model.getMinASCII());
        }
        if(e.getSource()== qualityPanel.getASCIISumLimit())
        {
            model.setMinSum(Integer.parseInt(qualityPanel.getASCIISumLimit().getText()));
            System.out.println("Min ASCII Sum is: " + model.getMinSum());
        }
        
        if (e.getSource() == qualityPanel.getSaveChanges())
        {
            model.setMinSum(Integer.parseInt(qualityPanel.getASCIISumLimit().getText()));
            model.setMinASCII(Integer.parseInt(qualityPanel.getASCIIMinLimit().getText()));
            System.out.println("Changes Saved!");
            System.out.println("Min ASCII Value is: " + model.getMinASCII());
            System.out.println("Min ASCII Sum is: " + model.getMinSum());
        }
        if(e.getSource() == qualityPanel.getSaveFile())
        {
            model.setSaveQualityFile(true);
            int returnVal = qualityPanel.getFileChooser()
                    .showSaveDialog(qualityPanel);
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                File qualityFile = qualityPanel.getFileChooser().getSelectedFile();
                System.out.println("File: "+ qualityFile.getPath());
                model.setQualitySaveLocation(qualityFile);
            }
        }
    }
}