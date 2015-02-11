import View.ModeSelect;
import Model.ModelInterface;
import javax.swing.JFrame;

/**
 * The Main class contains the main function which is necessary for the start
 * up of the code.
 * @author lawrence
 */
public class Main extends JFrame
{
    /**
     * Initialises a JPanel with a choice for either the Preprocessing Flow or
     * the BLAST Flow. A ModelInterface is created and is passed around for use
     * by other classes.
     */
    public static void main(final String[] args)
    {
        ModelInterface m = new ModelInterface();
        ModeSelect ms = new ModeSelect(m);

        ms.setVisible(true);
    }
}
