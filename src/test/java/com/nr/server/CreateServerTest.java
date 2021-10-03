package com.nr.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

// The server must be started first and tests should be run separately (it needs to be improved)
class CreateServerTest {
    @Test
    void sendValidNumbers_shouldWriteAllNumbersToTheLogFile() throws IOException {
        Socket clientSocket = new Socket("localhost", 4000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("123456789");
        out.println("223456789");
        out.println("000000789");
        out.println("000000000");
    }

    @Test
    void sendDuplicateNumbers_shouldWriteOnlyUniqueNumbersToTheLogFile() throws IOException {
        Socket clientSocket = new Socket("localhost", 4000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("123456789");
        out.println("123456789");
        out.println("223456789");
        out.println("223456789");
    }

    @Test
    void sendValidAndInvalidNumbers_shouldWriteAndCloseConnectionImmediately() throws IOException {
        Socket clientSocket = new Socket("localhost", 4000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("123456789");
        out.println("123");
        out.println("223456789");
    }

    @Test
    void sendTerminate_shouldStopTheApplicationImmediately() throws IOException {
        Socket clientSocket = new Socket("localhost", 4000);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("terminate");
    }
}
