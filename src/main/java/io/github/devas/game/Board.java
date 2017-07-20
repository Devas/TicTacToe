package io.github.devas.game;

abstract class Board implements World2D {

    final int sixeX;
    final int sixeY;
    private final int area;
    final String[][] board;
    private final String FILL_UP_STRING = "*";

    Board(int sixeX, int sixeY) {
        this.sixeX = sixeX;
        this.sixeY = sixeY;
        this.area = sixeX * sixeY;
        this.board = new String[sixeX][sixeY];
        setAll(FILL_UP_STRING);
    }

    @Override
    public abstract String draw();

    int getSixeX() {
        return sixeX;
    }

    int getSixeY() {
        return sixeY;
    }

    int getArea() {
        return area;
    }

    String[][] getBoard() {
        return board;
    }

    String getValueAt(int x, int y) {
        return board[x][y];
    }

    String getValueAt(Position2D position) {
        return board[position.getX()][position.getY()];
    }

    void setValueAt(int x, int y, String value) {
        board[x][y] = value;
    }

    void setValueAt(Position2D position, String value) {
        board[position.getX()][position.getY()] = value;
    }

    /**
     * Fills up the board with specified String.
     *
     * @param value String used to fill up the board
     */
    void setAll(String value) {
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                board[x][y] = value;
            }
        }
    }

    /**
     * Fills up the board with default filling up String.
     */
    void reset() {
        setAll(FILL_UP_STRING);
    }

    /**
     * Returns whether board contains any String different from the default filling up String.
     */
    boolean isFilledUpWithDefaultString() {
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                if (!board[x][y].equals(FILL_UP_STRING)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Only for tests
     */
    void setAllWithAlphabet() {
        char ch = 'a';
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++, ch++) {
                board[x][y] = String.valueOf(ch);
            }
        }
    }

}
