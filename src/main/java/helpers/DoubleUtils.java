package helpers;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import sun.net.dns.ResolverConfiguration;

import javax.swing.text.html.Option;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DoubleUtils {

    private static void checkArrayLengthEquals( String[] regex, String[] replacement){
        Assertions.assertThat(regex.length).isEqualTo(replacement.length);
    }

    public static double getDoubleWithReplacement(String doubleValue){
        return Double.parseDouble(doubleValue.replaceAll(",",".").replaceAll(" ",""));
    }

    public static String getDecimalWithFormat(double value,String pattern,RoundingMode mode){
        DecimalFormat df=new DecimalFormat(pattern);
        df.setRoundingMode(mode);
        df.setMinimumFractionDigits(2);
        return df.format(value);
    }


}
