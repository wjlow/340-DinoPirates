package View;

import Model.ModelInterface;

import View.BLAST.ParametersPanel;
import View.BLAST.QueryBLASTPanel;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


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

    public final QueryBLASTPanel getQueryBLASTPanel()
    {
        return qBlast;
    }
}
