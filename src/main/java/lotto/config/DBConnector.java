package lotto.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private final DataSource dataSource;

    public DBConnector(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {

        try {
            Class.forName(dataSource.getDriverClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(dataSource.getUrl(), dataSource.getProperties());
    }
}
