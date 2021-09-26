package com.nr.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class CreateServer extends Thread {
    private final int port;
    private final Input input;
    private final Timer timer;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;

    public CreateServer(int port, Input input, Timer timer) {
        this.port = port;
        this.input = input;
        this.timer = timer;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String text = in.readLine();
            input.validate(text);
            System.out.println(text);

            if (text.equals("terminate")) {
                timer.cancel();
            }

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        try {
            close();
        } catch (IOException ignored) {}
    }

    public void close() throws IOException {
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
