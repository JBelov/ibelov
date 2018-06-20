package ru.job4j.tree;


import java.util.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E root) {
        this.root = new Node(root);
    }

    public ArrayList<E> toList() {
        ArrayList<E> list = new ArrayList<>();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            list.add(el.getValue());
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return list;
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            int countLeaves = 0;
            for (Node<E> child : el.leaves()) {
                data.offer(child);
                countLeaves++;
            }
            if (countLeaves > 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(parent).isPresent()) {
            findBy(parent).get().add(new Node(child));
            return true;
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return this.toList().iterator();
    }
}
