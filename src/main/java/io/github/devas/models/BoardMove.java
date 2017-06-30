package io.github.devas.models;

import io.github.devas.api.Move;

/**
 * Represents a single move on board.
 * Additionally we can define here other activities connected with single move.
 */
public class BoardMove implements Move {

    private Position2D position;

    public BoardMove(Position2D position) {
        this.position = position;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "BoardMove{" +
                "position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardMove boardMove = (BoardMove) o;

        return position != null ? position.equals(boardMove.position) : boardMove.position == null;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }

}
