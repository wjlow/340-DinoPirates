/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 * The class is responisible for storing a list of IDs
 * entered by the use for a group. It also stores the
 * name of the group which will be the same as the file name.
 * @author jpatel
 */
public class Group
{
    private String groupName;
    private ArrayList<String> identifications = new ArrayList<String>();

    public Group(String groupName, ArrayList<String> ids)
    {
        this.groupName = groupName;
        this.identifications = ids;
    }

    /**
     * The method returns name of the group.
     * @return groupName
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     * The method returns a list of IDs.
     * @return identifications (list)
     */
    public ArrayList<String> getIdList()
    {
        return identifications;
    }

    /**
     * The method sets the groupName.
     * @param groupName
     */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * The method sets the id list.
     * @param list
     */
    public void setIdList (ArrayList<String> list)
    {
        identifications = list;
    }

    /**
     * The method returns the size of list of identifications.
     * @return size of list of ids
     */
    public int getSizeOfList()
    {
        return identifications.size();
    }

    public void printIds()
    {
        for(int i=0; i<identifications.size(); i++)
        {
            System.out.println(identifications.get(i));
        }
    }
}
