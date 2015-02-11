package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wengn
 */
public class InputLineTest {
    private InputLine il;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;

    @Before
    public void setUp() {
        c1 = "USI-EAS39_8218_FC30495_PE";
        c2 = "1";
        c3 = "1";
        c4 = "155";
        c5 = "1404/1";
        c6 = "GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG";
        c7 = "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb";

    }

    /**
     * Test if column 6 has only AGCT characters
     */
    @Test
    public void testCol6AGCTChar() {
        System.out.println("Col6AGCTChar");
        boolean b = c6.matches("[ACGT]+");

        assertTrue(b);
    }

    /**
     * Test if column 6 has non-AGCT characters
     */
    @Test
    public void testCol6NonAGCTChar() {
        System.out.println("Col6NonAGCTChar");
        c6 = c6 + "SD";
     //   il = new InputLine(c1,c2,c3,c4,c5,c6,c7);
        boolean b = c6.matches("[ACGT]+");

        assertFalse(b);
    }


}