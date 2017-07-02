package io.github.devas;

import io.github.devas.game.HumanPlayer;
import io.github.devas.game.TicTacToeGame;
import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import org.testng.annotations.Test;

@Test
public class TicTacToeGameTests {

    public void testGameFinishing() {
        ConfigurationManager configManager = new ConfigurationManager();
        LocalizationManager localizationManager = new LocalizationManager("ENG");
        localizationManager.loadLocalization();
        HumanPlayer playerO = new HumanPlayer("playerO", "o");
        HumanPlayer playerX = new HumanPlayer("playerX", "x");
        TicTacToeGame game = new TicTacToeGame(playerO, playerX, 3, 3, 3, configManager, localizationManager);
    }

}
