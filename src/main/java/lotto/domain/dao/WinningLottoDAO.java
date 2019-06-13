package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;
import lotto.domain.model.WinningLotto;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {

    public void addWinningLotto(WinningLottoDTO winningLottoDTO) throws SQLException {
        Connection con = ConnectionGenerator.getConnection();
        String query = "INSERT INTO winning_lotto " +
                "(round, first_num, second_num, third_num, forth_num, fifth_num, sixth_num, bonus_num) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, winningLottoDTO.getRound());

        for (int i = 0; i <= 5; i++) {
            pstmt.setInt(i + 2, winningLottoDTO.getWinningLotto().getLotto().get(i).getNumber());
        }
        pstmt.setInt(8, winningLottoDTO.getBonusNumber().getNumber());

        pstmt.executeUpdate();
        ConnectionGenerator.closeConnection(con);
    }

    public int getLatestRound() throws SQLException {
        String query = "SELECT round FROM winning_lotto ORDER BY round";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return 1;
        }

        return rs.getInt("round");
    }
//
//    public WinningLottoDTO getWinningLotto(int round) throws SQLException {
//        String query = "SELECCT * FROM winning_lotto where round = ?";
//        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
//        ResultSet rs = pstmt.executeQuery();
//        if(!rs.next()) {
//            return new WinningLottoDTO();
//        }
//        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();
//        winningLottoDTO.setRound(rs.getString("round"));
//        Lotto winningLotto = new Lotto(NumberSet.of(rs.getInt("first_num"), rs.getInt("second_num"), rs.getInt("third_num"), rs.getInt()));
//        winningLottoDTO.setWinningLotto
//        winningLottoDTO.setRound(rs.getString("round"));
//    }
}
