package com.nr.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.ExecutorService;

public class SocketPool implements Runnable {
    private final Socket socket;
    private final Input input;
    private final Timer timer;
    private final ServerSocket serverSocket;
    private final ExecutorService executor;

    public SocketPool(Socket socket, Input input, Timer timer, ServerSocket serverSocket, ExecutorService executor) {
        this.socket = socket;
        this.input = input;
        this.timer = timer;
        this.serverSocket = serverSocket;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine = in.readLine();
            boolean isValid = input.isValid(inputLine);
            if (isValid) {
                System.out.println(inputLine);
                if (inputLine.equals("terminate")) {
                    timer.cancel();
                    serverSocket.close();
                    executor.shutdown();
                }
            }
            in.close();
            socket.close();
        } catch (Exception ignored) {}
    }
}
