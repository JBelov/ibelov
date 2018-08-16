package ru.job4j.tracker;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 28.04.2018
 */

public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    private int[] range = new int[]{0, 1, 2, 3, 4, 5, 6};


    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int key;
        do {
            menu.show();
            key = (input.ask("Select: ", range));
            menu.select(key);
        } while (key != 6);
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        try (Tracker tracker = new Tracker()) {
            new StartUI(
                    new ValidateInput(
                            new ConsoleInput()
                    ),
                    tracker
            ).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
