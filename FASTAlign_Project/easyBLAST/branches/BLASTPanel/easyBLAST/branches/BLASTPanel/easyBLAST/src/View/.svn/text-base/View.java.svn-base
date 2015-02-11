package View;

import Model.Model;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class View extends JFrame
{
    Model model;

    JPanel mainPanel;
    JTabbedPane     mainTabMenu;
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

        setTitle("easyBLAST");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel       = new JPanel(new GridLayout(1,2));
        mainTabMenu     = new JTabbedPane(JTabbedPane.LEFT);
        ip              = new InputPanel(m);
        rp              = new ReferencePanel(m);
        bp              = new BLASTPanel(m);

        mainTabMenu.addTab("Query Files", ip);
        mainTabMenu.addTab("Reference Files", rp);
        mainTabMenu.addTab("BLAST", bp);
        mainPanel.add(mainTabMenu);
        getContentPane().add(mainTabMenu);

    }

    public void addInputParser(ActionListener pfl)
    {
        ip.ifs.addParseFilesListener(pfl);
    }

    public void addReferenceHeaderParser(ActionListener pfl)
    {
        rp.rfs.addReferenceHeaderParser(pfl);
    }

}
