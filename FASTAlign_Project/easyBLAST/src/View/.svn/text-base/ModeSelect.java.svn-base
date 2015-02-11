package View;

import Model.ModelInterface;
import Controller.Controller;
import Controller.ModeSelectListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * A JPanel which allows the user to select the flow of the system.
 * Based on the user's decision, the correct View is displayed.
 * @author lawrence
 */
public class ModeSelect extends JFrame
{
    private View view;
    private Controller controller;
    private ModelInterface model;

    private JButton preprocessingFlowButton;
    private JButton blastFlowButton;

    private ModeSelectListener modeSelectListener;

    /**
     * The contructor takes in a ModelInterface and sets the flow of the system
     * there.
     * @param m The ModelInterface to set.
     */
    public ModeSelect(ModelInterface m, View v, Controller c)
    {
        this.model = m;
        this.view = v;
        this.controller = c;

        modeSelectListener = new ModeSelectListener(this);

        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
          //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        preprocessingFlowButton = new JButton("Pre-processing Flow");
        preprocessingFlowButton.setMnemonic('P');
        preprocessingFlowButton.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        preprocessingFlowButton.addActionListener(modeSelectListener);
        bottomPanel.add(preprocessingFlowButton);

        blastFlowButton = new JButton("Alignment Flow");
        blastFlowButton.setMnemonic('A');
        blastFlowButton.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        blastFlowButton.addActionListener(modeSelectListener);
        bottomPanel.add(blastFlowButton);

        JLabel topLabel = new JLabel("Please select a flow to begin with...");
        topLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        topLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        topLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        topPanel.add(topLabel);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        setTitle("FASTAlign");
        setSize(600, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 600, 150);
    }

    public JButton getPreprocessingFlowButton()
    {
        return preprocessingFlowButton;
    }

    public JButton getBlastFlowButton()
    {
        return blastFlowButton;
    }

    public ModelInterface getModelInterface()
    {
        return model;
    }

    public Controller getController()
    {
        return controller;
    }

    public View getView()
    {
        return view;
    }
}
