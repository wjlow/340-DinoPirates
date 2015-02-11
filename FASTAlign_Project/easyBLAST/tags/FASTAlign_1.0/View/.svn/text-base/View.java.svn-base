package View;

import Controller.BLASTTabListener;
import Controller.Controller;
import Model.ModelInterface;
import Model.Parameters;
import java.util.List;
import Controller.ReferenceTabChangeListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

public class View extends JFrame
{
    private ModelInterface model;
    private Controller controller;
    private Dimension dimension;

    private InputPanel      inputPanel;
    private ReferencePanel  referencePanel;
    private BLASTPanel      blastPanel;
    private OutputPanel     outputPanel;

    private JTabbedPane mainTabMenu;

    public View()
    {
    }

    public void initView()
    {
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setName("MainPanel");
        mainTabMenu = new JTabbedPane(JTabbedPane.LEFT);
        mainTabMenu.setName("MainTab");

        if (!model.isBLASTMode())
        {
            /* Work with two query files for pre-processing flow */
            inputPanel = new InputPanel(model);
            mainTabMenu.addTab("Query Files", inputPanel);
            inputPanel.setName("InputPanel");
        }

        if (model.isBLASTMode())
        {
            /* Only work with one query file for alignment flow */
            inputPanel = new InputPanel(model);
            mainTabMenu.addTab("Query File", inputPanel);
            inputPanel.setName("InputPanel");

            referencePanel = new ReferencePanel(model);
            referencePanel.setName("ReferencePanel");
            mainTabMenu.addTab("Reference Files", referencePanel);
            blastPanel = new BLASTPanel(model);
            blastPanel.setName("BLASTPanel");
            mainTabMenu.addTab("Alignment", blastPanel);

            outputPanel = new OutputPanel(model);
            outputPanel.setName("OutputPanel");
            mainTabMenu.addTab("Output", outputPanel);
            mainTabMenu.setEnabledAt(3, false);
        }

        mainPanel.add(mainTabMenu);
        getContentPane().add(mainTabMenu);

        setTitle("FASTAlign");
        //setSize(700, 800);
        dimension = new Dimension(840, 790);
        setMinimumSize(dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        // this was annoying, especially on large monitors.
        //this.setExtendedState(this.getExtendedState()
        // | JFrame.MAXIMIZED_BOTH);
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

    // The following TWO functions are adding listeners to their respective tabs
    public final void addInputTabChangeListener(final ChangeListener l)
    {
        inputPanel.addInputTabListener(l);
    }

    public void addReferenceTabChangeListener(ReferenceTabChangeListener l)
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
        int maxLinesToDisplay;
        int totalLines = model.getPreviewLines().size();

        if (totalLines >= 20)
        {
            maxLinesToDisplay = 20;
        }
        else
        {
            maxLinesToDisplay = totalLines / 2;
        }

        JTextField[] idSelectionTFList =
                getInputPanel().getInputIDSelection().getShortReadTF();
        JTextField[] idSelectionTFList2 =
                getInputPanel().getInputIDSelection().getShortReadTF2();
        JTextField[] blastSelectionTFList = null;
        JTextField[] blastSelectionTFList2 = null;

        if (model.isBLASTMode())
        {
            blastSelectionTFList =
                    getInputPanel().getInputRegionSelection().getShortReadTF();
            blastSelectionTFList2 =
                    getInputPanel().getInputRegionSelection().getShortReadTF2();
        }

        for (int i = 0; i < maxLinesToDisplay; i++)
        {
            if (preview.size() > i)
            {
                idSelectionTFList[i].setText(preview.get(i));

                if (model.isBLASTMode())
                {
                    blastSelectionTFList[i].setText(preview.get(i));
                }
            }
        }

        if (model.isPairedEnd())
        {
            for (int i = 0; i < maxLinesToDisplay; i++)
            {
                if (preview.size() > i + maxLinesToDisplay)
                {
                    idSelectionTFList2[i].setText(preview.get(i
                            + maxLinesToDisplay));
                    if (model.isBLASTMode())
                    {
                        blastSelectionTFList2[i].setText(preview.get(i
                                + maxLinesToDisplay));
                    }
                }
            }
        }
    }

    public final void addReferenceCheckBoxes()
    {
        referencePanel.getRefColumnChooser().addCheckBoxes();
    }

    public final void removeReferenceCheckBoxes()
    {
        referencePanel.getRefColumnChooser().removeAllCheckboxes();
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

    public JTabbedPane getMainTabMenu()
    {
        return mainTabMenu;
    }

    public void addBLASTTabListener(BLASTTabListener bLASTTabListener)
    {
        blastPanel.addBLASTTabListener(bLASTTabListener);
    }
}
