package io.github.devas.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalizationManager implements Manager {

    private final String resourcesPath;
    private final String localizationName;
    private final String localizationFileName;
    private Properties localizationProperties;

    public LocalizationManager(String localizationName) {
        this.resourcesPath = "src/main/resources/";
        this.localizationName = localizationName.toUpperCase();
        this.localizationFileName = "localization" + this.localizationName + ".properties";
        this.localizationProperties = new Properties();
    }

    public void loadLocalization() {
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
