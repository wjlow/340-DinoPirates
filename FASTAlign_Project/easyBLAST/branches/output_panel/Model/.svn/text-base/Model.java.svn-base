package Model;

import java.util.List;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.*;

public class Model
{
    File inputFile = null;
    PreProcessingHandler preprocessing;
    List<String> groups;
    private int idcolumn;
    private int idLeft;
    private int idRight;
    private int qualitysum;
    private int qualityASCII;

    public Model(File iFile, List<String> groups, int idcolumn, int idLeft,
            int idRight, int qsum, int qASCII)
    {
        inputFile = iFile;
        this.groups = groups;
        this.idcolumn = idcolumn;
        this.idLeft = idLeft;
        this.idRight = idRight;
        qualitysum = qsum;
        qualityASCII = qASCII;
    }

    public int getIdColumn()
    {
        return idcolumn;
    }
    public int getIdLeft()
    {
        return idLeft;
    }

    public int getIdRight()
    {
        return idRight;
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

    Integer blast_region_start = null;
    Integer blast_region_end = null;

    String group1 = null;
    String group2 = null;

    Double expFailMarg = 10.0;
    Integer winSize = 0;
    Integer numHits = 0;
    Integer queryStrands = 3;
    Integer numDBSeq1Ln = 500;
    Integer numDBSeqAlig = 50;

    public Model()
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
        return in1;
    }

    public void setFile1(File f)
    {
        in1 = f;
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

    public void setBLASTRegion(Integer start, Integer end)
    {
        blast_region_end = end;
        blast_region_start = start;
    }

    public Integer getBLASTRegionStart()
    {
        return blast_region_start;
    }

    public Integer getBLASTRegionEnd()
    {
        return blast_region_end;
    }
}
