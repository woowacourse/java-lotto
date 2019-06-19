package lotto.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoJDBCTemplate {
    private static LottoJDBCTemplate lottoJDBCTemplate = null;

    private LottoJDBCTemplate() {
    }

    public static LottoJDBCTemplate getInstance() {
        if (lottoJDBCTemplate == null) {
            lottoJDBCTemplate = new LottoJDBCTemplate();
        }
        return lottoJDBCTemplate;
    }

    public void executeUpdate(String query) {
        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(String query, List<Object> queryValues) {
        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = createPreparedStatement(connection, query, queryValues)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, String query, List<Object> queryValues) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (int index = 0; index < queryValues.size(); index++) {
                pstmt.setObject(index + 1, queryValues.get(index));
            }
            return pstmt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public List<Map<String, Object>> executeQuery(String query) {
        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            return makeResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public List<Map<String, Object>> executeQuery(String query, List<Object> queryValues) {
        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = createPreparedStatement(connection, query, queryValues);
             ResultSet rs = pstmt.executeQuery()) {

            return makeResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
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
}
