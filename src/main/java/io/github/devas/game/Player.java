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

    void addMove(Move move);

    List<Move> getMoves();

    void resetMoves();

    boolean hasMoved(Move move);

}
