package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 26.04.2018
 */

public class Tracker implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    private static final Random RN = new Random(100);

    private final ResourceBundle resource = ResourceBundle.getBundle("tracker");
    private final String url = resource.getString("url");
    private final String user = resource.getString("user");
    private final String password = resource.getString("password");
    private final String checkDB = resource.getString("checkDB");
    private final String createDB = resource.getString("createDB");
    private final String createTable = resource.getString("createTable");
    private final String clearTable = resource.getString("clearTable");


    private Connection conn;
    private PreparedStatement stat;
    private ResultSet resultSet;


    /**
     * Main tracker class that implements DB connection, items creation/modification e.t.c.
     *
     * @throws SQLException
     */
    Tracker() throws SQLException {
        checkOrCreateBD();
        conn = DriverManager.getConnection(url + "tracker", user, password);
        init();
    }

    /**
     * Tracker initialization. Creates Items table in DB if it not exists.
     */
    private void init() {
        try (PreparedStatement stat = conn.prepareStatement(createTable)) {
            stat.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Checks DB existence. Or create new DB in case of no ready DB.
     */
    private void checkOrCreateBD() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(checkDB);
            resultSet.next();
            if (!resultSet.getBoolean(1)) {
                statement.execute(createDB);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Clears all the data from Items table. It useful in case of automatic testing.
     */
    public void clearAll() {
        try (PreparedStatement stat = conn.prepareStatement(clearTable)) {
            stat.execute();
            init();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Adds new element in tracker.
     *
     * @param item item to be added.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        try (PreparedStatement stat = conn.prepareStatement(
                "INSERT INTO items(id, name, description, created) VALUES (?, ?, ?, ?)")) {
            stat.setString(1, item.getId());
            stat.setString(2, item.getName());
            stat.setString(3, item.getDesc());
            stat.setLong(4, item.getCreated());
            stat.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return item;
    }


    /**
     * Generates new ID using random and timestamp.
     *
     * @return new id.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }


    /**
     * Replace Item in the Tracker.
     *
     * @param id   id of item to be replaced.
     * @param item item to replace it.
     */
    public void replace(String id, Item item) {
        try (PreparedStatement stat = conn.prepareStatement(
                "UPDATE items SET name = ?, description = ?, created = ? WHERE id = ?")) {
            stat.setString(4, id);
            stat.setString(1, item.getName());
            stat.setString(2, item.getDesc());
            stat.setLong(3, item.getCreated());
            stat.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes item from the tracker.
     *
     * @param id of item to be deleted.
     */
    public void delete(String id) {
        try (PreparedStatement stat = conn.prepareStatement(
                "DELETE FROM items WHERE id = ?")) {
            stat.setString(1, id);
            stat.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Finds item by it's id.
     *
     * @param id item to find.
     * @return Item if exists, else null.
     */
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement stat = conn.prepareStatement(
                "SELECT * FROM items WHERE id = ?")) {
            stat.setString(1, id);
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                result = new Item(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("created"));
                result.setId(id);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds item by it's name.
     *
     * @param name of item to find.
     * @return Item if exists, else null.
     */
    public Item findByName(String name) {
        Item result = null;
        try (PreparedStatement stat = conn.prepareStatement(
                "SELECT * FROM items WHERE name = ?")) {
            stat.setString(1, name);
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                result = new Item(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("created"));
                result.setId(resultSet.getString("id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Get all Items from tracker.
     *
     * @return List of all items.
     */
    public ArrayList<Item> getAll() {
        ArrayList<Item> result = new ArrayList<>();
        try (PreparedStatement stat = conn.prepareStatement(
                "SELECT * FROM items")) {
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("created"));
                item.setId(resultSet.getString("id"));
                result.add(item);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        conn.close();
        System.out.println("GOOD BYE!");
    }
}