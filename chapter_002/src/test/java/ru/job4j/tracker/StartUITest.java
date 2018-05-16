package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private static final String NL = System.lineSeparator();
    private static final String MENU = "0. Add the new item." + NL
            + "1. Show all items." + NL
            + "2. Edit item." + NL
            + "3. Delete item." + NL
            + "4. Find item by ID." + NL
            + "5. Find item by name." + NL
            + "6. Exit Program." + NL;


    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenValueIsNull() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем две заявки
        Item item = tracker.add(new Item("test name", "desc"));
        Item item2 = tracker.add(new Item("test name 2", "desc 2"));
        //создаём StubInput с последовательностью действий по удалению первой заявки.
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя второй заявки.
        assertThat(tracker.getAll().get(0).getName(), is("test name 2"));
    }

    @Test
    public void whenGetAllItemsTheyAreHere() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем две заявки.
        Item item = tracker.add(new Item("test name", "desc"));
        Item item2 = tracker.add(new Item("test name 2", "desc 2"));
        //создаём StubInput с последовательностью действий по отображению всех заявок.
        Input input = new StubInput(new String[]{"1", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что вывод содержит обе заявки.
        Date date1 = new Date(item.getCreated());
        Date date2 = new Date(item2.getCreated());
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        MENU
                                + NL
                                + "------------ Заявка (ID: " + item.getId() + ")----------" + NL
                                + "Время создания: " + date1 + NL
                                + "Имя заявки: test name" + NL
                                + "Описание заявки: desc" + NL
                                + NL
                                + NL
                                + "------------ Заявка (ID: " + item2.getId() + ")----------" + NL
                                + "Время создания: " + date2 + NL
                                + "Имя заявки: test name 2" + NL
                                + "Описание заявки: desc 2" + NL
                                + NL
                                + MENU
                )

        );
    }

    @Test
    public void whenFindItemByNameItFound() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку.
        Item item = tracker.add(new Item("test name", "desc"));
        //создаём StubInput с последовательностью действий по поиску заявки по имени.
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что вывод содержит заявку.
        Date date1 = new Date(item.getCreated());
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        MENU
                                + NL
                                + "------------ Поиск заявки по имени --------------" + NL
                                + NL
                                + "------------ Заявка (ID: " + item.getId() + ")----------" + NL
                                + "Время создания: " + date1 + NL
                                + "Имя заявки: test name" + NL
                                + "Описание заявки: desc" + NL
                                + NL
                                + MENU

                )

        );
    }

    @Test
    public void whenFindItemByIdItFound() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку.
        Item item = tracker.add(new Item("test name", "desc"));
        //создаём StubInput с последовательностью действий по поиску заявки по id.
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что вывод содержит заявку.
        Date date1 = new Date(item.getCreated());
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        MENU
                                + NL
                                + "------------ Поиск заявки по id --------------" + NL
                                + NL
                                + "------------ Заявка (ID: " + item.getId() + ")----------" + NL
                                + "Время создания: " + date1 + NL
                                + "Имя заявки: test name" + NL
                                + "Описание заявки: desc" + NL
                                + NL
                                + MENU

                )

        );
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "1"})
        );
        input.ask("Enter", new int[]{1});
        assertThat(
                this.out.toString(),
                is(
                        String.format("Please enter validate data again.%n")
                )
        );
    }


}
