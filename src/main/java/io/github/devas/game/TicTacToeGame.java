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
    private WinnerChecker winnerChecker;
    private static int turn = 1;

    TicTacToeGame(Player playerA, Player playerB, int sizeX, int sizeY, int marksToWin, ConfigurationManager conf, LocalizationManager loc) {
        super(playerA, playerB);
        configManager = conf;
        localizationManager = loc;
        board = new ConsoleBoard(sizeX, sizeY);
        winnerChecker = new WinnerChecker(board, marksToWin);
    }

    @Override
    public void startGame() {
        while (!isFinished()) {
            runSingleTurn();
        }
    }

    private void runSingleTurn() {
        configManager.println(localizationManager.get("turn") + turn + " ***");
        configManager.println(getPlayerA() + " | " + getPlayerB());
        Position2D position = handleInputCoords();
        BoardMove playersMove = new BoardMove(position);
        getCurrentPlayer().addMove(playersMove);
        board.setValueAt(playersMove.getPosition(), getCurrentPlayer().getNick());
        TurnStatus turnStatus = winnerChecker.checkAll(getCurrentPlayer().getNick());
        configManager.print(board.draw());
        if (turnStatus.equals(TurnStatus.WON)) {
            getCurrentPlayer().incrementGamesWon();
            getCurrentPlayer().getResult().increaseScore(3);
            configManager.println(localizationManager.get("wins") + getCurrentPlayer().getName() + " | " + getPlayerA() + " | " + getPlayerB());
            resolveEndOfGame();
        }
        if (turn == board.getArea()) {
            getPlayerA().getResult().increaseScore(1);
            getPlayerB().getResult().increaseScore(1);
            configManager.println(localizationManager.get("draw") + getCurrentPlayer().getName() + " | " + getPlayerA() + " | " + getPlayerB());
            resolveEndOfGame();
        }
        nextTurn();
        turn++;
    }

    /**
     * Handles moves and asks to press input again if move is out of board or a move has been done already
     */
    private Position2D handleInputCoords() {
        Scanner s = new Scanner(System.in);
        configManager.println(localizationManager.get("nowplays") + getCurrentPlayer().getName());
        Position2D pos = new Position2D();
        Move move;
        do {
            configManager.println(localizationManager.get("xcoord"));
            pos.setX(s.nextInt() - 1);
            configManager.println(localizationManager.get("ycoord"));
            pos.setY(s.nextInt() - 1);
            move = new BoardMove(pos);
        }
        while (pos.getX() < 0 || pos.getY() < 0 || pos.getX() >= board.getSixeX() || pos.getY() >= board.getSixeY() ||
                getPlayerA().hasMoved(move) || getPlayerB().hasMoved(move));
        return pos;
    }

    /**
     * Game can end after bestOf or it can ask when end
     * Uncomment nextTurn() to always use the same initial player or comment to swap initial player in every game
     */
    private void resolveEndOfGame() {
        getPlayerA().resetMoves();
        getPlayerB().resetMoves();

        if (shouldGameEndBasedOnBestOf(3) || shouldGameEndBasedOnInput()) {
            configManager.println(localizationManager.get("whole") + getCurrentPlayer().getName() + " | " + getPlayerA() + " | " + getPlayerB());
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
        return (getPlayerA().getGamesWon() == bestOf || getPlayerB().getGamesWon() == bestOf);
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
