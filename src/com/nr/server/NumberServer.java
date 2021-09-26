package com.nr.server;

import java.util.Timer;
import java.util.TimerTask;

public class NumberServer {

    public void connect() {
        Input input = new Input();
        Timer timer = new Timer();
        new CreateServer(4000, input, timer).start();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        }, 0, 5000);
    }



    public static void main(String[] args) {
        NumberServer server = new NumberServer();
        server.connect();
    }
}
