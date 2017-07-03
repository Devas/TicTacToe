package io.github.devas.game;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
public class BoardMoveTests {

    public void testTwoBoardMovesAreEqual() {
        BoardMove boardMove1 = new BoardMove(new Position2D(1, 2));
        BoardMove boardMove2 = new BoardMove(new Position2D(1, 2));
        assertEquals(boardMove1, boardMove2);
    }

    public void testTwoBoardMovesAreNotEqual() {
        BoardMove boardMove1 = new BoardMove(new Position2D(1, 2));
        BoardMove boardMove2 = new BoardMove(new Position2D(3, 2));
        assertNotEquals(boardMove1, boardMove2);
    }

}
