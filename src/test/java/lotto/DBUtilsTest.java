package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilsTest {
    private DBUtils dbUtils;

    @BeforeEach
    void setUp() {
        dbUtils = new DBUtils();
    }

    @Test
    public void connection() {
        Connection con = dbUtils.getConnection();
        assertNotNull(con);
    }
}