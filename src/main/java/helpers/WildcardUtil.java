package helpers;

import core.Config;
import services.BNMService;

import java.util.*;

public class WildcardUtil {

    private static final List<String> BNM_CHARCODES = new ArrayList<>(BNMService.getCurrencyRate(DataUtil.TODAY, Lang.EN).keySet());
    private static final List<String> WILDCARDS_RANDOM = Arrays.asList(Config.getString("wildcards.random").split(","));
    private static final Random RANDOM = new Random();
    private static final Set<String> GENERATED_CHARCODES = new HashSet<>();

    public static String processDouble(String value) {
        return WILDCARDS_RANDOM.contains(value)
                ? String.format("%.2f", 10 + 390 * RANDOM.nextDouble())
                : value;
    }

    public static String processCharCode(String charCode) {
        return WILDCARDS_RANDOM.contains(charCode) ? getRandomCode() : charCode;
    }

    private static String getRandomCode() {
        String code;
        do {
            code = BNM_CHARCODES.get(RANDOM.nextInt(BNM_CHARCODES.size()));
        } while (GENERATED_CHARCODES.contains(code));
        GENERATED_CHARCODES.add(code);
        return code;
    }
}
