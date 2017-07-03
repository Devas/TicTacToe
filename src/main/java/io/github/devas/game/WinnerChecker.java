package io.github.devas.game;

import java.util.ArrayList;
import java.util.List;

import static io.github.devas.game.TurnStatus.NONE_WON;
import static io.github.devas.game.TurnStatus.WON;

class WinnerChecker {

    private Board board;
    private int marksToWin;

    WinnerChecker(Board board, int usersMarksToWin) {
        this.board = board;
        int minSize = Math.min(board.sixeX, board.sixeY);
        this.marksToWin = Math.min(minSize, usersMarksToWin);
    }

    /**
     * If row, column or diagonal is found this method returns status WON (a player won).
     * Otherwise returns status NONE_WON (nobody has won yet)
     */
    TurnStatus checkAll(String value) {
        return winnerIn(value, "rows") || winnerIn(value, "cols") || winnerIn(value, "diag1") || winnerIn(value, "diag2") ? WON : NONE_WON;
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

    private TurnStatus checkRows(String value) throws ArrayIndexOutOfBoundsException {
        List<Position2D> positions = new ArrayList<>();
        for (int y = 0; y < board.sixeY; y++) {
            positions.clear();
            int count = 0;
            for (int x = 0; x < board.sixeX; x++) {
                if (board.getBoard()[x][y].equals(value)) {
                    count++;
                    positions.add(new Position2D(x, y));
                }
                if (count == marksToWin) {
                    markPositionsToUpperCase(positions);
                    return WON;
                }
            }
        }
        return TurnStatus.NONE_WON;
    }

    private TurnStatus checkColumns(String value) throws ArrayIndexOutOfBoundsException {
        List<Position2D> positions = new ArrayList<>();
        for (int x = 0; x < board.sixeX; x++) {
            positions.clear();
            int count = 0;
            for (int y = 0; y < board.sixeY; y++) {
                if (board.getBoard()[x][y].equals(value)) {
                    count++;
                    positions.add(new Position2D(x, y));
                }
                if (count == marksToWin) {
                    markPositionsToUpperCase(positions);
                    return WON;
                }
            }
        }
        return TurnStatus.NONE_WON;
    }

    private TurnStatus checkDiagonals(String value) throws ArrayIndexOutOfBoundsException {
        ArrayList<ArrayList<Position2D>> diagonals = new ArrayList<>();
        int index = 0;

        int cols = board.sixeY;
        int rows = board.sixeX;

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

        return TurnStatus.NONE_WON;
    }

    private TurnStatus checkAntiDiagonals(String value) throws ArrayIndexOutOfBoundsException {
        ArrayList<ArrayList<Position2D>> diagonals = new ArrayList<>();
        int index = 0;

        int cols = board.sixeY;
        int rows = board.sixeX;

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

        return TurnStatus.NONE_WON;
    }

    private boolean markDiagonal(String value, ArrayList<ArrayList<Position2D>> diagonals) {
        for (ArrayList<Position2D> diagonal : diagonals) {
            List<Position2D> valuePositions = new ArrayList<>();
            for (Position2D position : diagonal) {
//                System.out.print("[" + position.getX() + "," + position.getY() + "] ");
                if (board.getBoard()[position.getX()][position.getY()].equals(value)) {
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
            board.getBoard()[p.getX()][p.getY()] = board.getBoard()[p.getX()][p.getY()].toUpperCase();
        }
    }

}
