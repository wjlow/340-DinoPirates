package View;

import Model.ModelInterface;
import View.Input.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

public class InputPanel extends JPanel
{
    JTabbedPane inputTabMenu;
    InputFileSelection ifs;
    InputIDSelection iids;
    InputRegionSelection irs;
    InputQualityPanel iqp;

    public InputPanel(ModelInterface m)
    {
        setLayout(new GridLayout(1,2));
        inputTabMenu = new JTabbedPane();

        ifs = new InputFileSelection(m);
        inputTabMenu.addTab("File Selection", ifs);

        iqp = new InputQualityPanel(m);
        inputTabMenu.addTab("Quality Threshold", iqp);

        iids = new InputIDSelection(m);
        inputTabMenu.addTab("ID Selection", iids);

        InputGroupingPanel igs = new InputGroupingPanel(m);
        inputTabMenu.addTab("Grouping", igs);

        if (m.isBLASTMode())
        {
            irs = new InputRegionSelection(m);
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

    public void addQualityListener(ActionListener ae)
    {
        iqp.addQualityListener(ae);
    }
}
