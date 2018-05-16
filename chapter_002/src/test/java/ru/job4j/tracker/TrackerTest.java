package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteFirstItemThenReturnSecondItem() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        tracker.add(firstItem);
        Item secondItem = new Item("test2", "testDescription2", 1234L);
        tracker.add(secondItem);
        tracker.delete(firstItem.getId());
        assertThat(tracker.getAll().get(0), is(secondItem));
    }

    @Test
    public void whenGetAllItemsThenTheyAreAllHere() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        tracker.add(firstItem);
        Item secondItem = new Item("test2", "testDescription2", 1234L);
        tracker.add(secondItem);
        ArrayList<Item> testList = new ArrayList<>();
        testList.add(firstItem);
        testList.add(secondItem);
        assertThat(tracker.getAll(), is(testList));

    }

    @Test
    public void whenFindItemByNameThenItFound() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        tracker.add(firstItem);
        assertThat(tracker.findByName("test1"), is(firstItem));
    }


    @Test
    public void whenFindItemIdThenItFound() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("test1", "testDescription", 123L);
        tracker.add(firstItem);
        assertThat(tracker.findById(firstItem.getId()), is(firstItem));
    }

}
