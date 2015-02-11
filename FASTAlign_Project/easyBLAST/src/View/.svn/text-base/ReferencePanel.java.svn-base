package View;

import Controller.ReferenceTabChangeListener;
import View.Reference.RefBLASTRegionSelection;
import View.Reference.RefColumnChooser;
import View.Reference.RefFileSelection;
import View.Reference.RefIDRegionSelection;
import Model.ModelInterface;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The Reference Panel contains the tabs relevant to the selection and
 * manipulation of reference files.
 * @author lawrence
 */
public class ReferencePanel extends JPanel
{
    private JTabbedPane refTabMenu;
    private RefFileSelection refFileSelection;
    private RefColumnChooser refColumnChooser;
    private RefIDRegionSelection refIDSelection;
    private RefBLASTRegionSelection refBLASTRegionSelection;
    private ReferenceTabChangeListener l;

    /**
     * The constructor takes in a ModelInterface and has full control.
     * @param m The ModelInterface this JPanel has access to.
     */
    public ReferencePanel(final ModelInterface m)
    {
        setLayout(new GridLayout(1, 2));

        refTabMenu = new JTabbedPane();
        refFileSelection = new RefFileSelection(m);
        refColumnChooser = new RefColumnChooser(m);
        refIDSelection = new RefIDRegionSelection(m);
        refBLASTRegionSelection = new RefBLASTRegionSelection(m);

        refTabMenu.add("Reference File Selection", refFileSelection);
        refTabMenu.add("Column Chooser", refColumnChooser);
        refTabMenu.add("Column Header Selection", refIDSelection);
        refTabMenu.add("Alignment Region Selection", refBLASTRegionSelection);

        refTabMenu.setEnabledAt(1, false);
        refTabMenu.setEnabledAt(2, false);
        refTabMenu.setEnabledAt(3, false);

        add(refTabMenu);
    }

    /**
     * A tab change listener to the tabs of the ReferencePanel.
     * @param l The change listener that is attached to the tabs.
     */
    public void addReferenceTabChangeListener(ReferenceTabChangeListener l)
    {
        this.l = l;
        refTabMenu.addChangeListener(l);
    }

    public final RefColumnChooser getRefColumnChooser()
    {
        return refColumnChooser;
    }

    public final RefFileSelection getRefFileSelection()
    {
        return refFileSelection;
    }

    public final RefIDRegionSelection getRefIDRegionSelection()
    {
        return refIDSelection;
    }

    public final RefBLASTRegionSelection getRefBLASTRegionSelection()
    {
        return refBLASTRegionSelection;
    }

    public JTabbedPane getRefTabPane()
    {
        return refTabMenu;
    }
}
