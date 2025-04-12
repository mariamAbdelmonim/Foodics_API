package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Initialize Properties object to hold the configuration data
    private static Properties properties = new Properties();

    static {
        try {
            // Load the configuration file from the specified path
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties.load(file); // Load the data from the properties file
        } catch (IOException e) {
            // Handle any errors during file loading
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file."); // Throw an exception if the file fails to load
        }
    }

    /**
     * Get the property value for a given key.
     *
     * @param key the key to look for in the properties file
     * @return the value associated with the provided key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key); // Return the property value for the provided key
    }
}
