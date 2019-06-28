package lotto.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JdbcConnectorTest {
    private JdbcConnector userDao;

    @BeforeEach
    public void setup() {
        userDao = new JdbcConnector();
    }

    @Test
    public void connection() {
        Connection con = JdbcConnector.getConnection();
        assertNotNull(con);
    }

}