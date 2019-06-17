package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDao {
    private static final RoundDao INSTANCE = new RoundDao();

    private RoundDao() {

    }

    public static RoundDao getInstance() {
        return INSTANCE;
    }

    public void add() {
        final Connection conn = DBManager.getConnection();
        final String query = "INSERT INTO round() VALUES ()";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public Integer findNext() {
        return findLast() + 1;
    }

    public Integer findLast() {
        Connection conn = DBManager.getConnection();
        String query = "SELECT id FROM round ORDER BY id DESC LIMIT 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
        return 0;
    }

    public List<Integer> findAll() {
        Connection conn = DBManager.getConnection();
        List<Integer> turns = new ArrayList<>();
        String query = "SELECT id FROM round ORDER BY id";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                turns.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
            return turns;
        }
    }

    public void deleteAll() {
        Connection conn = DBManager.getConnection();
        String query = "DELETE FROM round";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    private void initialize() {
        Connection conn = DBManager.getConnection();
        String query = "ALTER TABLE round AUTO_INCREMENT=1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }
}
