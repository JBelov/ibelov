package sqlru;

import jdbc.StoreSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class DataBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class);
    private String dbName;
    private String url;
    private String user;
    private String password;
    private String createDB;
    private String createTable;
    private String checkDB;
    private String getAll;

    /**
     * Constructor.
     **/
    DataBase() {
        ResourceBundle resource = App.getResource();
        url = resource.getString("url");
        user = resource.getString("user");
        password = resource.getString("password");
        createDB = resource.getString("createDB");
        createTable = resource.getString("createTable");
        checkDB = resource.getString("checkDB");
        dbName = resource.getString("dbName");
        getAll = resource.getString("getAll");

        checkOrCreateBD();
        init();
    }

    /**
     * Adds list of elements in the database.
     *
     * @param items items to add.
     */
    void add(List<Item> items) {
        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
             PreparedStatement stat = conn.prepareStatement(
                     "INSERT INTO items(id, url, description, created)VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING")) {
            conn.setAutoCommit(false);
            for (Item item : items
                    ) {
                stat.setInt(1, item.getId());
                stat.setString(2, item.getUrl());
                stat.setString(3, item.getSubject());
                stat.setTimestamp(4, item.getCreated());
                stat.execute();
            }
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Get the last parsed post time.
     *
     * @return Timestamp of the last post.
     */
    Timestamp getLast() {
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        Timestamp result = Timestamp.valueOf(firstDay.atStartOfDay());
        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
             PreparedStatement stat = conn.prepareStatement(
                     "SELECT MAX(created) FROM items;")) {
            ResultSet resultSet = stat.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getTimestamp(1) == null ? result : resultSet.getTimestamp(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
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
     * Tracker initialization. Creates Items table in DB if it not exists.
     */
    private void init() {
        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
             PreparedStatement stat = conn.prepareStatement(createTable)) {
            stat.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
