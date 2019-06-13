package lotto.domain.dao;

import lotto.domain.WinningLotto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDao extends Connector {
    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winning_lotto (round, lotto_num1, lotto_num2, lotto_num3, lotto_num4, lotto_num5, lotto_num6, bonus_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        for (int i = 0; i < 6; i++) {
            pstmt.setInt(i + 2, winningLotto.getWinningLottoValueByIndex(i));
        }
        pstmt.setInt(8, winningLotto.getBonusBallValue());
        pstmt.executeUpdate();
    }

    public List<Integer> findWinningLottoByRound(int round) throws SQLException {
        String query = "SELECT lotto_num1, lotto_num2, lotto_num3, lotto_num4, lotto_num5, lotto_num6 FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Integer> winningLotto = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningLotto.add(rs.getInt("lotto_num" + i));
        }
        return winningLotto;
    }

    public int findBonusNumByRound(int round) throws SQLException {
        String query = "SELECT bonus_num FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getInt("bonus_num");
    }
}
