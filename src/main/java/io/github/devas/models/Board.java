package io.github.devas.models;

import io.github.devas.api.World2D;

import java.util.*;

public abstract class Board implements World2D {

    protected final int sixeX;
    protected final int sixeY;
    protected final int area;
    protected final int marksToWin;
    protected final String[][] board;
    protected final String FILL_STRING = "*";

    public Board(int sixeX, int sixeY) {
        this.sixeX = sixeX;
        this.sixeY = sixeY;
        this.area = sixeX * sixeY;
        this.marksToWin = Math.min(sixeX, sixeY);
        this.board = new String[sixeX][sixeY];
        setAll(FILL_STRING);
    }

    @Override
    public abstract void draw();

    public int getSixeX() {
        return sixeX;
    }

    public int getSixeY() {
        return sixeY;
    }

    public int getArea() {
        return area;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getValueAt(int x, int y) {
        return board[x][y];
    }

    public String getValueAt(Position2D position) {
        return board[position.getX()][position.getY()];
    }

    public void setValueAt(int x, int y, String value) {
        board[x][y] = value;
    }

    public void setValueAt(Position2D position, String value) {
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

    /**
     * If row, column or diagonal is found this method returns status WON (a player won).
     * Otherwise returns status NONEWON (nobody has won yet)
     */
    public TurnStatus checkAll(String value) {
        try {
            if (checkRows(value).equals(TurnStatus.WON))
                return TurnStatus.WON;
            if (checkColumns(value).equals(TurnStatus.WON))
                return TurnStatus.WON;
            if (checkDiagonals(value).equals(TurnStatus.WON))
                return TurnStatus.WON;
            if (checkAntiDiagonals(value).equals(TurnStatus.WON))
                return TurnStatus.WON;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Array out of bounds");
            e.printStackTrace();
        }
        return TurnStatus.NONEWON;
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
                    return TurnStatus.WON;
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
                    return TurnStatus.WON;
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

        if (markDiagonal(value, diagonals)) return TurnStatus.WON;

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

        if (markDiagonal(value, diagonals)) return TurnStatus.WON;

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
