package lotto.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBUtilsTest {
    @Test
    void getConnectionTest() {
        assertNotNull(DBUtils.getConnection());
    }
}