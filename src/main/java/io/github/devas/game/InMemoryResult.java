package io.github.devas.game;

/**
 * Represents results stored as objects
 */
class InMemoryResult implements Result, Comparable<InMemoryResult> {

    private int score;

    InMemoryResult() {
        this.score = 0;
    }

    InMemoryResult(int score) {
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
