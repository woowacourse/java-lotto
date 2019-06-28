package lotto.dao;

import java.sql.*;
import java.util.*;

public class QueryManager {
    private static QueryManager manager;

    private QueryManager() {
    }

    public static QueryManager getManager() {
        if (Objects.isNull(manager)) {
            manager = new QueryManager();
            return manager;
        }
        return manager;
    }

    public static int lastRound() {
        String sql = "SELECT MAX(round) FROM lotto_game";
        ResultSet rs;
        try (PreparedStatement stmt = DBManager.getConnection().prepareStatement(sql)) {
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public void executeUpdate(String query, List<Integer> queryValues) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement statement = createPreparedStatement(conn, query, queryValues)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Map<String, Integer>> executeQuery(String query) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            return createResult(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Map<String, Integer>> executeQuery(String query, List<Integer> queryValues) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement statement = createPreparedStatement(conn, query, queryValues);
             ResultSet rs = statement.executeQuery()) {
            return createResult(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    private List<Map<String, Integer>> createResult(ResultSet rs) throws SQLException {
        List<Map<String, Integer>> endResult = new ArrayList<>();

        while (rs.next()) {
            Map<String, Integer> result = offerResult(rs);
            endResult.add(result);
        }

        return endResult;
    }

    private Map<String, Integer> offerResult(ResultSet rs) throws SQLException {
        Map<String, Integer> result = new HashMap<>();

        ResultSetMetaData reMetaData = rs.getMetaData();

        for (int i = 1; i <= reMetaData.getColumnCount(); i++) {
            String columnName = reMetaData.getColumnName(i);
            result.put(columnName, rs.getInt(columnName));
        }

        return result;
    }

    private PreparedStatement createPreparedStatement(Connection conn, String query, List<Integer> queryValues) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(query);

        for (int i = 0; i < queryValues.size(); i++) {
            statement.setInt(i + 1, queryValues.get(i));
        }

        return statement;
    }
}