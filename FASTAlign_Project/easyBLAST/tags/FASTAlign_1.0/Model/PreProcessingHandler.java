package Model;

import Model.opencsv.CSVWriter;
import View.View;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 * This class is responsible for communicating with other classes
 * that are involved in handling the PreProcessing Flow.
 */
public class PreProcessingHandler
{

    private InputLine inputLine = null;
    private InputLine inputPairedEndLine = null;
    private List<ShortRead> shortReadList = new Vector<ShortRead>();
    private List<File> groupedFiles = new ArrayList<File>();
    private List<File> groupedPairedEndFiles = new ArrayList<File>();
    private List<Group> tempGroups = new ArrayList<Group>();
    private CSVWriter qualityWriter = null;
    private CSVWriter qualityWriterPairedEnd = null;
    private CSVWriter groupWriter = null;
    private Hashtable shortReadsHashTable = new Hashtable();
    private File qualityFile = null;
    private int countWrites = 0;
    private boolean isPairedEnd;
    private List<Hashtable> shortReadHT = new ArrayList<Hashtable>();
    private List<Hashtable> pairedEndShortReadHT = new ArrayList<Hashtable>();

    private View view;

    public PreProcessingHandler()
    {
    }

    /**
     * The methods creates initialises qualitWriter to write to the specified
     * quality file
     */
    public final void createQualityFileWriter()
    {
        try
        {
            File tempQualityFile =
                    qualityFile.createTempFile(
                    "quality_filtered", ".txt", qualityFile);

            qualityWriter = new CSVWriter(new FileWriter(tempQualityFile, true),
                    ':', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "The file cannot be opened. "
                    + "Please return to the Quality Panel", "easyBLAST",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void createQualityFileWriter(boolean b)
    {
        try
        {
            File tempQualityFile =
                    new File(System.getProperty("user.dir")
                    + File.separator + "quality_filtered.txt");

            qualityWriter = new CSVWriter(new FileWriter(tempQualityFile, true),
                    ':', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            if (!b)
            {
                tempQualityFile.deleteOnExit();
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "The file cannot be opened. "
                    + "Please return to the Quality Panel", "easyBLAST",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (isPairedEnd)
        {
            try
            {
                File tempQualityFile2 =
                        new File(System.getProperty("user.dir")
                        + File.separator + "quality_filtered_pairedEnd.txt");

                qualityWriterPairedEnd = new CSVWriter(new FileWriter(
                        tempQualityFile2, true),
                        ':', CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);

                if (!b)
                {
                    tempQualityFile2.deleteOnExit();
                }
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "The file cannot be opened."
                        + " Please return to the Quality Panel", "easyBLAST",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method appends an input line to the Quality.txt file. Input line is
     * only appended to the file, if the the line's quality thresholds is above
     * the set thresholds.
     * @param qSum
     * @param qASCII
     * @throws IOException
     */
    public boolean doQualityProcessing(int qSum, int qASCII) throws IOException
    {
        if ((inputLine.getQuality2ndMin() >= qASCII))
        {
            if (inputLine.getQualitySum() >= qSum)
            {
                if (isPairedEnd)
                {
                    if((inputPairedEndLine.getQuality2ndMin() >= qASCII)
                            && (inputPairedEndLine.getQualitySum() >= qSum ))
                    {
                        try
                        {
                            qualityWriter.writeNext(inputLine.printInputLine());
                            countWrites++;
                            qualityWriter.flush();
                        }
                        catch (NullPointerException ee)
                        {

                        }

                        try
                        {
                            qualityWriterPairedEnd.writeNext(inputPairedEndLine
                                    .printInputLine());
                            countWrites++;
                            qualityWriterPairedEnd.flush();
                            return true;
                        }
                        catch (NullPointerException ee)
                        {

                        }
                    }
                }
                else
                {
                    try
                    {
                        qualityWriter.writeNext(inputLine.printInputLine());
                        countWrites++;
                        qualityWriter.flush();
                        return true;
                    }
                    catch (NullPointerException ee)
                    {

                    }
                }
            }
            return false;
        }
        return false;
    }

    /**
     * This method creates empty group files as initiation.
     * Note that it does not add identifications into each file, it merely
     * creates empty files based on the list of available groups.
     * A 2-D array of Strings is passed in. It is also made available as a local
     * attribute to be accessed by doGrouping();
     * The file created is stored in a list of files and the Group created is
     * stored in a list of groups. A group will correspond to a file and will
     * have the same index for easy retrieval in the doGrouping()
     * @param groups A 2-D array of Strings that contains Ids for each group
     */
    public void makeGroups(List<List<String>> groups)
    {
        tempGroups.clear();
        groupedFiles.clear();
        groupedPairedEndFiles.clear();

        int count = 1;
        for (int i = 0; i < groups.size(); i++) {
            // Go through every id in the group and append each id to the
            // filename

            String groupFileName = "Group" + count;

            String appendedIds = "";

            for (int j = 0; j < groups.get(i).size(); j++) {
                // Go through every id in the current group
                // add each id to a String
                appendedIds = appendedIds + "_" + groups.get(i).get(j);
            }

            appendedIds = appendedIds.replace('*', 'x');

            groupFileName = groupFileName + appendedIds + ".txt";

            // Check if there is a paired end file
            // If yes, create grouped files for the paired end files
            if (isPairedEnd) {
                String groupPairedEndFileName = "Group" + count + "_2";
                groupPairedEndFileName =
                        groupPairedEndFileName + appendedIds + ".txt";
                File groupPairedEndFile =
                        new File(System.getProperty("user.dir") + File.separator
                        + groupPairedEndFileName);

               // Creates and adds hashtable to the list for each paired
               // end file.
                pairedEndShortReadHT.add(new Hashtable());
                groupedPairedEndFiles.add(groupPairedEndFile);
            }

            //create file with the extension Group#.txt

            File groupFile = new File(System.getProperty("user.dir")
                    + File.separator + groupFileName);

            //creates and adds a hashtable to the list for each group file
            shortReadHT.add(new Hashtable());

            //Store the ids of the group in a list of string and create
            //a group object.
            ArrayList<String> tempIds = (ArrayList<String>) groups.get(i);
            Group tempGroup = new Group(groupFile.getName(), tempIds);

            //Group object is added to the tempGroup list.
            tempGroups.add(tempGroup);

            //Created File is added to the list of files (groupedFiles)
            groupedFiles.add(groupFile);

            count++;
        }

    }

    /**
     * This method grabs the identification of the current line of input
     * to be processed, and it looks through all the group files available.
     * When it finds the file the identification belongs in, it appends
     * the identification to the end of the file.
     * NOTE: This method works only if the user selects the identification
     * region from the first input file.
     * @param idLeft The left index of the identification region.
     * @param idRight The right index of the identification region.
     */
    public void doGrouping(int idLeft, int idRight)
    {
        // Current inputLine's id
        String id = inputLine.getShortRead().
                substring(idLeft, idRight);

        for (int i = 0; i < tempGroups.size(); i++)
        {
            //Find the group that contains the same id as the specified id
            for (int j = 0; j < tempGroups.get(i).getSizeOfList(); j++)
            {
                //comparing string in the list to the id
                //if found, get the groupName and open the corresponding file.
                //Write the shortRead to the file.

                //tempGroups.get(i).printIds();

                // User specified id (may contain wildcard)
                String tempId = tempGroups.get(i).getIdList().get(j);

                tempId = tempId.replace('*', '.');

                if (id.matches("^" + tempId + "$")) {
                    File tempFile = groupedFiles.get(i);
                    try {
                        groupWriter = new CSVWriter(
                                new FileWriter(tempFile, true), ':',
                                CSVWriter.NO_QUOTE_CHARACTER,
                                CSVWriter.DEFAULT_LINE_END);
                    } catch (Exception ex) {
                        Logger.getLogger(PreProcessingHandler.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }

                    //Get the shortread and store it in a array. Then write to
                    //file and flush the file.
                    String[] groupArray = {inputLine.toString()};
                    groupWriter.writeNext(groupArray);
                    try
                    {
                        groupWriter.flush();
                    } catch (IOException ex)
                    {
                        Logger.getLogger(PreProcessingHandler.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }

                    if (isPairedEnd)
                    {
                        File tempPairedEndFile = groupedPairedEndFiles.get(i);
                        try
                        {
                            this.groupWriter =
                                    new CSVWriter(
                                    new FileWriter(tempPairedEndFile, true),
                                    ':', CSVWriter.NO_QUOTE_CHARACTER,
                                    CSVWriter.DEFAULT_LINE_END);
                        } catch (IOException ex) {
                            Logger.getLogger(
                                    PreProcessingHandler.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }

                        // Get the shortread and store it in a array. Then
                        // write to file and flush the file.
                        String[] groupPairedEndArray =
                                        {inputPairedEndLine.toString()};

                        groupWriter.writeNext(groupPairedEndArray);
                        try
                        {
                            groupWriter.flush();
                        } 
                        catch (IOException ex)
                        {
                            Logger.getLogger(PreProcessingHandler.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                        updateShortReadHashTable(i, inputLine.getShortRead(),
                                inputPairedEndLine.getShortRead());
                    }
                    if (!isPairedEnd)
                    {
                        updateShortReadHashTable(i,
                                inputLine.getShortRead(),"");
                    }
                    return;
                }
            }
        }
        return;
    }

    /**
     * Go through every ShortRead and create a tag file based on it.
     * The tag file will contain the id and count.
     */
    public final void doTagFile()
    {
        for (int j = 0; j < shortReadHT.size(); j++)
        {
            List<ShortRead> shortRList = new Vector<ShortRead>();
            for (Enumeration ee = shortReadHT.get(j).keys();
                    ee.hasMoreElements();)
            {
                shortRList.add(
                        (ShortRead) shortReadHT.get(j).get(ee.nextElement()));
            }
            ShortReadScoreComparator src = new ShortReadScoreComparator();
            Collections.sort(shortRList, src);

            for (j = 0; j < groupedFiles.size(); j++)
            {
                String filename = groupedFiles.get(j).getName();

                try
                {
                    FileWriter f0 =
                            new FileWriter(System.getProperty("user.dir")
                        + File.separator + filename + ".tag", false);

                    for (int i = 0; i < shortRList.size(); i++) {
                        //System.out.print(shortRList.get(i).getCount() + "\t\t"
                               // + shortRList.get(i).getKey() + "\n");

                        f0.write(shortRList.get(i).getCount() + "\t\t"
                                + shortRList.get(i).getKey() + "\n");
                    }
                    f0.close();
                }
                catch (IOException ioe)
                {
                }
            }
        }
    }

    public final void doTagFilePairedEnd()
    {
        for (int j = 0; j < pairedEndShortReadHT.size(); j++)
        {
            List<ShortRead> shortRList = new Vector<ShortRead>();
            for (Enumeration ee = pairedEndShortReadHT.get(j).keys();
                    ee.hasMoreElements();)
            {
                shortRList.add(
                    (ShortRead) pairedEndShortReadHT.
                        get(j).get(ee.nextElement()));
            }
            ShortReadScoreComparator src = new ShortReadScoreComparator();
            Collections.sort(shortRList, src);

            String filename = groupedPairedEndFiles.get(j).getName();

            try
            {
                FileWriter f0 = new FileWriter(System.getProperty("user.dir")
                    + File.separator + filename + ".tag", false);

                for (int i = 0; i < shortRList.size(); i++) 
                {
                    f0.write(shortRList.get(i).getCount() + "\t\t"
                            + shortRList.get(i).getKey() + "\n");
                }
                f0.close();
            }
            catch (IOException ioe)
            {
            }
        }
    }

    public void updateShortReadHashTable(int index, String key1, String key2) {

        ShortRead tempSR = new ShortRead(key1);

        if (shortReadHT.get(index).get(key1) == null) {
            shortReadHT.get(index).put(key1, tempSR);
            if (isPairedEnd) {
                ShortRead tempSR2 = new ShortRead(key2);
                pairedEndShortReadHT.get(index).put(key1, tempSR2);
            }
        } else {
            ((ShortRead) shortReadHT.get(index).get(key1)).increaseCount();

            if (isPairedEnd)
            {
                ((ShortRead) pairedEndShortReadHT.get(index).get(key1))
                        .increaseCount();
            }
        }
    }

    /**
     * The method returns a list of short reads.
     * @return shortReadList is a list of short reads
     */
    public List<ShortRead> getShortReadList()
    {
        return shortReadList;
    }

    /**
     * The method allows to set the parameter inputLine.
     * @param inputLine
     */
    public final void setInputLine(InputLine inputLine)
    {
        this.inputLine = inputLine;
    }

    public final void setInputPairedEndLine(InputLine line)
    {
        this.inputPairedEndLine = line;
    }

    public InputLine getInputLine()
    {
        return inputLine;
    }

    public InputLine getInputPairedEndLine()
    {
        return inputPairedEndLine;
    }

    /**
     * The method returns list of Group
     * @return
     */
    public final List<Group> getGroupList()
    {
        return tempGroups;
    }

    public final File getQualityFile()
    {
        return qualityFile;

    }

    public final void setQualityFile(File qFile)
    {
        qualityFile = qFile;
    }

    public final Hashtable getHashTable()
    {
        return shortReadsHashTable;
    }

    public int getCountWrites()
    {
        return countWrites;
    }

    public boolean getIsPairedEnd()
    {
        return isPairedEnd;
    }

    public void setIsPairedEnd(boolean b)
    {
        this.isPairedEnd = b;
    }

    public CSVWriter getQualityCSVWriter()
    {
        return qualityWriter;
    }
    public CSVWriter getQualityPairedEndCSVWriter()
    {
        return qualityWriterPairedEnd;
    }

    /**
     * This method checks the list of groups for conflicting identifications.
     * The method only check conflicts for one wildcard in the identification.
     * @return true if there is a conflict; false otherwise.
     */
    public boolean checkGroupsForConflicts()
    {
        List<String> listOfIds = new ArrayList<String>();
        String one = null, two = null;

        // Put every id into a list
        for (int i = 0; i < tempGroups.size(); i++)
        {
            for (int j = 0; j < tempGroups.get(i).getSizeOfList(); j++)
            {
                listOfIds.add(tempGroups.get(i).getIdList().get(j));
            }
        }

        // Do pattern matching
        for (int i = 0; i < listOfIds.size(); i++)
        {
            String currentId = listOfIds.get(i);
            for (int j = i + 1; j < listOfIds.size(); j++)
            {
                String idToCompare = listOfIds.get(j);

                one = currentId;
                two = idToCompare;

                if (currentId.contains("*"))
                {
                    int star = currentId.indexOf("*");
                    char replaceChar = two.charAt(star);
                    one = currentId.replace('*', replaceChar);
                }

                if (idToCompare.contains("*"))
                {
                    int star = idToCompare.indexOf("*");
                    char replaceChar = one.charAt(star);
                    two = idToCompare.replace('*', replaceChar);
                }

                // Compare one and two
                if (one.replace('*', '.').matches(two.replace('*', '.')))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public final List<Hashtable> getShortReadHT()
    {
        return shortReadHT;
    }

    public final List<Hashtable> getPairedEndShortReadHT()
    {
        return pairedEndShortReadHT;
    }

    public View getView()
    {
        return view;
    }

    public void setView(View view)
    {
        this.view = view;
    }
}

