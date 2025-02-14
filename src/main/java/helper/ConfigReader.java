package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String getProperty(String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/configuration/config.properties")){
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Could Not Load the File " + e.getMessage());
        }

        return prop.getProperty(key);
    }
}
