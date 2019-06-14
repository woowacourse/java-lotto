package lotto.domain.dao;

import lotto.domain.WinningLotto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDao extends Connector {
    private static final int LOTTO_COUNT = 6;

    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winning_lotto (round, num1, num2, num3, num4, num5, num6, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        for (int i = 0; i < LOTTO_COUNT; i++) {
            pstmt.setInt(i + 2, winningLotto.getWinningLottoValueByIndex(i));
        }
        pstmt.setInt(8, winningLotto.getBonusBallValue());
        pstmt.executeUpdate();
    }

    public List<Integer> findWinningLottoByRound(int round) throws SQLException {
        String query = "SELECT num1, num2, num3, num4, num5, num6 FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Integer> winningLotto = new ArrayList<>();
        for (int i = 1; i <= LOTTO_COUNT; i++) {
            winningLotto.add(rs.getInt("num" + i));
        }
        return winningLotto;
    }

    public int findBonusNumByRound(int round) throws SQLException {
        String query = "SELECT bonus FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getInt("bonus");
    }
}
