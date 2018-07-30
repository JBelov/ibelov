package ru.job4j.nonblock;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Cache {

    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException("wrong version");
            }
            value.setValue(model.getValue());
            value.updateVersion();
                    return value;
                }
        );

    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public Base get(int id) {
        return cache.get(id);
    }
}
