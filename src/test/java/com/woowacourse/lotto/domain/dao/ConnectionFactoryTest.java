package com.woowacourse.lotto.domain.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionFactoryTest {
    @Test
    void connect() {
        Connection conn = ConnectionFactory.getConnection();
        assertThat(conn).isNotNull();
    }
}
