package io.github.devas.net;

import java.io.*;
import java.net.Socket;

class GameClient implements Client {

    private int port = 6789;
    private String host = "localhost";
    private final String EXIT_MESSAGE = "quit";
    private boolean finished = false;

    public static void main(String argv[]) {
        GameClient tcpClient = new GameClient();
        tcpClient.startClient();
    }

    private void startClient() {
        try {
            Socket socket = new Socket(host, port);
            do {
                BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
                String messageFromUser = fromUser.readLine();
                closeOnExitMessage(messageFromUser);

                DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
                toServer.writeBytes(messageFromUser + '\n');
                toServer.flush();

                BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String messageFromServer = fromServer.readLine();
                System.out.println(messageFromServer);
            } while (!finished);
            socket.close();
        } catch (IOException e) {
            System.out.println("Client error");
            System.out.println(e);
        }
    }

    private void closeOnExitMessage(String message) {
        finished = message.equalsIgnoreCase(EXIT_MESSAGE);
    }

}
