package lotto.dbconnction;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBConnectionTest {
    @Test
    void connection() {
        Connection con = DBConnection.getConnection();
        assertNotNull(con);

        DBConnection.closeConnection(con);
    }
}
