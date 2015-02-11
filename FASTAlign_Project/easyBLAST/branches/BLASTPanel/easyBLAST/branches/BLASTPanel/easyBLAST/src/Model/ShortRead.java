package Model;

public class ShortRead {
    String id;
    String region;
    Integer count;
    public ShortRead(String id, String region)
    {
        this.id = id;
        this.region = region;
        this.count = 1;
    }

    public String getID()
    {
        return id;
    }

    public String getRegion()
    {
        return region;
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
