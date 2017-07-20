package io.github.devas.game;

import io.github.devas.net.ClientInfo;
import io.github.devas.net.ServerSideGame;
import io.github.devas.net.TimeStamp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.github.devas.net.TimeStamp.getTimeStamp;

class Server {

    private int port = 6789;
    private int connections;
    private Map<Integer, ServerSideGame> games = new HashMap<>(); // maps games with unique id
    private boolean finished = false;

    private Server() {
//        TimeStamp.disableTimeStamp();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(getTimeStamp() + "Server initialized and running");
            do {
                System.out.println("\n" + getTimeStamp() + "Connections alive: " + connections + " | Listening for new connection ...");
                Socket socket1 = serverSocket.accept();
                Socket socket2 = serverSocket.accept();
                // New connection ...

                int gameId1 = socket1.getPort();
                int gameId2 = socket2.getPort();
                System.out.println(getTimeStamp() + "New connection ID=" + gameId1);
                System.out.println(getTimeStamp() + "New connection ID=" + gameId2);
                menageConnection(gameId1);
                menageConnection(gameId2);


                // Game
                TicTacToeGame game = new TicTacToeGame();
                game.setSocket1(socket1);
                game.setSocket2(socket2);
                game.initAndRunGame();



                executeServerSideGame(socket1, socket2);
            } while (!finished);
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Server error");
        }
    }

    private void executeServerSideGame(Socket socket1, Socket socket2) {

    }

    private void menageConnection(int id) {
        if (hasNewClientArrived(id)) {
            connections++;
            getAvailableGame(id).addClient(new ClientInfo(id));
        } else {
            reconnectClient(id);
        }
    }

    private boolean hasNewClientArrived(int id) {
        return !games.containsKey(id);
    }

    private void reconnectClient(int id) {

    }

    /**
     * Returns existing game or creates new game and returns it.
     */
    private ServerSideGame getAvailableGame(int id) {
        Optional<ServerSideGame> gameToJoin = findGameToJoin();
        if(gameToJoin.isPresent()) {
            return gameToJoin.get();
        } else {
            ServerSideGame newGame = new ServerSideGame(id);
            addGame(id, newGame);
            return newGame;
        }
    }

    /**
     * Checks games if any can be joined and returns it if found, otherwise returns empty Optional.
     */
    private Optional<ServerSideGame> findGameToJoin() {
        for (ServerSideGame serverSideGame : games.values()) {
            if (serverSideGame.canClientJoinGame()) {
                return Optional.of(serverSideGame);
            }
        }
        return Optional.empty();
    }

    private void addGame(int id, ServerSideGame game) {
        games.put(id, game);
    }

    private void removeGame(int id) {
        games.remove(id);
//        connections -= 1; connections -= 2;
    }

    private void removeAllGames() {
        games.clear();
        connections = 0;
    }

}
