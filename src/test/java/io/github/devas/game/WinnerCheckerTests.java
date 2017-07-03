package io.github.devas.game;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class WinnerCheckerTests {

    public void testDoesTurnStatusTellIfGameIsWonOrNotWon() {
        Board board = new ConsoleBoard(3, 3);
        WinnerChecker winnerChecker = new WinnerChecker(board, 3);
        String x = "x";
        board.setValueAt(0, 0, x);
        assertEquals(winnerChecker.checkAll(x), TurnStatus.NONE_WON);
        board.setValueAt(0, 1, x);
        assertEquals(winnerChecker.checkAll(x), TurnStatus.NONE_WON);
        board.setValueAt(0, 2, x);
        assertEquals(winnerChecker.checkAll(x), TurnStatus.WON);
    }

}
