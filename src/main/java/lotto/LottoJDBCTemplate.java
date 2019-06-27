package lotto;

import lotto.domain.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoJDBCTemplate {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class LottoJDBCTemplateHolder {
        private static final LottoJDBCTemplate instance = new LottoJDBCTemplate();
    }

    public static LottoJDBCTemplate getInstance() {
        return LottoJDBCTemplateHolder.instance;
    }

    private LottoJDBCTemplate(){}

    public int executeUpdate(String query, List<Object> queryValues) {
        int result;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = createPreparedStatement(con, query, queryValues)) {
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid Execute Update");
        }
        return result;
    }

    public List<Map<String, Object>> executeQuery(String query) {
        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            return makeResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Invalid Execute Query");
        }
    }

    public List<Map<String, Object>> executeQuery(String query, List<Object> queryValues) {
        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = createPreparedStatement(con, query, queryValues);
             ResultSet rs = pstmt.executeQuery()) {

            return makeResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Invalid Execute Query");
        }
    }

    private List<Map<String, Object>> makeResults(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> resultRow = makeResultRow(rs);
            results.add(resultRow);
        }
        return results;
    }

    private Map<String, Object> makeResultRow(ResultSet rs) throws SQLException {
        Map<String, Object> resultRow = new HashMap<>();

        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int index = 1; index <= rsMetaData.getColumnCount(); index++) {
            String columnName = rsMetaData.getColumnName(index);
            resultRow.put(columnName, rs.getObject(columnName));
        }
        return resultRow;
    }

    private PreparedStatement createPreparedStatement(Connection connection, String query, List<Object> queryValues) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (int index = 0; index < queryValues.size(); index++) {
            pstmt.setObject(index + 1, queryValues.get(index));
        }
        return pstmt;
    }
}
