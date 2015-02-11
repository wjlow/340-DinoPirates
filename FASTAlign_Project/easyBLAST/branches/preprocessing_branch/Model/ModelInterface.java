package Model;

import Model.opencsv.CSVReader;
import java.util.List;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;

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
    private PreProcessingHandler preprocessing;
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

        
    }

    /**
     * The method reads the first 20 lines of the specified file and returns
     * a list of first 20 lines of that file.
     * @return shortreads - is a list containing 20 lines of shortreads
     * @throws java.io.IOException
     */
    public List<String> getPreviewLines(File inputFile) throws IOException
    {
        List<String> shortReads = new ArrayList<String>();
        String line[]= null;
        int count = 0;

        try
        {
            csvReaderPreview = new CSVReader(new FileReader(inputFile), ':');

            while ((line = csvReaderPreview.readNext()) != null &&
                    (count <= numberOfLines))
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
        String line[]= null;
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
    public int getIdColumn()
    {
        return idColumn;
    }

    /**
     * The method returns the starting index of the id string,
     * which is the idLeft.
     * @return idLeft
     */
    public int getIdLeft()
    {
        return idLeft;
    }

    /**
     * The method returns the end index of the id string, which is the idRight
     * @return idRight.
     */
    public int getIdRight()
    {
        return idRight;
    }

    /**
     * The method sets the start and the end index of string which is the
     * blast region.
     * @param regionLeft
     * @param regionRight
     */
    public void setBlastRegion(int regionLeft, int regionRight)
    {
        inputBlastRegionLeft = regionLeft;
        inputBlastRegionRight = regionRight;
    }

    /**
     * The method returns the set quality sum value.
     * @return qualitySum
     */
    public int getQSum()
    {
        return qualitySum;
    }

    /**
     * The method returns the set quality ASCII value.
     * @return qualityASCII
     */
    public int getQualityASCII()
    {
        return qualityASCII;
    }

     /**
     * The method defines the variable inputFile.
     * @param inputFile is an inputFile
     */
    public void setInputFile(File inputFile)
    {
        this.inputFile = inputFile;
    }


    /**
     * The method is called to set the quality thresholds :
     * quality sum and quality ASCII values.
     * @param qualitySum
     * @param qualityASCII
     */
    public void setQualityThreshold(int qualitySum, int qualityASCII)
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
    public void setIDRegion(int idLeft, int idRight)
    {
        this.idLeft = idLeft;
        this.idRight = idRight;
    }

      /**
     * The method calls make makeGroups() to create Group files.
     * @param groups
     */
    public void callMakeGroups(String groups [][])
    {
        preprocessing.makeGroups(groups);
    }
    
    /**
     * The method calls doGrouping() for each inputLine.
     */
    public void callDoGrouping()
    {
        for(int i=0; i<ilList.size(); i++)
        {
            preprocessing.setInputLine(ilList.get(i));
            preprocessing.doGrouping(id_start, id_end);
        }
    }


    Boolean isBLASTMode;
    List<InputLine> ilList = new Vector<InputLine>();
    List<ShortRead> srList = new Vector<ShortRead>();

    List<String> refHeaders = new ArrayList();

    Hashtable ht = new Hashtable(30);

    File in1 = null;
    File in2 = null;

    File ref1 = null;

    int refIDColumn;
    int refRegionColumn;

    Integer minASCII = 70;
    Integer minSum = 0;

    Integer id_start = 0;
    Integer id_end = 2;

    Integer region_start = 28;
    Integer region_end = 35;

    String group1 = null;
    String group2 = null;

    Double expFailMarg = 10.0;
    Integer winSize = 0;
    Integer numHits = 0;
    Integer queryStrands = 3;
    Integer numDBSeq1Ln = 500;
    Integer numDBSeqAlig = 50;

    public ModelInterface()
    {

    }

    public List<InputLine> getInputLineList()
    {
        return ilList;
    }

    public List<ShortRead> getShortReadList()
    {
        return srList;
    }

    public List getRefHeaders()
    {
        return refHeaders;
    }

    public Hashtable getHT()
    {
        return ht;
    }

    public void printShortReadList()
    {
        for(int i=0; i<ilList.size(); i++)
        {
            System.out.println("DNA: " + ilList.get(i).getShortRead());
        }
    }

    public String printInputLine(int i)
    {
        return  ilList.get(i).getColumn1() + ":" +
                ilList.get(i).getColumn2() + ":" +
                ilList.get(i).getColumn3() + ":" +
                ilList.get(i).getColumn4() + ":" +
                ilList.get(i).getColumn5() + ":" +
                ilList.get(i).getShortRead() + ":" +
                ilList.get(i).getQualityASCII();
    }

    public void printDetailedShortReadList()
    {
        for(int i=0; i<ilList.size(); i++)
        {
            System.out.println(ilList.get(i).getShortRead()+"|"+ilList.get(i).getQualityASCII()+"|"+ilList.get(i).getQualitySum()+"|"+ilList.get(i).getQualityMin());
        }
    }

    public File getFile1()
    {
        return inputFile;
    }

 
    public void setFile2(File f)
    {
        in2 = f;
    }

    public File getFile2()
    {
        return in2;
    }

    public File getRefFile()
    {
        return ref1;
    }

    public void setRefFile(File f)
    {
        ref1 = f;
    }

    public void setMinASCII(int i)
    {
        minASCII = i;
    }

    public void setMinSum(int i)
    {
        minSum = i;
    }

    public Integer getMinASCII()
    {
        return minASCII;
    }

    public Integer getMinSum()
    {
        return minSum;
    }

    public Integer getIDStart()
    {
        return id_start;
    }

    public Integer getIDEnd()
    {
        return id_end;
    }

    public void setIDStart(Integer n)
    {
        id_start = n;
    }

    public void setIDEnd(Integer n)
    {
        id_end = n;
    }

    public Integer getRegionStart()
    {
        return region_start;
    }

    public Integer getRegionEnd()
    {
        return region_end;
    }

    public void setRegionStart(Integer n)
    {
        region_start = n;
    }

    public void setRegionEnd(Integer n)
    {
        region_end = n;
    }

    public void setRefRegionColumn(int n)
    {
        refRegionColumn = n;
    }

    public int getRefRegionColumn()
    {
        return refRegionColumn;
    }

    public Double getExpFailureMargin()
    {
        return expFailMarg;
    }

    public void setExpFailMargin(Double n)
    {
        expFailMarg = n;
    }
    public Integer getWinSize()
    {
        return winSize;
    }

    public void setWinSize(Integer n)
    {
        winSize = n;
    }

    public Integer getNumHits()
    {
        return numHits;
    }

    public void setNumHits(Integer n)
    {
        numHits = n;
    }

    public Integer getQueryStrands()
    {
        return queryStrands;
    }

    public void setQueryStrands(Integer n)
    {
        queryStrands = n;
    }

    public Integer getNumDBSeq1Ln()
    {
        return numDBSeq1Ln;
    }

    public void setNumDBSeq1Ln(Integer n)
    {
        numDBSeq1Ln = n;
    }

    public Integer getNumDBSeqAlig()
    {
        return numDBSeqAlig;
    }

    public void setNumDBSeqAlig(Integer n)
    {
        numDBSeqAlig = n;
    }

    public boolean isBLASTMode()
    {
        return isBLASTMode;
    }

    public void setBLASTMode(Boolean b)
    {
        isBLASTMode = b;
    }
}
