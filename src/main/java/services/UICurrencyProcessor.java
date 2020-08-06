package services;

import POMs.CursMD;
import helpers.DataUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UICurrencyProcessor {

    private static final Logger log = LogManager.getLogger(UICurrencyProcessor.class);
    private static final int TODAY_INDEX = 3;

    /**
     * @param date - today, yesterday and day before yesterday
     */
    public static Map<String, Currency> parseFromCurs(CursMD cursMD, LocalDate date) {
        int headerIndex = IntStream.range(1, 8)
                .filter(index -> {
                    try {
                        cursMD.officialRateTable.findElement(By.xpath(".//th[" + index + "][text()='" + date.format(DataUtil.DATE_FORMAT) + "']"));
                        return true;
                    } catch (Exception ignored) {
                        return false;
                    }
                }).findFirst()
                .orElse(TODAY_INDEX);
        if (TODAY_INDEX == headerIndex)
            Assert.assertEquals("Unexpected date", LocalDate.now(), date);
        return cursMD.officialRateTable.findElements(By.xpath("./tbody/tr")).stream()
                .map(tableRow -> new Currency(log, tableRow, headerIndex))
                .collect(Collectors.toMap(Currency::getCharCode, Function.identity()));
    }
}
