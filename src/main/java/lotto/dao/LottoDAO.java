package lotto.dao;

import lotto.dto.LottoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO extends DAO {
    public List<LottoDTO> selectRoundLotto(String round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<LottoDTO> lottoDTOS = new ArrayList<>();
        while (!resultSet.next()) {
            LottoDTO lottoDTO = new LottoDTO();
            lottoDTO.setRound(resultSet.getString("round"));
            lottoDTO.setNumbers(resultSet.getString("numbers"));
            lottoDTO.setRank(resultSet.getString("lotto_rank"));
            lottoDTOS.add(lottoDTO);
        }
        return lottoDTOS;
    }

    public void insertLotto(LottoDTO lottoDTO) throws SQLException {
        String query = "INSERT INTO lotto (round, numbers, lotto_rank) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, lottoDTO.getRound());
        preparedStatement.setString(2, lottoDTO.getNumbers());
        preparedStatement.setString(3, lottoDTO.getRank());
    }
}
