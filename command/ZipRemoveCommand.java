package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Paths;

// команда удаления файла из архива
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {

        ZipFileManager zipFileManager = getZipFileManager();

        try {
            ConsoleHelper.writeMessage("Из какого архива и какой файл будем удалять?");
            zipFileManager.removeFile(Paths.get(ConsoleHelper.readString()));

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
