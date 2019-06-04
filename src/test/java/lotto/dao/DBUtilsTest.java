package lotto.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilsTest {
    @Test
    void connection() {
        assertNotNull(DBUtils.getConnection());
    }
}