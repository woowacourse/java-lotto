package lotto.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.database.JdbcConnector.getConnection;


public class RoundDAO {
    public static RoundDAO getInstance() {
        return RoundDAOHolder.INSTANCE;
    }

    public void addRound(int round) throws SQLException {
        String query = "INSERT INTO round VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }

    public int getMaxRound() throws SQLException {
        String query = "SELECT MAX(id) FROM round";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return 0;
        }
        return rs.getInt("MAX(id)");
    }

    public void deleteRound(int round) throws SQLException {
        String query = "DELETE FROM round WHERE id=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, String.valueOf(round));
        pstmt.executeUpdate();
    }

    public void deleteRoundAll() throws SQLException {
        String query = "DELETE FROM round";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    private static class RoundDAOHolder {
        private static final RoundDAO INSTANCE = new RoundDAO();
    }
}