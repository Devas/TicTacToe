package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class ManagersTests {

    public void testLoadingLocalization() {
        LocalizationManager localizationManager = new LocalizationManager("ENG");
        localizationManager.loadLocalization();
        assertEquals(localizationManager.get("turn"), "\n*** Turn ");
    }

    public void testLoadingConfigurationAndGettingPlayerName() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        assertEquals(configurationManager.get("playera"), "John");
    }

}
