package lotto.application.lottoresult;

import lotto.application.LottoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoResultDAO {
    private static LottoResultDAO lottoResultDAO = null;

    private LottoResultDAO() {
    }

    public static LottoResultDAO getInstance() {
        if (lottoResultDAO == null) {
            lottoResultDAO = new LottoResultDAO();
        }
        return lottoResultDAO;
    }

    public void createNextRound() {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "INSERT INTO lotto_result(round) values(?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }

    public int getLatestRoundNum() {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "SELECT round FROM lotto_result ORDER BY round DESC LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return 0;
            return rs.getInt("round");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
        return 0;
    }

    public void deleteRound(int round) {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "delete from lotto_result where round = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.executeUpdate();

            String queryForIncrement = "ALTER TABLE lotto_result AUTO_INCREMENT = ?";
            pstmt = connection.prepareStatement(queryForIncrement);
            pstmt.setInt(1, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }
}

