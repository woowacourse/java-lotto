package lotto.dbconnction;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBConnectionTest {
    @Test
    void connection() {
        DBConnection dbConnection = DBConnection.getInstacne();

        Connection con = dbConnection.getConnection();
        assertNotNull(con);
    }
}
