package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static final Map<Operation, Command> ALL_KNOWN_COMMANDS_MAP;

    static {
        ALL_KNOWN_COMMANDS_MAP = new HashMap<>();

        ALL_KNOWN_COMMANDS_MAP.put(Operation.CREATE, new ZipCreateCommand());
        ALL_KNOWN_COMMANDS_MAP.put(Operation.ADD, new ZipAddCommand());
        ALL_KNOWN_COMMANDS_MAP.put(Operation.REMOVE, new ZipRemoveCommand());
        ALL_KNOWN_COMMANDS_MAP.put(Operation.EXTRACT, new ZipExtractCommand());
        ALL_KNOWN_COMMANDS_MAP.put(Operation.CONTENT, new ZipContentCommand());
        ALL_KNOWN_COMMANDS_MAP.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor(Map<Operation, Command> ALL_KNOWN_COMMANDS_MAP) {
        // чтобы запретить явный вызов конструктора
    }

    // найдет нужную команду и вызовет у нее метод execute.
    public static void execute(Operation operation) throws Exception {
        for (Map.Entry<Operation, Command> pair : ALL_KNOWN_COMMANDS_MAP.entrySet()) {
            if (operation.equals(pair.getKey())) {
                pair.getValue().execute();
            }
        }
    }
}
