package Model;

import Model.opencsv.CSVReader;
import View.View;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * The class acts as an interface to the View and the Controller. It
 * communicates with classes within the Model package to execute out
 * Preprocessing Flow and BLAST processing Flow.
 * @author jpatel
 */
public class ModelInterface
{
    // general data
    private final int numberOfLines = 20;
    private Boolean isBLASTMode = false;
    private int algorithmIndex = 0;

    // pre-processing data
    private File inputFile1 = null;
    private File pairedEndFile = null;
    private int qualitySum = 3000;
    private int qualityASCII = 70;
    private PreProcessingHandler preProHandler = new PreProcessingHandler();
    private List<String> groups;
    private boolean selectedPairedEndInIdSelection = false;
    private List<InputLine> inputList = new ArrayList<InputLine>();
    private List<ShortRead> srList = new Vector<ShortRead>();
    private boolean isPairedEnd;
    private boolean isQualityFileSaved;
    private int inputBLASTRegionLeft;
    private int inputBLASTRegionRight;
    private int inputIDRegionLeft;
    private int inputIDRegionRight;
    private CSVReader csvReaderPreview;
    private CSVReader csvReaderInputFile;
    private CSVReader csvReaderInputPairedEndFile;

    // blast data
    private List<File> referenceFileList = new ArrayList<File>();
    private List<Integer> refColumnHeadersIndex = new ArrayList<Integer>();
    private File referenceFile1 = null;
    private int refIDColumn;
    private int refRegionColumn;
    private int blastRegionStart;
    private int blastRegionEnd;
    private List<String> refHeaders = new ArrayList();
    private List<AbstractAlignmentTool> listOfTools =
            new ArrayList<AbstractAlignmentTool>();
    private File outputTableSaveLocation;

    private Parameters parameters;
    private boolean hasGeneratedPairedEndTextBoxes = false;
    private Hashtable htable = new Hashtable(30);

    private View view;

