package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO {
    private Connection connection;

    public RoundDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer getNextRound() throws SQLException {
        String query = "SELECT lotto_round FROM round ORDER BY lotto_round DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 1;

        return Integer.parseInt(rs.getString("lotto_round")) + 1;
    }

    public Integer getCurrentRound() throws SQLException {
        String query = "SELECT lotto_round FROM round ORDER BY lotto_round DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return Integer.parseInt(rs.getString("lotto_round"));
    }

    public void addRound(String round) throws SQLException {
        String query = "INSERT INTO round VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        pstmt.executeUpdate();
    }

    public void deleteRound(String round) throws SQLException {
        String query = "DELETE FROM round WHERE lotto_round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        pstmt.executeUpdate();
    }
}
