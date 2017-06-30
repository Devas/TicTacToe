package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;

import java.util.Scanner;

/**
 * If player wins the x or o is upper-cased to X or O
 */
class TicTacToeGame extends Game1vs1 {

    private ConfigurationManager configManager;
    private LocalizationManager localizationManager;
    private ConsoleBoard board;
    private static int turn = 1;

    TicTacToeGame(Player playerA, Player playerB, int sizeX, int sizeY, ConfigurationManager conf, LocalizationManager loc) {
        super(playerA, playerB);
        configManager = conf;
        localizationManager = loc;
        board = new ConsoleBoard(sizeX, sizeY, configManager);
    }

    @Override
    public void startGame() {
        while (!isFinished()) {
            runSingleTurn();
        }
    }

    @SuppressWarnings("Duplicates")
    private void runSingleTurn() {
        configManager.println(localizationManager.get("turn") + turn + " ***");
        configManager.println(playerA + " | " + playerB);
        Position2D position = handleInputCoords();
        BoardMove playersMove = new BoardMove(position);
        getCurrentPlayer().addMove(playersMove);
        board.setValueAt(playersMove.getPosition(), getCurrentPlayer().getNick());
        TurnStatus turnStatus = board.checkAll(getCurrentPlayer().getNick());
        board.draw();
        if (turnStatus.equals(TurnStatus.WON)) {
            getCurrentPlayer().incrementGamesWon();
            getCurrentPlayer().getResult().increaseScore(3);
            configManager.println(localizationManager.get("wins") + getCurrentPlayer().getName() + " | " + playerA + " | " + playerB);
            resolveEndOfGame();
        }
        if (turn == board.getArea()) {
            getPlayerA().getResult().increaseScore(1);
            getPlayerB().getResult().increaseScore(1);
            configManager.println(localizationManager.get("draw") + getCurrentPlayer().getName() + " | " + playerA + " | " + playerB);
            resolveEndOfGame();
        }
        nextTurn();
        turn++;
    }

    /**
     * Handles moves and asks to press inpu again if move is out of board or a move has been done already
     */
    private Position2D handleInputCoords() {
        Scanner s = new Scanner(System.in);
        configManager.println(localizationManager.get("nowplays") + currentPlayer.getName());
        int x = 0;
        int y = 0;
        Move move;
        do {
            configManager.println(localizationManager.get("xcoord"));
            x = s.nextInt() - 1;
            configManager.println(localizationManager.get("ycoord"));
            y = s.nextInt() - 1;
            move = new BoardMove(new Position2D(x, y));
        }
        while (x < 0 || y < 0 || x >= board.getSixeX() || y >= board.getSixeY() || playerA.hasMoved(move) || playerB.hasMoved(move));
        return new Position2D(x, y);
    }

    /**
     * Game can end after bestOf or it can ask when end
     * Uncomment nextTurn() to always use the same initial player or comment to swap initial player in every game
     */
    private void resolveEndOfGame() {
        playerA.resetMoves();
        playerB.resetMoves();

        if (shouldGameEndBasedOnBestOf(3) || shouldGameEndBasedOnInput()) {
            configManager.println(localizationManager.get("whole") + getCurrentPlayer().getName() + " | " + playerA + " | " + playerB);
            setFinished(true);
        } else {
            setFinished(false);
            board.reset();
            turn = 0;
//            nextTurn()
        }
    }

    /**
     * Indicates game should end if player wins bestOf games
     */
    private boolean shouldGameEndBasedOnBestOf(int bestOf) {
        return (playerA.getGamesWon() == bestOf || playerB.getGamesWon() == bestOf);
    }

    /**
     * Indicates game should end if player wins bestOf games
     */
    private boolean shouldGameEndBasedOnInput() {
        configManager.print(localizationManager.get("again"));
        Scanner s = new Scanner(System.in);
        return s.next().equalsIgnoreCase("n");
    }

}
