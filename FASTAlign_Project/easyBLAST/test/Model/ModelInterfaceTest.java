package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class ModelInterfaceTest {
    File inputFile;
    InputLine iL;
    
    List<String> groups;
    int idColumn;
    int idLeft;
    int idRight;
    int inBlastRegionLeft;
    int inBlastRegionRight;
    int qualitySum;
    int qualityASCII;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        inputFile = new File(".\\testdata\\s_1_1_sequence.txt") ;
        
        groups = new ArrayList<String>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPreviewLines method, of class ModelInterface.
     * This test uses a 20-line input file
     */
    @Test
    public void testGetPreviewLessEqual20Lines() throws Exception {
        System.out.println("getPreview=<20Lines");


        ModelInterface instance = new ModelInterface(inputFile, groups, idColumn,
            idLeft, idRight, qualitySum, qualityASCII);
        List expResult = new ArrayList<String>();

        //1st
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GACCTGCTTTGTTAAGTTTGCGTTGGGCCTGCAAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        //10th
        expResult.add("GAGATTGCTTAGTGGTATGGTATTTGGCTAGTTTT");
        expResult.add("GATGTTTTTATTCCGCCTACCATTCTGTGGTGTCT");
        expResult.add("GCAAGATCGGAAGAGCGGTTCAGCAGGAATGCCGA");
        expResult.add("GCTCTTCCGATCTACGTGGCCCTTGAGATCGGAAG");
        expResult.add("GCATTTCATAGCCTTTTATTAATTTTTGGATTCGT");
        expResult.add("GCAAGATCGGAAGAGCGGTTCAGCAGGAATGCCGA");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        //20th
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        
        //List result = instance.getPreviewLines();

//        assertEquals(expResult, result);
        
    }

        /**
     * Test of getPreviewLines method, of class ModelInterface.
     * This test uses a 21-line input file
     */
    @Test
    public void testGetPreviewMore20Lines() throws Exception {
        System.out.println("getPreview>20Lines");

        ModelInterface instance = new ModelInterface(inputFile, groups, idColumn,
            idLeft, idRight, qualitySum, qualityASCII);
        List expResult = new ArrayList<String>();

        //1st
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GACCTGCTTTGTTAAGTTTGCGTTGGGCCTGCAAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        //10th
        expResult.add("GAGATTGCTTAGTGGTATGGTATTTGGCTAGTTTT");
        expResult.add("GATGTTTTTATTCCGCCTACCATTCTGTGGTGTCT");
        expResult.add("GCAAGATCGGAAGAGCGGTTCAGCAGGAATGCCGA");
        expResult.add("GCTCTTCCGATCTACGTGGCCCTTGAGATCGGAAG");
        expResult.add("GCATTTCATAGCCTTTTATTAATTTTTGGATTCGT");
        expResult.add("GCAAGATCGGAAGAGCGGTTCAGCAGGAATGCCGA");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        //20th
        expResult.add("GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG");
        
//        List result = instance.getPreviewLines();

//        assertEquals(expResult, result);
        
    }


    /**
     * Test of printShortReadList method, of class ModelInterface.
     */
    @Test
    public void testPrintShortReadList() {
        System.out.println("printShortReadList");
        ModelInterface instance = new ModelInterface();
        instance.printShortReadList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printInputLine method, of class ModelInterface.
     */
    @Test
    public void testPrintInputLine() {
        System.out.println("printInputLine");
        int i = 0;
        ModelInterface instance = new ModelInterface();
        String expResult = "";
        String result = instance.printInputLine(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printDetailedShortReadList method, of class ModelInterface.
     */
    @Test
    public void testPrintDetailedShortReadList() {
        System.out.println("printDetailedShortReadList");
        ModelInterface instance = new ModelInterface();
        instance.printDetailedShortReadList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }   

    /**
     * Test of convertToInputFASTA method, of class ModelInterface.
     */
    @Test
    public void testConvertToInputFASTA() {
        System.out.println("convertToInputFASTA");
        ModelInterface instance = new ModelInterface();
        instance.convertInputToFASTA();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readInputFile method, of class ModelInterface.
     */
    @Test
    public void testReadInputFile() throws Exception {
        System.out.println("readInputFile");
        ModelInterface instance = new ModelInterface();

        // Create a expected List of InputLine
        List<InputLine> expiList = new ArrayList<InputLine>();

        // Initialise 20 expected InputLines to stored in expiList
        // These InputLines are taken from the testdata/s_1_1_sequence.txt
        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "155","1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb");
        expiList.add(iL);
        
        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "161","1271/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhhhVhhhhUThPhhhhhhhcRdGg");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "136","677/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhh`R\\WhMhhLgU[hFhhhhYh[FXNHb");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "121","707/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhh_^hLhPhhh_RYhIhghdfhUCNKMY");
        expiList.add(iL);
        //5th
        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "113","682/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhh`Zh[hBhhgcDhhOcehh_h^ELQB\\");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "1135","818/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hehhhhhhhfRhQhRhSRbcfgKg\\b_\\cIIWFRR");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "175","1376/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhhhJhhhhWhh[hhhhhhhK\\[Jh");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "166","1320/1","GACCTGCTTTGTTAAGTTTGCGTTGGGCCTGCAAG",
                "hhhhhhhhhhhhhhWhhhhhhhghh^hhhhVUOId");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "160","1506/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhhhHhhhhKPhHUUhh`hhG`IJh");
        expiList.add(iL);

        //10th
        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "1552","1284/1","GAGATTGCTTAGTGGTATGGTATTTGGCTAGTTTT",
                "hhhhhhhhhhTSZhbbWhUJSfhhhGOHhWQ[hgh");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "218","1570/1","GATGTTTTTATTCCGCCTACCATTCTGTGGTGTCT",
                "hh]ghhhhhN\\hhhKhhhhhhOhhhLehBhI[hhh");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "160","1466/1","GCAAGATCGGAAGAGCGGTTCAGCAGGAATGCCGA",
                "hhhhhdhhhhNhhhhhhhhhh[hhhhhMhhehhaF");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "168","1581/1","GCTCTTCCGATCTACGTGGCCCTTGAGATCGGAAG",
                "hhhhhhhhh^hhhhhhhhhhhhhhh`hThh[_NM`");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "691","1085/1","GCATTTCATAGCCTTTTATTAATTTTTGGATTCGT",
                "hhPhhhhhhOWhhhahhhhhO]gedhXBBR\\bIDK");
        expiList.add(iL);

        //15th
        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "125","485/1","GCAAGATCGGAAGAGCGGTTCAGCAGGAATGCCGA",
                "hh`hh\\hhhhTchX^hhhbhhK`^GYhVLh_QXSO");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "150","745/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhehhhKMh`[BhhQKB[fDYh]O\\h^D@BAM");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "155","1191/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhh^d]hUhhhh^hhOhhhhhhhKbhEh");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "117","661/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhYThCVUhdbRUGcEd^hRdhMCLDLP");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "172","1520/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhd^hYhhhhechLhhhhZhhSaYAh");
        expiList.add(iL);

        iL = new InputLine("USI-EAS39_8218_FC30495_PE","1","1",
                "145","670/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhgThVhPhh^gKUhIh_Vheh]HUUDY");
        expiList.add(iL);

        instance.setInputFile(inputFile);
        instance.readInputFile();

        //Create a List of InputLine
        List<InputLine> iLlist = new ArrayList<InputLine>();
        iLlist = instance.getInputLineList();

        int i = 0;
        while (i < expiList.size() )
        {
            assertEquals(expiList.get(i).toString(), iLlist.get(i).toString());
            i++;
        }
        

    }


}