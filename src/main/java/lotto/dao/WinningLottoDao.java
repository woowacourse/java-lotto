package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.WinningLotto;
import lotto.util.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class WinningLottoDao {
    public static WinningLottoDao getInstance() {
        return WinningLottoDaoHolder.INSTANCE;
    }

    public void addWinningLotto(WinningLotto winningLotto, int roundNo) {
        try {
            String query = "INSERT INTO winning_lotto(NO_1, NO_2, NO_3, NO_4, NO_5, NO_6, BONUS_NO, ROUND_NO)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            pstmt.setInt(1, winningLotto.getWinningLottoNo(0));
            pstmt.setInt(2, winningLotto.getWinningLottoNo(1));
            pstmt.setInt(3, winningLotto.getWinningLottoNo(2));
            pstmt.setInt(4, winningLotto.getWinningLottoNo(3));
            pstmt.setInt(5, winningLotto.getWinningLottoNo(4));
            pstmt.setInt(6, winningLotto.getWinningLottoNo(5));
            pstmt.setInt(7, winningLotto.getBonusNo());
            pstmt.setInt(8, roundNo);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
    }

    public WinningLotto findByRoundNo(int roundNo) {
        try {
            String query = "SELECT * FROM winning_lotto WHERE round_no = ?";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            pstmt.setInt(1, roundNo);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return null;

            return new WinningLotto(
                    Lotto.of(Arrays.asList(rs.getInt("NO_1"),
                            rs.getInt("NO_2"),
                            rs.getInt("NO_3"),
                            rs.getInt("NO_4"),
                            rs.getInt("NO_5"),
                            rs.getInt("NO_6"))),
                    LottoNo.of(rs.getInt("BONUS_NO"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbConnector.closeConnection();
        }
    }

    private static class WinningLottoDaoHolder {
        static final WinningLottoDao INSTANCE = new WinningLottoDao();
    }
}
