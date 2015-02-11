package View;

import Controller.Controller;
import Model.ModelInterface;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.List;

public class View extends JFrame
{
    private ModelInterface model;
    private Controller controller;

    private InputPanel      inputPanel;
    private ReferencePanel  referencePanel;
    private BLASTPanel      blastPanel;
    private OutputPanel     outputPanel;

    public View()
    {
    }

    /**
     * This method initialises the View. 
     */
    public void initView()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        JPanel mainPanel = new JPanel(new GridLayout(1,2));
        mainPanel.setName("MainPanel");
        JTabbedPane mainTabMenu = new JTabbedPane(JTabbedPane.LEFT);
        mainTabMenu.setName("MainTab");

        inputPanel = new InputPanel(model);
        mainTabMenu.addTab("Query Files", inputPanel);
        inputPanel.setName("InputPanel");
        
        if (model.isBLASTMode())
        {
            referencePanel = new ReferencePanel(model);
            referencePanel.setName("ReferencePanel");
            mainTabMenu.addTab("Reference Files", referencePanel);
            blastPanel = new BLASTPanel(model);
            blastPanel.setName("BLASTPanel");
            mainTabMenu.addTab("BLAST", blastPanel);

            outputPanel = new OutputPanel(model);
            outputPanel.setName("OutputPanel");
            mainTabMenu.addTab("Output", outputPanel);
        }        
        
        mainPanel.add(mainTabMenu);
        getContentPane().add(mainTabMenu);

        setTitle("easyBLAST");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.setVisible(true);
    }

    public final ReferencePanel getReferencePanel()
    {
        return referencePanel;
    }

    public final InputPanel getInputPanel()
    {
        return inputPanel;
    }

    public final BLASTPanel getBLASTPanel()
    {
        return blastPanel;
    }

    public final OutputPanel getOutputPanel()
    {
        return outputPanel;
    }

    public final void addInputParser(final ActionListener pfl)
    {
        inputPanel.getInputFileSelection().addParseFilesListener(pfl);
    }
/* NOT USED
    public final void addReferenceFileParser(final ActionListener pfl)
    {
        referencePanel.getRefFileSelection().addReferenceFileParser(pfl);
    }
*/

    // The following TWO functions are adding listeners to their respective tabs
    public final void addInputTabChangeListener(final ChangeListener l)
    {
        inputPanel.addInputTabListener(l);
    }
    public final void addReferenceTabChangeListener(final ChangeListener l)
    {
        referencePanel.addReferenceTabChangeListener(l);
    }


    public final void addQualityListener(final ActionListener l)
    {
        inputPanel.getInputQualityPanel().addQualityListener(l);
    }

    /**
     * This method is used to display the preview of short reads for
     * the identification and BLAST region selection screens.
     */
    public final void fillInTextBoxes()
    {
        List<String> preview = model.getPreviewLines();
        
        JTextField[] idSelectionTFList = getInputPanel().
                getInputIDSelection().getShortReadTF();
        JTextField[] blastSelectionTFList = getInputPanel().
                getInputRegionSelection().getShortReadTF();
        JTextField[] idSelectionTFList2 = getInputPanel().
                getInputIDSelection().getShortReadTF2();
        JTextField[] blastSelectionTFList2 = getInputPanel().
                getInputRegionSelection().getShortReadTF2();

        for (int i = 0; i < 20; i++)
        {
            idSelectionTFList[i].setText(preview.get(i));
            blastSelectionTFList[i].setText(preview.get(i));
        }

        System.out.println("isPairedEnd = " + model.isPairedEnd());
        if (model.isPairedEnd() == true)
        {
            for (int i = 0; i < 20; i++)
            {
                idSelectionTFList2[i].setText(preview.get(i+20));
                blastSelectionTFList2[i].setText(preview.get(i+20));
            }
        }
    }

    public final void addReferenceCheckBoxes()
    {
        referencePanel.getRefColumnChooser().addCheckBoxes();
    }

    public final void fillInRefDropDown()
    {
        referencePanel.getRefIDRegionSelection().fillInValues();
    }

    public final void refShowPreviewSequences()
    {
        referencePanel.getRefBLASTRegionSelection().showPreviewSequences();
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }


    public void setModelInterface(ModelInterface model)
    {
        this.model = model;
    }
}
