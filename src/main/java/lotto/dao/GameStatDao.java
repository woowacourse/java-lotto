package lotto.dao;

import lotto.dto.GameStatDto;
import lotto.domain.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GameStatDao {
    private final static double DEFAULT_PROFIT_RATE = 0.0;
    private static final GameStatDao INSTANCE = new GameStatDao();

    private GameStatDao() {

    }

    public static GameStatDao getInstance() {
        return INSTANCE;
    }

    public void add(final GameStatDto gameStatDto, final int turn) {
        Connection conn = DBManager.getConnection();
        try {
            String query = "insert into stat(turn, first, second, third, fourth, fifth, miss, profit) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, turn);
            pstmt.setInt(2, gameStatDto.getCount(Rank.FIRST));
            pstmt.setInt(3, gameStatDto.getCount(Rank.SECOND));
            pstmt.setInt(4, gameStatDto.getCount(Rank.THIRD));
            pstmt.setInt(5, gameStatDto.getCount(Rank.FOURTH));
            pstmt.setInt(6, gameStatDto.getCount(Rank.FIFTH));
            pstmt.setInt(7, gameStatDto.getCount(Rank.MISS));
            pstmt.setDouble(8, gameStatDto.getProfit());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public GameStatDto findByTurn(final int turn) {
        Connection conn = DBManager.getConnection();
        Map<Rank, Integer> map = new HashMap<Rank, Integer>();
        try {
            String query = "SELECT first, second, third, fourth, fifth, miss, profit FROM stat WHERE turn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, turn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                map.put(Rank.FIRST, rs.getInt(1));
                map.put(Rank.SECOND, rs.getInt(2));
                map.put(Rank.THIRD, rs.getInt(3));
                map.put(Rank.FOURTH, rs.getInt(4));
                map.put(Rank.FIFTH, rs.getInt(5));
                map.put(Rank.MISS, rs.getInt(6));
                return GameStatDto.of(map, rs.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
        return GameStatDto.of(map, DEFAULT_PROFIT_RATE);
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
