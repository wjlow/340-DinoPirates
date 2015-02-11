package View;

import View.Reference.*;
import Model.Model;
import java.awt.*;
import javax.swing.*;

public class ReferencePanel extends JPanel {
    JPanel              mainPanel;
    JTabbedPane         refTabMenu;
    RefFileSelection    rfs;
    RefColumnChooser    rcc;

    public ReferencePanel(Model m)
    {
        setLayout(new GridLayout(1,2));
        refTabMenu = new JTabbedPane();
        rfs = new RefFileSelection(m);
        rcc = new RefColumnChooser(m);
        RefIDRegionSelection ris = new RefIDRegionSelection(m);
        RefBLASTRegionSelection rbrs = new RefBLASTRegionSelection(m);

        refTabMenu.add("Reference File Selection", rfs);
        refTabMenu.add("Column Chooser", rcc);
        refTabMenu.add("ID Selection", ris);
        refTabMenu.add("BLAST Selection", rbrs);

        add(refTabMenu);
    }
}