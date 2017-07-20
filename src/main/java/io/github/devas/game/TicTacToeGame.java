package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import io.github.devas.net.TimeStamp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static io.github.devas.net.TimeStamp.getTimeStamp;

/**
 * If player wins the x or o is upper-cased to X or O
 */
public class TicTacToeGame extends Game1vs1 {

    private ConfigurationManager configManager;
    private LocalizationManager localizationManager;
    private ConsoleBoard board;
    private WinnerChecker winnerChecker;
    private static int turn = 1;
    private int boardSizeX;
    private int boardSizeY;
    private int marksToWin;
    private Socket socket1;
    private Socket socket2;
    public String messageToClient;
    public String messageFromClient;
    private int port = 6789;
    private boolean finished = false;

    public TicTacToeGame() {
    }

    void setBoardSizeX(int boardSizeX) {
        this.boardSizeX = boardSizeX;
    }

    void setBoardSizeY(int boardSizeY) {
        this.boardSizeY = boardSizeY;
    }

    void setMarksToWin(int marksToWin) {
        this.marksToWin = marksToWin;
    }

    public void setSocket1(Socket socket1) {
        this.socket1 = socket1;
    }

    public void setSocket2(Socket socket2) {
        this.socket2 = socket2;
    }

    public void initAndRunGame() {
        Scanner s = new Scanner(System.in);

        configManager = new ConfigurationManager();

        String locale = askForLocale(configManager);
        localizationManager = new LocalizationManager(locale);
        localizationManager.loadLocalization();

        configManager.println(localizationManager.get("greeting"));

        HumanPlayer playerO = new HumanPlayer(configManager.get("playera"), "o");
        HumanPlayer playerX = new HumanPlayer(configManager.get("playerb"), "x");
        setPlayerA(playerO);
        setPlayerB(playerX);

        Position2D boardSize = askForBoardSize(configManager, localizationManager);
        boardSizeX = boardSize.getX();
        boardSizeY = boardSize.getY();
        board = new ConsoleBoard(boardSizeX, boardSizeY);

        marksToWin = askForMarksToWin(configManager, localizationManager);
        winnerChecker = new WinnerChecker(board, marksToWin);

        configManager.print(playerO.getName() + localizationManager.get("whofirst"));
        if (s.next().equalsIgnoreCase("n")) {
            setInitialTurnPlayerB();
        } else {
            setInitialTurnPlayerA();
        }

        while (!isFinished()) {
            startGame();
        }
    }

    public void sendMessage(Socket socket, String message) {
        try {
            // Send whatever message you want to client
            String messageToClient = message + '\n';
            System.out.println(TimeStamp.getTimeStamp() + "Sending message to client ID=" + socket.getPort() + " | Message: " + messageToClient);
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes(messageToClient);
            toClient.flush();
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Connection handler error");
        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                System.out.println(TimeStamp.getTimeStamp() + "Closing resources error in connection handler");
//            }
        }
    }

    public String getMessage(Socket socket) {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            messageFromClient = fromClient.readLine();
            System.out.println(TimeStamp.getTimeStamp() + "Message received from client ID=" + socket.getPort() + " | Message: " + messageFromClient);

            // Do something with received message
//            handleGameMessagesFromClient(messageFromClient);
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Connection handler error");
        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                System.out.println(TimeStamp.getTimeStamp() + "Closing resources error in connection handler");
//            }
        }
        return messageFromClient;
    }

    /**
     * Add new languages by adding new cases and .properties files
     */
    private String askForLocale(ConfigurationManager configManager) {
        sendMessage(socket1, configManager.println("Locale settings | 'E' English | 'P' Polish | Press key: "));
//        Scanner s = new Scanner(System.in);
//        String lang;
//
//        do {
//            lang = s.next();
//        } while (!lang.matches("[A-Za-z]"));

        String lang = getMessage(socket1);

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
        final int minBoardSize = 1;
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

    @Override
    public void startGame() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(getTimeStamp() + "Server initialized and running");
            do {
//                System.out.println("\n" + getTimeStamp() + "Connections alive: " + connections + " | Listening for new connection ...");
                socket1 = serverSocket.accept();
                socket2 = serverSocket.accept();
                // New connection ...

                int gameId1 = socket1.getPort();
                int gameId2 = socket2.getPort();
                System.out.println(getTimeStamp() + "New connection ID=" + gameId1);
                System.out.println(getTimeStamp() + "New connection ID=" + gameId2);
//                menageConnection(gameId1);
//                menageConnection(gameId2);

//                Callable connectionHandler1 = new GameConnectionHandlerIn(socket1, gameId1);
//                Callable connectionHandler2 = new GameConnectionHandlerIn(socket2, gameId2);
//                Thread connectionThread1 = new Thread(connectionHandler1);
//                Thread connectionThread2 = new Thread(connectionHandler2);
//                connectionThread1.start();
//                connectionThread2.start();

//                executeServerSideGame(socket1, socket2);

                while (!isFinished()) {
                    runSingleTurn();
                }
            } while (!finished);
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Server error");
        }
//        while (!isFinished()) {
//            runSingleTurn();
//        }
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
     * Handles moves and asks to press input again if move is out of board or net move has been done already
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
