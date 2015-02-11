package View.BLAST;

import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AlignmentToolSelection extends JPanel
{
    private ModelInterface model;
    private JComboBox tools;
    private JButton nextButton;

    /**
     * The constructor takes in a ModelInterface and attaches it to the current
     * view
     * @param m The ModelInterface to attach.
     */
    public AlignmentToolSelection(final ModelInterface m)
    {
        this.model = m;

        // Sets the layout of the screen
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(3, 1));
        add(northPanel, BorderLayout.NORTH);

        JPanel labelPanel = new JPanel(new FlowLayout());
        JLabel toolsLabel = new JLabel("Choose an alignment tool to use:");
        toolsLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        labelPanel.add(toolsLabel, BorderLayout.NORTH);
        northPanel.add(labelPanel);

        tools = new JComboBox();
        JPanel panel2 = new JPanel(new FlowLayout());
        add(panel2);
        for (int i = 0; i < m.getListofTools().size(); i++)
        {
            tools.addItem(m.getListofTools().get(i).getAlgorithmName());
        }
        
        panel2.add(tools);

        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        navigationPanel.add(nextButton);
        this.add(navigationPanel, BorderLayout.SOUTH);
    }

    public final void addToolsSelectListener(final ActionListener l)
    {
        tools.addActionListener(l);
        nextButton.addActionListener(l);
    }

    public final JComboBox getToolsComboBox()
    {
        return tools;
    }

    public JButton getNextButton()
    {
        return nextButton;
    }
}
