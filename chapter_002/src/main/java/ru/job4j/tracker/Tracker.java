package ru.job4j.tracker;

import java.util.Random;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 26.04.2018
 */

public class Tracker {
    /**
 * Массив для хранение заявок.
 */
private final Item[] items = new Item[100];
private static final Random RN = new Random(100);

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод заменяет заявку по заданному id.
     * @param id идентификатор заявки, которую нужно заменить.
     * @param item заявка на которую заменяем.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.length; index++) {
            if (items[index].getId().equals(id)) {
                items[index] = item;
                items[index].setId(id);
                break;
            }
        }
    }

    /**
     * Метод удаляет заявку по заданному id.
     * @param id идентификатор заявки, которую удаляем.
     */
    public void delete(String id) {
        for (int index = 0; index < this.items.length; index++) {
            if (items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, items.length - index - 1);
                items[items.length - 1] = null;
                position--;
                break;
            }
        }
    }

    /**
     * Метод возвращает заявку по заданному id.
     * @param id идентификатор заявки.
     * @return возвращаемая заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод находит заявку по заданному имени.
     * @param name Имя заявки.
     * @return Возвращаемая заявка.
     */
    public Item findByName(String name) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getName().equals(name)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает массив из всех не пустых заявок.
     * @return Массив не пустых заявок.
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++){
            result[index] = this.items[index];
        }
        return result;
    }



}