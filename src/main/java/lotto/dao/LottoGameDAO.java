package lotto.dao;

import lotto.domain.DBConnectionController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoGameDAO {
    private final DBConnectionController controller;

    public LottoGameDAO(DBConnectionController controller) {
        this.controller = controller;
    }

    public int addLottoGame(int round) throws SQLException {
        String query = "INSERT INTO lotto_game VALUES (?)";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }

    public int getLastRound() throws SQLException {
        String query = "SELECT * from lotto_game ORDER BY round DESC limit 1";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return rs.getInt("round");
    }

    public int deleteLottoGame(int round) throws SQLException {
        String query = "DELETE FROM lotto_game WHERE round=?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
