package io.github.devas.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

@Test
public class PlayerTests {

    private HumanPlayer playerO;
    private HumanPlayer playerX;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() throws Exception {
        playerO = new HumanPlayer("David", "o");
        playerX = new HumanPlayer("John", "x");
    }

    public void testDoesGameWinningCount() {
        softAssert.assertEquals(playerO.getGamesWon(), 0);
        playerO.incrementGamesWon();
        softAssert.assertEquals(playerO.getGamesWon(), 1);
        softAssert.assertAll();
    }

    public void testCompareTwoPlayersByName() {
        softAssert.assertEquals(playerO.compareTo(playerX), 1);
        softAssert.assertEquals(playerX.compareTo(playerO), -1);
        softAssert.assertEquals(playerO.compareTo(playerO), 0);
        softAssert.assertAll();
    }

    public void testAddingGettingResettingPlayersMoves() {
        softAssert.assertEquals(playerO.getMoves(), new ArrayList<Move>());
        playerO.addMove(new BoardMove(new Position2D(1, 1)));
        softAssert.assertEquals(playerO.getMoves(), new ArrayList<>().add(new Position2D(1, 1)));
        playerO.resetMoves();
        softAssert.assertEquals(playerO.getMoves(), new ArrayList<Move>());
        softAssert.assertAll();
    }

    public void testArePlayersResultsCorrectlyComputed() {
        softAssert.assertEquals(playerO.getResult().getScore(), 0);
        playerO.getResult().setScore(10);
        softAssert.assertEquals(playerO.getResult().getScore(), 10);
        playerO.getResult().increaseScore(15);
        softAssert.assertEquals(playerO.getResult().getScore(), 25);
        playerO.getResult().decreaseScore(20);
        softAssert.assertEquals(playerO.getResult().getScore(), 5);
        playerO.getResult().resetScore();
        softAssert.assertEquals(playerO.getResult().getScore(), 0);
        softAssert.assertAll();
    }

    public void testArePlayersCorrectlyComparedBasedOnResults() {
        softAssert.assertEquals(playerO.compareTo(playerX), 0);
        playerO.getResult().setScore(10);
        softAssert.assertEquals(playerO.compareTo(playerX), 1);
        playerX.getResult().setScore(10);
        softAssert.assertEquals(playerO.compareTo(playerX), 0);
        playerO.getResult().increaseScore(15);
        softAssert.assertEquals(playerO.compareTo(playerX), -1);
        softAssert.assertAll();
    }

}
