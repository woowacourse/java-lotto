package lotto.domain.DAO;

import java.sql.*;
import java.util.Map;

import static lotto.domain.DAO.DBUtil.getConnection;

public class WinningLottoDAO {

    public static void addWinningLottoInfo(int lottoRound, String winningNumber, int bonusBall) throws SQLException {
        String query = "INSERT INTO winning_lotto_info(lottoRound, winningNumber, bonusBall) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, lottoRound);
        pstmt.setString(2, winningNumber);
        pstmt.setInt(3, bonusBall);
        pstmt.executeUpdate();
    }

    public static int getCurrentLottoRound() throws SQLException {
        String query = "SELECT MAX(lottoRound) FROM winning_lotto_info";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return 0;
        return rs.getInt("MAX(lottoRound)");
    }

    public static void deleteWinningLottoInfoByLottoRound(int lottoRound) throws SQLException {
        String query = "DELETE FROM winning_lotto_info where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, lottoRound);
        pstmt.executeUpdate();
    }

    public static void selectWholeResultByCurrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        Connection con = DBUtil.getConnection();
        String query = "SELECT * FROM winning_lotto_info where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, inquiredRound);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return;
        model.put("winningNumbers", rs.getString("winningNumber"));
        model.put("bonusBall", rs.getInt("bonusBall"));
        DBUtil.closeConnection(con);
    }
}
