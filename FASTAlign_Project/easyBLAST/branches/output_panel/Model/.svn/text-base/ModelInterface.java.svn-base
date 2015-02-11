package Model;

import Model.opencsv.CSVReader;
import java.util.List;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class acts as an interface to the View and the Controller. It
 * communicates with classes within the Model package to execute out
 * Preprocessing Flow and BLAST processing Flow.
 * @author jpatel
 */
public class ModelInterface
{
    final int numberOfLines = 20;
    private File inputFile = null;
    private PreProcessingHandler preprocessing = new PreProcessingHandler();
    private List<String> groups;
    private int idColumn;
    private int idLeft;
    private int idRight;
    private int inputBlastRegionLeft;
    private int inputBlastRegionRight;
    private int qualitySum;
    private int qualityASCII;
    private CSVReader csvReaderPreview;
    private CSVReader csvReaderInputFile;
    private Parameters parameters;
    private List<InputLine> inputList = new ArrayList<InputLine>();
    private List<ShortRead> srList = new Vector<ShortRead>();

    private List<String> refHeaders = new ArrayList();

    private Hashtable ht = new Hashtable(30);

    private File qualityFileSaveLocation;
    private File outputTableSaveLocation;
    private File refFASTA = new File("P:\\My Documents\\refFASTA.fsa");
    private File inputFASTA = new File("P:\\My Documents\\inputFASTA.fsa");
    private File tagFile = new File("P:\\My Documents\\inputFASTA.fsa");

    private Integer blastRegionStart = null;
    private Integer blastRegionEnd = null;

    //private List<InputLine> inputLines = new ArrayList<InputLine>();

    public ModelInterface(File inputFile, List<String> groups, int idColumn, 
            int idLeft, int idRight, int qualitySum, int qualityASCII)
    {
        this.inputFile = inputFile;
        this.groups = groups;
        this.idColumn = idColumn;
        this.idLeft = idLeft;
        this.idRight = idRight;
        this.qualitySum = qualitySum;
        this.qualityASCII = qualityASCII;
        csvReaderPreview = null;
        csvReaderInputFile = null;

        // This should be moved to the BLASTHandler next time.
        parameters = new Parameters();
        System.out.println("AAAAAAAAAAAAAAAAAA");
    }

