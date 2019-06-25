package lotto.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBConnectionTest {
    @Test
    void connection_테스트() {
        Connection con = DBConnection.getConnection();
        assertNotNull(con);
    }

    @Test
    void connection_close_테스트() {
        Connection con = DBConnection.getConnection();
        assertDoesNotThrow(() -> DBConnection.closeConnection(con));
    }

}