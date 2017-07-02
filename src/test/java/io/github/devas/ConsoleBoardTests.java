package io.github.devas;

import io.github.devas.game.ConsoleBoard;
import io.github.devas.managers.ConfigurationManager;
import org.testng.annotations.Test;

@Test
public class ConsoleBoardTests {

    public void testBoardDraw() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        ConsoleBoard board = new ConsoleBoard(3, 3, configurationManager);
//        assertEquals(board.draw(), );

    }

    public void drawDiagonals() {

    }

}
