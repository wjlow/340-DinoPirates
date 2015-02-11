package View.Input;

import Model.ModelInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;

public class InputIDSelection extends JPanel
{
    private ModelInterface m;

    private final int lengthToDisplay = 38;
    private final int numOfRowsToDisplay = 20;

    private final CaretListenerLabel caretListenerLabel =
            new CaretListenerLabel("Caret Status");
    private final CaretListenerLabel caretListenerLabel2 =
            new CaretListenerLabel("Caret Status");

    private JPanel[] panelForTF;
    private JTextField[] shortReadTF;
    private JTextField[] shortReadTF2;
    private Highlighter[] highlighter;
    private Highlighter[] highlighter2;
    private Highlighter.HighlightPainter painter;

    private JButton backButton;
    private JButton nextButton;

    private JLabel selectionRegionLabel = new JLabel("No region selected");

    /**
     * True if the user selected the identification from the paired end file.
     */
    private boolean selectedPairedEnd;

    public InputIDSelection(ModelInterface m)
    {
        selectedPairedEnd = false;

        //setLayout(new GridLayout(numOfRowsToDisplay + 3, 1));
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(
                new GridLayout(numOfRowsToDisplay + 1, 1));
        this.add(centerPanel, BorderLayout.CENTER);
        this.m = m;

        JPanel labelPanel = new JPanel(new FlowLayout());
        this.add(labelPanel, BorderLayout.NORTH);
        JLabel mainLabel = new JLabel("Please select the region that " +
                "represents the ID of each shortread.");
        mainLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        labelPanel.add(mainLabel);

        panelForTF = new JPanel[numOfRowsToDisplay];
        shortReadTF = new JTextField[numOfRowsToDisplay];
        highlighter = new DefaultHighlighter[numOfRowsToDisplay];

        for (int i = 0; i < numOfRowsToDisplay; i++)
        {
            // Create a panel to put the textfield in
            panelForTF[i] = new JPanel(new FlowLayout());
            centerPanel.add(panelForTF[i]);

            // Create the textfield and put it into the panel
            shortReadTF[i] = new JTextField(lengthToDisplay);
            shortReadTF[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
            shortReadTF[i].setHorizontalAlignment(JTextField.CENTER);
            shortReadTF[i].addCaretListener(caretListenerLabel);
            shortReadTF[i].setEditable(false);
            panelForTF[i].add(shortReadTF[i]);

            // Create a highlighter for each textfield
            highlighter[i] = new DefaultHighlighter();
            shortReadTF[i].setHighlighter(highlighter[i]);

            if (i > 0)
            {
                shortReadTF[i].setEnabled(false);
            }
        }

        JPanel selectionRegionLabelPanel = new JPanel(new FlowLayout());
        selectionRegionLabelPanel.add(selectionRegionLabel);
        centerPanel.add(selectionRegionLabelPanel);


        painter = new DefaultHighlighter.DefaultHighlightPainter(
                Color.DARK_GRAY);

        JPanel navigationButtonsPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        nextButton.setEnabled(false);

        navigationButtonsPanel.add(backButton);
        navigationButtonsPanel.add(nextButton);
        this.add(navigationButtonsPanel, BorderLayout.SOUTH);
    }

    /**
     * If a paired end file exists, this method is called to generate
     * the text boxes for previewing the second file.
     */

    public void generatePairedEndTextBoxes()
    {
        if (m.isPairedEnd() && !m.getHasGeneratedPairedEndTextBoxes())
        {
            // If a paired end file exists and no paired end text boxes
            // have been generated yet, generate them.

            shortReadTF2 = new JTextField[numOfRowsToDisplay];
            highlighter2 = new DefaultHighlighter[numOfRowsToDisplay];

            for (int i = 0; i < numOfRowsToDisplay; i++)
            {
                // Create the textfield and put it into the panel
                shortReadTF2[i] = new JTextField(lengthToDisplay);
                shortReadTF2[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
                shortReadTF2[i].setHorizontalAlignment(JTextField.CENTER);
                shortReadTF2[i].addCaretListener(caretListenerLabel2);
                shortReadTF2[i].setEditable(false);
                panelForTF[i].add(shortReadTF2[i]);

                // Create a highlighter for each textfield
                highlighter2[i] = new DefaultHighlighter();
                shortReadTF2[i].setHighlighter(highlighter2[i]);

                if (i > 0)
                {
                    shortReadTF2[i].setEnabled(false);
                }
            }

            // Toggle flag to indicate that textfields for the paired end
            // short reads have been generated.
            // DO NOT DELETE THIS LINE, OTHERWISE THE REGION SELECTION
            // SCREENS FOR PAIRED END FILES WILL BREAK

            if (m.isPairedEnd())
            {
                m.setHasGeneratedPairedEndTextBoxes(true);
            }
        }

        else if (!m.isPairedEnd() && m.getHasGeneratedPairedEndTextBoxes())
        {
            // If a paired end file does not exist anymore (box is unticked)
            // and the system has once generated the paired end text boxes,
            // remove these textfields.

            for (int i = 0; i < numOfRowsToDisplay; i++)
            {
                panelForTF[i].remove(shortReadTF2[i]);
            }

            //this.setVisible(false);
            //this.setVisible(true);
        }
    }

    protected class CaretListenerLabel extends JLabel implements CaretListener
    {
        private CaretEvent event;
        public CaretListenerLabel(final String label)
        {
            super(label);
        }

        public void caretUpdate(CaretEvent e)
        {
            event = e;
            displaySelectionInfo(event.getDot(), event.getMark());
        }

        /**
         * This method sets the selected ID region in the ModelInterface and
         * highlights the rest of the text fields.
         * @param dot The point where the user first puts their mouse.
         * @param mark The point where the user ends their selection.
         */
        protected void displaySelectionInfo(final int dot, final int mark)
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    //getNextButton().setVisible(true);
                    if (dot < mark) //Selection from right to left.
                    {
                        getNextButton().setEnabled(true);
                        m.setInputIDRegion(dot, mark);

                        try
                        {
                            if (m.getHasGeneratedPairedEndTextBoxes())
                            {
                                for (int i = 0; i < highlighter.length; i++)
                                {
                                    highlighter2[i].removeAllHighlights();
                                    highlighter[i].removeAllHighlights();

                                    if (event.getSource() == shortReadTF2[0])
                                    {
                                        // The user selected a region from
                                        // the paired end file
                                        highlighter2[i].addHighlight(
                                                dot, mark, painter);
                                        selectedPairedEnd = true;
                                    }
                                    else
                                    {
                                        // The user selected a region from
                                        // the first file
                                        highlighter[i].addHighlight(
                                                dot, mark, painter);
                                        selectedPairedEnd = false;
                                    }
                                }
                            }
                            else
                            {
                                for (int i = 1; i < highlighter.length; i++)
                                {
                                    highlighter[i].removeAllHighlights();
                                    highlighter[i].addHighlight(
                                            dot, mark, painter);
                                }
                                selectedPairedEnd = false;
                            }

                            getNextButton().setEnabled(true);
                            m.setSelectedPairedEndInIdSelection(
                                    selectedPairedEnd);
                        }
                        catch (BadLocationException ble)
                        {
                            // dsf
                        }
                    }
                    else if (dot > mark) //Selection from left to right .
                    {
                        try
                        {
                            if (m.getHasGeneratedPairedEndTextBoxes())
                            {
                                for (int i = 0; i < highlighter.length; i++)
                                {
                                    highlighter2[i].removeAllHighlights();
                                    highlighter[i].removeAllHighlights();

                                    if (event.getSource() == shortReadTF2[0])
                                    {
                                        //selected from paired end
                                        highlighter2[i].addHighlight(
                                                dot, mark, painter);
                                        selectedPairedEnd = true;
                                    }
                                    else
                                    {
                                        //selected from file one
                                        highlighter[i].addHighlight(
                                                dot, mark, painter);
                                        selectedPairedEnd = false;
                                    }
                                }
                            }
                            else
                            {
                                for (int i = 1; i < highlighter.length; i++)
                                {
                                    highlighter[i].removeAllHighlights();
                                    highlighter[i].addHighlight(
                                            mark, dot, painter);
                                }
                                selectedPairedEnd = false;
                            }
                            m.setSelectedPairedEndInIdSelection(
                                    selectedPairedEnd);
                        }
                        catch (BadLocationException ble)
                        {
                        }
                        getNextButton().setEnabled(true);
                        // By now, selectedPairedEnd would be updated
                        // So set the inputIDRegion accordingly
                        m.setInputIDRegion(mark, dot);
                    }

                    if (m.getSelectedPairedEndInIDSelection())
                    {
                        if (m.getInputIDEnd() == 0 && m.getInputIDEnd() == 0)
                        {
                            selectionRegionLabel.setText(
                            "You have selected from "
                            + (m.getInputIDStart() + 1) + " to "
                            + m.getInputIDEnd() + " of the paired "
                            + "end file as the identification of "
                            + "the shortreads.");
                        }
                        else
                        {
                            selectionRegionLabel.setText(
                            "You have selected from "
                            + (m.getInputIDStart() + 1) + " to "
                            + (m.getInputIDEnd()) + " of the paired "
                            + "end file as the identification of "
                            + "the shortreads.");
                        }
                    }
                    else
                    {
                        if (m.getInputIDEnd() == 0 && m.getInputIDEnd() == 0)
                        {
                            selectionRegionLabel.setText(
                            "You have selected from "
                            + m.getInputIDStart() + " to "
                            + m.getInputIDEnd() + " of the paired "
                            + "end file as the identification of "
                            + "the shortreads.");
                        }
                        else
                        {
                            selectionRegionLabel.setText(
                            "You have selected from "
                            + (m.getInputIDStart() + 1) + " to "
                            + (m.getInputIDEnd())
                            + " as the identification of the shortreads.");
                        }
                    }
                }
            });
        }
    }
    public void addActionListeners(ActionListener al)
    {
        backButton.addActionListener(al);
        nextButton.addActionListener(al);
    }

    public JTextField[] getShortReadTF()
    {
        return shortReadTF;
    }

    public JTextField[] getShortReadTF2()
    {
        return shortReadTF2;
    }

    public boolean getSelectedPairedEnd()
    {
        return selectedPairedEnd;
    }

    public JButton getNextButton()
    {
        return nextButton;
    }

    public JButton getBackButton()
    {
        return backButton;
    }
}
