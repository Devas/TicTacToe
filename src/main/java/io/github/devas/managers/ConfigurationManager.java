package io.github.devas.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class ConfigurationManager implements Manager {

    private final String configFileName = "src/main/resources/config.properties";
    private Properties configProperties = new Properties();
    private PrintStream printStream = System.out;

    public ConfigurationManager() {
        loadConfigProperties();
        initOutputMessagesTarget();
    }

    public String get(String key) {
        return configProperties.get(key).toString();
    }

    public void print(String message) {
        printStream.print(message);
    }

    public void println(String message) {
        printStream.println(message);
    }

    public void println() {
        printStream.println();
    }

    private void loadConfigProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(configFileName))) {
            configProperties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Couldn't load configuration properties");
            e.printStackTrace();
        }
    }

    /**
     * Add new targets here and also add options to .properties file
     */
    private void initOutputMessagesTarget() {
        switch (get("target")) {
            case "out":
                printStream = System.out;
                break;
            case "err":
                printStream = System.err;
                break;
            default:
                printStream = System.out;
        }
    }

}
