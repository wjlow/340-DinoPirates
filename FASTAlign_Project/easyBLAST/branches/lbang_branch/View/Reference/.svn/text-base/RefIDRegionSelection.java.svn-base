package View.Reference;

import Model.Model;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RefIDRegionSelection extends JPanel implements ActionListener
{
    Model m;
    JComboBox id;
    JComboBox region;
    JButton update;

    public RefIDRegionSelection (Model m)
    {
        this.m = m;

        setLayout(new GridLayout(2,1));

        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        id = new JComboBox();
        region = new JComboBox();
        region.addActionListener(this);
        panel1.add(id);
        panel1.add(region);
        
        update = new JButton("Update");
        update.addActionListener(this);
        panel1.add(update);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == update)
        {
            for(int i=0; i<m.getRefHeaders().size(); i++)
            {
                id.addItem(m.getRefHeaders().get(i).toString());
                region.addItem(m.getRefHeaders().get(i).toString());
            }
        }

        if(e.getSource() == region)
        {
            m.setRefRegionColumn(region.getSelectedIndex());
            System.out.println(m.getRefRegionColumn());
        }
    }
}
