package com.nr.server;

import com.google.common.cache.Cache;

import java.net.ServerSocket;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateServer extends Thread {
    private final Input input;
    private final Timer timer;
    private final Cache<String, String> cache;

    public CreateServer(Input input, Timer timer, Cache<String, String> cache) {
        this.input = input;
        this.timer = timer;
        this.cache = cache;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            ExecutorService executor = Executors.newFixedThreadPool(5);
            while (!executor.isShutdown()) {
                executor.execute(new SocketPool(serverSocket.accept(), input, timer, serverSocket, executor, cache));
            }
        } catch (Exception ignored) {
        }
    }
}
