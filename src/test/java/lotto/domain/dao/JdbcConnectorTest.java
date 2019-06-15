package lotto.domain.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JdbcConnectorTest {
    @Test
    void connect() {
        assertThat(JdbcConnector.getConnection()).isNotNull();
    }
}