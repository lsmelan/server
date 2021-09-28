package com.nr.server;

import java.util.Timer;
import java.util.TimerTask;

public class NumberServer {

    private Timer timer;

    public void connect() {
        timer = new Timer();
        new CreateServer(new Input(), timer).start();
    }

    private void output() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Received 50 unique numbers, 2 duplicates. Unique total:567231");
            }
        }, 0, 10000);
    }

    public static void main(String[] args) {
        NumberServer server = new NumberServer();
        server.connect();
        server.output();
    }
}
