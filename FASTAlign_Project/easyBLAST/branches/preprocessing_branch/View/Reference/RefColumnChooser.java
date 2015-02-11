package View.Reference;

import Model.ModelInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RefColumnChooser extends JPanel implements ActionListener {

    ModelInterface m;
    
    JCheckBox[] checkbox;
    JButton     update;
    JPanel      panel1;
    JPanel      panel2;
    
    public RefColumnChooser(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(2,1));

        panel1 = new JPanel(new FlowLayout());
        add(panel1);
        update = new JButton("Update");
        update.addActionListener(this);
        panel1.add(update);

        panel2 = new JPanel(new FlowLayout());
        add(panel2);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == update)
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
}
