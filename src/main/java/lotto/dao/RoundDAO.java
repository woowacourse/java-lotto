package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO {
    private final Connection conn;

    public RoundDAO(Connection conn) {
        this.conn = conn;
    }

    public int getRound() throws SQLException {
        String query = "SELECT MAX(round) FROM lotto;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return 1;
        }
        return rs.getInt(1) + 1;
    }
}
