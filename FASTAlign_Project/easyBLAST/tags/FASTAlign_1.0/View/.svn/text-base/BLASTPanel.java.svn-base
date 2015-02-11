package View;

import Controller.BLASTTabListener;
import Model.ModelInterface;

import View.BLAST.AlignmentToolSelection;
import View.BLAST.ParametersPanel;
import View.BLAST.QueryBLASTPanel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * This BLASTPanel contains the structure and the layout of the panel.
 * @author wengn
 */
public class BLASTPanel extends JPanel
{
    private JTabbedPane             blastTabMenu;
    private AlignmentToolSelection  align;
    private ParametersPanel         para;
    private QueryBLASTPanel         qBlast;

    public BLASTPanel(ModelInterface m)
    {
        setLayout(new GridLayout(1, 2));
        blastTabMenu = new JTabbedPane();
        align = new AlignmentToolSelection(m);
        para = new ParametersPanel(m);
        qBlast = new QueryBLASTPanel(m);

        blastTabMenu.add("Alignment Tool Selection", align);
        blastTabMenu.add("Parameters Option", para);
        blastTabMenu.add("Multi-threading BLAST", qBlast);

        add(blastTabMenu);
    }

    public final AlignmentToolSelection getAlignmentToolSelect()
    {
        return align;
    }

    public final QueryBLASTPanel getQueryBLASTPanel()
    {
        return qBlast;
    }

    public final ParametersPanel getParametersPanel()
    {
        return para;
    }

    public JTabbedPane getBlastTabMenu()
    {
        return blastTabMenu;
    }

    void addBLASTTabListener(BLASTTabListener bLASTTabListener)
    {
        blastTabMenu.addChangeListener(bLASTTabListener);
    }
}
