package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseConnectionTest {
    private DatabaseConnection databaseConnection;

    @BeforeEach
    public void setUp() {
        databaseConnection = new DatabaseConnection();
    }

    @Test
    void 연결_테스트() {
        Connection connection = databaseConnection.getConnection();
        assertNotNull(connection);
    }
}