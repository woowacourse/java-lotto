package lotto.dao;

import lotto.config.DBConnector;
import lotto.domain.Rank;
import lotto.domain.WinPrize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinPrizeDao {
    private DBConnector dbConnector;

    public WinPrizeDao(final DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void save(WinPrize winPrize, int round) {
        String sql = "INSERT INTO win_prize (round, first, second, third, fourth, fifth, miss) VALUES(?, ?,?,?,?,?,?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, round);
            ps.setInt(2, winPrize.getWinCount(Rank.FIRST));
            ps.setInt(3, winPrize.getWinCount(Rank.SECOND));
            ps.setInt(4, winPrize.getWinCount(Rank.THIRD));
            ps.setInt(5, winPrize.getWinCount(Rank.FOURTH));
            ps.setInt(6, winPrize.getWinCount(Rank.FIFTH));
            ps.setInt(7, winPrize.getWinCount(Rank.MISS));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WinPrize findAllByRound(int round) {
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, round);
             ResultSet rs = ps.executeQuery()) {

            WinPrize winPrize = new WinPrize();
            if (rs.next()) {
                winPrize.put(Rank.FIRST, rs.getInt("first"));
                winPrize.put(Rank.SECOND, rs.getInt("second"));
                winPrize.put(Rank.THIRD, rs.getInt("third"));
                winPrize.put(Rank.FOURTH, rs.getInt("fourth"));
                winPrize.put(Rank.FIFTH, rs.getInt("fifth"));
                winPrize.put(Rank.MISS, rs.getInt("miss"));

                return winPrize;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement createPreparedStatement(final Connection conn, final int round) throws SQLException {
        String sql = "SELECT * FROM win_prize WHERE round = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, round);
        return ps;
    }
}
