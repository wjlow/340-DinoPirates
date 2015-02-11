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

public class InputIDSelection extends JPanel
{
    private ModelInterface m;
    JTextField  sr1;
    JTextField  sr2;
    JTextField  sr3;
    JTextField  sr4;
    JTextField  sr5;
    JButton     update;

    final Highlighter hilit2;
    final Highlighter hilit3;
    final Highlighter hilit4;
    final Highlighter hilit5;
    final Highlighter.HighlightPainter painter;

    private final int lengthToDisplay = 40;

    private final CaretListenerLabel caretListenerLabel =
            new CaretListenerLabel("Caret Status");

    public InputIDSelection(ModelInterface m)
    {
        setLayout(new GridLayout(7, 1));

        this.m = m;

        JPanel jPanel1 = new JPanel(new FlowLayout());
        add(jPanel1);
        JLabel label1  = new JLabel("Please select the ID from this short read");
        jPanel1.add(label1);

        JPanel jPanel2 = new JPanel(new FlowLayout());
        add(jPanel2);
        sr1  = new JTextField(lengthToDisplay);
        sr1.addCaretListener(caretListenerLabel);
        jPanel2.add(sr1);

        JPanel jPanel3 = new JPanel(new FlowLayout());
        add(jPanel3);
        sr2  = new JTextField(lengthToDisplay);
        sr2.addCaretListener(caretListenerLabel);
        jPanel3.add(sr2);

        JPanel jPanel5 = new JPanel(new FlowLayout());
        add(jPanel5);
        sr3  = new JTextField(lengthToDisplay);
        sr3.addCaretListener(caretListenerLabel);
        jPanel5.add(sr3);

        JPanel jPanel6 = new JPanel(new FlowLayout());
        add(jPanel6);
        sr4  = new JTextField(lengthToDisplay);
        sr4.addCaretListener(caretListenerLabel);
        jPanel6.add(sr4);

        JPanel jPanel7 = new JPanel(new FlowLayout());
        add(jPanel7);
        sr5  = new JTextField(lengthToDisplay);
        sr5.addCaretListener(caretListenerLabel);
        jPanel7.add(sr5);

        JPanel jPanel4 = new JPanel(new FlowLayout());
        add(jPanel4);
        update = new JButton("Update");
        //jPanel4.add(update);

        hilit2 = new DefaultHighlighter();
        hilit3 = new DefaultHighlighter();
        hilit4 = new DefaultHighlighter();
        hilit5 = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(
                Color.LIGHT_GRAY);

        sr2.setEnabled(false);
        sr3.setEnabled(false);
        sr4.setEnabled(false);
        sr5.setEnabled(false);

        sr2.setHighlighter(hilit2);
        sr3.setHighlighter(hilit3);
        sr4.setHighlighter(hilit4);
        sr5.setHighlighter(hilit5);

    }

    /**
     * This function displays the previews of the short reads in the right
     * components.
     */
    public final void fillInValues()
    {
        ArrayList text = new ArrayList();
        try
        {
            CSVReader reader = new CSVReader(new FileReader(m.getInputFile()), ':');

            for (int i = 0; i < 6; i++)
            {
                String cutLine[] = reader.readNext();
                text.add(cutLine[5]);
            }
            reader.close();
            sr1.setText(text.get(1).toString());
            sr2.setText(text.get(2).toString());
            sr3.setText(text.get(3).toString());
            sr4.setText(text.get(4).toString());
            sr5.setText(text.get(5).toString());
        }
        catch(IOException ie)
        {

        }
    }

    protected class CaretListenerLabel extends JLabel implements CaretListener
    {
        public CaretListenerLabel(final String label)
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
                        m.setInputIDRegion(dot, mark);

                        try
                        {
                            hilit2.removeAllHighlights();
                            hilit2.addHighlight(dot, mark, painter);
                            hilit3.removeAllHighlights();
                            hilit3.addHighlight(dot, mark, painter);
                            hilit4.removeAllHighlights();
                            hilit4.addHighlight(dot, mark, painter);
                            hilit5.removeAllHighlights();
                            hilit5.addHighlight(dot, mark, painter);
                        }
                        catch (BadLocationException ble)
                        {
                            // dsf
                        }
                    }
                    else if (dot > mark)
                    {
                        m.setInputIDRegion(mark, dot);

                        try
                        {
                            hilit2.removeAllHighlights();
                            hilit2.addHighlight(mark, dot, painter);
                            hilit3.removeAllHighlights();
                            hilit3.addHighlight(mark, dot, painter);
                            hilit4.removeAllHighlights();
                            hilit4.addHighlight(mark, dot, painter);
                            hilit5.removeAllHighlights();
                            hilit5.addHighlight(mark, dot, painter);
                        }
                        catch (BadLocationException ble)
                        {
                        }
                    }

                    System.out.println("input ID selection is from: "
                            + m.getInputIDStart() + " to " + m.getInputIDEnd());

                }
            });
        }
    }
}
