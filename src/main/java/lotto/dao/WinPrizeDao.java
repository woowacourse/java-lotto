package lotto.dao;

import lotto.domain.Rank;
import lotto.domain.WinPrize;
import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinPrizeDao {
    public int add(WinPrize winPrize, int round) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "INSERT INTO win_prize (round, first, second, third, fourth, fifth, miss) VALUES(?, ?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, round);
            ps.setInt(2, winPrize.getWinCount(Rank.FIRST));
            ps.setInt(3, winPrize.getWinCount(Rank.SECOND));
            ps.setInt(4, winPrize.getWinCount(Rank.THIRD));
            ps.setInt(5, winPrize.getWinCount(Rank.FOURTH));
            ps.setInt(6, winPrize.getWinCount(Rank.FIFTH));
            ps.setInt(7, winPrize.getWinCount(Rank.MISS));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return result;
    }

    public WinPrize findByRound(int round) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WinPrize winPrize = new WinPrize();

        try {
            String sql = "SELECT * FROM win_prize WHERE round = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, round);
            rs = ps.executeQuery();

            if (rs.next()) {
                winPrize.put(Rank.FIRST, rs.getInt("first"));
                winPrize.put(Rank.SECOND, rs.getInt("second"));
                winPrize.put(Rank.THIRD, rs.getInt("third"));
                winPrize.put(Rank.FOURTH, rs.getInt("fourth"));
                winPrize.put(Rank.FIFTH, rs.getInt("fifth"));
                winPrize.put(Rank.MISS, rs.getInt("miss"));
            }
            return winPrize;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return null;
    }
}
