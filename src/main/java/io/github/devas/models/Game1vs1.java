package io.github.devas.models;

import io.github.devas.api.Player;

public abstract class Game1vs1 extends AbstractGame {

    protected Player playerA;
    protected Player playerB;
    protected Player currentPlayer;

    public Game1vs1(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public abstract void start();

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setInitialTurnPlayerA() {
        currentPlayer = playerA;
    }

    public void setInitialTurnPlayerB() {
        currentPlayer = playerB;
    }

    public Player nextTurn() {
        if (currentPlayer.equals(playerA)) {
            currentPlayer = playerB;
        } else {
            currentPlayer = playerA;
        }
        return currentPlayer;
    }

}
