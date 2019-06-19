package lotto.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {
    @Test
    void 커넥션_연결이_제대로_되는지_확인() throws Exception {
        Connection con = DatabaseConnection.getConnection();
        assertNotNull(con);
    }
}
