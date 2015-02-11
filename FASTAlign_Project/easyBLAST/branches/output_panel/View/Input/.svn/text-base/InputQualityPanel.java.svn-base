package View.Input;

import Model.ModelInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class InputQualityPanel extends JPanel implements ActionListener
{
    JLabel      mainLabel;
    JLabel      asciiStringLabel;
    JTextField  asciiSumLimit;
    JLabel      asciiMinLabel;
    JTextField  asciiMinLimit;
    JButton     removeBelowThreshold, saveFile;

    JPanel      panel1, panel2, panel3, panel4;
    JButton saveChanges;
    ModelInterface m;

    public InputQualityPanel(ModelInterface m)
    {
        this.m = m;
        setLayout(new GridLayout(4,1));

        panel1 = new JPanel(new FlowLayout());
        add(panel1);
        mainLabel = new JLabel("Specify your quality threshold here");
        panel1.add(mainLabel);

        panel2 = new JPanel(new FlowLayout());
        add(panel2);
        asciiMinLabel = new JLabel("Minimum ASCII Value: ");
        asciiMinLimit = new JTextField(m.getMinASCII().toString(), 3);
        panel2.add(asciiMinLabel);
        panel2.add(asciiMinLimit);

        panel3 = new JPanel(new FlowLayout());
        add(panel3);
        asciiStringLabel = new JLabel("Minimum ASCII Sum: ");
        asciiSumLimit = new JTextField(m.getMinSum().toString(), 3);
        asciiMinLimit.addActionListener(this);
        asciiSumLimit.addActionListener(this);
        panel3.add(asciiStringLabel);
        panel3.add(asciiSumLimit);

        panel4 = new JPanel(new FlowLayout());
        add(panel4);
        
        saveChanges = new JButton("Store Thresholds");
        //removeBelowThreshold = new JButton("Trim!");
        saveFile = new JButton("Save Results");

        saveChanges.addActionListener(this);
        
        //panel4.add(removeBelowThreshold);
        panel4.add(saveChanges);
        panel4.add(saveFile);

       // removeBelowThreshold.addActionListener(this);
        //saveFile.addActionListener(this);
    }

    public void addQualityListener(ActionListener ae)
    {
     //   m.setMinASCII(Integer.parseInt(asciiMinLimit.getText()));
      //  m.setMinSum(Integer.parseInt(asciiSumLimit.getText()));
      //  System.out.println("removing those below "  + asciiMinLimit.getText() + ", " + m.getMinSum());
        saveFile.addActionListener(ae);
    }
    public void actionPerformed(ActionEvent e)
    {
  /*      if(e.getSource() == removeBelowThreshold)
        {
            for(int i =0; i<m.getInputLineList().size(); i++)
            {
                if (m.getInputLineList().get(i).getQualityMin() < Integer.parseInt(asciiMinLimit.getText())
                        || m.getInputLineList().get(i).getQualitySum() < Integer.parseInt(asciiSumLimit.getText()))
                {
                    m.getInputLineList().remove(i);
                    i--;
                }
            }
            m.printDetailedShortReadList();
        }

        if(e.getSource() == saveFile)
        {
            String filename = m.getFile1().getName();

            try {
                FileWriter f0 = new FileWriter("C:\\" + filename + "_QUALITY_.txt", false);
                for (int i =0; i<m.getInputLineList().size(); i++)
                {
                    System.out.print(m.printInputLine(i));
                    f0.write(m.printInputLine(i));
                }
                f0.close();
            }
            catch(IOException ioe)
            {

            }
        }*/

        if (e.getSource() == asciiMinLimit)
        {
            m.setMinASCII(Integer.parseInt(asciiMinLimit.getText()));
            System.out.println("a");
        }

        if (e.getSource() == asciiSumLimit)
        {
            m.setMinSum(Integer.parseInt(asciiSumLimit.getText()));
            System.out.println("b");
        }

        if (e.getSource() == saveChanges)
        {
            m.setMinSum(Integer.parseInt(asciiSumLimit.getText()));
            m.setMinASCII(Integer.parseInt(asciiMinLimit.getText()));
            System.out.println("b");
        }
    }
}
