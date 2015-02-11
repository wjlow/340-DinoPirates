package View.Reference;

import Model.ModelInterface;
import java.awt.*;
import javax.swing.*;

public class RefColumnChooser extends JPanel
{
    ModelInterface m;
    
    JCheckBox[] checkbox;
    JPanel      panel2;
    
    public RefColumnChooser(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(1,1));

        panel2 = new JPanel(new FlowLayout());
        add(panel2);
    }

    /**
     * This function adds the checkboxes to the RefColumnChooser Panel
     */
    public void addCheckBoxes()
    {
        checkbox = new JCheckBox[m.getRefHeaders().size()];
        for(int i=0; i<m.getRefHeaders().size(); i++)
        {
            checkbox[i] = new JCheckBox(m.getRefHeaders().get(i).toString());
            panel2.add(checkbox[i]);
        }
        panel2.setVisible(false); //hack to get the checkboxes on screen instantly
        panel2.setVisible(true);
    }
}
