package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.LottosResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoResultDAO {
    private final DBConnector controller;

    public LottoResultDAO(DBConnector controller) {
        this.controller = controller;
    }

    public int addLottoResult(LottosResult result, int round) throws SQLException {
        String query = "INSERT INTO lotto_result VALUES (?, ?, ?)";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setLong(2, result.getWinningMoney());
        pstmt.setDouble(3, Math.round(result.getROI() * 100));
        return pstmt.executeUpdate();
    }

    public long findByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getLong("winning_money");
    }

    public double getROIByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getLong("ROI");
    }

    public int deleteLottoResult(int round) throws SQLException {
        String query = "DELETE FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
