package lotto.domain.dao;

import lotto.domain.WinningLotto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDao {
    private static final int LOTTO_COUNT = 6;
    private static final String INSERT_WINNING_LOTTO = "INSERT INTO winning_lotto (round, num1, num2, num3, num4, num5, num6, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_WINNING_LOTTO = "SELECT num1, num2, num3, num4, num5, num6 FROM winning_lotto WHERE round = ?";
    private static final String SELECT_BONUS_NUM = "SELECT bonus FROM winning_lotto WHERE round = ?";

    private DataSource dataSource;

    public WinningLottoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeAddWinningLotto(round, winningLotto, con);
        }
    }

    private void executeAddWinningLotto(int round, WinningLotto winningLotto, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_WINNING_LOTTO)) {
            pstmt.setInt(1, round);
            setParamAddWinningLotto(winningLotto, pstmt);
            pstmt.setInt(8, winningLotto.getBonusBallValue());
            pstmt.executeUpdate();
        }
    }

    private void setParamAddWinningLotto(WinningLotto winningLotto, PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < LOTTO_COUNT; i++) {
            pstmt.setInt(i + 2, winningLotto.getWinningLottoValueByIndex(i));
        }
    }

    public List<Integer> findWinningLottoByRound(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindWinningLottoByRound(round, con);
        }
    }

    private List<Integer> executeFindWinningLottoByRound(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_WINNING_LOTTO)) {
            pstmt.setInt(1, round);
            return getResultFindWinningLottoByRound(pstmt);
        }
    }

    private List<Integer> getResultFindWinningLottoByRound(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) return null;

            List<Integer> winningLotto = new ArrayList<>();
            for (int i = 1; i <= LOTTO_COUNT; i++) {
                winningLotto.add(rs.getInt("num" + i));
            }
            return winningLotto;
        }
    }

    public int findBonusNumByRound(int round) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindBonusNumByRound(round, con);
        }
    }

    private int executeFindBonusNumByRound(int round, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_BONUS_NUM)) {
            pstmt.setInt(1, round);
            return getResultFindBonusNumByRound(pstmt);
        }
    }

    private int getResultFindBonusNumByRound(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            return (rs.next() ? rs.getInt("bonus") : -1);
        }
    }
}
