package com.inmemorydb;

import static com.inmemorydb.command.CommandFactory.delete;
import static com.inmemorydb.command.CommandFactory.set;
import static com.inmemorydb.query.QueryFactory.get;
import static com.inmemorydb.query.QueryFactory.count;

import com.inmemorydb.engine.DatabaseEngineFactory;


public class Main {

    public static void main(String[] args) {
       case1();
       case2();
       case3();
       case4();
       case5();
    }

    public static void case1() {
        DatabaseEngine databaseEngine = DatabaseEngineFactory.inMemoryDatabaseEngine();
        databaseEngine.execute(set("a", 10));
        databaseEngine.execute(delete("a"));
        compare("a", "null", databaseEngine.query(get("a")));
    }

    public static void case2() {
        DatabaseEngine databaseEngine = DatabaseEngineFactory.inMemoryDatabaseEngine();
        databaseEngine.execute(set("a", 10));
        databaseEngine.execute(set("b", 10));

        compare("count 10", "2", databaseEngine.query(count(10)));
        compare("count 20", "0", databaseEngine.query(count(20)));

        databaseEngine.execute(delete("a"));

        compare("count 10", "1", databaseEngine.query(count(10)));

        databaseEngine.execute(set("b", 30));

        compare("count 20", "0", databaseEngine.query(count(20)));

    }

    public static void case3() {
        DatabaseEngine databaseEngine = DatabaseEngineFactory.inMemoryDatabaseEngine();

        databaseEngine.begin();
        databaseEngine.execute(set("a", 10));
        compare("get a", "10", databaseEngine.query(get("a")));

        databaseEngine.begin();
        databaseEngine.execute(set("a", 20));
        compare("get a", "20", databaseEngine.query(get("a")));

        databaseEngine.rollback();
        compare("get a", "10", databaseEngine.query(get("a")));

        databaseEngine.rollback();
        compare("get a", "null", databaseEngine.query(get("a")));

    }

    public static void case4() {
        DatabaseEngine databaseEngine = DatabaseEngineFactory.inMemoryDatabaseEngine();
        databaseEngine.begin();
        databaseEngine.execute(set("a", 30));
        databaseEngine.begin();
        databaseEngine.execute(set("a", 40));
        databaseEngine.commit();
        compare("get a", "40", databaseEngine.query(get("a")));

        databaseEngine.rollback();
    }

    public static void case5() {
        DatabaseEngine databaseEngine = DatabaseEngineFactory.inMemoryDatabaseEngine();
        databaseEngine.execute(set("a", 50));
        databaseEngine.begin();
        compare("get a", "50", databaseEngine.query(get("a")));
        databaseEngine.execute(set("a", 60));
        databaseEngine.begin();
        databaseEngine.execute(delete("a"));
        databaseEngine.execute(set("a", 40));
        databaseEngine.commit();
        compare("get a", "40", databaseEngine.query(get("a")));

        databaseEngine.rollback();
    }

    private static void compare(String description, String s1, String s2) {
        if (s1.equals(s2)) {
            System.err.println(String.format("TEST: %-10s should be %-5s and it is equal %-4s PASS", description, s1, s2));
        } else {
            System.err.println(String.format("TEST: %-10s should be %-5s and it is equal %-4s FAIL", description, s1, s2));
        }
    }
}
