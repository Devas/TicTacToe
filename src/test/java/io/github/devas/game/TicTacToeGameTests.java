package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test
public class TicTacToeGameTests {

    private ConfigurationManager configManager;
    private LocalizationManager localizationManager;
    private HumanPlayer playerO;
    private HumanPlayer playerX;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() throws Exception {
        configManager = new ConfigurationManager();
        localizationManager = new LocalizationManager("ENG");
        localizationManager.loadLocalization();
        playerO = new HumanPlayer("playerO", "o");
        playerX = new HumanPlayer("playerX", "x");
    }

    public void testIsGameInitiallyNotFinished() {
        TicTacToeGame game = new TicTacToeGame(playerO, playerX, 3, 3, 3, configManager, localizationManager);
        assertEquals(game.isFinished(), false);
    }

    public void testIsGameFinished() {
        TicTacToeGame game = new TicTacToeGame(playerO, playerX, 3, 3, 3, configManager, localizationManager);
        game.setFinished(true);
        assertEquals(game.isFinished(), true);
    }

    public void testIsCurrentPlayerCorrect() {
        TicTacToeGame game = new TicTacToeGame(playerO, playerX, 3, 3, 3, configManager, localizationManager);
        game.setInitialTurnPlayerA();
        softAssert.assertEquals(game.getCurrentPlayer(), playerO);
        game.setInitialTurnPlayerB();
        softAssert.assertEquals(game.getCurrentPlayer(), playerX);
    }

    public void testIsCurrentPlayerCorrectAfterSeveralTurns() {
        TicTacToeGame game = new TicTacToeGame(playerO, playerX, 3, 3, 3, configManager, localizationManager);
        game.setInitialTurnPlayerA();
        softAssert.assertEquals(game.getCurrentPlayer(), playerO);
        game.nextTurn();
        softAssert.assertEquals(game.getCurrentPlayer(), playerX);
        game.nextTurn();
        softAssert.assertEquals(game.getCurrentPlayer(), playerO);
    }

}
