package com.inmemorydb.command;


import com.inmemorydb.Store;

class SetCommand implements Command {

    private final String key;
    private final Integer value;

    SetCommand(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute(Store store) {
        store.set(key, value);
    }
}
