package io.github.devas;

import io.github.devas.game.Board;
import io.github.devas.game.ConsoleBoard;
import io.github.devas.managers.ConfigurationManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class BoardTests {

    public void testBoardAreas() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        Board board = new ConsoleBoard(3, 3, configurationManager);
        assertEquals(board.getArea(), 9);
        board = new ConsoleBoard(2, 2, configurationManager);
        assertEquals(board.getArea(), 4);
        board = new ConsoleBoard(3, 5, configurationManager);
        assertEquals(board.getArea(), 15);
        board = new ConsoleBoard(5, 3, configurationManager);
        assertEquals(board.getArea(), 15);
    }

    public void testBoardReset() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        Board board = new ConsoleBoard(3, 3, configurationManager);
        board.reset();
        assertEquals(board.getValueAt(1, 2), "*");
        assertEquals(board.getValueAt(0, 0), "*");
        assertEquals(board.getValueAt(2, 2), "*");
    }

    public void testBoardSetAll() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        Board board = new ConsoleBoard(3, 3, configurationManager);
        board.setAll("@");
        assertEquals(board.getValueAt(2, 1), "@");
        assertEquals(board.getValueAt(0, 0), "@");
        assertEquals(board.getValueAt(1, 1), "@");
    }

    public void testBoardSetValueAndGetValue() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        Board board = new ConsoleBoard(3, 3, configurationManager);
        board.setValueAt(0, 0, "X");
        assertEquals(board.getValueAt(0, 1), "*");
        assertEquals(board.getValueAt(1, 0), "*");
        assertEquals(board.getValueAt(0, 0), "X");
    }

    public void testFillingBoardWithAlphabet() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        Board board = new ConsoleBoard(3, 3, configurationManager);
        board.setAllWithAlphabet();
        assertEquals(board.getValueAt(0, 0), "a");
        assertEquals(board.getValueAt(2, 2), "i");
    }

}
