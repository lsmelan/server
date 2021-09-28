package com.nr.server;

import java.net.ServerSocket;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateServer extends Thread {
    private final Input input;
    private final Timer timer;

    public CreateServer(Input input, Timer timer) {
        this.input = input;
        this.timer = timer;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            ExecutorService executor = Executors.newFixedThreadPool(5);
            while (!executor.isShutdown()) {
                executor.execute(new SocketPool(serverSocket.accept(), input, timer, serverSocket, executor));
            }
        } catch (Exception ignored) {}
    }
}
