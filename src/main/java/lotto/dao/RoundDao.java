package lotto.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDao {
    private static final String INSERT_ROUND_AND_PRICE_SQL = "INSERT INTO round(id_,price) values(?,?)";
    private static final String SELECT_LAST_ROUND_SQL = "SELECT MAX(id_) FROM round";
    private static final String SELECT_ALL_ROUND_SQL = "SELECT id_ FROM round";
    private static final String SELECT_PRICE_BY_ROUND_SQL = "SELECT price FROM round WHERE id_=?";
    private static final String DELETE_ALL_ROUND_AND_PRICE_SQL = "DELETE FROM round";

    private final Connection conn;

    public RoundDao(final Connection conn) {
        this.conn = conn;
    }

    public void addRound(int round, int price) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_ROUND_AND_PRICE_SQL);
        pstmt.setInt(1, round);
        pstmt.setInt(2, price);

        pstmt.executeUpdate();
    }

    public int findLatestRound() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_LAST_ROUND_SQL);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;
        return rs.getInt("MAX(id_)");
    }

    public List<Integer> findAllRound() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ROUND_SQL);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> rounds = new ArrayList<>();

        while (rs.next()) {
            rounds.add(rs.getInt("id_"));
        }
        return rounds;
    }

    public int findPriceByRound(int round) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_PRICE_BY_ROUND_SQL);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("price");
        }
        return 0;
    }

    public void deleteAllRound() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(DELETE_ALL_ROUND_AND_PRICE_SQL);
        pstmt.executeUpdate();
    }
}
