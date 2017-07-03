package io.github.devas.game;

abstract class AbstractGame implements Game {

    private boolean finished = false;

    boolean isFinished() {
        return finished;
    }

    void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public abstract void startGame();

}
