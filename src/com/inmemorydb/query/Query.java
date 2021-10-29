package com.inmemorydb.query;

import com.inmemorydb.Store;

public interface Query {

    String execute(Store store);
}
