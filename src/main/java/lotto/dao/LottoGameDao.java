package lotto.dao;

import lotto.dto.LottoGameDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningNumberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.dao.DatabaseConnection.closeConnection;
import static lotto.dao.DatabaseConnection.getConnection;

public class LottoGameDao {
    private static final int WINNING_START_INDEX = 2;
    private static final int WINNING_LAST_INDEX = 8;

    private static LottoGameDao lottoGameDao;

    private LottoGameDao() {
    }

    public static LottoGameDao getInstance() {
        if (lottoGameDao == null) {
            lottoGameDao = new LottoGameDao();
        }
        return lottoGameDao;
    }

    // TODO DAO Singleton으로 구현, 테이블 별로 DAO 나누기
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

    public void addWinningNumber(int round, WinningNumberDto winningNumberDto) throws SQLException {
        String sql = "INSERT INTO winning_lotto VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        parseValuesForQuery(round, winningNumberDto, pstmt);

        pstmt.executeUpdate();
        closeConnection(con);
    }

    private void parseValuesForQuery(final int round, final WinningNumberDto winningNumberDto,
                                     final PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, round);
        List<Integer> winningNumbers = winningNumberDto.getNumbers();
        for (int i = WINNING_START_INDEX; i < WINNING_LAST_INDEX; i++) {
            pstmt.setInt(i, winningNumbers.get(i - 2));
        }
        pstmt.setInt(WINNING_LAST_INDEX, winningNumberDto.getBonusBall());
    }

    public WinningNumberDto findWinningLottoByRound(final int round) throws SQLException {
        String sql = "SELECT * FROM winning_lotto WHERE id = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        WinningNumberDto winningNumberDto = combineWinningNumber(rs);
        closeConnection(con);
        return winningNumberDto;
    }

    private WinningNumberDto combineWinningNumber(ResultSet rs) throws SQLException {
        WinningNumberDto winningNumberDto = new WinningNumberDto();
        rs.next();
        List<Integer> winningLottoNumbers = new ArrayList<>();

        for (int i = WINNING_START_INDEX; i < WINNING_LAST_INDEX; i++) {
            winningLottoNumbers.add(rs.getInt(i));
        }

        winningNumberDto.setNumbers(winningLottoNumbers);
        winningNumberDto.setBonusBall(rs.getInt(WINNING_LAST_INDEX));
        return winningNumberDto;
    }

    public void removeWinningNumber(int round) throws SQLException {
        String sql = "DELETE FROM winning_lotto WHERE id = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);

        pstmt.executeUpdate();
        closeConnection(con);
    }

    public Object findResultByRound(final int round) {
        return null;
    }

    public void addResult(final int round, final ResultDto resultDto) {

    }
}
