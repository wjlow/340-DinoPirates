import Controller.Controller;
import View.ModeSelect;
import Model.ModelInterface;
import View.View;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

/**
 * The Main class contains the main function which is necessary for the start
 * up of the code.
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
        ModelInterface model = new ModelInterface();
        View view = new View();
        Controller controller = new Controller();

        // Set View and Model in Controller
        controller.setModelInterface(model);
        controller.setView(view);

        // Set Controller and Model in View
        view.setModelInterface(model);
        view.setController(controller);

        // Set View in Model (for updating Pre-processing progress)
        model.setView(view);
        model.getPreProcessingHandler().setView(view);

        // Instantiate the first screen of the program and make it visible
        ModeSelect modeSelect = new ModeSelect(model, view, controller);
        modeSelect.setVisible(true);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();

        File dir = new File(
                "FASTAlign_run" + File.separator + dateFormat.format(date));

        if (dir.mkdirs())
        {
            System.out.println(dir.getAbsolutePath() + " was created.");
            System.setProperty("user.dir", dir.getAbsolutePath());
        }
    }
}
