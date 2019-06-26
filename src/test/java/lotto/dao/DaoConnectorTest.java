package lotto.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DaoConnectorTest {

    @Test
    void connection() {
        assertThat(DaoConnector.getConnection()).isNotNull();
    }
}
