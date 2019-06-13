package lotto.dao;

import lotto.dto.StatDto;
import lotto.domain.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatDao {
    private final static double DEFAULT_PROFIT_RATE = 0.0;
    private static final StatDao INSTANCE = new StatDao();

    private StatDao() {

    }

    public static StatDao getInstance() {
        return INSTANCE;
    }

    public void add(final StatDto statDto, final int round) {
        Connection conn = DBManager.getConnection();
        try {
            String query = "insert into stat(turn, first, second, third, fourth, fifth, miss, profit) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.setInt(2, statDto.getCount(Rank.FIRST));
            pstmt.setInt(3, statDto.getCount(Rank.SECOND));
            pstmt.setInt(4, statDto.getCount(Rank.THIRD));
            pstmt.setInt(5, statDto.getCount(Rank.FOURTH));
            pstmt.setInt(6, statDto.getCount(Rank.FIFTH));
            pstmt.setInt(7, statDto.getCount(Rank.MISS));
            pstmt.setDouble(8, statDto.getProfit());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public StatDto findByRound(final int round) {
        Connection conn = DBManager.getConnection();
        Map<Rank, Integer> map = new HashMap<Rank, Integer>();
        try {
            String query = "SELECT first, second, third, fourth, fifth, miss, profit FROM stat WHERE turn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                map.put(Rank.FIRST, rs.getInt(1));
                map.put(Rank.SECOND, rs.getInt(2));
                map.put(Rank.THIRD, rs.getInt(3));
                map.put(Rank.FOURTH, rs.getInt(4));
                map.put(Rank.FIFTH, rs.getInt(5));
                map.put(Rank.MISS, rs.getInt(6));
                return StatDto.of(map, rs.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
        return StatDto.of(map, DEFAULT_PROFIT_RATE);
    }

    public void deleteAll() {
        Connection conn = DBManager.getConnection();
        try {
            String query = "delete from stat";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }
}
