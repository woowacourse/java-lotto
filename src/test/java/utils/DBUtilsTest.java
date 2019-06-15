package utils;

import lotto.utils.DBUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBUtilsTest {
    @Test
    void connect() {
        Connection conn = DBUtils.getConnection();
        assertNotNull(conn);
    }
}
