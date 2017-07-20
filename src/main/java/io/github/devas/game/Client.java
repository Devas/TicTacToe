package io.github.devas.game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Client {

    private int port = 6789;
    private String host = "localhost";
    private final String EXIT_MESSAGE = "quit";
    private boolean finished = false;

    public static void main(String argv[]) {
        Client tcpClient = new Client();
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
