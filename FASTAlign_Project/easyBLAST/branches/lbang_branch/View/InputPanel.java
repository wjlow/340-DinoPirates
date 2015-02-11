package View;

import Model.Model;
import View.Input.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class InputPanel extends JPanel
{
    JTabbedPane inputTabMenu;
    InputFileSelection ifs;

    public InputPanel(Model m)
    {
        setLayout(new GridLayout(1,2));
        inputTabMenu = new JTabbedPane();

        ifs = new InputFileSelection(m);
        inputTabMenu.addTab("File Selection", ifs);

        InputQualityPanel iqp = new InputQualityPanel(m);
        inputTabMenu.addTab("Quality Threshold", iqp);

        InputIDSelection iids = new InputIDSelection(m);
        inputTabMenu.addTab("ID Selection", iids);

        InputGroupingPanel igs = new InputGroupingPanel(m);
        inputTabMenu.addTab("Grouping", igs);

        if (m.isBLASTMode())
        {
            InputRegionSelection irs = new InputRegionSelection(m);
            inputTabMenu.addTab("Region Selection", irs);
        }

        InputEnd ie = new InputEnd(m);
        inputTabMenu.addTab("Input End", ie);

        add(inputTabMenu);
    }

    public void addInputTabListener(ChangeListener l)
    {
        inputTabMenu.addChangeListener(l);
    }
}
