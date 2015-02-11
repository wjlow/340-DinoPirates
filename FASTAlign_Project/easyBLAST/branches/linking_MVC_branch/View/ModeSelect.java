package View;

import Model.ModelInterface;
import Controller.Controller;
import Controller.ModeSelectListener;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

/**
 * A JPanel which allows the user to select the flow of the system.
 * Based on the user's decision, the correct View is displayed.
 * @author lawrence
 */
public class ModeSelect extends JFrame
{
    View view;
    Controller controller;
    private ModelInterface model;

    private JButton preprocessingFlowButton;
    private JButton blastFlowButton;

    private final int standardFontSize = 12;
    private ModeSelectListener modeSelectListener;

    /**
     * The contructor takes in a ModelInterface and sets the flow of the system
     * there.
     * @param m The ModelInterface to set.
     */
    public ModeSelect(final ModelInterface m,
            final View view, final Controller controller)
    {
        this.model = m;
        this.view = view;
        this.controller = controller;


        modeSelectListener = new ModeSelectListener(this);

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());

        preprocessingFlowButton = new JButton("Preprocess");
        preprocessingFlowButton.setFont(new Font(
                "Trebuchet MS", Font.PLAIN, standardFontSize));
        preprocessingFlowButton.addActionListener(modeSelectListener);
        bottomPanel.add(preprocessingFlowButton);

        blastFlowButton = new JButton("BLAST");
        blastFlowButton.setFont(new Font(
                "Trebuchet MS", Font.PLAIN, standardFontSize));
        blastFlowButton.addActionListener(modeSelectListener);
        bottomPanel.add(blastFlowButton);

        JLabel topLabel = new JLabel("Please select a flow to begin with...");
        topLabel.setFont(new Font(
                "Trebuchet MS", Font.BOLD, standardFontSize * 2));
        topLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        topLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        topPanel.add(topLabel);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        add(mainPanel);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        setTitle("easyBLAST");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
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
