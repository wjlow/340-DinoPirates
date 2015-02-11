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
 */
public class Controller
{
    private ModelInterface model;
    private View view;

    /**
     * The object is created here with the ModelInterface and the View passed in
     * as arguments.
     */
    public Controller()
    {

    }

    /**
     * This method initialises the listeners that exist within the
     * Controller component.
     */
    public void initController()
    {
        view.addInputTabChangeListener(new InputTabChangeListener(model, view));

        if (!model.isBLASTMode())
        {
            view.getInputPanel().getInputFileSelection().
              addInputFileListener(new InputFileSelectionListener(model, view));
            view.getInputPanel().getInputFileSelection().
                    addPairedEndListener(new PairedEndListener(model, view));

            view.getInputPanel().getInputQualityPanel().addQualityListener(
                   new QualityListener(model, view));
            view.getInputPanel().getInputQualityPanel()
                     .addQualityThresholdFocusListener(
                    new QualityListener(model, view));

            view.getInputPanel().getInputIDSelection().
                    addActionListeners(
                    new InputIDSelectionListener(model, view));

            view.getInputPanel().getInputGroupingPanel().
                    addGroupingListener(new GroupListener(model, view));
            view.getInputPanel().
                    getInputEnd().addTagActionListeners(
                    new TagFilePreprocessListener(model, view));
        }

        if (model.isBLASTMode())
        {
            view.getInputPanel().getInputFileSelection().
              addInputFileListener(new InputFileSelectionListener(model, view));

            view.getInputPanel().getInputIDSelection().
                    addActionListeners(
                    new InputIDSelectionListener(model, view));

            view.getInputPanel().getInputRegionSelection().
                    addActionListeners(
                    new InputRegionSelectionListener(model, view));

            //v.addReferenceFileParser(new ReferenceFileParser(m, v));
            view.getReferencePanel().getRefFileSelection().
                    addRefFileSelectionListener(
                    new RefFileSelectionListener(model, view));
            view.addReferenceTabChangeListener(
                    new ReferenceTabChangeListener(model, view));

            view.addBLASTTabListener(new BLASTTabListener(model, view));

            view.getBLASTPanel().getQueryBLASTPanel()
                    .addBLASTListener(new BLASTListener(model, view));

            view.getBLASTPanel().getAlignmentToolSelect()
                    .addToolsSelectListener(
                    new AlignmentSelectListener(model, view));

            view.getBLASTPanel().getParametersPanel().addParamListener(
                    new ParametersListener(model, view));

            view.getReferencePanel().getRefFileSelection().setRefFileParser(
                    new ReferenceFileParser(model, view));

            view.getReferencePanel().getRefIDRegionSelection()
                    .addSelectionListener(
                    new ReferenceHeaderSelectionListener(model, view));

            view.getReferencePanel().getRefColumnChooser().
                    addNavigationListener(
                    new RefColumnHeaderChooserListener(model, view));

            view.getReferencePanel().getRefBLASTRegionSelection().
                    addRefBlastRegionSelectionListener(
                    new RefBLASTRegionSelectionListener(model, view));
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

    public void setView(View v)
    {
        view = v;
    }

    public void setModelInterface(ModelInterface m)
    {
        model = m;
    }
}
