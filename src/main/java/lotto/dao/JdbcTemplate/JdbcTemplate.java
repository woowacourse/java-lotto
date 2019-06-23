package lotto.dao.JdbcTemplate;

import lotto.dao.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class JdbcTemplate {
    public static void query(String sql, Map<String, ?> rowMapper, PreparedStatementProcessor preparedStatementProcessor) throws SQLException {
        NamedSqlMapper namedSqlMapper = NamedSqlMapper.mapSql(sql, rowMapper);
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(namedSqlMapper.getMappedSql(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            Map<Integer, String> indexMap = namedSqlMapper.getIndexMap();
            for (Integer index : indexMap.keySet()) {
                preparedStatement.setObject(index, rowMapper.get(indexMap.get(index)));
            }

            preparedStatementProcessor.process(preparedStatement);
        } catch (SQLException e) {
            throw e;
        }
    }
}
