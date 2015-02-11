package View;

import Model.ModelInterface;
import View.BLAST.*;
import java.awt.*;
import javax.swing.*;

public class BLASTPanel extends JPanel{
    JTabbedPane         blastTabMenu;
    ParametersPanel     para;
    QueryBLASTPanel     qBlast;

    public BLASTPanel(ModelInterface m)
    {
        setLayout(new GridLayout(1,2));
        blastTabMenu    = new JTabbedPane();
        para   = new ParametersPanel(m);
        qBlast = new QueryBLASTPanel(m);

        blastTabMenu.add("Parameters Option", para);
        blastTabMenu.add("Multi-threading BLAST", qBlast);

        add(blastTabMenu);
    }
}
