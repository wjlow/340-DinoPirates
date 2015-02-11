package View.Input;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import Model.*;
import Model.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputRegionSelection extends JPanel
{
    private ModelInterface m;

    private final int lengthToDisplay = 38;

    private final CaretListenerLabel caretListenerLabel =
            new CaretListenerLabel("Caret Status");
    private final CaretListenerLabel caretListenerLabel2 =
            new CaretListenerLabel("Caret Status");


    private JPanel jPanel1;

    private JPanel[] panelForTF;
    private JTextField[] shortReadTF;
    private JTextField[] shortReadTF2;
    private Highlighter[] highlighter;
    private Highlighter[] highlighter2;
    private Highlighter.HighlightPainter painter;

    private boolean selectedPairedEnd;

    public InputRegionSelection(ModelInterface m)
    {
        selectedPairedEnd = false;

        setLayout(new GridLayout(21, 1));

        this.m = m;

        jPanel1 = new JPanel(new FlowLayout());
        add(jPanel1);
        JLabel label1 = new JLabel("Please select the BLAST region " +
                "from this short read");
        jPanel1.add(label1);

        panelForTF = new JPanel[20];
        shortReadTF = new JTextField[20];
        highlighter = new DefaultHighlighter[20];

        for (int i = 0; i < 20; i++)
        {
            // Create a panel to put the textfield in
            panelForTF[i] = new JPanel(new FlowLayout());
            this.add(panelForTF[i]);

            // Create the textfield and put it into the panel
            shortReadTF[i] = new JTextField(lengthToDisplay);
            shortReadTF[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
            shortReadTF[i].setHorizontalAlignment(JTextField.CENTER);
            shortReadTF[i].addCaretListener(caretListenerLabel);
            panelForTF[i].add(shortReadTF[i]);

            // Create a highlighter for each textfield
            highlighter[i] = new DefaultHighlighter();
            shortReadTF[i].setHighlighter(highlighter[i]);

            if (i > 0)
            {
                shortReadTF[i].setEnabled(false);
            }
        }

        painter = new DefaultHighlighter.DefaultHighlightPainter(
                Color.LIGHT_GRAY);

    }

    /**
     * If a paired end file exists, this method is called to generate
     * the text boxes for previewing the second file.
     */

    public void generatePairedEndTextBoxes()
    {
        if (m.isPairedEnd() && (m.getHasGeneratedPairedEndTextBoxes() == false))
        {
            // If a paired end file exists and no paired end text boxes
            // have been generated yet, generate them.

            shortReadTF2 = new JTextField[20];
            highlighter2 = new DefaultHighlighter[20];

            for (int i = 0; i < 20; i++)
            {
                // Create the textfield and put it into the panel
                shortReadTF2[i] = new JTextField(lengthToDisplay);
                shortReadTF2[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
                shortReadTF2[i].setHorizontalAlignment(JTextField.CENTER);
                shortReadTF2[i].addCaretListener(caretListenerLabel2);
                panelForTF[i].add(shortReadTF2[i]);

                // Create a highlighter for each textfield
                highlighter2[i] = new DefaultHighlighter();
                shortReadTF2[i].setHighlighter(highlighter2[i]);

                if (i > 0)
                {
                    shortReadTF2[i].setEnabled(false);
                }
            }

            this.setVisible(false);
            this.setVisible(true);

            // Toggle flag to indicate that textfields for the paired end
            // short reads have been generated.
            // DO NOT DELETE THIS LINE, OTHERWISE THE REGION SELECTION
            // SCREENS WILL BREAK
            m.setHasGeneratedPairedEndTextBoxes(true);
        }

        else if ((m.isPairedEnd() == false) &&
                (m.getHasGeneratedPairedEndTextBoxes() == true))
        {
            // If a paired end file does not exist anymore (box is unticked)
            // and the system has once generated the paired end text boxes,
            // remove these textfields.

            for (int i = 0; i < 20; i++)
            {
                panelForTF[i].remove(shortReadTF2[i]);
            }

            this.setVisible(false);
            this.setVisible(true);
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
                    if (dot < mark) //Selection from left to right.
                    {
                        m.setInputBlastRegion(dot, mark);

                        try
                        {
                            if (m.getHasGeneratedPairedEndTextBoxes())
                            {
                                for (int i = 1; i < highlighter.length; i++)
                                {
                                    highlighter2[i].removeAllHighlights();
                                    highlighter[i].removeAllHighlights();
                                    if (event.getSource() == shortReadTF2[0])
                                    {
                                        highlighter2[i].addHighlight
                                                (dot, mark, painter);
                                        selectedPairedEnd = true;
                                    }
                                    else
                                    {
                                        highlighter[i].addHighlight
                                                (dot, mark, painter);
                                        selectedPairedEnd = false;
                                    }
                                }
                            }
                            else
                            {
                                for (int i = 1; i < highlighter.length; i++)
                                {
                                    highlighter[i].removeAllHighlights();
                                    highlighter[i].addHighlight
                                            (dot, mark, painter);
                                }
                                selectedPairedEnd = false;
                            }
                        }
                        catch (BadLocationException ble)
                        {
                            // dsf
                        }
                    }
                    else if (dot > mark) //Selection from right to left.
                    {
                        m.setInputBlastRegion(mark, dot);

                        try
                        {
                            if (m.getHasGeneratedPairedEndTextBoxes())
                            {
                                for (int i = 1; i < highlighter.length; i++)
                                {
                                    highlighter2[i].removeAllHighlights();
                                    highlighter[i].removeAllHighlights();
                                    if (event.getSource() == shortReadTF2[0])
                                    {
                                        highlighter2[i].addHighlight
                                                (dot, mark, painter);
                                        selectedPairedEnd = true;
                                    }
                                    else
                                    {
                                        highlighter[i].addHighlight
                                                (dot, mark, painter);
                                        selectedPairedEnd = false;
                                    }
                                }
                            }
                            else
                            {
                                for (int i = 1; i < highlighter.length; i++)
                                {
                                    highlighter[i].removeAllHighlights();
                                    highlighter[i].addHighlight
                                            (mark, dot, painter);
                                }
                                selectedPairedEnd = false;
                            }
                        }
                        catch (BadLocationException ble)
                        {
                        }
                    }

                    System.out.println("input BLAST selection is from: "
                            + m.getInputBLASTRegionStart() + " to "
                            + m.getInputBLASTRegionEnd());
                    System.out.println("selectedPairedEnd: " +
                            selectedPairedEnd);
                }
            });
        }
    }

    public JTextField[] getShortReadTF()
    {
        return shortReadTF;
    }

    public JTextField[] getShortReadTF2()
    {
        return shortReadTF2;
    }
}
