package ru.job4j.nonblock;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class CacheTest {

    @Test
    public void tryToUseCacheWithSuccess() {
        Cache cache = new Cache();
        Base model1 = new Base(1, "model 1 value 0");
        Base model2 = new Base(2, "model 2 value 0");
        Base model3 = new Base(3, "model 3 value 0");
        Thread threadA = new Thread(() ->
        {
            cache.add(model1);
            cache.add(model2);
            cache.add(model3);
            for (int i = 1; i < 100; i++) {
                model2.setValue("model 2 value " + i);
                cache.update(model2);
            }
        });
        Thread threadB = new Thread(() ->
        {
            for (int i = 1; i < 150; i++) {
                Base fakeModel = new Base(2, "Second Stream");
                fakeModel.setVersion(101);
                cache.update(fakeModel);
            }
        });
        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(cache.get(2).getValue(), is("model 2 value 99"));
        assertThat(cache.get(2).getVersion(), is(99));
        System.out.println(cache.get(2));
    }
}