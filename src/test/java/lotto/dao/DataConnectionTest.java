package lotto.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataConnectionTest {

    @Test
    void 연결_테스트() {
        Connection con = DataConnection.getConnection();
        assertNotNull(con);
    }
}
