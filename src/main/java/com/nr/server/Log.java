package com.nr.server;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Log {
    public static final String FILE_NAME = "numbers.log";
    private Writer writer = null;

    public void write(String content) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(FILE_NAME), StandardCharsets.UTF_8));
            writer.write(content);
        } catch (IOException ignored) {
        } finally {
            try {writer.close();} catch (Exception ignored) {}
        }
    }

    public void touch() {
        write("");
    }
}
