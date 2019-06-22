package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.LottosResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoResultDAOImpl implements LottoResultDAO {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class LottoResultDAOImplHolder {
        private static final LottoResultDAO instance = new LottoResultDAOImpl();
    }

    public static LottoResultDAO getInstance() {
        return LottoResultDAOImplHolder.instance;
    }

    @Override
    public long findByRound(int round) {
        String query = "SELECT * FROM lotto_result WHERE round = ?";
        long winningMoney = -1;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return winningMoney;

            winningMoney = rs.getLong("winning_money");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return winningMoney;
    }

    @Override
    public int addLottoResult(LottosResult lottosResult, int round) {
        String query = "INSERT INTO lotto_result VALUES (?, ?, ?)";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            pstmt.setLong(2, lottosResult.getWinningMoney());
            pstmt.setDouble(3, Math.round(lottosResult.getROI() * 100));
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteLottoResult(int round) {
        String query = "DELETE FROM lotto_result WHERE round = ?";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
