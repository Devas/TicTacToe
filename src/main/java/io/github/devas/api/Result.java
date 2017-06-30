package io.github.devas.api;

public interface Result {

    int getScore();

    void setScore(int score);

    void increaseScore(int value);

    void decreaseScore(int value);

    void resetScore();

}
