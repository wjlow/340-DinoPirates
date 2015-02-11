package View.Reference;

import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This RefColumnChooser panel contains the layout and structure of the panel.
 * @author lbang and wengn
 */
public class RefColumnChooser extends JPanel
{
    private int onceDone = 0;
    private ModelInterface model;

    private final int rowsToDisplay = 15;

    private JCheckBox[] checkbox = null;

    private JPanel panel2;
    private JPanel panel3;

    private JPanel[] panel2s1;
    private JPanel[] panel3s1;

    private JPanel navigationPanel;

    private JButton backButton;
    private JButton nextButton;

    public RefColumnChooser(ModelInterface m)
    {
        model = m;
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select columns to appear in the output "
                + "reference FASTA file");
        JLabel label2 =
                new JLabel("Note that the ID is automatically included.");
        label.setFont(new Font("Sans Serif", Font.BOLD, 20));
        label2.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panel6 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new GridLayout(2, 1));
        panel5.add(label);
        panel6.add(label2);
        panel2.add(panel5);
        panel2.add(panel6);
        add(panel2, BorderLayout.NORTH);
        JPanel panel1 = new JPanel(new GridLayout(1, 2));
        add(panel1, BorderLayout.CENTER);

        panel2 = new JPanel(new GridLayout(rowsToDisplay, 1));
        panel3 = new JPanel(new GridLayout(rowsToDisplay, 1));

        panel2s1 = new JPanel[rowsToDisplay];
        panel3s1 = new JPanel[rowsToDisplay];

        for (int i = 0; i < rowsToDisplay; i++)
        {
            panel2s1[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel2.add(panel2s1[i]);
            panel3s1[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel3.add(panel3s1[i]);
        }
        panel1.add(panel2);
        panel1.add(panel3);

        navigationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        navigationPanel.add(backButton);
        navigationPanel.add(nextButton);
        this.add(navigationPanel, BorderLayout.SOUTH);
    }

    public final void addCheckListener(final ActionListener l)
    {
        for (int i = 0; i < checkbox.length; i++)
        {
            checkbox[i].addActionListener(l);
        }
    }

    public final void addNavigationListener(final ActionListener al)
    {
        backButton.addActionListener(al);
        nextButton.addActionListener(al);
    }

    /**
     * This function adds the checkboxes to the RefColumnChooser Panel
     */
    public void addCheckBoxes()
    {
//        if(onceDone == 0)
//        {
             checkbox = new JCheckBox[model.getRefHeaders().size()];

            for (int i = 0; i < model.getRefHeaders().size(); i++)
            {
                checkbox[i] =
                        new JCheckBox(model.getRefHeaders().get(i).toString());

                if (i % 2 == 0)
                {
                    panel2s1[i / 2].add(checkbox[i]);
                }
                else
                {
                    panel3s1[i / 2].add(checkbox[i]);
                }
            }
//            onceDone = 1;
//        }
    }

    public void removeAllCheckboxes()
    {
        int totalHeaders = model.getRefHeaders().size();

        for (int j = 0; j < model.getRefHeaders().size(); j++)
        {
            if (j % 2 == 0)
            {
                panel2s1[j / 2].remove(checkbox[j]);
            }
            else
            {
                panel3s1[j / 2].remove(checkbox[j]);
            }
        }

        for (int i = totalHeaders - 1; i >= 0 ; i--)
        {
            if (checkbox != null)
            {
                model.removeRefHeaders(i);
            }
        }
    }

    public JCheckBox[] getCheckBox()
    {
        return checkbox;
    }

    public JButton getBackButton()
    {
        return backButton;
    }

    public JButton getNextButton()
    {
        return nextButton;
    }
    public void reSetOneDone()
    {
        onceDone = 0;
    }
}