    public ModelInterface()
    {
        Parameters[] arrayOfBLASTParams =
        {
            new Parameters("Program", "-p", "blastn"),
            new Parameters("Query File", "-i", "input.fa"),
            new Parameters("Reference File", "-d", "reference.fa"),
            new Parameters("Output File", "-o", "align_output.txt"),
            new Parameters("Alignment view options(-m)", "-m", "8"),
            new Parameters("Expectation Value(-e)", "-e", "10"),
            new Parameters("Word Size(-W)", "-W", "11"),
            new Parameters("Penalty for a nucleotide mismatch(-q)", "-q", "-2"),
            new Parameters("Number of best hits to keep(-K)", "-K", "100"),
            new Parameters("Query strands to search database(-S)", "-S", "3"),
            new Parameters("Number of cores to use(-a)", "-a", "2")
        };

        Parameters[] arrayOfFormatdbParams =
        {
            new Parameters("Protein? (T/F)", "-p", "F"),
            new Parameters("Input File", "-i", "reference.fa")
        };

        listOfTools.add(new BLASTTool("BLAST", "blastall.exe", "formatdb.exe",
                arrayOfBLASTParams, arrayOfFormatdbParams));

        Parameters[] arrayOfBowtieParams =
        {
            new Parameters("Number of processors?", "-p", "4"),
            new Parameters("Query File", "-f", "input.fa"),
            new Parameters("Reference File", "-d", "reference"),
            new Parameters("Output File", "-o", "align_output.txt")
        };
        listOfTools.add(new BowtieTool("Bowtie", "bowtie.exe", "formatdb.exe",
                arrayOfBowtieParams, arrayOfFormatdbParams));
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
            if (inputFile1 != null)
            {
                csvReaderPreview = new CSVReader(
                        new FileReader(inputFile1), ':');

                while ((line = csvReaderPreview.readNext()) != null
                        && (count <= numberOfLines))
                {
                    String shortR = line[5];
                    shortReads.add(shortR);
                    count++;
                }
                csvReaderPreview.close();
            }
        }
        catch (Exception e)
        {

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
     * @param arg_groups
     */
    public final void callMakeGroups(List<List<String>> arg_groups)
    {
        preProHandler.makeGroups(arg_groups);
    }

    /**
     * The method takes in a list of input files. The list will either
     * contain one input file or two input files (the paired end).
     * @param inputFileList
     */
    public void processInputFile()
    {
        System.out.println(inputIDRegionLeft + " " + inputIDRegionRight);
        System.out.println(inputBLASTRegionLeft + "" + inputBLASTRegionRight);

        List<File> inputFileList = new ArrayList<File>();

        inputFileList.add(inputFile1);

        if (isPairedEnd)
        {
            inputFileList.add(pairedEndFile);
        }

        preprocessingFlow(inputFileList);
    }

    /**
     * The method does the preprocessing of the data by calling the methods
     * in the PreprocessingHandler. Processing is done on one input line at a
     * time. Each inputLine from the inputfile is read, filtered
     * (quality processing), written to a group and finally shortread
     * object is created and added to a list. After the inputlines have been
     * processed, a tag file is generated.
     */
    private void preprocessingFlow(List<File> inputFiles)
    {
        InputLine tempInputLine;
        String[] line = null;
        String[] linePairedEnd = null;

        int count = 0;

        preProHandler.createQualityFileWriter(isQualityFileSaved);
        
        try
        {
            csvReaderInputFile =
                    new CSVReader(new FileReader(inputFiles.get(0)), ':');

            if (isPairedEnd)
            {
                csvReaderInputPairedEndFile = new
                        CSVReader(new FileReader(inputFiles.get(1)), ':');
            }

            while ((line = csvReaderInputFile.readNext()) != null)
            {
                String column1 = line[0];
                String column2 = line[1];
                String column3 = line[2];
                String column4 = line[3];
                String column5 = line[4];
                String shortRead = line[5];
                String quality = line[6];

                if (shortRead.matches("[ACGTN]+"))
                {
                    tempInputLine = new InputLine(column1, column2, column3,
                            column4, column5, shortRead, quality);
                    preProHandler.setInputLine(tempInputLine);
                }

                if (isPairedEnd)
                {
                    // Do the same to paired end inputLine
                    linePairedEnd = csvReaderInputPairedEndFile.readNext();

                    try
                    {
                        column1 = linePairedEnd[0];
                        column2 = linePairedEnd[1];
                        column3 = linePairedEnd[2];
                        column4 = linePairedEnd[3];
                        column5 = linePairedEnd[4];
                        shortRead = linePairedEnd[5];
                        quality = linePairedEnd[6];
                    }
                    catch (NullPointerException npe)
                    {
                        JOptionPane.showMessageDialog(
                                null, "The two query files "
                        + "do not have the same number of lines, and thus "
                        + "are not paired files. The program will now exit.",
                        "FASTAlign", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    if (shortRead.matches("[ACGNT]+"))
                    {
                        tempInputLine = new InputLine(column1, column2, column3,
                            column4, column5, shortRead, quality);
                        preProHandler.setInputPairedEndLine(tempInputLine);

                        if (selectedPairedEndInIdSelection)
                        {
                            // Swap inputLine and inputPairedEndLine
                            preProHandler.setInputPairedEndLine(
                                    preProHandler.getInputLine());
                            preProHandler.setInputLine(tempInputLine);
                        }
                    }
                }
                
                // True if the line is NOT filtered out
                // during quality processing
                boolean hasPassedFilter = preProHandler.
                        doQualityProcessing(getQSum(), getQualityASCII());
                if (hasPassedFilter)
                {
                    // Only group the lines that have passed the quality
                    // threshold
                   preProHandler.
                           doGrouping(inputIDRegionLeft, inputIDRegionRight);
                }
                count++;
                //System.out.println("At count: " + count);

                /* Update progress label */
                view.getInputPanel().getInputEnd().setCount(count);
            }

            preProHandler.doTagFile();
            preProHandler.doTagFilePairedEnd();

            csvReaderInputFile.close();
            try
            {
                preProHandler.getQualityCSVWriter().close();
                preProHandler.getQualityPairedEndCSVWriter().close();
            }
           catch (NullPointerException e)
            {

            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
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
     * The method is called to set the quality thresholds :
     * quality sum and quality ASCII values.
     * @param qSum The sum of the ASCII characters.
     * @param qASCII The minimum ASCII character.
     */
    public void setQualityThreshold(int qSum, int qASCII)
    {
        this.qualitySum = qSum;
        this.qualityASCII = qASCII;
    }

    public void setQualitySum(int qSum)
    {
        qualitySum = qSum;
    }

    public void setQualityASCII(int qASCII)
    {
        qualityASCII = qASCII;
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

    public final void setReferenceFileList(List<File> refFileList)
    {
        referenceFileList = refFileList;
    }

    public List<File> getRefFileList()
    {
        return referenceFileList;
    }

    /**
     * The method specifies the start and the end index of the
     * highlighted region which is the id of the shortread.
     * @param idLeft
     * @param idRight
     */
    public final void
            setInputIDRegion(final int inputIDLeft, final int inputIDRight)
    {
        this.inputIDRegionLeft = inputIDLeft;
        this.inputIDRegionRight = inputIDRight;
    }

    public final void generateRefFASTAFile()
    {
        for (int i = 0; i < referenceFileList.size(); i++)
        {
            try
            {
                CSVReader refReader = new CSVReader(
                        new FileReader(referenceFileList.get(i)), ',');

                String[] line;
                line = refReader.readNext(); // ignore the first line. this is
                                            // the header.

                // append is true to multiple reference files.
                FileWriter refFASTAWriter = new FileWriter(
                        System.getProperty("user.dir") + File.separator
                        + "reference.fa", true);

                while ((line = refReader.readNext()) != null)
                {
                    String tempString = line[0];
                    for (int j = 0; j < refColumnHeadersIndex.size(); j++)
                    {
                        tempString += "|" + line[refColumnHeadersIndex.get(j)];
                    }

                    refFASTAWriter.write(">" + tempString + "\n");
                    refFASTAWriter.write(line[refRegionColumn].
                            substring(blastRegionStart, blastRegionEnd)
                            + "\n");
                }
                refReader.close();
                refFASTAWriter.close();
            }
            catch (IOException ie)
            {
            }
        }
    }

    public final void convertInputToFASTA()
    {
        ShortReadComparator src = new ShortReadComparator();
        Collections.sort(preProHandler.getShortReadList(), src);

        try
        {
            List<ShortRead> tempList = preProHandler.getShortReadList();
            String id = tempList.get(0).getID();
            for (int i = 0; i < tempList.size(); i++)
            {
                if (!id.equals(tempList.get(i).getID()))
                {
                    id = tempList.get(i).getID();
                }
                FileWriter f0 =
                        new FileWriter(System.getProperty("user.dir")
                                        + File.separator + id + ".fa", true);
                f0.write(">" + tempList.get(i).getID() + "_"
                        + tempList.get(i).getCount() + "_"
                        + tempList.get(i).getRegion() + "\n");

                f0.write(tempList.get(i).getRegion() + "\n");
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
        String[] line = null;
        try
        {
            while ((line = readerInputFile.readNext()) != null)
            {
                String column1 = line[0];
                String column2 = line[1];
                String column3 = line[2];
                String column4 = line[3];
                String column5 = line[4];
                String shortRead = line[5];
                String quality = line[6];

                if (shortRead.matches("[ACNGT]+"))
                {
                    tempInputLine = new InputLine(column1, column2, column3,
                            column4, column5, shortRead, quality);

                    inputList.add(tempInputLine);

                    String id = shortRead.substring(
                            inputIDRegionLeft, inputIDRegionRight);

                    String region = shortRead.substring(
                            inputBLASTRegionLeft, inputBLASTRegionRight);

                    preProHandler.getShortReadList().add(
                            new ShortRead(id, region));
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ModelInterface.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        for (Enumeration ee = preProHandler.getHashTable().keys();
        ee.hasMoreElements();)
        {
            preProHandler.getShortReadList().add((ShortRead)
                    preProHandler.getHashTable().get(ee.nextElement()));
        }
    }

    public void generateInputFASTAFile() throws IOException
    {
        CSVReader readerInputFile = null;

        int countA = 0;
        int countC = 0;
        int countG = 0;
        int countT = 0;
        int countN = 0;
            
        FileWriter tagWriter = new FileWriter(
                    new File(System.getProperty("user.dir") + File.separator
                    + "input.tag"));

        FileWriter inputFAWriter = new FileWriter(
                    new File(System.getProperty("user.dir") + File.separator
                    + "input.fa"));

   
        //CSV Reader to read input file
        try
        {
            readerInputFile = new CSVReader(new FileReader(inputFile1), ':');
        } 
        catch (Exception ex)
        {
            Logger.getLogger(ModelInterface.class.getName()).log(Level
                    .SEVERE, null, ex);
        }

        int i = 0;
        String[] line;

        try
        {
//            BufferedWriter bw = new BufferedWriter(
//                    new FileWriter(System.getProperty("user.dir")
//                    + File.separator + "input.fa"));
            ShortRead sr;
            while ((line = readerInputFile.readNext()) != null)
            {
                String id = line[5].substring(inputIDRegionLeft,
                        inputIDRegionRight);
                String region = line[5].substring(inputBLASTRegionLeft,
                        inputBLASTRegionRight);
                String key = id + region;

                sr = new ShortRead(id, region);

                if(id.charAt(0) == 'A')
                {
                    try
                    {
                        FileOutputStream fos1 = new FileOutputStream(System.getProperty("user.dir") + File.
                       separator + "A_hashtable.tmp", true);
                        
                        DataOutputStream oos1 = new DataOutputStream(fos1);
                        oos1.writeChars(key);

                        oos1.close();
                        fos1.close();
                      //  System.out.println("ID: " + id + "\tA");
                         countA++;
                        
                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
                if(id.charAt(0) == 'C')
                {
                    try
                    {
                        FileOutputStream fos2 = new FileOutputStream
                                  (System.getProperty("user.dir") + File.separator + "C_hashtable.tmp", true);

                       DataOutputStream oos2 = new DataOutputStream(fos2);
                        oos2.writeChars(key);

                        oos2.close();
                        fos2.close();
                      //  System.out.println("ID: " + id + "\tC");
                         countC++;

                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
                if(id.charAt(0) == 'G')
                {
                     try
                    {
                         FileOutputStream fos3 = new FileOutputStream(System.getProperty("user.dir") + File.
                      separator + "G_hashtable.tmp", true);
                       DataOutputStream oos3 = new DataOutputStream(fos3);
                        oos3.writeChars(key);

                        oos3.close();
                        fos3.close();
                       // System.out.println("ID: " + id + "\tG");
                         countG++;

                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
                if(id.charAt(0) == 'T')
                {
                    try
                    {
                         FileOutputStream fos4 = new FileOutputStream(System.getProperty("user.dir") + File.
                      separator + "T_hashtable.tmp", true);
                       DataOutputStream oos4 = new DataOutputStream(fos4);
                        oos4.writeChars(key);

                        oos4.close();
                        fos4.close();
                     //   System.out.println("ID: " + id + "\tT");
                         countT++;

                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
                if(id.charAt(0) == 'N')
                {
                    try
                    {
                        FileOutputStream fos5 = new FileOutputStream(System.getProperty("user.dir") + File.
                      separator + "N_hashtable.tmp", true);
                      DataOutputStream oos5 = new DataOutputStream(fos5);
                        oos5.writeChars(key);

                        oos5.close();
                        fos5.close();
                       // System.out.println("ID: " + id + "\tN");
                         countN++;

                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
              //  System.out.println("i: " + i);
                i++;
               // System.out.println(i + "|" + hTable.size());
            }

            try
            {
                FileInputStream fis = new FileInputStream(System.getProperty
                        ("user.dir") + File.separator + "A_hashtable.tmp");
                Hashtable htA = covertFiletoHT(fis, countA);

                Enumeration e = htA.elements();
                while(e.hasMoreElements())
                {
                    ShortRead sr2 = (ShortRead)e.nextElement();
                    tagWriter.write(sr2.getCount() + "\t\t" + sr2.getRegion() + "\n");
                    inputFAWriter.write(">" + sr2.getID() + "|" + sr2.getRegion() + "|" + sr2.getCount() + "\n");
                    inputFAWriter.write(sr2.getRegion() + "\n");
                }
            }
            catch(FileNotFoundException ee)
            {


            }
            try
            {
                FileInputStream fis1 = new FileInputStream(System.getProperty
                        ("user.dir") + File.separator + "C_hashtable.tmp");
                    Hashtable htC = covertFiletoHT(fis1, countC);

                 Enumeration e = htC.elements();
                while(e.hasMoreElements())
                {
                    ShortRead sr2 = (ShortRead)e.nextElement();
                    tagWriter.write(sr2.getCount() + "\t\t" + sr2.getRegion() + "\n");
                    inputFAWriter.write(">" + sr2.getID() + "|" + sr2.getRegion() + "|" + sr2.getCount() + "\n");
                    inputFAWriter.write(sr2.getRegion() + "\n");
                }

            }
            catch(FileNotFoundException ee)
            {

            }
            try
            {
                FileInputStream fis3 = new FileInputStream(System.getProperty
                        ("user.dir") + File.separator + "G_hashtable.tmp");

                Hashtable htG = covertFiletoHT(fis3, countG);

                Enumeration e = htG.elements();
                while(e.hasMoreElements())
                {
                    ShortRead sr2 = (ShortRead)e.nextElement();
                    tagWriter.write(sr2.getCount() + "\t\t" + sr2.getRegion() + "\n");
                    inputFAWriter.write(">" + sr2.getID() + "|" + sr2.getRegion() + "|" + sr2.getCount() + "\n");
                    inputFAWriter.write(sr2.getRegion() + "\n");
                }


            }
            catch(FileNotFoundException ee)
            {

            }
            try
            {
                 FileInputStream fis4 = new FileInputStream(System.getProperty
                        ("user.dir") + File.separator + "T_hashtable.tmp");

                Hashtable htT = covertFiletoHT(fis4, countT);
                 Enumeration e = htT.elements();
                while(e.hasMoreElements())
                {
                    ShortRead sr2 = (ShortRead)e.nextElement();
                    tagWriter.write(sr2.getCount() + "\t\t" + sr2.getRegion() + "\n");
                    inputFAWriter.write(">" + sr2.getID() + "|" + sr2.getRegion() + "|" + sr2.getCount() + "\n");
                    inputFAWriter.write(sr2.getRegion() + "\n");
                }
            }
            catch(FileNotFoundException ee)
            {

            }
            try
            {
                  FileInputStream fis5 = new FileInputStream(System.getProperty
                        ("user.dir") + File.separator +  "N_hashtable.tmp");

                 Hashtable htN = covertFiletoHT(fis5, countN);
                  Enumeration e = htN.elements();
                while(e.hasMoreElements())
                {
                    ShortRead sr2 = (ShortRead)e.nextElement();
                    tagWriter.write(sr2.getCount() + "\t\t" + sr2.getRegion() + "\n");
                    inputFAWriter.write(">" + sr2.getID() + "|" + sr2.getRegion() + "|" + sr2.getCount() + "\n");
                    inputFAWriter.write(sr2.getRegion() + "\n");
                }

            }
            catch(FileNotFoundException ee)
            {

            }

             //System.out.println("I am here after hashing");

            

            /*Enumeration ee = hTable.elements();

            while(ee.hasMoreElements())
            {
                ShortRead sr2 = ((ShortRead) ee.nextElement());
//                System.out.println("ShortRead ID: " + sr2.getID());
                tagWriter.write(sr2.getCount() + "\t\t" + sr2.getRegion() 
                        + "\n");

                inputFAWriter.write(">" + sr2.getID() + "|" + sr2.getRegion() 
                        + "|" + sr2.getCount() + "\n");
                inputFAWriter.write(sr2.getRegion() + "\n");

            }
             * */
          

               // FileInputStream fis2 = new FileInputStream(System.getProperty
                       // ("user.dir") + File.separator + String.valueOf(j+1) + "_hashtable.tmp");
                //ObjectInputStream ois2 = new ObjectInputStream(fis);

               // Hashtable rHashTable2 = (Hashtable) ois2.readObject();
               // fis2.close();
               // ois2.close();

            inputFAWriter.close();
            tagWriter.close();
        }
        catch (Exception ee)
        {
            System.out.println(ee);
            ee.printStackTrace();
        }
    }
    public Hashtable covertFiletoHT(FileInputStream fis, 
            int numberOfSR) throws IOException, ClassNotFoundException
    {
        Hashtable ht = new Hashtable();
        DataInputStream ois = new DataInputStream(fis);
        //ShortRead sr = null;
        //String id;
       // String region;
        char c;
        int len = (inputIDRegionRight - inputIDRegionLeft)
                + (inputBLASTRegionRight - inputBLASTRegionLeft);
        int j = 0;
        //String line;

        char[] line = new char [len];

        for(int i = 0; i < numberOfSR; i++)
        {
            j =0;
            while(j < len)
            {
                line[j] = ois.readChar();
                j++;
            }
                String s = new String(line);
                //System.out.println("String: " + s + "Length:" + s.length());
                String id = s.substring(0,(inputIDRegionRight - inputIDRegionLeft));
                //System.out.println("ID: " + id);
                String region = s.substring(inputIDRegionRight - 
                        inputIDRegionLeft,inputBLASTRegionRight-
                        inputBLASTRegionLeft + inputIDRegionRight -
                        inputIDRegionLeft );
                //System.out.println("Region: " + region);
                ShortRead sr = new ShortRead(id, region);

                //System.out.println("ID: " + sr.getID());

            if (ht.get(sr.getID()) == null)
            {
                ht.put(sr.getID(), sr);
                 //System.out.println("ADD to hashtable: " );
            }
            else
            {
                ((ShortRead)ht.get(sr.getID())).increaseCount();
                 //System.out.println("Increment: " );
            }
        }

        fis.close();
        ois.close();
        return ht;
    }


    public final List<InputLine> getInputLineList()
    {
        return inputList;
    }

    public final List<ShortRead> getShortReadList()
    {
        return srList;
    }

    public final List<String> getRefHeaders()
    {
        return refHeaders;
    }

    public final void removeRefHeaders(int indexHeader)
    {
        this.refHeaders.remove(indexHeader);
    }

    public  final Hashtable getHT()
    {
        return htable;
    }

    public final void printShortReadList()
    {
        for (int i = 0; i < inputList.size(); i++)
        {
            System.out.println("DNA: " + inputList.get(i).getShortRead());
        }
    }

    public final String printInputLine(final int i)
    {
        return  inputList.get(i).getColumn1() + ":"
                + inputList.get(i).getColumn2() + ":"
                + inputList.get(i).getColumn3() + ":"
                + inputList.get(i).getColumn4() + ":"
                + inputList.get(i).getColumn5() + ":"
                + inputList.get(i).getShortRead() + ":"
                + inputList.get(i).getQualityASCII();
    }

    public final void printDetailedShortReadList()
    {
        for (int i = 0; i < inputList.size(); i++)
        {
            System.out.println(inputList.get(i).getShortRead() + "|"
                    + inputList.get(i).getQualityASCII() + "|"
                    + inputList.get(i).getQualitySum() + "|"
                    + inputList.get(i).getQuality2ndMin());
        }
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

    public  final Integer getInputIDStart()
    {
        return inputIDRegionLeft;
    }

    public  final Integer getInputIDEnd()
    {
        return inputIDRegionRight;
    }

    public final Integer getInputBLASTRegionStart()
    {
        return inputBLASTRegionLeft;
    }

    public final Integer getInputBLASTRegionEnd()
    {
        return inputBLASTRegionRight;
    }

    public final void setRefRegionColumn(final int n)
    {
        refRegionColumn = n;
    }

    public final int getRefRegionColumn()
    {
        return refRegionColumn;
    }

    public final int getRefIDColumn()
    {
        return refIDColumn;
    }

    public final void setRefIDColumn(final int n)
    {
        refIDColumn = n;
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

    public final int getBLASTRegionStart()
    {
        return blastRegionStart;
    }

    public final int getBLASTRegionEnd()
    {
        return blastRegionEnd;
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

    public void setPairedEnd(boolean b)
    {
        this.isPairedEnd = b;
        preProHandler.setIsPairedEnd(b);
    }

    public boolean getIsQualityFileSaved()
    {
        return isQualityFileSaved;
    }

    public void setSaveQualityFile(boolean saveValue)
    {
        isQualityFileSaved = saveValue;
    }

    public void setHasGeneratedPairedEndTextBoxes(boolean b)
    {
        this.hasGeneratedPairedEndTextBoxes = b;
    }

    public boolean getHasGeneratedPairedEndTextBoxes()
    {
        return hasGeneratedPairedEndTextBoxes;
    }

    public void setSelectedPairedEndInIdSelection(boolean b)
    {
        this.selectedPairedEndInIdSelection = b;
    }

    public PreProcessingHandler getPreProcessingHandler()
    {
        return preProHandler;
    }

    public boolean getSelectedPairedEndInIDSelection()
    {
        return selectedPairedEndInIdSelection;
    }

    public void setAlgorithmIndex(int aIndex)
    {
        this.algorithmIndex = aIndex;
    }

    public int getAlgorithmIndex()
    {
        return algorithmIndex;
    }

    public List<Integer> getRefColumnHeaderIndex()
    {
        return refColumnHeadersIndex;
    }

    public void setRefColumnHeaderIndex(List<Integer> refIndex)
    {
        this.refColumnHeadersIndex = refIndex;
    }

    public boolean checkForConflictsInGroups()
    {
        return preProHandler.checkGroupsForConflicts();
    }
    public int getLengthOfIDRegion()
    {
        return (getInputIDEnd() - getInputIDStart());
    }

    public List<AbstractAlignmentTool> getListofTools()
    {
        return listOfTools;
    }

    public View getView()
    {
        return view;
    }

    public void setView(View view)
    {
        this.view = view;
    }
}

