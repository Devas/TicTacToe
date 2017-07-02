package io.github.devas.game;

public abstract class Board implements World2D {

    final int sixeX;
    final int sixeY;
    private final int area;
    final String[][] board;
    private final String FILL_STRING = "*";

    Board(int sixeX, int sixeY) {
        this.sixeX = sixeX;
        this.sixeY = sixeY;
        this.area = sixeX * sixeY;
        this.board = new String[sixeX][sixeY];
        setAll(FILL_STRING);
    }

    @Override
    public abstract void draw();

    int getSixeX() {
        return sixeX;
    }

    int getSixeY() {
        return sixeY;
    }

    public int getArea() {
        return area;
    }

    String[][] getBoard() {
        return board;
    }

    public String getValueAt(int x, int y) {
        return board[x][y];
    }

    String getValueAt(Position2D position) {
        return board[position.getX()][position.getY()];
    }

    public void setValueAt(int x, int y, String value) {
        board[x][y] = value;
    }

    void setValueAt(Position2D position, String value) {
        board[position.getX()][position.getY()] = value;
    }

    public void setAll(String value) {
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                board[x][y] = value;
            }
        }
    }

    public void reset() {
        setAll(FILL_STRING);
    }

    /**
     * Only for tests
     */
    public void setAllWithAlphabet() {
        char ch = 'a';
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++, ch++) {
                board[x][y] = String.valueOf(ch);
            }
        }
    }

}
