package io.github.devas;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import io.github.devas.models.ConsoleBoard;
import org.testng.annotations.Test;

@Test
public class ConsoleBoardTests {

    ConfigurationManager configurationManager;
    LocalizationManager localizationManager;

    public ConsoleBoardTests() {
        configurationManager = new ConfigurationManager();
        localizationManager = new LocalizationManager();
        localizationManager.loadLocalization();
    }

    public void testBoardDraw() {
        ConsoleBoard board = new ConsoleBoard(3, 3, configurationManager);
//        assertEquals(board.draw(), );

    }

    public void drawDiagonals() {

    }

}
