package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.lotto.WinningLotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAOImpl implements WinningLottoDAO {
    private static final String regexForDelteBracket = "(\\[|])+";
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class WinningLottoDAOImplHolder {
        private static final WinningLottoDAO instance = new WinningLottoDAOImpl();
    }

    public static WinningLottoDAO getInstance() {
        return WinningLottoDAOImplHolder.instance;
    }

    @Override
    public WinningLotto findByRound(int round) throws SQLException {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new WinningLotto(rs.getString("numbers"),
                rs.getString("bonus_number"));
    }

    @Override
    public int addWinningLotto(WinningLotto winningLotto, int round) throws SQLException {
        String query = "INSERT INTO winning_lotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setString(2,
                winningLotto.getLotto().getNumbers().toString().replaceAll((regexForDelteBracket), ""));
        pstmt.setString(3, winningLotto.getBonusNumber().toString());
        return pstmt.executeUpdate();
    }

    @Override
    public int deleteWinningLotto(int round) throws SQLException {
        String query = "DELETE FROM winning_lotto WHERE round=?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
