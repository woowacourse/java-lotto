package lotto.domain.dao;

import java.sql.*;

public class RoundDao extends Connector {
    public int addNextRound() throws SQLException {
        String query = "INSERT INTO round VALUE (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        int addedRound = getMaxRound() + 1;
        pstmt.setInt(1, addedRound);
        pstmt.executeUpdate();
        return addedRound;
    }

    public int getMaxRound() throws SQLException {
        String query = "SELECT max(round) FROM round";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery(query);

        if (!rs.next()) return -1;

        return rs.getInt("max(round)");
    }
}
