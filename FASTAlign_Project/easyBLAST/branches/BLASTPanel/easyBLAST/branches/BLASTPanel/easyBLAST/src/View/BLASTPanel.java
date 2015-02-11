package View;

import Model.Model;
import View.BLAST.*;
import java.awt.*;
import javax.swing.*;

public class BLASTPanel extends JPanel{
    JTabbedPane         blastTabMenu;
    ParametersPanel     para;

    public BLASTPanel(Model m)
    {
        setLayout(new GridLayout(1,2));
        blastTabMenu    = new JTabbedPane();
        para = new ParametersPanel(m);

        blastTabMenu.add("Parameters Option", para);

        add(blastTabMenu);
    }
}
