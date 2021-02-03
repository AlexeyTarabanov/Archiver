package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.IOException;

/**
 * 01. Создал классы Archiver (main class) и ZipFileManager (будет совершать операции над файлом архива)
 * В классе ZipFileManager:
 * - добавил поле Path zipFile (в нем будем хранить полный путь к архиву, с которым будем работать), добавил конструктор
 * - объявил метод createZip(Path source) throws Exception, пока с пустой реализацией.
 * В классе Archiver:
 * - вызвал метод main, запросил пользователя ввести полный путь архива с клавиатуры
 * - создал объект класса ZipFileManager, передав в него имя файла архива
 * - запросил пользователя ввести путь к файлу, который будем архивировать
 * - вызвал метод createZip
 * 02. В классе ZipFileManager:
 * - Реализовал метод createZip(Path source), в котором будем архивировать файл, заданный переменной source
 * 03. Объявил enum Operation, в него добавил команды:
 * CREATE (создать архив), ADD (добавить файл в архив), REMOVE (удалить файл из архива), EXTRACT (извлечь содержимое архива),
 * CONTENT (росмотреть содержимое архива), EXIT (выйти из программы)
 * - Создал класс ConsoleHelper, в нем реализовал публичные методы:
 * writeMessage(String message), String readString() и int readInt()
 * 04. Создал пакет command, в нем:
 * - объявил интерфейс Command с методом execute() - отвечает за выполнение каких-то действий
 * - объявил класс ExitCommand, реализующий интерфейс Command реализовал в нем метод execute()
 * 05. Разделим команды на два типа:
 * 1. те, которые работают непосредственно с архивом и
 * 2. вспомогательные (например EXIT)
 * Все команды первого типа, будут иметь общий функционал.
 * Его вынесем в общий базовый класс ZipCommand.
 * В пакете command:
 * - создал абстрактный класс ZipCommand реализующий интерфейс Command
 * - создал классы ZipCreateCommand, ZipContentCommand, ZipExtractCommand, ZipAddCommand и ZipRemoveCommand,
 * унаследовал их от ZipCommand.
 * 06. Создал класс CommandExecutor (с помощью этого класса пользователь будет сообщать нам, что он хочет сделать).
 * В классе CommandExecutor:
 * - добавил поле Map<Operation, Command> ALL_KNOWN_COMMANDS_MAP, в статическом блоке проинициализировал значения.
 * - реализовал метод execute()
 * 07. Создал пакет exception, в нем:
 * - объявил класс PathIsNotFoundException (будем кидать, если не сможем найти путь, в который нужно распаковать архив,
 * или путь к файлу, который хотим запаковать, или любой другой путь)
 * - объявил класс WrongZipFileException (будем кидать, если будет попытка сделать что-нибудь с архивом, который не существует)
 * 08. В классе Archiver:
 * - добавил и реализовал метод Operation askOperation() -
 * будет запрашивать у пользователя номер операции, которую он хочет совершить.
 * - переписал метод main
 * 09.
 */

public class Archiver {

    public static void main(String[] args) {

        Operation operation = null;

        while (true) {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
            if (operation == Operation.EXIT)
                break;
        }
    }

    public static Operation askOperation() throws IOException {

        Operation operation;
        ConsoleHelper.writeMessage(
                "Выберите операцию:" + "\n" +
                        "0 - упаковать файлы в архив\n" +
                        "1 - добавить файл в архив\n" +
                        "2 - удалить файл из архива\n" +
                        "3 - распаковать архив\n" +
                        "4 - просмотреть содержимое архива\n" +
                        "5 - выход");
        int numberOperation = ConsoleHelper.readInt();

        return Operation.values()[numberOperation];
    }
}
