package InteTest;

//Requires UISpec4J library:
import Controller.Controller;
import Model.ModelInterface;
import View.InputPanel;
import View.ModeSelect;
import View.View;
import javax.swing.JFrame;
import org.uispec4j.UISpecTestCase;

//Requires JUnit 4.1 library:
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.uispec4j.Panel;
import org.uispec4j.Trigger;
import org.uispec4j.UISpec4J;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

/**
 *
 * @author wengn
 */
public class AutomatedBLASTUITest extends UISpecTestCase {
    private Window mainWindow;
    //private JFrame main;

    ModelInterface model = new ModelInterface();
    
    //InputPanel ip = new InputPanel(model);
    ModeSelect ms;

    WindowInterceptor wi;

    static {
        UISpec4J.init();  
    }

    @Before
    public void setUp() {

        /*
        Window appWindow = WindowInterceptor.run(new Trigger() {
        public void run() {
            main(new String[0]);
        }
        });

        */

        ms = new ModeSelect(model);
        mainWindow = new Window(ms);


        //main = new Main();
        //mainframe = new Panel(main);
        
    }

    @Test
    public void testshouldClickBLAST() {
        String title = mainWindow.getTitle();
        System.out.println(title);

        Window window = WindowInterceptor.run(mainWindow.getButton("BLAST").triggerClick());


        window.getPanel();


        //Window win = WindowInterceptor.run(mainframe.getButton("BLAST").click());
        //WindowInterceptor.init(mainframe.getButton("BLAST").click());
         //       .process()
    }

    @After
    public void tearDown() {

    }
/*
    protected void pressBLAST() {
        mainframe.getTree();
        WindowInterceptor
            .init(mainframe.getButton("BLAST").click())
            .process(new WindowHandler("")) {
        }


        }
 
    }*/
}
