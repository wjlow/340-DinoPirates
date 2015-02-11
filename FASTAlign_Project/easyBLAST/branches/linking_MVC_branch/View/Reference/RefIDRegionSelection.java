package View.Reference;

import Model.ModelInterface;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RefIDRegionSelection extends JPanel implements ActionListener
{
    ModelInterface m;
    JComboBox id;
    JComboBox region;

    public RefIDRegionSelection (ModelInterface m)
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
        
    }

    public void fillInValues()
    {
        for(int i=0; i<m.getRefHeaders().size(); i++)
        {
            id.addItem(m.getRefHeaders().get(i).toString());
            region.addItem(m.getRefHeaders().get(i).toString());
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == region)
        {
            m.setRefRegionColumn(region.getSelectedIndex());
            System.out.println(m.getRefRegionColumn());
        }
    }
}
