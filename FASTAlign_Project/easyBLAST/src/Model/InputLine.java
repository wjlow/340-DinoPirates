package Model;

/**
 * A line from an input file that is colon separated.
 */
public class InputLine
{

    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String shortread;
    private String quality;

    /**
     * A new InputLine is created.
     * @param c1 The first column of input file.
     * @param c2 The second column of input file.
     * @param c3 The third column of input file.
     * @param c4 The fourth column of input file.
     * @param c5 The fifth column of input file.
     * @param sr The sixth column of input file, i.e. the short read.
     *           If it contains characters that are neither A, C, G nor T,
     *           then an exception is thrown.
     * @param q The seventh column of input file, i.e. the quality.
     */
    public InputLine(String c1, String c2, String c3, String c4, String c5,
            String sr, String q)
    {
            this.column1 = c1;
            this.column2 = c2;
            this.column3 = c3;
            this.column4 = c4;
            this.column5 = c5;
            this.shortread = sr;
            this.quality = q;
    }

    @Override
    public String toString()
    {
        return column1 + ":" + column2 + ":" +  column3 + ":" + column4 + ":"
                + column5 + ":" + shortread + ":" + quality;
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

    /**
     *
     * @return The ASCII sum of the quality string is returned.
     */
    public int getQualitySum()
    {
        int sum = 0;

        for (int i = 0; i < quality.length(); i++)
        {
            sum += (int) quality.charAt(i);
        }
        return sum;
    }

    /**
     *
     * @param s The string to evaluate.
     * @return The ASCII sum of the s is returned.
     */
    public int getQualitySum(String s)
    {
        int sum = 0;

        for (int i = 0; i < s.length(); i++)
        {
            sum += (int) s.charAt(i);
        }
        return sum;
    }

    /**
     * @return The second minimum ASCII character of the quality string is
     * returned.
     */
    public int getQuality2ndMin()
    {
        int min2 = 129; //(int)quality.charAt(0); //2nd minimum ASCII
        int min1 = 129; //(int)quality.charAt(1); // minimum  ASCII
        //if (quality == null) System.out.println("QualityNULL!2");

        for (int i = 0; i < quality.length(); i++)
        {
            if (min2 > (int) quality.charAt(i))
            {
                if (min1 > (int) quality.charAt(i))
                {
                    min2 = min1;
                    min1 = (int) quality.charAt(i);
                }
                else
                {
                    min2 = (int) quality.charAt(i);
                }
            }
        }
        return min2;
    }

    /**
     *
     * @param s The string to evaluate.
     * @return The minimum ASCII character of the quality string is returned.
     */
    public int getQualityMin(String s)
    {
        int min = (int) s.charAt(0);

        for (int i = 1; i < s.length(); i++)
        {
            if (min > (int) s.charAt(i))
            {
                min = (int) s.charAt(i);
            }
        }
        return min;
    }

    public String[] printInputLine()
    {
        String[] array = {column1, column2, column3, column4, column5,
                            shortread, quality};
        return array;
    }
}
