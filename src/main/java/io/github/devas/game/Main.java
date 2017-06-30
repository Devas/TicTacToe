package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import io.github.devas.models.HumanPlayer;
import io.github.devas.models.Position2D;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Scanner s = new Scanner(System.in);

        LocalizationManager localizationManager = new LocalizationManager();
        localizationManager.askForLocale();
        localizationManager.loadLocalization();

        ConfigurationManager configManager = new ConfigurationManager();

        int x = 0;
        int y = 0;
        do {
            configManager.print(localizationManager.get("xboardsize"));
            x = s.nextInt();
            configManager.print(localizationManager.get("yboardsize"));
            y = s.nextInt();
        } while (x < 1 || y < 1);
        Position2D boardSize = new Position2D(x, y);

        HumanPlayer playerO = new HumanPlayer(configManager.get("playera"), "o");
        HumanPlayer playerX = new HumanPlayer(configManager.get("playerb"), "x");

        TicTacToeGame game = new TicTacToeGame(playerO, playerX, boardSize.getX(), boardSize.getY(), configManager, localizationManager);

        configManager.print(playerO.getName() + localizationManager.get("whofirst"));
        if (s.next().equalsIgnoreCase("y")) {
            game.setInitialTurnPlayerA();
        } else {
            game.setInitialTurnPlayerB();
        }

        while (!game.isFinished()) {
            game.start();
        }
    }

}
