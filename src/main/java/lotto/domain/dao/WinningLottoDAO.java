package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.NumberSet;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {

    public static final int NEXT_ROUND = 1;

    public void addWinningLotto(WinningLottoDTO winningLottoDTO) throws SQLException {
        Connection con = ConnectionGenerator.getConnection();
        String query = "INSERT INTO winning_lotto " +
                "(first_num, second_num, third_num, forth_num, fifth_num, sixth_num, bonus_num, round) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);

        pstmt.setInt(1, winningLottoDTO.getFirstNum().getNumber());
        pstmt.setInt(2, winningLottoDTO.getSecondNum().getNumber());
        pstmt.setInt(3, winningLottoDTO.getThirdNum().getNumber());
        pstmt.setInt(4, winningLottoDTO.getForthNum().getNumber());
        pstmt.setInt(5, winningLottoDTO.getFifthNum().getNumber());
        pstmt.setInt(6, winningLottoDTO.getSixthNum().getNumber());
        pstmt.setInt(7, winningLottoDTO.getBonusNum().getNumber());
        pstmt.setInt(8, winningLottoDTO.getRound());

        pstmt.executeUpdate();
        ConnectionGenerator.closeConnection(con);
    }

    public int getNewRound() throws SQLException {
        String query = "SELECT round FROM winning_lotto ORDER BY round DESC";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return 1;
        }

        return rs.getInt("round") + NEXT_ROUND;
    }

    public WinningLottoDTO getWinningLotto(int round) throws SQLException {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();

        if(!rs.next()) {
            return winningLottoDTO;
        }

        winningLottoDTO.setRound(rs.getInt("round"));
        winningLottoDTO.setFirstNum(NumberSet.of(rs.getInt("first_num")));
        winningLottoDTO.setSecondNum(NumberSet.of(rs.getInt("second_num")));
        winningLottoDTO.setThirdNum(NumberSet.of(rs.getInt("third_num")));
        winningLottoDTO.setForthNum(NumberSet.of(rs.getInt("forth_num")));
        winningLottoDTO.setFifthNum(NumberSet.of(rs.getInt("fifth_num")));
        winningLottoDTO.setSixthNum(NumberSet.of(rs.getInt("sixth_num")));
        winningLottoDTO.setBonusNum(NumberSet.of(rs.getInt("bonus_num")));

        return winningLottoDTO;
    }

    public void deleteWinningLotto(final int round) throws SQLException {
        String query = "DELETE FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }
}
