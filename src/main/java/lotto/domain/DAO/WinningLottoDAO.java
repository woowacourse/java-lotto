package lotto.domain.DAO;

import lotto.domain.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static lotto.domain.util.DBUtil.getConnection;

public class WinningLottoDAO {

    public static void addWinningLottoInfo(String winningNumber, int bonusBall) throws SQLException {
        Connection con = DBUtil.getConnection();
        String query = "INSERT INTO winning_lotto_info(lottoRound, winningNumber, bonusBall) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, getCurrentLottoRound() + 1);
        pstmt.setString(2, winningNumber);
        pstmt.setInt(3, bonusBall);
        pstmt.executeUpdate();
        DBUtil.closeConnection(con);
    }

    public static int getCurrentLottoRound() throws SQLException {
        String query = "SELECT MAX(lottoRound) FROM winning_lotto_info";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            rs.close();
            return 0;
        }
        return rs.getInt("MAX(lottoRound)");
    }

    public static void deleteWinningLottoInfoByLottoRound(int lottoRound) throws SQLException {
        String query = "DELETE FROM winning_lotto_info where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, lottoRound);
        pstmt.executeUpdate();
    }

    public static void selectWinningNumbersByCurrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        Connection con = DBUtil.getConnection();
        String query = "SELECT * FROM winning_lotto_info where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, inquiredRound);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            rs.close();
            return;
        }
        model.put("winningNumbers", rs.getString("winningNumber"));
        model.put("bonusBall", rs.getInt("bonusBall"));
        DBUtil.closeConnection(con);
    }
}
