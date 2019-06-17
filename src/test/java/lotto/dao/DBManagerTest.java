package lotto.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBManagerTest {

    @Test
    public void connection() {
        Connection conn = DBManager.getConnection();
        assertNotNull(conn);
    }
}