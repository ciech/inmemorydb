package com.inmemorydb.command;


import com.inmemorydb.Store;

public class DeleteCommand implements Command {

    private final String key;

    public DeleteCommand(String key) {
        this.key = key;
    }

    @Override
    public void execute(Store store) {
        store.delete(key);
    }
}
