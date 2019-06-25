package lotto.utils;

import org.junit.jupiter.api.Test;

public class DBUtilsTest {
    @Test
    void DB_연결() {
        DBUtils.getConnection();
    }

    @Test
    void DB_종료() {
        DBUtils.closeConnection(DBUtils.getConnection());
    }
}
