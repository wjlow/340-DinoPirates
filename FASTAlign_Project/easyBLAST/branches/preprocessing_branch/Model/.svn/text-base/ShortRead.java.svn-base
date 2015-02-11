package Model;

/**
 * This class represents a particular short read in an input file.
 * This class is used for generating tag files.
 *
 * @key Unique identification of each ShortRead object.
 * @id Identification of each short read.
 * @blastRegion Region of BLASTing of each short read.
 * @count The number of times this ShortRead object has occurred.
 * @author wjlow
 */
public class ShortRead {

    private String key;
    private String id;
    private String blastRegion;
    private Integer count;

    public ShortRead(String id, String region)
    {
        this.key = id + region;
        this.id = id;
        this.blastRegion = region;
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
}
