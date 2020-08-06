package helpers;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DataUtil {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final LocalDate TODAY = LocalDate.now();
    public static final double EPSILON = 0.01;

    public static double parseDouble(String doubleValue) {
        return Double.parseDouble(doubleValue.trim().replaceAll(" ", "").replace(",", "."));
    }

    public static double parseDouble(WebElement input) {
        return parseDouble(Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException("Input element was null!"))
                .getAttribute("value"));
    }
}
