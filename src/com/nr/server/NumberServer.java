package com.nr.server;

import java.util.Timer;
import java.util.TimerTask;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

public class NumberServer {
    private final Timer timer;
    private final Cache<String, String> cache;
    private final Log log;

    public NumberServer() {
        this.timer = new Timer();
        this.cache = CacheBuilder.newBuilder().build();
        this.log = new Log();
        log.touch();
    }

    public void start() {
        new CreateServer(new Input(), timer, cache).start();
    }

    private void schedule() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showStats();
                createLog();
            }
        }, 0, 10000);
    }

    private void showStats() {
        System.out.printf("Received %d unique numbers, %d duplicates. Unique total:%d\n",
                SocketPool.uniqueNumbers, SocketPool.duplicates, SocketPool.uniqueTotal);
        SocketPool.duplicates = 0;
        SocketPool.uniqueNumbers = 0;
    }

    private void createLog() {
        String numbers = StringUtils.join(cache.asMap().values(), "\n");
        log.write(numbers);
    }

    public static void main(String[] args) {
        NumberServer server = new NumberServer();
        server.start();
        server.schedule();
    }
}
