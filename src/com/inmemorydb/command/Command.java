package com.inmemorydb.command;

import com.inmemorydb.Store;

public interface Command {

    void execute(Store store);
}
