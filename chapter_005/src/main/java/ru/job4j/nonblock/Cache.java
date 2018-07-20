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
        if (cache.containsKey(model.getId())) {
            cache.computeIfPresent(model.getId(), (key, value) -> {
                        if (model.getVersion() == value.getVersion()) {
                            value.setValue(model.getValue());
                            value.updateVersion();
                        } else {
                            throw new OptimisticException("wrong version");
                        }
                        return value;
                    }
            );
        }
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public Base get(int id) {
        return cache.get(id);
    }


}
