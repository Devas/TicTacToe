package io.github.devas.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalizationManager implements Manager {

    private final String resourcesPath = "src/main/resources/";
    private final String localizationName;
    private Properties localizationProperties = new Properties();

    public LocalizationManager(String localizationName) {
        this.localizationName = localizationName;
    }

    public void loadLocalization() {
        String localizationFileName = "localization" + localizationName.toUpperCase() + ".properties";
        try (FileInputStream fileInputStream = new FileInputStream(new File(resourcesPath + localizationFileName))) {
            localizationProperties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Couldn't load localization properties");
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return localizationProperties.get(key).toString();
    }

}
