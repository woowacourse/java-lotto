package lotto.domain.dao;

import lotto.domain.Rank;
import lotto.domain.dto.ResultDto;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class ResultDao extends Connector {
    public void addResult(int round, ResultDto resultDTO) throws SQLException {
        String query = "INSERT INTO lotto_result " +
                "(round, first, second, third, fourth, fifth, miss, yield, win_prize) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        int parameterIndex = 1;
        pstmt.setInt(parameterIndex++, round);
        for (Rank rank : Rank.values()) {
            pstmt.setInt(parameterIndex++, resultDTO.getWinnersCountByRank(rank));
        }
        pstmt.setDouble(parameterIndex++, resultDTO.getYield());
        pstmt.setDouble(parameterIndex, resultDTO.getWinPrize());
        pstmt.executeUpdate();
    }

    public Map<Rank, Integer> findWinnerCountByRound(int round) throws SQLException {
        String query = "SELECT FIRST,SECOND,THIRD,FOURTH,FIFTH,MISS FROM lotto_result WHERE round = ?";
        Map<Rank, Integer> winnerCount = new TreeMap<>();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        for (Rank rank : Rank.values()) {
            winnerCount.put(rank,rs.getInt(rank.name()));
        }
        return winnerCount;
    }


    public double findYieldByRound(int round) throws SQLException {
        String query = "SELECT yield FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getDouble("yield");
    }

    public long findWinPrizeByRound(int round) throws SQLException {
        String query = "SELECT win_prize FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getLong("win_prize");
    }
}
