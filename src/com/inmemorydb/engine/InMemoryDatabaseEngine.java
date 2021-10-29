package com.inmemorydb.engine;

import com.inmemorydb.DatabaseEngine;
import com.inmemorydb.command.Command;
import com.inmemorydb.query.Query;
import java.util.Deque;
import java.util.LinkedList;

class InMemoryDatabaseEngine implements DatabaseEngine {

    private static final class Transaction extends LinkedList<Command> {}
    private final Deque<Transaction> transactions = new LinkedList<>();
    private InMemoryStore store = new InMemoryStore();

    @Override
    public void execute(Command command) {
        if (transactions.isEmpty()) {
            command.execute(store);
        } else {
            transactions.getLast().add(command);
        }
    }

    @Override
    public String query(Query query) {
        final var currentStore = calculateCurrentStore();
        return query.execute(currentStore);
    }

    private InMemoryStore calculateCurrentStore() {
        final var currentStore = new InMemoryStore(store);
        transactions.stream().flatMap(Transaction::stream).forEach(command -> command.execute(currentStore));
        return currentStore;
    }

    @Override
    public void begin() {
        if (transactions.isEmpty() || !transactions.getLast().isEmpty()) {
            transactions.addLast(new Transaction());
        }
    }


    @Override
    public void commit() {
        store = calculateCurrentStore();
        transactions.clear();
    }

    @Override
    public void rollback() {
        if (transactions.isEmpty()) {
            System.err.println("No transaction");
        } else {
            transactions.removeLast();
        }

    }

}
