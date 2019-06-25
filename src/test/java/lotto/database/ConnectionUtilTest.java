package lotto.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConnectionUtilTest {
    private ConnectionUtil userDao;

    @BeforeEach
    public void setup() {
        userDao = new ConnectionUtil();
    }

    @Test
    public void connection() {
        Connection con = ConnectionUtil.getConnection();
        assertNotNull(con);
    }

}