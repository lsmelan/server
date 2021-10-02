package com.nr.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.ExecutorService;

import com.google.common.cache.Cache;
import org.apache.commons.lang3.StringUtils;

public class SocketPool implements Runnable {
    private final Socket socket;
    private final Input input;
    private final Timer timer;
    private final ServerSocket serverSocket;
    private final ExecutorService executor;
    private final Cache<String, String> cache;
    public static long duplicates;
    public static long uniqueNumbers;
    public static long uniqueTotal;

    public SocketPool(Socket socket, Input input, Timer timer, ServerSocket serverSocket,
                      ExecutorService executor, Cache<String, String> cache) {
        this.socket = socket;
        this.input = input;
        this.timer = timer;
        this.serverSocket = serverSocket;
        this.executor = executor;
        this.cache = cache;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while (true) {
                line = in.readLine();
                if (line == null || !input.isValid(line)) {
                    break;
                }
                if (line.equals("terminate")) {
                    timer.cancel();
                    serverSocket.close();
                    executor.shutdown();
                    cache.cleanUp();
                    break;
                }
                if (cache.asMap().containsKey(line)) {
                    duplicates++;
                } else {
                    cache.put(line, StringUtils.defaultIfEmpty(StringUtils.stripStart(line, "0"), "0"));
                    uniqueNumbers++;
                    uniqueTotal++;
                }
            }
            in.close();
            socket.close();
        } catch (Exception ignored) {
        }
    }
}
