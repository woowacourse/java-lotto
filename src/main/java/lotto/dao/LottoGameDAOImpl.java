package lotto.dao;

import lotto.domain.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoGameDAOImpl implements LottoGameDAO {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class LottoGameDAOImplHolder {
        private static final LottoGameDAO instance = new LottoGameDAOImpl();
    }

    public static LottoGameDAO getInstance() {
        return LottoGameDAOImplHolder.instance;
    }

    @Override
    public int getLastRound() throws SQLException {
        String query = "SELECT * from lotto_game ORDER BY round DESC limit 1";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return rs.getInt("round");
    }

    @Override
    public int addLottoGame(int round) throws SQLException {
        String query = "INSERT INTO lotto_game VALUES (?)";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }

    @Override
    public int deleteLottoGame(int round) throws SQLException {
        String query = "DELETE FROM lotto_game WHERE round=?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
