package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat;

    public static String changeDateFormat(String date, String inputFormat, String outputFormat) throws ParseException {
        simpleDateFormat = new SimpleDateFormat(inputFormat);
        Date datE = simpleDateFormat.parse(date);
        simpleDateFormat.applyPattern(outputFormat);
        return simpleDateFormat.format(datE);
    }

    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private static Date tomorrow() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        return cal.getTime();
    }

    public static String getYesterdayDateString(String format) {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(yesterday());
    }

    public static String getTomorrowDateString(String format) {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(yesterday());
    }

    public static String getDateString(int amount, String format) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(cal.getTime());
    }

    public static String getDateAfterDateString(int amount, String date, String inputFormat, String outputFormat) throws ParseException {
        simpleDateFormat = new SimpleDateFormat(inputFormat);
        Date datE = simpleDateFormat.parse(date);

        Calendar cal = dateToCalendar(datE);
        cal.add(Calendar.DATE, amount);
        simpleDateFormat.applyPattern(outputFormat);

        return simpleDateFormat.format(cal.getTime());
    }

    private static Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
