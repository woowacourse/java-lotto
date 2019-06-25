package lotto.dao.JdbcTemplate;

import lotto.dao.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class JdbcTemplate {
    public static void query(String sql, Map<String, ?> rowMapper, PreparedStatementProcessor preparedStatementProcessor) throws SQLException {
        NamedSqlMapper namedSqlMapper = NamedSqlMapper.mapSQL(sql);
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(namedSqlMapper.getMappedSQL(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameter(preparedStatement, rowMapper, namedSqlMapper.getIndexMap());
            preparedStatementProcessor.process(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setPreparedStatementParameter(PreparedStatement preparedStatement, Map<String, ?> rowMapper, Map<Integer, String> indexMap) {
        indexMap.keySet().forEach(index -> {
            try {
                preparedStatement.setObject(index, rowMapper.get(indexMap.get(index)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
