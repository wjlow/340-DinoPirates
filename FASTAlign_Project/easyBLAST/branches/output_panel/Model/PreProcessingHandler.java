/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Model.opencsv.CSVReader;
import Model.opencsv.CSVWriter;
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

/**
 * This class is responsible for communicating with other classes
 * that are involved in handling the PreProcessing Flow.
 *
 * @inputLine One line of input file.
 * @csvReader Used to read a CSV file.
 * @csvWriter Used to write to a CSV file.
 * @author wjlow and jpatel
 */
import java.util.Collections;
public class PreProcessingHandler {

    private InputLine inputLine = null;
    //private CSVReader csvReader;
    private List<ShortRead> shortReadList = new Vector<ShortRead>();
    private List<File> groupedFiles = new ArrayList<File> ();
    private List<Group> tempGroups = new ArrayList<Group>();
    private CSVWriter qualityWriter = null;
    private CSVWriter groupWriter = null;
    private Hashtable shortReadsHashTable = new Hashtable();
    private File qualityFile = new File("Quality.txt");


    public PreProcessingHandler()
    {
        //this.csvReader = csvReader;

        try
        {
            this.qualityWriter = new CSVWriter(new FileWriter
                    (qualityFile, true), ':', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

    }

    /**
     * This method appends an input line to the Quality.txt file. Input line is
     * only appended to the file, if the the line's quality thresholds is above
     * the set thresholds.
     * @param qSum
     * @param qASCII
     * @throws java.io.IOException
     */
    public void doQualityProcessing(int qSum, int qASCII) throws IOException
    {
        if ((inputLine.getQualityMin() >= qASCII) &&
                   (inputLine.getQualitySum() >= qSum))
        {
              qualityWriter.writeNext(inputLine.printInputLine());
        }
        qualityWriter.flush();
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

    public void makeGroups(String groups[][])
    {
        int count = 1;
        for(int i=0; i<groups.length; i++)
        {
            //create file with the extension Group#.txt
            File groupFile = new File("P:\\My Documents\\Group" + count + ".txt");
            //File groupFile = new File("D:\\Group" + count + ".txt");
            try
            {
                //System.out.println("In the grouping creating files");
                boolean check = groupFile.createNewFile();
                if (check)
                {
                     System.out.println("Group " + count + " created");
                }
                else
                {
                    System.out.println("Group " + count + " NOT created");

                }
            }
           catch (IOException ex)
            {
                Logger.getLogger(PreProcessingHandler.class.getName()).
                        log(Level.SEVERE, null, ex);
                System.out.println("In the grouping creating exception");
            }
            //Store the ids of the group in a list of string and create
            //a group object.
            ArrayList<String> tempIds = new ArrayList<String>();
            for(int j=0; j<groups[i].length; j++)
            {
                tempIds.add(groups[i][j]);
            }
            Group tempG = new Group(groupFile.getName(), tempIds);
            //Group object is added to the tempGroup list.
            tempGroups.add(tempG);
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
     * @param idLeft The left index of the identification region.
     * @param idRight The right index of the identification region.
     * @param groups A list of Strings each containing the name of a group.
     */

    public void doGrouping(int idLeft, int idRight)
    {
        String id = inputLine.getShortRead().
                substring(idLeft, idRight);

        for(int i=0; i<tempGroups.size(); i++)
        {
            //Find the group that contains the same id as the specified id
            for(int j=0; j<tempGroups.get(i).getSizeOfList(); j++)
            {
                //comparing string in the list to the id
                //if found, get the groupName and open the corresponding file.
                //Write the shortRead to the file.

                tempGroups.get(i).printIds();
                if(tempGroups.get(i).getIdList().get(j).
                        compareToIgnoreCase(id) == 0)
                {
                    File tempFile = groupedFiles.get(i);
                    try
                    {
                        this.groupWriter =
                                new CSVWriter(new
                                 FileWriter(tempFile, true),':',
                                 CSVWriter.NO_QUOTE_CHARACTER,
                                 CSVWriter.DEFAULT_LINE_END);
                    }

                    catch (IOException ex)
                    {
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
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(PreProcessingHandler.class.getName())
                                .log(Level.SEVERE, null, ex);
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
    public void doTagFile()
    {
        for (Enumeration ee = shortReadsHashTable.keys(); ee.hasMoreElements() ; )
        {
            shortReadList.add((ShortRead)shortReadsHashTable.get(ee.nextElement()));
        }

        ShortReadScoreComparator src = new ShortReadScoreComparator();
        Collections.sort(shortReadList, src);

        String filename = "tag";
        try {
            FileWriter f0 = new FileWriter(filename + ".tag", false);
            for (int i =0; i<shortReadList.size(); i++)
            {
                System.out.print(shortReadList.get(i).getCount() + "\t\t"
                        + shortReadList.get(i).getRegion() + "\n");
                f0.write(shortReadList.get(i).getCount() + "\t\t"
                        + shortReadList.get(i).getRegion() + "\n");
            }
            f0.close();
        }
        catch(IOException ioe)
        {

        }

    /*    for (Enumeration e = shortReadsHashTable.keys() ;
            e.hasMoreElements(); )
        {
            String key = (String)e.nextElement();

            // Get ShortRead using key
            ShortRead shortRead = (ShortRead)shortReadsHashTable.get(key);
            
            // Get ID and count
            String[] content
                    = {shortRead.getID(), shortRead.getCount().toString()};

            // Create a writer for a file with the filename as the
            // identification of the ShortRead
            try
            {
                writer = new CSVWriter(new FileWriter
                        ("Tag" + ".txt", true),
                        '\t', CSVWriter.NO_QUOTE_CHARACTER);
            }

            catch (IOException ex)
            {
                Logger.getLogger(PreProcessingHandler.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

            // Write the id and count into the file
            writer.writeNext(content);

            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(PreProcessingHandler.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }*/
    }

    public void updateShortReadHashTable(String id, String region)
    {
        String key = id + region;
        ShortRead tempSR = new ShortRead(id, region);

        if (shortReadsHashTable.get(key) == null)
        {
            shortReadsHashTable.put(key, tempSR);
        }
        else
        {
            ((ShortRead)shortReadsHashTable.get(key)).increaseCount();
        }
    }

    /**
     * The method returns a list of short reads.
     * @return shortReadList is a list of short reads
     */
    public List <ShortRead> getShortReadList()
    {
        return shortReadList;
    }

    /**
     * The method allows to set the parameter inputLine.
     * @param inputLine
     */
    public void setInputLine(InputLine inputLine)
    {
        System.out.println("setting inputLine in inputline");
        this.inputLine = inputLine;
    }

    /**
     * The method returns list of Group
     * @return
     */
    public List<Group> getGroupList()
    {
        return tempGroups;
    }

    public File getQualityFile()
    {
        return qualityFile;
    }

    public Hashtable getHashTable()
    {
        return shortReadsHashTable;
    }
}
