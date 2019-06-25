package lotto.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoundDAO {
    private final Connection connection;

    public RoundDAO(final Connection connection) {
        this.connection = connection;
    }

    public void addRound(int round) throws SQLException {
        String query = "INSERT INTO round VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }

    public int getMaxRound() throws SQLException {
        String query = "SELECT MAX(id) FROM round";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return 0;
        }
        return rs.getInt("MAX(id)");
    }

    public void deleteRound(int round) throws SQLException {
        String query = "DELETE FROM round WHERE id=?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, String.valueOf(round));
        pstmt.executeUpdate();
    }

    public void deleteRoundAll() throws SQLException {
        String query = "DELETE FROM round";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }
}