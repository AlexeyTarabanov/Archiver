package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// отвечает за получение списка всех файлов в какой-то папке.
public class FileManager {

    // корневой путь директории, файлы которой нас интересуют
    private Path rootPath;
    // список относительных путей файлов внутри rootPath
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectFileList(Path path) throws IOException {

        // если переданный путь path является обычным файлом
        if (Files.isRegularFile(path)) {
            // получил его относительный путь относительно rootPath и добавил в список fileList
            fileList.add(rootPath.relativize(path));
        }
        // если переданный путь path, является директорией
        if (Files.isDirectory(path)) {
            // для того, чтобы пройтись по всему содержимому директории
            // получаем объект DirectoryStream
            DirectoryStream<Path> stream = Files.newDirectoryStream(path);
            for (Path path1 : stream) {
                // рекурсия
                collectFileList(path1);
            }
            stream.close();
        }
    }
}
