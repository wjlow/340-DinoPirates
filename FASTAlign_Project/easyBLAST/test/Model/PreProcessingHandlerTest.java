package Model;

import Model.opencsv.CSVReader;
import Model.opencsv.CSVWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PreProcessingHandlerTest {
    InputLine il, il2, il3;
    String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    File qFile;
    File gFile;
    CSVReader csvReader;
    String[] c;
    String[] g = {"AAGA", "TCGG", "CCTG", "GATT"};
    List<String> group1 = new ArrayList<String>();
    List<String> group2 = new ArrayList<String>();
    List<String> group3 = new ArrayList<String>();
    List<List<String>> group = new ArrayList<List<String>>();
    List<InputLine> iLlist = new ArrayList<InputLine>();

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Before
    public void setUp() {
        c1 = "USI-EAS39_8218_FC30495_PE";
        c2 = "1";
        c3 = "1";
        c4 = "155";
        c5 = "1404/1";
        c6 = "GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG";
        c7 = "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb";

        il = new InputLine(c1, c2, c3, c4, c5, c6, c7);

        c7 = "hhhhhhhhhhhfhhhchdhJhhhbhZZZZZZZh_hZZYYY";
        il2 = new InputLine(c1, c2, c3, c4, c5, c6, c7);

        c7 = "hhhhhhhhhhhfhhhchbhZZZZZZZh_hZZYYY";
        il3 = new InputLine(c1, c2, c3, c4, c5, c6, c7);

        qFile = new File("Quality.txt");               

        csvReader = null;

    }

    @After
    public void tearDown() {       

        qFile.delete();
        //qFile.deleteOnExit();
    }

    /**
     * Test of doQualityProcessing method, of class PreProcessingHandler.
     * Test if a InputLine is good quality gets written in the Quality.txt file.
     * This is an on point boundary testing. Two lines are written in the file
     * and compared with expected result
     */
    @Test
    public void testDoGoodQualityProcessing() throws Exception {
        System.out.println("doQualityProcessing");
        int qSum = 3475;
        int qASCII = 66;
        int j;
        
        //String[] c = {c1, c2, c3, c4, c5, c6, c7};
        PreProcessingHandler instance =
                new PreProcessingHandler();

        System.out.println("checkQuality");
        System.out.println("-QualityMin: " + il.getQuality2ndMin());
        System.out.println("-QualitySum: " + il.getQualitySum());

        System.out.println("checkQuality");
        System.out.println("-QualityMin: " + il2.getQuality2ndMin());
        System.out.println("-QualitySum: " + il2.getQualitySum());

        System.out.println("checkQuality");
        System.out.println("-QualityMin: " + il3.getQuality2ndMin());
        System.out.println("-QualitySum: " + il3.getQualitySum());

        instance.setInputLine(il);
        instance.doQualityProcessing(qSum, qASCII);
        
        instance.setInputLine(il2);
        instance.doQualityProcessing(qSum, qASCII);

        instance.setInputLine(il3);
        instance.doQualityProcessing(qSum, qASCII);

        String[] expli = { il.toString(), il2.toString(), il3.toString() };

        csvReader = new CSVReader(new FileReader(qFile));

        int i = 0;
        String[] line;
        while ( (line = csvReader.readNext()) != null)
        {            
            System.out.println(line[0]);
            System.out.println(expli[i]);

            assertEquals(expli[i], line[0]);
            i++;

        }

        csvReader.close();
        
    }

    /**
     * Test of makeGroups method, of class PreProcessingHandler.
     * This method should ensure the group files are generated as .txt files
     * with the correct group name. Asserts if the groups set in the class are
     * correct
     *
     * The files are generated in the project folder. To check if the file names
     * are correct, this is done by inspection for each file.
     */
    @Test
    public void testMakeGroups() {
        System.out.println("makeGroups");
        //List<String> group1 = new ArrayList<String>();
        //List<String> group2 = new ArrayList<String>();
        //List<List<String>> group = new ArrayList<List<String>>();

        group1.add("ACTG");
        group1.add("AAGT");
        group.add(group1);
        group2.add("GTTC");
        group.add(group2);

        PreProcessingHandler instance = new PreProcessingHandler();

        //Create groups
        instance.makeGroups(group);

        assertNotNull("P:\\My Documents\\Group1.txt");
        assertNotNull("P:\\My Documents\\Group2.txt");

    }


    /**
     * Test of doGrouping method, of class PreProcessingHandler.
     */
    @Test
    public void testDoGrouping() throws IOException {
        System.out.println("doGrouping");
        int idLeft = 2;
        int idRight = 6;
        int i;

        group1.add("ACTG");
        group1.add("AAGT");
        group.add(group1);
        group2.add("GTTC");
        group.add(group2);

        PreProcessingHandler instance = new PreProcessingHandler();

        //String[][] grp = { {"ACTG", "AAGT"}, {"GTTC", "TCGG"}, {"AATC"} };

        //Create the group files
        instance.makeGroups(group);

        //Create InputLines
        InputLine iL = new InputLine("0USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTGTTCCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("1USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTGTTCCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("2USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTAAGTCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G1
        iLlist.add(iL);
        iL = new InputLine("3USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTAAGTCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G1
        iLlist.add(iL);
        iL = new InputLine("4USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("5USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("6USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTACTGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G1
        iLlist.add(iL);
        iL = new InputLine("7USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("8USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("9USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("10USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTACTGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G1
        iLlist.add(iL);
        iL = new InputLine("11USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("12USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("13USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("14USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("15USI-EAS39_8218_FC30495_PE","1","1","155",
                "1404/1","GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("16USI-EAS39_8218_FC30495_PE", "1", "1", "155",
                "1404/1", "GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("17USI-EAS39_8218_FC30495_PE", "1", "1", "155",
                "1404/1", "GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("18USI-EAS39_8218_FC30495_PE", "1", "1", "155",
                "1404/1", "GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);
        iL = new InputLine("19USI-EAS39_8218_FC30495_PE", "1", "1", "155",
                "1404/1", "GTTCGGCCTGCAAGATCGGAAGAGCGGTTCAGCAG",
                "hhhhhhhhhhhhChfhhhchdhJhhhbhh_BhZZb"); // Into G2
        iLlist.add(iL);

        //instance.setGroups(grp);
        for (i = 0; i < iLlist.size(); i++) {
            //Set input file
            instance.setInputLine(iLlist.get(i));

            instance.doGrouping(idLeft, idRight);
        }

        gFile = new File("P:\\My Documents\\Group1.txt");
        FileInputStream fis = new FileInputStream(gFile);
        Integer empty = fis.read();
        System.out.println(empty);
        int result = empty.compareTo(0);
        assertEquals(1, result);

        gFile = new File("P:\\My Documents\\Group2.txt");
        fis = new FileInputStream(gFile);
        empty = fis.read();
        System.out.println(empty);
        result = empty.compareTo(0);
        assertEquals(1, result);

        gFile = new File("P:\\My Documents\\Group3.txt");
        fis = new FileInputStream(gFile);
        empty = fis.read();
        System.out.println(empty);
        result = empty.compareTo(-1);
        assertEquals(0, result);

    }

    /**
     * Test of doTagFile method, of class PreProcessingHandler.
     */
    @Test
    public void testDoTagFile() {
        System.out.println("doTagFile");
        PreProcessingHandler instance = new PreProcessingHandler();
        String id = "AC_1";
        String region = "ACGT";

        instance.updateShortReadHashTable(0, id, region);
        instance.updateShortReadHashTable(1, id, region);
        instance.updateShortReadHashTable(2, id, region);

        id = "AG_2";
        region = "CCGT";

        instance.updateShortReadHashTable(3, id, region);

        id = "AT_3";
        region = "TAGT";

        instance.updateShortReadHashTable(4, id, region);
        instance.updateShortReadHashTable(5, id, region);


        instance.doTagFile();

        File tagFile = new File("Tag.txt");

        boolean value = tagFile.exists();

        assertNotNull(tagFile);

        System.out.println(value);
    }

    /**
     * Test of updateShortReadHashTable method, of class PreProcessingHandler.
     * 3 identical ShortReads(A1) and 1 unique ShortRead(A2) are put in the
     * table. The count for A1 should be 3 and the count for A2 should be 1.
     */
    @Test
    public void testUpdateShortReadHashTable() {
        System.out.println("updateShortReadHashTable");
        String id = "A1";
        String region = "ACGT";
        String key = id + region;

        //Initialise PreProcessingHandler
        PreProcessingHandler instance = new PreProcessingHandler();

        instance.updateShortReadHashTable(0, id, region);
        instance.updateShortReadHashTable(1, id, region);
        instance.updateShortReadHashTable(2, id, region);

        Integer actResult = ((ShortRead) (instance.getHashTable())
                .get(key)).getCount();

        Integer expResult = 3;

        assertEquals(expResult, actResult);

        id = "A2";
        region = "CCGT";

        instance.updateShortReadHashTable(3, id, region);

        key = id + region;

        actResult = ((ShortRead) (instance.getHashTable()).get(key)).getCount();
        expResult = 1;

        assertEquals(expResult, actResult);

    }
    /**
     * This 1st test case test if checkGroupForConflicts check if any of the
     * groups has a duplicate ID. The IDs in the groups do not contain wildcards
     * Returns true
     */
    @Test
    public void testCheckGroupForConflictsNoWildCards()
    {
        System.out.println("checkGroupForConflictsNoWildCards");
        group1.add("ACTG");
        group1.add("AAGT");
        group1.add("ACGG");

        group2.add("GTTC");
        group2.add("ACTG");

        group3.add("TTTC");
        group3.add("GTTA");
        group3.add("ACGG");

        group.add(group1);
        group.add(group2);
        group.add(group3);

        PreProcessingHandler instance = new PreProcessingHandler();
        instance.makeGroups(group);

        boolean noConflict = instance.checkGroupsForConflicts();

        System.out.println(noConflict);

        boolean expBool = true;
        assertEquals(noConflict, expBool);
    }

    /**
     * This 2nd test case test if checkGroupForConflicts check if any of the
     * groups has a duplicate ID. The IDs in the group 2 contain a wildcard.
     * Returns true
     */
    @Test
    public void testCheckGroupForConflictsHasWildCards1()
    {
        System.out.println("checkGroupForConflictsHasWildCards1");
        group1.add("ACTG");
        group1.add("AAGT");
        group1.add("ACGG");

        group2.add("GTTC");
        group2.add("ACAG");
        group2.add("TA*G");

        group3.add("TTTC");
        group3.add("GTTA");
        group3.add("TACG");
        //group3.add("ACTG");

        group.add(group1);
        group.add(group2);
        group.add(group3);

        PreProcessingHandler instance = new PreProcessingHandler();
        instance.makeGroups(group);

        boolean noConflict = instance.checkGroupsForConflicts();

        System.out.println(noConflict);

        boolean expBool = true;
        assertEquals(noConflict, expBool);
    }

    /**
     * This 3rd test case test if checkGroupForConflicts check if any of the
     * groups has a duplicate ID. The identifications in the group 3 contain a
     * wildcard.
     * Returns true
     */
    @Test
    public void testCheckGroupForConflictsHasWildCards2()
    {
        System.out.println("checkGroupForConflictsHasWildCards2");
        group1.add("ACTG");
        group1.add("AAGT");
        group1.add("ACGG");

        group2.add("GTTC");
        group2.add("ACAG");
        group2.add("TAAG");

        group3.add("TTTC");
        group3.add("GTT*");
        group3.add("TACG");
        //group3.add("ACTG");

        group.add(group1);
        group.add(group2);
        group.add(group3);

        PreProcessingHandler instance = new PreProcessingHandler();
        instance.makeGroups(group);

        boolean noConflict = instance.checkGroupsForConflicts();

        System.out.println(noConflict);

        boolean expBool = true;
        assertEquals(noConflict, expBool);
    }

    /**
     * This 3rd test case test if checkGroupForConflicts check if any of the
     * groups has a duplicate ID. The identifications in group2 and group 3
     * contain a wildcard at different location.
     * Returns true
     */
    @Test
    public void testCheckGroupForConflictsHasWildCards3()
    {
        System.out.println("checkGroupForConflictsHasWildCards3");
        group1.add("ACTG");
        group1.add("AAGT");
        group1.add("ACGG");

        group2.add("*TTC");
        group2.add("ACAG");
        group2.add("TAAG");

        //group3.add("TTTC");
        group3.add("GTT*");
        group3.add("TACG");
        //group3.add("ACTG");

        group.add(group1);
        group.add(group2);
        group.add(group3);

        PreProcessingHandler instance = new PreProcessingHandler();
        instance.makeGroups(group);

        boolean noConflict = instance.checkGroupsForConflicts();

        System.out.println(noConflict);

        boolean expBool = true;
        assertEquals(noConflict, expBool);
    }
}