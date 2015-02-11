package View;

import Model.ModelInterface;
import View.Input.InputEnd;
import View.Input.InputFileSelection;
import View.Input.InputGroupingPanel;
import View.Input.InputIDSelection;
import View.Input.InputQualityPanel;
import View.Input.InputRegionSelection;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

/**
 * This InputPanel contains the structure and the layout of the panel.
 * @author wengn
 */
public class InputPanel extends JPanel
{
    private JTabbedPane inputTabMenu;
    private InputFileSelection inputFileSelection;
    private InputIDSelection inputIDs;
    private InputRegionSelection inputRegions;
    private InputGroupingPanel inputGroups;
    private InputQualityPanel inputQualityPanel;
    private InputEnd inputEnd;

    public InputPanel(ModelInterface m)
    {
        setLayout(new GridLayout(1, 2));
        inputTabMenu = new JTabbedPane();
        inputTabMenu.setName("InputTab");

        inputFileSelection = new InputFileSelection(m);
        inputFileSelection.setName("InputFileSelection");
        inputTabMenu.addTab("File Selection", inputFileSelection);

        if (!m.isBLASTMode())
        {
            inputQualityPanel = new InputQualityPanel(m);
            inputQualityPanel.setName("InputQualityPanel");
            inputTabMenu.addTab("Quality Threshold", inputQualityPanel);
        }

        inputIDs = new InputIDSelection(m);
        inputIDs.setName("InputIDSelection");
        inputTabMenu.addTab("ID Selection", inputIDs);
        inputTabMenu.setEnabledAt(0, true);
        inputTabMenu.setEnabledAt(1, false);

        // This tab only exists in the BLAST/Alignment flow
        if (m.isBLASTMode())
        {
            inputRegions = new InputRegionSelection(m);
            inputRegions.setName("InputRegionSelection");
            inputTabMenu.addTab("Alignment Region Selection", inputRegions);
            inputTabMenu.setEnabledAt(2, false);
        }

        if (!m.isBLASTMode())
        {
            inputGroups = new InputGroupingPanel(m);
            inputGroups.setName("InputGroupingPanel");
            inputTabMenu.addTab("Grouping", inputGroups);

            inputEnd = new InputEnd(m);
            inputTabMenu.addTab("Summary", inputEnd);
            inputEnd.setName("InputEnd");

            inputTabMenu.setEnabledAt(2, false);
            inputTabMenu.setEnabledAt(3, false);
            inputTabMenu.setEnabledAt(4, false);
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
        return inputQualityPanel;
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

    public JTabbedPane getInputTabMenu()
    {
        return inputTabMenu;
    }
}
