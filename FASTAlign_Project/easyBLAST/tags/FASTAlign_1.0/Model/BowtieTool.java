package Model;

/**
 * This class holds the pathnames to the BLAST alignment tool, FASTA reference
 * file, output file and the parameters for BLAST
 * @author wengn
 */
public class BowtieTool extends AbstractAlignmentTool
{
    public BowtieTool(String algorithmName,
            String pathNameToAlignmentTool, String pathNametoFormatTool,
             Parameters[] alignparameters, Parameters[] formatparameters)
    {
        super(algorithmName, pathNameToAlignmentTool, pathNametoFormatTool,
                alignparameters, formatparameters);
    }
}
