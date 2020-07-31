package helpers;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WildcardUtil {

    private static final int MIN = 10;
    private static final int MAX = 400 - MIN;
    private static final List<String> WILDCARDS_RANDOM = Arrays.asList(Config.getString("wildcards.random").split(","));
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String processDouble(String value) {
        return WILDCARDS_RANDOM.contains(value) ? getRandomDouble() : value;
    }

    public static String processSymbols(String anySymbol) {
        return WILDCARDS_RANDOM.contains(anySymbol) ? getRandomSymbol() : anySymbol;
    }

    public static String processInteger(String intValue) {
        return WILDCARDS_RANDOM.contains(intValue) ? getRandomInteger() : intValue;
    }

    private static String getRandomSymbol() {
        char c = (char) (RANDOM.nextInt(26) + 'a');
        return String.valueOf(c);
    }

    private static String getRandomDouble() {
        return String.format("%.2f", MIN + RANDOM.nextDouble() * MAX);
    }

    private static String getRandomInteger() {
        return String.format("%d", RANDOM.nextInt(MAX) + MIN);
    }
}
