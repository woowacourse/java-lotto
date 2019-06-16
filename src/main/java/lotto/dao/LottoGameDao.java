package lotto.dao;

import lotto.dto.LottoGameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.dao.DatabaseConnection.closeConnection;
import static lotto.dao.DatabaseConnection.getConnection;

public class LottoGameDao {
    private static LottoGameDao lottoGameDao;

    private LottoGameDao() {
    }

    public static LottoGameDao getInstance() {
        if (lottoGameDao == null) {
            lottoGameDao = new LottoGameDao();
        }
        return lottoGameDao;
    }

    public int currentRound() throws SQLException {
        String sql = "SELECT count(*) FROM lotto_game";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        return rs.getInt(1);
    }

    public void removeRound(final int testRound) throws SQLException {
        String sql = "DELETE FROM lotto_game WHERE round = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, testRound);
        pstmt.executeUpdate();

        closeConnection(con);
    }

    public void addRound(LottoGameDto lottoGameDto) throws SQLException {
        String sql = "INSERT INTO lotto_game VALUES (?, ?, ?)";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, lottoGameDto.getRound());
        pstmt.setInt(2, lottoGameDto.getMoney());
        pstmt.setInt(3, lottoGameDto.getCountOfManual());
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
