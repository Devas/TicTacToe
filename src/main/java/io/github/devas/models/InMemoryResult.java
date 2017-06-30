package io.github.devas.models;

import io.github.devas.api.Result;

/**
 * Represents results stored as objects
 */
public class InMemoryResult implements Result, Comparable<InMemoryResult> {

    private int score;

    public InMemoryResult() {
        this.score = 0;
    }

    public InMemoryResult(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void increaseScore(int value) {
        score += value;
    }

    @Override
    public void decreaseScore(int value) {
        score -= value;
    }

    @Override
    public void resetScore() {
        score = 0;
    }

    @Override
    public String toString() {
        return "InMemoryResult{" +
                "score=" + score +
                '}';
    }

    @Override
    public int compareTo(InMemoryResult o) {
        if (score > o.score)
            return 1;
        else if (score < o.score)
            return -1;
        else
            return 0;
    }

}
