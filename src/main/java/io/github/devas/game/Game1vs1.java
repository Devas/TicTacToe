package io.github.devas.game;

abstract class Game1vs1 extends AbstractGame {

    private Player playerA;
    private Player playerB;
    private Player currentPlayer;

    Game1vs1(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public abstract void startGame();

    Player getPlayerA() {
        return playerA;
    }

    void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    Player getPlayerB() {
        return playerB;
    }

    void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    void setInitialTurnPlayerA() {
        currentPlayer = playerA;
    }

    void setInitialTurnPlayerB() {
        currentPlayer = playerB;
    }

    Player nextTurn() {
        if (currentPlayer.equals(playerA)) {
            currentPlayer = playerB;
        } else {
            currentPlayer = playerA;
        }
        return currentPlayer;
    }

}
