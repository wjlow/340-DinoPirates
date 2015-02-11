package Model;

public class InputLine {

    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String shortread;
    private String quality;

    public InputLine(String c1, String c2, String c3, String c4, String c5, String sr, String q)
    {
        if(sr.matches("[ACGT]+"))
        {
            this.column1 = c1;
            this.column2 = c2;
            this.column3 = c3;
            this.column4 = c4;
            this.column5 = c5;
            this.shortread = sr;
            this.quality = q;
        }
        else
        {
            //throw exception
        }
    }

    public String toString()
    {
        return column1 + ":" +
                column2 + ":" +
                column3 + ":" +
                column4 + ":" +
                column5 + ":" +
                shortread + ":" +
                quality;
    }

    public String getColumn1()
    {
        return column1;
    }

    public String getColumn2()
    {
        return column2;
    }
    public String getColumn3()
    {
        return column3;
    }
    public String getColumn4()
    {
        return column4;
    }
    public String getColumn5()
    {
        return column5;
    }

    public String getShortRead()
    {
        return shortread;
    }

    public String getQualityASCII()
    {
        return quality;
    }

    public int getQualitySum()
    {
        int sum = 0;

        for (int i = 0; i<quality.length(); i++)
        {
            sum += (int)quality.charAt(i);
        }
        return sum;
    }

    public int getQualityMin()
    {
        int min = (int)quality.charAt(0);

        for (int i = 1; i<quality.length(); i++)
        {
            if (min > (int)quality.charAt(i))
                min = (int)quality.charAt(i);
        }
        return min;
    }
}
