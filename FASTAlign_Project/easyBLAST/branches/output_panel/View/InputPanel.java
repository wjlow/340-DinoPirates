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
    private InputFileSelection ifs;
    private InputIDSelection iids;
    private InputRegionSelection irs;
    private InputGroupingPanel igs;
    private InputQualityPanel iqp;

    public InputPanel(ModelInterface m)
    {
        setLayout(new GridLayout(1,2));
        inputTabMenu = new JTabbedPane();

        ifs = new InputFileSelection(m);
        inputTabMenu.addTab("File Selection", ifs);

        if (!m.isBLASTMode())
        {
            iqp = new InputQualityPanel(m);
            inputTabMenu.addTab("Quality Threshold", iqp);     
        }

        iids = new InputIDSelection(m);
        inputTabMenu.addTab("ID Selection", iids);

     // Need Region Selection for Tag files.
        irs = new InputRegionSelection(m);
        inputTabMenu.addTab("Region Selection", irs);

        if (!m.isBLASTMode())
        {
            igs = new InputGroupingPanel(m);
            inputTabMenu.addTab("Grouping", igs);

            InputEnd ie = new InputEnd(m);
            inputTabMenu.addTab("Tag Files", ie);
        }

        add(inputTabMenu);
    }

    public void addInputTabListener(ChangeListener l)
    {
        inputTabMenu.addChangeListener(l);
    }

    public InputIDSelection getInputIDSelection()
    {
        return iids;
    }

    public InputQualityPanel getInputQualityPanel()
    {
        return iqp;
    }

    public InputFileSelection getInputFileSelection()
    {
        return ifs;
    }

    public InputGroupingPanel getInputGroupingPanel()
    {
        return igs;
    }

    public InputRegionSelection getInputRegionSelection()
    {
        return irs;
    }
}
