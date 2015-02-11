package View;

import View.Reference.*;
import Model.ModelInterface;
import javax.swing.event.ChangeListener;
import java.awt.*;
import javax.swing.*;

public class ReferencePanel extends JPanel
{
    private JTabbedPane refTabMenu;
    private RefFileSelection refFileSelection;
    private RefColumnChooser refColumnChooser;
    private RefIDRegionSelection refIDSelection;
    private RefBLASTRegionSelection refBLASTRegionSelection;

    public ReferencePanel(ModelInterface m)
    {
        setLayout(new GridLayout(1,2));

        refTabMenu = new JTabbedPane();
        refFileSelection = new RefFileSelection(m);
        refColumnChooser = new RefColumnChooser(m);
        refIDSelection = new RefIDRegionSelection(m);
        refBLASTRegionSelection = new RefBLASTRegionSelection(m);

        refTabMenu.add("Reference File Selection", refFileSelection);
        refTabMenu.add("Column Chooser", refColumnChooser);
        refTabMenu.add("ID Selection", refIDSelection);
        refTabMenu.add("BLAST Selection", refBLASTRegionSelection);

        add(refTabMenu);
    }

    public void addReferenceTabChangeListener(ChangeListener l)
    {
        refTabMenu.addChangeListener(l);
    }

    public RefColumnChooser getRefColumnChooser()
    {
        return refColumnChooser;
    }

    public RefFileSelection getRefFileSelection()
    {
        return refFileSelection;
    }

    public RefIDRegionSelection getRefIDRegionSelection()
    {
        return refIDSelection;
    }

    public RefBLASTRegionSelection getRefBLASTRegionSelection()
    {
        return refBLASTRegionSelection;
    }
}