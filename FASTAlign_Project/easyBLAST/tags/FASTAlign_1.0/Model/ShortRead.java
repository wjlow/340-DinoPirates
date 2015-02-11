package Model;

import java.io.Serializable;

/**
 * This class represents a particular short read in an input file.
 * This class is used for generating tag files.
 */
public class ShortRead implements Serializable
{
    private String key;
    private String id;
    private String blastRegion;
    private Integer count;

    public ShortRead(String i, String region)
    {
        this.key = i + region;
        this.id = i;
        this.blastRegion = region;
        count = 1;
    }

    //For PreprocessingFlow tag file generation.
    public ShortRead(String k)
    {
        this.key = k;
        this.id = "";
        this.blastRegion = "";
        count = 1;
    }

    public String getID()
    {
        return id;
    }

    public String getRegion()
    {
        return blastRegion;
    }

    public Integer getCount()
    {
        return count;
    }

    public void increaseCount()
    {
        count++;
    }
    public String getKey()
    {
        return key;
    }
}
