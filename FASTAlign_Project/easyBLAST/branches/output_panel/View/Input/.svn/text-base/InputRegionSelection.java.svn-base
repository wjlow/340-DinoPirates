package View.Input;

import Model.ModelInterface;
import Model.opencsv.CSVReader;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputRegionSelection extends JPanel
{
    ModelInterface       m;
    JLabel      label1;
    JTextField  sr1;
    JTextField  sr2;
    JTextField  sr3;
    JTextField  sr4;
    JTextField  sr5;
    JButton     update;
    JPanel      jPanel1;
    JPanel      jPanel2;
    JPanel      jPanel3;
    JPanel      jPanel4;
    JPanel      jPanel5;
    JPanel      jPanel6;
    JPanel      jPanel7;

    final Highlighter hilit2;
    final Highlighter hilit3;
    final Highlighter hilit4;
    final Highlighter hilit5;
    final Highlighter.HighlightPainter painter;


    CaretListenerLabel caretListenerLabel = new CaretListenerLabel("Caret Status");

    public InputRegionSelection(ModelInterface m)
    {
        setLayout(new GridLayout(7,1));

        this.m = m;

        jPanel1 = new JPanel(new FlowLayout());
        add(jPanel1);
        label1  = new JLabel("Please select the ID from this short read");
        jPanel1.add(label1);

        jPanel2 = new JPanel(new FlowLayout());
        add(jPanel2);
        sr1  = new JTextField(40);
        sr1.addCaretListener(caretListenerLabel);
        jPanel2.add(sr1);
        
        jPanel3 = new JPanel(new FlowLayout());
        add(jPanel3);
        sr2  = new JTextField(40);
        sr2.addCaretListener(caretListenerLabel);
        jPanel3.add(sr2);

        jPanel5 = new JPanel(new FlowLayout());
        add(jPanel5);
        sr3  = new JTextField(40);
        sr3.addCaretListener(caretListenerLabel);
        jPanel5.add(sr3);

        jPanel6 = new JPanel(new FlowLayout());
        add(jPanel6);
        sr4  = new JTextField(40);
        sr4.addCaretListener(caretListenerLabel);
        jPanel6.add(sr4);

        jPanel7 = new JPanel(new FlowLayout());
        add(jPanel7);
        sr5  = new JTextField(40);
        sr5.addCaretListener(caretListenerLabel);
        jPanel7.add(sr5);

        jPanel4 = new JPanel(new FlowLayout());
        add(jPanel4);
        update = new JButton("Update");
        //jPanel4.add(update);

        hilit2 = new DefaultHighlighter();
        hilit3 = new DefaultHighlighter();
        hilit4 = new DefaultHighlighter();
        hilit5 = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);

        sr2.setEnabled(false);
        sr3.setEnabled(false);
        sr4.setEnabled(false);
        sr5.setEnabled(false);

        sr2.setHighlighter(hilit2);
        sr3.setHighlighter(hilit3);
        sr4.setHighlighter(hilit4);
        sr5.setHighlighter(hilit5);
    }

    public void fillInValues()
    {
        ArrayList text = new ArrayList();
        try
        {
            CSVReader reader = new CSVReader(new FileReader(m.getInputFile()), ':');
            System.out.println("file read");
            for (int i = 0; i < 6; i++)
            {
                String cutLine[] = reader.readNext();
                System.out.println(i);
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
            Logger.getLogger(ModelInterface.class.getName()).log(Level
                    .SEVERE, null, ie);
        }

 /*       sr1.setText(m.getInputLineList().get(0).getShortRead());
        sr2.setText(m.getInputLineList().get(1).getShortRead());
        sr3.setText(m.getInputLineList().get(2).getShortRead());
        sr4.setText(m.getInputLineList().get(3).getShortRead());
        sr5.setText(m.getInputLineList().get(4).getShortRead());*/
    }

    protected class CaretListenerLabel extends JLabel implements CaretListener {
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
                        m.setInputBlastRegion(dot, mark);
                        
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
                        }
                    }
                    else if ( dot > mark )
                    {
                        m.setInputBlastRegion(mark, dot);

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

                    System.out.println("input region is selected from: "
                            + m.getRegionStart() + " to " + m.getRegionEnd() );

                }
            });
        }
    }
}
