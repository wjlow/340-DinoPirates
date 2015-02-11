package View;

import Model.ModelInterface;
import Controller.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ModeSelect extends JFrame implements ActionListener
{
    ModelInterface m;

    JButton preprocessingFlow;
    JButton blastFlow;

    public ModeSelect(ModelInterface m)
    {
        this.m = m;

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        add(mainPanel);

        preprocessingFlow = new JButton("Preprocess");
        preprocessingFlow.addActionListener(this);

        blastFlow = new JButton("BLAST");
        blastFlow.addActionListener(this);

        mainPanel.add(preprocessingFlow);
        mainPanel.add(blastFlow);

        setTitle("easyBLAST");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == preprocessingFlow)
        {
            m.setBLASTMode(false);
            this.setVisible(false);
            View v = new View(m);
            v.setVisible(true);
            Controller c = new Controller(m,v);
        }

        if (e.getSource() == blastFlow)
        {
            m.setBLASTMode(true);
            this.setVisible(false);
            View v = new View(m);
            v.setVisible(true);
            Controller c = new Controller(m,v);
        }
    }
}
