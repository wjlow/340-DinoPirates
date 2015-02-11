/* ShortReadScoreComparator.java
 *
 * The purpose of this class is to compare the count of the occurence of a short
 * read and to sort them descendingly. This will be particularly useful when we
 * need to generate tag files, which are also sorted descendingly.
 */

package Model;

import java.util.Comparator;

public class ShortReadScoreComparator implements Comparator<ShortRead>{
    public int compare(ShortRead one, ShortRead two)
    {
        return -(one.getCount().compareTo(two.getCount()));
    }
}