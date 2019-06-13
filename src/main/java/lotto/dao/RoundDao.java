package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDao {
    private static final String INSERT_SQL = "INSERT INTO round(id_) values(?)";
    private static final String SELECT_LAST_ROUND_SQL = "SELECT MAX(id_) FROM round";
    private static final String SELECT_ALL_ROUND_SQL = "SELECT * FROM round";
    private static final String DELETE_ALL_ROUND_SQL = "DELETE FROM round";

    private final Connection conn;

    public RoundDao(final Connection conn) {
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

        if (rs.next()) return 1;
        return rs.getInt("id_");
    }

    public List<Integer> findAllRound() throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ROUND_SQL);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> rounds = new ArrayList<>();

        while (rs.next()){
            rounds.add(rs.getInt("id_"));
        }
        return rounds;
    }

    public void deleteAllRound() throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(DELETE_ALL_ROUND_SQL);
        pstmt.executeUpdate();
    }
}
