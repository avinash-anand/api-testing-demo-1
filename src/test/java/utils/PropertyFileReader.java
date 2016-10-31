package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertyFileReader.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
