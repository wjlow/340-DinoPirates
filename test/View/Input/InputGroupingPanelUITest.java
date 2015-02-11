package View.Input;

//Requires UISpec4J library:
import Model.ModelInterface;
import Model.PreProcessingHandler;
import java.io.File;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Panel;

//Requires JUnit 4.1 library:
import org.junit.Test;
import org.junit.After;
import org.junit.Before;



/**
 *
 * @author wengn
 */

public class InputGroupingPanelUITest extends UISpecTestCase {
    private Panel panel;
    private ModelInterface m;
    private File inputFile = new File(".//testdata//s_1_1_sequence.txt");
    private InputGroupingPanel igp;
    private PreProcessingHandler pph = new PreProcessingHandler();


    @Before
    public void setUp() {
        m = new ModelInterface();
        m.setInputFile(inputFile);
        igp = new InputGroupingPanel(m);
        panel = new Panel(igp);
        
    }

    @Test
    public void testshouldGroupActionPerform() {

        assertTrue(panel.getTextBox("group1").textIsEmpty());
        panel.getTextBox("group1").appendText("CGGA,AGAG,CGGT");

        assertTrue(panel.getTextBox("group2").textIsEmpty());
        panel.getTextBox("group2").appendText("TCAG,CAGA");

        panel.getButton("Group!").click();
    }

    @After
    public void tearDown() {
        
    }

}