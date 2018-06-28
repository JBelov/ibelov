package ru.job4j.exchange;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class ExchangeTest {

    @Test
    public void whenExhangeTestIsOk() {
        Exchange nyse = new Exchange();
        nyse.add(new Order("001", "MSFT", "BUY", 91f, 200));
        nyse.add(new Order("002", "MSFT", "BUY", 91f, 300));
        nyse.add(new Order("003", "MSFT", "BUY", 92f, 100));
        assertThat(
                nyse.add(new Order("003", "MSFT", "BUY", 93f, 5000)),
                is(false));
        nyse.add(new Order("004", "MSFT", "SELL", 96f, 600));
        nyse.add(new Order("005", "MSFT", "SELL", 97f, 700));
        nyse.add(new Order("006", "MSFT", "SELL", 98f, 400));
        assertThat(nyse.remove("006"), is(true));
        assertThat(nyse.remove("006"), is(false));
        nyse.add(new Order("007", "AAPL", "BUY", 184f, 400));
        nyse.add(new Order("008", "AAPL", "BUY", 185f, 700));
        nyse.add(new Order("009", "AAPL", "BUY", 183f, 1200));
        nyse.add(new Order("010", "AAPL", "SELL", 191f, 500));
        nyse.add(new Order("011", "AAPL", "SELL", 180f, 600));
        nyse.add(new Order("012", "AAPL", "SELL", 192f, 800));
        nyse.add(new Order("013", "GOOG", "BUY", 1184f, 400));
        nyse.add(new Order("014", "GOOG", "BUY", 1185f, 700));
        nyse.add(new Order("015", "GOOG", "BUY", 1183f, 1200));
        nyse.add(new Order("016", "GOOG", "SELL", 1191f, 500));
        nyse.add(new Order("017", "GOOG", "SELL", 1000f, 6000));
        nyse.add(new Order("018", "GOOG", "SELL", 1192f, 800));
        assertThat(
                nyse.getBook("MSFT").toString(),
                is(
                        "------MSFT------\n"
                                + "97.0  700\n"
                                + "96.0  600\n"
                                + "        100  92.0\n"
                                + "        500  91.0\n"
                ));
        assertThat(
                nyse.getBook("AAPL").toString(),
                is(
                        "------AAPL------\n"
                                + "192.0  800\n"
                                + "191.0  500\n"
                                + "        100  185.0\n"
                                + "        400  184.0\n"
                                + "        1200  183.0\n"
                ));
        assertThat(
                nyse.getBook("GOOG").toString(),
                is(
                        "------GOOG------\n"
                                + "1192.0  800\n"
                                + "1191.0  500\n"
                                + "1000.0  3700\n"
                ));
        System.out.println(nyse.getBook("MSFT"));
        System.out.println(nyse.getBook("AAPL"));
        System.out.println(nyse.getBook("GOOG"));
    }
}