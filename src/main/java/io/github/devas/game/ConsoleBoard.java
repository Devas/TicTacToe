package io.github.devas.game;

class ConsoleBoard extends Board {

    ConsoleBoard(int sixeX, int sixeY) {
        super(sixeX, sixeY);
    }

    @Override
    public String draw() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                stringBuilder.append(board[x][y]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Only for tests
     */
    String drawDiagonals() {
        StringBuilder stringBuilder = new StringBuilder();
        int rows = sixeX;
        int cols = sixeY;
        for (int c = 0; c < cols; c++) {
            for (int i = 0, j = c; i < rows && j >= 0; i++, j--) {
                stringBuilder.append(board[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        for (int r = 1; r < rows; r++) {
            for (int i = r, j = cols - 1; i < rows && j >= 0; i++, j--) {
                stringBuilder.append(board[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
