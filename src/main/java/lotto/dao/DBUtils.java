package lotto.dao;

import java.sql.*;

public class DBUtils {
    private DBUtils() {
    }

    static Connection getConnection() {
        Connection conn = null;
        PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
        try {
            Class.forName(propertiesUtil.getDriverClassName());
            conn = DriverManager.getConnection(propertiesUtil.getDbURL(), propertiesUtil.getProperties());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // TODO 추상화하기
    static Connection getTestConnection() {
        Connection conn = null;
        PropertiesUtil propertiesUtil = PropertiesUtil.getTestIntance();
        try {
            Class.forName(propertiesUtil.getDriverClassName());
            conn = DriverManager.getConnection(propertiesUtil.getDbURL(), propertiesUtil.getProperties());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }



    static void close(Connection conn, PreparedStatement ps) {
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

    static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
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
