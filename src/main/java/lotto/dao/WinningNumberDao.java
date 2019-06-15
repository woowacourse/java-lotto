package lotto.dao;

import lotto.dto.WinningNumberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.dao.DatabaseConnection.closeConnection;
import static lotto.dao.DatabaseConnection.getConnection;

public class WinningNumberDao {
    private static final int WINNING_START_INDEX = 2;
    private static final int WINNING_LAST_INDEX = 8;

    private static WinningNumberDao winningNumberDao;

    private WinningNumberDao() {
    }

    public static WinningNumberDao getInstance() {
        if (winningNumberDao == null) {
            winningNumberDao = new WinningNumberDao();
        }
        return winningNumberDao;
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
        pstmt.setInt(WINNING_LAST_INDEX, winningNumberDto.getBonusNumber());
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
        winningNumberDto.setBonusNumber(rs.getInt(WINNING_LAST_INDEX));
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
}
