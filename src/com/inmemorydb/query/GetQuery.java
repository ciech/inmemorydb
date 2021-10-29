package com.inmemorydb.query;

import com.inmemorydb.Store;

class GetQuery implements Query {

    private final String key;

    GetQuery(String key) {
        this.key = key;
    }

    @Override
    public String execute(Store store) {
        return String.valueOf(store.get(key));
    }
}
