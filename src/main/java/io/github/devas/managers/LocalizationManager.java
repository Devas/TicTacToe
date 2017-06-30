package io.github.devas.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class LocalizationManager {

    private final String resourcesPath = "src/main/resources/";
    private String localizationName = "ENG";
    private Properties localizationProperties = new Properties();

    /**
     * Add new languages by adding new cases and .properties files
     */
    public void askForLocale() {
        System.out.println("Locale settings");
        System.out.print("'E' English managers | 'P' Polish managers | Press key: ");
        Scanner s = new Scanner(System.in);
        String lang = s.next().toUpperCase();
        switch (lang) {
            case "E":
                localizationName = "ENG";
                break;
            case "P":
                localizationName = "PL";
                break;
            default:
                localizationName = "ENG";
        }
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
