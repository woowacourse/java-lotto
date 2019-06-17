package lotto.application;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoJDBCDriverConnectorTest {
    @Test
    void connection() {
        Connection con = LottoJDBCDriverConnector.getConnection();
        assertNotNull(con);
    }
}