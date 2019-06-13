package lotto.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionDBTest {
    private ConnectionDB connectionDB;

    @BeforeEach
    void setUp() {
        connectionDB = new ConnectionDB();
    }

    @Test
    void DB_연결하는_테스트() {
        Connection connection = ConnectionDB.getConnection();
        assertNotNull(connection);
    }
}
