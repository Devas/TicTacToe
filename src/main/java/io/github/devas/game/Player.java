package io.github.devas.game;

import java.util.List;

interface Player {

    String getName();

    void setName(String name);

    String getNick();

    void setNick(String nick);

    int getGamesWon();

    void incrementGamesWon();

    Result getResult();

    default void addMove(Move move) {}

    default List<Move> getMoves() {
        return null;
    }

    default void resetMoves() {}

    default boolean hasMoved(Move move) {
        return false;
    }

}
