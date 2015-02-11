package Model;

import java.util.List;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.*;

public class Model
{
    Boolean isBLASTMode; // dummy commit on lbang_branch
    List<InputLine> ilList = new Vector<InputLine>();
    List<ShortRead> srList = new Vector<ShortRead>();

    List<String> refHeaders = new ArrayList();

    Hashtable ht = new Hashtable(30);

    File in1 = null;
    File in2 = null;

    File ref1 = null;

    int refIDColumn;
    int refRegionColumn;

    Integer minASCII = 0;
    Integer minSum = 0;

    Integer id_start = 0;
    Integer id_end = 2;

    Integer region_start = 28;
    Integer region_end = 35;

    String group1 = null;
    String group2 = null;

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
                ilList.get(i).getQualityASCII() + "\n";
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

    public void setMinASCII(Integer i)
    {
        minASCII = i;
    }

    public void setMinSum(Integer i)
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

    public boolean isBLASTMode()
    {
        return isBLASTMode;
    }

    public void setBLASTMode(Boolean b)
    {
        isBLASTMode = b;
    }
}
