package View;

import Model.ModelInterface;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class View extends JFrame
{
    ModelInterface model;

    private InputPanel      ip;
    private ReferencePanel  rp;
    private BLASTPanel      bp;
    private OutputPanel     op;

    public View(ModelInterface m)
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

            op = new OutputPanel(m);
            mainTabMenu.addTab("Output", op);
        }        
        
        mainPanel.add(mainTabMenu);
        getContentPane().add(mainTabMenu);

        setTitle("easyBLAST");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public final ReferencePanel getReferencePanel()
    {
        return rp;
    }

    public final InputPanel getInputPanel()
    {
        return ip;
    }

    public final void addInputParser(final ActionListener pfl)
    {
        ip.getInputFileSelection().addParseFilesListener(pfl);
    }

    public final void addReferenceFileParser(final ActionListener pfl)
    {
        rp.getRefFileSelection().addReferenceFileParser(pfl);
    }


    // The following TWO functions are adding listeners to their respective tabs
    public void addInputTabChangeListener(ChangeListener l)
    {
        ip.addInputTabListener(l);
    }
    public void addReferenceTabChangeListener(ChangeListener l)
    {
        rp.addReferenceTabChangeListener(l);
    }
    

    public void addQualityListener(ActionListener l)
    {
        ip.getInputQualityPanel().addQualityListener(l);
    }

    public void fillInValues()
    {

        ip.getInputIDSelection().fillInValues();
        ip.getInputRegionSelection().fillInValues();
    }

    public final void addReferenceCheckBoxes()
    {
        rp.getRefColumnChooser().addCheckBoxes();
    }

    public final void fillInRefDropDown()
    {
        rp.getRefIDRegionSelection().fillInValues();
    }

    public final void refShowPreviewSequences()
    {
        rp.getRefBLASTRegionSelection().showPreviewSequences();
    }

    public final OutputPanel getOutputPanel()
    {
        return op;
    }
}
