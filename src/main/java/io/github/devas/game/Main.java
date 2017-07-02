package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Scanner s = new Scanner(System.in);

        ConfigurationManager configManager = new ConfigurationManager();

        LocalizationManager localizationManager = new LocalizationManager(askForLocale());
        localizationManager.loadLocalization();

        HumanPlayer playerO = new HumanPlayer(configManager.get("playera"), "o");
        HumanPlayer playerX = new HumanPlayer(configManager.get("playerb"), "x");

        Position2D boardSize = askForBoardSize(configManager, localizationManager);

        int marksToWin = askForMarksToWin(configManager, localizationManager);

        TicTacToeGame game = new TicTacToeGame(playerO, playerX, boardSize.getX(), boardSize.getY(), marksToWin, configManager, localizationManager);

        configManager.print(playerO.getName() + localizationManager.get("whofirst"));
        if (s.next().equalsIgnoreCase("y")) {
            game.setInitialTurnPlayerA();
        } else {
            game.setInitialTurnPlayerB();
        }

        while (!game.isFinished()) {
            game.startGame();
        }
    }

    /**
     * Add new languages by adding new cases and .properties files
     */
    private String askForLocale() {
        System.out.println("Locale settings");
        System.out.print("'E' English managers | 'P' Polish managers | Press key: ");
        Scanner s = new Scanner(System.in);
        String lang;

        do {
            lang = s.next();
        } while (!lang.matches("[A-Za-z]"));

        switch (lang.toUpperCase()) {
            case "E":
                return "ENG";
            case "P":
                return "PL";
            default:
                return "ENG";
        }
    }

    private Position2D askForBoardSize(ConfigurationManager configManager, LocalizationManager localizationManager) {
        final int minBoardSize = 3;
        Scanner s = new Scanner(System.in);
        int x = 0;
        int y = 0;
        do {
            configManager.print(localizationManager.get("xboardsize"));
            x = s.nextInt();
            configManager.print(localizationManager.get("yboardsize"));
            y = s.nextInt();
        } while (x < minBoardSize || y < minBoardSize);
        return new Position2D(x, y);
    }

    private int askForMarksToWin(ConfigurationManager configManager, LocalizationManager localizationManager) {
        final int minMarksToWin = 3;
        Scanner s = new Scanner(System.in);
        int marksToWin = 0;
        do {
            configManager.print(localizationManager.get("marks"));
            marksToWin = s.nextInt();
        } while (marksToWin < minMarksToWin);
        return marksToWin;
    }

}
