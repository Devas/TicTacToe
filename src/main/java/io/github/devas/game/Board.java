package io.github.devas.game;

import java.util.ArrayList;
import java.util.List;

import static io.github.devas.game.TurnStatus.NONEWON;
import static io.github.devas.game.TurnStatus.WON;

abstract class Board implements World2D {

    final int sixeX;
    final int sixeY;
    private final int area;
    private final int marksToWin;
    final String[][] board;
    private final String FILL_STRING = "*";

    Board(int sixeX, int sixeY) {
        this.sixeX = sixeX;
        this.sixeY = sixeY;
        this.area = sixeX * sixeY;
        this.marksToWin = Math.min(sixeX, sixeY);
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

    private void setAll(String value) {
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                board[x][y] = value;
            }
        }
    }

    void reset() {
        setAll(FILL_STRING);
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

    /**
     * If row, column or diagonal is found this method returns status WON (a player won).
     * Otherwise returns status NONEWON (nobody has won yet)
     */
    TurnStatus checkAll(String value) {
        return winnerIn(value, "rows") || winnerIn(value, "cols") || winnerIn(value, "diag1") || winnerIn(value, "diag2") ? WON : NONEWON;
    }

    private boolean winnerIn(String value, String what) {
        try {
            switch (what) {
                case "rows":
                    return checkRows(value).equals(WON);
                case "cols":
                    return checkColumns(value).equals(WON);
                case "diag1":
                    return checkDiagonals(value).equals(WON);
                case "diag2":
                    return checkAntiDiagonals(value).equals(WON);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Array out of bounds");
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("Duplicates")
    private TurnStatus checkRows(String value) throws ArrayIndexOutOfBoundsException {
        List<Position2D> positions = new ArrayList<>();
        for (int y = 0; y < sixeY; y++) {
            positions.clear();
            int count = 0;
            for (int x = 0; x < sixeX; x++) {
                if (board[x][y].equals(value)) {
                    count++;
                    positions.add(new Position2D(x, y));
                }
                if (count == marksToWin) {
                    markPositionsToUpperCase(positions);
                    return WON;
                }
            }
        }
        return TurnStatus.NONEWON;
    }

    @SuppressWarnings("Duplicates")
    private TurnStatus checkColumns(String value) throws ArrayIndexOutOfBoundsException {
        List<Position2D> positions = new ArrayList<>();
        for (int x = 0; x < sixeX; x++) {
            positions.clear();
            int count = 0;
            for (int y = 0; y < sixeY; y++) {
                if (board[x][y].equals(value)) {
                    count++;
                    positions.add(new Position2D(x, y));
                }
                if (count == marksToWin) {
                    markPositionsToUpperCase(positions);
                    return WON;
                }
            }
        }
        return TurnStatus.NONEWON;
    }

    @SuppressWarnings("Duplicates")
    private TurnStatus checkDiagonals(String value) throws ArrayIndexOutOfBoundsException {
        ArrayList<ArrayList<Position2D>> diagonals = new ArrayList<>();
        int index = 0;

        int cols = sixeY;
        int rows = sixeX;

        int x, y;
        for (int i = cols - 1; i > 0; i--) {
            y = i;
            x = 0;
            diagonals.add(new ArrayList<>());
            while (y < cols) {
                diagonals.get(index).add(new Position2D(x, y));
                x++;
                y++;
            }
            index++;
        }

        int i = 0;
//        if (sixeX % 2 == 0) i = 0;
        for (; i < rows; i++) {
            x = i;
            y = 0;
            diagonals.add(new ArrayList<>());
            while (x < rows) {
                diagonals.get(index).add(new Position2D(x, y));
                x++;
                y++;
            }
            index++;
        }

        if (markDiagonal(value, diagonals)) return WON;

        return TurnStatus.NONEWON;
    }

    @SuppressWarnings("Duplicates")
    private TurnStatus checkAntiDiagonals(String value) throws ArrayIndexOutOfBoundsException {
        ArrayList<ArrayList<Position2D>> diagonals = new ArrayList<>();
        int index = 0;

        int cols = sixeY;
        int rows = sixeX;

        int x, y;
        for (int i = 0; i < cols; i++) {
            y = i;
            x = 0;
            diagonals.add(new ArrayList<>());
            while (y >= 0 && x < rows) {
                diagonals.get(index).add(new Position2D(x, y));
                x++;
                y--;
            }
            index++;
        }

        int i = 0;
//        if (sixeX == sixeY) i = 0;
        for (; i < rows; i++) {
            x = i;
            y = cols - 1;
            diagonals.add(new ArrayList<>());
            while (x < rows) {
                diagonals.get(index).add(new Position2D(x, y));
                x++;
                y--;
            }
            index++;
        }

        if (markDiagonal(value, diagonals)) return WON;

        return TurnStatus.NONEWON;
    }

    private boolean markDiagonal(String value, ArrayList<ArrayList<Position2D>> diagonals) {
        for (ArrayList<Position2D> diagonal : diagonals) {
            List<Position2D> valuePositions = new ArrayList<>();
            for (Position2D position : diagonal) {
//                System.out.print("[" + position.getX() + "," + position.getY() + "] ");
                if (board[position.getX()][position.getY()].equals(value)) {
                    valuePositions.add(position);
                    if (valuePositions.size() == marksToWin) {
                        markPositionsToUpperCase(valuePositions);
                        return true;
                    }
                }
            }
//            System.out.println();
        }
        return false;
    }

    private void markPositionsToUpperCase(List<Position2D> list) {
        for (Position2D p : list) {
            board[p.getX()][p.getY()] = board[p.getX()][p.getY()].toUpperCase();
        }
    }

}
