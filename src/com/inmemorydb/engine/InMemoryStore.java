package com.inmemorydb.engine;

import com.inmemorydb.Store;
import java.util.HashMap;
import java.util.Objects;

class InMemoryStore implements Store {

    private final HashMap<String, Integer> map;
    private final HashMap<Integer, Integer> counts;

    public InMemoryStore(InMemoryStore store) {
        this.map = new HashMap<>(store.map);
        this.counts = new HashMap<>(store.counts);
    }

    public InMemoryStore() {
        this.map = new HashMap<>();
        this.counts = new HashMap<>();
    }

    public void set(String key, Integer value) {
        if (Objects.nonNull(map.get(key))) {
            delete(key);
        }
        map.put(key, value);
        final var currentCount = count(value);
        counts.put(value, currentCount + 1);
    }

    public void delete(String key) {
        final var value = map.get(key);
        if (Objects.nonNull(value)) {
            final var currentCount = counts.get(value);
            counts.put(value, currentCount - 1);
        }
        map.remove(key);
    }

    public Integer get(String key) {
        return map.get(key);
    }

    public Integer count(Integer value) {
        return counts.getOrDefault(value, 0);
    }

}
