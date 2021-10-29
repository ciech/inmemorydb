package com.inmemorydb.query;

import com.inmemorydb.Store;

class CountQuery implements Query {

    private final Integer value;

    CountQuery(Integer value) {
        this.value = value;
    }

    @Override
    public String execute(Store store) {
        return String.valueOf(store.count(value));
    }
}
