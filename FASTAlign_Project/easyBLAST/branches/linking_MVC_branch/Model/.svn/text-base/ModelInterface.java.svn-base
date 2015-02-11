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

    private final int numberOfLines = 20;
    private Boolean isBLASTMode = false;
    private File referenceFile1 = null;
    private int refIDColumn;
    private int refRegionColumn;
    private File inputFile1 = null;
    private File pairedEndFile = null;

    private PreProcessingHandler preprocessing = new PreProcessingHandler();
    private List<String> groups;
    private int idColumn;
    private int idLeft;
    private int idRight;
    private int inputBLASTRegionLeft;
    private int inputBLASTRegionRight;
    private int qualitySum;
    private int qualityASCII;
    private CSVReader csvReaderPreview;
    private CSVReader csvReaderInputFile;
    private Parameters parameters;
    private List<InputLine> inputList = new ArrayList<InputLine>();
    private List<ShortRead> srList = new Vector<ShortRead>();
    private boolean isPairedEnd;
    private boolean isQualityFileSaved;
    private boolean hasGeneratedPairedEndTextBoxes = false;

    private List<String> refHeaders = new ArrayList();

    private Hashtable htable = new Hashtable(30);

    private String qualityFileSaveLocation;
    private File outputTableSaveLocation;
    private File refFASTA = new File("P:\\My Documents\\refFASTA.fsa");
    private File inputFASTA = new File("P:\\My Documents\\inputFASTA.fsa");
    private File tagFile = new File("P:\\My Documents\\inputFASTA.fsa");

    private Integer blastRegionStart = null;
    private Integer blastRegionEnd = null;
    private List<String> inputLineList;


    private Integer minASCII = 70;
    private Integer minSum = 0;

    private Integer region_start = 28;
    private Integer region_end = 35;

    private Double expFailMarg = 10.0;
    private Integer winSize = 0;
    private Integer numHits = 0;
    private Integer queryStrands = 3;
    private Integer numDBSeq1Ln = 500;
    private Integer numDBSeqAlig = 50;

    //private List<InputLine> inputLines = new ArrayList<InputLine>();

    public ModelInterface(File inputFile, List<String> groups, int idColumn, 
            int idLeft, int idRight, int qualitySum, int qualityASCII)
    {
        this.inputFile1 = inputFile;
        this.groups = groups;
        this.idColumn = idColumn;
        this.idLeft = idLeft;
        this.idRight = idRight;
        this.qualitySum = qualitySum;
        this.qualityASCII = qualityASCII;
        csvReaderPreview = null;
        csvReaderInputFile = null;
        this.isPairedEnd = false;
        this.isQualityFileSaved = false;

        // This should be moved to the BLASTHandler next time.
        parameters = new Parameters();
        System.out.println("AAAAAAAAAAAAAAAAAA");
    }

    /**
     * The method reads the first 20 lines of the specified file and returns
     * a list of first 20 lines of that file.
     * If a paired end file exists, the first 20 lines of the paired end
     * file will also be read and added to the list.
     * @return shortreads - is a list containing 20 lines of shortreads
     * @throws java.io.IOException An exception if the file can not be read.
     */
    public List<String> getPreviewLines()
    {
        List<String> shortReads = new ArrayList<String>();
        String[] line = null;
        int count = 0;

        // Read first file

        try
        {
            csvReaderPreview = new CSVReader(new FileReader(inputFile1), ':');

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

        count = 0;

        // Read second file

        if (this.isPairedEnd)
        {
            try
            {
                csvReaderPreview =
                        new CSVReader(new FileReader(pairedEndFile), ':');

                while ((line = csvReaderPreview.readNext()) != null
                        && (count <= numberOfLines))
                {
                    String shortRead = line[5];
                    shortReads.add(shortRead);
                    count++;
                }
            }

            catch (IOException e)
            {
                System.err.println(e);
            }
        }

        return shortReads;
    }

      /**
     * The method calls make makeGroups() to create Group files.
     * @param groups
     */
    public final void callMakeGroups(List<List<String>> groups)
    {
       // System.out.println("In the model interface in make groups");
        preprocessing.makeGroups(groups);
    }

    /**
     * The method takes in a list of input files. The list will either
     * contain one input file or two input files (the paired end).
     * @param inputFileList
     */
    public void processInputFile()
    {
        System.out.println(idLeft + " " + idRight);
        System.out.println(inputBLASTRegionLeft + ""
                + inputBLASTRegionRight);

        List<File> inputFileList = new ArrayList<File>();

        inputFileList.add(inputFile1);
         System.out.println("Input File:"+ inputFileList.get(0).getName());

        if(pairedEndFile != null)
        {
            inputFileList.add(pairedEndFile);
            System.out.println("Paried End:"+ inputFileList.get(1).getName());
        }
        
        for(int i=0; i<inputFileList.size(); i++)
        {
            preprocessingFlow(inputFileList.get(i));
        }
    }

    /**
     * The method does the preprocessing of the data by calling the methods
     * in the PreprocessingHandler. Processing is done on one input line at a
     * time. Each inputLine from the inputfile is read, filered
     * (quality processing), written to a group and finally shortread
     * object is created and added to a list. After the inputlines have been
     * processed, a tag file is generated.
     */
    private void preprocessingFlow(File inputFile)
    {
        InputLine tempInputLine;
        String[] line = null;
        int count = 0;

        preprocessing.createQualityFileWriter();
               
        try
        {
            csvReaderInputFile = new CSVReader(new FileReader(inputFile), ':');

            while ((line = csvReaderInputFile.readNext()) != null)
            {
                String column1 = line[0];
                String column2 = line[1];
                String column3 = line[2];
                String column4 = line[3];
                String column5 = line[4];
                String shortRead = line[5];
                String quality = line[6];

                tempInputLine = new InputLine(column1, column2, column3,
                        column4, column5, shortRead, quality);

                preprocessing.setInputLine(tempInputLine);
                boolean ifFiltered = preprocessing.
                        doQualityProcessing(qualitySum, qualityASCII);
                if(ifFiltered)
                {
                   preprocessing.doGrouping(idLeft, idRight);
                    String id = shortRead.substring(idLeft, idRight);
                    String region = shortRead.substring(inputBLASTRegionLeft,
                            inputBLASTRegionRight);
               
                    preprocessing.updateShortReadHashTable(id, region);
                }
                
                count++;
            }
            preprocessing.doTagFile();

            csvReaderInputFile.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        //preprocessing.getQualityFile().
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
        inputBLASTRegionLeft = regionLeft;
        inputBLASTRegionRight = regionRight;
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
        this.inputFile1 = inputFile;
    }

    /**
     * The method returns an inputfilee.
     * @return inputFile
     */
    public final File getInputFile()
    {
        return inputFile1;
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
    /*
    public final void callMakeGroups(String[][] groups)
    {
       // System.out.println("In the model interface in make groups");
        preprocessing.makeGroups(groups);
    }
    */
    /**
     * The method calls doGrouping() for each inputLine.
     */
    /*
    public final void callDoGrouping()
    {
        System.out.println("In the model interface in callDoGrouping");

        for (int i = 0; i < inputList.size(); i++)
        {
            System.out.println("InputLine: "+ inputList.get(i).toString());
            System.out.println("Inside the loop");
            preprocessing.setInputLine(inputList.get(i));
            System.out.println(inputList.get(i).toString() + "\n");
            preprocessing.doGrouping(idLeft, idRight);
        }
    }
*/
    public final void generateRefFASTAFile()
    {
        try
        {
            CSVReader refReader = new CSVReader(new FileReader(referenceFile1),
                    ',');
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
                FileWriter f0 = new FileWriter("C:\\temp\\" + id + ".fsa", true);
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
        CSVReader readerInputFile = null;
        try {
            readerInputFile = new CSVReader(new FileReader(inputFile1), ':');
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
                tempInputLine = new InputLine(column1, column2, column3,
                        column4, column5, shortRead, quality);
                inputList.add(tempInputLine);
                String id = shortRead.substring(idLeft, idRight);
                String region = shortRead.substring(inputBLASTRegionLeft,
                        inputBLASTRegionRight);
                System.out.println("id: " + id + " region: " + region);
                preprocessing.updateShortReadHashTable(id, region);
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
        return htable;
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

    public void setPairedEndFile(File f)
    {
        pairedEndFile = f;
    }

    public final File getPairedEndFile()
    {
        return pairedEndFile;
    }

    public final File getRefFile()
    {
        return referenceFile1;
    }

    public final void setRefFile(final File f)
    {
        referenceFile1 = f;
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

    public final Integer getInputBLASTRegionStart()
    {
        return inputBLASTRegionLeft;
    }

    public final Integer getInputBLASTRegionEnd()
    {
        return inputBLASTRegionRight;
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

    public final void setNumHits(final Integer n)
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

    public final String getQualitySaveLocation()
    {
        return qualityFileSaveLocation;
    }

    public final void setQualitySaveLocation(File path)
    {
        preprocessing.setQualityFile(path);
    }

    public final File getOutputTableSaveLocation()
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

    /**
     * Method for checking whether a paired end file exists or not.
     * @return true if paired end file exists; otherwise false.
     */
    public boolean isPairedEnd()
    {
        return isPairedEnd;
    }

    public void setPairedEnd(boolean isPairedEnd)
    {
        this.isPairedEnd = isPairedEnd;
    }

    public boolean getIsQualityFileSaved()
    {
        return isQualityFileSaved;
    }

    public void setSaveQualityFile(boolean saveValue)
    {
        isQualityFileSaved = saveValue;
    }

    public void setHasGeneratedPairedEndTextBoxes(boolean
            hasGeneratedPairedEndTextBoxes)
    {
        this.hasGeneratedPairedEndTextBoxes =  hasGeneratedPairedEndTextBoxes;
    }

    public boolean getHasGeneratedPairedEndTextBoxes()
    {
        return hasGeneratedPairedEndTextBoxes;
    }
}
