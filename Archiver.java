package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 01. Создал классы Archiver (main class) и ZipFileManager (будет совершать операции над файлом архива)
     В классе ZipFileManager:
   - добавил поле Path zipFile (в нем будем хранить полный путь к архиву, с которым будем работать), добавил конструктор
   - объявил метод createZip(Path source) throws Exception, пока с пустой реализацией.
     В классе Archiver:
   - вызвал метод main, запросил пользователя ввести полный путь архива с клавиатуры
   - создал объект класса ZipFileManager, передав в него имя файла архива
   - запросил пользователя ввести путь к файлу, который будем архивировать
   - вызвал метод createZip
 02. В классе ZipFileManager:
   - Реализовал метод createZip(Path source), в котором будем архивировать файл, заданный переменной source
 03. Объявил enum Operation, в него добавил команды:
     CREATE (создать архив), ADD (добавить файл в архив), REMOVE (удалить файл из архива), EXTRACT (извлечь содержимое архива),
     CONTENT (росмотреть содержимое архива), EXIT (выйти из программы)
   - Создал класс ConsoleHelper, в нем реализовал публичные методы:
     writeMessage(String message), String readString() и int readInt()
 04. Создал пакет command, в нем:
   - объявил интерфейс Command с методом execute()
   - объявил класс ExitCommand, реализующий интерфейс Command реализовал в нем метод execute()
 05.
 */

public class Archiver {

    public static void main(String[] args) throws Exception {

        System.out.println("Введите полный путь к архиву");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path zipFile = Paths.get(reader.readLine());
        ZipFileManager manager = new ZipFileManager(zipFile);

        System.out.println("Введите полный путь к файлу, который необходимо архивировать");
        Path source = Paths.get(reader.readLine());
        manager.createZip(source);

        new ExitCommand().execute();

    }
}
