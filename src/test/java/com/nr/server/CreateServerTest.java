package com.nr.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class CreateServerTest {
    @Test
    void connect() throws IOException {
        Socket clientSocket = new Socket("localhost", 4000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("123456789");
        out.println("223456789");
        out.println("000000789");
        out.println("000000000");
    }
}
