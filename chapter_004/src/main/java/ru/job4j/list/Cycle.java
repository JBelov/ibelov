package ru.job4j.list;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Cycle {

    boolean hasCycle(Node first) {

        boolean result = false;
        Node current = first;
        int mainIndex = 0;

        while (current != null && !result) {
            int innerIndex = 1;
            Node inner = first;
            while (inner != current) {
                if (inner.next == current && innerIndex < mainIndex) {
                    result = true;
                }
                inner = inner.next;
                innerIndex++;
            }
            current = current.next;
            mainIndex++;
        }
        return result;
    }
}
