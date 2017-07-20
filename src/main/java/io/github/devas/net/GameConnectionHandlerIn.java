package io.github.devas.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class GameConnectionHandlerIn implements RichConnectionHandler {

    private Socket socket;
    private int id;
    private boolean finished = false;
    private String messageFromClient;

    GameConnectionHandlerIn(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        try {
            do {
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                messageFromClient = fromClient.readLine();
                System.out.println(TimeStamp.getTimeStamp() + "Message received from client ID=" + id + " | Message: " + messageFromClient);

                // Do something with received message
                handleGameMessagesFromClient(messageFromClient);
            } while (!finished);
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Connection handler error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(TimeStamp.getTimeStamp() + "Closing resources error in connection handler");
            }
        }
        return messageFromClient;
    }

    private void handleGameMessagesFromClient(String message) {
        //  ....
    }

}
