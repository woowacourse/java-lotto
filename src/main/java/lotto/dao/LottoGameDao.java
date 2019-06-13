package lotto.dao;

import lotto.dto.LottoGameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.dao.DataConnection.closeConnection;
import static lotto.dao.DataConnection.getConnection;

public class LottoGameDao {

    public void removeRound(final int testRound) throws SQLException {
        String sql = "DELETE FROM lotto_game WHERE round = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, testRound);
        pstmt.executeUpdate();

        closeConnection(con);
    }

    public void addRound(final int round, final int money, final int countOfManual) throws SQLException {
        String sql = "INSERT INTO lotto_game VALUES (?, ?, ?)";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);
        pstmt.setInt(2, money);
        pstmt.setInt(3, countOfManual);
        pstmt.executeUpdate();

        closeConnection(con);
    }

    public LottoGameDto findRoundById(final int round) throws SQLException {
        String sql = "SELECT * FROM lotto_game WHERE round = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        LottoGameDto lottoGame = combineLottoGame(rs);
        closeConnection(con);
        return lottoGame;
    }

    private LottoGameDto combineLottoGame(ResultSet rs) throws SQLException {
        LottoGameDto lottoGame = new LottoGameDto();
        rs.next();

        lottoGame.setRound(rs.getInt("round"));
        lottoGame.setMoney(rs.getInt("money"));
        lottoGame.setCountOfManual(rs.getInt("number_of_manual"));

        return lottoGame;
    }
}
