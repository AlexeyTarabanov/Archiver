package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// менеджер архива
// он будет совершать операции над файлом архива (файлом, который будет храниться на диске и иметь расширение zip).
public class ZipFileManager {

    // в ней будем хранить полный путь к архиву, с которым будем работать
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    // здесь будем архивировать файл, заданный переменной source
    // Path source - это путь к чему-то, что мы будем архивировать
    public void createZip(Path source) throws Exception {

        try (
                // класс ZipOutputStream сжимает (архивирует) переданные в него данные
                ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
                // создаем поток на чтение, здесь берем наш файл (который необходимо занести в архив) и указываем путь до него
                InputStream inputStream = Files.newInputStream(source)
        ) {
            // создаем файл, который хотим перенести в архив
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            // добавляем файл в архив
            zipOutputStream.putNextEntry(zipEntry);

            // считываем содержимое файла в массив byte
            byte[] buffer;
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            // добавляем содержимое к архиву
            zipOutputStream.write(buffer);

            zipOutputStream.closeEntry();
        }
    }
}
