package ru.job4j.tracker;
import java.util.Date;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 28.04.2018
 */

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа вывода всех заявок.
     */
    private static final String GETALL = "1";
    /**
     * Константа для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа для поиска заявки по ID.
     */
    private static final String FINDID = "4";
    /**
     * Константа для поиска заявки по имени.
     */
    private static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
    
    private final String NL = System.lineSeparator();

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask(NL + "Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (GETALL.equals(answer)) {
                this.getAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDID.equals(answer)) {
                this.findByIdItem();
            } else if (FINDNAME.equals(answer)) {
                this.findByNameItem();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }


    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println(NL + "------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println(NL + "-------- Новая Заявка (ID: " + item.getId() + ")----------");
        Date date = new Date(item.getCreated());
        System.out.println("Время создания: " + date);
        System.out.println("Имя заявки: " + item.getName());
        System.out.println("Описание заявки: " + item.getDesc() + NL);
    }

    /**
     * Метод реализует редактирование заявки в хранилище.
     */
    private void editItem() {
        System.out.println(NL + "------------ Редактирование новой заявки --------------");
        String id = this.input.ask("Введите id заявки для редактирования: ");
        if (this.tracker.findById(id) != null) {
            String name = this.input.ask("Введите имя заявки :");
            String desc = this.input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            this.tracker.replace(id, item);
            System.out.println("-------- Отредактирована Заявка (ID: " + item.getId() + ")----------");
            Date date = new Date(item.getCreated());
            System.out.println("Время обновления: " + date);
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDesc() + NL);
        } else {
            System.out.println("Ошибка: не верный id" + NL);
        }
    }

    /**
     * Метод реализует поиск заявки по id в хранилище.
     */
    private void findByNameItem() {
        System.out.println(NL + "------------ Поиск заявки по имени --------------");
        String name = this.input.ask("Введите имя заявки: ");
        if (this.tracker.findByName(name) != null) {
            System.out.println(NL + "------------ Заявка (ID: " + this.tracker.findByName(name).getId() + ")----------");
            Date date = new Date(this.tracker.findByName(name).getCreated());
            System.out.println("Время создания: " + date);
            System.out.println("Имя заявки: " + this.tracker.findByName(name).getName());
            System.out.println("Описание заявки: " + this.tracker.findByName(name).getDesc() + NL);
        } else {
            System.out.println("Ошибка: нет заявок с таким именем" + NL);
        }
    }

    /**
     * Метод реализует поиск заявки по имени в хранилище.
     */
    private void findByIdItem() {
        System.out.println(NL + "------------ Поиск заявки по id --------------");
        String id = this.input.ask("Введите id заявки: ");
        if (this.tracker.findById(id) != null) {
            System.out.println(NL + "------------ Заявка (ID: " + id + ")----------");
            Date date = new Date(this.tracker.findById(id).getCreated());
            System.out.println("Время создания: " + date);
            System.out.println("Имя заявки: " + this.tracker.findById(id).getName());
            System.out.println("Описание заявки: " + this.tracker.findById(id).getDesc() + NL);
        } else {
            System.out.println("Ошибка: не верный id" + NL);
        }
    }

    /**
     * Метод реализует удаление заявки в хранилище.
     */
    private void deleteItem() {
        System.out.println(NL + "-------------- Удаление заявки --------------");
        String id = this.input.ask("Введите id заявки для удаления: ");
        if (this.tracker.findById(id) != null) {
            this.tracker.delete(id);
            System.out.println("---- Заявка (ID: " + id + ") удалена ----" + NL);
        } else {
            System.out.println("Ошибка: не верный id" + NL);
        }
    }

    /**
     * Метод реализует отображение всех заявок.
     */
    private void getAllItems() {
        for (Item item : this.tracker.getAll()
             ) {
            System.out.println(NL + "------------ Заявка (ID: " + item.getId() + ")----------");
            Date date = new Date(item.getCreated());
            System.out.println("Время создания: " + date);
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDesc() + NL);

        }
    }

    private void showMenu() {
        System.out.println("MAIN MENU." + NL +
                "0. Add new Item" + NL +
                "1. Show all items" + NL +
                "2. Edit item" + NL +
                "3. Delete item" + NL +
                "4. Find item by Id" + NL +
                "5. Find items by name" + NL +
                "6. Exit Program" +
                "");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
