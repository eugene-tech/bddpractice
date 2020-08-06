package core;

import helpers.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.BNMService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class Config {

    private static final Logger log = LogManager.getLogger(BNMService.class);
    private static final Properties props = new Properties();
    private static final String CONFIG_PATH = "config/config.properties";

    static {
        log.info("Loading properties located at <" + CONFIG_PATH + ">");
        try (InputStream inputStream = new FileInputStream(CONFIG_PATH)) {
            props.load(inputStream);
        } catch (IOException ignored) {}
    }

    public static String getString(String key) {
        log.info("Searching property <" + key + ">");
        String property = Optional.ofNullable(props.getProperty(key))
                .orElseThrow(() -> new IllegalArgumentException("Property <" + key + "> not found!"));
        return LogHelper.logged(log, property, "Found:");
    }
}
