package View;

import Model.ModelInterface;
import View.Input.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

public class InputPanel extends JPanel
{
    private JTabbedPane inputTabMenu;
    private InputFileSelection inputFileSelection;
    private InputIDSelection inputIDs;
    private InputRegionSelection inputRegions;
    private InputGroupingPanel inputGroups;
    private InputQualityPanel inputQualitYPanel;
    private InputEnd inputEnd;

    public InputPanel(ModelInterface m)
    {
        setLayout(new GridLayout(1,2));
        inputTabMenu = new JTabbedPane();
        inputTabMenu.setName("InputTab");
        
        inputFileSelection = new InputFileSelection(m);
        inputFileSelection.setName("InputFileSelection");
        inputTabMenu.addTab("File Selection", inputFileSelection);

        if (!m.isBLASTMode())
        {
            inputQualitYPanel = new InputQualityPanel(m);
            inputQualitYPanel.setName("InputQualityPanel");
            inputTabMenu.addTab("Quality Threshold", inputQualitYPanel);
        }


        inputIDs = new InputIDSelection(m);
        inputIDs.setName("InputIDSelection");
        inputTabMenu.addTab("ID Selection", inputIDs);

        // Need Region Selection for Tag files.
        inputRegions = new InputRegionSelection(m);
        inputRegions.setName("InputRegionSelection");
        inputTabMenu.addTab("Region Selection", inputRegions);

        if (!m.isBLASTMode())
        {
            inputGroups = new InputGroupingPanel(m);
            inputGroups.setName("InputGroupingPanel");
            inputTabMenu.addTab("Grouping", inputGroups);


            inputEnd = new InputEnd(m);
            inputTabMenu.addTab("Tag Files", inputEnd);
            inputEnd.setName("InputEnd");
        }

        add(inputTabMenu);
    }

    public void addInputTabListener(ChangeListener l)
    {
        inputTabMenu.addChangeListener(l);
    }

    public InputIDSelection getInputIDSelection()
    {
        return inputIDs;
    }

    public InputQualityPanel getInputQualityPanel()
    {
        return inputQualitYPanel;
    }

    public InputFileSelection getInputFileSelection()
    {
        return inputFileSelection;
    }

    public InputGroupingPanel getInputGroupingPanel()
    {
        return inputGroups;
    }

    public InputRegionSelection getInputRegionSelection()
    {
        return inputRegions;
    }

    public InputEnd getInputEnd()
    {
        return inputEnd;
    }
}
