package lotto.domain.dao;

import lotto.domain.Rank;
import lotto.domain.dto.ResultDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class ResultDao {
    private static final String INSERT_RESULT = "INSERT INTO result " +
            "(round, first, second, third, fourth, fifth, miss, yield, win_prize) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_WINNER_COUNT_BY_ID = "SELECT FIRST, SECOND, THIRD, FOURTH, FIFTH, MISS FROM result WHERE round = ?";
    private static final String SELECT_YIELD_BY_ID = "SELECT yield FROM result WHERE round = ?";
    private static final String SELECT_WIN_PRIZE_BY_ROUND = "SELECT win_prize FROM result WHERE round = ?";

    private DataSource dataSource;

    public ResultDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addResult(int round, ResultDto resultDto) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeAddResult(round, resultDto, con);
        }
    }

    private void executeAddResult(int round, ResultDto resultDto, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_RESULT)) {
            setPstmtForAddResult(round, resultDto, pstmt);
            pstmt.executeUpdate();
        }
    }

    private void setPstmtForAddResult(int round, ResultDto resultDto, PreparedStatement pstmt) throws SQLException {
        int parameterIndex = 1;
        pstmt.setInt(parameterIndex++, round);
        for (Rank rank : Rank.values()) {
            pstmt.setInt(parameterIndex++, resultDto.getWinnersCountByRank(rank));
        }
        pstmt.setDouble(parameterIndex++, resultDto.getYield());
        pstmt.setDouble(parameterIndex, resultDto.getWinPrize());
    }

    public Map<Rank, Integer> findWinnerCountByRound(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeWinnerCountById(round, con);
        }
    }

    private Map<Rank, Integer> executeWinnerCountById(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_WINNER_COUNT_BY_ID)) {
            pstmt.setInt(1, round);
            return getResultWinnerCountById(pstmt);
        }
    }

    private Map<Rank, Integer> getResultWinnerCountById(PreparedStatement pstmt) throws  SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            Map<Rank, Integer> winnerCount = new TreeMap<>();
            if (getWinnerCount(rs, winnerCount)) return null;
            return winnerCount;
        }
    }

    private boolean getWinnerCount(ResultSet rs, Map<Rank, Integer> winnerCount) throws SQLException {
        if (!rs.next()) return true;

        for (Rank rank : Rank.values()) {
            winnerCount.put(rank, rs.getInt(rank.name()));
        }
        return false;
    }

    public double findYieldByRound(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindYield(round, con);
        }
    }

    private double executeFindYield(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_YIELD_BY_ID)) {
            pstmt.setInt(1, round);
            return getResultFindYield(pstmt);
        }
    }

    private double getResultFindYield(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            return (rs.next() ? rs.getDouble("yield") : -1);
        }
    }

    public long findWinPrizeByRound(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindWinPrize(round, con);
        }
    }

    private long executeFindWinPrize(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_WIN_PRIZE_BY_ROUND)) {
            pstmt.setInt(1, round);
            return getResultFindWinPrize(pstmt);
        }
    }

    private long getResultFindWinPrize(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            return (rs.next() ? rs.getLong("win_prize") : -1);
        }
    }
}
