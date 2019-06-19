package lotto.dao;

import lotto.dbconnction.DBConnection;
import lotto.domain.WinningLotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDao {
    public static void addWinningLotto(WinningLotto winningLotto, int lottoRound) {
        try {
            String query = "INSERT INTO winningLotto VALUES (?,?,?)";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);

            pstm.setInt(1, lottoRound);
            pstm.setString(2, winningLotto.toString());
            pstm.setInt(3, winningLotto.getBonus());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String offerWinningNumber(int lottoRound) {
        try {
            String query = "SELECT winningLottoNumber FROM winningLotto WHERE round = ?";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, lottoRound);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) return null;

            return rs.getString(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static int offerBonusNumber(int lottoRound) {
        try {
            String query = "SELECT bonus FROM winningLotto WHERE round = ?";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, lottoRound);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) return 0;

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

}
