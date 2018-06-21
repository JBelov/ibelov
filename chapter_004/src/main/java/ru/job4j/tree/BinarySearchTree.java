package ru.job4j.tree;

import java.util.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class BinarySearchTree<E extends Comparable> implements Iterable<E> {

    private Node<E> mainRoot;

    private class Node<E> {
        private E value;
        private Node left, right;

        Node(E value) {
            this.value = value;
        }
    }

    public void add(E value) {
        if (mainRoot == null) {
            mainRoot = new Node<>(value);
        } else {
            add(value, mainRoot);
        }
    }

    private Node<E> add(E value, Node<E> root) {
        if (root == null) {
            root = new Node<>(value);
        } else if (root.value.compareTo(value) >= 0) {
            root.left = add(value, root.left);
        } else {
            root.right = add(value, root.right);
        }
        return root;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Deque<Node<E>> stack = new LinkedList<>();
            private Node<E> current = mainRoot;

            @Override
            public boolean hasNext() {
                return (!stack.isEmpty() || current != null);
            }

            @Override
            public E next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                Node<E> node = current;
                current = current.right;
                return node.value;
            }
        };
    }
}
