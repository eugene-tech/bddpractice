package helpers;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DriverProvider {

    private static final Map<String, WebDriver> DRIVERS = new HashMap<>();

    public static WebDriver get() {
        String callerName = getCaller();
        WebDriver driver;
        if (!DRIVERS.containsKey(callerName)) {
            DRIVERS.put(callerName, driver = SeleniumUtils.setUpDriver());
            return driver;
        } else {
            return DRIVERS.get(callerName);
        }
    }

    private static String getCaller() {
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .filter(stack -> stack.getClassName().endsWith("Test"))
                .findFirst().orElseThrow(IllegalStateException::new)
                .getMethodName();
    }
}
