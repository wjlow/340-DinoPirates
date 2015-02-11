package View;

import Model.Model;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class View extends JFrame
{
    Model model;

    InputPanel      ip;
    ReferencePanel  rp;
    BLASTPanel      bp;

    public View(Model m)
    {
        model = m;

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        JPanel mainPanel = new JPanel(new GridLayout(1,2));
        JTabbedPane mainTabMenu = new JTabbedPane(JTabbedPane.LEFT);

        ip = new InputPanel(m);
        mainTabMenu.addTab("Query Files", ip);
        
        if (m.isBLASTMode())
        {
            rp = new ReferencePanel(m);
            mainTabMenu.addTab("Reference Files", rp);
            bp = new BLASTPanel(m);
            mainTabMenu.addTab("BLAST", bp);
        }        
        
        mainPanel.add(mainTabMenu);
        getContentPane().add(mainTabMenu);

        setTitle("easyBLAST");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void addInputParser(ActionListener pfl)
    {
        ip.ifs.addParseFilesListener(pfl);
    }

    public void addReferenceHeaderParser(ActionListener pfl)
    {
        rp.rfs.addReferenceHeaderParser(pfl);
    }

    public void addInputTabChangeListener(ChangeListener l)
    {
        ip.addInputTabListener(l);
    }

    public void fillInValues()
    {
        ip.iids.fillInValues();
        ip.irs.fillInValues();
    }
}
