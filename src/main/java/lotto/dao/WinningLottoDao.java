package lotto.dao;

import lotto.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDao {

    private static final String quaryForAddWinningLotto = "INSERT INTO winninglotto (numbers,bonus_no,round_no) VALUES (?,?,?)";
    private static final String queryForFindWinningLotto = "SELECT numbers,bonus_no FROM winninglotto WHERE round_no=?";

    private Connection con = null;

    private WinningLottoDao() {
    }

    private static class WinningLottoDaoHolder {
        private static final WinningLottoDao INSTANCE = new WinningLottoDao();
    }

    public static WinningLottoDao getInstance() {
        return WinningLottoDaoHolder.INSTANCE;
    }

    public void addWinningLotto(WinningLotto winningLotto, int round) throws SQLException {
        PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), quaryForAddWinningLotto);

        try {
            pstmt.setString(1, winningLotto.getNumbers());
            pstmt.setString(2, winningLotto.getBonusBall().toString());
            pstmt.setInt(3, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
        }
    }

    public WinningLotto findAllByRound(int round) throws SQLException {
        PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), queryForFindWinningLotto);

        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return WinningLottoParser.parseWinningLotto(rs.getString("numbers"), rs.getString("bonus_no"));
        }
        DBConnection.closeConnection(con);
        return null;
    }
}
