package jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class);
    private ResourceBundle resource;
    private String dbname;
    private String url;
    private String user;
    private String password;
    private String createData;
    private String createTable;
    private String clearTable;
    private String getAll;
    private Connection conn;

    /**
     * Create BD connection. DB creation.
     *
     * @param config
     */
    public StoreSQL(String config) throws SQLException {

        resource = ResourceBundle.getBundle(config);
        dbname = resource.getString("dbname");
        url = resource.getString("url");
        user = resource.getString("user");
        password = resource.getString("password");
        createData = resource.getString("createData");
        createTable = resource.getString("createTable");
        clearTable = resource.getString("clearTable");
        getAll = resource.getString("getAll");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        conn = DriverManager.getConnection(url + dbname, user, password);
        conn.setAutoCommit(false);
        init();
        clear();
    }

    /**
     * Table creation.
     */
    private void init() {
        try (PreparedStatement stat = conn.prepareStatement(createTable)) {
            stat.execute();
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Clear table. Remove all items.
     */
    private void clear() {
        try (PreparedStatement stat = conn.prepareStatement(clearTable)) {
            stat.execute();
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Generate entries in DB.
     *
     * @param n number of entries to generate.
     */
    public void generate(int n) {
        try (PreparedStatement statement = conn.prepareStatement(createData)) {
            for (int i = 1; i <= n; i++) {
                statement.setInt(1, i);
                statement.execute();
            }
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List<Entry> getList() {
        List<Entry> list = new LinkedList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(getAll)) {
            while (resultSet.next()) {
                list.add(new Entry(resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}
