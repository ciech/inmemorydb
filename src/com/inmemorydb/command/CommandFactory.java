package com.inmemorydb.command;

public class CommandFactory {

    private CommandFactory() {
    }

    public static Command set(String key, Integer value) {
        return new SetCommand(key, value);
    }

    public static Command delete(String key) {
        return new DeleteCommand(key);
    }

}
