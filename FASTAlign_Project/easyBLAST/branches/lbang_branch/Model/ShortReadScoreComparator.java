package Model;

import java.util.Comparator;

public class ShortReadScoreComparator implements Comparator<ShortRead>{
    public int compare(ShortRead one, ShortRead two) {
        return -(one.getCount().compareTo(two.getCount()));
    }
}