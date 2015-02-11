package View.Reference;

import Model.ModelInterface;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import Model.opencsv.CSVReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

/**
 * This RefBLASTRegionSelection panel contains the layout and structure of the
 * panel.
 * @author lbang
 */
public class RefBLASTRegionSelection extends JPanel
{
    private ModelInterface model;

    private final int numberOfReadsToDisplay = 20;

    private JTextField[] shortRead = new JTextField[numberOfReadsToDisplay];
    private JPanel[] panel = new JPanel[numberOfReadsToDisplay];
    private Highlighter[] highlight =
            new DefaultHighlighter[numberOfReadsToDisplay];

    private CSVReader reader;

    private final Highlighter.HighlightPainter painter;

    private CaretListenerLabel caretListenerLabel =
            new CaretListenerLabel("Caret Status");

    private JLabel selectionRegionLabel = new JLabel("No region selected");

    private JButton backButton;
    private JButton nextButton;

    public RefBLASTRegionSelection(ModelInterface m)
    {
        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(numberOfReadsToDisplay
                + 3, 1));
        this.add(centerPanel, BorderLayout.CENTER);
        this.model = m;

        JPanel labelPanel = new JPanel(new FlowLayout());
        this.add(labelPanel, BorderLayout.NORTH);
        JLabel mainLabel  = new JLabel("Please select the alignment region for "
                + "each DNA sequence in the reference file(s).");
        mainLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        labelPanel.add(mainLabel);

        for (int i = 0; i < numberOfReadsToDisplay; i++)
        {
            panel[i] = new JPanel(new FlowLayout());
            centerPanel.add(panel[i]);

            shortRead[i] = new JTextField(99);
            shortRead[i].setFont(new Font("Monospaced", Font.PLAIN, 11));
            shortRead[i].setHorizontalAlignment(JTextField.CENTER);
            shortRead[i].addCaretListener(caretListenerLabel);
            shortRead[i].setEnabled(false);
            panel[i].add(shortRead[i]);

            highlight[i] = new DefaultHighlighter();
            shortRead[i].setHighlighter(highlight[i]);

            if (i == 0)
            {
                shortRead[0].setEnabled(true);
                shortRead[0].setEditable(false);
            }
        }

        JPanel selectionRegionLabelPanel = new JPanel(new FlowLayout());
        selectionRegionLabelPanel.add(selectionRegionLabel);
        centerPanel.add(selectionRegionLabelPanel);

        painter = new DefaultHighlighter
                .DefaultHighlightPainter(Color.DARK_GRAY);

        JPanel navigationButtonsPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');

        navigationButtonsPanel.add(backButton);
        navigationButtonsPanel.add(nextButton);
        this.add(navigationButtonsPanel, BorderLayout.SOUTH);
    }

    public void showPreviewSequences()
    {
        List text = new ArrayList();
        try
        {
            reader = new CSVReader(
                    new FileReader(model.getRefFileList().get(0)));
            String[] cutLine = reader.readNext();

            for (int i = 0; i < numberOfReadsToDisplay; i++)
            {
                cutLine = reader.readNext();
                text.add(cutLine[model.getRefRegionColumn()]);
                shortRead[i].setText(cutLine[model.getRefRegionColumn()]);
            }
            reader.close();
        }
        catch (IOException ie)
        {

        }
    }

    protected class CaretListenerLabel extends JLabel implements CaretListener
    {
        public CaretListenerLabel(String label)
        {
            super(label);
        }

        public void caretUpdate(CaretEvent e)
        {
            displaySelectionInfo(e.getDot(), e.getMark());
        }

        protected void displaySelectionInfo(final int dot, final int mark)
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    if (dot < mark)
                    {
                        model.setBLASTRegion(dot, mark);

                        for (int i = 1; i < numberOfReadsToDisplay; i++)
                        {
                            try
                            {
                                highlight[i].removeAllHighlights();
                                highlight[i].addHighlight(dot, mark, painter);
                            }
                            catch (BadLocationException ble)
                            {
                            }
                        }
                    }
                    else if (dot > mark)
                    {
                        model.setBLASTRegion(mark, dot);

                        for (int i = 1; i < numberOfReadsToDisplay; i++)
                        {
                            try
                            {
                                highlight[i].removeAllHighlights();
                                highlight[i].addHighlight(mark, dot, painter);
                            }
                            catch (BadLocationException ble)
                            {
                            }
                        }
                    }

                    if (model.getBLASTRegionStart() == 0 &&
                            model.getBLASTRegionEnd() == 0)
                    {
                        selectionRegionLabel.setText("You have selected from "
                            + model.getBLASTRegionStart() + " to "
                            + model.getBLASTRegionEnd()
                            + " as the alignment region");
                    }
                    else
                    {
                        selectionRegionLabel.setText("You have selected from "
                            + (model.getBLASTRegionStart() + 1) + " to "
                            + model.getBLASTRegionEnd()
                            + " as the alignment region");
                    }
                }
            });
        }
    }

    public void addRefBlastRegionSelectionListener(ActionListener al)
    {
        backButton.addActionListener(al);
        nextButton.addActionListener(al);
    }

    public JButton getBackButton()
    {
        return backButton;
    }

    public JButton getNextButton()
    {
        return nextButton;
    }
}
