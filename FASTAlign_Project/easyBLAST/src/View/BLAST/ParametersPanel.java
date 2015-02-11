package View.BLAST;

import Controller.ParametersListener;
import Model.Parameters;
import Model.ModelInterface;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.event.ChangeListener;

public class ParametersPanel extends JPanel
{
    private JPanel[] parameters_panel;
    private JPanel[] parameters_panel2;
    private JLabel[] paramText;
    private JTextField[] paramValue;
    private JFrame frame;

    private JButton storeVals;
    private JButton backButton;
    private JButton nextButton;

    private Parameters param;

    private ParametersListener paramListener;

    private ModelInterface model;

    public ParametersPanel(ModelInterface m)
    {
        model = m;
        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1, BorderLayout.NORTH);
        JLabel label1 = new JLabel("Please enter the values for the parameters");
        label1.setFont(new Font("Sans Serif", Font.BOLD, 18));
        panel1.add(label1);

        int numberParams = model.getListofTools().
                get(model.getAlgorithmIndex()).getAlignParameters().length;

        JPanel panel11 = new JPanel(new GridLayout(numberParams, 2));
        add(panel11, BorderLayout.CENTER);

        parameters_panel = new JPanel[numberParams + 1];
        parameters_panel2 = new JPanel[numberParams + 1];
        paramText = new JLabel[numberParams + 1];
        paramValue = new JTextField[numberParams + 1];
        
        for (int i = 0; i < numberParams; i++)
        {
            parameters_panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            parameters_panel2[i] = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panel11.add(parameters_panel[i]);
            panel11.add(parameters_panel2[i]);
            paramText[i] = new JLabel(model.getListofTools().
                    get(model.getAlgorithmIndex()).
                    getAlignParameters()[i].getText());
            paramValue[i] = new JTextField(model.getListofTools().
                    get(model.getAlgorithmIndex()).
                    getAlignParameters()[i].getValue(), 15);
            parameters_panel[i].add(paramText[i]);
            parameters_panel2[i].add(paramValue[i]);
        }

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(southPanel, BorderLayout.SOUTH);
        backButton = new JButton("Back");
        backButton.setMnemonic('B');
        storeVals = new JButton("Store");
        nextButton = new JButton("Next");
        nextButton.setMnemonic('N');
        
        southPanel.add(backButton);
        southPanel.add(nextButton);
       // storeVals.addActionListener(this);
    }

    public JTextField[] getJPanels()
    {
        return paramValue;
    }

    public String getValues(int i)
    {
        return paramValue[i].getText();
    }

    public final void addParamListener(final ActionListener ae)
    {
        //storeVals.addActionListener(ae);
        backButton.addActionListener(ae);
        nextButton.addActionListener(ae);
        
    }

    public final void addParamListener2(final FocusListener ce)
    {
        int numberParams = model.getListofTools().
                get(model.getAlgorithmIndex()).getAlignParameters().length;
        for (int i = 0; i < numberParams; i++)
        {
            paramValue[i].addFocusListener(ce);
        }
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
