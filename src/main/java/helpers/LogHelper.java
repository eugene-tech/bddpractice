package helpers;

import org.apache.logging.log4j.Logger;

public class LogHelper {

    public static <T> T logged(Logger log, T toLog, String description) {
        return logged(log, toLog, description, "");
    }

    public static <T> T logged(Logger log, T toLog, String description, String additionalDescription) {
        log.info(String.join(" ", description, toLog.toString(), additionalDescription));
        return toLog;
    }
}
