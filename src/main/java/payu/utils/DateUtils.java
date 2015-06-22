package payu.utils;

import java.util.Date;

public class DateUtils {

    public static boolean isInBetween(Date d, Date start, Date end) {
        boolean greater = d.after(start) || d.equals(start);
        boolean lesser = d.before(end) || d.equals(end);
        return greater && lesser;
    }

}
