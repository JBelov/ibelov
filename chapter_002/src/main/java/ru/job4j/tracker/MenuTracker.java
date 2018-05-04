package ru.job4j.tracker;

import java.util.Date;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 04.05.2018
 */

/**
 * Внешний класс для редактирования элемента.
 */
class EditItem implements UserAction {

    private final static String NL = System.lineSeparator();

    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println(NL + "------------ Редактирование новой заявки --------------");
        String id = input.ask("Введите id заявки для редактирования: ");
        if (tracker.findById(id) != null) {
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.replace(id, item);
            System.out.println("-------- Отредактирована Заявка (ID: " + item.getId() + ")----------");
            Date date = new Date(item.getCreated());
            System.out.println("Время обновления: " + date);
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDesc() + NL);
        } else {
            System.out.println("Ошибка: не верный id" + NL);
        }
    }

    public String info() {
        return String.format("%s. %s", key(), "Edit item.");
    }
}

/**
 * Внешний класс для удаления элемента.
 */
class DeleteItem implements UserAction {

    private final static String NL = System.lineSeparator();

    public int key() {
        return 3;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println(NL + "-------------- Удаление заявки --------------");
        String id = input.ask("Введите id заявки для удаления: ");
        if (tracker.findById(id) != null) {
            tracker.delete(id);
            System.out.println("---- Заявка (ID: " + id + ") удалена ----" + NL);
        } else {
            System.out.println("Ошибка: не верный id" + NL);
        }
    }

    public String info() {
        return String.format("%s. %s", key(), "Delete item.");
    }
}

/**
 * Внешний класс для поиска элемента по ID.
 */
class FindItemById implements UserAction {

    private final static String NL = System.lineSeparator();

    public int key() {
        return 4;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println(NL + "------------ Поиск заявки по id --------------");
        String id = input.ask("Введите id заявки: ");
        if (tracker.findById(id) != null) {
            System.out.println(NL + "------------ Заявка (ID: " + id + ")----------");
            Date date = new Date(tracker.findById(id).getCreated());
            System.out.println("Время создания: " + date);
            System.out.println("Имя заявки: " + tracker.findById(id).getName());
            System.out.println("Описание заявки: " + tracker.findById(id).getDesc() + NL);
        } else {
            System.out.println("Ошибка: не верный id" + NL);
        }
    }

    public String info() {
        return String.format("%s. %s", key(), "Find item by ID.");
    }
}

/**
 * Внешний класс для поиска элемента по имени.
 */
class FindItemByName implements UserAction {

    private final static String NL = System.lineSeparator();

    public int key() {
        return 5;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println(NL + "------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя заявки: ");
        if (tracker.findByName(name) != null) {
            System.out.println(NL + "------------ Заявка (ID: " + tracker.findByName(name).getId() + ")----------");
            Date date = new Date(tracker.findByName(name).getCreated());
            System.out.println("Время создания: " + date);
            System.out.println("Имя заявки: " + tracker.findByName(name).getName());
            System.out.println("Описание заявки: " + tracker.findByName(name).getDesc() + NL);
        } else {
            System.out.println("Ошибка: нет заявок с таким именем" + NL);
        }
    }

    public String info() {
        return String.format("%s. %s", key(), "Find item by name.");
    }
}

/**
 * Основной класс реализующий меню.
 */
public class MenuTracker {

    private final static String NL = System.lineSeparator();

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Инициализация элементов меню внутренними классами.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new ExitProgram();

    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Внутренний класс реализующий добавление элемента.
     */
    private class AddItem implements UserAction {

        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(NL + "------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println(NL + "-------- Новая Заявка (ID: " + item.getId() + ")----------");
            Date date = new Date(item.getCreated());
            System.out.println("Время создания: " + date);
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDesc() + NL);

        }

        public String info() {
            return String.format("%s. %s", key(), "Add the new item.");
        }
    }

    /**
     * Внутренний класс для выхода из программы.
     */
    private class ExitProgram implements UserAction {

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
        }

        public String info() {
            return String.format("%s. %s", key(), "Exit Program.");
        }
    }

    /**
     * Внутренний класс реализующий отображение всех элементов.
     */
    private static class ShowItems implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                System.out.println(NL + "------------ Заявка (ID: " + item.getId() + ")----------");
                Date date = new Date(item.getCreated());
                System.out.println("Время создания: " + date);
                System.out.println("Имя заявки: " + item.getName());
                System.out.println("Описание заявки: " + item.getDesc() + NL);

            }
        }

        public String info() {
            return String.format("%s. %s", key(), "Show all items.");
        }
    }
}

