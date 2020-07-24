package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat;

    public static String changeDateFormat(String date,String inputFormat,String outputFormat) throws ParseException {
        simpleDateFormat = new SimpleDateFormat(inputFormat);
        Date datE = simpleDateFormat.parse(date);
        simpleDateFormat.applyPattern(outputFormat);
        return simpleDateFormat.format(datE);
    }
}
