package View.Input;

import Controller.TagFilePreprocessListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Collections;
import Model.*;

public class InputEnd extends JPanel
{
    JButton     generateTag;
    JButton     finish;
    JFileChooser fileChooser;

    TagFilePreprocessListener tagFilePreprocess;
    private ModelInterface m;

    public InputEnd(ModelInterface m)
    {
        this.m = m;

        setLayout(new GridLayout(3,1));

        JPanel jPanel1 = new JPanel(new FlowLayout());
        this.add(jPanel1);

        generateTag = new JButton("Generate tag Files");
        generateTag.addActionListener(tagFilePreprocess);
        jPanel1.add(generateTag);

        JPanel jPanel2 = new JPanel(new FlowLayout());
        this.add(jPanel2);
        
        finish = new JButton("PreProcess");
        finish.addActionListener(tagFilePreprocess);
        jPanel2.add(finish);
    }

    public void addTagActionListeners(ActionListener al)
    {
        generateTag.addActionListener(al);
        finish.addActionListener(al);
    }
    public JButton getGenerateTag()
    {
        return generateTag;
    }

    public JButton getPreProcess()
    {
        return finish;
    }

    public JFileChooser getFileChooser()
    {
        return fileChooser;
    }
}
