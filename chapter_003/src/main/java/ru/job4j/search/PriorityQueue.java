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
        if (tasks.isEmpty()) {
            tasks.add(task);
        } else {
            int size = tasks.size();
            for (int index = 0; index < size; index++) {
                if (index == tasks.size() - 1) {
                    tasks.addLast(task);
                }
                if (tasks.get(index).getPriority() > task.getPriority()) {
                    tasks.add(index, task);
                    break;
                }
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }

}
