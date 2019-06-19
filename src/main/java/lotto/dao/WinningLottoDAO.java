package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.lotto.WinningLotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {
    private static String regexForDelteBracket = "(\\[|])+";

    private final DBConnector controller;

    public WinningLottoDAO(DBConnector controller) {
        this.controller = controller;
    }

    public int addWinningLotto(WinningLotto winningLotto, int round) throws SQLException {
        String query = "INSERT INTO winning_lotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setString(2,
                winningLotto.getLotto().getNumbers().toString().replaceAll((regexForDelteBracket), ""));
        pstmt.setString(3, winningLotto.getBonusNumber().toString());
        return pstmt.executeUpdate();
    }

    public WinningLotto findByRound(int round) throws SQLException {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new WinningLotto(rs.getString("numbers"),
                                rs.getString("bonus_number"));
    }

    public int deleteWinningLotto(int round) throws SQLException {
        String query = "DELETE FROM winning_lotto WHERE round=?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
