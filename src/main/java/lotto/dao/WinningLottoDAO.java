package lotto.dao;

import lotto.dto.WinningLottoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO extends DAO {
    public List<WinningLottoDTO> selectWinningLotto(String round) throws SQLException {
        String query = "SELECT * FROM winningLotto WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet re = pstmt.executeQuery();

        List<WinningLottoDTO> winningLottoDTOs = new ArrayList<>();
        while (!re.next()) {
            WinningLottoDTO winningLottoDTO = new WinningLottoDTO();
            winningLottoDTO.setRound(re.getString("round"));
            winningLottoDTO.setNumbers(re.getString("numbers"));
            winningLottoDTO.setBonusNumber(re.getString("bonus"));
            winningLottoDTOs.add(winningLottoDTO);
        }
        return winningLottoDTOs;
    }

    public void insertWinningLotto(WinningLottoDTO winningLottoDto) throws SQLException {
        String query = "INSERT INTO winningLotto (round, numbers, bonus) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, winningLottoDto.getRound());
        pstmt.setString(2, winningLottoDto.getNumbers());
        pstmt.setString(3, winningLottoDto.getBonusNumber());
    }
}
