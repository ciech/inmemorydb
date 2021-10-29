package com.inmemorydb;

public interface Store {

    void set(String key, Integer value);

    void delete(String key);

    Integer get(String key);

    Integer count(Integer value);

}
