package main.java.dataUtils;

import java.util.Calendar;
import java.util.Date;

public class DataUtil {
    public static Date getDataMonthAndYear(Date aDate){
        final Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(aDate);
        myCalendar.set(Calendar.DAY_OF_MONTH, 0);
        myCalendar.set(Calendar.HOUR_OF_DAY, 0);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 0);
        myCalendar.set(Calendar.MILLISECOND, 0);
        return myCalendar.getTime();
    }
}
