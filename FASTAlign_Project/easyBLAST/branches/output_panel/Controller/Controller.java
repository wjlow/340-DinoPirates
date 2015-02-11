package Controller;

import View.View;
import Model.ModelInterface;
import Model.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Controller class created here with access to the ModelInterface and a
 * View.
 * @author lawrence
 */
public class Controller
{

    private ModelInterface model;
    private View view;

    /**
     * The object is created here with the ModelInterface and the View passed in
     * as arguments.
     * @param m The ModelInterface the Controller has access to.
     * @param v The View the Controller has access to.
     */
    public Controller(final ModelInterface m, final View v)
    {
        model = m;
        view = v;

        view.addInputParser(new InputFileParser(model));
        view.addInputTabChangeListener(new InputTabChangeListener(model, view));
        if (!model.isBLASTMode())
        {
            view.addQualityListener(new QualityListener(model));
        }

        if (model.isBLASTMode())
        {
            //v.addReferenceFileParser(new ReferenceFileParser(m, v));
            view.addReferenceTabChangeListener(
                    new ReferenceTabChangeListener(model, view));
        }
    }

    /**
     * Accepts a File object and appends its contents to the JTable on-screen.
     * @param file The file to be parsed.
     */
    public final void addData(final File file)
    {
        try
        {
            // Reads the File object.
            // \t is used as delimiter.
            CSVReader reader = new CSVReader(new FileReader(file), '\t');

            //try adding dummy data
            String[] line;

            while ((line = reader.readNext()) != null)
            {
                view.getOutputPanel().addData(file);

            }
        }
        catch (IOException e)
        {
            Logger.getLogger(ModelInterface.class.getName()).log(Level.SEVERE,
                    null, e);
        }
    }
}
