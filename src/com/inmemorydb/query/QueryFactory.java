package com.inmemorydb.query;

public class QueryFactory {

    private QueryFactory() {
    }

    public static Query count(Integer value) {
        return new CountQuery(value);
    }

    public static Query get(String key) {
        return new GetQuery(key);
    }
}
