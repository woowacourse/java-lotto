package lotto.domain.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTemplate {
    private static JDBCTemplate jdbcTemplate = new JDBCTemplate();

    private JDBCTemplate() {
    }

    public static JDBCTemplate getInstance() {
        return jdbcTemplate;
    }

    public void updateQuery(String query, List<String> args) {
        try (Connection con = Connector.getConnection()) {
            PreparedStatement pstmt = getPreparedStatement(query, args, con);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        } catch (JDBCDriverLoadException e) {
            e.printStackTrace();
        } catch (JDBCConnectException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> selectQuery(String query, List<String> args) {
        List<Map<String, String>> result = new ArrayList<>();

        try (Connection con = Connector.getConnection()) {
            PreparedStatement pstmt = getPreparedStatement(query, args, con);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }

            do {
                Map<String, String> map = new HashMap<>();
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String columnName = md.getColumnName(i);
                    map.put(columnName, rs.getString(columnName));
                }
                result.add(map);
            } while (rs.next());
            rs.close();
        } catch (Exception e) {
            throw new DataAccessException(e);
        } catch (JDBCDriverLoadException e) {
            e.printStackTrace();
        } catch (JDBCConnectException e) {
            e.printStackTrace();
        }
        return result;
    }

    private PreparedStatement getPreparedStatement(String query, List<String> args, Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(query);
        if (args == null) {
            return pstmt;
        }
        int argIdx = 1;
        for (String arg : args) {
            pstmt.setString(argIdx++, arg);
        }
        return pstmt;
    }
}
