package Model;

/**
 * This class provides an abstract class to be extended by different
 * alignment algorithms. The alignment tool class requires to extend this class
 * to instantiate.
 * @author wengn
 */
public abstract class AbstractAlignmentTool
{
    private String algorithmName;
    private String alignerFileName;
    private String formatterFileName;
    private Parameters[] alignParameters;
    private Parameters[] formatParameters;

    public AbstractAlignmentTool(String aName, String pathATool,
            String pathFTool, Parameters[] para, Parameters[] para2)
    {
        algorithmName = aName;
        alignerFileName = pathATool;
        formatterFileName = pathFTool;
        alignParameters = para;
        formatParameters = para2;
    }

    public void setAlgorithmName(String aName)
    {
        this.algorithmName = aName;
    }

    public void setAlignmentTool(String pathATool)
    {
        this.alignerFileName = pathATool;
    }

    public void setFormatReferenceFileTool(String pathFTool)
    {
        this.formatterFileName = pathFTool;
    }

    public String getAlgorithmName()
    {
        return algorithmName;
    }

    public String getAlignmentTool()
    {
        return alignerFileName;
    }

    public String getFormatReferenceFileTool()
    {
        return formatterFileName;
    }

    public Parameters[] getFormatParams()
    {
        return formatParameters;
    }

    public Parameters[] getAlignParameters()
    {
        return alignParameters;
    }
}
