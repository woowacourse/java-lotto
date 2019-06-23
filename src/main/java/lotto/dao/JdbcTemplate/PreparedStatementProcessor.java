package lotto.dao.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementProcessor {
    void process(PreparedStatement preparedStatement) throws SQLException;
}
