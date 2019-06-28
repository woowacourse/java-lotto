package lotto.database.dao;

import lotto.dto.ResultDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.database.JdbcConnector.getConnection;

public class ResultDAO {
    private final int PRIZE_NUMBER = 6;

    public static ResultDAO getInstance() {
        return ResultDAOHolder.INSTANCE;
    }

    public void addResult(final ResultDTO resultDTO) throws SQLException {
        String query = "INSERT INTO result (round, miss, fifth, fourth, third, second, first,rate) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        int index = 1;
        pstmt.setInt(index++, resultDTO.getRound());
        for (int num : resultDTO.getRanks()) {
            pstmt.setInt(index++, num);
        }
        pstmt.setDouble(index, resultDTO.getWinningRate());
        pstmt.executeUpdate();
    }

    public double getLastestRate() throws SQLException {
        String query = "SELECT rate FROM result ORDER BY round DESC limit 1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }

    public List<Integer> getLastestPrize() throws SQLException {
        String query = "SELECT miss,fifth,fourth,third,second,first FROM result ORDER BY round DESC limit 1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> ranks = new ArrayList<>();
        rs.next();
        for (int i = 1; i <= PRIZE_NUMBER; i++) {
            ranks.add(rs.getInt(i));
        }
        rs.close();
        return ranks;
    }

    public List<Integer> getPrizeByRound(final int round) throws SQLException {
        String query = "SELECT miss,fifth,fourth,third,second,first FROM result WHERE round = (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> ranks = new ArrayList<>();
        rs.next();
        for (int i = 1; i <= PRIZE_NUMBER; i++) {
            ranks.add(rs.getInt(i));
        }
        rs.close();
        return ranks;
    }

    public double getWinningRateByRound(final int round) throws SQLException {
        String query = "SELECT rate FROM result WHERE round = (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }

    private static class ResultDAOHolder {
        private static final ResultDAO INSTANCE = new ResultDAO();
    }
}
