package io.github.devas.game;

interface Result {

    int getScore();

    void setScore(int score);

    void increaseScore(int value);

    void decreaseScore(int value);

    void resetScore();

}
