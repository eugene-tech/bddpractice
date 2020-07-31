package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static helpers.LogHelper.logged;

public class Config {

    private static final Logger log = LogManager.getLogger(Config.class);
    private static final Properties props = new Properties();
    private static final String CONFIG_PATH = "config/config.properties";

    static {
        log.info("Loading properties located at <" + CONFIG_PATH + ">");
        try (InputStream inputStream =
                     Config.class.getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            props.load(inputStream);
        } catch (IOException ignored) {}
    }

    public static String getString(String key) {
        log.info("Searching property <" + key + ">");
        String property = Optional.ofNullable(props.getProperty(key))
                .orElseThrow(() -> new IllegalArgumentException("Property <" + key + "> not found!"));
        return logged(log, property, "Found:");
    }
}
