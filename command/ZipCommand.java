package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

// так как мы не будем создавать объекты класса ZipCommand, сделаем его абстрактным
public abstract class ZipCommand implements Command {

    // т.к. создание объекта будет необходимо и другим командам, вынесем создание в отдельный метод
    public ZipFileManager getZipFileManager() throws Exception {

        ConsoleHelper.writeMessage("Ведите полный путь файла архива");
        String stringPath = ConsoleHelper.readString();
        Path path = Paths.get(stringPath);

        return new ZipFileManager(path);
    }
}
