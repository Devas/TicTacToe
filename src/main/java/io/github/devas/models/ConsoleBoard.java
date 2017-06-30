package io.github.devas.models;

import io.github.devas.managers.ConfigurationManager;

public class ConsoleBoard extends Board {

    private ConfigurationManager configManager;

    public ConsoleBoard(int sixeX, int sixeY, ConfigurationManager conf) {
        super(sixeX, sixeY);
        configManager = conf;
    }

    @Override
    public void draw() {
        configManager.println();
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                configManager.print(board[x][y] + " ");
            }
            configManager.println();
        }
        configManager.println();
    }

    /**
     * Only for tests
     */
    public void drawDiagonals() {
        int rows = sixeX;
        int cols = sixeY;

        for (int c = 0; c < cols; c++) {
            for (int i = 0, j = c; i < rows && j >= 0; i++, j--) {
                configManager.print(board[i][j] + " ");
            }
            configManager.println();
        }

        for (int r = 1; r < rows; r++) {
            for (int i = r, j = cols - 1; i < rows && j >= 0; i++, j--) {
                configManager.print(board[i][j] + " ");
            }
            configManager.println();
        }
    }

}
