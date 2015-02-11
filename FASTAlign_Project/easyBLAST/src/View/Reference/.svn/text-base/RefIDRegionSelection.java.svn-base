package View.Reference;

import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This RefIDRegionSelection panel contains the structure and layout of the
 * panel.
 * @author lbang
 */
public class RefIDRegionSelection extends JPanel
{
    private ModelInterface model;
    private JComboBox id;
    private JComboBox region;
    private JButton backButton;
    private JButton nextButton;

    public RefIDRegionSelection(ModelInterface m)
    {
        model = m;

        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout());
        JLabel northLabel = new JLabel("Specify the unique ID and "
                + "full hairpin sequence columns:");
        northLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        northPanel.add(northLabel);

        JPanel idPanel = new JPanel(new GridLayout(20, 1));
        JPanel hairpinPanel = new JPanel(new GridLayout(20, 1));
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(idPanel);
        centerPanel.add(hairpinPanel);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        JLabel idLabel = new JLabel("Unique ID");
        idLabel.setFont(new Font("San Serif", Font.BOLD, 14));
        JLabel hairpinLabel = new JLabel("Full Hairpin Sequence");
        hairpinLabel.setFont(new Font("San Serif", Font.BOLD, 14));

        idPanel.add(idLabel);
        hairpinPanel.add(hairpinLabel);

        id = new JComboBox();
        region = new JComboBox();

        idPanel.add(id);
        hairpinPanel.add(region);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        southPanel.add(backButton);
        southPanel.add(nextButton);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void fillInValues()
    {
        for (int i = 0; i < model.getRefHeaders().size(); i++)
        {
            id.addItem(model.getRefHeaders().get(i).toString());
            region.addItem(model.getRefHeaders().get(i).toString());
        }
    }

    public void addSelectionListener(ActionListener al)
    {
        id.addActionListener(al);
        region.addActionListener(al);
        backButton.addActionListener(al);
        nextButton.addActionListener(al);
    }

    public JComboBox getRegionCheckbox()
    {
        return region;
    }

    public JComboBox getIDCheckbox()
    {
        return id;
    }

    public JButton getBackButton()
    {
        return backButton;
    }

    public JButton getNextButton()
    {
        return nextButton;
    }
}
