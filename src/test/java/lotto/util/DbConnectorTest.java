package lotto.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DbConnectorTest {
    @Test
    public void connection() {
        Connection con = DbConnector.getConnection();
        assertNotNull(con);
    }

    @Test
    public void single_connection() {
        Connection con = DbConnector.getConnection();
        assertThat(con == DbConnector.getConnection()).isTrue();
    }

    @Test
    void close_connection() {
        DbConnector.getConnection();
        DbConnector.closeConnection();
        assertThat(DbConnector.isClosed()).isTrue();
    }
}