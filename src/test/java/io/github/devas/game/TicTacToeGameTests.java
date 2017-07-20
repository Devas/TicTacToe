package io.github.devas.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test
public class TicTacToeGameTests {

    private TicTacToeGame game;
    private HumanPlayer playerO;
    private HumanPlayer playerX;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() throws Exception {
        playerO = new HumanPlayer("playerO", "o");
        playerX = new HumanPlayer("playerX", "x");
        game = new TicTacToeGame();
        game.setPlayerA(playerO);
        game.setPlayerA(playerO);
        game.setBoardSizeX(3);
        game.setBoardSizeY(3);
        game.setMarksToWin(3);
    }

    public void testIsGameInitiallyNotFinished() {
        assertEquals(game.isFinished(), false);
    }

    public void testIsGameFinished() {
        game.setFinished(true);
        assertEquals(game.isFinished(), true);
    }

    public void testIsCurrentPlayerCorrect() {
        game.setInitialTurnPlayerA();
        softAssert.assertEquals(game.getCurrentPlayer(), playerO);
        game.setInitialTurnPlayerB();
        softAssert.assertEquals(game.getCurrentPlayer(), playerX);
        softAssert.assertAll();
    }

    public void testIsCurrentPlayerCorrectAfterSeveralTurns() {
        game.setInitialTurnPlayerA();
        softAssert.assertEquals(game.getCurrentPlayer(), playerO);
        game.nextTurn();
        softAssert.assertEquals(game.getCurrentPlayer(), playerX);
        game.nextTurn();
        softAssert.assertEquals(game.getCurrentPlayer(), playerO);
        softAssert.assertAll();
    }

}
