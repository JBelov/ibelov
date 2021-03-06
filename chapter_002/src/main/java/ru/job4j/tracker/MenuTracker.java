package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 04.05.2018
 */

/**
 * Внешний класс для редактирования элемента.
 */
class EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    private final static String NL = System.lineSeparator();

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
}

/**
 * Внешний класс для удаления элемента.
 */
class DeleteItem extends BaseAction {

    public DeleteItem(int key, String name) {
        super(key, name);
    }

    private final static String NL = System.lineSeparator();

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
}

/**
 * Внешний класс для поиска элемента по ID.
 */
class FindItemById extends BaseAction {

    public FindItemById(int key, String name) {
        super(key, name);
    }

    private final static String NL = System.lineSeparator();

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
}

/**
 * Внешний класс для поиска элемента по имени.
 */
class FindItemByName extends BaseAction {

    public FindItemByName(int key, String name) {
        super(key, name);
    }

    private final static String NL = System.lineSeparator();

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
}

/**
 * Основной класс реализующий меню.
 */
public class MenuTracker {

    private final static String NL = System.lineSeparator();

    private Input input;
    private Tracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Инициализация элементов меню внутренними классами.
     */
    public void fillActions() {
        this.actions.add(0, new AddItem(0, "Add the new item."));
        this.actions.add(1, new MenuTracker.ShowItems(1, "Show all items."));
        this.actions.add(2, new EditItem(2, "Edit item."));
        this.actions.add(3, new DeleteItem(3, "Delete item."));
        this.actions.add(4, new FindItemById(4, "Find item by ID."));
        this.actions.add(5, new FindItemByName(5, "Find item by name."));
        this.actions.add(6, new ExitProgram(6, "Exit Program."));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
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
    private class AddItem extends BaseAction {

        private AddItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Внутренний класс для выхода из программы.
     */
    private class ExitProgram extends BaseAction {

        private ExitProgram(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
        }
    }

    /**
     * Внутренний класс реализующий отображение всех элементов.
     */
    private static class ShowItems extends BaseAction {

        private ShowItems(int key, String name) {
            super(key, name);
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
    }
}

