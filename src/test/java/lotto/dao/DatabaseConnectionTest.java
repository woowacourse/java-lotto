package lotto.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    void 연결_테스트() {
        Connection con = DatabaseConnection.getConnection();
        assertNotNull(con);
    }
}
