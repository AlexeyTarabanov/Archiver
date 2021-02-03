package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.IOException;

/**
 Archiver

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
 05. Разделим команды на два типа:
     1. те, которые работают непосредственно с архивом и
     2. вспомогательные (например EXIT)
     Все команды первого типа, будут иметь общий функционал.
     Его вынесем в общий базовый класс ZipCommand.
     В пакете command:
   - создал абстрактный класс ZipCommand реализующий интерфейс Command
   - создал классы ZipCreateCommand, ZipContentCommand, ZipExtractCommand, ZipAddCommand и ZipRemoveCommand,
     унаследовал их от ZipCommand
 06. Создал класс CommandExecutor (с помощью этого класса пользователь будет сообщать нам, что он хочет сделать).
     В классе CommandExecutor:
   - добавил поле Map<Operation, Command> ALL_KNOWN_COMMANDS_MAP, в статическом блоке проинициализировал значения.
   - реализовал метод execute()
 07. Создал пакет exception, в нем:
   - объявил класс PathIsNotFoundException (будем кидать, если не сможем найти путь, в который нужно распаковать архив,
     или путь к файлу, который хотим запаковать, или любой другой путь)
   - объявил класс WrongZipFileException (будем кидать, если будет попытка сделать что-нибудь с архивом, который не существует)
 08. В классе Archiver:
   - добавил и реализовал метод Operation askOperation() -
     будет запрашивать у пользователя номер операции, которую он хочет совершить.
   - переписал метод main
 09. Создай класс FileManager, который будет получать список всех файлов в какой-то папке, которую нужно заархивировать
     В классе FileManager:
   - добавил 2 поля
     Path rootPath, корневой путь директории, файлы которой нас интересуют
     List<Path> fileList, список относительных путей файлов внутри rootPath, геттер для него
   - реализовал метод collectFileList(), который будет складывать в переменную класса fileList все файлы,
     обнаруженные внутри переданного пути path, вызывая сам себя для всех объектов, в обнаруженных директориях
   - создал конструктор ileManager(Path rootPath) throws IOException, проинициализировал переменные,
     вызвал метод collectFileList(rootPath)
 10. В классе ZipFileManager:
   - реализовал метод addNewZipEntry()
   - отрефакторил его путем создания вспомогательных методов (addNewZipEntry() и copyData())
 11. В классе ZipCreateCommand, реализовал метод execute()
     В классе ZipCommand, реализовал метод getZipFileManager()
 12. Создал класс FileProperties, который будет отвечать за свойства каждого файла в архиве
     В классе FileProperties:
   - создал поля String name, long size, long compressedSize (размер после сжатия), int compressionMethod (метод сжатия)
   - добавил геттеры и конструктор
   - создал и реализовал метод getCompressionRatio (будет считать степень сжатия)
   - переопределил метод toString()
 13. В классе ZipFileManager:
   - реализовал метод getFilesList(), он будет возвращать список свойств файлов
 14. В классе ZipContentCommand:
   - реализовал метод execute(), он отвечает за просмотр содержимого архива
 15.
 */

public class Archiver {
    public static void main(String[] args) throws IOException {

        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        } while (operation != Operation.EXIT);
    }


    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть содержимое архива", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}