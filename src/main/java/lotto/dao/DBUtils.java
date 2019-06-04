package lotto.dao;

import java.sql.*;

public class DBUtils {
    private DBUtils() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(propertiesUtil.getDbURL(), propertiesUtil.getProperties());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //TODO 이펙티브 자바 보고 예외처리 수정하기
    public static void close(Connection conn, PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        close(conn, ps);
    }
}
