package com.inmemorydb.engine;

import com.inmemorydb.DatabaseEngine;

public class DatabaseEngineFactory {

    private DatabaseEngineFactory() {
    }

    public static DatabaseEngine inMemoryDatabaseEngine() {
        return new InMemoryDatabaseEngine();
    }

}
