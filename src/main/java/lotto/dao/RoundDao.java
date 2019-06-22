package lotto.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoundDao {
    private static final String INSERT_ROUND_AND_PRICE_SQL = "INSERT INTO round(id,price) values(?,?)";
    private static final String SELECT_LAST_ROUND_SQL = "SELECT MAX(id) FROM round";
    private static final String SELECT_PRICE_BY_ROUND_SQL = "SELECT price FROM round WHERE id=?";
    private static final String LATEST_ROUND = "MAX(id)";
    private static final String PRICE = "price";
    private static final int ZERO_ROUND = 0;

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

        if (!rs.next()) return ZERO_ROUND;
        return rs.getInt(LATEST_ROUND);
    }

    public int findPriceByRound(int round) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_PRICE_BY_ROUND_SQL);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(PRICE);
        }
        throw new SQLException("round에 해당하는 price가 없습니다!");
    }

}