    /**
     * The method reads the first 20 lines of the specified file and returns
     * a list of first 20 lines of that file.
     * @return shortreads - is a list containing 20 lines of shortreads
     * @throws java.io.IOException An exception if the file can not be read.
     */
    public List<String> getPreviewLines(File inputFile) throws IOException
    {
        List<String> shortReads = new ArrayList<String>();
        String[] line = null;
        int count = 0;

        try
        {
            csvReaderPreview = new CSVReader(new FileReader(inputFile), ':');

            while ((line = csvReaderPreview.readNext()) != null
                    && (count <= numberOfLines))
            {
                String shortR = line[5];
                shortReads.add(shortR);
                count++;
            }
            csvReaderPreview.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

        return shortReads;
    }

    /**
     * The method does the preprocessing of the data by calling the methods
     * in the PreprocessingHandler. Processing is done on one input line at a
     * time. Each inputLine from the inputfile is read, filered
     * (quality processing), written to a group and finally shortread
     * object is created and added to a list. After the inputlines have been
     * processed, a tag file is generated.
     */
    public void preprocessingFLow()
    {
        InputLine tempInputLine;
        String[] line = null;
        int count = 0;

        try
        {
            csvReaderInputFile = new CSVReader(new FileReader(inputFile), ':');

            while ((line = csvReaderInputFile.readNext()) != null)
            {

                String column1 = line[1];
                String column2 = line[2];
                String column3 = line[3];
                String column4 = line[4];
                String column5 = line[5];
                String shortRead = line[6];
                String quality = line[7];

                tempInputLine = new InputLine(column1, column2, column3,
                        column4, column5, shortRead, quality);

                preprocessing.setInputLine(tempInputLine);
                preprocessing.doQualityProcessing(qualitySum, qualityASCII);
                preprocessing.doGrouping(idLeft, idRight);
                String id = shortRead.substring(idLeft, idRight);
                String region = shortRead.substring(inputBlastRegionLeft,
                        inputBlastRegionRight);
                preprocessing.updateShortReadHashTable(id, region);
                count++;
            }
            preprocessing.doTagFile();

            csvReaderInputFile.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }

    /**
     * The method returns the ID Column.
     * @return idColumn.
     */
    public final int getIdColumn()
    {
        return idColumn;
    }

    /**
     * The method returns the starting index of the id string,
     * which is the idLeft.
     * @return idLeft
     */
    public final int getIdLeft()
    {
        return idLeft;
    }

    /**
     * The method returns the end index of the id string, which is the idRight
     * @return idRight.
     */
    public final int getIdRight()
    {
        return idRight;
    }

    /**
     * The method sets the start and the end index of string which is the
     * blast region.
     * @param regionLeft Left of the region.
     * @param regionRight Right of the region
     */
    public final void setInputBlastRegion(final int regionLeft,
            final int regionRight)
    {
        inputBlastRegionLeft = regionLeft;
        inputBlastRegionRight = regionRight;
    }

    /**
     * The method returns the set quality sum value.
     * @return qualitySum
     */
    public final int getQSum()
    {
        return qualitySum;
    }

    /**
     * The method returns the set quality ASCII value.
     * @return qualityASCII
     */
    public final int getQualityASCII()
    {
        return qualityASCII;
    }

     /**
     * The method defines the variable inputFile.
     * @param inputFile is an inputFile
     */
    public final void setInputFile(File inputFile)
    {
        this.inputFile = inputFile;
    }

    /**
     * The method returns an inputfilee.
     * @return inputFile
     */
    public final File getInputFile()
    {
        return inputFile;
    }


    /**
     * The method is called to set the quality thresholds :
     * quality sum and quality ASCII values.
     * @param qualitySum The sum of the ASCII characters.
     * @param qualityASCII The minimum ASCII character.
     */
    public final void setQualityThreshold(final int qualitySum,
            final int qualityASCII)
    {
        this.qualitySum = qualitySum;
        this.qualityASCII = qualityASCII;
    }

    /**
     * The method specifies the start and the end index of the
     * highlighted region which is the id of the shortread.
     * @param idLeft
     * @param idRight
     */
    public final void setInputIDRegion(final int inputIDLeft, final int inputIDRight)
    {
        this.idLeft = inputIDLeft;
        this.idRight = inputIDRight;
    }

      /**
     * The method calls make makeGroups() to create Group files.
     * @param groups
     */
    public final void callMakeGroups(String[][] groups)
    {
       // System.out.println("In the model interface in make groups");
        preprocessing.makeGroups(groups);
    }
    
    /**
     * The method calls doGrouping() for each inputLine.
     */
    public final void callDoGrouping()
    {
        System.out.println("In the model interface in callDoGrouping");

        for (int i = 0; i < inputList.size(); i++)
        {
            System.out.println("InputLine: "+ inputList.get(i).toString());
            System.out.println("Inside the loop");
            preprocessing.setInputLine(inputList.get(i));
            System.out.println(inputList.get(i).toString() + "\n");
            preprocessing.doGrouping(id_start, id_end);
        }
    }


    public final void convertInputToFASTA()
    {
        System.out.println("===== sorted =====");
        ShortReadComparator src = new ShortReadComparator();
        Collections.sort(preprocessing.getShortReadList(), src);

        for (int i = 0; i < preprocessing.getShortReadList().size(); i++)
        {
            System.out.println("id: " +  preprocessing.getShortReadList().
                    get(i).getID() + "\tregion: " + preprocessing.
                    getShortReadList().get(i).getRegion() + "\t\tcount: "
                    + preprocessing.getShortReadList().get(i).getCount());
        }

        try
        {
            String id = preprocessing.getShortReadList().get(0).getID();
            for (int i = 0; i < preprocessing.getShortReadList().size(); i++)
            {
                if (!id.equals(preprocessing.getShortReadList().get(i).getID()))
                {
                    id = preprocessing.getShortReadList().get(i).getID();
                }
                FileWriter f0 = new FileWriter("P:\\" + id + ".fsa", true);
                System.out.print(">" + preprocessing.getShortReadList().get(i).
                        getID() + "_" + preprocessing.getShortReadList().
                        get(i).getCount() + "_" + preprocessing.
                        getShortReadList().get(i).getRegion() + "\n");
                System.out.print(preprocessing.getShortReadList().get(i).
                        getRegion() + "\n");
                f0.write(">" + preprocessing.getShortReadList().get(i).getID()
                        + "_" + preprocessing.getShortReadList().get(i).
                        getCount() + "_" + preprocessing.getShortReadList().
                        get(i).getRegion() + "\n");
                f0.write(preprocessing.getShortReadList().get(i).
                        getRegion() + "\n");
                f0.close();
            }
        }

        catch (IOException ioe)
        {

        }

    }
/**
 * The method reads the lines from the inputFile and stores it an List.
 * ShorRead List is also created and ShortRead objects are added to the list.
 * @throws FileNotFoundException
 */
    public void readInputFile()
    {
        String name = inputFile.getName();
        String pathName = inputFile.getPath();

        System.out.println(name + "\n" + pathName);
        CSVReader readerInputFile = null;
        try {
            readerInputFile = new CSVReader(new FileReader(inputFile), ':');
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelInterface.class.getName()).log(Level
                    .SEVERE, null, ex);
        }

        InputLine tempInputLine;
        String line[]= null;
        try 
        {
            while ((line = readerInputFile.readNext()) != null) 
            {
                //System.out.println("InputLine: "+ line.toString() + "\n");
                String column1 = line[0];
                String column2 = line[1];
                String column3 = line[2];
                String column4 = line[3];
                String column5 = line[4];
                String shortRead = line[5];
                String quality = line[6];

                System.out.println("InputLine: " + column1 + column2 + column3);

                tempInputLine = new InputLine(column1, column2, column3,
                        column4, column5, shortRead, quality);
                inputList.add(tempInputLine);
                String id = shortRead.substring(idLeft, idRight);
                String region = shortRead.substring(inputBlastRegionLeft,
                        inputBlastRegionRight);
                System.out.println("id: " + id + " region: " + region);
                preprocessing.updateShortReadHashTable(id, region);
                System.out.println(tempInputLine.printInputLine()+ "\n");
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ModelInterface.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        for (Enumeration ee = preprocessing.getHashTable().keys();
        ee.hasMoreElements();)
        {
            preprocessing.getShortReadList().add((ShortRead)
                    preprocessing.getHashTable().get(ee.nextElement()));
        }
    }

    private Boolean isBLASTMode;

    private File in1 = null;
    private File in2 = null;

    private File ref1 = null;

    private int refIDColumn;
    private int refRegionColumn;

    private Integer minASCII = 70;
    private Integer minSum = 0;

    private Integer id_start = 0;
    private Integer id_end = 2;

    private Integer region_start = 28;
    private Integer region_end = 35;

    private String group1 = null;
    private String group2 = null;

    private Double expFailMarg = 10.0;
    private Integer winSize = 0;
    private Integer numHits = 0;
    private Integer queryStrands = 3;
    private Integer numDBSeq1Ln = 500;
    private Integer numDBSeqAlig = 50;
    private List<String> inputLineList;

    /**
     * 
     */
    public ModelInterface()
    {

    }

    public final List<InputLine> getInputLineList()
    {
        return inputList;
    }

    public final List<ShortRead> getShortReadList()
    {
        return srList;
    }

    public final List getRefHeaders()
    {
        return refHeaders;
    }

    public  final Hashtable getHT()
    {
        return ht;
    }

    public final  void printShortReadList()
    {
        for (int i = 0; i < inputList.size(); i++)
        {
            System.out.println("DNA: " + inputList.get(i).getShortRead());
        }
    }

    public final String printInputLine(final int i)
    {
        return  inputList.get(i).getColumn1() + ":" +
                inputList.get(i).getColumn2() + ":" +
                inputList.get(i).getColumn3() + ":" +
                inputList.get(i).getColumn4() + ":" +
                inputList.get(i).getColumn5() + ":" +
                inputList.get(i).getShortRead() + ":" +
                inputList.get(i).getQualityASCII();
    }

    public final void printDetailedShortReadList()
    {
        for (int i = 0; i < inputList.size(); i++)
        {
            System.out.println(inputList.get(i).getShortRead() + "|"
                    + inputList.get(i).getQualityASCII() + "|"
                    + inputList.get(i).getQualitySum() + "|"
                    + inputList.get(i).getQualityMin());
        }
    }

    /**
     * Sets the parameters in the Parameters object.
     * @param m
     * @param e
     * @param W
     * @param K
     */
    public final void setAllParameters(Integer m, Integer e, Integer W,
            Integer K)
    {
        parameters.setM(m);
        parameters.setE(e);
        parameters.setW(W);
        parameters.setK(K);
    }

    public final File getFile1()
    {
        return inputFile;
    }


    public final void setFile2(final File f)
    {
        in2 = f;
    }

    public final File getFile2()
    {
        return in2;
    }

    public final File getRefFile()
    {
        return ref1;
    }

    public final void setRefFile(final File f)
    {
        ref1 = f;
    }

    public  final void setMinASCII(final int i)
    {
        minASCII = i;
    }

    public  final void setMinSum(final int i)
    {
        minSum = i;
    }

    public  final Integer getMinASCII()
    {
        return minASCII;
    }

    public  final Integer getMinSum()
    {
        return minSum;
    }

    public  final Integer getInputIDStart()
    {
        return idLeft;
    }

    public  final Integer getInputIDEnd()
    {
        return idRight;
    }

  /*  public final void setIDStart(final Integer n)
    {
        id_start = n;
    }

    public  final void setIDEnd(final Integer n)
    {
        id_end = n;
    }
*/
    public final Integer getRegionStart()
    {
        return inputBlastRegionLeft;
    }

    public final Integer getRegionEnd()
    {
        return inputBlastRegionRight;
    }

    public final void setRegionStart(final Integer n)
    {
        region_start = n;
    }

    public final void setRegionEnd(final Integer n)
    {
        region_end = n;
    }

    public final void setRefRegionColumn(final int n)
    {
        refRegionColumn = n;
    }

    public final int getRefRegionColumn()
    {
        return refRegionColumn;
    }

    public final Double getExpFailureMargin()
    {
        return expFailMarg;
    }

    public final void setExpFailMargin(final Double n)
    {
        expFailMarg = n;
    }
    public final Integer getWinSize()
    {
        return winSize;
    }

    public final void setWinSize(final Integer n)
    {
        winSize = n;
    }

    public final Integer getNumHits()
    {
        return numHits;
    }

    public final void setNumHits(Integer n)
    {
        numHits = n;
    }

    public final Integer getQueryStrands()
    {
        return queryStrands;
    }

    public final void setQueryStrands(final Integer n)
    {
        queryStrands = n;
    }

    public final Integer getNumDBSeq1Ln()
    {
        return numDBSeq1Ln;
    }

    public final void setNumDBSeq1Ln(final Integer n)
    {
        numDBSeq1Ln = n;
    }

    public final Integer getNumDBSeqAlig()
    {
        return numDBSeqAlig;
    }

    public final void setNumDBSeqAlig(final Integer n)
    {
        numDBSeqAlig = n;
    }

    public final boolean isBLASTMode()
    {
        return isBLASTMode;
    }

    public final void setBLASTMode(final Boolean b)
    {
        isBLASTMode = b;
    }
    public final void setBLASTRegion(final Integer start, final Integer end)
    {
        blastRegionEnd = end;
        blastRegionStart = start;
    }

    public final Integer getBLASTRegionStart()
    {
        return blastRegionStart;
    }

    public final Integer getBLASTRegionEnd()
    {
        return blastRegionEnd;
    }

    public final File getQualitySaveLocation()
    {
        return qualityFileSaveLocation;
    }

    public final void setQualitySaveLocation(final File file)
    {
        qualityFileSaveLocation = file;
    }

    public File getOutputTableSaveLocation()
    {
        return outputTableSaveLocation;
    }

    public final void setOutputTableSaveLocation(final File file)
    {
        outputTableSaveLocation = file;
    }

    public final Parameters getParameters()
    {
        return parameters;
    }

    public final void generateRefFASTAFile()
    {
        try
        {
            CSVReader refReader = new CSVReader(new FileReader(ref1), ',');
            String[] line;

            line = refReader.readNext(); // ignore the first line. this is the
                                        //header.

            FileWriter refFASTAWriter = new FileWriter("C:\\ref.fa");

            while ((line = refReader.readNext()) != null)
            {
                refFASTAWriter.write(">" + line[0] + "\r\n");
                refFASTAWriter.write(line[refRegionColumn].
                        substring(blastRegionStart, blastRegionEnd)
                        + "\r\n");

                System.out.println(">" + line[0] + "\r\n");
                System.out.println(line[10].
                        substring(blastRegionStart, blastRegionEnd)
                        + "\r\n");
            }

            refReader.close();
            refFASTAWriter.close();

        }
        catch (IOException ie)
        {

        }
    }
}
