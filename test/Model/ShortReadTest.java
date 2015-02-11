/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ShortReadTest {
    private ShortRead sr;
    private ShortRead sr1;
    String id;
    private String region;

    @Before
    public void setUp() {
        id = "abc123";
        region = "AGCTTT";
        sr = new ShortRead(id , region);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCount() {
        System.out.println("addTwoShortRead");
        
        id = "def456";
        region = "GATCT";

        sr1 = new ShortRead(id, region);

        Integer expresult = 1;
        Integer result = sr1.getCount();

        assertEquals(expresult, result);

    }

    /**
     * Test of getID method, of class ShortRead.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        ShortRead instance = new ShortRead(id, region);
        String expResult = "abc123";
        String result = instance.getID();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getRegion method, of class ShortRead.
     */
    @Test
    public void testGetRegion() {
        System.out.println("getRegion");
        ShortRead instance = new ShortRead(id, region);
        String expResult = "AGCTTT";
        String result = instance.getRegion();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCount method, of class ShortRead.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        ShortRead instance = new ShortRead(id, region);
        Integer expResult = 1;
        Integer result = instance.getCount();
        assertEquals(expResult, result);
        
    }

}