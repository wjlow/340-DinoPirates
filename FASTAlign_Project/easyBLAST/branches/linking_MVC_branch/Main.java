import Controller.Controller;
import View.ModeSelect;
import Model.ModelInterface;
import View.View;
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
     * @param args Not used, but is needed for the main function.
     */
    public static void main(final String[] args)
    {
        // Instantiate Model, View and Controller
        View view = new View();
        ModelInterface model = new ModelInterface();
        Controller controller = new Controller();

        // Set View and Model in Controller
        controller.setModelInterface(model);
        controller.setView(view);

        // Set Controller and Model in View
        view.setModelInterface(model);
        view.setController(controller);

        // Instantiate the first screen of the program and make it visible
        ModeSelect modeSelect = new ModeSelect(model, view, controller);
        modeSelect.setVisible(true);

        

        
    }
}
