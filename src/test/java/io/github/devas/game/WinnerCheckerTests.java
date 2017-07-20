package io.github.devas.game;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class WinnerCheckerTests {

    private SoftAssert softAssert = new SoftAssert();

    public void testDoesTurnStatusTellIfGameIsWonOrNotWon() {
        Board board = new ConsoleBoard(3, 3);
        WinnerChecker winnerChecker = new WinnerChecker(board, 3);
        String x = "x";
        board.setValueAt(0, 0, x);
        softAssert.assertEquals(winnerChecker.checkAll(x), TurnStatus.NONE_WON);
        board.setValueAt(0, 1, x);
        softAssert.assertEquals(winnerChecker.checkAll(x), TurnStatus.NONE_WON);
        board.setValueAt(0, 2, x);
        softAssert.assertEquals(winnerChecker.checkAll(x), TurnStatus.WON);
        softAssert.assertAll();
    }

}
