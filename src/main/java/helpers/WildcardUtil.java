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

    private static String getRandomDouble() {
        return String.format("%.2f", MIN + RANDOM.nextDouble() * MAX);
    }
}
