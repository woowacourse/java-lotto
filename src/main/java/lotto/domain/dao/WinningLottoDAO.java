package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.NumberSet;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {

    private static final int FIRST_ROUND = 1;

    public void addWinningLotto(WinningLottoDTO winningLottoDTO) {
        try (Connection con = ConnectionGenerator.getConnection()) {
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
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public int getLatestRound() {
        int latestRound = FIRST_ROUND;

        try (Connection con = ConnectionGenerator.getConnection()) {
            String query = "SELECT round FROM winning_lotto ORDER BY round DESC";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return latestRound;
            latestRound = rs.getInt("round");
        } catch (SQLException e) {
            e.getMessage();
        }

        return latestRound;
    }

    public WinningLottoDTO getWinningLotto(int round) {
        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();

        try (Connection con = ConnectionGenerator.getConnection()) {
            String query = "SELECT * FROM winning_lotto WHERE round = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) return winningLottoDTO;

            winningLottoDTO.setRound(rs.getInt("round"));
            winningLottoDTO.setFirstNum(NumberSet.of(rs.getInt("first_num")));
            winningLottoDTO.setSecondNum(NumberSet.of(rs.getInt("second_num")));
            winningLottoDTO.setThirdNum(NumberSet.of(rs.getInt("third_num")));
            winningLottoDTO.setForthNum(NumberSet.of(rs.getInt("forth_num")));
            winningLottoDTO.setFifthNum(NumberSet.of(rs.getInt("fifth_num")));
            winningLottoDTO.setSixthNum(NumberSet.of(rs.getInt("sixth_num")));
            winningLottoDTO.setBonusNum(NumberSet.of(rs.getInt("bonus_num")));
        } catch (SQLException e) {
            e.getMessage();
        }
        return winningLottoDTO;
    }

    public void deleteWinningLotto(final int round) {
        try (Connection con = ConnectionGenerator.getConnection()) {
            String query = "DELETE FROM winning_lotto WHERE round = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
