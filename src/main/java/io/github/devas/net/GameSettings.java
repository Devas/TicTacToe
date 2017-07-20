package io.github.devas.net;

class GameSettings implements Settings {

    private int maxNumberOfPlayers;

    GameSettings(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    @Override
    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

}
