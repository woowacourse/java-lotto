package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO {
    private static final String INSERT_SQL = "INSERT INTO round(id_) values(?)";
    private static final String SELECT_LAST_ROUND_SQL = "SELECT MAX(id_) FROM round";

    private final Connection conn;

    public RoundDAO(final Connection conn) {
        this.conn = conn;
    }

    public void addRound(int round) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
        pstmt.setInt(1, round);

        pstmt.executeUpdate();
    }

    public int findLatestRound() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_LAST_ROUND_SQL);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) return 0;
        return rs.getInt("id_");
    }

}
