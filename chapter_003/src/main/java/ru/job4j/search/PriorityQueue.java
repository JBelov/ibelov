package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     *
     * @param task задача
     */
    public void put(Task task) {
        int size = tasks.size();
        int index = 0;
        while (index < size) {
            if (tasks.get(index).getPriority() > task.getPriority()) {
                break;
            }
            index++;
        }
        tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
