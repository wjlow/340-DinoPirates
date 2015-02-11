package Model;

import java.util.Comparator;

public class ShortReadComparator implements Comparator<ShortRead>{
    public int compare(ShortRead one, ShortRead two) {
        int temp = one.getID().compareTo(two.getID());
        if (temp == 0)
            return -(one.getCount().compareTo(two.getCount()));
        else
            return temp;
    }
}
