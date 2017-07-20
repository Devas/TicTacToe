package io.github.devas.game;

/**
 * Represents net single move on board.
 * Additionally we can define here other activities connected with single move.
 */
class BoardMove implements Move {

    private Position2D position;

    BoardMove(Position2D position) {
        this.position = position;
    }

    Position2D getPosition() {
        return position;
    }

    void setPosition(Position2D position) {
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
