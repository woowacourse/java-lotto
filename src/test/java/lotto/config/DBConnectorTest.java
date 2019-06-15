package lotto.config;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBConnectorTest {
    DataSource dataSource = DataSource.getInstance();
    DBConnector dbConnector = new DBConnector(dataSource);

    @Test
    void getConnectionTest() throws SQLException {
        assertNotNull(dbConnector.getConnection());
    }
}