package io.github.devas.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class GameConnectionHandlerOut implements RichConnectionHandler {

    private Socket socket;
    private int id;
    private boolean finished = false;
    private String messageToClient;

    GameConnectionHandlerOut(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    private void setMessagesToClient(String message) {
        this.messageToClient = message;
    }

    @Override
    public String call() throws Exception {
        try {
            do {
                // Send whatever message you want to client
                String messageToClient = this.messageToClient + '\n';
                System.out.println(TimeStamp.getTimeStamp() + "Sending message to client ID=" + id + " | Message: " + messageToClient);
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                toClient.writeBytes(messageToClient);
                toClient.flush();
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
        return "";
    }

}
