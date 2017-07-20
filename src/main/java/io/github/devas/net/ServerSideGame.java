package io.github.devas.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Every game has own id, settings and clients.
 */
public class ServerSideGame implements Game {

    private int gameId;
    private Settings settings;
    private List<ClientInfo> clients;

    public ServerSideGame(int gameId) {
        this.gameId = gameId;
        this.settings = new GameSettings(2);
        this.clients = new ArrayList<>(settings.getMaxNumberOfPlayers());
    }

    int getGameId() {
        return gameId;
    }

    public void addClient(ClientInfo client) {
        clients.add(client);
    }

    void removeClient(ClientInfo client) {
        clients.remove(client);
    }

    void removeAllClients() {
        clients.clear();
    }

    public boolean canClientJoinGame() {
        return clients.size() < settings.getMaxNumberOfPlayers();
    }

}
