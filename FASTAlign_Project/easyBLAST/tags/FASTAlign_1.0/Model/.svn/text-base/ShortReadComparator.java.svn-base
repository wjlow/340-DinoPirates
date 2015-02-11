/* ShortReadComparator.java
 *
 * The purpose of this class is to compare the ID of a short-read and to sort
 * them so that short-rads with the same ID are grouped together. This will be
 * particularly useful when we need to generate FASTA files because it will
 * allow us to 'group' and focus on each ID separately.
 */

package Model;

import java.util.Comparator;

public class ShortReadComparator implements Comparator<ShortRead>
{
    public int compare(ShortRead one, ShortRead two)
    {
        int temp = one.getID().compareTo(two.getID());
        if (temp == 0)
        {
            return -(one.getCount().compareTo(two.getCount()));
        }
        else
        {
            return temp;
        }
    }
}
