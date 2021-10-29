package com.inmemorydb;

import com.inmemorydb.command.Command;
import com.inmemorydb.query.Query;

public interface DatabaseEngine {

    void execute(Command command);
    String query(Query query);

    void begin();
    void commit();
    void rollback();

}
