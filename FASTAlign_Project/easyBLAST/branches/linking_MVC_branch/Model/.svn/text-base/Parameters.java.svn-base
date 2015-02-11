/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 * This class contains all the parameters that will be used by BLAST.
 * There should only be one Parameters object at any one time during runtime.
 * @author wjlow
 */
public class Parameters
{
    /**
     * Alignment view options.
     */
    private Integer m;
    /**
     * Expectation value.
     */
    private Integer e;
    /**
     * Word size, default if zero (blastn 11, megablast 28, all others 3).
     */
    private Integer W;
    /**
     * Number of best hits from a region to keep. Off by default.
     */
    private Integer K;

    /**
     * Construct the object with default parameter values for each flag.
     */
    public Parameters()
    {
        this.m = 8;
        this.e = 10;
        this.W = 11;
        this.K = 1;
    }

    public void setM(Integer m)
    {
        this.m = m;
    }

    public void setE(Integer e)
    {
        this.e = e;
    }

    public void setW(Integer W)
    {
        this.W = W;
    }

    public void setK(Integer K)
    {
        this.K = K;
    }

    public Integer getM()
    {
        return m;
    }

    public Integer getE()
    {
        return e;
    }

    public Integer getW()
    {
        return W;
    }

    public Integer getK()
    {
        return K;
    }

    public String toString()
    {
        return "-m " + m.toString() + " -e " + e.toString() + " -W " + W.toString() + " -K " + K.toString();
    }
}
