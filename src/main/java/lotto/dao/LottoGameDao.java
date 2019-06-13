package lotto.dao;

import lotto.dto.LottoGameDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningNumberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static lotto.dao.DataConnection.closeConnection;
import static lotto.dao.DataConnection.getConnection;
import static lotto.dao.LottoDao.BOUGHT_LOTTO_NUMBER_FROM_INDEX;
import static lotto.dao.LottoDao.BOUGHT_LOTTO_NUMBER_TO_INDEX;

public class LottoGameDao {

    private static final int WINNING_BONUS_INDEX = 8;
    private static final List<String> PRIZE_NAMES = Arrays.asList("first", "second", "third", "fourth", "fifth", "none");

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

        for (int i = BOUGHT_LOTTO_NUMBER_FROM_INDEX + 2; i < BOUGHT_LOTTO_NUMBER_TO_INDEX + 2; i++) {
            winningLottoNumbers.add(rs.getInt(i));
        }

        winningNumberDto.setNumbers(winningLottoNumbers);
        winningNumberDto.setBonusBall(rs.getInt(WINNING_BONUS_INDEX));
        return winningNumberDto;
    }

    public ResultDto findResultByRound(final int round) throws SQLException {
        String sql = "SELECT * FROM result WHERE id = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        ResultDto resultDto = conbineResultDto(rs);
        closeConnection(con);
        return resultDto;
    }

    private ResultDto conbineResultDto(ResultSet rs) throws SQLException {
        ResultDto resultDto = new ResultDto();
        Map<String, Integer> prize = new HashMap<>();
        rs.next();

        for (String prizeName : PRIZE_NAMES) {
            prize.put(prizeName, rs.getInt(prizeName));
        }

        resultDto.setPrize(prize);
        resultDto.setWinningMoney(rs.getInt("winning_money"));
        return resultDto;
    }
}
