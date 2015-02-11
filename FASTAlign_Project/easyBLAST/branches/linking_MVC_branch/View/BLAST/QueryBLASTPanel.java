package View.BLAST;

import Model.ModelInterface;
import Model.Parameters;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel which displays a button which allows the user to BLAST.
 * @author lawrence
 */
public class QueryBLASTPanel extends JPanel
{

    private JButton blast;

    private ModelInterface model = new ModelInterface();

    private Parameters param = new Parameters();

    /**
     * The constructor takes in a ModelInterface and attaches it to the current
     * view
     * @param m The ModelInterface to attach.
     */
    public QueryBLASTPanel(final ModelInterface m)
    {
        this.model = m;

        setLayout(new GridLayout(1, 1));
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        blast = new JButton("BLAST");
        panel1.add(blast);
    }

    /**
     * Adds an ActionListener to the BLAST JButton
     * @param l The ActionListener that is attached.
     */
    public final void addBLASTListener(final ActionListener l)
    {
        blast.addActionListener(l);
    }
}
