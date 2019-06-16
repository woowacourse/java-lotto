package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.persistence.DataSourceFactory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class DataSourceFactoryTest {
    @Test
    void connect() throws SQLException {
        try (Connection conn = new DataSourceFactory().createDataSource().getConnection()) {
            assertThat(conn).isNotNull();
        }
    }
}
