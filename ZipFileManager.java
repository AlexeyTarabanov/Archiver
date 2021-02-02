package com.javarush.task.task31.task3110;

import java.nio.file.Path;

// менеджер архива
// он будет совершать операции над файлом архива (файлом, который будет храниться на диске и иметь расширение zip).
public class ZipFileManager {

    // в ней будем хранить полный путь к архиву, с которым будем работать
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        // Path source - это путь к чему-то, что мы будем архивировать
    }
}
