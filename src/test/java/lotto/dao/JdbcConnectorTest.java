package lotto.dao;

import lotto.dao.utils.JdbcConnector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JdbcConnectorTest {
    @Test
    void connect() {
        assertThat(JdbcConnector.getConnection()).isNotNull();
    }
}