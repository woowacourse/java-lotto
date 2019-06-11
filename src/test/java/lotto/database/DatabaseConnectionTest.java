package lotto.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {
    @Test
    void 드라이버_연결이_제대로_되는지_확인() {
        Connection con = DatabaseConnection.getConnection();
        assertNotNull(con);
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.closeConnection();
    }
}
