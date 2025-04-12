package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        loadConfiguration();
    }

    private static void loadConfiguration() {
        try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH, e);
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("baseURI");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "10000"));
    }
}