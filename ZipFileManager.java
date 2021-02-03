package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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

        // Создаем объект Path: родительская директория целевого файла
        Path zipPath = zipFile.getParent();

        // проверяем, существует ли директория
        if (Files.notExists(zipPath)) {
            // если директория не существует, создаем новую
            Files.createDirectory(zipPath);
        }

        // создаем новый поток архива, в аргументах указываем zipFile - путь к архиву, с которым будем работать
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            // если переданный в параметре путь ведет к обычному файлу - вызываем метод
            if (Files.isRegularFile(source)) {
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            }
            // если путь ведет к директории
            else if (Files.isDirectory(source)) {
                // создем объект класса файловый менеджер
                FileManager fileManager = new FileManager(source);
                // получаем список файлов
                List<Path> fileNames = fileManager.getFileList();
                for (Path fileName : fileNames) {
                    // для всех элементов fileNames, вызываем метод addNewZipEntry
                    addNewZipEntry(zipOutputStream, source, fileName);
                }
            } else {
                // если путь не ведет никуда - исключение
                throw new PathIsNotFoundException();
            }
        }
    }

    // будет добавлять элементы архива
    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {

        // создал новый путь для fileName, расположенного в директории filePath
        // resolve(Path) - метод создания нового пути путем присоединения существующего пути к текущему
        Path fullPath = filePath.resolve(fileName);
        // создал поток на чтение
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            // создал новый элемент архива ZipEntry
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            // добавил в переданный поток, новый элемент архива
            zipOutputStream.putNextEntry(zipEntry);
            // считываем содержимое файла и добавляем новый эл-т в поток
            copyData(inputStream, zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }

    // должен читать данные из in и записывать в out, пока не вычитает все
    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        int len;
        // считываю содержимое файла
        while ((len = in.read(buffer)) != -1) {
            // добавляю новый эл-т в поток
            out.write(buffer, 0, len);
        }
    }
}
