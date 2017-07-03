package io.github.devas.game;

import java.util.ArrayList;
import java.util.List;

class HumanPlayer implements Player, Comparable<HumanPlayer> {

    private String name;
    private String nick;
    private int gamesWon;

    private Result result = new InMemoryResult();
    private List<Move> moves = new ArrayList<>();

    HumanPlayer(String name, String nick) {
        this.name = name;
        this.nick = nick;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNick() {
        return nick;
    }

    @Override
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public int getGamesWon() {
        return gamesWon;
    }

    @Override
    public void incrementGamesWon() {
        gamesWon++;
    }

    @Override
    public Result getResult() {
        return result;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    @Override
    public List<Move> getMoves() {
        return moves;
    }

    @Override
    public void resetMoves() {
        moves.clear();
    }

    @Override
    public boolean hasMoved(Move move) {
        for (Move m : moves) {
            if (m.equals(move)) return true;
        }
        return false;
    }

    @Override
    public int compareTo(HumanPlayer o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name + ": " + result.getScore();
    }

}
