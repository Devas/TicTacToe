package io.github.devas;

import io.github.devas.game.Board;
import io.github.devas.game.ConsoleBoard;
import io.github.devas.game.TurnStatus;
import io.github.devas.game.WinnerChecker;
import io.github.devas.managers.ConfigurationManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class WinnerCheckerTests {

    public void testWinning() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        Board board = new ConsoleBoard(3, 3, configurationManager);
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
