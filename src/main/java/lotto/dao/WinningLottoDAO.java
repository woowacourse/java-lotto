package lotto.dao;

import lotto.dto.WinningLottoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO extends DAO {
    public WinningLottoDTO selectWinningLotto(String round) throws SQLException {
        String query = "SELECT * FROM winningLotto WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet re = pstmt.executeQuery();
        re.next();

        return new WinningLottoDTO(re.getString("round"),
                re.getString("numbers"),
                re.getString("bonus"));
    }

    public void insertWinningLotto(WinningLottoDTO winningLottoDto) throws SQLException {
        String query = "INSERT INTO winningLotto (round, numbers, bonus) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, winningLottoDto.getRound());
        pstmt.setString(2, winningLottoDto.getNumbers());
        pstmt.setString(3, winningLottoDto.getBonusNumber());

        pstmt.executeUpdate();
    }
}
