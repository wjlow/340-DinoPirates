package View;

import Model.Model;
import View.Input.*;
import java.awt.*;
import javax.swing.*;

public class InputPanel extends JPanel
{

    JTabbedPane         inputTabMenu;
    InputFileSelection  ifs;
    InputQualityPanel   iqp;
    InputIDSelection    iids;
    InputGroupingPanel  igs;
    InputRegionSelection irs;
    InputEnd            ie;

    public InputPanel(Model m)
    {
        setLayout(new GridLayout(1,2));
        inputTabMenu    = new JTabbedPane();
        ifs             = new InputFileSelection(m);
        iqp             = new InputQualityPanel(m);
        iids            = new InputIDSelection(m);
        igs             = new InputGroupingPanel(m);
        irs             = new InputRegionSelection(m);
        ie              = new InputEnd(m);

        inputTabMenu.addTab("File Selection", ifs);
        inputTabMenu.addTab("Quality Threshold", iqp);
        inputTabMenu.addTab("ID Selection", iids);
        inputTabMenu.addTab("Grouping", igs);
        inputTabMenu.addTab("Region Selection", irs);
        inputTabMenu.addTab("Input End", ie);

        add(inputTabMenu);
    }
}
