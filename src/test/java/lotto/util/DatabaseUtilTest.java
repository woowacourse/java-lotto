package lotto.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseUtilTest {
        @Test
        void DB_연결_테스트() throws Exception {
                Connection conn = DatabaseUtil.getInstance().getConnection();
                assertNotNull(conn);
        }
}