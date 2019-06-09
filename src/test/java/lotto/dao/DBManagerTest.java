package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBManagerTest {
    private Connection con;

    @BeforeEach
    void setup() {
        con = DBManager.getConnection();
    }

    @Test
    void connection() {
        assertNotNull(con);
    }

    @Test
    void disconnection() {
        DBManager.closeConnection(con);
    }
}
