package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // выводит сообщение в консоль
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    // читает строку с консоли
    public static String readString() throws IOException {
        return reader.readLine();
    }

    // читает число с консоли
    public static int readInt() throws IOException {
        return Integer.parseInt(readString());
    }
}
