package io.github.devas.game;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class BoardTests {

    private SoftAssert softAssert = new SoftAssert();

    public void testCalculationOfBoardAreas() {
        Board board = new ConsoleBoard(3, 3);
        softAssert.assertEquals(board.getArea(), 9);
        board = new ConsoleBoard(2, 2);
        softAssert.assertEquals(board.getArea(), 4);
        board = new ConsoleBoard(3, 5);
        softAssert.assertEquals(board.getArea(), 15);
        board = new ConsoleBoard(5, 3);
        softAssert.assertEquals(board.getArea(), 15);
        softAssert.assertAll();
    }

    public void testIfBoardResetsItselfWithDefaultValue() {
        Board board = new ConsoleBoard(3, 3);
        board.reset();
        softAssert.assertEquals(board.getValueAt(1, 2), "*");
        softAssert.assertEquals(board.getValueAt(0, 0), "*");
        softAssert.assertEquals(board.getValueAt(2, 2), "*");
        softAssert.assertAll();
    }

    public void testIfWholeBoardIsFilledWithSpecifiedString() {
        Board board = new ConsoleBoard(3, 3);
        board.setAll("@");
        softAssert.assertEquals(board.getValueAt(2, 1), "@");
        softAssert.assertEquals(board.getValueAt(0, 0), "@");
        softAssert.assertEquals(board.getValueAt(1, 1), "@");
        softAssert.assertAll();
    }

    public void testBoardSetValueAndGetValueForPrimitives() {
        Board board = new ConsoleBoard(3, 3);
        board.setValueAt(0, 0, "X");
        softAssert.assertEquals(board.getValueAt(0, 1), "*");
        softAssert.assertEquals(board.getValueAt(1, 0), "*");
        softAssert.assertEquals(board.getValueAt(0, 0), "X");
        softAssert.assertAll();
    }

    public void testBoardSetValueAndGetValueForPosition() {
        Board board = new ConsoleBoard(3, 3);
        board.setValueAt(new Position2D(0, 0), "X");
        softAssert.assertEquals(board.getValueAt(new Position2D(0, 1)), "*");
        softAssert.assertEquals(board.getValueAt(new Position2D(1, 0)), "*");
        softAssert.assertEquals(board.getValueAt(new Position2D(0, 0)), "X");
        softAssert.assertAll();
    }

    public void testFillingBoardWithAlphabet() {
        Board board = new ConsoleBoard(3, 3);
        board.setAllWithAlphabet();
        softAssert.assertEquals(board.getValueAt(0, 0), "a");
        softAssert.assertEquals(board.getValueAt(2, 2), "i");
        softAssert.assertAll();
    }

    public void testIsStringWithBoardRepresentationCorrect() {
        Board board = new ConsoleBoard(3, 3);
        softAssert.assertEquals(board.draw(), "* * * \n* * * \n* * * \n");
        board = new ConsoleBoard(4, 3);
        softAssert.assertEquals(board.draw(), "* * * \n* * * \n* * * \n* * * \n");
        board = new ConsoleBoard(3, 4);
        softAssert.assertEquals(board.draw(), "* * * * \n* * * * \n* * * * \n");
    }

    public void testIsStringWithDiagonalsCorrect() {
        ConsoleBoard board = new ConsoleBoard(3, 3);
        String diagonals = board.drawDiagonals();
        softAssert.assertTrue(diagonals.startsWith("*"));
        softAssert.assertTrue(diagonals.endsWith("* \n"));
    }

}
